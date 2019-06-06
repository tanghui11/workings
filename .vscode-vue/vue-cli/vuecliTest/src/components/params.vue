<template>
    <div>
        <h2>{{msg}}--{{$route.params.username}}--{{$route.params.id}}</h2>
        <p>newsId:{{$route.params.newsId}}</p>
        <p>newstitle
            :{{$route.params.newstitle}}</p>
    </div>
</template>

<script>
export default {
  name: 'params',
  data(){
      return {
          msg:"我是params页面"
      }
  }
}
</script>

<style>
</style>
