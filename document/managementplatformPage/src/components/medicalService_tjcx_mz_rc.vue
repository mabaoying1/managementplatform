<template>
    <div class="medicalService_tjcx_mz_rc">
        <commonHead :title = "title"></commonHead>
        <div class="ks ">
            <span class="s1 van-ellipsis">{{this.ksnames}}</span>
            <i class="icon iconfont icon-xiayiji"></i>
        </div>
        <div class="w">
            <div class="card">
           
                <div class="d1">
                    <span class="s1">{{startTime_show}}-{{endTime_show}}</span>
                    <span class="s2" @click="jump('medicalService_tjcx_mz_rc_ysrc')">查看当前科室医生 <i class="icon iconfont icon-xiayiji"></i></span>
                </div>
                <div class="d2">
                    <p class="p1">{{this.rc_mzrc|incisionLeft}}</p>
                    <p class="p2">当前科室挂号总人次</p>
                </div>
                <div class="d3">
                    <img src="../assets/z.png" alt="">
                    <img src="../assets/zfb2.png" alt="">
                    <img src="../assets/wx2.png" alt="">
                    <img src="../assets/ck.png" alt="">
                </div>
            </div> 
        </div>
        <div class="info">
            <div class="d1">
                <p class="p1">
                    {{rc_mzrc_tb|leftTwoDecimal}}
                    <img v-if="rc_mzrc_tb>0" src="../assets/9.png" alt=""> 
                    <img v-if="rc_mzrc_tb<0" src="../assets/8.png" alt=""> 
                </p>
                <p class="p2">
                    挂号人次同比
                </p>
            </div>
            <div class="d2">
                <p class="p1">
                    {{rc_mzrc_hb|leftTwoDecimal}}
                    <img v-if="rc_mzrc_hb>0" src="../assets/9.png" alt=""> 
                    <img v-if="rc_mzrc_hb>0" src="../assets/8.png" alt=""> 
                </p>
                <p class="p2">
                    挂号人次环比
                </p>
            </div>
        </div>
        <div class="chart">
            <div id="chart"></div>
        </div>
        <div class="warp">
            <commonTitle2 :title="'重点关注占比'" :pageName="''" :pageUrl="''"></commonTitle2>
            <van-swipe class="van_swip" :loop="false" :width="344">
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s12">{{rc_ryrc|incisionLeft}}</span>
                            </span>
                            <span class="s2">
                                收治人次
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{rc_ryrc_tb |leftTwoDecimal}}<img v-if="rc_ryrc_tb>0" src="../assets/9.png" alt=""> <img v-if="rc_ryrc_tb<0" src="../assets/8.png" alt=""></span>
                            <span class="s1">{{rc_ryrc_hb |leftTwoDecimal}}<img v-if="rc_ryrc_hb>0" src="../assets/9.png" alt=""> <img v-if="rc_ryrc_hb<0" src="../assets/8.png" alt=""></span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s12">{{rc_cyrc|incisionLeft}}</span>
                            </span>
                            <span class="s2">
                                出院人次
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{rc_cyrc_tb |leftTwoDecimal}}<img v-if="rc_cyrc_tb>0" src="../assets/9.png" alt=""> <img v-if="rc_cyrc_tb<0" src="../assets/8.png" alt=""></span>
                            <span class="s1">{{rc_cyrc_hb |leftTwoDecimal}}<img v-if="rc_cyrc_hb>0" src="../assets/9.png" alt=""> <img v-if="rc_cyrc_hb<0" src="../assets/8.png" alt=""></span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s12">{{rc_ssrc|incisionLeft}}</span>
                            </span>
                            <span class="s2">
                                手术
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{rc_ssrc_tb |leftTwoDecimal}}<img v-if="rc_ssrc_tb>0" src="../assets/9.png" alt=""> <img v-if="rc_ssrc_tb<0" src="../assets/8.png" alt=""></span>
                            <span class="s1">{{rc_ssrc_hb |leftTwoDecimal}}<img v-if="rc_ssrc_hb>0" src="../assets/9.png" alt=""> <img v-if="rc_ssrc_hb<0" src="../assets/8.png" alt=""></span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
            </van-swipe>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import common_title2 from "./common_title2.vue"

