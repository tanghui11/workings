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
export default new Vuex.Store({
    state,
    mutations
    
})