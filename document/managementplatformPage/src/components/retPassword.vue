<template>
    <div class="retPassword">
        <commonHead :title = "title"></commonHead>
        <div class="main">
            <div class="li" @click="cao()">
                <span>原密码</span>
                <input type="Password" placeholder="请输入原始密码" v-model="oldPassword">
                <p class="red_font" v-show="show_msg1">密码输入错误</p>
            </div>
            <div class="li">
                <span>新密码</span>
                <input type="Password" placeholder="请输入新密码" v-model="password1">
                <input type="Password" placeholder="请输入再次输入新密码" v-model="password">
                <p v-show="show_msg" class="red_font">请出入正确的密码格式</p>
            </div>
            
        </div>
        <div class="tfoot">
            <div class="signOut" @click="retPassword()">
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
    name: 'retPassword',
    name: 'retPassword',
    
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':'密码重置',
                "title_black":'myZone'
            },
            userId:'',
            oldPassword:'',
            password:'',
            password1:'',
            show_msg:false,
            show_msg1:false



        }
    },
    methods:{
        cao(){
        },
        async retPassword(){
            if(this.oldPassword != localStorage.getItem('loginPassword') ){
                this.oldPassword = ''
                this.show_msg1 = true
                return
            }
            
            if(this.password1 != this.password){
                this.password1 =''
                this.password = ''
                this.show_msg = true
                return
            }

            let data = {
                userId:localStorage.getItem('loginNmae'),
                oldPassword:this.oldPassword,
                password:this.password
            }
            let res = await http.post(api.updatePasswordMS,data)
           
            if(res.data.return_code == "SUCCESS"){
                localStorage.setItem('loginNmae','')
                localStorage.setItem('loginPassword','')
                this.jump('login')
            }
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  	@import '../sass/Common';
    .retPassword{
        height: 100%;
        width: 100%;
        background: $cF7F3F0;
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
                background: $cF7F3F0;
                font-size: 0.15rem;
                color: #8A8A8A;
                // margin-bottom: .03rem;
                input{
                    height: 0.4rem;
                    width:calc(100% - 0.2rem); 
                    border: 0.01rem solid #ddd;
                    color: $cD7C3AC;
                    padding-left: .2rem;
                    margin-bottom: 0.15rem;
                }
                input::-webkit-input-placeholder{
                    color: $cD7C3AC;
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
                color: $cffffff;
                border-radius: .03rem;
            }
        }
    }
</style>
