<template>
  <d2-container>
    <el-form :model="query" :inline="true">
      <el-form-item label="所属环境：">
        <el-select v-model="query.env" clearable filterable placeholder="请选择数据库环境...">
          <el-option v-for="item in conditions.envs" :key="item.value" :label="item.label" :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="英文简称：">
        <el-select v-model="query.eng" placeholder="请输入英文简称..." clearable>
          <el-option v-for="item in conditions.engs" :key="item.value" :label="item.label" :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="中文简称：">
        <el-input v-model="query.chs" placeholder="请输入中文简称..." clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleBtnQuery(query)" type="primary" icon="el-icon-search">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus">新增</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus">导出</el-button>
      </el-form-item>
    </el-form>
    
    <el-table
      :data="tableData"
      hight="250"
      border
      stripe
      show-overflow-tooltip
      max-height="700"
      style="width: 100%">
      <el-table-column fixed type="index" width="50"></el-table-column>
      <!--<el-table-column fixed prop="id" label="主键" width="50" v-show="false"></el-table-column>-->
      <el-table-column fixed prop="eng" label="英文简称" width="200"></el-table-column>
      <el-table-column prop="chs" label="中文简称" width="150"></el-table-column>
      <el-table-column prop="env" label="环境标识" width="100"></el-table-column>
      <el-table-column prop="valid" label="是否有效" width="80">
        <template scope="scope">
          <span v-if="scope.row.valid==1">是</span>
          <span v-if="scope.row.valid==0">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="benchmark" label="基准库" width="70">
        <template scope="scope">
          <span v-if="scope.row.benchmark==1">是</span>
          <span v-if="scope.row.benchmark==0">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="数据库类型" width="100"></el-table-column>
      <el-table-column prop="driver" label="数据库驱动" width="210"></el-table-column>
      <el-table-column prop="url" label="数据库URL" width="400" show-overflow-tooltip></el-table-column>
      <el-table-column prop="username" label="数据库账号" width="150"></el-table-column>
      <el-table-column prop="password" label="数据库密码" width="150"></el-table-column>
      <el-table-column prop="note" label="备注" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="creater" label="创建人" width="80"></el-table-column>
      <el-table-column prop="ctime" label="创建时间" width="170"></el-table-column>
      <el-table-column prop="updater" label="更新人" width="80"></el-table-column>
      <el-table-column prop="utime" label="更新时间" width="170"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleBtnEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="handleBtnDelete(scope.row)" type="text" size="small">删除</el-button>
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
        <el-form-item label="是否有效：" :label-width="formLabelWidth">
          <el-select v-model="formData.valid" placeholder="请选择...">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
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
          <el-input v-model="formData.username" clearable placeholder="请输入账号...">
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
        <el-button type="primary" @click="handleBtnAdd()">确 定</el-button>
      </div>
    </el-dialog>
  </d2-container>
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
          // 更新查询结果
          this.tableData = res.data;
          this.$message({
            message: res.msg,
            type: res.code == 200 ? 'success' : 'warning'
          });
        })
        .catch(err => {
          console.log(err)
        })
      },
      handleBtnAdd(){
        this.dialogFormVisible = false;

        if (this.formData.id > 0) {
          DbUpdate(this.formData).then(res => {
            this.$message({
              message: res.msg,
              type: res.code == 200 ? 'success' : 'warning'
            });
          })
          .catch(err => {
            console.log(err)
          });

          this.formData = {id:0, valid: 1, benchmark: 1, type: 'MYSQL', driver: 'com.mysql.jdbc.Driver'}
        } else {
          DbAdd(this.formData).then(res => {
            this.$message({
              message: res.msg,
              type: res.code == 200 ? 'success' : 'warning'
            });
          })
          .catch(err => {
            console.log(err)
          })
        }
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
          valid: 1,
          benchmark: 1,
          type: 'MYSQL',
          driver: 'com.mysql.jdbc.Driver',
          url: '',
          username: '',
          password: '',
          creater: '',
          updater: '',
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
            {value: 'fanfengping-admittance', label: 'fanfengping-admittance(宙斯全栈能效)'},
            {value: 'fanfengping-cfbs', label: 'fanfengping-cfbs(宙斯全栈能效)'},
            {value: 'fanfengping-channel', label: 'fanfengping-channel(宙斯全栈能效)'},
            {value: 'fanfengping-chitu', label: 'fanfengping-chitu(宙斯全栈能效)'},
            {value: 'fanfengping-chnl', label: 'fanfengping-chnl(宙斯全栈能效)'},
            {value: 'fanfengping-collection', label: 'fanfengping-collection(宙斯全栈能效)'},
            {value: 'fanfengping-css', label: 'fanfengping-css(宙斯全栈能效)'},
            {value: 'fanfengping-ev-order', label: 'fanfengping-ev-order(宙斯全栈能效)'},
            {value: 'fanfengping-fanfengpingpay', label: 'fanfengping-fanfengpingpay(宙斯全栈能效)'},
            {value: 'fanfengping-fpss', label: 'fanfengping-fpss(宙斯全栈能效)'},
            {value: 'fanfengping-frss', label: 'fanfengping-frss(宙斯全栈能效)'},
            {value: 'fanfengping-fund', label: 'fanfengping-fund(宙斯全栈能效)'},
            {value: 'fanfengping-insurance', label: 'fanfengping-insurance(宙斯全栈能效)'},
            {value: 'fanfengping-insurance-admin', label: 'fanfengping-insurance-admin(宙斯全栈能效)'},
            {value: 'fanfengping-insurance-web', label: 'fanfengping-insurance-web(宙斯全栈能效)'},
            {value: 'fanfengping-moxie', label: 'fanfengping-moxie(宙斯全栈能效)'},
            {value: 'fanfengping-oss', label: 'fanfengping-oss(宙斯全栈能效)'},
            {value: 'fanfengping-riskcontrol', label: 'fanfengping-riskcontrol(宙斯全栈能效)'},
            {value: 'fanfengping-riskeng', label: 'fanfengping-riskeng(宙斯全栈能效)'},
            {value: 'fanfengping-risksrc', label: 'fanfengping-risksrc(宙斯全栈能效)'},
            {value: 'fanfengping-romp', label: 'fanfengping-romp(宙斯全栈能效)'},
            {value: 'fanfengping-running', label: 'fanfengping-running(宙斯全栈能效)'},
            {value: 'fanfengping-samp', label: 'fanfengping-samp(宙斯全栈能效)'},
            {value: 'fanfengping-scf', label: 'fanfengping-scf(宙斯全栈能效)'},
            {value: 'fanfengping-smartpay', label: 'fanfengping-smartpay(宙斯全栈能效)'},
            {value: 'fanfengping-toprules', label: 'fanfengping-toprules(宙斯全栈能效)'},
            {value: 'fanfengping-wechat', label: 'fanfengping-wechat(宙斯全栈能效)'}
          ]
        },
        
        query: {
          env: '',
          eng: '',
          chs: ''
        },
        
        tableData: []
      }
    }
  }
</script>
