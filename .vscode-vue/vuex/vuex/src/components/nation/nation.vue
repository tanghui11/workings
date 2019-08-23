<template>
    <ul class="nations">
        <li>首页</li>
        <li>新闻</li>
        <li>发现</li>
        <li>我的</li>
    </ul>
</template>

<script>
export default {

}
</script>

<style scoped>
ul,ol,li{
    list-style:none;
    margin: 0;
    padding: 0;
}
.nations{
    width: 100%;
    position: fixed;
    bottom: 0;
    left: 0;
}
.nations>li{
    float: left;
    width: 25%;
    background: #ddd;
    height: 40px;
    line-height: 40px;
    border-right: 1px solid #999;
    box-sizing: border-box;
}
.nations>li:last-child{
    border-right: none;
}
</style>

