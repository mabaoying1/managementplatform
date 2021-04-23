<template>
    <div class="myZone">
        <commonHead :title = "title"></commonHead>
        <div class="top">
            {{userName}}
        </div>
        <div class="main">
            <!-- <div class="li">
                <span>登录用户：{{userName}}</span>
            </div> -->
            <div class="li" @click="retPassword()">
                <img src="../assets/l7.png" alt="">
                <span>密码重置</span>
                <i class="icon iconfont icon-xiayiji" ></i>
            </div>
            <div class="li" @click="jump('about')">
                <img src="../assets/l6.png" alt="">

                <span>关于</span>
                <i class="icon iconfont icon-xiayiji"></i>
            </div>
           
            <div class="li">
                <img src="../assets/l5.png" alt="">

                <span>版本号</span>
                <i>2.1</i>
            </div>
        </div>
        <div class="tfoot">
            <div class="signOut" @click="show=!show">
                退出登录
            </div>
        </div>
        <commonNav :page="4"></commonNav>
        <!-- 退出 -->
        <van-popup v-model="show" position="bottom">
            <div class="van_main">
                <div class="b1" @click="logout()">退 出</div>
                <div class="b1" @click="show=!show">取 消</div>
            </div>
        </van-popup>
    </div>
    
</template>

<script>
import common_head from "./common_head.vue";
import common_nva from "./common_nva.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'myZone',
    components:{
        "commonNav":common_nva,
        "commonHead":common_head,
    },
    data () {
        return {
            msg: 'Welcome to Your myZone',
            title:{
                'title':'个人中心',
               //"title_black":'hospitalInfo_gy_yl'
            },
            userName:sessionStorage.getItem("userName"),
            show:false,

        }
    },
    methods:{
        retPassword(){
            this.jump('retPassword')
        },
        logout(){
           //sessionStorage.setItem('loginState','9')
            this.jump('login')
            //this.show=false
        }
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  	@import '../sass/Common';
    .myZone{
        height: 100%;
        width: 100%;
        background: $cF7F3F0;
        .common_head{
            background: $cAF8760;
            border: none;
            color: $cffffff;
            i{
                display: none;
            }
            span{
                text-align: left;
            }
        }
        .top{
            height: 1.4rem;
            background: $cAF8760;
            padding-bottom: 0.4rem;
            padding-left: 0.2rem;
            box-sizing: border-box;
            font-size: 0.28rem;
            color: $cffffff;
            display: flex;
            align-items: flex-end;
        }
        .main{
            height: auto;
            .li{
                display: flex;
                padding: 0 0.3rem;
                justify-content: space-between;
                align-items: center;
                height: 0.6rem;
                background: #fff;
                font-size: 0.16rem;
                color: #555;
                margin-bottom: .03rem;
                img{
                    height: 0.3rem;
                    width: 0.3rem;
                    margin-right: 0.2rem;
                }
                i{
                    flex: 1;
                    text-align: right;
                }
            }
            .li:nth-child(1){
                margin-bottom: .1rem;
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
                background: $cAF8760;;
                color: $cffffff;;
                border-radius: .03rem;
            }
        }
        .van-popup{
            background-color: transparent;
        }
        .van_main{
            // display: flex;
            // flex-direction: column;
            // justify-content: space-between;
            // align-items: center;
            padding: 0.4rem 0.27rem;
            // height: 1.2rem;
            width: 3.75rem;
            // background: red;s
            box-sizing: border-box;
            .b1{
                margin-top: 0.2rem;
                width: 100%;
                height: 0.5rem;
                background: $cEFE7DF;
                @include fcc;
                color: $cAF8760;
                font-size: 0.18rem;
            }

        }
    }
</style>
