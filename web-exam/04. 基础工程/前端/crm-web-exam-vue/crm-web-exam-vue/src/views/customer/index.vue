<template>
  <div class="app-container">

    <!--搜索表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="searchCustomer.name" placeholder="请输入客户姓名"></el-input>
      </el-form-item>

      <el-form-item label="性别">
        <el-select v-model="searchCustomer.gender" placeholder="请选择">
          <el-option :label="gender.name" :value="gender.id" v-for="(gender,index) in genderList" :key="index"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="手机号">
        <el-input v-model="searchCustomer.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>

      <el-form-item label="QQ">
        <el-input v-model="searchCustomer.qq" placeholder="请输入QQ号"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
         <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <!--按钮-->
    <el-row>
      <el-button type="primary" size="medium" @click="dialogVisible = true; customer = { };" >+ 新增客户</el-button>
      <el-button type="danger" size="medium" @click="deleteByIds()" >- 批量删除</el-button>
    </el-row>

    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="客户信息" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="customer" label-width="80px" >
        
        <el-form-item label="姓名">
            <el-input v-model="customer.name" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        
        <el-form-item label="手机号">
          <el-input v-model="customer.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>

        <el-form-item label="性别">
          <el-select v-model="customer.gender" placeholder="请选择" style="width: 100%">
            <el-option :label="gender.name" :value="gender.id" v-for="(gender,index) in genderList" :key="index"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="年龄">
          <el-input v-model="customer.age" placeholder="请输入年龄"></el-input>
        </el-form-item>

        <el-form-item label="QQ">
          <el-input v-model="customer.qq" placeholder="请输入QQ"></el-input>
        </el-form-item>

        <el-form-item label="备注说明">
          <el-input v-model="customer.description" placeholder="请输入备注说明"></el-input>
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="save">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>


    <br>


    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"  align="center"></el-table-column>
        <el-table-column prop="name"  label="姓名"  align="center"></el-table-column>
        <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
        <el-table-column label="性别" align="center">
          <template slot-scope="scope">
            {{scope.row.gender == 1? '男':'女'}}
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" align="center"></el-table-column>
        <el-table-column prop="qq" label="QQ" align="center"></el-table-column>
        <el-table-column align="center" label="最后操作时间">
          <template slot-scope="scope">
            {{scope.row.updateTime ? scope.row.updateTime.replace('T',' '):''}}
          </template>
        </el-table-column>
        
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
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :background="background"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById } from "@/api/clazz.js";

export default {
  data() {
    return {
      background: true,
      // 每页显示的条数
      pageSize: 5,
      // 总记录数
      totalCount: 0,
      // 当前页码
      currentPage: 1,
      // 添加数据对话框是否展示的标记
      dialogVisible: false,
      // 模型数据
      searchCustomer: {
        name: "",
        gender: "",
        phone: "",
        qq: ""
      },
      customer: {
         id: "",
         name: "",
         phone: "",
         gender: "",
         age: "",
         qq: "",
         description: ""
      },
      genderList: [{id: 1,name: "男"},{id: 2,name: "女"}],
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: []
    };
  },

  mounted() {
    this.page(); //当页面加载完成后，发送异步请求，获取数据
  },
  
  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchCustomer.name,
        this.searchCustomer.gender,
        this.searchCustomer.phone,
        this.searchCustomer.qq,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      });
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1;
      this.page();
    },

    clear(){
      this.searchCustomer = {name: "", gender: "", phone: "", qq: ""};
      this.page();
    },

    // 保存数据
    save() {
      let operator;

      if (this.customer.id) { //修改
        operator = update(this.customer);
      } else { //添加
        operator = add(this.customer);
      }

      operator.then((resp) => {
        if (resp.data.code == 1) {
          this.dialogVisible = false;
          this.page();
          this.$message({ message: "恭喜你，保存成功", type: "success" });
          this.customer = {};
        } else {
          this.$message.error(resp.data.msg);
        }
      });
    },

    update(id) {
      //1. 打开窗口
      this.dialogVisible = true;
      //2. 发送请求
      selectById(id).then((result) => {
        if (result.data.code == 1) {
          this.customer = result.data.data;
        }
      });
    },


    // 批量删除员工信息
    deleteByIds() {
      // 弹出确认提示框
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
          //1. 创建id数组, 从 this.multipleSelection 获取即可
          for (let i = 0; i < this.multipleSelection.length; i++) {
            this.selectedIds[i] = this.multipleSelection[i].id;
          }
          
          //2. 发送AJAX请求
          deleteById(this.selectedIds).then((resp) => {
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

    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection=val;
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
    },


    //删除班级信息
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
  },

  watch: {
    
  },
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.picker-width{
    width: 100%;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>
