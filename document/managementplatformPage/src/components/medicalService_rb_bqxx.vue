<template>
    <div class="medicalService_rb_bqxx">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick==0?'bingo':''" @click="tab(0)">人次</div>
            <div class="d2" :class="tab_pick==1?'bingo':''" @click="tab(1)">辅检</div>
            <div class="d2" :class="tab_pick==2?'bingo':''" @click="tab(2)">护理</div>
            <div class="d3" :class="tab_pick==3?'bingo':''" @click="tab(3)">手术</div>
        </div>
        <div class="main">
            <div id="chart"></div>
        </div>
        
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment' //格式化时间
export default {
    name: 'medicalService_rb_bqxx',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            tab_pick:0,
            title:{
                'title':`病区信息`,
                "title_black":'medicalService_rb'
            },
            //人次
            ret_rc:[], 
            //今日人次
            jrrc:[],

            //昨天人次
            zrrc:[2,3,4,5],

            //辅检
            ret_fjinfo:[],
            tmp_fj:[],
            tmp_fzjc:[],
            fzjc_info:[],
            tmp_fjsr:0,
            tmp_fjrjsr:0,
            //护理
            ret_hl:[],
            hl_0:0,
            hl_1:0,
            hl_2:0,
            hl_3:0,
            hl_info:[],

            //今日手术统计
            ret_jrss:[],
            jrss_jrtc:0,
            jrss_wctc:0,
            jrss_sqtc:0,
            //明日手术统计
            ret_mrss:[],
            mrss_jrtc:0,
            ss_info:[],


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
        // 图表 挂号
        async init0(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                color: ['#AF8760','#D7C3AC'],
                tooltip : {
                    trigger: 'axis',
                    // position: ['50%', '10%'],
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
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
                        data : ['入院', '出院', '在院', '危重'],
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
                        name:'人次',
                        type:'bar',
                        data:this.jrrc,
                        itemStyle:{
                            color:'#D7C3AC',
                            normal:{
                                barBorderRadius:[25, 25, 0, 0],
                            }
                        }
                    },
                    // {
                    //     name:'2',
                    //     type:'bar',
                    //     data:this.zrrc,
                    //     itemStyle:{
                    //         color:'#D7C3AC',
                    //         normal:{
                    //             barBorderRadius:[25, 25, 0, 0],
                    //         }
                    //     }
                    // },
                ]
            }
            chart1.setOption(option,true);

        },
        // 图表 辅检
        async init1(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let data = this.fzjc_info
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
                      //  console.log(111,p.data[0])
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
                        }
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
        // 图表护理
        async init2(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));            
            let option = {                
                tooltip: {
                    // trigger: 'axis',
                    // axisPointer: {
                    //     type: 'cross'
                    // },
                    formatter:function(p){                       
                        let s = ''
                        // 悬浮窗口
                        s+=p.name+':'+p.value; 
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
                angleAxis: {
                },
                radiusAxis: {
                    type: 'category',
                    data: ['III级','II级','I级','特级'],
                    // data: [''','222','333','555'],
                    z: 100,
                    splitLine:{
                        // show:true
                    }
                },
                polar: {
                },
                
                series: [{
                    type: 'bar',
                    data: that.hl_info,
                    coordinateSystem: 'polar',
                    stack: 'a',
                    itemStyle:{
                        color:'#8B5016'           
                    }
                }],
                
            }
            chart1.setOption(option,true);
        },
        // 图表手术
        async init3(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let option = {
                color: ['#AF8760','#D7C3AC'],
                tooltip : {
                    trigger: 'axis',
                    // position: ['50%', '10%'],
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
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
                       // data : ['安排', '申请', '完成','明日' ],
                       data : ['安排', '申请', '完成' ],
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
                        name:'台数',
                        type:'bar',
                        barMaxWidth:20,
                        data:that.ss_info,
                        itemStyle:{
                            color:'#D7C3AC',
                            normal:{
                                barBorderRadius:[25, 25, 0, 0],
                            }
                        }
                    }
                ]
            }
            chart1.setOption(option,true);
        },

        async fun_zyrc(){
			let data = {
                "themeID":"index_cryxx",
				//"startdate":localStorage.getItem("today"),
               // "enddate":localStorage.getItem("today"),    
                "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	            
				// "startdate":"2017-01-01",
				// "enddate":"2017-01-10"			
        	}
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_rc = res.data[0]
                //console.log(this.ret_rc)
                this.jrrc = [this.ret_rc.INDEX_CRYXX_JRRYRC_,this.ret_rc.INDEX_CRYXX_DRCYRC_,this.ret_rc.INDEX_CRYXX_DRZYRC_,this.ret_rc.ZYWZRC_]
                this.init0()
            }         
        } ,
        async fun_zyfj(){
			let data = {
                "themeID":"index_zy_fzjc",
 				//"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),               
				// "startdate":"2017-01-01",
                // "enddate":"2018-12-01"	
				"startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	,                		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_fjinfo =  res.data
                for(this.i = 0;this.i < this.ret_fjinfo.length;this.i++){
                    this.tmp_fjsr = Number((Number(this.ret_fjinfo[this.i].FJSR_)/10000).toFixed(2))
                    this.tmp_fjrjsr = Number(this.ret_fjinfo[this.i].FJRC_)>0?(Number(this.ret_fjinfo[this.i].FJSR_)/Number(this.ret_fjinfo[this.i].FJRC_)).toFixed(2):0
                    this.tmp_fj = [Number(this.ret_fjinfo[this.i].FJRC_),this.tmp_fjsr,this.tmp_fjrjsr,this.ret_fjinfo[this.i].TYPE_Text]
                    this.tmp_fzjc.push(this.tmp_fj )   
                }
                this.tmp_fzjc.sort(function(a,b){
                        return a[0] - b[0];
                });
                //console.log( this.tmp_fzjc)
                this.fzjc_info.push(this.tmp_fzjc )   
                // this.init1()
            }

        } ,            
        async fun_zyhl(){
			let data = {
                "themeID":"index_bqhl",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
				// "startdate":"2019-01-01",
                // "enddate":"2019-01-01"	
				 "startdate":moment().format("YYYY-MM-DD"),
				"enddate":moment().format("YYYY-MM-DD")	,                		
        	}
            let res = await http.get(api.getBIDataAll,data)
            if( res.data !=null){
                this.ret_hl = res.data   
                for(this.i = 0;this.i < this.ret_hl.length;this.i++){
                    if(this.ret_hl[this.i].HLJB=="4"){
                        this.hl_0 = Number(this.ret_hl[this.i].BQHL_HLRC_)
                    }
                    if(this.ret_hl[this.i].HLJB=="1"){
                        this.hl_1 = Number(this.ret_hl[this.i].BQHL_HLRC_)
                    }
                    if(this.ret_hl[this.i].HLJB=="2"){
                        this.hl_2 = Number(this.ret_hl[this.i].BQHL_HLRC_)
                    }
                    if(this.ret_hl[this.i].HLJB=="3"){
                        this.hl_3 = Number(this.ret_hl[this.i].BQHL_HLRC_)
                    }                                                   
                }
                this.hl_info = [this.hl_3,this.hl_2,this.hl_1,this.hl_0]    
                //this.init2()
            }

        } ,  
        async fun_zyss(){
			let data = {
                "themeID":"index_sstj",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
				// "startdate":"2019-01-09",
                // "enddate":"2019-01-09"	
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	,                		
        	}
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_jrss = res.data[0]
                this.jrss_jrtc = Number(this.ret_jrss.INDEX_SSTJ_APTC_)
                this.jrss_wctc = Number(this.ret_jrss.INDEX_SSTJ_WCTC_)
                this.jrss_sqtc = Number(this.ret_jrss.INDEX_SSTJ_SQTC_)
            }
         /*   let data2 = {
                "themeID":"index_sstj",              
                // "startdate":"2019-01-10",
                // "enddate":"2019-01-10",
                "startdate":moment(new Date()).add(1,'days').format("YYYY-MM-DD"),
                "enddate":moment(new Date()).add(1,'days').format("YYYY-MM-DD")	,                		
            }
            let res2 = await http.get(api.getBIDataAll,data2)
            if(res2.data!=null){
                this.ret_mrss = res2.data[0]
                this.mrss_jrtc = Number(this.ret_mrss.INDEX_SSTJ_APTC_)
            }
            this.ss_info = [this.jrss_jrtc,this.jrss_sqtc,this.jrss_wctc, this.mrss_jrtc ]
            */
           this.ss_info = [this.jrss_jrtc,this.jrss_sqtc,this.jrss_wctc]
            //this.init3()            
        } , 
    },
    mounted(){
        this.fun_zyrc()
        this.fun_zyfj()
        this.fun_zyhl()
        this.fun_zyss()   
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_rb_bqxx{
        height:calc(100% - 0.3rem);
        font-size: .14rem;
        display: flex;
        flex-direction: column;

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
    }

</style>
