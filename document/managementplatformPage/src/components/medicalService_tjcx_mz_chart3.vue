<template>
    <div class="medicalService_tjcx_mz_chart3">
        <commonHead :title = "title"></commonHead>
        <div class="tab2">
            住院收入
        </div>
        <div class="main">
            <div id="chart"></div>
        </div>
       
        <div class="bootnm">
            <div class="d1">
                <span class="s1">
                    <span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="info.fy"></v-countup>
                </span>
                <span class="s2">
                   {{info.fylb}}
                </span>
            </div>
            <div class="d2">
                <span class="s1">{{info.tb |leftTwoDecimal}} <img v-if="info.tb<0" src="../assets/8.png" alt=""><img v-if="info.tb>0" src="../assets/9.png" alt=""> </span>
                <span class="s1">{{info.hb |leftTwoDecimal}} <img v-if="info.hb<0" src="../assets/8.png" alt=""><img v-if="info.hb>0" src="../assets/9.png" alt=""></span>
            </div>
            <div class="d3">
                <span>同比</span>
                <span>环比</span>
            </div>
        </div>

       
        
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'medicalServiceBqxx',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            tab_pick:false,
            title:{
                'title':sessionStorage.getItem('startTime')+'-'+sessionStorage.getItem('endTime'),
                "title_black":'medicalService_tjcx_mz'
            }, 
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),              
            info:{
                fylb:'',                
                fy:0,
                tb:0,
                hb:0                
            },
            i:0,
            tmp_mz:{},
            tmp_zy:{},
            list_mz:[],
            list_zy:[],
            list_info:[],
            name:"住院收入",
        }
    },
    methods:{
        // 图表 
        async initChart(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {               
                legend: {
                    orient: 'vertical',
                    x: 'right',
                    y: 'top',
                },
                series: [{
                    name:this.name,
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center',
                            formatter: '{b}:{c}万元\n{per|{d}%}',
                            backgroundColor: '#eee',
                            borderColor: '#aaa',
                            borderWidth: 0,
                            borderRadius: 4,
                            
                            rich: {
                                a: {
                                    color: '#999',
                                    lineHeight: 11,
                                    align: 'center'
                                },
                                abg: {
                                    backgroundColor: '#333',
                                    width: '100%',
                                    align: 'right',
                                    height: 22,
                                    borderRadius: [4, 4, 0, 0]
                                },
                                // hr: {
                                //     borderColor: '#aaa',
                                //     width: '100%',
                                //     borderWidth: 0.5,
                                //     height: 0
                                // },
                                b: {
                                    fontSize: 11,
                                    lineHeight: 22
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '11',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:this.list_info
                }]
            }
            
            chart1.setOption(option,true);
            chart1.on('click', function (param) {
                that.fyxq(param.data.FYLB,param.data.name)
            })
        },
		async fyxq(param,name){
            console.log(param);
			let data = {
                    "themeID":"yw_zfy_zb",
 				"startdate":this.startTime_show,
                "enddate":this.endTime_show,                    
                    // "startdate":"2019-01-01",
                    // "enddate":"2019-01-11",

                    "dim_key":"fylb",
                    "value":param,				
        	 }
     
			let res = await http.get(api.getBIDataAll,data)
            this.list =res.data[0] 
           
            this.info.fylb = name
            this.info.fy= Number(this.list.ZYFY_)
            this.info.hb = Number(this.list.ZYFY_MOMGROWTH)/Number(this.list.ZYFY_MOM)
            this.info.tb = Number(this.list.ZYFY_YOYGROWTH)/Number(this.list.ZYFY_YOY)  
        },        
		async init(){
			let data = {
                "themeID":"yw_flfy_tj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,                 
				// "startdate":"2019-01-01",
				// "enddate":"2019-01-11"				
        	 }
     
			let res = await http.get(api.getBIDataAll,data)
			this.list =res.data
            for(this.i = 0;this.i < this.list.length;this.i++){
                this.tmp_mz = {value:(Number(this.list[this.i].MZ_FLFY_)/10000).toFixed(2),name:this.list[this.i].FYLB_Text,FYLB:this.list[this.i].FYLB}
                this.list_mz.push(this.tmp_mz )
                this.tmp_zy = {value:(Number(this.list[this.i].ZY_FLFY_)/10000).toFixed(2),name:this.list[this.i].FYLB_Text,FYLB:this.list[this.i].FYLB}
                this.list_zy.push(this.tmp_zy )                         
            }
            this.list_info = this.list_zy
            this.initChart()		   
        },
        


    },
    mounted(){
        this.init()
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_chart3{
        height:calc(100% );
        font-size: .14rem;
        display: flex;
        flex-direction: column;
        .tab2{
            height: 0.3rem;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            color: #8B5016;
            font-size: 0.2rem
        }
        .tab{
            padding: 0.24rem 0.16rem;
            height: 0.3rem;;
            @include fcc;
            color: $c8B5016;
            .d1{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $c8B5016;
                @include fcc;
                border-radius: 0.03rem 0 0 0.03rem;
            }
            .d2{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $c8B5016;
                @include fcc;
                // border-radius:0 0.03rem 0.03rem 0;
            }
            .d3{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $c8B5016;
                @include fcc;
                border-radius:0 0.03rem 0.03rem 0;
            }
        }
        .bingo{
            background: $c8B5016;
            color: $cffffff;
        }
       
        
        .main{
            flex: 1;
            #chart{
                height: 100%;
            }
            
        }
        
        .bootnm{
			// width: 100%;
            height: 1.45rem;

			// background: $cffffff;
			background-image: url(../assets/index_bg.png);
			// background-size:cover;
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
    }

</style>
