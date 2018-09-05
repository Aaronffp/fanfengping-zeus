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
            <el-select v-model="query.eng" placeholder="请输入英文简称..." clearable>
              <el-option v-for="item in conditions.engs" :key="item.value" :label="item.label" :value="item.value">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-input v-model="query.chs" placeholder="请输入中文简称..." clearable></el-input>
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
  // import {DbAdd, DbUpdate, DbFindAllByConditions, DbDelete, DbSetBenchmark} from '@/service/database'
  export default {
    methods: {
      handleBtnQuery(query) {
        console.log(query);
        
        // DbFindAllByConditions(query).then(res => {
        //   console.log("查询结果");
        //   console.log(res);
        //   this.tableData = res
        // })
        // .catch(err => {
        //   console.log(err)
        // })
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
            {value: 'aaronffp-dev', label: 'aaronffp-dev(宙斯全栈能效)'},
            {value: 'aaronffp-docs', label: 'aaronffp-docs(宙斯全栈能效)'},
            {value: 'aaronffp-node', label: 'aaronffp-node(宙斯全栈能效)'},
            {value: 'aaronffp-modules', label: 'aaronffp-modules(宙斯全栈能效)'},
            {value: 'aaronffp-public', label: 'aaronffp-public(宙斯全栈能效)'},
            {value: 'aaronffp-src', label: 'aaronffp-src(宙斯全栈能效)'},
            {value: 'aaronffp-api', label: 'aaronffp-api(宙斯全栈能效)'},
            {value: 'aaronffp-assets', label: 'aaronffp-assets(宙斯全栈能效)'},
            {value: 'aaronffp-compon', label: 'aaronffp-compon(宙斯全栈能效)'},
            {value: 'aaronffp-layout', label: 'aaronffp-layout(宙斯全栈能效)'},
            {value: 'aaronffp-libs', label: 'aaronffp-libs(宙斯全栈能效)'},
            {value: 'aaronffp-menu', label: 'aaronffp-menu(宙斯全栈能效)'},
            {value: 'aaronffp-mock', label: 'aaronffp-mock(宙斯全栈能效)'},
            {value: 'aaronffp-pages', label: 'aaronffp-pages(宙斯全栈能效)'},
            {value: 'aaronffp-plugin', label: 'aaronffp-plugin(宙斯全栈能效)'},
            {value: 'aaronffp-router', label: 'aaronffp-router(宙斯全栈能效)'},
            {value: 'aaronffp-store', label: 'aaronffp-store(宙斯全栈能效)'},
            {value: 'aaronffp-app', label: 'aaronffp-app(宙斯全栈能效)'},
            {value: 'aaronffp-main', label: 'aaronffp-main(宙斯全栈能效)'},
            {value: 'aaronffp-setting', label: 'aaronffp-setting(宙斯全栈能效)'},
            {value: 'aaronffp-tests', label: 'aaronffp-tests(宙斯全栈能效)'},
            {value: 'aaronffp-env', label: 'aaronffp-env(宙斯全栈能效)'},
            {value: 'aaronffp-eslintrc', label: 'aaronffp-eslintrc(宙斯全栈能效)'},
            {value: 'aaronffp-post', label: 'aaronffp-post(宙斯全栈能效)'},
            {value: 'aaronffp-get', label: 'aaronffp-get(宙斯全栈能效)'},
            {value: 'aaronffp-update', label: 'aaronffp-update(宙斯全栈能效)'},
            {value: 'aaronffp-delete', label: 'aaronffp-delete(宙斯全栈能效)'}
          ]
        },
        
        query: {
          env: '',
          eng: '',
          chs: ''
        },
        
        tableData: [
          {'id': 99, 'env': 'SIT01', 'eng': 'aaronffp-api', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.125:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:04.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:04.0', 'note': '宙斯全栈能效平台'},
          {'id': 101, 'env': 'SIT01', 'eng': 'aaronffp-assets', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.125:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:04.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:04.0', 'note': '宙斯全栈能效平台'},
          {'id': 103, 'env': 'SIT01', 'eng': 'aaronffp-components', 'chs': '宙斯全栈能效平台', 'benchmark': 0, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:04.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:04.0', 'note': '宙斯全栈能效平台'},
          {'id': 92, 'env': 'SIT01', 'eng': 'aaronffp-layout', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:03.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:03.0', 'note': '宙斯全栈能效平台'},
          {'id': 93, 'env': 'SIT01', 'eng': 'aaronffp-libs', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.44:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:03.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:03.0', 'note': '宙斯全栈能效平台'},
          {'id': 97, 'env': 'SIT01', 'eng': 'aaronffp-menu', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:35:03.0', 'updater': '范丰平', 'utime': '2018-09-03 15:35:03.0', 'note': '宙斯全栈能效平台'},
          {'id': 60, 'env': 'SIT01', 'eng': 'aaronffp-mock', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:59.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:59.0', 'note': '宙斯全栈能效平台'},
          {'id': 64, 'env': 'SIT01', 'eng': 'aaronffp-pages', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:59.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:59.0', 'note': '宙斯全栈能效平台'},
          {'id': 65, 'env': 'SIT01', 'eng': 'aaronffp-plugin', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:59.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:59.0', 'note': '宙斯全栈能效平台'},
          {'id': 51, 'env': 'SIT01', 'eng': 'aaronffp-router', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:58.0', 'note': '宙斯全栈能效平台'},
          {'id': 52, 'env': 'SIT01', 'eng': 'aaronffp-store', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'ORACLE', 'driver': 'oracle.jdbc.driver.OracleDriver', 'url': 'jdbc:oracle:thin:@172.16.10.85:1521:db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:58.0', 'note': '宙斯全栈能效平台'},
          {'id': 53, 'env': 'SIT01', 'eng': 'aaronffp-app', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'ORACLE', 'driver': 'oracle.jdbc.driver.OracleDriver', 'url': 'jdbc:oracle:thin:@172.16.10.85:1521:db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:58.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:58.0', 'note': '宙斯全栈能效平台'},
          {'id': 47, 'env': 'SIT01', 'eng': 'aaronffp-admin', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:57.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:57.0', 'note': '宙斯全栈能效平台'},
          {'id': 36, 'env': 'SIT01', 'eng': 'aaronffp-test', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:56.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:56.0', 'note': '宙斯全栈能效平台'},
          {'id': 40, 'env': 'SIT01', 'eng': 'aaronffp-env', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:56.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:56.0', 'note': '宙斯全栈能效平台'},
          {'id': 28, 'env': 'SIT01', 'eng': 'aaronffp-license', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:55.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:55.0', 'note': '宙斯全栈能效平台'},
          {'id': 32, 'env': 'SIT01', 'eng': 'aaronffp-package', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:55.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:55.0', 'note': '宙斯全栈能效平台'},
          {'id': 4, 'env': 'SIT01', 'eng': 'aaronffp-zeus', 'chs': '宙斯全栈能效平台', 'benchmark': 1, 'type': 'MYSQL', 'driver': 'com.mysql.jdbc.Driver', 'url': 'jdbc:mysql://172.16.10.15:3306/db_aaronffp', 'account': 'fanfengping', 'password': 'fanfengping', 'creater': '范丰平', 'ctime': '2018-09-03 15:34:51.0', 'updater': '范丰平', 'utime': '2018-09-03 15:34:51.0', 'note': '宙斯全栈能效平台'}
        ]
      }
    }
  }
</script>
