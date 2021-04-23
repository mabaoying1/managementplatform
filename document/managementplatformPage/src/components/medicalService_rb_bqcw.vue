<template>
  <div class="medicalService_rb_bqxx">
    <commonHead :title = "title"></commonHead>
    <div class="table">
        <div id="left_div">
            <div id="left_div1">
                <table id="left_table1">
                    <tr>
                        <td>科室</td>
                    </tr>
                     <tr style="background:#AF8760">
                            <td>病区合计</td>
                        </tr>
                </table>
            </div>
            <div id="left_div2">
                <table id="left_table2">
                    <tbody>
                        <tr v-for="itme in ret_info" >
                            <td class="td1">{{itme.OFFICE_Text}}</td>
                        </tr>
                    </tbody>
                    <!-- <tfoot>
                        <tr>
                            <td>病区合计</td>
                        </tr>
                    </tfoot>  -->
                </table>
            </div>
        </div>
        <div id="right_div">
            <div id="right_div1">
                <div id="right_divx">
                    <table id="right_table1">
                        <tr>
                            <td>开放床位</td>
                            <td>在院</td>
                            <td>入院</td>
                            <td>出院</td>
                            <td>危重</td>
                        </tr>
                          <tr style="background:#AF8760;">
                            <td>{{total.RATEDBED}}</td>
                            <td>{{total.INHOS}}</td>
                            <td>{{total.BEHOS}}</td>
                            <td>{{total.LEAVEHOS}}</td>
                            <td>{{total.CRITICAL}}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="right_div2">
                <table id="right_table2">
                    <tbody>
                        <tr v-for="itme in ret_info" >
                            <td>{{itme.INDEX_CRYXX_CWXX_}}</td>
                            <td>{{itme.INDEX_CRYXX_DRZYRC_}}</td>
                            <td>{{itme.INDEX_CRYXX_JRRYRC_}}</td>
                            <td>{{itme.INDEX_CRYXX_DRCYRC_}}</td>
                            <td>{{itme.INDEX_CRYXX_ZYWZRC_}}</td>
                        </tr>
                    </tbody>
                    <!-- <tfoot>
                        <tr>
                            <td>{{total.RATEDBED}}</td>
                            <td>{{total.INHOS}}</td>
                            <td>{{total.BEHOS}}</td>
                            <td>{{total.LEAVEHOS}}</td>
                            <td>{{total.CRITICAL}}</td>
                        </tr>
                        
                    </tfoot> -->
                </table>
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
    name: 'medicalService_rb_bqxx',
    components:{
        "commonHead":common_head,
    },
    data () {
        return {
            title:{
                'title':'病区床位',
                "title_black":'medicalService_rb'
            },
            data:[
                {   
                    DEPTNAME:1,
                    BEHOS:0,
                    LEAVEHOS:0,
                    RATEDBED:0,
                    INHOS:0,
                    CRITICAL:0
                },{
                    DEPTNAME:2,
                    BEHOS:0,
                    LEAVEHOS:0,
                    RATEDBED:0,
                    INHOS:0,
                    CRITICAL:0
                },{
                    DEPTNAME:2,
                    BEHOS:0,
                    LEAVEHOS:0,
                    RATEDBED:0,
                    INHOS:0,
                    CRITICAL:0
                },
            ],
            total:{
                BEHOS:0,
                LEAVEHOS:0,
                RATEDBED:0,
                INHOS:0,
                CRITICAL:0
            },

           // bedUseRate:sessionStorage.getItem('bedUseRate')//床位使用率
           ret_info:[],
           i:0, 
            
        }
    },
    methods:{
        async init(){
			let data = {
                "themeID":"index_cryxx_office",
				//"startdate":localStorage.getItem("today"),
				//"enddate":localStorage.getItem("today"),                
				// "startdate":"2017-01-01",
                // "enddate":"2017-01-01",
                 "startdate":moment().format("YYYY-MM-DD"),
				 "enddate":moment().format("YYYY-MM-DD")				
        	}
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_info = res.data
                console.log( res.data);
                for(this.i=0;this.i<this.ret_info.length;this.i++){
                    this.total.RATEDBED = this.total.RATEDBED+Number(this.ret_info[this.i].INDEX_CRYXX_CWXX_)
                    this.total.INHOS = this.total.INHOS+Number(this.ret_info[this.i].INDEX_CRYXX_DRZYRC_)
                    this.total.BEHOS = this.total.BEHOS+Number(this.ret_info[this.i].INDEX_CRYXX_JRRYRC_)
                    this.total.LEAVEHOS = this.total.LEAVEHOS+Number(this.ret_info[this.i].INDEX_CRYXX_DRCYRC_)
                    this.total.CRITICAL = this.total.CRITICAL+Number(this.ret_info[this.i].INDEX_CRYXX_ZYWZRC_)
                }
            }
            this.load()
        },
        load(){
            //固定首列，首行
            //右边滑动带动左边
            var right_div2 = document.getElementById("right_div2");
            right_div2.onscroll = function(){
                var right_div2_top = this.scrollTop;
                var right_div2_left = this.scrollLeft;
                document.getElementById("left_div2").scrollTop = right_div2_top;
                document.getElementById("right_div1").scrollLeft = right_div2_left;
            }
            
            //获取手机高度 适配
            document.getElementById('right_div2').style.height = (document.body.clientHeight - 50 - 40)+'px'
            document.getElementById('left_div2').style.height = (document.body.clientHeight - 50 - 40)+'px'
        }

    },
    mounted(){
        this.init()
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    .medicalService_rb_bqxx{
        width: 100%;
        height:calc(100% +0.48rem);
        // overflow-y: auto;
        font-size: .14rem;
        font-family:"Microsoft YaHei";


    
        .table{

            text-align: center;
            border-color: #C8C7CC;
            border: none;
            
            tr{
                height: 0.5rem;
            }
            td{
                color: #666666;
                width: 0.5rem;
            }
            thead,tfoot tr{
                td{
                    height: 0.41rem;
                }
            }
            tbody{
                tr:nth-child(odd){
                    background: $cEFE7DF;
                }
                tr:nth-child(even){
                    background: $cAF8760;
                }
        
            }
            tfoot{
                background: $cAF8760;
                td{
                    width: 0.48rem;
                    color: #fff;
                }
            }
        }
        
        
        table,table tr th, table tr td { border:1px solid #e5e5e5; }
        td{
            width: 0.245rem;
        }
        #left_div{
            width:1.26rem;
            float: left;
        }
        #left_div1{
            width: 100%;
        }
        #left_div2{
            width: 100%;
            // height: calc(6.67rem - 0.48rem);
            overflow: hidden;
            tbody tr:nth-child(odd){
                background: #F2F2F3;
                td:nth-child(1){
                    background:$cF7F3F0 ;
                }
            }
            tbody tr:nth-child(even){
                background: #fff;
                td:nth-child(1){
                    background: $cD7C3AC  ;
                }
            }
            tfoot tr:nth-last-child(1){
                background: $cAF8760;
            }
        }
        #left_table1,#left_table2{
            width: 100%
        }

        #right_div{
            width: 2.49rem;
            float: left;
        }
        #right_div1{
            width: 100%;
            overflow: hidden;
        }
        #right_divx{
            width: 8rem;
        }
        #right_div2{
            width:100%;
            // height: calc(6.67rem - 0.48rem);
            overflow: auto;
            tr:nth-child(odd){
                background: $cF7F3F0;
            
            }
            tr:nth-child(even){
                background: $cD7C3AC;
                
            }
            tfoot tr:nth-last-child(1){
                background: $cAF8760;
            }
        }
        #right_table1{
            width: 5rem;
        }
        #right_table2{
            width: 5rem;
        }
        
    }

</style>