export default {
    name: 'medicalServiceBqxx',
    components:{
        "commonHead":common_head,
		"commonTitle2":common_title2

    },
    data () {
        return {
            tab_pick:0,
            title:{
                'title':'科室人次详情',
                "title_black":'medicalService_tjcx_mz'
            }, 
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),                        
            info:{
            },
            ret_rctj:[],
            rc_mzrc:0,
            rc_mzrc_tb:0,
            rc_mzrc_hb:0,
            rc_ryrc:0,
            rc_ryrc_tb:0,
            rc_ryrc_hb:0,            
            rc_cyrc:0,
            rc_cyrc_tb:0,
            rc_cyrc_hb:0,                        
            rc_ssrc:0,
            rc_ssrc_tb:0,
            rc_ssrc_hb:0, 

            MZRC_MOM:0,
            MZRC_YOY:0,
            MZRC_MOMGROWTH:0,
            MZRC_YOYGROWTH:0,   
            RYRC_MOM:0,
            RYRC_YOY:0,                     
            RYRC_MOMGROWTH:0,
            RYRC_YOYGROWTH:0,
            CYRC_MOM:0,
            CYRC_YOY:0,            
            CYRC_MOMGROWTH:0,
            CYRC_YOYGROWTH:0,
            SSRC_MOM:0,
            SSRC_YOY:0,            
            SSRC_MOMGROWTH:0,
            SSRC_YOYGROWTH:0,

            ret_ghrc:[],
            rc_gh_wx:0,
            rc_gh_zfb:0,                               
            rc_gh_ck:0,                                
            rc_gh_zzj:0,
        }
    },
    methods:{
    
        async init0(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('chart'));
            let arr1 = ['自助机','支付宝','微信','窗口']
            let arr2 = [{value: this.rc_gh_zfb, name:'支付宝'}, {value: this.rc_gh_zzj, name:'自助机'}, {value: this.rc_gh_wx, name:'微信'}, {value: this.rc_gh_ck, name:'窗口'},,]
           
           let option = {
               color:['#55789E','#C56535','#5C9252','#BB993F'],
                legend: {
                    type:'scroll',
                    orient: 'center',
                    // x: 'top',
                    // y: 'top',
                    right:10,
                    top:10,
                    data:arr1
                },
                series: [{
                    name:'挂号人次分析',
                    type:'pie',
                    center:['40%','50%'],
                    radius: ['50%', '85%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center',
                            formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
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
                    itemStyle: {
                        normal: {
                            label: {        //此处为指示线文字
                                show: true,
                                position: 'inner', //标签的位置
                                textStyle: {
                                    fontWeight: 200,
                                    fontSize: 10    //文字的字体大小
                                },
                                formatter: function (p) {   //指示线对应文字
                                    var data = p.data;
                                    console.log(p)
                                    return p.name+':'+p.percent;
                                }
                            },
                            labelLine:{  
                                normal:{  
                                    show:false,
                                    length:5  
                                }  
                            },
                        }
                    },
                    data:arr2
                }]
            }
            
            chart1.setOption(option,true);
            chart1.on('click', function (param) {
                // 图表 - 点击事件
                console.log(param);
            })
        },
        //  人次查询
        async initRcInfo(){            
			let data = {
                "themeID":this.CWorYW+"_rc_rctj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show, 
                "dimDept":this.ksids,		                       		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_rctj = res.data
                for(this.i = 0;this.i <this.ret_rctj.length;this.i++){
                    this.rc_mzrc += Number( this.ret_rctj[this.i].MZRC_)
                    this.rc_ryrc += Number( this.ret_rctj[this.i].RYRC_)
                    this.rc_cyrc += Number( this.ret_rctj[this.i].CYRC_)
                    this.rc_ssrc += Number( this.ret_rctj[this.i].SSRC_)                    
                    this.MZRC_MOM+= Number(this.ret_rctj[this.i].MZRC_MOM)
                    this.MZRC_MOMGROWTH+=Number(this.ret_rctj[this.i].MZRC_MOMGROWTH)
                    this.MZRC_YOY+=Number(this.ret_rctj[this.i].MZRC_YOY)
                    this.MZRC_YOYGROWTH+=Number(this.ret_rctj[this.i].MZRC_YOYGROWTH)
                    this.RYRC_MOM+=Number(this.ret_rctj[this.i].RYRC_MOM)
                    this.RYRC_MOMGROWTH+=Number(this.ret_rctj[this.i].RYRC_MOMGROWTH)
                    this.RYRC_YOY+=Number(this.ret_rctj[this.i].RYRC_YOY)
                    this.RYRC_YOYGROWTH+=Number(this.ret_rctj[this.i].RYRC_YOYGROWTH)
                    this.CYRC_MOM+=Number(this.ret_rctj[this.i].ZYFY_MOMGROWTH)
                    this.CYRC_MOMGROWTH+=Number(this.ret_rctj[this.i].CYRC_MOMGROWTH)
                    this.CYRC_YOY+=Number(this.ret_rctj[this.i].CYRC_YOY)
                    this.CYRC_YOYGROWTH+=Number(this.ret_rctj[this.i].CYRC_YOYGROWTH)
                    this.SSRC_MOM+=Number(this.ret_rctj[this.i].SSRC_MOM)
                    this.SSRC_MOMGROWTH+=Number(this.ret_rctj[this.i].SSRC_MOMGROWTH)
                    this.SSRC_YOY+=Number(this.ret_rctj[this.i].SSRC_YOY)
                    this.SSRC_YOYGROWTH+=Number(this.ret_rctj[this.i].SSRC_YOYGROWTH)                                        
                }
            }
            this.rc_mzrc_hb = this.MZRC_MOM>0?this.MZRC_MOMGROWTH /this.MZRC_MOM:this.MZRC_MOMGROWTH
            this.rc_mzrc_tb = this.MZRC_YOY>0?this.MZRC_YOYGROWTH /this.MZRC_YOY:this.MZRC_YOYGROWTH
            this.rc_ryrc_hb = this.RYRC_MOM>0?this.RYRC_MOMGROWTH /this.RYRC_MOM:this.RYRC_MOMGROWTH 
            this.rc_ryrc_tb = this.RYRC_YOY>0?this.RYRC_YOYGROWTH /this.RYRC_YOY:this.RYRC_YOYGROWTH
            this.rc_cyrc_hb = this.CYRC_MOM>0?this.CYRC_MOMGROWTH /this.CYRC_MOM:this.CYRC_MOMGROWTH
            this.rc_cyrc_tb = this.CYRC_YOY>0?this.CYRC_YOYGROWTH /this.CYRC_YOY:this.CYRC_YOYGROWTH
            this.rc_ssrc_hb = this.SSRC_MOM>0?this.SSRC_MOMGROWTH /this.SSRC_MOM:this.SSRC_MOMGROWTH
            this.rc_ssrc_tb = this.SSRC_YOY>0?this.SSRC_YOYGROWTH /this.SSRC_YOY:this.SSRC_YOYGROWTH

			let data2 = {
                "themeID":this.CWorYW+"_ghrc",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,  
                "dimDept":this.ksids,		       		
        	 }
            let res2 = await http.get(api.getBIDataAll,data2)
            this.ret_ghrc = res2.data
            for(this.i = 0;this.i < this.ret_ghrc.length ;this.i++){
               if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("微") !=-1){
                   this.rc_gh_wx = Number(this.ret_ghrc[this.i].MZRC_)
               }else
               if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("支") !=-1){
                   this.rc_gh_zfb = Number(this.ret_ghrc[this.i].MZRC_)
               }else
               if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("自") !=-1){
                   this.rc_gh_zzj = Number(this.ret_ghrc[this.i].MZRC_)
               }else
               if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("人") !=-1){
                   this.rc_gh_ck = Number(this.ret_ghrc[this.i].MZRC_)
               }                                             
               
            }
            this.init0()            
        },        
    },
    mounted(){
        this.initRcInfo()
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_rc{
        min-height:100% ;
        font-size: .14rem;
        display: flex;
        flex-direction: column;
        background: $cF7F3F0;
        .ks{
            background: $cffffff;
            padding: 0 0.15rem;
            box-sizing: border-box;
            width: 100%;
            height: 0.6rem;
            @include fcs;
            border-bottom: 0.01rem solid $cF7F3F0;
            .s1{
                font-size: 0.14rem;
                color: $c6E6E6E;
            }
            i{
                font-size: 0.14rem;
                color: $c6E6E6E;
            }
        }
        .w{
            background: $cffffff;
            padding: 0.1rem;
            .card{
                height: 1.8rem;
                
                box-sizing: border-box;
                background: $cffffff;

                background-image: url('../assets/bg3.png') ;
                // background-size:cover;
                background-repeat:no-repeat;
                background-size:100% 100%;
                
                .d1{
                    
                    // height: 0.6rem;
                    @include fcs;
                    padding: 0 0.22rem;
                    padding-top: 0.2rem;
                    .s1{
                        font-size: 0.12rem;
                        color: $cD7C3AC;
                    }
                    .s2{
                        width: auto;
                        height: 0.2rem;
                        background: $cffffff;
                        padding: 0 0.13rem;
                        border-radius: 0.15rem;
                        font-size: 0.1rem;
                        @include fcc;
                        color: $cAF8760;
                        i{
                            font-size: 0.1rem;
                            line-height: 0.2rem;
                        }
                    }
                }
                .d2{
                    padding:  0 0.22rem;
                    .p1{
                        font-size: 0.38rem;
                        font-weight: 600;
                        color: $cffffff;
                    }
                    .p2{
                        font-size: 0.13rem;
                        color: $cEFE7DF;
                    }
                }
                .d3{
                    padding:  0.1rem 0.22rem;
                    @include fcc;
                    justify-content: flex-start;
                    img{
                        // background: #cfcfcf;
                        height:0.2rem ;
                        margin-right: 0.1rem;
                    }
                }
            }
            margin-bottom: 0.01rem;
        }
        
        .info{
            background: $cffffff;
            height:0.8rem ;
            margin-bottom: 0.02rem;
            width: 100%;
            @include fcs;
            div{
                
                flex: 1;
                @include fmc;
                .p1{
                    box-sizing: border-box;
                    padding-left: 25%;
                    width: 100%;
                    font-size: .16rem;
                    color: $c6E6E6E;
                    img{
                        width: .1rem;
                        height: .14;
                    }
                }
                .p2{
                    box-sizing: border-box;

                    padding-left: 25%;
                    width: 100%;
                    font-size: .13rem;
                    color: $c9E9D9C;
                }
            }
            .d1{
                border-right: 0.01rem solid $cF7F3F0;
            }
        }
        .chart{
            background: $cffffff;

            height: 3rem;
            // background: red;
            #chart{
                height: 100%;;
            }
        }
        .warp{
            margin-bottom: 0.2rem;
            .van_swip{
                height: 1.2rem;
                .li{
                    height: 100%;
                    background-image: url(../assets/zhyb_bg.png);
                    background-size:cover;
                    background-repeat:no-repeat;
                    margin:0 0.12rem;
                    .d0{
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
                                color: $c6E6E6E;
                                font-size: 0.24rem;
                                font-weight: 600;
                            }
                        }
                        .s2{
                            font-size: 0.1rem;
                            color: $c6D473A;
                            min-width: 0.7rem;
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
        }
        

    }

</style>
