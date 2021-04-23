<template>
    <div class="medicalService_rb_ysap">
        <commonHead :title = "title"></commonHead>
        <div class="tab">
            <div class="d1" :class="tab_pick?'bingo':''" @click="tab(true)">上午</div>
            <div class="d2" :class="!tab_pick?'bingo':''" @click="tab(false)">下午</div>
        </div>
        <div class="main">
            <div class="li" v-for="item in list">
                <div class="d1">
                    <p class="p1">{{item.DOCTOR_Text}}</p>
                    <p class="p2">{{item.DEPT_Text}}</p>
                </div>
                <div class="d2">
                    <p class="p1">{{item.YH}}/{{item.ZHS}}</p>
                    <p class="p2">余/总号源</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import common_head from "./common_head.vue";
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment' //格式化时间
export default {
    name: 'medicalService_rb_ysap',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':`医生排班`,
                "title_black":'medicalService_rb'
            },
            tab_pick:true,
            list:[
                {
                    doct:'柳青青',
                    dp:'内科',
                    hy:'12/50',
                },

            ]
            
        }
    },
    methods:{
        tab(t){
            this.tab_pick = t
            if(t){
                this.initSw()
            } else{
                this.initXw()
            }                   
        },
        //上午医生排班
        async initSw(){
			let data = {
                "themeID":"index_mzgh_sw",
			//	"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
                // "startdate":"2018-08-01",
                // "enddate":"2018-08-02",                
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	,                				
        	 }     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data != null){
			    this.list =res.data   
            }
       
        },
        //下午医生排班
        async initXw(){
			let data = {
                "themeID":"index_mzgh_xw",
               // "startdate":"2018-08-01",
              //  "enddate":"2018-08-02",	
			//	"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	,                			
        	 }     
			let res = await http.get(api.getBIDataAll,data)
            if(res.data != null){
			    this.list =res.data   
            }        
        },        
        
    },
    mounted(){
        this.initSw()
        
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_rb_ysap{
        height: 100%;
        display: flex;
        flex-direction: column;
        background: $cF7F3F0;

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
        .main{
            flex: 1;
            overflow: auto;
            .li{
                height: 0.8rem;
                margin: 0 0.15rem 0.12rem 0.15rem;
                background: $cffffff;
                box-sizing: border-box;
                @include fcc;
                
                .d1{
                    flex: 1;
                    padding-left: 0.2rem;
                    .p1{
                        font-size: 0.17rem;
                        color: $c6E6E6E;
                        margin-bottom: 0.05rem;
                    }
                    .p2{
                        font-size: 0.13rem;
                        color: $c9E9D9C;
                    }

                }
                .d2{
                    width: 1rem;
                    @include fmc;
                    background: $cAF8760;
                    height: 100%;
                    color: $cffffff;
                    .p1{
                        font-size: 0.17rem;
                    }
                    .p2{
                        font-size: 0.13rem;
                    }

                }
            }
        }
    }
    
</style>
