<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.username" placeholder="用户名" clearable></el-input>
          <el-input v-model="searchModel.phone" placeholder="电话" clearable></el-input>
          <el-button type="primary" @click="getUserList" round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" @click="openEditUi(null)">新增</el-button>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-table :data="userList" stripe style="width: 100%">
        <el-table-column label="#" width="80">
          <template slot-scope="scope">
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="username" label="姓名" width="180">
        </el-table-column>
        <!-- <el-table-column prop="password" label="密码" width="180">
        </el-table-column> -->
        <el-table-column prop="sex" label="性别" width="180">
          <template slot-scope="scope">
            <span v-if="scope.row.sex==1">男</span>
            <span v-else>女</span>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="180">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==1">正常</el-tag>
            <el-tag v-else type="danger">禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column  label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="openEditUi(scope.row.id)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="deleteUser(scope.row.id)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[5, 10, 20, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" ref="userFormRef" :rules="rules">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-model="userForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="userForm.id==null || userForm.id==undefined" label="密码" prop="password" :label-width="formLabelWidth">
          <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-input v-model="userForm.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
          <el-input v-model="userForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-switch
            v-model="userForm.status"
            :active-value="1"
            :inactive-value="0">
          </el-switch>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import userApi from "@/api/usermanage"
export default {
  data() {
    var reg=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
    var checkEmial = (rule, value, callback) => {
        if (!reg.test(value)) {
          return callback(new Error('邮箱格式不正确'));
        }
        callback();
      }
    return {
      title:"",
      dialogFormVisible:false,
      userForm:{},
      formLabelWidth:'130px',
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 5
      },
      userList: [],
      rules:{
        username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 6, message: '长度在 3 到 6 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmial, trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    deleteUser(id){
      this.$confirm(`您是否删除id为${id}的用户`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userApi.deleteUserById(id).then(response=>{
            this.$message({
              type: 'success',
              message: response.message
            });
            this.getUserList();
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });

    },
    saveUser(){
      this.$refs.userFormRef.validate((valid) => {
          if (valid) {
            userApi.saveUser(this.userForm).then(response=>{
              this.$message({
                message: response.message,
                type: 'success'
            });
            this.dialogFormVisible = false;
            this.getUserList();
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      
    },
    clearForm(){
      this.userForm={};
      this.$refs.userFormRef.clearValidate();

    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getUserList();


    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getUserList();


    },
    getUserList() {
      userApi.getUserList(this.searchModel).then(response => {
        this.userList = response.data.rows;
        this.total = response.data.total;
      })
    },
    openEditUi(id){
      if(id==null){
        this.title="新增用户";
      }else{
        this.title="修改用户";
        this.userForm=userApi.getUserById(id).then(response=>{
          this.userForm=response.data;
        })
      }
      
      this.dialogFormVisible=true;
    }

  },
  created() {
    this.getUserList();
  }

}
</script>
<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}
.el-dialog .el-input{
  width: 85%;
}
</style>
  