<template>
  <el-container>
    <br>
    <el-header>
      <el-row>
        <el-form :model="query" :inline="true">
          <el-form-item>
            <el-select v-model="query.env" clearable filterable placeholder="请选择数据库环境...">
              <el-option v-for="item in conditions.envs" :key="item.value" :label="item.label" :value="item.value">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-input v-model="query.eng" placeholder="请输入英文简称" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="query.chs" placeholder="请输入中文简称" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="handleBtnQuery(query)" type="primary" icon="el-icon-search">搜索</el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus">新增</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        hight="250"
        border
        stripe
        max-height="650"
        style="width: 100%">
        <el-table-column fixed type="index" width="50"></el-table-column>
        <!--<el-table-column fixed prop="id" label="主键" width="50" v-show="false"></el-table-column>-->
        <el-table-column fixed prop="eng" label="英文简称" width="180"></el-table-column>
        <el-table-column prop="chs" label="中文简称" width="150"></el-table-column>
        <el-table-column prop="env" label="环境标识" width="100"></el-table-column>
        <el-table-column prop="benchmark" label="基准库" width="70"></el-table-column>
        <el-table-column prop="type" label="数据库类型" width="100"></el-table-column>
        <el-table-column prop="driver" label="数据库驱动" width="210"></el-table-column>
        <el-table-column prop="url" label="数据库URL" width="400"></el-table-column>
        <el-table-column prop="account" label="数据库账号" width="150"></el-table-column>
        <el-table-column prop="password" label="数据库密码" width="150"></el-table-column>
        <el-table-column prop="note" label="备注" width="200"></el-table-column>
        <el-table-column prop="creater" label="创建人" width="100"></el-table-column>
        <el-table-column prop="ctime" label="创建时间" width="170"></el-table-column>
        <el-table-column prop="updater" label="更新人" width="100"></el-table-column>
        <el-table-column prop="utime" label="更新时间" width="170"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small">编辑</el-button>
            <el-button type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="新增数据库" :visible.sync="dialogFormVisible">
        <el-form :model="formData">
          <el-form-item label="环境：" :label-width="formLabelWidth">
            <el-select v-model="formData.env" clearable placeholder="请选择数据库环境...">
              <el-option v-for="item in conditions.envs" :key="item.value" :label="item.label" :value="item.value">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="英文名称：" :label-width="formLabelWidth">
            <el-input v-model="formData.eng" clearable placeholder="请输入英文名称..."></el-input>
          </el-form-item>
          <el-form-item label="中文名称：" :label-width="formLabelWidth">
            <el-input v-model="formData.chs" clearable placeholder="请输入中文名称..."></el-input>
          </el-form-item>
          <el-form-item label="基准库：" :label-width="formLabelWidth">
            <el-select v-model="formData.benchmark" placeholder="请选择...">
              <el-option label="是" value="1"></el-option>
              <el-option label="否" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="类型：" :label-width="formLabelWidth">
            <el-select v-model="formData.type" placeholder="请选择数据库类型...">
              <el-option label="MYSQL" value="MYSQL"></el-option>
              <el-option label="ORACLE" value="ORACLE"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="驱动：" :label-width="formLabelWidth">
            <el-select v-model="formData.driver" placeholder="请选择数据库驱动...">
              <el-option label="MYSQL驱动" value="com.mysql.jdbc.Driver"></el-option>
              <el-option label="ORACLE驱动" value="oracle.jdbc.driver.OracleDriver"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="链接：" :label-width="formLabelWidth">
            <el-input v-model="formData.url" clearable placeholder="请输入链接...">
            </el-input>
          </el-form-item>
          <el-form-item label="账号：" :label-width="formLabelWidth">
            <el-input v-model="formData.account" clearable placeholder="请输入账号...">
            </el-input>
          </el-form-item>
          <el-form-item label="密码：" :label-width="formLabelWidth">
            <el-input v-model="formData.password" clearable placeholder="请输入密码...">
            </el-input>
          </el-form-item>
          <el-form-item label="备注：" :label-width="formLabelWidth">
            <el-input v-model="formData.note" type="textarea" :autosize="{maxRows: 4}" clearable placeholder="请输入备注信息...">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
  import {DbAdd, DbUpdate, DbFindAllByConditions, DbDelete, DbSetBenchmark} from '@/service/database'
  export default {
    methods: {
      handleBtnQuery(query) {
        console.log(query);
        
        DbFindAllByConditions(query).then(res => {
          console.log("查询结果");
          console.log(res);
          this.tableData = res
        })
          .catch(err => {
            console.log(err)
          })
      },
      handleClick(row) {
        console.log(row);
      }
    },
    data() {
      return {
        dialogTableVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        formData: {
          env: '',
          eng: '',
          chs: '',
          benchmark: 0,
          type: 'MYSQL',
          driver: 'com.mysql.jdbc.Driver',
          url: '',
          account: '',
          password: '',
          note: ''
        },
        conditions: {
          envs: [
            {value: 'SIT01', label: '测试环境'},
            {value: 'SIT04', label: '开发环境'},
            {value: 'PRE', label: '预发环境'},
            {value: 'DOCKER', label: '容器环境'}
          ],
          engs: [
            {value: 'shenma-admittance', label: 'shenma-admittance(C端准入系统)'},
            {value: 'shenma-cfbs', label: 'shenma-cfbs(业务系统)'},
            {value: 'shenma-channel', label: 'shenma-channel(渠道系统)'},
            {value: 'shenma-chitu', label: 'shenma-chitu(赤兔系统)'},
            {value: 'shenma-chnl', label: 'shenma-chnl(新渠道系统)'},
            {value: 'shenma-collection', label: 'shenma-collection(催收系统)'},
            {value: 'shenma-css', label: 'shenma-css(什马清结算系统)'},
            {value: 'shenma-ev-order', label: 'shenma-ev-order(EV订单系统)'},
            {value: 'shenma-fpss', label: 'shenma-fpss(资金兑付系统)'},
            {value: 'shenma-frss', label: 'shenma-frss(账务对账文件清结算)'},
            {value: 'shenma-fund', label: 'shenma-fund(旧资金系统)'},
            {value: 'shenma-insurance', label: 'shenma-insurance(保险项目)'},
            {value: 'shenma-insurance-admin', label: 'shenma-insurance-admin(新风管理系统)'},
            {value: 'shenma-insurance-web', label: 'shenma-insurance-web(保险后台页面)'},
            {value: 'shenma-moxie', label: 'shenma-moxie(新风控魔蝎)'},
            {value: 'shenma-oss', label: 'shenma-oss(文件系统)'},
            {value: 'shenma-riskcontrol', label: 'shenma-riskcontrol(风控系统)'},
            {value: 'shenma-riskeng', label: 'shenma-riskeng(新风控引擎)'},
            {value: 'shenma-risksrc', label: 'shenma-risksrc(新风控数据源)'},
            {value: 'shenma-romp', label: 'shenma-romp(EV商品管理系统)'},
            {value: 'shenma-running', label: 'shenma-running(账务清结算系统)'},
            {value: 'shenma-samp', label: 'shenma-samp(新资金系统)'},
            {value: 'shenma-scf', label: 'shenma-scf(供应链金融系统)'},
            {value: 'shenma-shenmapay', label: 'shenma-shenmapay(支付系统)'},
            {value: 'shenma-smartpay', label: 'shenma-smartpay(支付路由)'},
            {value: 'shenma-toprules', label: 'shenma-toprules(规则引擎)'},
            {value: 'shenma-wechat', label: 'shenma-wechat(微信后台系统)'}
          ]
        },
        
        query: {
          env: '',
          eng: '',
          chs: ''
        },
        
        tableData: [
          {'id': 99, 'env': 'SIT01', 'eng': 'shenma-ev-order', 'chs': 'EV订单系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.125:3306/db_apollo_order', 'account': 'user_shenma', 'password': 'pass_shenma', 'creater': '师宗强', 'ctime': '2018-09-03 15:35:04.0', 'updater': 'system', 'utime': '2018-09-03 15:35:04.0', 'note': 'EV订单系统'},
          {'id': 101, 'env': 'SIT01', 'eng': 'shenma-romp', 'chs': 'EV商品管理系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.125:3306/db_romp?useUnicode=true&characterEncoding=UTF-8', 'account': 'romp', 'password': 'Shenma007', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:04.0', 'updater': 'system', 'utime': '2018-09-03 15:35:04.0', 'note': 'EV商品管理系统'},
          {'id': 103, 'env': 'SIT01', 'eng': 'shenma-css', 'chs': '什马清结算系统', 'benchmark': 0, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_css', 'account': 'user_frss', 'password': 'Shenma@fsde', 'creater': '夏天', 'ctime': '2018-09-03 15:35:04.0', 'updater': 'system', 'utime': '2018-09-03 15:35:04.0', 'note': '清结算创世块'},
          {'id': 92, 'env': 'SIT01', 'eng': 'shenma-frss', 'chs': '账务对账文件清结算', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_frss', 'account': 'user_frss', 'password': 'Shenma@fsde', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:03.0', 'updater': 'system', 'utime': '2018-09-03 15:35:03.0', 'note': '账务对账文件清结算'},
          {'id': 93, 'env': 'SIT01', 'eng': 'shenma-collection', 'chs': '催收系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.44:3306/db_ccms', 'account': 'user_ccms', 'password': 'Bj132Y11', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:03.0', 'updater': 'system', 'utime': '2018-09-03 15:35:03.0', 'note': '催收系统'},
          {'id': 97, 'env': 'SIT01', 'eng': 'shenma-fpss', 'chs': '资金兑付系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_running_fund', 'account': 'user_rfund', 'password': 'Shenma#zaq7', 'creater': '夏天', 'ctime': '2018-09-03 15:35:03.0', 'updater': 'system', 'utime': '2018-09-03 15:35:03.0', 'note': '创世块'},
          {'id': 60, 'env': 'SIT01', 'eng': 'shenma-risksrc', 'chs': '风控数据源', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_xmjk', 'account': 'user_xmjk', 'password': 'Mashen@2qaz', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:59.0', 'updater': 'system', 'utime': '2018-09-03 15:34:59.0', 'note': '风控数据源 + 小马金卡'},
          {'id': 64, 'env': 'SIT01', 'eng': 'shenma-insurance-web', 'chs': '保险服务', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_insurance_web', 'account': 'user_insurance', 'password': 'Shenma@sob1', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:59.0', 'updater': 'system', 'utime': '2018-09-03 15:34:59.0', 'note': '初始化环境数据库信息'},
          {'id': 65, 'env': 'SIT01', 'eng': 'shenma-insurance', 'chs': '保险项目', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_insurance', 'account': 'user_insurance', 'password': 'Shenma@sob1', 'creater': '周苏辉', 'ctime': '2018-09-03 15:34:59.0', 'updater': 'system', 'utime': '2018-09-03 15:34:59.0', 'note': '保险项目'},
          {'id': 51, 'env': 'SIT01', 'eng': 'shenma-samp', 'chs': '新资金系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_samp_fund', 'account': 'user_samp_fund', 'password': 'pass_samp_fund', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': 'system', 'utime': '2018-09-03 15:34:58.0', 'note': '备注'},
          {'id': 52, 'env': 'SIT01', 'eng': 'shenma-cfbs', 'chs': '业务系统', 'benchmark': 1, 'type': 'ORACLE', 'driver': 'oracle.jdbc.driver.OracleDriver', 'url': 'jdbc:oracle:thin:@172.16.10.85:1521:cfbsdb', 'account': 'huateng', 'password': 'qazxsw', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': 'system', 'utime': '2018-09-03 15:34:58.0', 'note': '业务系统数据库'},
          {'id': 53, 'env': 'SIT01', 'eng': 'shenma-smartpay', 'chs': '支付路由', 'benchmark': 1, 'type': 'ORACLE', 'driver': 'oracle.jdbc.driver.OracleDriver', 'url': 'jdbc:oracle:thin:@172.16.10.85:1521:cfbsdb', 'account': 'cfbs1', 'password': 'QAZxsw1', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': 'system', 'utime': '2018-09-03 15:34:58.0', 'note': '致富路由数据库'},
          {'id': 47, 'env': 'SIT01', 'eng': 'shenma-insurance-admin', 'chs': '保险服务', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_insurance_admin', 'account': 'user_insurance', 'password': 'Shenma@sob1', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:57.0', 'updater': 'system', 'utime': '2018-09-03 15:34:57.0', 'note': '初始化环境数据库信息'},
          {'id': 36, 'env': 'SIT01', 'eng': 'shenma-wechat', 'chs': '微信后台系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_weixin', 'account': 'user_wechat', 'password': 'shenma', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:56.0', 'updater': 'system', 'utime': '2018-09-03 15:34:56.0', 'note': '初始化环境数据库信息'},
          {'id': 40, 'env': 'SIT01', 'eng': 'shenma-running', 'chs': '账务清结算系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_running_fund', 'account': 'user_rfund', 'password': 'Shenma#zaq7', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:56.0', 'updater': 'system', 'utime': '2018-09-03 15:34:56.0', 'note': '初始化环境数据库信息'},
          {'id': 28, 'env': 'SIT01', 'eng': 'shenma-chitu', 'chs': '赤兔系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_chitu', 'account': 'user_chitu', 'password': 'Shenmaqaz1', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:55.0', 'updater': 'system', 'utime': '2018-09-03 15:34:55.0', 'note': '初始化环境数据库信息'},
          {'id': 32, 'env': 'SIT01', 'eng': 'shenma-scf', 'chs': '供应链金融系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_scf', 'account': 'user_scf', 'password': 'UU13k7pm', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:55.0', 'updater': 'system', 'utime': '2018-09-03 15:34:55.0', 'note': '初始化环境数据库信息'},
          {'id': 4, 'env': 'SIT01', 'eng': 'shenma-shenmapay', 'chs': '支付系统', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_shenmapay', 'account': 'user_shenmapay', 'password': 'w127Gb56', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:51.0', 'updater': 'system', 'utime': '2018-09-03 15:34:51.0', 'note': '初始化环境数据库信息'}
        ]
      }
    }
  }
</script>
