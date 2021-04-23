<template>
    <div class="yb"  v-loading="loading">
        <div class="title">综合月报(万)</div>
        <van-tabs class="van-tabs" v-model="info.activeName" @change="onChange">
            <van-tab v-for="item in info.arr" :title="item" :key="item"  >
            </van-tab>
        </van-tabs>
        <div class="charts">
            <canvas id="myChart" ></canvas>
        </div>
        <div class="warp">
            <div class="d1">
                <div class="d1_1">
                    <p class="p1">月收入</p>
                    <p class="p2"><span class="s1">¥</span><span class="s2">{{yzfy/10000|incisionLeft}}万</span></p>
                </div>
                 <div>
                    <p class="p1">当月日均收入</p>
                    <p class="p2"><span class="s1">¥</span><span class="s2">{{ypjfy/10000|incisionLeft}}万</span></p>
                </div>
            </div>
            <commonTitle2 :title="'重点关注占比'" :pageName="''" :pageUrl="''"></commonTitle2>
            <van-swipe class="van_swip" :loop="false" :width="378">
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s12">{{this.ypzb |leftTwoDecimal}}</span>	
                                <!-- <v-countup class="s12" :duration="'2'" :end-value="this.ypzb"></v-countup> -->
                            </span>
                            <span class="s2">
                               药占比
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{this.yptb |leftTwoDecimal}} <img v-if="this.yptb<0" src="../assets/8.png" alt=""><img v-if="this.yptb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{this.yphb |leftTwoDecimal}} <img v-if="this.yphb<0" src="../assets/8.png" alt=""><img v-if="this.yphb>0" src="../assets/9.png" alt=""> </span>
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
                                <span class="s12">{{this.clzb |leftTwoDecimal}} </span>
                                	<!-- <v-countup class="s12" :duration="'2'" :end-value="this.clzb"></v-countup> -->
                            </span>
                            <span class="s2">
                               材料占比
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{this.cltb |leftTwoDecimal}} <img v-if="this.cltb<0" src="../assets/8.png" alt=""><img v-if="this.cltb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{this.clhb |leftTwoDecimal}} <img v-if="this.clhb<0" src="../assets/8.png" alt=""><img v-if="this.clhb>0" src="../assets/9.png" alt=""> </span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
 
            </van-swipe>
            <commonTitle2 :title="'均次费用'" :pageName="''" :pageUrl="''"></commonTitle2>
            <van-swipe class="van_swip" :loop="false" :width="378">
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="this.mz_jcfy"></v-countup>
                            </span>
                            <span class="s2">
                                门急诊均次费用
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{jc_mzsr_tb | leftTwoDecimal}} <img v-if="this.jc_mzsr_tb<0" src="../assets/8.png" alt=""><img v-if="this.jc_mzsr_tb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{jc_mzsr_hb | leftTwoDecimal}} <img v-if="this.jc_mzsr_hb<0" src="../assets/8.png" alt=""><img v-if="this.jc_mzsr_hb>0" src="../assets/9.png" alt=""> </span>
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
                                <span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="this.zy_jcfy"></v-countup>
                            </span>
                            <span class="s2">
                                住院均次费用
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{jc_zysr_tb |leftTwoDecimal}} <img v-if="this.jc_zysr_tb<0" src="../assets/8.png" alt=""><img v-if="this.jc_zysr_tb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{jc_zysr_hb |leftTwoDecimal}} <img v-if="this.jc_zysr_hb<0" src="../assets/8.png" alt=""><img v-if="this.jc_zysr_hb>0" src="../assets/9.png" alt=""> </span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
            </van-swipe>
            <commonTitle2 :title="'收治人次'" :pageName="''" :pageUrl="''"></commonTitle2>
            <van-swipe class="van_swip" :loop="false" :width="378">
                <van-swipe-item>
                    <div class="li">
                        <div class="d0">
                            <span class="s1">
                                <span class="s11"></span>	<v-countup class="s12" :duration="'2'" :end-value="this.mzrc"></v-countup>
                            </span>
                            <span class="s2">
                                月门急诊人次
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{this.mzrc_tb |leftTwoDecimal}} <img v-if="this.mzrc_tb<0" src="../assets/8.png" alt=""><img v-if="this.mzrc_tb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{this.mzrc_hb |leftTwoDecimal}} <img v-if="this.mzrc_hb<0" src="../assets/8.png" alt=""><img v-if="this.mzrc_hb>0" src="../assets/9.png" alt=""> </span>
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
                                <span class="s11"></span>	<v-countup class="s12" :duration="'2'" :end-value="this.rc_szrc"></v-countup>
                            </span>
                            <span class="s2">
                                月收治人次
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{this.rc_szrc_tb |leftTwoDecimal}} <img v-if="this.rc_szrc_tb<0" src="../assets/8.png" alt=""><img v-if="this.rc_szrc_tb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{this.rc_szrc_hb |leftTwoDecimal}} <img v-if="this.rc_szrc_hb<0" src="../assets/8.png" alt=""><img v-if="this.rc_szrc_hb>0" src="../assets/9.png" alt=""> </span>
                        </div>
                        <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div>
                    </div>
                </van-swipe-item>
                <van-swipe-item>
                    <div class="li2">
                        <div class="d0">
                            <!-- <span class="s1">
                                <span class="s11"></span>	<v-countup class="s12" :duration="'2'" :end-value="this.rc_rjszrc"></v-countup>
                            </span> -->
                           <!-- <span class="s2">
                                 日均收治人次
                            </span> -->
                            <span class="s1">
                                日均门急诊人次
                            </span>
                        </div>
                        <!-- <div class="d2">
                            <span class="s1">{{this.rc_rjszrc_tb |leftTwoDecimal}}<img v-if="this.rc_rjszrc_tb<0" src="../assets/8.png" alt=""><img v-if="this.rc_rjszrc_tb>0" src="../assets/9.png" alt=""> </span>
                            <span class="s1">{{this.rc_rjszrc_hb |leftTwoDecimal}}<img v-if="this.rc_rjszrc_hb<0" src="../assets/8.png" alt=""><img v-if="this.rc_rjszrc_hb>0" src="../assets/9.png" alt=""> </span>
                        </div> -->
                          <!-- <div class="d3">
                            <span>同比</span>
                            <span>环比</span>
                        </div> -->
                         <div class="d2">
                            <span class="s1">{{this.rc_rjjzrc |incisionLeft}} </span>
                            <span class="s1">{{this.rc_rjmzrc |incisionLeft}} </span>
                        </div>
                        <div class="d3">
                            <span>急诊人次</span>
                            <span>门诊人次</span>
                        </div>        
                    </div>
                </van-swipe-item>

            </van-swipe>
             <commonTitle2 :title="'出入院人次'" :pageName="''" :pageUrl="''"></commonTitle2>
            <van-swipe class="van_swip" :loop="false" :width="378">
                <van-swipe-item>
                    <div class="li2">
                        <div class="d0">
                            
                            <span class="s1">
                                月出入院
                            </span>
                        </div>
                        <div class="d2">
                            <span class="s1">{{this.cyrc |incisionLeft}} </span>
                            <span class="s1">{{this.ryrc |incisionLeft}} </span>
                        </div>
                        <div class="d3">
                            <span>出院</span>
                            <span>入院</span>
                        </div>
                    </div>
                </van-swipe-item>
            <van-swipe-item>
                <div class="li2">
                    <div class="d0">
                        
                        <span class="s1">
                            日均出入院
                        </span>
                    </div>
                    <div class="d2">
                        <span class="s1">{{this.rjcyrc |incisionLeft}} </span>
                        <span class="s1">{{this.rjryrc |incisionLeft}} </span>
                    </div>
                    <div class="d3">
                        <span>出院</span>
                        <span>入院</span>
                    </div>
                </div>
            </van-swipe-item>
                
            </van-swipe>
        </div>
        <commonNavMeishan :page="1"></commonNavMeishan>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import common_title2 from "./common_title2.vue"
