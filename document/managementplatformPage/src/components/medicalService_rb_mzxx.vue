<template>
	<div class="medicalService_rb_mzxx">
        <commonHead :title = "title"></commonHead>
		<div class="head">就诊概况</div> 
        <div class="gh">
            <div class="d1">
                <p class="p1">{{this.ptmzl}}</p>
                <p class="p2">门诊就诊人数</p>
            </div>
            <div class="d2">
                <p class="p1">{{this.tsmzl}}</p>
                <p class="p2">急诊就诊人数</p>
            </div>
        </div>
        <div class="head">辅检概况</div>
        <div class="main">
            <div id="chart"></div>
        </div>
        <div class="head">处方概况</div>
        <van-swipe class="van_swip" :loop="false" :width="160">
            <van-swipe-item>
                <div class="li">
                    <p class="p1">{{this.cfgk_info.JRCF_MZCFS_}}</p>
                    <p class="p2">处方数量</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                    <p class="p1">
                        <span class="s1">¥</span>
                        <span class="s2">{{this.cfgk_info.JRCF_CFMX_}}</span>
                    </p>
                    <p class="p2">处方总额</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                     <p class="p1">
                        <span class="s1">¥</span>
                        <span class="s2">{{this.cfgk_info.JRCF_CFMX_MAX_}}</span>
                    </p>
                    <p class="p2">最大处方</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                     <p class="p1">
                        <span class="s1">¥</span>
                        <span class="s2">{{this.cfgk_info.JRCF_CFMX_MIN_}}</span>
                    </p>
                    <p class="p2">最小处方</p>
                </div>
            </van-swipe-item>
        </van-swipe>
        <div class="head">医生概况</div>
        <van-swipe class="van_swip" :loop="false" :width="160">
            <van-swipe-item>
                <div class="li">
                    <p class="p1">{{ysgk_info.INDEX_JRCZL_AP_}}</p>
                    <p class="p2">出诊医生总数</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                    <p class="p1"> {{ysgk_info.INDEX_JRCZL_PJMZL_}} </p>
                    <p class="p2">平均门诊量</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                     <p class="p1">
                        <span class="s1">¥</span>
                        <span class="s2">{{cfgk_info.INDEX_JRCZL_PJCFJE_}}</span>
                    </p>
                    <p class="p2">平均处方金额</p>
                </div>
            </van-swipe-item>
            <van-swipe-item>
                <div class="li">
                     <p class="p1">
                        {{ysgk_info.INDEX_JRCZL_PJCFLF_}}
                    </p>
                    <p class="p2">平均处方量</p>
                </div>
            </van-swipe-item>
        </van-swipe>
	</div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment' //格式化时间
