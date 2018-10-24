package com.fanfengping.zeus.util;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.DataDictionary;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.service.cicd.DataDictionaryService;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MysqlUtil {
    @Autowired
    DatabaseCompareResultService databaseCompareResultService;
    @Autowired
    DataDictionaryService dataDictionaryService;

    private Database dbs;
    private Database dbt;
    private String message = "";
    private DatabaseCompareResult databaseCompare;
    private String timestamp;

    private void log(String message, Integer tblCompResult){
        databaseCompare = new DatabaseCompareResult(timestamp, tblCompResult, dbs.getEng(), dbs.getId(), dbs.getEnv(), dbs.getUrl(), dbt.getId(), dbt.getEnv(), dbt.getUrl(), message, "");
        databaseCompareResultService.add(databaseCompare);
    }

    public void genDataDictionary(Database dbs) {
        ResponseJson responseJson = new ResponseJson(Codes.DICTIONARY, Codes.DICTIONARY_INSERT);
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet rs;

        String sql = "SELECT ta.TABLE_SCHEMA, ta.TABLE_NAME, ta.TABLE_COMMENT, ta.ENGINE, ta.TABLE_COLLATION, " +
                "       co.COLUMN_NAME, co.COLUMN_COMMENT, co.COLUMN_KEY, " +
                "       co.COLUMN_TYPE, co.IS_NULLABLE, co.COLUMN_DEFAULT, co.CHARACTER_SET_NAME, co.COLLATION_NAME " +
                "FROM information_schema.`TABLES` ta, information_schema.`COLUMNS` co " +
                "WHERE ta.TABLE_NAME = co.TABLE_NAME AND ta.TABLE_SCHEMA = co.TABLE_SCHEMA " +
                "  AND ta.TABLE_TYPE = 'BASE TABLE' AND ta.TABLE_SCHEMA = '" + dbs.getUrl().split("/")[3].split("\\?")[0] + "' " +
                " ORDER BY ta.TABLE_SCHEMA, ta.TABLE_NAME, co.ORDINAL_POSITION ";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbs.getUrl(), dbs.getUsername(), dbs.getPassword());

            String sn = "" + System.currentTimeMillis();

            if (!conn.isClosed()) {
                responseJson.succ(200,"Succeeded connecting to the Database!").data(dbs);
                log.info(responseJson.toString());
            }

            sqlStatement = conn.createStatement();

            rs = sqlStatement.executeQuery(sql);

            while(rs.next()) {
                String tableSchema = rs.getString("TABLE_SCHEMA");
                String tableName = rs.getString("TABLE_NAME");
                String tableComment = CharsetUtil.convert(rs.getString("TABLE_COMMENT"), "UTF-8");
                String columnName = rs.getString("COLUMN_NAME");
                String columnComment = CharsetUtil.convert(rs.getString("COLUMN_COMMENT"), "UTF-8");
                String engine = rs.getString("ENGINE");
                String tableCollation = rs.getString("TABLE_COLLATION");
                String columnKey = rs.getString("COLUMN_KEY");
                String columnType = rs.getString("COLUMN_TYPE");
                String nullable = rs.getString("IS_NULLABLE");
                String columnDefault = rs.getString("COLUMN_DEFAULT") == null ? "" : rs.getString("COLUMN_DEFAULT");
                String characterSetName = rs.getString("CHARACTER_SET_NAME");
                String collationName = rs.getString("COLLATION_NAME");

                DataDictionary dataDictionary = new DataDictionary(sn, dbs.getId(), dbs.getEnv(), dbs.getEng(), dbs.getUrl(), dbs.getUsername(), dbs.getPassword(),
                        tableSchema, tableName, tableComment, engine, tableCollation,
                        columnName, columnComment, columnKey, columnType, nullable, columnDefault, characterSetName, collationName);
                dataDictionaryService.add(dataDictionary);
            }
        } catch (ClassNotFoundException cnfe) {
            responseJson.fail(999, "数据库驱动失败。" + cnfe.getMessage()).data(dbs);
            log.error(responseJson.toString(), cnfe);
        } catch (Exception e){
            responseJson.fail(999, "数据库连接失败。" + e.getMessage()).data(dbs);
            log.error(responseJson.toString(), e);
        } finally {
            try {
                if (sqlStatement != null) {
                    sqlStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                responseJson.fail(999, "数据库语句执行异常。" + sqle.getMessage()).data(dbs);
                log.error(responseJson.toString(), sqle);
            }
        }
    }

    public void compareAllTables(Database dbs, Database dbt, String timestamp) throws SQLException {
        this.dbs = dbs;
        this.dbt = dbt;
        this.timestamp = timestamp;

        try (Connection dbsConnection = DriverManager.getConnection(dbs.getUrl(), dbs.getUsername(), dbs.getPassword());
             Connection dbtConnection = DriverManager.getConnection(dbt.getUrl(), dbt.getUsername(), dbt.getPassword())) {
            DatabaseMetaData dbsMetData = dbsConnection.getMetaData();
            DatabaseMetaData dbtMetData = dbtConnection.getMetaData();
            List<String> dbsTables = getAllTableAsString(dbsMetData);
            List<String> dbtTables = getAllTableAsString(dbtMetData);

            if (dbsTables.size() != dbtTables.size()) {
                message = String.format("表数量不一致。基准库：%d，比对库：%d", dbsTables.size(), dbtTables.size());
                log(message, -1);
            }

            for (int i = 0, l = dbsTables.size(); i < l; i++) {
                boolean sexist = false;

                for (int j = 0; j < dbtTables.size(); j++) {
                    if (dbsTables.get(i).equals(dbtTables.get(j))) {
                        sexist = true;

                        message = String.format("开始比对数据表：%s", dbsTables.get(i));
                        log(message, 0);

                        matchOneTable(dbsMetData, dbtMetData, dbsTables.get(i));
                        break;
                    }
                }

                if (!sexist) {
                    message = String.format("数据表：%s 在比对库中缺失", dbsTables.get(i));
                    log(message, -1);
                }
            }

            for (int i = 0, l = dbtTables.size(); i < l; i++) {
                boolean texist = false;

                for (int j = 0; j < dbsTables.size(); j++) {
                    if (dbtTables.get(i).equals(dbsTables.get(j))) {
                        texist = true;
                        break;
                    }
                }

                if (!texist) {
                    message = String.format("数据表：%s 在比对库中冗余", dbtTables.get(i));
                    log(message, -1);
                }
            }
        } catch (Exception e){
            message = String.format("数据库[%s | %s] - [%s | %s] 连接失败。。。", dbs.getUrl(), dbs.getUsername(), dbt.getUrl(), dbt.getUsername());
            log(message, -1);
            e.printStackTrace();
        }
    }

    public List<String> getAllTableAsString(DatabaseMetaData dbMetData) throws SQLException {
        List<String> res = new ArrayList<>();
        try (ResultSet resultSet = dbMetData.getTables(null, null, null, new String[]{"TABLE"})) {
            while (resultSet.next()) {
                if (resultSet.getString(4) != null
                        && (resultSet.getString(4).equalsIgnoreCase("TABLE"))) {
                    String tableName = resultSet.getString(3).toLowerCase();
                    res.add(tableName);
                }
            }
        }
        return res;
    }

    public void matchOneTable(DatabaseMetaData dbsMetData, DatabaseMetaData dbtMetData, String tableName) throws SQLException {
        try (ResultSet dbsColRet = dbsMetData.getColumns(null, "%", tableName, "%");
             ResultSet dbtColRet = dbtMetData.getColumns(null, "%", tableName, "%")) {
            Map<String, String> dbsTable = assembleMap(dbsColRet);
            Map<String, String> dbtTable = assembleMap(dbtColRet);

            if (dbsTable.size() > 0 && dbtTable.size() > 0) {
                for (Map.Entry<String, String> entry : dbsTable.entrySet()) {
                    if (dbtTable.containsKey(entry.getKey())) {
                        if (!dbtTable.get(entry.getKey()).equals(entry.getValue())) {
                            message = String.format("比对库表[%s]字段[%s]基本设置不一致。[%s >>> %s]", tableName, entry.getKey(), entry.getValue(), dbtTable.get(entry.getKey()));
                            log(message, -1);
                        }
                    } else {
                        message = String.format("比对库表[%s]字段缺失，字段名称为：%s", tableName, entry.getKey());
                        log(message, -1);
                    }
                }
            } else if (dbsTable.size() < 1) {
                message = String.format("基准库表[%s]存在空表现象", tableName);
                log(message, -1);
            } else {
                message = String.format("比对库表[%s]存在空表现象", tableName);
                log(message, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Map<String, String> assembleMap(ResultSet rs) throws SQLException {
        Map<String, String> res = new HashMap<>();
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String columnType = rs.getString("TYPE_NAME");
            int datasize = rs.getInt("COLUMN_SIZE");
            int digits = rs.getInt("DECIMAL_DIGITS");
            String nullable = rs.getString("IS_NULLABLE");
            String defaultValue = rs.getString("COLUMN_DEF");
            String columnComment = rs.getString("REMARKS");

            // 字段类型，字段长度，小数位数，是否可为空，默认值，备注
            res.put(columnName, String.format("%s | %s | %s | %s | %s | %s", columnType, datasize, digits, nullable, defaultValue, columnComment));
        }
        return res;
    }
}
