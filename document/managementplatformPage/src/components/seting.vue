<template>
    <div class="seting">
        <commonHead :title = "title"></commonHead>
        <div class="main">
            <div class="li">
                <span>IP端口</span>
                <el-input
                type="text"
                placeholder="请输入IP端口"
                v-model="setIP"
                clearable>
                </el-input>
            </div>
            <!-- <div class="li">
                <span>新密码</span>
                <input type="Password" placeholder="请输入新密码">
                <input type="Password" placeholder="请输入再次输入新密码">
            </div> -->
            
        </div>
        <div class="tfoot">
            <div class="signOut" @click="set()">
                保存
            </div>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'seting',

    
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            msg: 'Welcome to Your seting',
            title:{
                'title':'高级设置',
                "title_black":'login'
            },
            setIP:localStorage.getItem('setIP')? localStorage.getItem('setIP'): '',

        }
    },
    methods:{
        set(){
            localStorage.setItem('setIP',this.setIP)
            this.jump('login')
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
@import '../sass/Common';

    .seting{
        height: 100%;
        width: 100%;
        background: $cFFFEF9;
        .main{
            height: auto;
            margin: .3rem 0;
            .li{
                display: flex;
                padding: 0 0.3rem;
                justify-content: center;
                align-items: flex-start;
                flex-direction: column;
                height: auto;
                // background: #fff;
                font-size: 0.15rem;
                color: #8A8A8A;
                // margin-bottom: .03rem;
                input{
                    height: 0.4rem;
                    // width:calc(100% - 0.2rem); 
                    border: 0.01rem solid #ddd;
                    color: $cAF8760;
                    padding-left: .2rem;
                    // margin-top: .05rem;
                    // margin-bottom: 0.15rem;
                }
                input::-webkit-input-placeholder{
                    color: $cAF8760;
                }
            }


        }
        .tfoot{
            margin-top: .6rem;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            .signOut{
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: .17rem;
                width: 3.27rem;
                height: 0.4rem;
                background: $c8B5016;
                color: #fff;
                border-radius: .03rem;
            }
        }
    }
</style>
