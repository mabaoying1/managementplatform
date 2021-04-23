<template>
    <div class="medicalService_tjcx_mz_kssr_chart">
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
                'title':sessionStorage.getItem("ksnames")+'收入(万元)雷达图',
                "title_black":'medicalService_tjcx_mz_kssr'
            }, 
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),
            
            ret_srfbt:[],
            tmp_rq:'',
            tmp_sr:[],            
            mz_srfbt:[],
            zy_srfbt:[], 
            time:[],
            mz_sr:[],
            zy_sr:[],           
        }
    },
    methods:{
        // 图表 挂号
        async init0(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                color:['#AE2C2E','#AF8760'],
                legend: {
                    data:['住院收入','门诊收入'],
                    left:10,
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
                radar: {
                    // shape: 'circle',
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5]
                    }
                    },
                    indicator: this.time
                },
                series: [{
                    // name: '预算 vs 开销（Budget vs spending）',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    label: {
                        show:true,
                        color:'#666666',
                        fontSize:10,
                    },
                    data : [
                        {
                            value :that.zy_sr,
                            name : '住院收入',
                            itemStyle: {
                                normal: {
                                    color: {
                                        type: 'linear',
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [{
                                            offset: 0, color: '#AE2C2E' // 0% 处的颜色
                                        }, {
                                            offset: 1, color: '#AE2C2E' // 100% 处的颜色
                                        }],
                                        globalCoord: false // 缺省为 false
                                    }
                                }
                            },
                            areaStyle: {
                                normal: {
                                    opacity: 0.3
                                }
                            }
                            
                        },
                        {
                            value : that.mz_sr,
                            name : '门诊收入',
                            itemStyle: {
                                normal: {
                                    color: {
                                        type: 'linear',
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [{
                                            offset: 0, color: '#AF8760' // 0% 处的颜色
                                        }, {
                                            offset: 1, color: '#AF8760' // 100% 处的颜色
                                        }],
                                        globalCoord: false // 缺省为 false
                                    }
                                }
                            },
                            areaStyle: {
                                normal: {
                                    opacity: 0.3
                                }
                            }
                        }
                    ]
                }]
            };
            
            chart1.setOption(option,true);

        },
        async init(){
            
			let data = {
                "themeID":this.CWorYW+"_zfy_tj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
                "dimDept":this.ksids,        		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            //console.log(res.data)
            if(res.data.length!=0){
                this.ret_srfbt = res.data
                for(this.i = 0;this.i < this.ret_srfbt.length ;this.i++){
                    this.tmp_rq = Number(this.ret_srfbt[this.i].DOC.substring(0,4)+''+this.ret_srfbt[this.i].DOC.substring(5,7)+''+this.ret_srfbt[this.i].DOC.substring(8,10))
                    this.mz_srfbt.push([this.tmp_rq,(Number(this.ret_srfbt[this.i].INDEX_MZFY_)/10000).toFixed(2),(Number(this.ret_srfbt[this.i].INDEX_ZYFY_/10000).toFixed(2))])  
                    
                }
                this.mz_srfbt.sort(function(a,b){
                    return a[0] - b[0];
                });
            // console.log( this.mz_srfbt)
            for(this.i = 0;this.i < this.mz_srfbt.length ;this.i++){
                this.time.push({name:this.mz_srfbt[this.i][0]})
                this.mz_sr.push(this.mz_srfbt[this.i][1])
                this.zy_sr.push(this.mz_srfbt[this.i][2])

            }
            //console.log( this.time)
            //console.log( this.mz_sr)
            // console.log( this.zy_sr)

                this.init0()
            }

        }


    },
    mounted(){
        this.init()
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_kssr_chart{
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
