<template>
    <div class="medicalService_tjcx_mz_rc_ksrc">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick==0?'bingo':''" @click="tab(0)">挂号</div>
            <div class="d2" :class="tab_pick==1?'bingo':''" @click="tab(1)">收治</div>
            <div class="d2" :class="tab_pick==2?'bingo':''" @click="tab(2)">出院</div>
            <div class="d3" :class="tab_pick==3?'bingo':''" @click="tab(3)">手术</div>
        </div>
        <div class="info">
            <div class="p1">{{info.date}}</div>
            <div class="p2">
                <!-- <span class="s1">¥</span> -->
                <span class="s2">{{rc|incisionLeft}}</span>
                <span class="s3">人次</span>
            </div>
            <div class="p3">
                <div class="d1">
                    <p class="p1">同比</p>
                    <p class="p2">{{tb|leftTwoDecimal}} <i v-if="tb>0" class="icon iconfont icon-uparrow"></i> <i v-if="tb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
                <div class="d1 d2">
                     <p class="p1">环比</p>
                    <p class="p2">{{hb|leftTwoDecimal}} <i v-if="hb>0" class="icon iconfont icon-uparrow"></i> <i v-if="hb<0" class="icon iconfont icon-downarrow"></i></p>
                </div>
            </div>
        </div>
        <div class="list">
            <div class="head">
                <span class="s1">人次排序 <i class="icon iconfont icon-paixu"  @click="fun_sort(sort)"></i></span>
                <span class="s2" @click="jump('medicalService_tjcx_mz_rc_ysrc_charts')">图表</span>
            </div>
            <div class="li"  v-for="(item,index) in list">
                <div class="d1">{{index+1}}</div>                
                <div class="d2">
                    <p class="p1">{{item[0]}}</p>    
                    <p class="p2"><span></span><span>{{item[1]|incisionLeft}}</span></p>
                </div>                
                <div class="d3">
                    <span class="s1">占比</span><span class="s2">{{item[2]|leftTwoDecimal}}</span>
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
    name: 'medicalService_tjcx_mz_rc_ksrc',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':sessionStorage.getItem('ksnames')+'医生人次',
                "title_black":'medicalService_tjcx_mz_rc'
            },
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),      
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'),                  
            tab_pick:0,
            sort:false,
            info:{
                date:sessionStorage.getItem('startTime')+'-'+sessionStorage.getItem('endTime'),
            },
            i:0,
            rc:0,
            tb:0,
            hb:0,
            list:[],
            mz_rc:0,
            sz_rc:0,
            cy_rc:0,
            ss_rc:0,
            ret_list:{},
            list_mz:[],
            list_sz:[],
            list_cy:[],
            list_ss:[],

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
        }
    },
    methods:{
        
        tab(t){
            this.rc = 0
            this.list= []

            if(t=="0"){
                this.rc = this.rc_mzrc
                this.tb = this.rc_mzrc_tb
                this.hb = this.rc_mzrc_hb
                this.list = this.list_mz

            }else if(t=="1"){
                this.rc = this.rc_ryrc
                this.tb = this.rc_ryrc_tb
                this.hb = this.rc_ryrc_hb                
                this.list = this.list_sz
            }else if(t=="2"){
                this.rc = this.rc_cyrc
                this.tb = this.rc_cyrc_tb
                this.hb = this.rc_cyrc_hb                
                this.list = this.list_cy
            }else{
                this.rc = this.rc_ssrc
                this.tb = this.rc_ssrc_tb
                this.hb = this.rc_ssrc_hb                
                this.list = this.list_ss
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
                "themeID":this.CWorYW+"_ys_rctj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
                "dimDept":this.ksids,		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_list = res.data
            for(this.i = 0;this.i <this.ret_list.length;this.i++){
                if(Number(this.ret_list[this.i].MZRC_) > 0){
                    this.mz_rc = this.mz_rc + Number(this.ret_list[this.i].MZRC_)
                }
                if(Number(this.ret_list[this.i].RYRC_) > 0){
                    this.sz_rc = this.sz_rc + Number(this.ret_list[this.i].RYRC_)
                }
                if(Number(this.ret_list[this.i].CYRC_) > 0){
                    this.cy_rc = this.cy_rc + Number(this.ret_list[this.i].CYRC_)
                }
                if(Number(this.ret_list[this.i].SSRC_) > 0){
                    this.ss_rc = this.ss_rc + Number(this.ret_list[this.i].SSRC_)
                }                
            }
            for(this.i = 0;this.i <this.ret_list.length;this.i++){
                if(Number(this.ret_list[this.i].MZRC_) > 0){
                    this.list_mz.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].MZRC_),this.mz_rc>0?Number(this.ret_list[this.i].MZRC_)/ this.mz_rc:0 ])

                }
                if(Number(this.ret_list[this.i].RYRC_) > 0){
                    this.list_sz.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].RYRC_),this.sz_rc>0?Number(this.ret_list[this.i].RYRC_)/ this.sz_rc:0 ])

                }
                if(Number(this.ret_list[this.i].CYRC_) > 0){
                    this.list_cy.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].CYRC_),this.cy_rc>0?Number(this.ret_list[this.i].CYRC_)/ this.cy_rc:0 ])

                }
                if(Number(this.ret_list[this.i].SSRC_) > 0){
                    this.list_ss.push([this.ret_list[this.i].DOCTOR_Text ,Number(this.ret_list[this.i].SSRC_),this.ss_rc>0?Number(this.ret_list[this.i].SSRC_)/ this.ss_rc:0 ])         
                }    
            }            
            this.list = this.list_mz
            this.list.sort(function(a,b){
                return a[1] - b[1];
            });            
            //console.log(this.mz_rc)
            //console.log( this.list)
			let data2 = {
                "themeID":this.CWorYW+"_rc_rctj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
                "dimDept":this.ksids,		
        	 }
            let res2 = await http.get(api.getBIDataAll,data2)
            this.ret_rctj = res2.data
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
            
            this.rc_mzrc_hb = this.MZRC_MOM>0?this.MZRC_MOMGROWTH /this.MZRC_MOM:this.MZRC_MOMGROWTH
            this.rc_mzrc_tb = this.MZRC_YOY>0?this.MZRC_YOYGROWTH /this.MZRC_YOY:this.MZRC_YOYGROWTH
            this.rc_ryrc_hb = this.RYRC_MOM>0?this.RYRC_MOMGROWTH /this.RYRC_MOM:this.RYRC_MOMGROWTH
            this.rc_ryrc_tb = this.RYRC_YOY>0?this.RYRC_YOYGROWTH /this.RYRC_YOY:this.RYRC_YOYGROWTH
            this.rc_cyrc_hb = this.CYRC_MOM>0?this.CYRC_MOMGROWTH /this.CYRC_MOM:this.CYRC_MOMGROWTH
            this.rc_cyrc_tb = this.CYRC_YOY>0?this.CYRC_YOYGROWTH /this.CYRC_YOY:this.CYRC_YOYGROWTH
            this.rc_ssrc_hb = this.SSRC_MOM>0?this.SSRC_MOMGROWTH /this.SSRC_MOM:this.SSRC_MOMGROWTH
            this.rc_ssrc_tb = this.SSRC_YOY>0?this.SSRC_YOYGROWTH /this.SSRC_YOY:this.SSRC_YOYGROWTH

            this.rc = this.rc_mzrc
            this.tb = this.rc_mzrc_tb
            this.hb = this.rc_mzrc_hb 

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
    .medicalService_tjcx_mz_rc_ksrc{
        height: auto;
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
