package com.fanfengping.zeus.util;

import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MysqlUtil {
    @Autowired
    DatabaseCompareResultService databaseCompareResultService;

    private Database dbs;
    private Database dbt;
    private String message = "";
    private DatabaseCompareResult databaseCompare;
    private String timestamp;

    private void log(String message, Integer tblCompResult){
        databaseCompare = new DatabaseCompareResult(timestamp, tblCompResult, dbs.getEng(), dbs.getId(), dbs.getEnv(), dbs.getUrl(), dbt.getId(), dbt.getEnv(), dbt.getUrl(), message, "");

        databaseCompareResultService.add(databaseCompare);
        System.out.println(String.format("[基准库：%s - 比对库：%s] - %s", dbs.getUrl(), dbt.getUrl(), message));
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
            int nullable = rs.getInt("NULLABLE");

            res.put(columnName, String.format("%s-%s-%s-%s", columnType, datasize, digits, nullable));
        }
        return res;
    }
}
