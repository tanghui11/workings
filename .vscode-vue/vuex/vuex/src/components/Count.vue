<template>
    <div>
        <h2>{{msg}}</h2><hr/>
        <p>{{$store.state.count}}</p>
        <h3>{{count}}</h3>
        <p>
            <button @click="$store.commit('add',10)">+</button>
            <button @click="add(10)">++</button>
            <button @click="$store.commit('reduce')">-</button>
            <button @click="reduce">--</button>
        </p>
        <p>
             <button @click="addAction">+</button>
              <button @click="reduceAction">--</button>
        </p>
    </div>
</template>
<script>
import store from "@/vuex/store";
import {mapState,mapMutations,mapGetters,mapActions} from 'vuex';
export default {
    data(){
        return {
            msg:"hello Vuex",
        }
    },
    // computed:mapState(['count']),
    // computed:mapState({
    //     count:state=>state.count
    // }),
    // computed:{
    //     count(){
    //         return this.$store.state.count;
    //     }
    // },
    computed:{
        ...mapState(['count']),
//...mapGetters(['count']),
        // count(){
        //     return this.$store.getters.count;
        // }
    },
    methods:{
        ...mapMutations(['add','reduce']),
        ...mapActions(['addAction','reduceAction'])
    },
    store
}
</script>
