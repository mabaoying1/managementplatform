<template>
    <div class="medicalService_rb_ssap">
        <commonHead :title = "title"></commonHead>
        <div class="h">今日共计<span>{{size}}</span>台手术</div>
        <div class="warp">
            <div class="li" v-for="item in list">
                <div class="d1">{{item.SSMC}}</div>
                <div class="d2"> 
                    <span class="s1">{{item.SSSJ}}</span>
                    <span class="s2">{{item.DEPT_Text}}</span>
                </div>
                <div class="d3">
                    <span class="s1">{{item.PATIENTNAME}}&nbsp;&nbsp;{{item.PATIENTSEX}}&nbsp;&nbsp;{{item.PATIENTAGE}}</span>
                    <span class="s2"><span>医生:{{item.DOCTOR_Text}}</span></span>
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
    name: 'medicalService_rb_ssap',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':'手术安排',
                'title_black':'medicalService_rb'
            },
            info:{

            },
            list:[
                // {
                //     title:"我是手术名称",
                //     date:"09:00",
                //     doct:'医生甲（主刀',
                //     sex:'男',
                //     name:'周杰伦',
                //     age:27,
                //     bq:'A病区'
                // }
            ],
            size:0
        }
    },
    methods:{
		async index_sfap(){
			let data = {
                "themeID":"index_sfap",
			//	"startdate":localStorage.getItem("today"),
			//	"enddate":localStorage.getItem("today"),                
               // "startdate":"2018-08-24",
                //"enddate":"2018-08-24",	  
				 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")	,                              
        	 }
     
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.list =res.data
                this.size= this.list.length
            }

		}
    },
    mounted(){
        this.index_sfap()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_rb_ssap{
        height: 100%;
        background: $cF7F3F0;
        display: flex;
        flex-direction: column;
        .h{
            height: 0.5rem;
            background: $cD7C3AC;
            @include fcc;
            color:$c6D473A;
            font-size: 0.17rem;
            font-weight: 600;

            span{
                margin: 0 0.1rem;
                font-weight: 600;
            }
        }
        .warp{
            flex: 1;
            overflow: auto;
            padding-top: 0.09rem;
            padding-bottom: 0.09rem;
            .li{
                display: flex;
                flex-direction: column;
                margin: 0.12rem 0.15rem 0 0.15rem;
                height:1.2rem;
                background: $cffffff;
                padding: 0.15rem 0.2rem;
                box-sizing: border-box;
                .d1{
                    font-weight: 550;
                    font-size: 0.15rem;
                    color: $c6E6E6E;
                }
                .d2{
                    flex: 1;
                    @include fcs;
                    span{
                        flex: 1;
                    }
                    .s1{
                        font-weight: 550;
                        font-size: 0.22rem;
                        color: $c8B5016;
                    }
                    .s2{
                        font-weight: 550;
                        font-size: 0.13rem;
                        color: $c6E6E6E;
                    }
                }
                .d3{
                    @include fcs;
                    .s1{
                        flex: 1;
                        font-weight: 550;
                        font-size: 0.13rem;
                        color: $c9E9D9C;
                    }
                    .s2{
                        flex: 1;
                        span{
                            width: auto;
                            height: 0.2rem;
                            border-radius: 0.1rem;
                            background: $cEFE7DF;
                            padding:  0 0.1rem;
                            font-size: 0.1rem;
                            color: $cAF8760;

                        }
                    }
                }
            }
        }
    }
</style>