export default {
    name: 'medicalService_rb_mzxx',
    components:{
        "commonHead":common_head,
    },
	data () {
		return {
		    title:{
                'title':'门诊信息',
                "title_black":'medicalService_rb'
            },
            info:{
            },
            i:0,
            //普通门诊
            ptmzl:0,
            //急诊量
            tsmzl:0,
            //门急诊量
            mjzl_info:{},
            //处方概况
            cfgk_info:{},
            //医生概况
            ysgk_info:{},

            tmp_fjsr:0,
            tmp_fjrjsr:0,
            tmp_fj:[],
            tmp_fzjc:[],
            fzjc_info:[],
            fj_info:[]
        
		}
	},
	methods:{
        async init(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
           // let data =  [[[2,1000,1000,'中国'],[3,1000,1000,'法国'],[1,1000,1000,'德国']]]
           let data = this.fzjc_info
           // console.log(data)
            let option = {       
                title: {
                    text: '今日人数，今日金额(万元)，人均金额(元)',
                    textStyle:{
                        fontSize:12,
                        color:"#666"
                    },
                    left:15,
                },
                tooltip: {
                    // trigger: 'axis',
                    // axisPointer: {
                    //     type: 'cross'
                    // },
                    formatter:function(p){
                        //console.log(111,p.data[0])
                        let s = ''
                        // return '今日人数:'+p.data[0]+'<br/>'+'今日金额'+p[0].data[1]+'<br/>'+'人均金额'+p[0].data[2]+'<br/>';
                        // let s='';
                        s+='人数:'+p.data[0]+'<br>';
                        s+='总额:'+p.data[1]+'万元<br>';
                        s+='人均:'+p.data[2]+'元<br>';
                        s+='项目:'+p.data[3]+'<br>';
                        return s
                    },
                    backgroundColor:'#fff',
                    borderColor:'#AF8760',
                    borderWidth:2,
                    textStyle:{
                        color:"#6E6E6E",
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    type: 'category',
                    axisLine: {
                        lineStyle: {
                            color: '#9E9D9C', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        color:'#9E9D9C',
                        fontSize:14,
                        fontWeight:'bold',
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        color:'#9E9D9C',
                        fontSize:14,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }                    
                },
                series: [{
                    // name: '1990',
                    data: data[0],
                    type: 'scatter',
                    symbolSize: function (data) {
                        return Math.sqrt(data[2]);
                    },
                    label: {
                        show: true,
                        color:'#6E6E6E',
                        formatter: function(param) {
                            return param.value[3];
                        },                                       
                    },
                    itemStyle: {
                        normal: {
                            // shadowBlur: 10,
                            shadowColor: '#AF8760',
                            // shadowOffsetY: 5,
                            color: new this.$echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                                offset: 0,
                                color: '#F7F3F0'
                            }, {
                                offset: 1,
                                color: '#AF8760'
                            }])
                        }
                    }
                }]
            };            
            chart1.setOption(option,true);
        },
        //辅助检查
        async fun_fjgk_info(){
			let data = {
                "themeID":"index_mz_fzjc",
				//"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
				//  "startdate":"2019-01-01",
                //  "enddate":"2019-04-01",		
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	                		
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.fj_info =  res.data
                for(this.i = 0;this.i < this.fj_info.length;this.i++){
                    this.tmp_fjsr = Number((Number(this.fj_info[this.i].FJSR_)/10000).toFixed(2))
                    this.tmp_fjrjsr = Number(this.fj_info[this.i].FJRC_)>0?(Number(this.fj_info[this.i].FJSR_)/Number(this.fj_info[this.i].FJRC_)).toFixed(2):0
                    this.tmp_fj = [Number(this.fj_info[this.i].FJRC_),this.tmp_fjsr,this.tmp_fjrjsr,this.fj_info[this.i].TYPE_Text]
                    //this.tmp_fj = [500000,500000,500000,this.fj_info[this.i].TYPE_Text]

                    this.tmp_fzjc.push(this.tmp_fj )     
                }
                this.tmp_fzjc.sort(function(a,b){
                    return a[0] - b[0];
                });                
                //console.log( this.tmp_fzjc)
                this.fzjc_info.push(this.tmp_fzjc )                
                this.init()
            }
        } ,
        //门急诊数
        async fun_mjzl_info(){
			let data = {
                "themeID":"index_mjzl",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
				// "startdate":"2018-12-01",
                // "enddate":"2018-12-01",	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	              			
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.mjzl_info =  res.data[0]
               /* for(this.i = 0;this.i < this.mjzl_info.length;this.i++){
                    if(this.mjzl_info[this.i].TYPE =="1"){
                        this.ptmzl = Number(this.mjzl_info[this.i].INDEX_MJZL_)
                    
                    }else{
                        this.tsmzl = Number(this.mjzl_info[this.i].INDEX_MJZL_)
                    }
                }*/
                 this.ptmzl = Number(this.mjzl_info.MZGHRC_)
                 this.tsmzl = Number(this.mjzl_info.JZGHRC_)
            }
             this.fun_cfgk_info()
        
        } ,
        //处方概况
        async fun_cfgk_info(){
			let data = {
                "themeID":"index_jrcf",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
				// "startdate":"2018-12-01",
                // "enddate":"2018-12-01",	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	            			
        	 }
     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.cfgk_info =res.data[0]
                this.cfgk_info.INDEX_JRCZL_PJCFJE_=(this.cfgk_info.JRCF_CFMX_/this.cfgk_info.JRCF_MZCFS_).toFixed(2);
            }
             this.fun_ysgk_info()
            
        } ,
        //医生概况
        async fun_ysgk_info(){
			let data = {
                "themeID":"index_jrczl",
			//	"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
				// "startdate":"2018-12-01",
                // "enddate":"2018-12-01",	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	              			
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ysgk_info =res.data[0]
                this.ysgk_info.INDEX_JRCZL_PJMZL_=Number((this.ptmzl+this.tsmzl)/this.ysgk_info.INDEX_JRCZL_AP_).toFixed(0);
                this.ysgk_info.INDEX_JRCZL_PJCFLF_=Number(this.cfgk_info.JRCF_MZCFS_/this.ysgk_info.INDEX_JRCZL_AP_).toFixed(0);
              // this.ysgk_info.INDEX_JRCZL_PJMZL_=((this.ptmzl+this.tsmzl)/this.ysgk_info.INDEX_JRCZL_AP_).toFixed(2);
               //this.ysgk_info.INDEX_JRCZL_PJCFLF_=(this.cfgk_info.JRCF_MZCFS_/this.ysgk_info.INDEX_JRCZL_AP_).toFixed(2);
              
            }
		}        
	},
	mounted(){
        this.fun_mjzl_info()
        this.fun_fjgk_info()
	}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
	@import '../sass/Common';
    .medicalService_rb_mzxx{
        background: $cF7F3F0;
        height: 100%;;
        overflow: auto;
        .head{
            height: 0.4rem;
            padding: 0 0.2rem;
            color: $c6E6E6E;
            font-size: 0.17rem;
            font-weight: 600;
            @include fcc;
            justify-content: flex-start;
            background: $cffffff;
        }
        .gh{
            @include fcs;
            height: 1.1rem;
            padding: 0.1rem 0.08rem;
            box-sizing: border-box;
            background: $cffffff;
            margin-bottom: 0.1rem;
            .d1{
                height: 100%;
                width: 1.76rem;
                background-color: $cAF8760;
                background-image: url(../assets/bg2.png);
                background-size:97%;
                
                background-repeat:no-repeat;
                background-position:center center;
                @include fms;
                .p1{
                    flex: 1;
                    width: 100%;
                    @include fcc;
                    align-items: flex-end;
                    color: $cffffff;
                    font-size: 0.24rem;
            
                }
                .p2{
                    flex: 1;
                    width: 100%;
                    @include fcc;
                    align-items: flex-start;
                    color: $cD7C3AC;
                    font-size: 0.13rem;

                }

            }
            .d2{
                height: 100%;
                width: 1.76rem;
                background: $cBB993F;
                background-image: url(../assets/bg2.png);
                background-size:97%;
                background-repeat:no-repeat;
                background-position:center center;
                @include fms;

                .p1{
                    flex: 1;
                    width: 100%;
                    @include fcc;
                    align-items: flex-end;
                    color: $cffffff;
                    font-size: 0.24rem;
            
                }
                .p2{
                    flex: 1;
                    width: 100%;
                    @include fcc;
                    align-items: flex-start;
                    color: $cD7C3AC;
                    font-size: 0.13rem;

                }
            }
        }
        .main{
            margin-bottom: 0.1rem;
           #chart{
               height: 2.75rem;
               background: $cffffff;
           }
        }
        .van_swip{
            background: $cffffff;
            height: 0.8rem;
            padding-bottom: 0.3rem;
            margin-bottom: 0.1rem;
            .li{
                height: 100%;
                background-image: url(../assets/zhyb_bg.png);
                background-size:100% 100%;
                background-repeat:no-repeat;
                margin-left:0.1rem;
                @include fmc;
                .p1{
                    font-size: 0.24rem;
                    color: $c6E6E6E;
                    @include fcc;

                    .s1{
                        font-size: 0.14rem;
                        color: $cAE2C2E;
                    }
                    .s2{
                        font-size: 0.24rem;
                        color: $cAE2C2E;
                    }
                }
                .p2{
                    font-size: 0.13rem;
                    color: $c9E9D9C;
                    


                }
              
            }
            .van-swipe-item:last-child{
                .li{
                    margin-right: 0.1rem;
                }
            }
            
        }
    }
</style>
