<template>
    <div class="medicalService_rb_chart">
        <commonHead :title = "title"></commonHead>
         <div class="charts">
            <div id="chart" ></div>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment'; //格式化时间
export default {
    name: 'medicalService_rb_chart',
    components:{
            "commonHead":common_head,
        },
    data () {
        return {
            title:{
                'title':'昨日收入图表',
                "title_black":'medicalService_rb'
            },
            tab_pick:true,
            tmp_sr:[],
            tmp_srpx:[],
            sr_info:[],
            list:[],
            sjd_info:[],
            jrsr_info:[],
            zrsr_info:[],
            sr_info:[],
            tmp_rq:[],
            tmp_zb:[],
            zbsr_info:[],
            zb_rq:[]
        }
    },
    methods:{
        // 图表 日报
        async init1(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                //  legend: {
                //      data:['今日收入','昨日收入']
                //  },
                //  tooltip: {
                //      trigger: 'axis',
                //      formatter: '{b0}:00收入:{c0}'
                //  },
                 tooltip: {
                    trigger: 'axis'
                },
               grid: {
                    left: '2%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    type: 'category',
                  //  boundaryGap: false,
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
                     data: this.sjd_info
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
                  }
                  ,
                  splitLine:{
                     lineStyle:{
                        type:'dashed',
                          color: '#C8CAD2'
                    }
                  }
                    
                },
                series: [
                     {
                         data: this.sr_info,
                         type: 'bar',
                         barWidth : 20
                     }
                   // {
                     //    name:"今日收入",
                     //    data: this.sr_info,
                     //    type: 'line'
                    // }
                     //,
                    //  {
                    //      name:"昨日收入",
                    //      data: this.zrsr_info,
                    //     type: 'bar'
                    // },
                ]
               
            };
            chart1.setOption(option,true);

        },
        //初始化
        async init(){
           // var today = localStorage.getItem("today");
           // var yesterday = moment(today,'YYYY-MM-DD').subtract(1, 'days').format('YYYY-MM-DD');
           var today=moment().format("YYYY-MM-DD");
           var yesterday=moment().subtract(1,'days').format('YYYY-MM-DD');
           //存放日期
            this.sjd_info =[yesterday,today];  
            //昨日
			let data2 = {
                "themeID":"yw_zfy_tj_sd",
                "startdate":yesterday,
                "enddate":yesterday,	             			
        	}     
            let res2 = await http.get(api.getBIDataAll,data2)
            if(res2.data!=null){
                this.list =res2.data
                for(this.i = 0;this.i < this.list.length;this.i++){
                    this.tmp_sr = [Number(this.list[this.i].DATETIME),(Number(this.list[this.i].MZFY_)+Number(this.list[this.i].ZYFY_)).toFixed(2)]
                    this.tmp_srpx.push(this.tmp_sr)     
                }
                this.tmp_srpx.sort(function(a,b){
                    return a[0] - b[0];
                }); 
                for(this.i = 0;this.i < this.tmp_srpx.length;this.i++){
                  //  this.sjd_info.push(this.tmp_srpx[this.i][0])  
                    this.sr_info.push(this.tmp_srpx[this.i][1])     
                }                 
            }
            //今日
            this.tmp_srpx = []
			let data = {
                "themeID":"yw_zfy_tj_sd",
				"startdate":today,
				"enddate":today,                	             			
             }
               
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.list =res.data
                for(this.i = 0;this.i < this.list.length;this.i++){
                    this.tmp_sr = [Number(this.list[this.i].DATETIME)+1,(Number(this.list[this.i].MZFY_)+Number(this.list[this.i].ZYFY_)).toFixed(2)]
                    this.tmp_srpx.push(this.tmp_sr)     
                }
                this.tmp_srpx.sort(function(a,b){
                    return a[0] - b[0];
                }); 
                for(this.i = 0;this.i < this.tmp_srpx.length;this.i++){
                   // this.sjd_info.push(this.tmp_srpx[this.i][0])  
                    this.sr_info.push(this.tmp_srpx[this.i][1])     
                } 
            }
           
            this.init1()
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
    .medicalService_rb_chart{
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
