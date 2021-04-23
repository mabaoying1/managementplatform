<template>
    <div class="common_title2">
        <!-- 传入title 即可 -->
       
       <span class="com_title"> {{title}}</span>
       <span class="info" v-show="pageName" @click="toJump">{{pageName}} <i class="icon iconfont icon-xiayiji"></i></span>
    </div>
</template>

<script>
export default {
    name: 'common_title2',
    props:{
        title:{
            type: String,
            required: true,
            default:'required , type: String'
        },
        pageName:{
            type: String,
            required: false,
            default:''
        },
        pageUrl:{
            type: String,
            required: false,
            default:''
        }
    },
    data () {
        return {
            
        }
    },
    methods:{  
        toJump(){
            if(this.pageUrl != ''){
                this.jump(this.pageUrl)
            }else{
                console.error('没有设置pageUrl')
            }
            
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .common_title2{
        width: 100%;
        height: 0.5rem;
        background: $cF7F3F0;
        display:flex;
        justify-content: flex-start;
        align-items: center;
        box-sizing: border-box;
        padding: 0 0.15rem;
        .com_title{
            flex: 1;
            font-size: 0.16rem;
            color: $c6E6E6E;
            font-weight: 600;
            
        }
        .info{
            text-align: right;
            height: 0.2rem;
            width: auto;
            font-size: .1rem;
            color: $cffffff;
            background: $c8B5016;
            @include fcc;
            padding: 0 0.1rem 0 0.14rem;
            border-radius: 0.1rem;
            i{
                height: 100%;
                font-size: 0.1rem;
                line-height: 0.22rem;
                margin-left: 0.05rem;

            }
        }


    }
</style>
