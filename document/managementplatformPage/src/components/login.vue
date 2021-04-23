<template>
  <div class="login_main" v-loading="loading">
    <!-- <i id="set" class="icon iconfont icon-shezhi" @click="toJump()"></i> -->
    <div class="top">
      <img src="../assets/l9.png" alt="">
      <span>四川省骨科医院</span>
       
    </div>
     <!-- <div class="top">
      <span>管理平台</span>
    </div> -->
    <div class="mil">
      <div class="name">
        <i class="icon iconfont icon-zhanghao"></i>
        <input type="text" placeholder="请输入用户名" v-model="loginNmae" >
      </div>
      <div class="password">
        <i class="icon iconfont icon-mima"></i>
        <input type="password" placeholder="请输入密码" v-model="loginPassword">
      </div>
      <div class="login">
        <button v-on:click="verifyIP()">登录</button>
      </div>
      <div class="register">
        <el-checkbox
          v-model="password_save"
          size="medium"
          >记住密码</el-checkbox>
          <span class="red_font" v-show="Message">{{msg}}</span>
        <!-- <div>立即注册</div> -->
        <!-- | -->
        <!-- <div>忘记密码</div> -->
      </div>
    </div>
    <div class="dowm"> 
      <img src="../assets/1.png" alt="">
      <p>Copyright © 2018 B-soft Corporation,All Rights Reserved</p>
    </div>
  </div>
</template>

