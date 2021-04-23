<template>
    <div class="nva">
      <div :class="{'bingo': page == '0'}" @click="Nav_tab('0')">
        <img src="../assets/l1.png" alt="">
        <span>综合日报</span>
      </div>
      <div :class="{'bingo': page == '1'}" @click="Nav_tab('1')">
        <img src="../assets/l2.png" alt="">
        <span>综合月报</span>
      </div>
      <div :class="{'bingo': page == '2'}" @click="Nav_tab('2')">
        <img src="../assets/l3.png" alt="">
        <span>统计查询</span>
      </div>
      <!-- <div :class="{'bingo': page == '3'}" @click="Nav_tab('3')">
        <i class="icon iconfont icon-gengduoxinxi1"  ></i>
        <span>更多信息</span>
      </div> -->
      <div :class="{'bingo': page == '4'}" @click="Nav_tab('4')">
        <img src="../assets/l4.png" alt="">
        <span>个人中心</span>
      </div> 
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props:{
    page:{
        type: Number,
        required: true,
        // default:'required , type: String'
    },
   
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      Nav:''
    }
  },
  methods:{
    //导航
    Nav_tab(index){
      
      this.Nav=index;
      switch(index){
        case '0':
            this.$router.push('medicalService_rb');
        break;
        case '1':
            this.$router.push('medicalService_zhyb');
        break; 
        case '2':
            this.$router.push('medicalService_tjcx');
        break;
        case '3':
            this.$router.push('');
        break;
         case '4':
            this.$router.push('myZone');
        break;              
      }
    }
  },
  mounted(){
   // console.log(this.page)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  	@import '../sass/Common';

     .nva{
        position: fixed;
        bottom: 0;
        height: -2rem;
        width: 3.75rem;
        background: #F9F9F9;
        display: flex;
        justify-content: space-around;
        align-items: center;
        div{
            height: auto;
            width: .67rem;
            display: flex;
            flex-direction: column;
            align-items: center;
            color: #AAAAAA;
            span{
                font-size: .12rem;
               
            }
            img{
                height: 0.2rem;
                width: 0.2rem;
                margin-bottom: 0.05rem;
                
            }
        }
        .bingo{
          color: $c6D473A;
          
        }
    }
</style>
