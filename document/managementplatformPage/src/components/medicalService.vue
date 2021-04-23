<template>
	<div class="rb" v-loading = "all_Loading" >
		<div class="top">
			<div class="t1">
				<span>综合日报</span>
				<span class="s2" @click="show_pop=true"><i class="icon iconfont icon-jiaoseqiehuan"></i></span>
			</div>
			<div class="t2">
				<div class="p1">我是机构A</div>
				<div class="p2">
					<span class="s1">¥</span><v-countup class="s2" :duration="'4'" :end-value="info.number1"></v-countup>
				</div>
				<div class="p3">
					<span>今日总收入</span>
				</div>
				<div class="p4">
					<span class="s1" v-for=" item in info.arr" :key='item'>{{item}}<i class="icon iconfont icon-xiayiji"></i>  </span>
				</div>
			</div>

		</div>
		<div class="middle">
			<div class="li">
				<img src="../assets/5.png" alt="">
				<p>门急诊量</p>
			</div>
			<div class="li">
				<img src="../assets/4.png" alt="">
				<p>病区床位</p>
			</div>
			<div class="li">
				<img src="../assets/7.png" alt="">
				<p>医生排班</p>
			</div>
			<div class="li">
				<img src="../assets/6.png" alt="">
				<p>手术安排</p>
			</div>
		</div>
		<commonTitle2 :title="'门诊信息'" :pageName="'详情'" :pageUrl="''"></commonTitle2>
		<div class="info_li">
			<div class="d1">
				<span class="s1">
					<span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="info.number2"></v-countup>
				</span>
				<span class="s2">
					今日门诊收入
				</span>
			</div>
			<div class="d2">
				<span class="s1">{{info.yaerOnYaer1 |leftTwoDecimal}} <img src="../assets/9.png" alt=""> </span>
				<span class="s1">{{info.monthOnmonth1 |leftTwoDecimal}} <img src="../assets/8.png" alt=""></span>
			</div>
			<div class="d3">
				<span>同比</span>
				<span>环比</span>
			</div>
		</div>
		<commonTitle2 :title="'住院信息'" :pageName="'详情'" :pageUrl="''"></commonTitle2>
		<div class="info_li">
			<div class="d1">
				<span class="s1">
					<span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="info.number2"></v-countup>
				</span>
				<span class="s2">
					今日门诊收入
				</span>
			</div>
			<div class="d2">
				<span class="s1">{{info.yaerOnYaer1 |leftTwoDecimal}} <img src="../assets/9.png" alt=""> </span>
				<span class="s1">{{info.monthOnmonth1 |leftTwoDecimal}} <img src="../assets/8.png" alt=""></span>
			</div>
			<div class="d3">
				<span>同比</span>
				<span>环比</span>
			</div>
		</div>
		<commonNavMeishan :page="0"></commonNavMeishan>
		<van-popup class="pop" position="right"  v-model="show_pop">
			<div class="pop_title">请选择机构</div>
			<div class="warp">
				<div class="pop_li" v-for="item in info.arr2" :key="item">{{item}}</div>
				
			</div>
			<div class="sub">
				<span @click="show_pop=false">取消</span>
			</div>
			
		</van-popup>
	</div>

	
</template>

<script>
import common_nva from "./common_nva.vue"
import common_title2 from "./common_title2.vue"
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment'; //格式化时间



