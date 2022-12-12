<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%;margin-right: 10px" >
      <el-menu :default-openeds="['1', '3']"  style="margin-right: 10px">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>导航一</template>
          <el-menu-item-group>
<!--            <template slot="title">分组一</template>-->
            <el-menu-item index="1-1" @click="$router.push('/home')">主页</el-menu-item>
            <el-menu-item index="1-2" @click="this.$router.go(0)">个人资料</el-menu-item>
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
      <el-form >
        <el-button  size="mini" style="color: black;" @click="$router.push('/home')">返回</el-button>
      </el-form>

      <el-main>
        <label-wrap style="">个人资料</label-wrap>
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="用户ID:">
            <el-input v-text="form.id"></el-input>
          </el-form-item>
          <el-form-item label="用户名:">
            <el-input v-text="form.name"></el-input>
          </el-form-item>
          <el-form-item label="手机号:">
            <el-input v-text="form.phonenum"></el-input>
          </el-form-item>
          <el-form-item label="权限:">
            <el-button v-text="form.role"> </el-button>
          </el-form-item>
<!--          <el-form-item label="活动区域">-->
<!--            <el-select v-model="form.region" placeholder="请选择活动区域">-->
<!--              <el-option label="区域一" value="shanghai"></el-option>-->
<!--              <el-option label="区域二" value="beijing"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="活动时间">-->
<!--            <el-col :span="11">-->
<!--              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>-->
<!--            </el-col>-->
<!--            <el-col class="line" :span="2">-</el-col>-->
<!--            <el-col :span="11">-->
<!--              <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>-->
<!--            </el-col>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="即时配送">-->
<!--            <el-switch v-model="form.delivery"></el-switch>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="活动性质">-->
<!--            <el-checkbox-group v-model="form.type">-->
<!--              <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>-->
<!--              <el-checkbox label="地推活动" name="type"></el-checkbox>-->
<!--              <el-checkbox label="线下主题活动" name="type"></el-checkbox>-->
<!--              <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>-->
<!--            </el-checkbox-group>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="特殊资源">-->
<!--            <el-radio-group v-model="form.resource">-->
<!--              <el-radio label="线上品牌商赞助"></el-radio>-->
<!--              <el-radio label="线下场地免费"></el-radio>-->
<!--            </el-radio-group>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="活动形式">-->
<!--            <el-input type="textarea" v-model="form.desc"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item>-->
<!--            <el-button type="primary" @click="onSubmit">立即创建</el-button>-->
<!--            <el-button>取消</el-button>-->
<!--          </el-form-item>-->
        </el-form>
      </el-main>

    </el-container>
  </el-container>



</template>

<script>
import router from "@/router";

export default {
name: "PersonInformation",
  data() {
    return {
      form: {
        id:'',
        name: '',
        phonenum:'',
        role:'Super'
      }
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){
      this.form.name=localStorage.getItem('UserName')
      this.form.id=localStorage.getItem('UserId')
      this.form.phonenum=localStorage.getItem('UserPhone')
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
    }
  }
}
</script>

<style scoped>

</style>