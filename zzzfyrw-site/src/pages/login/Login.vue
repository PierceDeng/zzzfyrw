<template>

  <div class="login-container">
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
                <span>Z-Admin后台管理系统</span>
              </el-header>
              <el-divider></el-divider>
              <el-main></el-main>
              <el-footer></el-footer>
            </el-container>
          </div>
        </div>
      </div>
    </div>
    <div class="login-footer">

    </div>
  </div>







</template>






<script>
export default {
  name: "Login",
  data() {
    return {
      size: 20,
    }
  },
}
</script>

<style lang="scss" scoped>
  @import "Login";
</style>