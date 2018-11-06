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
      <el-form-item label="域名地址：">
        <el-input v-model="query.url" placeholder="请输入域名地址..." clearable></el-input>
      </el-form-item>
      <el-form-item label="英文简称：">
        <el-input v-model="query.eng" placeholder="请输入英文简称..." clearable></el-input>
      </el-form-item>
      <el-form-item label="中文简称：">
        <el-input v-model="query.chs" placeholder="请输入中文简称..." clearable></el-input>
      </el-form-item>
      <el-form-item label="更新人：">
        <el-input v-model="query.updater" placeholder="请输入更新人..." clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleBtnQuery(query)" type="primary" icon="el-icon-search">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus">新增</el-button>
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
      <!--<el-table-column prop="id" label="系统主键" width="100"></el-table-column>-->
      <el-table-column fixed prop="eng" label="英文简称" width="180"></el-table-column>
      <el-table-column prop="chs" label="中文简称" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="env" label="环境标识" width="100"></el-table-column>
      <!--<el-table-column prop="url" label="访问链接" width="400" show-overflow-tooltip></el-table-column>-->
      <el-table-column label="访问链接" width="400" show-overflow-tooltip>
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank" class="buttonText">{{scope.row.url}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="账号" width="120"></el-table-column>
      <el-table-column prop="password" label="密码" width="120"></el-table-column>
      <el-table-column prop="note" label="备注" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="updater" label="更新人" width="100"></el-table-column>
      <el-table-column prop="utime" label="更新时间" width="170"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleBtnEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="handleBtnDelete(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
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
    <el-dialog :title="!formData.id ? '新增服务信息' : '修改服务信息'" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="英文简称：" :label-width="formLabelWidth">
          <el-input v-model="formData.eng" clearable placeholder="请输入英文名称..."></el-input>
        </el-form-item>
        <el-form-item label="中文简称：" :label-width="formLabelWidth">
          <el-input v-model="formData.chs" clearable placeholder="请输入中文名称..."></el-input>
        </el-form-item>
        <el-form-item label="环境：" :label-width="formLabelWidth">
          <el-select v-model="formData.env" clearable placeholder="请选择数据库环境...">
            <el-option v-for="item in conditions.envs" :key="item.value" :label="item.label" :value="item.value">
              <span style="float: left">{{ item.label }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="访问链接：" :label-width="formLabelWidth">
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
        <el-form-item label="更新人：" :label-width="formLabelWidth">
          <el-input v-model="formData.updater" clearable placeholder="请输入备注信息...">
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
  import {ServAdd, ServUpdate, ServDelete, ServFindAllByConditions} from '@/service/cicd'
  export default {
    methods: {
      handleBtnQuery(query) {
        if (query.env === "") {
          this.$message({
            message: '请选择查询环境',
            type: 'warning'
          });
          return;
        }

        ServFindAllByConditions(query).then(res => {
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
      handleBtnAdd(){
        this.dialogFormVisible = false;

        if (this.formData.id > 0) {
          ServUpdate(this.formData).then(res => {
            this.$message({
              message: res.msg,
              type: res.code == 200 ? 'success' : 'warning'
            });
          })
          .catch(err => {
            console.log(err)
          });

          this.formData = { id: 0, env: '', eng: '', chs: '', url: '', username: '', password: '', updater: '', utime: '', note: ''}
        } else {
          ServAdd(this.formData).then(res => {
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
      handleBtnEdit(row) {
        this.formData = row;
        this.dialogFormVisible = true;
      },
      handleBtnDelete(row) {
        this.formData = row;
        ServDelete(this.formData).then(res => {
          this.formData = { id: 0, env: '', eng: '', chs: '', url: '', username: '', password: '', updater: '', utime: '', note: ''}
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
        options: {
          fullscreen: true,
          lock: true,
          text: '拼尽性命，努力加载中...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.8)'
        },
        currentPage: 1,
        pageSize: 30,
        currentTotal: 0,
        dialogTableVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        formData: {
          id: 0,
          env: '',
          eng: '',
          chs: '',
          url: '',
          username: '',
          password: '',
          updater: '',
          utime: '',
          note: ''
        },
        conditions: {
          envs: [
            {value: 'SIT01', label: '测试环境'},
            {value: 'DEV', label: '开发环境'},
            {value: 'PRE', label: '预发环境'},
            {value: 'DOCKER', label: '容器环境'}
          ]
        },

        query: {
          env: '',
          url: '',
          eng: '',
          chs: '',
          updater: ''
        },

        tableData: []
      }
    }
  }
</script>
