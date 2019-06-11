import Vue from 'vue';
import Vuex from 'vuex';
// vuex是个数据仓库，也是一个动态管理器
Vue.use(Vuex);
//状态对象
const state = {
    count:3
}
//改变我们的状态对象的方法
const mutations={
    add(state,n){
        state.count+=n;
    },
    reduce(state){
        state.count--;
    }
}

const getters = {
    count:state=>state.count+=10
    // count:function(state){
    //     return state.count+=10;
    // }
}
const actions = {
    addAction(context){
        context.commit('add',10),
        setTimeout(() => {
            context.commit('reduce')
        }, 3000);
        console.log("我比reduce先执行")
    },
    reduceAction({commit}){
        commit('reduce');
    }
}
const moduleA = {
    state,
    mutations,
    getters ,
    actions
}
export default new Vuex.Store({
    modules:{a:moduleA}
})