<template>
  <div class="homeBox">
    <el-container style="height: 100%; border: 0px solid #eee" >
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);min-height: 100%;margin-right: 10px" >
        <el-menu :default-openeds="['1', '3']"  style="margin-right: 10px;overflow-x: hidden;min-height: 100%"
                 background-color="rgb(48, 65, 86)"
                 text-color="#fff"
                 active-text-color="#ffd04b">
          <el-submenu index="1" style="color: #42b983">
            <template slot="title"><i class="el-icon-message"></i>林锴城</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="this.$router.go(0)">主页</el-menu-item>
              <el-menu-item index="1-2" @click="$router.push('/dataPanel')">数据统计</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container style="height: 100%; border: 1px solid #eee" direction="vertical">
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown @command="handleCommand">
<!--            <i class="el-icon-setting" style="margin-right: 15px"></i>-->
<!--            <el-dropdown-menu slot="dropdown">-->
<!--              <el-dropdown-item command="person">个人信息</el-dropdown-item>-->
<!--              <el-dropdown-item command="logout">注销</el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
          </el-dropdown>
          <el-form-item label="用户名">

          </el-form-item>

          <div class="message"c> </div>
        </el-header>

        <el-dialog title="新增人员" :visible.sync="addDialogFormVisible">
          <el-form label-width="120">
            <el-form-item label="编号" >
              <el-input v-model="form.pno" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="姓名" >
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" >
              <el-input v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="年龄" >
              <el-input v-model="form.age" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="职务">
              <el-input v-model="form.ptype" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="职称">
              <el-input v-model="form.title" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="政治面貌">
              <el-input v-model="form.politic" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="教育程度">
              <el-input v-model="form.education" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="任职时间">
              <el-input v-model="form.duration" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="来院时间">
              <el-input v-model="form.enterTime" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否退休">
              <el-input v-model="form.retired" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否临时工">
              <el-input v-model="form.temp" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="addDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="add">确 定</el-button>
          </div>
        </el-dialog>


<!--        编辑对话框-->
        <el-dialog title="编辑人员信息" :visible.sync="editDialogFormVisible">
          <el-form label-width="120">
            <el-form-item label="姓名" >
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" >
              <el-input v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="年龄" >
              <el-input v-model="form.age" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="职务">
              <el-input v-model="form.ptype" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="职称">
              <el-input v-model="form.title" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="政治面貌">
              <el-input v-model="form.politic" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="教育程度">
              <el-input v-model="form.education" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="任职时间">
              <el-input v-model="form.duration" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="来院时间">
              <el-input v-model="form.enterTime" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否退休">
              <el-input v-model="form.retired" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否临时工">
              <el-input v-model="form.temp" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>

        <el-main>
          <label style="">主页</label>
          <div style="padding: 10px 10px">
            <el-input style="width: 200px" placeholder="请输入姓名或编号查询" suffix-icon="el-icon-search" v-model="keyword"></el-input>
            <el-button class="ml5" type="primary" @click="search(keyword,keyWordType)" style="margin: 10px 10px">搜索</el-button>
            <div></div>
            <el-radio-group v-model="keyWordType" @change="">
              <el-radio :label="1">姓名查询</el-radio>
              <el-radio :label="2">编号查询</el-radio>
            </el-radio-group>
          </div>

          <div><el-button type="" style="margin: 10px 10px" @click="handleAdd">新增</el-button>
<!--            <el-button type="" style="margin: 10px 10px" @click="exp">导出</el-button>-->
<!--            <el-upload class="upload-demo" action="http://localhost:8081/students/import" accept="xlsx" :show-file-list="false" :on-success="handleSuccessInUpload">-->
<!--              <el-button size="mid" type="" style="margin: 10px 10px">点击上传</el-button>-->
<!--            </el-upload>-->
            <el-select v-model="rankValue" placeholder="请选择排序方式" @change="rankSelectHandler(rankValue)">
              <el-option
                  v-for="item in rankOptions"
                  :key="item.rankValue"
                  :label="item.label"
                  :value="item.rankValue">
              </el-option>
            </el-select>
          </div>


          <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
<!--            <el-table-column-->
<!--                type="selection"-->
<!--                width="55">-->
<!--            </el-table-column>-->
            <el-table-column prop="pno" label="编号" width="65">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="65">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="60">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="65">
            </el-table-column>
            <el-table-column prop="ptype" label="职务">
            </el-table-column>
            <el-table-column prop="title" label="职称" width="70">
            </el-table-column>
            <el-table-column prop="politic" label="政治面貌">
            </el-table-column>
            <el-table-column prop="education" label="教育程度">
            </el-table-column>
            <el-table-column prop="duration" label="任职时间" width="100">
            </el-table-column>
            <el-table-column prop="enterTime" label="来院时间" width="100">
            </el-table-column>
            <el-table-column prop="retired" label="退休">
            </el-table-column>
            <el-table-column prop="temp" label="临时工">
            </el-table-column>
            <el-table-column label="操作" width="160" fixed="right">
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="handleEdit(scope.row)">编辑</el-button>
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.row.pno)">删除</el-button>
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

