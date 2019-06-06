<template>
  <div id="app">
    <img src="./assets/logo.png">
     <div>
      <router-link to="/">首页</router-link>
      <router-link to="/hi">Hi页面</router-link>
      <router-link :to="{name:'hi1',params:{username:'th',id:'8888'}}">Hi页面1</router-link>
      <router-link to="/hi/hi2">Hi页面2</router-link>
      <router-link to="/params/198/th is china">params</router-link>

    </div>
    <!-- name传值 -->
    <p>{{$route.name}}</p>
    <!-- 当页面多路由操作 -->
    <router-view/>
   <router-view name="left" style="width:50%;float:left;background:#ddd;height:300px"/>
   <router-view name="right" style="width:50%;float:left;background:red;height:300px"/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
