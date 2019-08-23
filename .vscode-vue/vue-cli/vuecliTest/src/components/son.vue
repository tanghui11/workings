<template>
    <div class="son">
      <p>{{ sonMsg }}</p>
      <p>子组件接收到内容：{{ psMsg }}</p>
    </div>
</template>

<script>
export default {
    name: "son",
    data(){
      return {
        sonMsg:'子组件',
      }
    },
    props:['psMsg'],//接手psMsg值
}
</script>

<style>

</style>