export default {
	name: 'hospitalInfo_qy_yl',
	components:{
		"commonNavMeishan":common_nva,
		"commonTitle2":common_title2
	
	},
	data () {
		return {
			all_Loading:false,
			show_pop:false,
			info:{
				number1:999999999,
				number2:333333333,
				number3:333333333,
				arr:['昨日日报','本周收入报告'],
				arr2:['我是机构A','我是机构A2','我是机构A3','我是机构A','我是机构A5','我是机构A','我是机构A7','我是机构A','我是机构A','我是机构A','我是机构A',],
				yaerOnYaer1:0.33,
				monthOnmonth1:-0.55,
			}
		}
		
	},
	methods:{
	
	},
	//
	created(){
		
	},
	mounted(){
		

	} 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only  -->
<style lang="scss">
  	@import '../sass/Common';
	.rb{
		background: $cF7F3F0;
		min-height: 100%;
		margin-bottom: 0.49rem;
		.top{
			position:  absolute;
			height: 1.85rem;
			width: calc(100% - 0.4rem);
			background: $cAF8760;
			padding: 0 0.2rem;
			.t1{
				@include fcs;
				height: 0.44rem;
				color: $cffffff;
				font-size: 0.17rem;
				font-weight: 600;
				.s2{
					background: $cffffff;
					width: 0.26rem;
					height: 0.26rem;
					border-radius: 50%;
					@include fcc;
					i{
						font-size:0.2rem; 
						color: $cAF8760;
						
					}
				}
			}
			.t2{
				position: relative;
				box-shadow:0 0 .08rem #999999;
				height: 1.6rem;
				width: 3.45rem;
				padding: 0.17rem 0.22rem;
				box-sizing: border-box;
				background: $cffffff;
				left: 50%;
        		top: 50%;
				transform: translateX(-50%) translateY(-50%);
				border-radius: 0.1rem;
				@include fms;
				align-items: flex-start;
				.p1{
					font-size: 0.16rem;
					color: $c6E6E6E;
					font-weight: 600;
				}
				.p2{
					.s1{
						color: $cAE2C2E;
						font-size: 0.28rem;
						margin-right: 0.05rem;
					}
					.s2{
						color: $cAE2C2E;
						font-size: 0.38rem;
						font-weight: 600;
					}
				}
				.p3{
					height: 0.18rem;;
					font-size: 0.13rem;
					color: $c888888;
				}
				.p4{
					height: 0.2rem;
					@include fcc;

					.s1{
						height: 0.2rem;
						width: auto;
						background: $c8B5016;
						color: $cffffff;
						padding: 0.03rem 0.07rem;
						font-size: 0.1rem;
						@include fcc;
						border-radius: 0.15rem;
						margin-right: 0.1rem;
						i{
							height: 0.2rem;
							margin-left: 0.02rem;
							font-size: 0.04rem;
							// @include fcc;
							line-height: 0.225rem;
							
						}

					}
				}
				
			}
		}
		.middle{
			height: 1.5rem;
			// background: red;
			padding-top: 1.85rem;
			@include fcsa;
			.li{
				@include fmc;
				margin-top: 0.1rem;
				img{
					height: 0.48rem;
					width: 0.48rem;
					@include fcc;

				}
				p{
					margin-top: 0.08rem;
					@include fcc;
					color: $c6E6E6E;
					font-size: 0.14rem;
					font-weight: 600;
				}
			}

		}
		.info_li{
			// width: 100%;
			height: 1.21rem;
			// background: $cffffff;
			background-image: url(../assets/index_bg.png);
			background-size:cover;
        	background-repeat:no-repeat;
			margin:0 0.15rem;
			@include fms;
			.d1{
				width: 100%;
				height: 0.6rem;
				@include fcc;
				justify-content: flex-start;
				.s1{
					margin-left: 0.2rem;
					.s11{
						font-size: 0.14rem;
						color: $cAE2C2E;
						font-weight: 600;
					}
					.s12{
						color: $cAE2C2E;
						font-size: 0.24rem;
						font-weight: 600;
					}
				}
				.s2{
					font-size: 0.1rem;
					color: $c6D473A;
					width: auto;
					padding: 0 0.05rem;
					background: $cF7F3F0;
					height: 0.2rem;
					border-radius: 0.1rem;
					padding: 0 0.05rem;
					margin-left: 0.08rem;
					@include fcc;
				}
			}
			.d2{
				height: 0.2rem;;
				width: 100%;
				@include fcc;
				.s1{
					flex: 1;
					@include fcc;
					font-size: 0.16rem;
					color: $c6E6E6E

				}
				.s2{
					flex: 1;
					@include fcc;
					font-size: 0.16rem;
					color: $c6E6E6E;
				}
				img{
					margin-left: 0.05rem;
					height: 0.14rem;;
				}
			}
			.d3{
				flex:1;
				width: 100%;
				@include fcc;
				padding-bottom: 0.15rem;
				box-sizing: border-box;
				span{
					flex: 1;
					@include fcc;
					font-size: 0.13rem;
					color: $c9E9D9C;
				}

			}
		}
		.pop{
			height: 100%;
			box-sizing: border-box;
			// padding-top: 0.3rem;
			width: 3rem;
			background: $cF7F3F0;
			.pop_title{
				padding-top: 0.25rem;
				height: 0.45rem;
				@include fcc;
				font-size: 0.13rem;
				color: $c9E9D9C;
				border-bottom: 0.01rem solid $cababab;
				background: $cffffff;
			}
			.warp{
				height: calc(100% - 0.7rem - 1.3rem);
				overflow: auto;
				.pop_li{
					height: 0.57rem;
					@include fcc;
					border-bottom: 0.01rem solid $cababab;
					font-size: 0.17rem;
					font-weight: 600;
					background: $cffffff;
					color: $c6E6E6E;

				}
			}
			.sub{
				height: 1.28rem;
				@include fcc;
				span{
					height: 0.48rem;
					width: 2.6rem;
					@include fcc;
					background: $cEFE7DF;
					font-size: 0.18rem;
					color: $cAF8760;

				}

			}
			
		}
		
	}
	
  
  
  
</style>
