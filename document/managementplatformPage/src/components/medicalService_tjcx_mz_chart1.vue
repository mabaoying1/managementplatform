<template>
    <div class="medicalService_tjcx_mz_chart1">
        <commonHead :title = "title"></commonHead>
        <div class="head">{{this.startTime_show}}-{{this.endTime_show}}</div>
        <div class="main">
            <div id="chart"></div>
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
            tab_pick:0,
            title:{
                'title':'门急诊住院收入趋势图',
                "title_black":'medicalService_tjcx_mz'
            },
            CWorYW : sessionStorage.getItem('CWorYW'),             
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'), 
            ret_srfbt:[],
            tmp_rq:'',
            tmp_sr:[],
            mz_srfbt:[],
            zy_srfbt:[],
        }
    },
    methods:{
        // 图表 
        async init(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                
                 tooltip: { //提示框组件
                    trigger: 'axis'
                },
                color:['#AF8760','#AE2C2E'],
                 legend: {
                    data:['门急诊收入(万元)','住院收入(万元)'],
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
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色                            
                        },
                    },
                    axisLabel:{                       
                        color:'#9E9D9C',
                        fontSize:14,
                        fontWeight:'bold',
                        rotate:60  
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        // show:false,
                        color:'#9E9D9C',
                        fontSize:9,
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
                    name:'门急诊收入(万元)',
                    symbolSize: 5,
                    data: that.mz_srfbt,
                     type: 'line'
                   // type: 'scatter' //分布图
                },{
                    name:'住院收入(万元)',
                    symbolSize: 5,
                    data: that.zy_srfbt,
                    type: 'line'
                }]
            };
            
            chart1.setOption(option,true);

        },
        //门急诊住院收入分布图
        async initSrfbt(){
            
			let data = {
                "themeID":this.CWorYW+"_zfy_tj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_srfbt = res.data
            for(this.i = 0;this.i < this.ret_srfbt.length ;this.i++){
                this.tmp_rq = Number(this.ret_srfbt[this.i].DOC.substring(0,4)+''+this.ret_srfbt[this.i].DOC.substring(5,7)+''+this.ret_srfbt[this.i].DOC.substring(8,10))
                this.mz_srfbt.push([this.tmp_rq,((Number(this.ret_srfbt[this.i].INDEX_MZFY_)+Number(this.ret_srfbt[this.i].INDEX_JZFY_))/10000).toFixed(2)])  
                this.zy_srfbt.push([this.tmp_rq,(Number(this.ret_srfbt[this.i].INDEX_ZYFY_)/10000).toFixed(2)])
            }
            this.mz_srfbt.sort(function(a,b){
                return a[0] - b[0];
            });
            this.zy_srfbt.sort(function(a,b){
                return a[0] - b[0];
            });

           this.init()
        },          



    },
    mounted(){
        this.initSrfbt()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_chart1{
        height:calc(100% - 0.3rem);
        font-size: .14rem;
        display: flex;
        flex-direction: column;
        .head{
            height: 0.4rem;
            @include fcc;
            font-size: 0.16rem;
            color: $c6E6E6E;
        }
       
        
        .main{
            flex: 1;
            #chart{
                height: 100%;
            }
            
        }
    }

</style>
