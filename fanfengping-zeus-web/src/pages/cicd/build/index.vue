<template>
  <d2-container>
    <el-form :model="query" :inline="true">
      <el-form-item label="英文简称：">
        <el-input v-model="query.eng" placeholder="请输入英文简称..." clearable></el-input>
      </el-form-item>
      <el-form-item label="版本状态：">
        <el-select v-model="query.status" clearable filterable placeholder="请选择版本状态...">
          <el-option v-for="item in conditions.status" :key="item.value" :label="item.label" :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <!--<span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>-->
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="GIT仓库地址：">
        <el-input v-model="query.gitUrl" placeholder="请输入GIT仓库地址..." clearable></el-input>
      </el-form-item>
      <el-form-item label="GIT分支：">
        <el-input v-model="query.gitBranch" placeholder="请输入GIT分支..." clearable></el-input>
      </el-form-item>
      <el-form-item label="构建人：">
        <el-input v-model="query.operator" placeholder="请输入构建构建人..." clearable></el-input>
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
      <el-table-column prop="status" label="版本状态" width="80">
        <template scope="scope">
          <span v-if="scope.row.status==-1">开发版本</span>
          <span v-if="scope.row.status==0">测试版本</span>
          <span v-if="scope.row.status==1">发布版本</span>
        </template>
      </el-table-column>
      <el-table-column prop="tag" label="构建版本" width="170"></el-table-column>
      <el-table-column label="GIT仓库地址" width="400" show-overflow-tooltip>
        <template slot-scope="scope">
          <a :href="scope.row.gitUrl" target="_blank" class="buttonText">{{scope.row.gitUrl}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="gitBranch" label="GIT分支" width="120"></el-table-column>
      <el-table-column prop="addr" label="构建服务器" width="180"></el-table-column>
      <el-table-column label="构建链接" width="400" show-overflow-tooltip>
        <template slot-scope="scope">
          <a :href="scope.row.link" target="_blank" class="buttonText">{{scope.row.link}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="operator" label="构建人" width="100"></el-table-column>
      <el-table-column prop="ctime" label="构建时间" width="170"></el-table-column>
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
    <el-dialog :title="!formData.id ? '新增构建信息' : '修改构建信息'" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="系统编号：" :label-width="formLabelWidth">
          <el-input v-model="formData.id" clearable placeholder="请输入系统编号..."></el-input>
        </el-form-item>
        <el-form-item label="英文简称：" :label-width="formLabelWidth">
          <el-input v-model="formData.eng" clearable placeholder="请输入英文简称..."></el-input>
        </el-form-item>
        <el-form-item label="构建版本：" :label-width="formLabelWidth">
          <el-input v-model="formData.tag" clearable placeholder="请输入构建版本..."></el-input>
        </el-form-item>
        <el-form-item label="版本状态：" :label-width="formLabelWidth">
          <el-select v-model="formData.status" clearable placeholder="请选择版本状态...">
            <el-option v-for="item in conditions.status" :key="item.value" :label="item.label" :value="item.value">
              <span style="float: left">{{ item.label }}</span>
              <!--<span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>-->
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="GIT仓库地址：" :label-width="formLabelWidth">
          <el-input v-model="formData.gitUrl" clearable placeholder="请输入GIT仓库地址...">
          </el-input>
        </el-form-item>
        <el-form-item label="GIT分支：" :label-width="formLabelWidth">
          <el-input v-model="formData.gitBranch" clearable placeholder="请输入GIT分支...">
          </el-input>
        </el-form-item>
        <el-form-item label="构建服务器：" :label-width="formLabelWidth">
          <el-input v-model="formData.addr" clearable placeholder="请输入构建服务器IP地址...">
          </el-input>
        </el-form-item>
        <el-form-item label="构建链接：" :label-width="formLabelWidth">
          <el-input v-model="formData.link" clearable placeholder="请输入构建链接...">
          </el-input>
        </el-form-item>
        <el-form-item label="构建人：" :label-width="formLabelWidth">
          <el-input v-model="formData.operator" clearable placeholder="请输入构建人...">
          </el-input>
        </el-form-item>
        <el-form-item label="构建时间：" :label-width="formLabelWidth">
          <el-input v-model="formData.ctime" clearable placeholder="请输入构建时间...">
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
  import {BuildAdd, BuildUpdate, BuildDelete, BuildFindAllByConditions,} from '@/service/cicd'
  export default {
    methods: {
      handleBtnQuery(query) {
        if (query.status === "") {
          query.status = '1,0,-1';
        }

          BuildFindAllByConditions(query).then(res => {
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
          BuildUpdate(this.formData).then(res => {
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
          BuildAdd(this.formData).then(res => {
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
        BuildDelete(this.formData).then(res => {
          this.formData = { id: 0, env: '', eng: '', chs: '', url: '', username: '', password: '', updater: '', utime: '', note: ''}
          this.$message({
            message: res.msg,
            type: res.code == 200 ? 'success' : 'warning'
          });
        })
        .catch(err => {
          console.log(err)
        })
        handleBtnQuery(query)
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
          eng: '',
          tag: '',
          status: '',
          gitUrl: '',
          gitBranch: '',
          addr: '',
          link: '',
          operator: '',
          ctime: ''
        },
        conditions: {
          status: [
            {value: '1,0,-1', label: '全部版本'},
            {value: '-1', label: '开发版本'},
            {value: '0', label: '测试版本'},
            {value: '1', label: '发布版本'}
          ]
        },

        query: {
          eng: '',
          status: '',
          gitUrl: '',
          gitBranch: '',
          operator: ''
        },

        tableData: []
      }
    }
  }
</script>
