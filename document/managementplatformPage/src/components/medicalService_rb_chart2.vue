<template>
    <div class="medicalService_rb_chart2">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick?'bingo':''" @click="tab(true)">门诊</div>
            <div class="d2" :class="!tab_pick?'bingo':''" @click="tab(false)">急诊</div>
        </div>
         <div class="charts">
            <div id="chart" ></div>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment' //格式化时间
export default {
    name: 'medicalService_rb_chart2',
    components:{
            "commonHead":common_head,
        },
    data () {
        return {
            title:{
                'title':`今日急门诊`,
                "title_black":'medicalService_rb'
            },
            i:0,
            tmp_rc:[],
            tmp_mzrc:[],
            mzrc_info:[],
            tab_pick:true,
            list:[],
            sjd_info:[],
            rc_info:[],
            ts:'门诊数'
        }
    },
    methods:{
        tab(t){
            this.tab_pick = t
            t?this.initPt():this.initTs() 
        },
        // 图表 门诊
        async init1(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                legend: {
                   color:'#000000'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    },
                    formatter: '{b0}:00'+this.ts+':{c0}'
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
                    data: that.sjd_info
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
                series: [
                    {
                        data: that.rc_info,
                        type: 'line',
                        areaStyle: {},
                        itemStyle:{
                            color:new that.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{  
                                offset: 0,  
                                color: '#AF8760'  
                            }, {  
                                offset: 1,  
                                color: '#F3EDE7'  
                            }]),//线条渐变色 
                        }
                    }
                ]
            };
            chart1.setOption(option,true);

        },
        //图表 月报
        async init2(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                color: ['#AF8760'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    },

                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        axisTick: {
                            alignWithLabel: true
                        },
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
                    
                ],
                yAxis : [
                    {
                        type : 'value',
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
                    }
                ],
                series : [
                    {
                        data: [820, 932, 901, 934, 1290, 1330, 1320],
                        type: 'line',
                        areaStyle: {},
                        itemStyle:{
                            color:new that.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{  
                                offset: 0,  
                                color: '#AF8760'  
                            }, {  
                                offset: 1,  
                                color: '#F3EDE7'  
                            }]),//线条渐变色 
                        }
                    }
                ]
            }
            chart1.setOption(option,true);
        },
        //普通门诊量
        async initPt(){
            this.tmp_mzrc = []
            this.sjd_info = []
            this.rc_info = []
			let data = {
                "themeID":"index_mjzl_sd",
			//	"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
                // "startdate":"2018-08-01",
                // "enddate":"2018-08-01",	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")              
                // "dim_key":"type",
                // "value":"0",			
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.list =res.data;
                console.log(res.data);
                for(this.i = 0;this.i < this.list.length;this.i++){
                    this.tmp_rc = [Number(this.list[this.i].DATETIME)+1,Number(this.list[this.i].INDEX_MJZL_)]
                    this.tmp_mzrc.push(this.tmp_rc )     
                }
                this.tmp_mzrc.sort(function(a,b){
                    return a[0] - b[0];
                });
                
                //console.log( this.tmp_mzrc)
                for(this.i = 0;this.i < this.tmp_mzrc.length;this.i++){
                    //this.sjd_info = [Number(this.tmp_mzrc[this.i].DATETIME)+1,Number(this.tmp_mzrc[this.i].INDEX_MJZL_)]
                    this.sjd_info.push(this.tmp_mzrc[this.i][0])  
                    this.rc_info.push(this.tmp_mzrc[this.i][1])     
                }
                //console.log(  this.sjd_info)     
                //console.log(  this.rc_info)    
                this.ts ='门诊数'   
                this.init1()  
            }

        },
        //特殊门诊量
        async initTs(){
            this.tmp_mzrc = []
            this.sjd_info = []
            this.rc_info = []
			let data = {
                "themeID":"index_jzl_sd",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
                // "startdate":"2018-08-01",
                // "enddate":"2018-08-01",	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")               
                // "dim_key":"type",
                // "value":"1",	                			
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.list =res.data
                for(this.i = 0;this.i < this.list.length;this.i++){
                    this.tmp_rc = [Number(this.list[this.i].DATETIME),Number(this.list[this.i].INDEX_JZL_)]
                    this.tmp_mzrc.push(this.tmp_rc )     
                }
                this.tmp_mzrc.sort(function(a,b){
                    return a[0] - b[0];
                });
                
                // console.log( this.tmp_mzrc)
                for(this.i = 0;this.i < this.tmp_mzrc.length;this.i++){
                    //this.sjd_info = [Number(this.tmp_mzrc[this.i].DATETIME),Number(this.tmp_mzrc[this.i].INDEX_MJZL_)]
                    this.sjd_info.push(this.tmp_mzrc[this.i][0])  
                    this.rc_info.push(this.tmp_mzrc[this.i][1])     
                }
            }
            this.ts ='急诊数'   
            this.init1()  
        },             
    },
    mounted(){
        this.initPt()        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_rb_chart2{
        height: 100%;
        display: flex;
        flex-direction: column;
        .tab{
            padding: 0.24rem 0.7rem;
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
                border-radius:0 0.03rem 0.03rem 0;
                
            }
        }
        .bingo{
            background: $c8B5016;
            color: $cffffff;
        }
        .charts{
            flex: 1;
            #chart{
                height: 100%;
            }
        }
    }
    
</style>
