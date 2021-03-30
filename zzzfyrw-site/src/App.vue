<template>
  <div id="app" v-cloak>
    <router-view />
  </div>

</template>

<script >
  export default {
    name: "App",
    created() {
    },
    data(){
      return {

      }
    },
    watch: {
      $router(to,come){
        console.log(to);
        console.log(come)
        console.log("==========")
      }
    }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  color: #2c3e50;
  /*margin-top: 60px;*/
}
</style>