import common_nva from "./common_nva.vue"

import http from '../utils/http'
import api from '../utils/api'
import {point} from '../utils/point'
import moment from 'moment' //格式化时间





export default {
    name: 'medicalService_zhyb',
    components:{
        "commonHead":common_head,
        "commonTitle2":common_title2,
        "commonNavMeishan":common_nva,
    },
    data () {
        return {
            //year:moment().year()-1,
            year:moment().format("YYYY"),
            //month:'01',
            month:moment().format("MM"),
            dateMonth:'',
            days:0,
            i:0,
            index:0,
            loading:false,
            info:{
                activeName:Number(moment().format("MM"))-1,
                arr:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            },
            //查询类型
            CWORYW:'yw',
            ret_mrsr:[],
            tmp_mrsr:0,
            //每日收入
            mrsr_info:[],
            //月总费用
            yzfy:0,
            yzfy_mom:0,
            yzfy_yoy:0,
            //日均费用
            ypjfy:0,
            //收入
            sr_info:{},
            //门诊收入
            sr_mzsr:0,
            //住院收入
            sr_zysr:0,
            //出院收入
            sr_cysr:0,
            CYFY_:0,
            CYFY_MOM:0,
            CYFY_YOY:0,

            MZFY_:0,
            MZFY_MOM:0,
            MZFY_MOMGROWTH:0,
            MZFY_YOY:0,
            MZFY_YOYGROWTH:0,
            ZYFY_:0,
            ZYFY_MOM:0,
            ZYFY_MOMGROWTH:0,
            ZYFY_YOY:0,
            ZYFY_YOYGROWTH:0,

            //材料占比
            ret_cl_info:[],
            clzb:0,
            cltb:0,
            clhb:0,
            //药占比
            ret_yp_info:[],
            ypzb:0,
            yptb:0,
            yphb:0,

            //出入院人次
            ret_zyrc:[],
            ryrc :0,
            cyrc :0,
            rjcyrc :0,
            rjryrc :0,
            ret_zyinfo:[],
            //
            RYXX_JRRYRC_:0,
            RYXX_JRRYRC_MOM:0,
            RYXX_JRRYRC_MOMGROWTH:0,
            RYXX_JRRYRC_YOY: 0,
            RYXX_JRRYRC_YOYGROWTH: 0,
            //门诊人次
            ret_mzrc:[],
            mzrc:0,
            mzrc_tb:0,
            mzrc_hb:0,
            //
            MJZLRC_:0,
            MJZLRC_MOM: 0,
            MJZLRC_MOMGROWTH:0,
            MJZLRC_YOY:0,
            MJZLRC_YOYGROWTH:0,
            
            //收治人次
            rc_szrc:0,
            rc_szrc_tb:0,
            rc_szrc_hb:0,
            //日均收治人次
            rc_rjszrc:0,
            rc_rjszrc_tb:0,
            rc_rjszrc_hb:0,

            //日均门急诊人次
            rc_rjmzrc:0,
            rc_rjjzrc:0,

            jc_tmp:0,
            //门诊均次费用
            mz_jcfy:0,
            jc_mzsr_tb:0,
            jc_mzsr_hb:0,
            //住院均次费用
            zy_jcfy:0,
            jc_zysr_tb:0,
            jc_zysr_hb:0,




        }
    },
    
    methods:{
        //获取显示的月份
        fun_getXsys(){
            let months = moment().format("MM")
            switch(months){
                case '01':
                    this.info.arr = ['一月']
                break;
                case '02':
                    this.info.arr = ['一月','二月']
                break;
                case '03':
                    this.info.arr = ['一月','二月','三月']
                break;
                case '04':
                    this.info.arr = ['一月','二月','三月','四月']
                break;
                case '05':
                    this.info.arr = ['一月','二月','三月','四月','五月']
                break;
                case '06':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月']
                break;
                case '07':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月']
                break;
                case '08':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月','八月']
                break;
                case '09':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月','八月','九月']
                break;
                case '10':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月']
                break;
                case '11':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月']
                break;
                case '12':
                    this.info.arr = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
                break;                                
            }
           // console.log(months)
        },
        async initCharts(){
            const chart = new this.$F2.Chart({
                id: 'myChart',
                pixelRatio: window.devicePixelRatio
            });
            // 载入数据
            chart.source(this.mrsr_info
            ,{
                x: {
                    min : 1,
                    max: this.mrsr_info.length
                }
             }
            );
            chart.tooltip({
                // custom: true,
                showCrosshairs: true,
                // showXTip: true,
                // showYTip: true,
               onShow: function onShow(ev) {
                    const items = ev.items;
                    //console.log(items)
                    items[0].name = items[0].origin.z+'  收入¥';
                }
            });
            chart.scale({
                time: {
                    range: [ 0, 1 ]
                },
                tem: {
                    tickCount: 5,
                    min: 0
                }
            });
            // x轴
            chart.axis('x', {
                label: function label(text, index, total) {
                    const textCfg = {
                        fill:'#ffffff',
                    };
                    if (index === 0) {
                        textCfg.textAlign = 'left';
                    } else if (index === total - 1) {
                        textCfg.textAlign = 'right';
                    }
                    
                    return textCfg;
                },
                line:{
                    lineWidth: 1,
                    stroke:'#ffffff'
                }
            });
            // y轴
            chart.axis('y', {
                label: function label(text, index, total) {
                    const textCfg = {
                        fill:'#ffffff',
                    };
                    if (index === 0) {
                        textCfg.textAlign = 'left';
                    } else if (index === total) {
                        textCfg.textAlign = 'right';
                    }
                    
                    return textCfg;
                },
                line:{
                    lineWidth: 1,
                    stroke:'#ffffff'
                },
                
            });

            chart.area().position('x*y').color('#ffffff').adjust('stack');
            chart.line().position('x*y').color('#ffffff');
            
            chart.render();

        },
        async init(){
            this.dateMonth = this.year + "-"+this.month
			let data = {
				"themeID":"mon_sy_gbdays",
                "dimMonth":this.dateMonth		
        	}
            let res = await http.get(api.getBIDataAll,data);
            //console.log(res)
            if(res.data!=null){
                this.ret_mrsr = res.data   
                this.days = this.ret_mrsr.length
                for(this.i = 0;this.i <  this.ret_mrsr.length;this.i++){
                    this.index = Number(this.ret_mrsr[this.i].DAY.substring(8))
                    this.tmp_mrsr = Number(this.ret_mrsr[this.i].MON_SR_DAYS_)/10000
                    this.mrsr_info[this.index-1]=new point(this.index,Number(this.tmp_mrsr.toFixed(2)),this.ret_mrsr[this.i].DAY);
                    this.yzfy =  this.yzfy + Number(this.ret_mrsr[this.i].MON_SR_DAYS_)
                }
                console.log("月总费用==="+this.yzfy);
               //当日日均费用
               this.ypjfy=  this.yzfy  /  this.days 

                this.initCharts()
                this.initSrInfo()
                this.initZdgz()
            }else{
                this.mrsr_info=[];
                this.initCharts();
            }



        },
        //重点关注占比
        async initZdgz(){
            //去年同月总收入
            this.dateMonthYoy = (Number(this.year)-1) + "-"+this.month;
			let data3 = {
				"themeID":"mon_sy_gbmons",
                "dimMonth":this.dateMonthYoy		
            }
             let res3 = await http.get(api.getBIDataAll,data3)
            if(res3.data!=null && res3.data.length>0){
                this.yzfy_yoy=res3.data[0].MON_SR_GBMONS_;
            }
            //同年上月总收入
            this.dateMonthMon = Number(this.month)==1 ? ((Number(this.year)-1) + "-"+12) : (this.year + "-"+(Number(this.month)-1));
			let data4 = {
				"themeID":"mon_sy_gbmons",
                "dimMonth":this.dateMonthMon		
            }
            let res4 = await http.get(api.getBIDataAll,data4)
            if(res4.data!=null && res4.data.length>0){
                this.yzfy_mom=res4.data[0].MON_SR_GBMONS_;
            }


            //*********财务  药品************
			let data2 = {
                "themeID":"yw_ypsr_zb",
                "dimMonth":this.dateMonth		
        	 }
            let res2 = await http.get(api.getBIDataAll,data2)
            if(res2.data!=null){
                this.ret_yp_info = res2.data[0];
                //药品占比
                if(this.yzfy !=0){
                    this.ypzb = (Number(this.ret_yp_info.MZ_YPSR_)+Number(this.ret_yp_info.ZY_YPSR_))/ this.yzfy                     
                }

                console.log("药品收入=="+this.ret_yp_info.MZ_YPSR_+"   住院药品收入="+this.ret_yp_info.ZY_YPSR_+"总费用="+this.yzfy+"    药占比="+this.ypzb);
                //药品总费用同环比
                this.fun_jcfythb((Number(this.ret_yp_info.MZ_YPSR_)+Number(this.ret_yp_info.ZY_YPSR_)), this.yzfy,  (Number(this.ret_yp_info.MZ_YPSR_MOM)+ Number(this.ret_yp_info.ZY_YPSR_MOM)), this.yzfy_mom);
                this.yphb = this.jc_tmp;
                this.fun_jcfythb((Number(this.ret_yp_info.MZ_YPSR_)+Number(this.ret_yp_info.ZY_YPSR_)), this.yzfy, (Number(this.ret_yp_info.MZ_YPSR_YOY) + Number(this.ret_yp_info.ZY_YPSR_YOY)), this.yzfy_yoy);
                this.yptb = this.jc_tmp;
                
            }

            //财务 材料
			let data = {
                "themeID":this.CWORYW+"_clsr_zb",
                "dimMonth":this.dateMonth
        	 }
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_cl_info = res.data[0] 
                if(this.yzfy !=0){
                    this.clzb = (Number(this.ret_cl_info.MZ_CLSR_)+Number(this.ret_cl_info.ZY_CLSR_))/ this.yzfy 
                }
              /*  if((Number(this.ret_cl_info.MZ_CLSR_MOM)+Number(this.ret_cl_info.ZY_CLSR_MOM))!=0){
                     this.clhb = (Number(this.ret_cl_info.MZ_CLSR_MOMGROWTH) +Number(this.ret_cl_info.ZY_CLSR_MOMGROWTH)) / (Number(this.ret_cl_info.MZ_CLSR_MOM)+Number(this.ret_cl_info.ZY_CLSR_MOM))
               } 
                if((Number(this.ret_cl_info.MZ_CLSR_YOY)+Number(this.ret_cl_info.ZY_CLSR_YOY))!=0){
                    this.cltb = (Number(this.ret_cl_info.MZ_CLSR_YOYGROWTH) +Number(this.ret_cl_info.ZY_CLSR_YOYGROWTH)) / (Number(this.ret_cl_info.MZ_CLSR_YOY)+Number(this.ret_cl_info.ZY_CLSR_YOY))
                } */
                
                 //同比
                if(this.yzfy_yoy != 0){
                    let clzb_yoy=(Number(this.ret_cl_info.MZ_CLSR_YOY)+Number(this.ret_cl_info.ZY_CLSR_YOY))/this.yzfy_yoy;
                    this.cltb=(this.clzb - clzb_yoy)/clzb_yoy;
                }
                 //环比
                if(this.yzfy_mom != 0){
                    let clzb_mom=(Number(this.ret_cl_info.MZ_CLSR_MOM)+Number(this.ret_cl_info.ZY_CLSR_MOM))/this.yzfy_mom;
                    this.clhb=(this.clzb - clzb_mom)/clzb_mom
                } 

            }

        }, 
        //收入查询
        async initSrInfo(){
            this.sr_mzsr = 0
            this.sr_zysr = 0
			let data = {
                "themeID":this.CWORYW+"_zfy_zb",
                "dimMonth":this.dateMonth        		
             }
             //门诊住院总费用
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null && res.data.length>0){
                this.sr_info =res.data[0]
                this.sr_mzsr = Number( this.sr_info.MZFY_) + Number( this.sr_info.JZFY_);         
                this.sr_zysr = Number( this.sr_info.ZYFY_);

                this.MZFY_ = Number( this.sr_info.MZFY_) + Number( this.sr_info.JZFY_)          
                this.MZFY_MOMGROWTH = Number( this.sr_info.MZFY_MOMGROWTH) + Number( this.sr_info.JZFY_MOMGROWTH)
                this.MZFY_MOM = Number( this.sr_info.MZFY_MOM) + Number( this.sr_info.JZFY_MOM)          
                this.MZFY_YOY = Number( this.sr_info.MZFY_YOY) + Number( this.sr_info.JZFY_YOY)
                this.MZFY_YOYGROWTH = Number( this.sr_info.MZFY_YOYGROWTH) + Number( this.sr_info.JsZFY_YOYGROWTH)    

                this.ZYFY_ = Number( this.sr_info.ZYFY_)          
                this.ZYFY_MOM = Number( this.sr_info.ZYFY_MOM)
                this.ZYFY_MOMGROWTH = Number( this.sr_info.ZYFY_MOMGROWTH)          
                this.ZYFY_YOY = Number( this.sr_info.ZYFY_YOY)
                this.ZYFY_YOYGROWTH = Number( this.sr_info.ZYFY_YOYGROWTH)  
                
               this.CYFY_ = Number( this.sr_info.CYFY_)          
               this.CYFY_MOM = Number( this.sr_info.CYFY_MOM)
               this.CYFY_YOY = Number( this.sr_info.CYFY_YOY)
               
            }

            //门诊就诊人次
			let data2 = {
                "themeID":"mjzlrc_total",
                "dimMonth":this.dateMonth
        	}
            let res2 = await http.get(api.getBIDataAll,data2)
            if(res2.data!=null){
                this.ret_mzrc = res2.data[0];
                //收治人次——》月门急诊挂号人次=门诊挂号人次+急诊挂号人次
               this.mzrc =    Number(this.ret_mzrc.MJZLRC_) + Number(this.ret_mzrc.JZLRC_)
                //月门急诊挂号人次同环比
               this.mzrc_hb = (Number(this.ret_mzrc.MJZLRC_MOMGROWTH)+ Number(this.ret_mzrc.JZLRC_MOMGROWTH)) / (Number(this.ret_mzrc.MJZLRC_MOM) + Number(this.ret_mzrc.JZLRC_MOM))
               this.mzrc_tb = (Number(this.ret_mzrc.MJZLRC_YOYGROWTH) + Number(this.ret_mzrc.JZLRC_YOYGROWTH))/ (Number(this.ret_mzrc.MJZLRC_YOY) + Number(this.ret_mzrc.JZLRC_YOY))
               
               //门急诊挂号人次=门诊挂号人次+急诊挂号人次
                this.MJZLRC_ = Number(this.ret_mzrc.MJZLRC_) + Number(this.ret_mzrc.JZLRC_)
                this.MJZLRC_MOM=Number(this.ret_mzrc.MJZLRC_MOM) + Number(this.ret_mzrc.JZLRC_MOM)
                this.MJZLRC_MOMGROWTH=Number(this.ret_mzrc.MJZLRC_MOMGROWTH) + Number(this.ret_mzrc.JZLRC_MOMGROWTH)
                this.MJZLRC_YOY= Number(this.ret_mzrc.MJZLRC_YOY) + Number(this.ret_mzrc.JZLRC_YOY)
                this.MJZLRC_YOYGROWTH= Number(this.ret_mzrc.MJZLRC_YOYGROWTH) + Number(this.ret_mzrc.JZLRC_YOYGROWTH)   
                
                //日均门诊挂号人次，急诊挂号人次
                this.rc_rjmzrc=this.ret_mzrc.MJZLRC_/this.days
                this.rc_rjjzrc=this.ret_mzrc.JZLRC_/this.days

               
                //门诊挂号均次费用
                if(this.MJZLRC_!=0){
                    this.mz_jcfy = this.MZFY_/this.MJZLRC_;   
                }   
                console.log("门诊人次=="+this.MJZLRC_+"   门诊费用==="+this.MZFY_);
                console.log("门诊均次费用==="+this.mz_jcfy);
                

                //门诊均次费用同环比
                this.fun_jcfythb(this.MZFY_, this.MJZLRC_, this.MZFY_MOM, this.MJZLRC_MOM);
                this.jc_mzsr_hb = this.jc_tmp;
                console.log("this.MZFY_MOM=="+this.MZFY_MOM+"    this.MJZLRC_MOM=="+this.MJZLRC_MOM);
                console.log("this.jc_mzsr_hb==="+this.jc_mzsr_hb);
                this.fun_jcfythb(this.MZFY_, this.MJZLRC_, this.MZFY_YOY, this.MJZLRC_YOY);
                this.jc_mzsr_tb = this.jc_tmp;
                console.log("this.MZFY_YOY=="+this.MZFY_YOY+"    this.MJZLRC_YOY="+this.MJZLRC_YOY);
                console.log("this.jc_mzsr_tb==="+this.jc_mzsr_tb);
            }

            //住院
			let data3 = {
                "themeID":"index_cryxx",
                "dimMonth":this.dateMonth
        	}
            let res3 = await http.get(api.getBIDataAll,data3)
            if(res3.data!=null){
                this.ret_zyrc = res3.data       
                for(this.i = 0;this.i < this.ret_zyrc.length;this.i++){
                    this.ryrc = this.ryrc + Number(this.ret_zyrc[this.i].INDEX_CRYXX_JRRYRC_)
                    this.cyrc = this.cyrc + Number(this.ret_zyrc[this.i].INDEX_CRYXX_DRCYRC_)
                }
                //日均入院人次
                this.rjryrc = this.ryrc /this.days
                //人次出院人次
                this.rjcyrc = this.cyrc /this.days  
                //月收治人次  只统计入院人次
                this.rc_szrc =  this.ryrc
                //日均收治人次
               // this.rc_rjszrc = this.rc_szrc /this.days                         
              
                //出院均次费用
                if(this.ryrc>0){
                    this.zy_jcfy = this.CYFY_/ this.cyrc
                }
                console.log("出院人次=="+this.cyrc+"   出院费用==="+this.CYFY_);
                console.log("出院均次费用==="+this.zy_jcfy);
               
            }

            //住院同环比
			let data4 = {
                "themeID":"cryxx_total",
                "dimMonth":this.dateMonth
        	 }
            let res4 = await http.get(api.getBIDataAll,data4)
            if(res4.data!=null){
                this.ret_zyinfo =res4.data[0]
                this.RYXX_JRRYRC_ = Number(this.ret_zyinfo.RYXX_JRRYRC_)
                this.RYXX_JRRYRC_MOM = Number(this.ret_zyinfo.RYXX_JRRYRC_MOM)
                this.RYXX_JRRYRC_MOMGROWTH= Number(this.ret_zyinfo.RYXX_JRRYRC_MOMGROWTH)
                this.RYXX_JRRYRC_YOY= Number(this.ret_zyinfo.RYXX_JRRYRC_YOY)
                this.RYXX_JRRYRC_YOYGROWTH= Number(this.ret_zyinfo.RYXX_JRRYRC_YOYGROWTH)

                //月收治人次同环比
                if((this.RYXX_JRRYRC_MOM+this.MJZLRC_MOM)>0){
                    this.rc_szrc_hb =this.RYXX_JRRYRC_MOMGROWTH/this.RYXX_JRRYRC_MOM

                }
                if((this.RYXX_JRRYRC_YOY+this.MJZLRC_YOY)>0){
                    this.rc_szrc_tb = this.RYXX_JRRYRC_YOYGROWTH/this.RYXX_JRRYRC_YOY
                }
                //日均收治人次同环比
                // this.rc_rjszrc_tb = this.rc_szrc_tb /this.days  
                // this.rc_rjszrc_hb = this.rc_szrc_hb /this.days    
                
                this.fun_jcfythb(this.CYFY_, this.ret_zyinfo.CRYXX_DRCYRC_, this.CYFY_MOM, this.ret_zyinfo.CRYXX_DRCYRC_MOM)
                this.jc_zysr_hb = this.jc_tmp;
                console.log("this.CYFY_MOM=="+this.CYFY_MOM+"    this.ret_zyinfo.CRYXX_DRCYRC_MOM=="+this.ret_zyinfo.CRYXX_DRCYRC_MOM);
                console.log("this.jc_zysr_hb==="+this.jc_zysr_hb);
                this.fun_jcfythb(this.CYFY_, this.ret_zyinfo.CRYXX_DRCYRC_, this.CYFY_YOY, this.ret_zyinfo.CRYXX_DRCYRC_YOY)
                this.jc_zysr_tb = this.jc_tmp;
                console.log("this.CYFY_YOY=="+this.CYFY_YOY+"    this.ret_zyinfo.CRYXX_DRCYRC_YOY="+this.ret_zyinfo.CRYXX_DRCYRC_YOY);
                console.log("this.jc_zysr_tb==="+this.jc_zysr_tb);

            }
        },  
        onChange(index) {
            this.ret_mrsr=[]
            //每日收入
            this.mrsr_info=[]
            //月总费用
            this.yzfy=0
            //日均费用
            this.ypjfy=0
            //收入
            this.sr_info={}
            //门诊收入
            this.sr_mzsr=0
            //住院收入
            this.sr_zysr=0

            this.MZFY_=0
            this.MZFY_MOM=0
            this.MZFY_MOMGROWTH=0
            this.MZFY_YOY=0
            this.MZFY_YOYGROWTH=0
            this.ZYFY_=0
            this.ZYFY_MOM=0
            this.ZYFY_MOMGROWTH=0
            this.ZYFY_YOY=0
            this.ZYFY_YOYGROWTH=0

            //材料占比
            this.ret_cl_info=[]
            this.clzb=0
            this.cltb=0
            this.clhb=0
            //药占比
            this.ret_yp_info=[]
            this.ypzb=0
            this.yptb=0
            this.yphb=0

            //出入院人次
            this.ret_zyrc=[]
            this.ryrc =0
            this.cyrc =0
            this.rjcyrc =0
            this.rjryrc =0

            //门诊人次
            this.ret_mzrc=[]
            this.mzrc=0
            this.mzrc_tb=0
            this.mzrc_hb=0
            
            //收治人次
            this.rc_szrc=0
            this.rc_szrc_tb=0
            this.rc_szrc_hb=0
            //日均门急诊人次
            // this.rc_rjszrc=0
            // this.rc_rjszrc_tb=0
            // this.rc_rjszrc_hb=0
            this.rc_rjmzrc=0
            this.rc_rjjzrc=0

            //门诊均次费用
            this.mz_jcfy=0
            this.jc_mzsr_tb=0
            this.jc_mzsr_hb=0
            //住院均次费用
            this.zy_jcfy=0
            this.jc_zysr_tb=0
            this.jc_zysr_hb=0

            this.MJZLRC_ = 0
            this.MJZLRC_MOM=0
            this.MJZLRC_MOMGROWTH=0
            this.MJZLRC_YOY= 0
            this.MJZLRC_YOYGROWTH= 0

            this.RYXX_JRRYRC_ = 0
            this.RYXX_JRRYRC_MOM=0
            this.RYXX_JRRYRC_MOMGROWTH=0
            this.RYXX_JRRYRC_YOY= 0
            this.RYXX_JRRYRC_YOYGROWTH= 0            
       
            if(index < 9){
                this.month = "0"+(index + 1)
            }else{
                this.month = index + 1
            }
            this.init()
        },
        fun_jcfythb(param1,param2,param3,param4){
            if(param1>0&&param2>0&&param3>0&&param4>0){
                this.jc_tmp = ((param1/param2)-(param3/param4))/(param3/param4)
            }else if(param1>0&&param2>0&&param3==0&&param4==0){
                this.jc_tmp = param1/param2
            }else{
                this.jc_tmp = 0
            }
        } ,  

    },
    mounted(){
        //this.fun_getXsys()
        this.init()
    }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  	@import '../sass/Common';
    .yb{
    //    height: 100%;
       
        padding-bottom: 0.49rem;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        background: $cF7F3F0;
        .title{
            height: 0.44rem;
            background: $cAF8760;
            color: $cffffff;
            font-size: 0.17rem;
            @include fcc;
            justify-content: flex-start;
            padding: 0 0.2rem;
            box-sizing: border-box;
            border-bottom: 0.01rem solid rgba($color: #000000, $alpha: 0.3);
        }
        .van-tabs{
            background: $cAF8760;

            .van-hairline--top-bottom::after, .van-hairline-unset--top-bottom::after{
                border: none;
            }
            .van-tab{
                

                @include fcc;
                color: $cffffff;
            }
            .van-tabs__wrap{
                padding:  0.15rem 0;
                height: 32px;
            }
            .van-tabs__nav{
                background: $cAF8760;
            }
            .van-tabs__nav--line{
                padding: 0;
            }
            .van-tabs__line{
                display: none;
            }
            .van-tab--active{
                background: $cffffff;
                color: $cAF8760;
                border-radius: 0.15rem;
            }
        }
        #myChart{
            width: 100%;
            height: 2.8rem;;
            background: $cAF8760;
        }
        .warp{
            flex: 1;
             overflow: auto;
            .d1{
                height: 0.8rem;
                @include fcc;
                background: $cffffff;
                .d1_1{
                    border-right: 0.01rem solid $cF7F3F0;
                }
                div{
                    flex: 1;
                    @include fmc;
                    height: 100%;
                    .p1{
                        font-size: 0.13rem;
                        color: $c6E6E6E;
                        padding:  0 0.15rem;
                        width: 100%;
                        box-sizing: border-box;
                    }
                    .p2{
                        padding:  0.07rem 0.15rem;
                        width: 100%;
                        box-sizing: border-box;
                        color: $cAE2C2E;
                        .s1{
                            font-size: 0.14rem;
                            font-weight: 600;
                        }
                        .s2{
                            font-size: 0.24rem;
                            font-weight: 600;
                            margin-left: 0.05rem;
                        }
                    }
                }
            }
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
                                color: $cAE2C2E;
                                font-size: 0.24rem;
                                font-weight: 600;
                            }
                        }
                        .s2{
                            font-size: 0.1rem;
                            color: $c6D473A;
                            width: auto;
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
               .li2{
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
                            padding-left:0.2rem;
                            font-size: 0.14rem;
                            color: $c6E6E6E;
                            font-weight: 600;
                        }                         
                       
                    }
                    .d2{
                        height: 0.2rem;;
                        width: 100%;
                        @include fcc;
                        .s1,.s2{
                            flex: 1;
                            padding-left: 0.2rem;
                            font-size: 0.24rem;
                            color: $c6E6E6E;
                            

                        }
                       
                       
                    }
                    .d3{
                        flex:1;
                        width: 100%;
                        @include fcc;
                        padding-bottom: 0.15rem;
                        margin-top: 0.05rem;
                        box-sizing: border-box;
                        span{
                            flex: 1;
                            // @include fcc;
                            padding-left: 0.2rem;
                            font-size: 0.13rem;
                            color: $c9E9D9C;
                        }

                    }
                }                
            }
        }
    }
</style>