<!--          <div id="chart"></div>-->

        </el-main>
      </el-container>
    </el-container>

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
      PageSize:5,
      keyword:"",
      keyWordType:1,
      rankOptions: [{
        rankValue: '默认排序',
        label: '默认排序'
      }, {
        rankValue: '年龄排序',
        label: '年龄排序'
      }, {
        rankValue: '来院时间排序',
        label: '来院时间排序'
      }],
      rankValue:"",
      outerVisible:false,
      // multipleSelection: [],
      form:{
        duration:null,
        enterTime:null,
        temp:null,
        retired:null
      },
      editDialogFormVisible:false,
      addDialogFormVisible:false,
    }
  },
  created(){
    this.load()
  },

  methods:{
    load(){
      console.log("loading")
      request.post("http://114.132.156.246:9090/Person/getPageList",{
          pageNum:this.PageNum,
          pageSize:this.PageSize,
          rankAction:null,
          statisticAction:null,
          dialogFormVisible:this.editDialogFormVisible,
          addDialogFormVisible:this.addDialogFormVisible,
        }
      ).then(res=> {
        // console.log(res)
        this.tableData = res.data.personList
        this.total = res.data.sum
      })
    },

    rankSelectHandler(rankAction){
      console.log("按照"+rankAction+"排序");

      if (rankAction == "默认排序"){
        this.load();
        return;
      }

      if (rankAction == "年龄排序"){
        rankAction = "ageRankAction";
      }

      if (rankAction == "来院时间排序"){
        rankAction = "enterDateRankAction";
      }

      request.post("http://114.132.156.246:9090/Person/getPageList",{
            pageNum:this.PageNum,
            pageSize:this.PageSize,
            rankAction:rankAction,
            statisticAction:null,
            dialogFormVisible:this.editDialogFormVisible,
            addDialogFormVisible:this.addDialogFormVisible,
          }
      ).then(res=> {
        // console.log(res)
        this.tableData = res.data.personList
        this.total = res.data.sum
        console.log(res)
      })
    },

    search(keyword,keyWordType){
      console.log("searching")
      if (keyword == null||keyword == ""){
        this.load();
        return;
      }
      request.post("http://114.132.156.246:9090/Person/getPerson",{
            "keyWord":keyword,
            "keyWordType":keyWordType,
          }
      ).then(async res => {
        console.log(res)
        this.tableData = res.data
        // console.log("tableData"+res.data)
        this.total = res.data.length
        await new Promise(res => setTimeout(res, 500))
      })

    },

    save(){
      console.log(this.form)
      request.post("http://114.132.156.246:9090/Person/updatePerson",this.form).then(async res => {
        console.log(res)
        if (res) {
          this.$message.success("保存成功")
          await new Promise(res => setTimeout(res, 400))
          this.editDialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
      this.load()
    },

    add(){
      console.log(this.form)
      request.post("http://114.132.156.246:9090/Person/insertPerson",this.form).then(async res => {
        console.log(res)
        if (res) {
          this.$message.success("保存成功")
          await new Promise(res => setTimeout(res, 500))
          this.addDialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },

    delete(id){
      console.log(id)
      request.post("http://114.132.156.246:9090/Person/deletePerson", {
        "pNo":id
      }).then(async res => {
        console.log(res)
        await new Promise(res => setTimeout(res, 500))
        if (res.data == true) {
          this.$message.success("删除成功")
        } else {
          this.$message.error(res.message)
        }
      })
      this.load();
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
        this.addDialogFormVisible=true
        this.form={}
    },
    handleEdit(form){
        this.form=form
        this.editDialogFormVisible=true
    },
    handleDelete(id){
      this.delete(id)
      this.load()
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection=val
    },
    refresh(){
      this.$router.push(0);
    }
  }
}

</script>

<style>
html,body,.homeBox,.el-container{
  /*设置内部填充为0，几个布局元素之间没有间距*/
  padding: 0px;
  /*外部间距也是如此设置*/
  margin: 0px;
  /*统一设置高度为100%*/
  height: 100%;
}
/*{*/
/*  width: 100%;*/
/*}*/
#chart {
  height: 400px;
}
</style>