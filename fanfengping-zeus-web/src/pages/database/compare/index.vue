<template>
  <d2-container>
    <el-form :model="query" :inline="true">
      <el-form-item label="英文简称：">
        <el-select v-model="query.eng" clearable filterable placeholder="请选择系统英文简称...">
          <el-option v-for="item in conditions.engs" :key="item.value" :label="item.label" :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="比对库环境：">
        <el-select v-model="query.env" clearable placeholder="请选择比对库...">
          <el-option v-for="item in conditions.envs" :key="item.value" :label="item.label" :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleBtnQuery(query)" type="primary" icon="el-icon-search">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleBtnCompare(query)" type="primary" icon="el-icon-refresh">比对</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
        hight="250"
        border
        stripe
        max-height="650"
        style="width: 100%">
      <el-table-column fixed type="index" width="50"></el-table-column>
      <el-table-column prop="id" label="系统编号" width="150" v-if="false"></el-table-column>
      <el-table-column fixed prop="flag" label="批次标识" width="130" v-if="false"></el-table-column>
      <el-table-column fixed prop="eng" label="英文简称" width="150"></el-table-column>
      <el-table-column prop="status" label="结果" width="50">
        <template scope="scope">
          <span v-if="scope.row.status==-1">失败</span>
          <span v-if="scope.row.status==0">成功</span>
        </template>
      </el-table-column>
      <el-table-column prop="benchmarkId" label="基准库编号" width="150" v-if="false"></el-table-column>
      <el-table-column prop="benchmarkEnv" label="基准库环境" width="100"></el-table-column>
      <el-table-column prop="benchmarkUrl" label="基准库URL" width="350" show-overflow-tooltip></el-table-column>
      <el-table-column prop="targetId" label="比对库编号" width="150" v-if="false"></el-table-column>
      <el-table-column prop="targetEnv" label="比对库环境" width="100"></el-table-column>
      <el-table-column prop="targetUrl" label="比对库URL" width="350" show-overflow-tooltip></el-table-column>
      <el-table-column prop="info" label="结果信息（字段类型，字段长度，小数位数，是否可为空，默认值，备注）" width="500" show-overflow-tooltip></el-table-column>
      <el-table-column prop="note" label="备注" width="150" v-if="false"></el-table-column>
      <el-table-column prop="ctime" label="日期" width="165"></el-table-column>
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
  import {DbCompFindAllByConditions, DbComp} from '@/service/database'
  export default {
    methods: {
      handleBtnQuery(query) {
        if (query.eng === "") {
          this.$message({
            message: '请选择查询的数据库',
            type: 'warning'
          });
          return;
        }

        DbCompFindAllByConditions(query).then(res => {
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
      handleBtnCompare(query) {
        if (query.eng === "") {
          this.$message({
            message: '请选择比对的数据库',
            type: 'warning'
          });
          return;
        }

        DbComp(query).then(res => {
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
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        console.log(`当前页: ${val}`);
      }
    },
    data() {
      return {
        currentPage: 1,
        pageSize: 30,
        currentTotal: 0,
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
          eng: '',
          env: ''
        },

        tableData: []
      }
    }
  }
</script>