<script>
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment' //格式化时间
export default {
  name: 'login',
  data () {
    return {
      
      loading: false,
      loginNmae:'',
      loginPassword:'',
      text:'',
      password_save:'',
      Message:false,
	  msg:'',
	  data:{},
	  user:{},
	  
    }
  },
  methods:{
    async register(){                
	  if(this.loginNmae==''){
		this.msg = '用户名不能为空!!!'
		this.Message = true
		return
	  }
	  if(this.loginPassword==''){
		this.msg = '密码不能为空!!!'
		this.Message = true
		return
	  }
      this.Message = false
      this.loading = true
      let data = {
		   "userName":this.loginNmae,
           "password":this.loginPassword
         }
      
      let res = await http.post(api.appLogin,data)
      // console.log(res)
      //this.$store.state.loginState = res.data.code//vex，保存登录状态
      // console.log(res.data)
      sessionStorage.setItem('loginState',res.data.code)//缓存登录状态 0表登录成功  1表示用户不存在  2表示用户禁用  3表示密码错误
      if(sessionStorage.getItem('loginState') == '0'){
		//localStorage.setItem("today",moment().format("YYYY-MM-DD"))
		  localStorage.setItem("today",moment().format('YYYY-MM-DD'))
		  //this.data = res.data
		  this.user = res.data.data.user
		  sessionStorage.setItem('jgid',this.user.jgid)
		  sessionStorage.setItem('jgname',this.user.jgmc)
		  	//console.log( this.user )
			// sessionStorage.setItem('LoginRoleid',res.data.body.tokens[0].userId)//登录人权限   0	系统管理员 1	市管理员2	区管理员3	院长
			// sessionStorage.setItem('jgid',res.data.body.tokens[0].manageUnitId)//市一级 机构ID
			// localStorage.setItem('jgid',res.data.body.tokens[0].manageUnitId);
			// sessionStorage.setItem('cover_jgid',res.data.body.tokens[0].manageUnitId)//市一级 机构ID
			// sessionStorage.setItem('jgid2',res.data.body.tokens[0].manageUnitId)//区一级 机构id
		sessionStorage.setItem('userName',this.user.userName)//用户名
		sessionStorage.setItem('token',res.data.data.token)//token
			// sessionStorage.setItem('LoginInfo',JSON.stringify(res.data.body.tokens[0].manageUnitId))//保存机构名称 机构 id
			// this.$store.state.jgName = res.data.body.tokens[0].manageUnitName
			// 保存密码
			if(this.password_save ==true){
				localStorage.setItem('password_save',this.password_save)
				localStorage.setItem('loginNmae',this.loginNmae)
				localStorage.setItem('loginPassword',this.loginPassword)
			}else{
				localStorage.removeItem('password_save')
				localStorage.removeItem('loginNmae')
				localStorage.removeItem('loginPassword')
			}
			this.loading = false
			//跳转页面
			this.goInPage()
      }else{
			this.Message = true
			this.msg = res.data.msg
			setTimeout(()=>{this.loading = false},500)
        
        
      }
    },
    goInPage(){
      this.$router.push('medicalService_rb')
    },
    getIdAndPassword(){
      if(localStorage.getItem('password_save')){
        this.password_save = true
        this.loginNmae = localStorage.getItem('loginNmae')
        this.loginPassword = localStorage.getItem('loginPassword')
      }
    },
    toJump(){
      this.jump('seting')
    },
    verifyIP(){
      //验证 设置ip
      //console.log(http)
     // let geturl =http.baseURL
     // let seturl ="http://${localStorage.getItem('setIP')}/ManagementPlatform/";
     // if( seturl == geturl){
        this.register()
     // }else{
     //  this.Message = true
     //   this.msg='请设置正确IP端口'
        // this.msg=geturl
     // }
      
    }
  },
  mounted(){
    this.getIdAndPassword()
    
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
@import '../sass/Common';

.login_main{
	#set{
		position: fixed;
		top:0.2rem;
		right: 0.15rem;
		color: $c6D473A;
		font-size: .25rem;
		
	}
	position: relative;
	display: flex;
	flex-direction:column;
	min-height: 100%;
	width: 3.75rem;
	font-size: 0.24rem;
	background: $cFFFEF9;
	.top{
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		// background: $c6D473A;
		width: 100%;
		height: 2.23rem;
		font-size: 0.25rem;
		font-family: 'PingFang-SC-Heavy';
		color: $c6D473A;
		display: flex;
		justify-content:center;
		align-items:center;
		font-weight: 600;
		img{
			width: 1.1rem;
			height: 1.1rem;
			margin-bottom: .15rem;
		}
	}
	.dowm{
		// height: calc(100% - 4.82rem );

		// background-image: url(../assets/3.png);
		background-repeat:no-repeat; 
		background-size:100% 100%;-moz-background-size:100% 100%;
		flex: 1;
		display: flex;
		justify-content: center;
		align-items:center;
		flex-direction: column;
		font-size: .14rem;
		color: #6A6B70;
		img{
			width: 1.15rem;
			height: .23rem;
			margin-bottom: .1rem;

		}
		font-size: 9px;
	
	}
	.mil{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		// position: absolute;
		// width: 3.42rem;
		height: 2.52rem;
		// top:50%;
		// left: 50%;
		// transform:translate(-50%,-50%);
		// background: #fff;
		div{
			// margin-left: .38rem;
			// margin-right: .38rem;
			height: .42rem;
			i{
				color: rgb(213, 213, 213);
				font-size: .20rem;
				margin: .14rem;
				color: $c6D473A;
			}
			input{
				width: 2.72rem;
				border: none;
				height: .24rem;
				font-size: .15rem;
				background: $cffffff ;
				
			}
		}
		.name{
			width: 3.22rem;
			display: flex;
			justify-content: center;
			align-items: center;
			margin-top: .38rem;
			box-sizing: border-box;
			border-bottom: .01rem solid $c6D473A;
			// border-radius: 0.05rem;
		}
		.password{
			width: 3.22rem;
			margin-top: .13rem;
			display: flex;
			justify-content: center;
			align-items: center;
			box-sizing: border-box;
			border-bottom: .01rem solid $c6D473A;
			// border-radius: 0.05rem;
		}
		.login{
			width: 3.2rem;
			margin-top: .3rem;
			height:.42rem ;
			
			button{
				width: 3.22rem;
				border: none;
				font-size: .16rem;
				height: .42rem;
				background: $c6D473A;
				color:#fff;
				font-family: 'PingFang-SC-Medium';
				border-radius:.07rem ;
			}
		}
		.register{
			width: 3.22rem;
			padding: 0 0.25rem;
			color: #d5d5d5;
			// display: flex;
			margin-top: .15rem;
			font-size: .13rem;
			div{
				color: red;
			}
			span{
				font-size: 0.14rem;
				color: $c6D473A;
			}
			.red_font{
				margin-left: 0.1rem;
			}
			.el-checkbox__input.is-checked .el-checkbox__inner, .el-checkbox__input.is-indeterminate .el-checkbox__inner{
				background-color: $c8B5016;
				border-color: $c8B5016;
			}
			.el-checkbox__inner:hover{
				border-color: $c8B5016;
			}
		}
		
	}
}
</style>
