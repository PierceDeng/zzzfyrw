<template>

  <div>
<!--    <div class="back1"></div>-->
    <div class="login-container back1">

      <div class="login-header">
        <el-space wrap :size="size">
          <el-button type="text">联系我们</el-button>
          <el-button type="text">联系我们</el-button>
          <el-button type="text">联系我们</el-button>
          <!--        <el-button type="text">联系我们</el-button>-->

        </el-space>

      </div>
      <div class="login-main">
        <div class="login-center">
          <div class="login-center-left" />
          <div class="login-center-right">
            <div class="login-info">
              <el-container>
                <el-header class="login-info-header">
                  <span>Welcome</span>
                  <span>Login</span>
                  <!--                <span>!</span>-->
                  <!--                <span>录</span>-->
                </el-header>
                <el-divider></el-divider>
                <el-main>
                  <el-form label-position="left" :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
                           label-width="50px" size="medium ">
                    <el-form-item label="账号" prop="pass">
                      <el-input  v-model="ruleForm.pass" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                      <el-input type="password" v-model="ruleForm.pass" />
                    </el-form-item>
                    <div>
                      <el-button type="primary">Sing In</el-button>
                    </div>

                  </el-form>

                </el-main>
                <el-footer></el-footer>
              </el-container>
            </div>
          </div>
        </div>
      </div>
      <div class="login-footer">

      </div>
    </div>

  </div>









</template>






<script>
export default {
  name: "Login",
  data() {
    return {
      size: 64,
      ruleForm: {},
      rules:[],
    }
  },
}
</script>

<style lang="scss" scoped>
  @import "Login";
</style>