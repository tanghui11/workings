<template>
    <div>
     <h2>{{msg}}</h2>
    <router-view/>            
    </div>
</template>

<script>
export default {
  name: 'hi',
  data(){
      return {
          msg:"我是Hi页面"
      }
  }
}
</script>

<style>
</style>
