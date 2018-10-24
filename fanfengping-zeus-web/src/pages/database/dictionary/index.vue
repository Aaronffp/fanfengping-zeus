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
      <el-form-item label="数据库URL：">
        <el-input v-model="query.url" placeholder="请输入数据库URL..." clearable></el-input>
      </el-form-item>
      <el-form-item label="数据表名：">
        <el-input v-model="query.tableName" placeholder="请输入数据表名..." clearable></el-input>
      </el-form-item>
      <el-form-item label="数据列名：">
        <el-input v-model="query.columnName" placeholder="请输入数据列名..." clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleBtnQuery(query)" type="primary" icon="el-icon-search">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus">导出</el-button>
      </el-form-item>
    </el-form>
    
    <el-table
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      hight="250"
      border
      stripe
      show-overflow-tooltip
      max-height="700"
      style="width: 100%">
      <el-table-column fixed type="index" width="50"></el-table-column>
      <el-table-column fixed prop="id" label="主键" width="80" v-if="false"></el-table-column>
      <el-table-column fixed prop="eng" label="英文简称" width="150" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sn" label="流水号" width="130"></el-table-column>
      <el-table-column prop="databaseId" label="数据库ID" width="80" v-if="false"></el-table-column>
      <el-table-column prop="env" label="所属环境" width="80"></el-table-column>
      <el-table-column prop="url" label="数据库URL" width="350" show-overflow-tooltip></el-table-column>
      <el-table-column prop="username" label="账号" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column prop="password" label="密码" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column prop="tableSchema" label="数据库名" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="tableName" label="表名称" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="tableComment" label="表注释" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="engine" label="表引擎" width="80"></el-table-column>
      <el-table-column prop="tableCollation" label="表字符集" width="150"></el-table-column>
      <el-table-column prop="columnName" label="字段名" width="150"></el-table-column>
      <el-table-column prop="columnComment" label="字段注释" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="columnKey" label="是否主键" width="80"></el-table-column>
      <el-table-column prop="columnType" label="字段类型" width="150"></el-table-column>
      <el-table-column prop="nullable" label="可为空？" width="80"></el-table-column>
      <el-table-column prop="columnDefault" label="默认值" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="characterSetName" label="字符集" width="100"></el-table-column>
      <el-table-column prop="collationName" label="字符排序规则" width="150"></el-table-column>
      <el-table-column prop="ctime" label="创建日期" width="170"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[15, 30, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="currentTotal">
    </el-pagination>
  </d2-container>
</template>

<script>
  import {DbDictFindAllByConditions} from '@/service/database'
  export default {
    methods: {
      handleBtnQuery(query) {
        DbDictFindAllByConditions(query).then(res => {
          this.tableData = res.data;
          this.currentTotal = this.tableData.length;
          this.$message({
            message: res.msg,
            type: res.code == 200 ? 'success' : 'warning'
          });
        })
        .catch(err => {
          console.log(err)
        })
      },
      handleSizeChange(val) {
        this.pageSize = val;
      },
      handleCurrentChange(val) {
        this.currentPage = val;
      }
    },
    data() {
      return {
        currentPage: 1,
        pageSize: 30,
        currentTotal: 0,
        formLabelWidth: '120px',
        conditions: {
          envs: [
            {value: 'SIT01', label: '测试环境'},
            {value: 'SIT04', label: '开发环境'},
            {value: 'PRE', label: '预发环境'},
            {value: 'DOCKER', label: '容器环境'}
          ],
          engs: [
            {value: 'fanfengping-zeus', label: 'fanfengping-zeus(宙斯全栈能效平台)'},
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
          url: '',
          tableName: '',
          columnName: ''
        },
        
        tableData: []
      }
    }
  }
</script>
