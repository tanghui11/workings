import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import one from '@/components/one'
import two from '@/components/two'
import Hi from '@/components/Hi'
import Hi1 from '@/components/Hi1'
import Hi2 from '@/components/Hi2'
import params from '@/components/params'
import shiyan from '@/components/shiyan'
import Error from '@/components/Error'

Vue.use(Router)

export default new Router({
  //mode:"histoy",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      components: {
        default:HelloWorld,
        left:one,
        right:two,
      },
      alias:"/homes"//别名的使用
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
      ]
    },
    {
      path:"/params/:newsId/:newstitle",
      name:"params",
      component:params
     /*  beforeEnter:(to,from,next)=>{
        console.log(to)
        console.log(from)
        console.log(next)
      //  next();
        next({
          path:"/"
        })
      } */
    },  
    {
      path:"/goHome",
      redirect:"/"//重定向
    },
    {
      path:"/goParams/:newsId/:newstitle",
      redirect:"/params/:newsId/:newstitle"///重定向
    },
    {
      path:"/shiyan",
      name:"shiyan",
      component:shiyan,
      alias:"/jsth"//别名的使用
    },
    {
      path:"*",
      component:Error
    }
  ]
})
// name传参数