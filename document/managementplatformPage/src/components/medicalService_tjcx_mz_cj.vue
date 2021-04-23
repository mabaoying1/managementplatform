<template>
    <div class="medicalService_tjcx_mz_cj">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick==0?'bingo':''" @click="tab(0)">门诊</div>
            <div class="d2" :class="tab_pick==1?'bingo':''" @click="tab(1)">住院</div>
            <div class="d2" :class="tab_pick==2?'bingo':''" @click="tab(2)">手术</div>
        </div>
        <div class="info">
            <div class="p1">{{info.date}}</div>
            <div class="p2">
                <!-- <span class="s1">¥</span> -->
                <span class="s2">{{jc_sr|incisionLeft}}</span>
                <!-- <span class="s3">人/次</span> -->
            </div>
            <div class="p3">
                <div class="d1">
                    <p class="p1">同比</p>
                    <p class="p2">{{jc_tb|leftTwoDecimal}} <i v-if="jc_tb>0" class="icon iconfont icon-uparrow"></i> <i v-if="jc_tb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
                <div class="d1 d2">
                     <p class="p1">环比</p>
                    <p class="p2">{{jc_hb|leftTwoDecimal}} <i v-if="jc_hb>0" class="icon iconfont icon-uparrow"></i> <i v-if="jc_hb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
            </div>
        </div>
        <div class="list">
             <div class="head">
                <span class="s1">均次收入排序 <i class="icon iconfont icon-paixu" @click="fun_sort(sort)"></i></span>
                <span class="s2" @click="jump('medicalService_tjcx_mz_cj_chart')">图表</span>
            </div>
            <div class="li" v-for="(item,index) in list">
                <div class="d1">{{index+1}}</div>                
                <div class="d2">
                    <p class="p1">{{item[0]}}</p>    
                    <p class="p2"><span>¥</span><span>{{item[1]|incisionLeft}}</span></p>
                </div>                
                <!-- <div class="d3">
                    <span class="s1">占比</span>   <span class="s2">{{item[2]|leftTwoDecimal}}</span>
                </div>                 -->
            </div>

        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'medicalService_tjcx_mz_cj',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':sessionStorage.getItem('ksnames')+'医生均次收入',
                "title_black":'medicalService_tjcx_mz'
            },
            tab_pick:0,
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),  
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),                      
            info:{
                date:sessionStorage.getItem('startTime')+'-'+sessionStorage.getItem('endTime'),


            },
            ret_ysinfo:[],
            i:0,
            jcfy:0,
            list:[],
            jc_mz:0,
            jc_zy:0,
            jc_ss:0,
            list_mz:[],
            list_zy:[],
            list_ss:[],
            sort:false,    
            
            ret_jcinfo:[],
            jc_mzsr:0,
            jc_mzrc:0,
            jc_mzsr_mom:0,
            jc_mzrc_mom:0,
            jc_mzsr_yoy:0,
            jc_mzrc_yoy:0,
            jc_mzjc:0,

            jc_zysr:0,
            jc_zyrc:0,
            jc_zysr_mom:0,
            jc_zyrc_mom:0,
            jc_zysr_yoy:0,
            jc_zyrc_yoy:0,                        
            jc_zyjc:0,

            jc_sssr:0,
            jc_ssrc:0,
            jc_sssr_mom:0,
            jc_ssrc_mom:0,
            jc_sssr_yoy:0,
            jc_ssrc_yoy:0,                        
            jc_ssjc:0,

            jc_mzjc_tb:0,
            jc_mzjc_hb:0,
            jc_zyjc_tb:0,
            jc_zyjc_hb:0,
            jc_ssjc_tb:0,
            jc_ssjc_hb:0,
            
            jc_sr:0,
            jc_tb:0,
            jc_hb:0,
            jc_tmp:0,
        }
    },
    methods:{
        tab(t){
            this.jc_sr = 0
            this.jc_tb = 0
            this.jc_hb = 0           
            this.list= []
            if(t==0){
                this.list = this.list_mz
                this.jc_sr = this.jc_mzjc
                this.jc_tb = this.jc_mzjc_tb
                this.jc_hb = this.jc_mzjc_hb
            }else if(t==1){
                this.list = this.list_zy
                this.jc_sr = this.jc_zyjc
                this.jc_tb = this.jc_zyjc_tb
                this.jc_hb = this.jc_zyjc_hb                
            }else{
                this.list = this.list_ss   
                this.jc_sr = this.jc_ssjc 
                this.jc_tb = this.jc_ssjc_tb
                this.jc_hb = this.jc_ssjc_hb                         
            }
            this.list.sort(function(a,b){
                    return a[1] - b[1];
            });             
            this.tab_pick = t
           
        },
        fun_sort(t){
            if(t){
                this.list.sort(function(a,b){
                    return a[1] - b[1];
                });   
               this.sort = false
            }else{
                this.list.sort(function(a,b){
                    return b[1] - a[1];
                });   
               this.sort = true
            }
            
        }, 
        
        async init(){
			let data = {
                "themeID":this.CWorYW +"_ys_sssrtj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				// "startdate":"2017-01-01",
                // "enddate":"2019-01-01",	                	
                "dimDept":this.ksids,		
        	 }

            let res = await http.get(api.getBIDataAll,data)
            this.ret_ysinfo = res.data

           for(this.i = 0;this.i <this.ret_ysinfo.length;this.i++){
                if(Number(this.ret_ysinfo[this.i].MZRC_) > 0){
                    this.list_mz.push([this.ret_ysinfo[this.i].DOCTOR_Text ,Number(this.ret_ysinfo[this.i].MZSR_)/Number(this.ret_ysinfo[this.i].MZRC_)])
                }
                if(Number(this.ret_ysinfo[this.i].ZYRC_) > 0){
                    this.list_zy.push([this.ret_ysinfo[this.i].DOCTOR_Text ,Number(this.ret_ysinfo[this.i].ZYSR_)/Number(this.ret_ysinfo[this.i].ZYRC_) ])
                }
                if(Number(this.ret_ysinfo[this.i].SSRC_) > 0){
                    this.list_zy.push([this.ret_ysinfo[this.i].DOCTOR_Text ,Number(this.ret_ysinfo[this.i].SSSR_)/Number(this.ret_ysinfo[this.i].SSRC_) ])
                }    
            }            
            this.list = this.list_mz
           // console.log(this.list)
            this.list.sort(function(a,b){
                return a[1] - b[1];
            });             
        },
        //均次
        async initJcfy(){
			let data = {
                "themeID":this.CWorYW +"_ys_sssrzb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				// "startdate":"2017-01-01",
                // "enddate":"2019-01-01",	                	
                "dimDept":this.ksids,		
        	 }

            let res = await http.get(api.getBIDataAll,data)   
            this.ret_jcinfo = res.data
             for(this.i = 0;this.i <this.ret_jcinfo.length;this.i++){
                this.jc_mzsr = this.jc_mzsr + Number(this.ret_jcinfo[this.i].MZSR_)
                this.jc_mzrc = this.jc_mzrc + Number(this.ret_jcinfo[this.i].MZRC_)
                this.jc_mzsr_mom = this.jc_mzsr_mom + Number(this.ret_jcinfo[this.i].MZSR_MOM)
                this.jc_mzrc_mom = this.jc_mzrc_mom + Number(this.ret_jcinfo[this.i].MZRC_MOM)
                this.jc_mzsr_yoy = this.jc_mzsr_yoy + Number(this.ret_jcinfo[this.i].MZSR_YOY)
                this.jc_mzrc_yoy = this.jc_mzrc_yoy + Number(this.ret_jcinfo[this.i].MZRC_YOY)
                this.jc_zysr = this.jc_zysr + Number(this.ret_jcinfo[this.i].ZYSR_)
                this.jc_zyrc = this.jc_zyrc + Number(this.ret_jcinfo[this.i].ZYRC_)
                this.jc_zysr_mom = this.jc_zysr_mom + Number(this.ret_jcinfo[this.i].ZYSR_MOM)
                this.jc_zyrc_mom = this.jc_zyrc_mom + Number(this.ret_jcinfo[this.i].ZYRC_MOM)
                this.jc_zysr_yoy = this.jc_zysr_yoy + Number(this.ret_jcinfo[this.i].ZYSR_YOY)
                this.jc_zyrc_yoy = this.jc_zyrc_yoy + Number(this.ret_jcinfo[this.i].ZYRC_YOY)                                
                this.jc_sssr = this.jc_sssr + Number(this.ret_jcinfo[this.i].SSSR_)
                this.jc_ssrc = this.jc_ssrc + Number(this.ret_jcinfo[this.i].SSRC_)
                this.jc_sssr_mom = this.jc_sssr_mom + Number(this.ret_jcinfo[this.i].SSSR_MOM)
                this.jc_ssrc_mom = this.jc_ssrc_mom + Number(this.ret_jcinfo[this.i].SSRC_MOM)
                this.jc_sssr_yoy = this.jc_sssr_yoy + Number(this.ret_jcinfo[this.i].SSSR_YOY)
                this.jc_ssrc_yoy = this.jc_ssrc_yoy + Number(this.ret_jcinfo[this.i].SSRC_YOY)                                
            }


            this.jc_mzjc = this.jc_mzrc >0? (this.jc_mzsr /this.jc_mzrc).toFixed(2):0
            this.jc_zyjc = this.jc_zyrc >0? (this.jc_zysr /this.jc_zyrc).toFixed(2):0
            this.jc_ssjc = this.jc_ssrc >0? (this.jc_ssrc /this.jc_ssrc).toFixed(2):0


            this.fun_jcfythb(this.jc_mzsr,this.jc_mzrc,this.jc_mzsr_mom,this.jc_mzrc_mom)
            this.jc_mzjc_hb = this.jc_tmp
            this.fun_jcfythb(this.jc_mzsr,this.jc_mzrc,this.jc_mzsr_yoy,this.jc_mzrc_yoy)
            this.jc_mzjc_tb = this.jc_tmp
            this.fun_jcfythb(this.jc_zysr,this.jc_zyrc,this.jc_zysr_mom,this.jc_zyrc_mom)
            this.jc_zyjc_hb = this.jc_tmp
            this.fun_jcfythb(this.jc_zysr,this.jc_zyrc,this.jc_zysr_yoy,this.jc_zyrc_yoy)
            this.jc_zyjc_tb = this.jc_tmp
            this.fun_jcfythb(this.jc_sssr,this.jc_ssrc,this.jc_sssr_mom,this.jc_ssrc_mom)
            this.jc_ssjc_hb = this.jc_tmp
            this.fun_jcfythb(this.jc_sssr,this.jc_ssrc,this.jc_sssr_yoy,this.jc_ssrc_yoy)
            this.jc_ssjc_tb = this.jc_tmp

            this.jc_sr = this.jc_mzjc
            this.jc_tb = this.jc_mzjc_tb
            this.jc_hb = this.jc_mzjc_hb

        },   
        fun_jcfythb(param1,param2,param3,param4){
            if(param1>0&&param2>0&&param3>0&&param4>0){
                this.jc_tmp = ((param1/param2)-(param3/param4))/(param3/param4)
            }else{
                this.jc_tmp = 0
            }

           //console.log(this.jc_tmp)
        } ,          
    },
    mounted(){
        this.init()
        this.initJcfy()
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_cj{
        //height: 100%;
        display: flex;
        flex-direction: column;
        background: $cF7F3F0;

        .tab{
            padding: 0.24rem 0.16rem;
            height: 0.3rem;;
            @include fcc;
            color: $cffffff;
            background: $cAF8760;
            .d1{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $cffffff;
                @include fcc;
                border-radius: 0.03rem 0 0 0.03rem;
            }
            .d2{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $cffffff;
                @include fcc;
                // border-radius:0 0.03rem 0.03rem 0;
                
            }
            .d3{
                height: 0.3rem;
                flex: 1;
                border: 0.01rem solid $cffffff;
                @include fcc;
                border-radius:0 0.03rem 0.03rem 0;
            }
        }
        .bingo{
            background: $cffffff;
            color: $c8B5016;
        }
        .info{
            height: 1.6rem;
            background: $cAF8760;
            padding: 0 0.37rem;
            display: flex;
            flex-direction: column;
            .p1{
                color: $cD7C3AC;
                font-size: 0.12rem;
            }
            .p2{
                color: $cffffff;
                width: 100%;
                @include fcs;
                .s1{
                    
                    font-size: 0.28rem;
                    font-weight: 600;
                }
                .s2{
                    flex: 1;
                    // width: 70%;
                    font-size: 0.38rem;
                    font-weight: 600;
                }
                .s3{
                    // width: 30%;
                    margin-left: 0.2rem;
                    height: 0.53rem;
                    color: $cD7C3AC;
                    font-size: 0.17rem;
                    display: flex;
                    justify-content: flex-end;
                    align-items: flex-end;

                }
            }
            .p3{
                @include fcc;
                width: 100%;
                flex: 1;
                
                div{
                    flex: 1;
                    @include fcc;
                    height: 0.4rem;
                    
                }
                .d1{
                    display: flex;
                    flex-direction: column;
                    i{
                        font-size: 0.14rem;
                    }
                    .p1{
                        width: 100%;
                        font-size: 0.13rem;
                        @include fcc

                    }
                    .p2{
                        width: 100%;
                        font-size: 0.16rem;
                        @include fcc
                    }
                }
                .d2{
                    border-left: 0.01rem solid $cF7F3F0;
                }
            }
            
        }
        .head{
            padding: 0 0.15rem;
            height: 0.5rem;
            @include fcs;
            .s1{
                font-size: 0.16rem;
                color: $c6E6E6E;
            }
            .s2{
                font-size: 0.1rem;
                background: $c8B5016;
                color: $cffffff;
                height: 0.2rem;
                width: 0.556rem;
                @include fcc;
                border-radius: 0.15rem;
            }
        }
        .li{
            background: $cffffff;
            height: 0.72rem;
            @include fcs;
            padding: 0 0.2rem;
            margin-bottom: 0.01rem;
            .d1{
                height:0.3rem;
                width: 0.3rem;
                border-radius: 50%;
                background: $cF7F3F0;
                color: $cAF8760;
                @include fcc;

            }
            .d2{
                flex: 1;
                margin-left: 0.2rem;
                .p1{
                    color: $c6E6E6E;
                    font-size: 0.17rem;
                }
                .p2{
                    color: $c9E9D9C;
                    font-size: 0.16rem;
                }
            }
            .d3{
                padding: 0 0.15rem;
                background: $cD20A02;
                color: $cffffff;
                border-radius: 0.1rem;
                .s1{
                    font-size: 0.1rem;
                }
                .s2{
                    font-size: 0.12rem;
                }
            }
            .d4{
                padding: 0 0.15rem;
                background: $c009241;
                color: $cffffff;
                border-radius: 0.1rem;
                .s1{
                    font-size: 0.1rem;
                    i{
                        font-size: 0.14rem;
                    }
                }
                .s2{
                    font-size: 0.12rem;
                }
            }
        }
        
    }
    
</style>
