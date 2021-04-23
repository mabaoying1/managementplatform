<template>
    <div class="medicalService_tjcx">
        <commonHead :title = "title"></commonHead>
       <!--  <div class="head">内容选择</div>
       <div class="tab t1">
             <div class="title">查询主题</div>
            
                <mt-radio
                    class="raido"
                    v-model="CWorYW"
                    :options="options"
                >
                </mt-radio>
            
        </div> -->
        
        <div class="head">统计时间段选择</div>

         <div class="tab">
        <div class="title">查询方式</div>
        
            <mt-radio
            class="raido"
            v-model="timeState"
            :options="optionsTime"
            >
            </mt-radio>
        
        </div> 

        <div v-show="timeState == 'month'"  class="getTime" > 
        
        <p >选择月份</p>
        <div class="startTime"  @click="show1()">
            <p class="p1">{{monthTime_show}}</p>
        </div>
        </div>

        <div v-show="timeState == 'day'" class="getTime">
        <p >开始时间</p>
        <div class="startTime" @click="showTimeStrat()">
            <p class="p1">{{startTime_show}}</p>
        </div>
        <p >结束时间</p>
        <div class="endTime" @click="showTimeEnd()">
            <p class="p1">{{endTime_show}}</p>
        </div>
        </div>

      

        <div v-show="Message" class="red_font Popups">
            {{msg}}
        </div>
        <div class="sub" @click="demand()">
            查询 
        </div>
        <mt-datetime-picker
            type="date"
            ref="month"
            year-format="{value} 年"
            month-format="{value} 月"
            date-format="{value} 日"
            :startDate = "minStartTime" 
            :endDate = "startTime_end_month"
            @confirm="handleConfirmMonth"
            v-model="monthTime">
        </mt-datetime-picker>
        <mt-datetime-picker
            type="date"
            ref="startTime"
            year-format="{value} 年"
            month-format="{value} 月"
            date-format="{value} 日"
            :startDate = "minStartTime" 
            :endDate = "startTime_end"
            @confirm="handleConfirmStart"
            v-model="startTime"
            
            >
        </mt-datetime-picker>
        <mt-datetime-picker
            type="date"
            ref="endTime"
            year-format="{value} 年"
            month-format="{value} 月"
            date-format="{value} 日" 
            :endDate = "maxEndTiem"
            @confirm="handleConfirmEnd"
            v-model="endTime" 
            
            >
        </mt-datetime-picker>
        <commonNavMeishan :page="2"></commonNavMeishan>
    </div>
</template>

<script>
import http from '../utils/http'
import api from '../utils/api'
import common_head from "./common_head.vue";
import Title from "./common_title.vue"
import common_nva from "./common_nva.vue"
import moment from 'moment'; //格式化时间

