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
                'title':'上周收入图表',
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
        //图表 周报
        async init2(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                color: ['#AF8760'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '2%',
                    right: '6%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                       // data : ['周一', '周二', '周三', '周四', '周五', '周六', '周天'],
                       data:this.zb_rq,
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
                        name:'总收入',
                        type:'line',
                       // barWidth: '60%',
                        data:this.zbsr_info
                        // itemStyle:{
                        //     normal:{
                        //         barBorderRadius:[25, 25, 0, 0],
                        //     }
                        // }
                    }
                ]
            }
            chart1.setOption(option,true);
        },
        //初始化
        async init(){
           // var today = localStorage.getItem("today");
           // var yesterday = moment(today,'YYYY-MM-DD').subtract(1, 'days').format('YYYY-MM-DD');
           var today=moment().format("YYYY-MM-DD");
           var yesterday=moment().subtract(1,'days').format('YYYY-MM-DD');
   
            let data_zb = {
                "themeID":"yw_zfy_tj",
				//"startdate":moment(today,'YYYY-MM-DD').day(-6).format("YYYY-MM-DD"),subtract(1, 'years')
               // "enddate":moment(today,'YYYY-MM-DD').day(-0).format("YYYY-MM-DD")	
                "startdate":moment().day(-6).format("YYYY-MM-DD"),
				"enddate":moment().day(-0).format("YYYY-MM-DD")	
            } 
            let res_zb  = await http.get(api.getBIDataAll,data_zb)
            if(res_zb.data!=null){
                this.list =res_zb.data;
                for(this.i = 0;this.i < this.list.length ;this.i++){
                    //this.tmp_rq = Number(this.list[this.i].DOC.substring(0,4)+''+this.list[this.i].DOC.substring(5,7)+''+this.list[this.i].DOC.substring(8,10))
                    this.zb_rq.push(this.list[this.i].DOC);
                    //this.tmp_zb.push([this.tmp_rq,Number(this.list[this.i].INDEX_MZFY_) + Number(this.list[this.i].INDEX_ZYFY_)])  
                  this.zbsr_info.push((Number(this.list[this.i].INDEX_MZFY_) + Number(this.list[this.i].INDEX_ZYFY_)).toFixed(2));
                }

                // this.tmp_zb.sort(function(a,b){
                //     return a[0] - b[0];
                // });
                // console.log("排序后")
                // console.log(this.tmp_zb);
                // // console.log( this.tmp_zb)
                // for(this.i = 0;this.i < this.tmp_zb.length ;this.i++){
                //     this.zbsr_info.push(Number(this.tmp_zb[this.i][1]))
                // }
            }
             this.init2()
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
