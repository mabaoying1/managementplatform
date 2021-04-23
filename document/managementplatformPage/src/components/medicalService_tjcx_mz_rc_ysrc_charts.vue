<template>
    <div class="medicalService_tjcx_mz_rc_ysrc_charts">
        <commonHead :title = "title"></commonHead>
        <div class="date">{{info.date}}</div>
        <div class="tab">
            <div class="d1" :class="tab_pick==0?'bingo':''" @click="tab(0)">挂号</div>
            <div class="d2" :class="tab_pick==1?'bingo':''" @click="tab(1)">收治</div>
            <div class="d2" :class="tab_pick==2?'bingo':''" @click="tab(2)">出院</div>
            <div class="d3" :class="tab_pick==3?'bingo':''" @click="tab(3)">手术</div>
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
                'title':'医生人次',
                "title_black":'medicalService_tjcx_mz_rc_ysrc'
            }, 
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),             
            info:{
				date:sessionStorage.getItem('startTime')+'-'+sessionStorage.getItem('endTime'),
                
            },
            i:0,
            mzysxm:[],
            szysxm:[],
            cyysxm:[],
            ssysxm:[],
            ret_list:{},
            list_mz:[],
            list_sz:[],
            list_cy:[],
            list_ss:[],           
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
                case 2: this.init2()
                break;
                case 3: this.init3()
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
                    data: arrx,
                    type: 'bar',
                    barWidth:15,
                    itemStyle:{
                        normal:{
                            label:{
                                show:true,
                                position:'right',
                                textStyle: { //数值样式
                                    // color: 'black',
                                    fontSize: 15
                                }

                            }
                        }
                    }
                },{
                    
                        
                    type: 'bar',
                    barWidth:15,
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
            let arrx = this.list_mz
            // 传入 x,y两组数据即可
            this.initChart(arrx,arry)
        },
        async init1(){
            let arry = this.szysxm
            let arrx = this.list_sz
            // 传入 x,y两组数据即可
            this.initChart(arrx,arry)
        },
        async init2(){
            let arry = this.zyysxm
            let arrx = this.list_cy
            // 传入 x,y两组数据即可
            this.initChart(arrx,arry)
        },
        async init3(){
            let arry = this.ssysxm
            let arrx = this.list_ss
            this.initChart(arrx,arry)
        },
        async init(){
            //收入查询
			let data = {
                "themeID":this.CWorYW+"_ys_rctj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
                "dimDept":this.ksids,		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_list = res.data
            for(this.i = 0;this.i <this.ret_list.length;this.i++){
                if(Number(this.ret_list[this.i].MZRC_) > 0){
                    this.mzysxm.push(this.ret_list[this.i].DOCTOR_Text)
                    this.list_mz.push(Number(this.ret_list[this.i].MZRC_))

                }
                if(Number(this.ret_list[this.i].RYRC_) > 0){
                    this.szysxm.push(this.ret_list[this.i].DOCTOR_Text)
                    this.list_sz.push(Number(this.ret_list[this.i].RYRC_))

                }
                if(Number(this.ret_list[this.i].CYRC_) > 0){
                    this.cyysxm.push(this.ret_list[this.i].DOCTOR_Text)
                    this.list_cy.push(Number(this.ret_list[this.i].CYRC_))

                }
                if(Number(this.ret_list[this.i].SSRC_) > 0){
                    this.ssysxm.push(this.ret_list[this.i].DOCTOR_Text)
                    this.list_ss.push(Number(this.ret_list[this.i].SSRC_))         
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
    .medicalService_tjcx_mz_rc_ysrc_charts{
        height:calc(100% );
        font-size: .14rem;
        display: flex;
        flex-direction: column;
        .tab{
            padding: 0.24rem 0.16rem;
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