export default {
  name: 'medicalService_tjcx',
  components:{
    "commonHead":common_head,
    "commonTitle":Title,
    "commonNavMeishan":common_nva,    
  },
  data () {
    return {
      title:{
        'title':'选择统计时间',
        "title_black":"medicalService_rb"
      },        
      CWorYW:sessionStorage.getItem('CWorYW')? sessionStorage.getItem('CWorYW'):'yw',
      timeState:sessionStorage.getItem('queryState')? sessionStorage.getItem('queryState'):'day',
      optionsTime:[
        {
          label: '按日',
          value: 'day'
        },
        {
          label: '按月',
          value: 'month'
        }
      ],
      options:[
        {
          label: '业务',
          value: 'yw'
        },
        {
          label: '财务',
          value: 'cw'
        }
      ],

      monthTime:'',
      monthTime_show:'',
      startTime:'',
      endTime:'',
      startTime_show:'',
      endTime_show:'',
      maxEndTiem:'',
      minStartTime:'',
      Message:false,
      msg:''
    }
  },
  created(){
    
    this.startTime_show = sessionStorage.getItem('startTime') ||(moment(new Date()).format('DD') != '01'  ?  `${moment(new Date()).format('YYYY-MM')}-01` : `${moment(new Date()).format('YYYY-MM').split('-')[0]}-${moment(new Date()).format('YYYY-MM').split('-')[1] - 1}-01`)
    // var startTime = new Date().getTime() -  24 * 60 * 60 * 1000;//减去一天
    var  startTime = new Date().getTime()
    this.startTime_end_month = new Date()  ;//显示
    this.startTime = new Date(startTime)//默认初始选择我时间
    this.startTime_end = new Date(startTime)
    this.minStartTime = new Date('2010-01-01')
    this.maxEndTiem = new Date(startTime)
    this.endTime_show = sessionStorage.getItem('endTime')||  moment(new Date(startTime)).format('YYYY-MM-DD')
    this.endTime = this.startTime;
    
    this.monthTime_show = sessionStorage.getItem('monthTimeShow') || moment(new Date(startTime)).format('YYYY-MM')
    this.monthTime=new Date(startTime);
  },
  methods:{
        showTimeStrat(){
            //设置开始 默认时间
            this.$refs.startTime.open()
        },
        showTimeEnd(){        
            this.$refs.endTime.open()
            //最大可选日期
        },
        handleConfirmStart (data) {
            let date = moment(data).format('YYYY-MM-DD')
            this.startTime_show = date
            //时间联动
            this.$refs.endTime.startDate = data
            this.endTime_show = ''
        },
        handleConfirmEnd (data) {
            let date = moment(data).format('YYYY-MM-DD')
            this.endTime_show = date
        },
        handleConfirmMonth(data){

            let date = moment(data).format('YYYY-MM');
            this.monthTime_show = date
        },
        demand(){   
            this.Message=false;
            if(this.timeState == 'month'){
                let t1=this.monthTime_show.split('-');
                let day=new Date(t1[0],t1[1],0).getDate();
                this.startTime_show=this.monthTime_show+"-01";
                this.endTime_show=this.monthTime_show+"-"+day;
            }

             //判断是否选择结束日期
            if(this.endTime_show.length<1){
                this.msg = '请选择结束日期'
                this.Message=true;
                return
            }

         /*    if(this.CWorYW=='cw'){
                 
                 if(Number(this.startTime_show.substring(5,7)) == (new Date().getMonth()+1) ||  Number(this.endTime_show.substring(5,7)) == (new Date().getMonth()+1)){
                    this.msg = '当月财务数据于下月20号后方可查询'
                    this.Message = true
                    return 
                }

                if(new Date().getDate()<=25){
                    if(Number(this.startTime_show.substring(5,7)) == (new Date().getMonth()) ||  Number(this.endTime_show.substring(5,7)) == (new Date().getMonth())){
                        this.msg = '上月财务数据于本月20号后方可查询'
                        this.Message = true
                        return 
                    }
                }                 
            }*/
            //console.log(this.CWorYW+"=====开始时间："+this.startTime_show+"   结束时间："+this.endTime_show)
            sessionStorage.setItem("startTime", this.startTime_show);
            sessionStorage.setItem("endTime", this.endTime_show);
            sessionStorage.setItem("CWorYW", this.CWorYW);
           
            this.jump('medicalService_tjcx_mz')       
        },
    
        show1(){
            this.$refs.month.open() 
        
        }
    },
    mounted(){
        

    },
    beforeUpdate(){
        var div = document.getElementsByClassName('picker-slot-center')
        div[2].style.display = 'none'
        
    },
    watch:{
        timeState(value,oldValue){
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    @import '../sass/Common';
    // @media only screen and (device-width: 375px) and (device-height: 812px) and (-webkit-device-pixel-ratio: 3){
    //     .medicalService_tjcx{
    //         position: fixed;
    //         top: 0.3rem;
    //         bottom: 0;
        
    //     }
    // }
    
    .medicalService_tjcx{
        background: $cF7F3F0;
        height: 100%;
        width: 3.75rem;
        .head{
            height: 0.4rem;
            padding: 0 0.2rem;
            color: $c6E6E6E;
            font-size: 0.17rem;
            font-weight: 600;
            @include fcc;
            justify-content: flex-start;
            background: $cffffff;
        }
    

        .getTime{
            // border-top: 0.01rem solid #DCDCDC;
            width: 3.25rem;
            background: $cffffff;
            color: $c8A8A8A;
            font-size: 0.14rem;
            font-weight: 600;
            padding: 0.14rem 0.25rem;
            p{
                margin: 0.03rem 0;
            }
            .p1{
                margin-left: .1rem;
            }
            .startTime,.endTime{
                width: 100%;
                height: .42rem;
                background: $cEFE7DF;;
                display: flex;
                align-items: center;
            }
        }
        .sub{
            width: calc(100% - 0.5rem);
            height: 0.42rem;
            background: $c8B5016;
            color: $cffffff;;
            font-size: .17rem;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: .5rem .25rem ;
        }
        .t1{
            margin-bottom: 0.1rem;
        }
        .tab{
            display: flex;
            background: $cffffff;;
            height: .8rem;
            
        .title{
            flex: 1; 
            display: flex;
            justify-content: center;
            align-items: center;
            color: $c8A8A8A;
        }
        
        .raido{
            color: $c8A8A8A;
            flex:2;
            display: flex;
            display: flex;
            justify-content: center;
            align-items: center;
            .mint-cell{
                background: none !important;
            }
            .mint-cell-wrapper{
                background: none !important;
            }
        }
        }
        .Popups{
            background: $cffffff;;
            padding-left: .25rem;
            padding-bottom: .15rem;
            height: 0.35rem;
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }
        .mint-radio-input:checked + .mint-radio-core{
            background-color:$c8B5016;
            border-color:$c8B5016;
        }
        
    }
</style>
