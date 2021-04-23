<template>
    <div class="about">
        <commonHead :title = "title"></commonHead>
        <div class="main">
            <img src="../assets/l9.png" alt="">
            <span>四川省骨科医院综合监管</span>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'about',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':'关于',
                "title_black":'myZone'
            },
           



        }
    },
    methods:{
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  	@import '../sass/Common';
    .about{
        height: 100%;
        width: 100%;
        background: $cF7F3F0;
        display: flex;
        flex-direction: column;
        .main{
            flex: 1;
            @include fcc;
            flex-direction: column;
            padding-bottom: 50%;
            box-sizing: border-box;
            span{
                margin-top: 0.4rem;
                width: 1.68rem;
                font-size: 0.23rem;
                text-align: center;
                color: $c6D473A;
                font-weight: 600;
            }
        }

        
    }
</style>
