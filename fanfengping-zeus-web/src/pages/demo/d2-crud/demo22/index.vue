<template>
  <d2-container>
    <template slot="header">表单校验</template>
    <d2-crud
      :columns="columns"
      :data="data"
      title="D2 CRUD"
      add-mode
      :add-button="addButton"
      :form-template="formTemplate"
      :form-options="formOptions"
      :form-rules="formRules"
      @row-add="handleRowAdd"
      @dialog-cancel="handleDialogCancel">
    </d2-crud>
    <el-card shadow="never" class="d2-mb">
      <d2-markdown :source="doc"/>
    </el-card>
    <el-card shadow="never" class="d2-mb">
      <d2-highlight :code="code"/>
    </el-card>
    <template slot="footer">
      <d2-link-btn title="文档" link="http://app.d3collection.cn/d2-admin-doc/lastest/zh/ecosystem-d2-crud/"/>
    </template>
  </d2-container>
</template>

<script>
import doc from './doc.md'
import code from './code.js'

export default {
  data () {
    return {
      doc,
      code,
      columns: [
        {
          title: '日期',
          key: 'date'
        },
        {
          title: '姓名',
          key: 'name'
        },
        {
          title: '地址',
          key: 'address'
        }
      ],
      data: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }
      ],
      addButton: {
        text: '点我查看表单校验',
        icon: 'el-icon-plus',
        size: 'small'
      },
      formTemplate: {
        date: {
          title: '日期',
          value: ''
        },
        name: {
          title: '姓名',
          value: ''
        },
        address: {
          title: '地址',
          value: ''
        }
      },
      formOptions: {
        labelWidth: '80px',
        labelPosition: 'left',
        saveLoading: false
      },
      formRules: {
        date: [ { required: true, message: '请输入日期', trigger: 'blur' } ],
        name: [ { required: true, message: '请输入姓名', trigger: 'blur' } ],
        address: [ { required: true, message: '请输入地址', trigger: 'blur' } ]
      }
    }
  },
  methods: {
    handleRowAdd (row, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        console.log(row)
        this.$message({
          message: '保存成功',
          type: 'success'
        })
        done()
        this.formOptions.saveLoading = false
      }, 300)
    },
    handleDialogCancel (done) {
      this.$message({
        message: '取消保存',
        type: 'warning'
      })
      done()
    }
  }
}
</script>
