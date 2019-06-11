import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Count from '@/components/Count'

Vue.use(Router)

export default new Router({
  mode:"history",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:"/count",
      name:"count",
      component:Count
    }
  ]
})
//请求数据方法
/* axios.get(url).then(function(respone){
  console.log(respone)
}).catch(function(error){
    console.log(error)
}) */
