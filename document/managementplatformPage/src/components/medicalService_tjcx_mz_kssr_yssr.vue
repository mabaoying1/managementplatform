<template>
    <div class="medicalService_tjcx_mz_kssr_yssr">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick?'bingo':''" @click="tab(true)">门诊</div>
            <div class="d2" :class="!tab_pick?'bingo':''" @click="tab(false)">住院</div>
        </div>
        <div class="info">
            <div class="p1">{{info.date}}</div>
            <div class="p2">
                <span class="s1">¥</span>
                <span class="s2">{{sr|incisionLeft}}</span>
            </div>
            <div class="p3">
                <div class="d1">
                    <p class="p1">同比</p>
                    <p class="p2">{{sr_tb>0.01?sr_tb:0|leftTwoDecimal}} <i v-if="sr_tb>0" class="icon iconfont icon-uparrow"></i> <i v-if="sr_tb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
                <div class="d1 d2">
                     <p class="p1">环比</p>
                    <p class="p2">{{sr_hb>0.01?sr_hb:0|leftTwoDecimal}} <i v-if="sr_hb>0" class="icon iconfont icon-uparrow"></i> <i  v-if="sr_hb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
            </div>
        </div>
        <div class="list">
            <div class="head">
                <span class="s1">收入排序 <i class="icon iconfont icon-paixu" @click="fun_sort(sort)"></i></span>
                <span class="s2" @click="jump('medicalService_tjcx_mz_kssr_yssr_chart')">图表</span>
            </div>
            <div class="li" v-for="(item,index) in list">
                <div class="d1">{{index+1}}</div>                
                <div class="d2">
                    <p class="p1">{{item[0]}}</p>    
                    <p class="p2"><span>¥</span><span>{{item[1]|incisionLeft}}</span></p>
                </div>                
                <div class="d3">
                    <span class="s1">占比</span>   <span class="s2">{{item[2]|leftTwoDecimal}}</span>
                </div>                
            </div>

        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
export default {
    name: 'medicalService_tjcx_mz_kssr_yssr',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':sessionStorage.getItem('ksnames')+'医生收入',
                "title_black":'medicalService_tjcx_mz_kssr'
            },
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),
            tab_pick:true,
            info:{},

            i:0,
            sr:0,
            list:[],
            sr_mz:0,
            sr_zy:0,
            ret_list:{},
            list_mz:[],
            list_zy:[],
            sort:false,       
            ret_info:0,
            sr_tb:0,
            sr_hb:0, 

            MZFY_MOMGROWTH:0,
            MZFY_MOM:0,
            MZFY_YOYGROWTH:0,
            MZFY_YOY:0,
            ZYFY_MOMGROWTH:0,
            ZYFY_MOM:0,
            ZYFY_YOYGROWTH:0,
            ZYFY_YOY:0,   
        }
    },
    methods:{
        tab(t){
            this.sr = 0
            this.sr_tb = 0
            this.sr_hb = 0           
            this.list= []
            if(t){
                this.sr = this.sr_mz
                this.list = this.list_mz
                this.sr_tb = this.sr_mztb 
                this.sr_hb = this.sr_mzhb
            }else{
                this.sr = this.sr_zy
                this.list = this.list_zy
                this.sr_tb = this.sr_ZYtb 
                this.sr_hb = this.sr_ZYhb               
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
            //收入查询
			let data = {
                "themeID": this.CWorYW+"_ys_ywsrtj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,	
                "dimDept":this.ksids,                   		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_list = res.data 
            for(this.i = 0;this.i <this.ret_list.length;this.i++){
                if(Number(this.ret_list[this.i].MZSR_) > 0){
                    this.sr_mz = this.sr_mz + Number(this.ret_list[this.i].MZSR_)
                }
                if(Number(this.ret_list[this.i].ZYSR_) > 0){
                    this.sr_zy = this.sr_zy + Number(this.ret_list[this.i].ZYSR_)
                }             
            }
            for(this.i = 0;this.i <this.ret_list.length;this.i++){
                if(Number(this.ret_list[this.i].MZSR_) > 0){
                    this.list_mz.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].MZSR_),this.sr_mz>0?Number(this.ret_list[this.i].MZSR_)/ this.sr_mz:0 ])

                }
                if(Number(this.ret_list[this.i].ZYSR_) > 0){
                    this.list_zy.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].ZYSR_),this.sr_zy>0?Number(this.ret_list[this.i].ZYSR_)/ this.sr_zy:0 ])

                }
    
            }            
            this.sr = this.sr_mz
             //console.log(this.sr_mz)
            this.list = this.list_mz
            //console.log(this.list)
            this.list.sort(function(a,b){
                return a[1] - b[1];
            }); 
            
        },
        async init0(){

            //收入查询
			let data = {
                "themeID":this.CWorYW +"_zfy_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				//"startdate":"2017-01-01",
               // "enddate":"2019-01-01",	                	
                "dimDept":this.ksids,		
        	 }

            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_info = res.data
                for(this.i = 0;this.i <this.ret_info.length;this.i++){
                    this.sr_mzsr += Number( this.ret_info[this.i].MZFY_)
                    this.sr_zysr += Number( this.ret_info[this.i].ZYFY_)
                    this.MZFY_MOMGROWTH+= Number(this.ret_info[this.i].MZFY_MOMGROWTH)
                    this.MZFY_MOM+=Number(this.ret_info[this.i].MZFY_MOM)
                    this.MZFY_YOYGROWTH+=Number(this.ret_info[this.i].MZFY_YOYGROWTH)
                    this.MZFY_YOY+=Number(this.ret_info[this.i].MZFY_YOY)
                    this.ZYFY_MOMGROWTH+=Number(this.ret_info[this.i].ZYFY_MOMGROWTH)
                    this.ZYFY_MOM+=Number(this.ret_info[this.i].ZYFY_MOM)
                    this.ZYFY_YOYGROWTH+=Number(this.ret_info[this.i].ZYFY_YOYGROWTH)
                    this.ZYFY_YOY+=Number(this.ret_info[this.i].ZYFY_YOY)
                }
                this.sr_zfy =  this.sr_mzsr + this.sr_zysr
                if( this.MZFY_MOM!=0){
                    this.sr_mzhb = this.MZFY_MOMGROWTH / this.MZFY_MOM
                }
                if( this.MZFY_YOY!=0){
                    this.sr_mztb = this.MZFY_YOYGROWTH / this.MZFY_YOY
                }
                if( this.ZYFY_MOM!=0){
                    this.sr_zyhb = this.ZYFY_MOMGROWTH / this.ZYFY_MOM
                }
                if( this.ZYFY_YOY!=0){
                    this.sr_zytb = this.ZYFY_YOYGROWTH / this.ZYFY_YOY
                }               
            }
            this.sr_tb = this.sr_mztb 
            this.sr_hb = this.sr_mzhb


        },        
    },
    mounted(){
        this.init0()
        this.init()
        
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_tjcx_mz_kssr_yssr{
      //  height: 100%;
        display: flex;
        flex-direction: column;
        background: $cF7F3F0;

        .tab{
            padding: 0.25rem 0.7rem;
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
                .s1{
                    font-size: 0.28rem;
                    font-weight: 600;
                }
                .s2{
                    font-size: 0.38rem;
                    font-weight: 600;
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
