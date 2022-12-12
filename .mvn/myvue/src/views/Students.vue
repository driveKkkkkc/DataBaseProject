<template>
  <div>
  <div>
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%;margin-right: 10px" >
        <el-menu :default-openeds="['1', '3']"  style="margin-right: 10px">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航一</template>
            <el-menu-item-group>
              <!--            <template slot="title">分组一</template>-->
              <el-menu-item index="1-1" @click="this.$router.go(0)">主页</el-menu-item>
              <el-menu-item index="1-2" @click="$router.push('/person')">个人资料</el-menu-item>
              <el-menu-item index="1-1" @click="$router.push('/usersmanage')">用户管理</el-menu-item>
            </el-menu-item-group>
            <!--          <el-menu-item-group title="分组2">-->
            <!--            <el-menu-item index="1-3">选项3</el-menu-item>-->
            <!--          </el-menu-item-group>-->
          </el-submenu>
          <!--        <el-submenu index="2">-->
          <!--          <template slot="title"><i class="el-icon-menu"></i>导航二</template>-->
          <!--          <el-menu-item-group>-->
          <!--            <template slot="title">分组一</template>-->
          <!--            <el-menu-item index="2-1">选项1</el-menu-item>-->
          <!--            <el-menu-item index="2-2">选项2</el-menu-item>-->
          <!--          </el-menu-item-group>-->
          <!--          <el-menu-item-group title="分组2">-->
          <!--            <el-menu-item index="2-3">选项3</el-menu-item>-->
          <!--          </el-menu-item-group>-->
          <!--          <el-submenu index="2-4">-->
          <!--            <template slot="title">选项4</template>-->
          <!--            <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
          <!--          </el-submenu>-->
          <!--        </el-submenu>-->
          <!--        <el-submenu index="3">-->
          <!--          <template slot="title"><i class="el-icon-setting"></i>导航三</template>-->
          <!--          <el-menu-item-group>-->
          <!--            <template slot="title">分组一</template>-->
          <!--            <el-menu-item index="3-1">选项1</el-menu-item>-->
          <!--            <el-menu-item index="3-2">选项2</el-menu-item>-->
          <!--          </el-menu-item-group>-->
          <!--          <el-menu-item-group title="分组2">-->
          <!--            <el-menu-item index="3-3">选项3</el-menu-item>-->
          <!--          </el-menu-item-group>-->
          <!--          <el-submenu index="3-4">-->
          <!--            <template slot="title">选项4</template>-->
          <!--            <el-menu-item index="3-4-1">选项4-1</el-menu-item>-->
          <!--          </el-submenu>-->
          <!--        </el-submenu>-->
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown @command="handleCommand">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="person">个人信息</el-dropdown-item>
              <el-dropdown-item command="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-form-item label="用户名">

          </el-form-item>

          <div class="message"c> </div>
        </el-header>

        <el-dialog title="新增学生" :visible.sync="dialogFormVisible">
          <el-form label-width="120">
            <el-form-item label="用户名" >
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="年龄" >
              <el-input v-model="form.age" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="总分">
              <el-input v-model="form.score" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Grade">
              <el-input v-model="form.grade" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>

        <el-main>
          <label style="">主页</label>
          <div style="padding: 10px 10px">
            <el-input style="width: 200px" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name"></el-input>
            <el-button class="ml5" type="primary" @click="load" style="margin: 10px 10px">搜索</el-button>
          </div>

          <div><el-button type="" style="margin: 10px 10px" @click="handleAdd">新增</el-button><el-button type="" style="margin: 10px 10px" @click="exp">导出</el-button>
            <el-upload class="upload-demo" action="http://localhost:8081/students/import" accept="xlsx" :show-file-list="false" :on-success="handleSuccessInUpload">
              <el-button size="mid" type="" style="margin: 10px 10px">点击上传</el-button>
            </el-upload>
          </div>

          <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column prop="id" label="编号" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="age" label="年龄">
            </el-table-column>
            <el-table-column prop="score" label="总分">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="handleEdit(scope.row)">编辑</el-button>
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <div>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="PageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="PageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>

          <div id="chart"></div>

        </el-main>
      </el-container>
    </el-container>
  </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from "echarts";
import router from "@/router";
export default {

  mounted() {},

  data() {
    return {
      tableData: [],
      chartData: {},
      total:0,
      PageNum:1,
      PageSize:2,
      id:1,
      name:"",
      age:1,
      score:0,
      outerVisible:false,
      grade:1,
      multipleSelection: [],
      form:{},
      dialogFormVisible:false,
    }
  },
  created(){
    this.load()
  },

  methods:{
    load(){
      console.log("loading")
      request.get("http://localhost:8081/students/page",{
        params:{
          id:this.id,
          PageNum:this.PageNum,
          PageSize:this.PageSize,
          name:this.name,
          age:this.age,
          score:this.age,
          grade:this.grade,
          multipleSelection: this.multipleSelection,
          dialogFormVisible:this.dialogFormVisible,
        }
      }).then(res=> {
        // console.log(res)
        this.tableData = res.studentIPage.records
        this.total = res.studentIPage.total
        this.chartData = res.nameVo
        console.log(res)
        // console.log("namevo")
        // console.log(this.chartData)

        let myChart = echarts.init(document.getElementById('chart'));
// 绘制图表
        myChart.setOption({
          title: {
            text: 'ECharts 入门示例'
          },
          tooltip: {},
          xAxis: {
            data: this.chartData.names
          },
          yAxis: {},
          series: [
            {
              name: '销量',
              type: 'bar',
              data: this.chartData.scores
            }
          ]
        })
      })
    },

    save(){
      console.log(this.form)
      request.post("http://localhost:8081/students",this.form).then(res=>{
        console.log(res)
        if(res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
        }
        else{
          this.$message.error("保存失败")
        }
      })
      this.load()
    },
    logout(){
      router.push("http://localhost:8080/security")
    },
    delete(id){
      console.log(id)
      request.delete("http://localhost:8081/students/"+id).then(res=>{
        console.log(res)
        if(res.code=="200"){
          this.$message.success("删除成功")
        }
        else{
          this.$message.error(res.message)
        }
      })
    },
    handleSizeChange(PageSize){
      console.log(PageSize)
      this.PageSize=PageSize
      this.load()
    },
    handleCurrentChange(PageNum){
      console.log(PageNum)
      this.PageNum=PageNum
      this.load()
    },

    handleAdd(){
      // console.log(localStorage.getItem("UserAuthorities"))
      if(localStorage.getItem("UserAuthorities").search("manage")!=-1){//判断是否存在对应权限
        this.dialogFormVisible=true
        this.form={}
      }
      else {
        this.$message.error("权限不足");
      }
    },
    handleEdit(form){
      if(localStorage.getItem("UserAuthorities").search("manage")!=-1){
        this.form=form
        this.dialogFormVisible=true
      }
      else {
        this.$message.error("权限不足");
      }
    },
    handleDelete(id){
      this.delete(id)
      this.load()
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection=val
    },
    exp(){
      window.open("http://localhost:8081/students/import")
    },
    handleSuccessInUpload(){
      this.$message.success("导入成功");
      this.load()
    },
    handleCommand(command){
      if(command=="logout"){
        console.log("logout"+command)
        router.push("/security");
      }
      if(command=="person"){
        console.log("logout"+command)
        router.push("/person");
      }
    },
    refresh(){
      this.$router.push(0);
    }
  }
}

</script>

<style>
#chart,
html,
body {
  width: 100%;
}
#chart {
  height: 400px;
}
</style>