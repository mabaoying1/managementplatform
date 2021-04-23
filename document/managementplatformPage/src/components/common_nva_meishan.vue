<template>
    <div class="nva" id="nan_tab">
      <div class="ul ul1" :class="{'blue_font': Nav == '1'}" @click="Nav_tab('1'),Tojump('medicalService_rb')">
        <i class="icon iconfont icon-tongjichaxun"   ></i>
        <span>综合日报</span>
      </div>
      <div  class="ul ul2"  :class="{'blue_font': Nav == '2'}" @click="Nav_tab('2'),Tojump('medicalService_zhyb')">
        <i class="icon iconfont icon-weishengziyuan2"   ></i>
        <span>综合月报</span>
      </div>
      <div  class="ul ul3"  :class="{'blue_font': Nav == '3'}" @click="Nav_tab('3'),Tojump('medicalService_tjcx')">
        <i class="icon iconfont icon-gengduoxinxi1"  ></i>
        <span>统计查询</span>
      </div>
      <div  class="ul ul3"  :class="{'blue_font': Nav == '4'}" @click="Nav_tab('4'),Tojump('myZone')">
        <i class="icon iconfont icon-yishengpaiban"  ></i>
        <span>个人中心</span>
      </div>
      <transition name="el-zoom-in-bottom">
        <!-- <div  class="ul_li ul1_li" v-show="$store.state.common_nav_meishan.show_tab_1">
          <div class="li"><span>望门投止思张俭</span> </div>
          <div class="li"><span>忍死须臾待杜根</span> </div>
          <div class="li"><span>我自横刀向天笑</span> </div>
          <div class="li"><span>去留肝胆两昆仑</span> </div>
        </div> -->
      </transition>
      <transition name="el-zoom-in-bottom">
        <!-- <div  class="ul_li ul2_li"  v-show="$store.state.common_nav_meishan.show_tab_2">
          <div class="li"><span>世事短如春梦</span> </div>
          <div class="li"><span>人情薄似秋云</span> </div>
          
        </div> -->
      </transition>
      <transition name="el-zoom-in-bottom">
        <!-- <div  class="ul_li ul3_li"  v-show="$store.state.common_nav_meishan.show_tab_3">
          <div class="li"><span @click="Tojump('medicalServiceSsap')">手术安排</span> </div>
          <div class="li"><span @click="Tojump('medicalServiceYsap')">医生安排</span> </div>
          <div class="li"><span @click="Tojump('medicalServiceJrmz')">门诊信息</span> </div>
          <div class="li"><span @click="Tojump('medicalServiceBqxx')">病区信息</span> </div>
          
        </div> -->
      </transition>
     
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      Nav:'0',
      caonima:1,
    }
  },
  methods:{
    //导航
    Nav_tab(index){
      this.Nav=index;
      switch(index){
        case '1':
            // this.$router.push('medicalService');
            this.$store.commit('commonNavMeishanTab',index)
        break;
        case '2':
            // this.$router.push('workbench');
            this.$store.commit('commonNavMeishanTab',index)
        break;
        case '3':
            // this.$router.push('hospitalInfo');
            // this.$store.commit('medicalServiceTabShow')
            this.$store.commit('commonNavMeishanTab',index)
        break;
        case '4':
            // this.$router.push('hospitalInfo');
            // this.$store.commit('medicalServiceTabShow')
            this.$store.commit('commonNavMeishanTab',index)
        break;
      }
    },
   //点击其他隐藏 导航
    click_other_hide(){
        //防止重复添加事件
        // console.log('click_other_hide')
        // if(this.$store.state.common_nav_meishan.handle == false){
        //     return
        //     myDiv.addEventListener("click",cao2);
        // }else{
            // console.log('添加事件')
            let that = this
            
            function A (){
                that.caonima = that.caonima+1
                that.$store.commit('commonNavMeishanTab',0)
            }
            var myDiv = document.getElementById("nan_tab");
            function B (e){
                // console.log('组织冒泡')
               if (e && e.stopPropagation)  
                    e.stopPropagation()  
                else  
                    window.event.cancelBubble = true
            }
            // document.removeEventListener("click",cao);
            // myDiv.removeEventListener("click",cao2);
            document.getElementById('app').addEventListener("click",A);
            // document.addEventListener("click",cao);
            myDiv.addEventListener("click",B);
            // this.$store.commit('commonNavMeishanTabOther')
        // }
        
    
    },
    Tojump(url){
      this.$router.push(url)
    }
  },
  mounted(){
    this.click_other_hide()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  .nva{
    z-index: 10;
    position: fixed;
    bottom: 0;
    height: 0.56rem;
    width: 3.75rem;
    background: #F9F9F9;
    display: flex;
    justify-content: space-around;
    align-items: center;
    .ul{
        flex: 1;
        height: 100%;
        // width: .67rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        color: #AAAAAA;
        span{
            font-size: .12rem;
            
        }
        i{
            font-size: .3rem;
            
        }
    }
    .ul1{
      position: relative;
    }
    .ul_li{
      border-radius: 0.05rem;
      background: #fff;
      display: flex;
      flex-direction: column;
      width: 0.94rem;
      justify-content: center;
      align-items: center;
      height: auto;
      border: 0.01rem solid rgb(211, 211, 211);
      box-sizing: border-box;
      .li{
        color: #666;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 0.45rem;
        
        span{
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
          border-top: 0.01rem solid #999;
        }
        
      }
      .li:nth-child(1) span {
        border: 0;
      }
    }
    .ul1_li{
      position:absolute;
      left: .05rem;
      bottom: 0.6rem;
      
      
    }
    .ul2_li{
      position:absolute;
      left:0.93rem;
      bottom: 0.6rem;
      
    }
    .ul3_li{
      position:absolute;
      left: 1.87rem;
      bottom: 0.6rem;
      
    }
      
  }
</style>
