<template>
  <div id="app" v-cloak>
    <router-view />
  </div>

</template>

<script >
  export default {
    name: "app",
    created() {
    },
    data(){
      return {

      }
    },
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
