<template>
    <div class="medicalService_tjcx_mz_kssr">
        <commonHead :title = "title"></commonHead>
        <div class="ks ">
            <span class="s1 van-ellipsis">{{this.ksnames}}</span>
            <i class="icon iconfont icon-xiayiji"></i>
        </div>
        <div class="w">
            <div class="card">
           
                <div class="d1">
                    <span class="s1">{{this.startTime_show}}-{{this.endTime_show}}</span>
                    
                </div>
                <div class="d2">
                    <p class="p1">{{this.sr_zfy|incisionLeft}}</p>
                    <p class="p2">当前科室总收入</p>
                </div>
                <div class="d3">
                   <span class="s2" @click="jump('medicalService_tjcx_mz_kssr_yssr')">查看当前科室医生 <i class="icon iconfont icon-xiayiji"></i></span>
                </div>
            </div> 
        </div>
        <div class="li">
            <div class="d1">同</div>
            <div class="d2">
                <p class="p1">{{this.sr_zfytb|leftTwoDecimal}}</p>
                <p class="p2">同比去年</p>
            </div>
            <div class="d3">
                <span v-if="this.sr_zfytb > 0">增长</span>
                <img  v-if="this.sr_zfytb > 0" src="../assets/9.png" alt="升"/>
                <span v-if="this.sr_zfytb < 0" >下降</span>
                <img  v-if="this.sr_zfytb < 0" src="../assets/8.png" alt="降"/>
            </div>
            
        </div>  
        <div class="li li2">
            <div class="d1">环</div>
            <div class="d2">
                <p class="p1">{{this.sr_zfyhb|leftTwoDecimal}}</p>
                <p class="p2">环比上月</p>
            </div>
            <div class="d3">
                <span v-if="this.sr_zfyhb > 0">增长</span>
                <img  v-if="this.sr_zfyhb > 0" src="../assets/9.png" alt="升"/>
                <span v-else-if="this.sr_zfyhb < 0">下降</span>
                <img  v-else-if="this.sr_zfyhb < 0" src="../assets/8.png" alt="降"/>
            </div>
		    
            
        </div>  
        <commonTitle2 :title="'当前科室门诊与住院收入'" :pageName="'详情'" :pageUrl="'medicalService_tjcx_mz_kssr_chart'"></commonTitle2>
        <div class="info">
            <div class="d1">
                <p class="p1">
                    <span class="s1">¥</span>
                    <span class="s2">{{this.sr_mzsr|incisionLeft}}</span>
                </p>
                <p class="p2">门诊收入</p>
                <p class="p3">{{this.sr_mzbfb|leftTwoDecimal}}</p>
            </div>
            <div class="d2">
                <p class="p1">
                    <span class="s1">¥</span>
                    <span class="s2">{{this.sr_zysr|incisionLeft}}</span>
                </p>
                <p class="p2">住院收入</p>
                <p class="p3">{{this.sr_zybfb|leftTwoDecimal}}</p>
            </div>
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
                'title':'科室收入情况',
                "title_black":'medicalService_tjcx_mz'
            }, 
            info:{
            },
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
            ksids : sessionStorage.getItem('ksids'),
            ksnames : sessionStorage.getItem('ksnames'), 
            i:0,   
            //收入
            ret_info:{},
            sr_zfy:0,
            sr_zfytb:0,
            sr_zfyhb:0,
            sr_mzsr:0,
            sr_zysr:0,
			sr_mztb:0,
            sr_mzhb:0,
            sr_mzbfb:0,
			sr_zytb:0,
            sr_zyhb:0,
            sr_zybfb:0,
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
        async init(){

            //收入查询
			let data = {
                "themeID":this.CWorYW +"_zfy_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				//"startdate":"2017-01-01",
                //"enddate":"2019-01-01",	                	
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
                    this.sr_mzhb = this.MZFY_MOM>0? this.MZFY_MOMGROWTH / this.MZFY_MOM: this.MZFY_MOMRATE
                    this.sr_mztb = this.MZFY_YOY>0? this.MZFY_YOYGROWTH / this.MZFY_YOY: this.MZFY_MOMRATE      
                    this.sr_zyhb = this.ZYFY_MOM>0?this.ZYFY_MOMGROWTH / this.ZYFY_MOM : this.MZFY_MOMRATE              
                    this.sr_zytb =  this.ZYFY_YOY>0?this.ZYFY_YOYGROWTH / this.ZYFY_YOY: this.MZFY_MOMRATE
                          
                 //门诊住院费用同环比         
                if((Number(this.MZFY_MOM) + Number(this.ZYFY_MOM))!=0) {
                    this.sr_zfyhb =(Number(this.MZFY_MOMGROWTH) + Number(this.ZYFY_MOMGROWTH)) / (Number(this.MZFY_MOM) + Number(this.ZYFY_MOM))
                }             
                 if((Number(this.MZFY_YOY) + Number(this.ZYFY_YOY))!=0) {
                    this.sr_zfytb =(Number(this.MZFY_YOYGROWTH) + Number(this.ZYFY_YOYGROWTH)) / (Number(this.MZFY_YOY) + Number(this.ZYFY_YOY))
                }   
              
                if(this.sr_zfy!=0){
                    this.sr_mzbfb = this.sr_mzsr /  this.sr_zfy
                    this.sr_zybfb = this.sr_zysr /  this.sr_zfy
                }

            }



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
    .medicalService_tjcx_mz_kssr{
        height:calc(100% );
        font-size: .14rem;
        display: flex;
        flex-direction: column;
        .ks{
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

                background-image: url('../assets/bg4.png') ;
                // background-size:cover;
                background-repeat:no-repeat;
                background-size:100% 100%;
                
                .d1{
                    
                    // height: 0.6rem;
                    @include fcs;
                    padding: 0 0.22rem;
                    padding-top: 0.3rem;
                    .s1{
                        font-size: 0.12rem;
                        color: $cD7C3AC;
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
            }
            margin-bottom: 0.01rem;
        }
        .li{
            height: 0.64rem;
            
            border-top: 0.01rem solid $cF7F3F0;
            @include fcs;
            padding: 0 0.15rem;
            .d1{
                height: 0.4rem;
                width: 0.4rem;
                background: $cAF8760;
                @include fcc;
                border-radius: 50%;
                color: $cffffff;
                font-size: 0.18rem;
            }
            .d2{
                flex: 1;
                margin-left: 0.3rem;
                height: 100%;
                @include fmc;
                align-items: flex-start;
                .p1{
                    font-size: 0.16rem;
                    color: $c6E6E6E;
                }
                .p2{
                    font-size: 0.13rem;
                    color: $c9E9D9C;
                }
            }
            .d3{
                font-size: 0.13rem;
                color: $c9E9D9C;
                @include fcc;
                img{
                    margin-left: 0.05rem;
                    width: 0.1rem;
                    height: 0.14rem;
                }
            }


        }
        .li2{
            border-top: 0.01rem solid $cF7F3F0;
            border-bottom: 0.1rem solid $cF7F3F0;
        }
        .common_title2{
            background: $cffffff;
        }
        .info{
            @include fcs;
            justify-content: space-around;
            div{
                height: 1rem;
                width: 1.7rem;
                border-radius: 0.1rem;
                padding: 0 0.15rem;
                box-sizing: border-box;
                .p1{
                    margin-top: 0.12rem;
                    display: flex;
                    align-items: flex-start;
                    .s1{
                        font-size: 0.14rem;
                        color: $cffffff;
                        font-weight: 600;
                    }
                    .s2{
                        font-size: 0.24rem;
                        color: $cffffff;
                        font-weight: 600;

                    }
                }
                .p2{
                    font-size: 0.14rem;
                    color: $cffffff;
                    font-weight: 600;

                }
                .p3{
                    text-align: right;
                    font-size: 0.16rem;
                    color: $cffffff;
                    font-weight: 600;
                }
            }
            .d1{
                background: $cC56535;

            }
            .d2{
                background: $c55789E;
            }
        }

    }

</style>
