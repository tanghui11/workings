import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import one from '@/components/one'
import two from '@/components/two'
import Hi from '@/components/Hi'
import Hi1 from '@/components/Hi1'
import Hi2 from '@/components/Hi2'
import params from '@/components/params'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      components: {
        default:HelloWorld,
        left:one,
        right:two,
      }
    },
    {
      path: '/th',
      name: 'HelloWorld',
      components: {
        default:HelloWorld,
        left:two,
        right:one,
      }
    },
    {
      path: '/hi',
      name: 'Hi',
      component: Hi,
      children:[
        {
          path:"/",
          name:"helloWrld/hi",
          component:Hi
        },
        {
          path:"hi1",
          name:"hi1",
          component:Hi1
        },
        {
          path:"hi2",
          name:"helloWrld/hi2",
          component:Hi2
        },
        {
          path:"/params/:newsId/:newstitle",
          name:"params",
          component:params
        }
      ]
    },
  ]
})
// name传参数