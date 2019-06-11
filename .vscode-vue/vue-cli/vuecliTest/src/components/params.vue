<template>
  <div>
    <h2>{{msg}}--{{$route.params.username}}--{{$route.params.id}}</h2>
    <p>newsId:{{$route.params.newsId}}</p>
    <p>
      newstitle:{{$route.params.newstitle}}
    </p>
  </div>
</template>

<script>
export default {
  name: "params",
  data() {
    return {
      msg: "我是params页面"
    };
  },
  beforeRouteEnter:(to,form,next)=>{
    console.log("准备进入params路由模板");
    next();
  },
  beforeRouteLeave:(to,from,next)=>{
     console.log("准备离开params路由模板");
      next();
  }
};
</script>

<style>
</style>
