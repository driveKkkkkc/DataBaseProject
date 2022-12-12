<template xmlns:th="http://www.w3.org/1999/xhtml">
  <div class="wrapper">
    <div
        style="margin: 100px auto; background-color: #fff; width: 825px; height: 500px; padding: 20px; border-radius: 10px">
      <el-button type="" size="small" autocomplete="off" @click="$router.push('/security')">back</el-button>
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册新账户</b></div>
      <el-form :model="user"  ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.phone"></el-input>
        </el-form-item>

        <el-form-item prop="captcha">
          <el-input id="captcha"  size="small" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="code"></el-input>
        </el-form-item>

        <el-form-item prop="captcha">
        <img src="http://localhost:8081/creatKaptcha" id="img"  @click="captcha"/>
        </el-form-item>


        <el-button type="primary" size="small" autocomplete="off" @click="verify">验证</el-button>
        <el-button type="primary" size="small" autocomplete="off" @click="register">注册用户</el-button>

      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import router from "@/router";


export default {
  name: "Register",
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

  },
  methods:{
    captcha(){
      document.getElementById("img").src="http://localhost:8081/creatKaptcha";
    },

    //验证验证码
    verify(){
      console.log(this.code)
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
    register(){
      console.log(this.user)
      request.post("http://localhost:8081/register",this.user).then(res=>{
        if(res.code=="200"){
          this.$message.success(res.message);
          router.push("/security")
        }
        else{
          this.$message.error(res.message);
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