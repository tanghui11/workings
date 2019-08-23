<template>
  <div id="app">
    <img src="./assets/logo.png">
  
    <div>
      <button @click="goBack">后退</button>
      <button @click="goGo">前进</button>
      <button @click="goHome">返回首页</button>
    </div>
     <div>
      <router-link to="/">首页</router-link>
      <router-link to="/hi">Hi页面</router-link>
      <router-link :to="{name:'hi1',params:{username:'th',id:'8888'}}">Hi页面</router-link>
      <router-link to="/hi/hi2">Hi页面2</router-link>
      <router-link :to="urls">params</router-link>
      <router-link to="/goHome">goHome</router-link>
      <router-link to="/goParams/1234/th">goParams</router-link>
      <router-link to="/shiyan">实验</router-link>
      <router-link to="/jsth">jsth</router-link>
      <router-link to="/homes">homes</router-link>
        
    </div>
    <!-- name传值 -->
    <!-- <p>{{$route.name}}</p> -->
    <!-- 当页面多路由操作 -->
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>
   <router-view name="left" style="width:50%;float:left;background:#ddd;height:300px"/>
   <router-view name="right" style="width:50%;float:left;background:red;height:300px"/>
  </div>
</template>

<script>
import son from "./components/son";
export default {
  name: 'App',
  data () {
    return {
       urls:"/params/198/th is china"
    }
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    },
    goGo(){
      this.$router.go(1);
    },
    goHome(){
      this.$router.push("/");
    }
  }
}
</script>

<style>
.fade-ender{
  opacity: 0;
}
.fade-enter-active {
  transition: opacity .5s;
}
.fade-leave{
  opacity: 1;
}
.fade-leave-active{
  opacity: 0;
  transition: opacity .5s;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
