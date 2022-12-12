<template xmlns:th="http://www.w3.org/1999/xhtml">
  <div class="wrapper">
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 430px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user"  ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>

        <!--        <el-form-item label="输入验证码" required>-->
        <!--          <el-input v-model="" auto-complete="off" class="el-col-width"></el-input>-->
        <!--        </el-form-item>-->
        <el-form-item prop="captcha">
          <el-input id="captcha"  size="small" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="code"></el-input>
        </el-form-item>

        <img src="http://localhost:8081/creatKaptcha" id="img"  @click="captcha"/>

        <!--          <div>-->
        <!--            <h5>图像验证码</h5>-->
        <!--            <img src="http://localhost:8080/getimg" id="images" @click="replace">-->
        <!--            <el-input size="mini" style="margin: 10px 0" prefix-icon="el-icon-lock" v-model="code"></el-input>-->
        <!--            <el-button type="" size="small" autocomplete="off" @click="login">验证</el-button>-->
        <!--            <a> 判断结果：{{this.$data.message}}</a>-->
        <!--          </div>-->
        <!--          <br>-->

        <el-form-item prop="login">
        <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="verify">验证</el-button>
        </el-form-item>
        <el-form-item prop="register">
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";


export default {
  name: "Login",
  data() {
    return {
      data:'',
      message:'',
      user: {},
      code:"",
      verify_result:''
    }
  },
  created() {
    localStorage.removeItem("token");
  },
  methods:{
    captcha(){
      document.getElementById("img").src="http://localhost:8081/creatKaptcha";
    },
    //验证验证码
    verify(){
      // console.log(this.code)
      request.get("http://localhost:8081/verify", {
        params:{
          code:this.code
        }
      }).then(res=>{
        this.verify_result=res
        if(this.verify_result==true){
          this.$message.success("验证成功");
        }
        else {
          this.$message.error("验证失败");
        }
        // console.log(res)
        console.log(this.verify_result)
      }).catch(err=>{
        this.$data.verify_result=err.data
      })
    },

    login(){
      console.log(this.code)
      console.log(this.user.name,this.user.password)
      request.post("http://localhost:8081/user/login",this.user).then(res=>{

        if(res.token==null){
          this.$message.error("login failed");
        }
        else{
          console.log(res);
          localStorage.setItem("token",res.token)
          localStorage.setItem("UserId",res.loginUser.user.id)
          localStorage.setItem("UserName",res.loginUser.user.name)
          localStorage.setItem("UserPhone",res.loginUser.user.phone_number)
          localStorage.setItem("UserAuthorities",res.loginUser.menus)
          this.$router.push("/home");
        }
      })
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>