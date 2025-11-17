<template>
  <div class="app-container">

    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="编辑学生" :visible.sync="dialogVisible" width="30%">
      <el-form :model="student" ref="student" label-width="80px" size="mini">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="student.name"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="no">
          <el-input v-model="student.no"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="gender">
          <el-select v-model="student.gender" placeholder="请选择" style="width:100%">
            <el-option v-for="item in genderList" :key="item.value" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="手机号" prop="phone">
          <el-input v-model="student.phone"></el-input>
        </el-form-item>

        <el-form-item label="最高学历">
          <el-select v-model="student.degree" placeholder="请选择" value-key="student.degree" style="width:100%">
            <el-option v-for="item in degreeList" :key="item.value" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>


        <el-form-item label="所属班级">
          <el-select v-model="student.clazzId" placeholder="请选择" style="width:100%">


            <el-option v-for="item in clazzList" :key="item.value" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="save('student')">提交</el-button>
          <el-button @click="cancel('student')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 违纪对话框 -->
    <el-dialog ref="form" title="学员违纪处理" :visible.sync="violationDialogVisible" width="30%">
      <el-form :model="student" ref="student" label-width="80px" size="mini">
        <el-form-item label="违纪" prop="violationScore">
          <el-input v-model="student.violationScore"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="violation()">确定</el-button>
          <el-button @click="cancel('student')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" align="center"></el-table-column>
        <el-table-column prop="no" label="学号" align="center"></el-table-column>
        <el-table-column prop="clazzName" label="班级" align="center"></el-table-column>
        <el-table-column align="center" label="性别">
          <template slot-scope="scope">
            <span style="margin-right: 10px">
              {{ scope.row.gender == "1" ? "男" : "女" }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
        <el-table-column align="center" label="最高学历">
          <template slot-scope="scope">
            <span style="margin-right: 10px" v-if="scope.row.degree == 1">初中</span>
            <span style="margin-right: 10px" v-if="scope.row.degree == 2">高中</span>
            <span style="margin-right: 10px" v-if="scope.row.degree == 3">大专</span>
            <span style="margin-right: 10px" v-if="scope.row.degree == 4">本科</span>
            <span style="margin-right: 10px" v-if="scope.row.degree == 5">硕士</span>
            <span style="margin-right: 10px" v-if="scope.row.degree == 6">博士</span>
          </template>
        </el-table-column>
        <el-table-column prop="violationCount" label="违纪次数" align="center"></el-table-column>
        <el-table-column prop="violationScore" label="违纪扣分" align="center"></el-table-column>
        <el-table-column align="center" label="最后操作时间">
          <template slot-scope="scope">
            {{ scope.row.updateTime ? scope.row.updateTime.replace('T', ' ') : '' }}
          </template>
        </el-table-column>

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <div style="display: flex; gap: 8px; justify-content: center;">
              <el-button type="primary" size="small" @click="update(scope.row.id)">编辑</el-button>
              <el-button type="primary" size="small" @click="openViolationDialog(scope.row)">违纪</el-button>
              <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <br>

    <!--分页工具条-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :background="background"
      :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="5"
      layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById, violation } from "@/api/student.js";
import { findAll } from "@/api/clazz";
import { getToken } from '@/utils/auth';
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
      //违纪对话框是否展示标记
      violationDialogVisible: false,
      // 品牌模型数据
      searchStudent: {
        name: "",
        no: "",
        degree: "",
        clazzId: "",
        page: "",
        pageSize: ""
      },
      student: {
        id: "",
        name: "",
        no: "",
        gender: "",
        phone: "",
        degree: "",
        violationCount: "",
        violationScore: "",
        clazzId: "",
        createTime: "",
        updateTime: "",
        clazzName: ""
      },
      genderList: [{ id: 1, name: "男" }, { id: 2, name: "女" }],
      degreeList: [
        { id: 1, name: "初中" },
        { id: 2, name: "高中" },
        { id: 3, name: "大专" },
        { id: 4, name: "本科" },
        { id: 5, name: "硕士" },
        { id: 6, name: "博士" }
      ],
      clazzList: [],
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],
      token: { token: getToken() },
    };
  },
  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchStudent.name,
        this.searchStudent.no,
        this.searchStudent.degree,
        this.searchStudent.clazzId,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      }).catch(err => {
        console.error("分页请求失败：", err);
      });
    },

    update(id) {
      //1. 打开窗口
      this.dialogVisible = true;
      //2. 发送请求
      selectById(id).then((result) => {
        if (result.data.code == 1) {
          this.student = result.data.data;
        }
      });
    },
    // 添加数据
    save(formName) {
      //校验表单
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let operator;
          if (this.student.id) {//修改
            operator = update(this.student);
          } else { //新增
            operator = add(this.student);
          }
          operator.then((resp) => {
            if (resp.data.code == 1) {
              this.dialogVisible = false;
              this.page();
              this.$message({ message: "恭喜你，保存成功", type: "success" });
              // this.emp = { image: "" };
            } else {
              this.$message.error(resp.data.msg);
            }
          });
        }
      });
    },
    //删除员工信息
     //删除员工信息
    deleteById(id){
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
          //2. 发送AJAX请求
          deleteById(id).then((resp) => {
            if (resp.data.code == 1) {
              //删除成功
              this.$message.success("恭喜你，删除成功");
              this.page();
            } else {
              this.$message.error(resp.data.msg);
            }
          });
      }).catch(() => {
          //用户点击取消按钮
          this.$message.info("已取消删除");
        });
    },
    //违纪对话框
    openViolationDialog(row) {
      //清空上次输入
      this.student = {
        id: row.id,
        violationScore: ''//初始化为空
      };
      this.violationDialogVisible = true;//显示对话框
    },
    //提交违纪处理
    async violation() {
      const id = this.student.id;           // 获取学生 ID
      const score = Number(this.student.violationScore); // 获取输入的分数

      // 校验输入
      if (!score || score <= 0) {
        this.$message.error('请输入大于0的扣分');
        return;
      }

      try {
        const resp = await violation(id, score); // 调用接口
        if (resp.data.code === 1) {
          this.$message.success('违纪处理成功');
          this.violationDialogVisible = false;
          this.page(); // 刷新列表
        } else {
          this.$message.error(resp.data.msg);
        }
      } catch (error) {
        this.$message.error('提交失败，请检查网络');
      }
    },

    cancel(formName) {
      this.dialogVisible = false;
      this.violationDialogVisible = false;
      this.$refs[formName].resetFields();
    },
    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //分页
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val;
      this.page();
    },

    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val;
      this.page();
    }
  },
  mounted() {
    this.page() // 当页面加载完成后，发送异步请求，获取数据
    findAll().then((result) => {
      this.clazztList = result.data.data;
    });
  },
}
</script>

<style>
h1 {
  text-align: center;
  font-family: 楷体;
}
</style>