<template>
    <div class="medicalService_tjcx_mz_kssr_yssr_chart">
        <commonHead :title = "title"></commonHead>
        <div class="date">{{info.date}}</div>
        <div class="tab">
            <div class="d1" :class="tab_pick==0?'bingo':''" @click="tab(0)">门诊</div>
            <div class="d2" :class="tab_pick==1?'bingo':''" @click="tab(1)">住院</div>
        </div>
        <div class="main">
            <div id="chart">
                
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
            tab_pick:0,
            title:{
                'title':sessionStorage.getItem('ksnames')+'医生收入',
                "title_black":'medicalService_tjcx_mz_kssr_yssr'
            },
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),             
            info:{
				date:sessionStorage.getItem('startTime')+'-'+sessionStorage.getItem('endTime'),
                
            },
            ret_list:{},
            tmp_mz:[],
            tmp_zy:[],           
            mzysxm:[],
            mzyssr:[],
            zyysxm:[],
            zyyssr:[],
        }
    },
    methods:{
        tab(t){
            this.tab_pick = t
            switch(t){
                case 0: this.init0()
                break;
                case 1: this.init1()
                break;
            }
           
        },
        // 图表 
        initChart(arrx,arry){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let dataShadow = [];
            // 数据
            // let data  = arrx;

            
            // 通过计算得出 data中最大的值
            let  yMax= Math.max(...arrx) 
            for (var i = 0; i < arrx.length; i++) {
                dataShadow.push(yMax);
            }
            let option = {
                color:['#AF8760','#AE2C2E'],
                dataZoom: [{
                    type: 'inside',
                    show: true,
                    yAxisIndex: [0],
                    left: '0%',
                    //   bottom: -5,
                    start: 0,
                    end: 100 //初始化滚动条
                }],
                legend: {
                    // data:['入院人数','出院人次'],
                },
                grid: {
                    left: '8%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    show:false,
                    boundaryGap: true,
                    type: 'value',
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
                    splitLine:{
                        show:false,
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                },
                yAxis: {
                    type: 'category',
                    data: arry,
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        // show:false,
                        color:'#9E9D9C',
                        fontSize:12,
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
                    data: arrx,
                    type: 'bar',
                    barWidth:5,
                    itemStyle:{
                        normal:{
                            label:{
                                show:true,
                                position:'right',
                                textStyle: { //数值样式
                                    // color: 'black',
                                    fontSize:12
                                },
                                opacity:0.1,
                                shadowOffsetX:22
                            }
                        },
                        
                    }
                },{
                    type: 'bar',
                    barWidth:5,
                    itemStyle: {
                        normal: {
                            color: 'rgba(0,0,0,0.05)',
                        }
                    },
                    barGap:'-100%',
                    barCategoryGap:'40%',
                    data: dataShadow,
                    animation: false
                }]
            };   
            chart1.setOption(option,true);

        },

        async init0(){
            let arry = this.mzysxm
            let arrx = this.mzyssr
            // 传入 x,y两组数据即可
            this.initChart(arrx,arry)
        },
        async init1(){
            let arry = this.zyysxm
            let arrx = this.zyyssr
            // 传入 x,y两组数据即可
            this.initChart(arrx,arry)
        },
        async init(){
            //收入查询
			let data = {
                "themeID": this.CWorYW+"_ys_ywsrtj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,	
                "dimDept":this.ksids,
                   		
        	 }

            let res = await http.get(api.getBIDataAll,data)
            if(res.data !=null){
                this.ret_list = res.data 
                for(this.i = 0;this.i <this.ret_list.length;this.i++){
                    this.tmp_mz.push([this.ret_list[this.i].DOCTOR_Text,Number(this.ret_list[this.i].MZSR_ ),Number(this.ret_list[this.i].ZYSR_)])
                }
            }
            //console.log(this.tmp_mz)
            this.tmp_mz.sort(function(a,b){
                return a[1] - b[1];
            });             
            for(this.i = 0;this.i <this.tmp_mz.length;this.i++){
                if(Number(this.tmp_mz[this.i][1]) > 0){
                    this.mzysxm.push(this.tmp_mz[this.i][0] )
                    this.mzyssr.push(this.tmp_mz[this.i][1] )

                }   
            } 
            this.tmp_mz.sort(function(a,b){
                return a[2] - b[2];
            });             
            for(this.i = 0;this.i <this.tmp_mz.length;this.i++){
                if(Number(this.tmp_mz[this.i][2]) > 0){
                    this.zyysxm.push(this.tmp_mz[this.i][0] )
                    this.zyyssr.push(this.tmp_mz[this.i][2] )

                }
            }             
            this.init0()           
            
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
    .medicalService_tjcx_mz_kssr_yssr_chart{
        height:calc(100% );
        font-size: .14rem;
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
        .date{
            height: 0.4rem;
            @include fcc;
            font-size: 0.17rem;
            color: $cffffff;
            background: $cD7C3AC;
        }
        .main{
            flex: 1;
            #chart{
                height: 100%;;
            }
        }
        
       
    }

</style>
