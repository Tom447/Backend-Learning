<template>
  <div class="app-container">

    <!--搜索表单-->
    <el-form :inline="true" :model="searchClazz" class="demo-form-inline">

      <el-form-item label="班级名称">
        <el-input v-model="searchClazz.name" placeholder="请输入班级名称" />
      </el-form-item>

      <el-form-item label="结课时间">
        <el-date-picker
          v-model="entrydate"
          clearable
          value-format="yyyy-MM-dd"
          type="daterange"
          placeholder="选择日期"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="width: 400px; margin-left: 20px"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>

    </el-form>

    <!--按钮-->
    <el-row>
      <el-button type="primary" size="medium" @click="dialogVisible = true;">+ 新增班级</el-button>
    </el-row>

    <br>

    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="name" label="班级名称" align="center" />
        <el-table-column prop="room" label="班级教室" align="center" />
        <el-table-column prop="beginDate" label="开课时间" align="center" />
        <el-table-column prop="endDate" label="结课时间" align="center" />
        <el-table-column prop="masterName" label="班主任" align="center" />

        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.id)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <br>

    <!--分页工具条-->
    <el-pagination
      :background="background"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="编辑班级信息" :visible.sync="dialogVisible" width="30%">
      <el-form ref="emp" :model="clazz" label-width="80px" size="mini">

        <el-form-item label="班级名称">
          <el-input v-model="clazz.name" />
        </el-form-item>

        <el-form-item label="班级教室">
          <el-input v-model="clazz.room" />
        </el-form-item>

        <el-form-item label="开课时间">
          <el-date-picker
            v-model="clazz.beginDate"
            clearable
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            size="small"
            style="width:100%"
          />
        </el-form-item>

        <el-form-item label="结课时间">
          <el-date-picker
            v-model="clazz.endDate"
            clearable
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            size="small"
            style="width:100%"
          />
        </el-form-item>

        <el-form-item label="班主任">
          <el-select v-model="clazz.masterId" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in empList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="save('emp')">提交</el-button>
          <el-button @click="cancel('emp')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { page, add, update, deleteById, selectById } from '@/api/clazz.js'
import { findAll } from '@/api/emp.js'

export default {
  data() {
    return {
      background: true,
      // 每页显示的条数
      pageSize: 5,
      // 总记录数
      totalCount: 100,
      // 当前页码
      currentPage: 1,
      // 添加数据对话框是否展示的标记
      dialogVisible: false,
      searchClazz: {
        name: ''
      },
      entrydate: '',
      beginTime: '',
      endTime: '',
      tableData: [],

      clazz: {
        name: '',
        room: '',
        beginDate: '',
        endDate: '',
        masterId: ''
      },

      empList: [],
      genderList: [{ id: 1, name: '男' }, { id: 2, name: '女' }],
      jobList: [{ id: 1, name: '班主任' }, { id: 2, name: '讲师' }, { id: 3, name: '学工主管' }, { id: 4, name: '教研主管' }],
      rules: {
        name: [
          { required: true, message: '请输入班级名称', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ]
      }
    }
  },

  watch: {
    entrydate(val) {
      if (val && val.length >= 2) {
        this.beginTime = val[0]
        this.endTime = val[1]
      } else {
        this.beginTime = ''
        this.endTime = ''
      }
    }
  },

  mounted() {
    this.page() // 当页面加载完成后，发送异步请求，获取数据
    findAll().then(res => {
      this.empList = res.data.data
    })
  },

  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchClazz.name,
        this.beginTime,
        this.endTime,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1
      this.page()
    },

    clear() {
      this.searchClazz = { name: '' }
      this.beginTime = ''
      this.endTime = ''
      this.entrydate = ''
      this.page()
    },

    // 添加数据
    save(formName) {
      let operator

      if (this.clazz.id) { // 修改
        operator = update(this.clazz)
      } else { // 新增
        operator = add(this.clazz)
      }

      operator.then((resp) => {
        // eslint-disable-next-line eqeqeq
        if (resp.data.code == 1) {
          this.dialogVisible = false // 关闭Dialog
          this.page()
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.clazz = {}
        } else {
          this.$message.error(resp.data.msg)
        }
      })
    },

    update(id) {
      // 1. 打开窗口
      this.dialogVisible = true

      // 2. 发送请求
      selectById(id).then((result) => {
        if (result.data.code === 1) {
          this.clazz = result.data.data
        }
      })
    },

    // 分页
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val
      this.page()
    },

    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val
      this.page()
    },

    // 删除员工信息 -- id
    deleteById(id) {
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 2. 发送AJAX请求
        deleteById(id).then((resp) => {
          if (resp.data.code === 1) {
            // 删除成功
            this.$message.success('恭喜你，删除成功')
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info('已取消删除')
      })
    }
  }
}
</script>

<style>
</style>
