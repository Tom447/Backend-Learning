<template>
  <div class="app-container">




    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column prop="targetClass" label="目标类名" align="center"></el-table-column>
        <el-table-column prop="targetMethod" label="目标方法名" align="center"></el-table-column>
        <el-table-column prop="clientBrowser" label="客户端浏览器" align="center"></el-table-column>
        <el-table-column prop="clientPlatform" label="客户端操作系统" align="center"></el-table-column>
        <el-table-column prop="operateTime" label="操作时间" align="center"></el-table-column>
        <el-table-column prop="costTime" label="操作耗时(ms)" align="center"></el-table-column>
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
import { page } from "@/api/log.js";

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
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      searchLog: {
        page: "",
        pageSize: ""
      },
      Log: {
        targetClass: "",
        targetMethod: "",
        clientBrowser: "",
        operateTime: "",
        costTime: ""
      },
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
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      });
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

.picker-width {
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
