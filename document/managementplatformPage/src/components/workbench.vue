<template>
  <div class="workbench">
    <div class="head">
        可编辑工作台
    </div>
    <div class="more">
        <div>
            <i class="icon iconfont icon-yiliaofuwu2 blue_font" ></i>
            <span>医疗服务</span>
        </div>
        <div>
            <i class="icon iconfont icon-weishengziyuan yellow_font" ></i>
            <span>卫生资源</span>
        </div>
        <div>
            <i class="icon iconfont icon-gonggongweisheng red_font"></i>
            <span>公共卫生</span>
        </div>
        <div>
            <i class="icon iconfont icon-jiatingyisheng green_font" @click="tab('hospitalInfo')" ></i>
            <span>家庭医生</span>
        </div>
        <div>
            <i class="icon iconfont icon-shouru1 violet_font"></i>
            <span>收入</span>
        </div>
        <div>
            <i class="icon iconfont icon-yaopin cyan_font"></i>
            <span>药品</span>
        </div>
        <div>
            <i class="icon iconfont icon-kangjunyaowu1 pink_font"></i>
            <span>抗菌药品</span>
        </div>
        <div class="none"></div>

    </div>
    <commonNva></commonNva>
    
  </div>
</template>

<script>
import common_nva from './common_nva.vue'
export default {
  name: 'workbench',
  data () {
    return {
      Nav:'',
    }
  },
  components:{
    "commonNva":common_nva
  },
  methods:{
   
    //导航
    Nav_tab(index){
      this.Nav=index;
    },
    tab(page){
      this.$router.push(page);
    }
  },
  created(){
    
    
  },
  mounted(){
    
   
    
  } 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  @import '../sass/workbench';
</style>
