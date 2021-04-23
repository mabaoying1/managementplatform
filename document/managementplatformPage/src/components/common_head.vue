<template>
    <div class="common_head">
       <i class="icon iconfont icon-fanhui l" @click="black()"></i>
        <span>{{title.title}}</span>
        
        <!-- 家庭医生 -->
    </div>
</template>

<script>
export default {
  name: 'common_head',
  props:['title'],
  data () {
    return {
      
    }
  },
  methods:{  
    black(){
     // console.log(this.title.black_page)
        this.$router.push(this.title.title_black)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
	@import '../sass/Common';

	.common_head{
		height: .48rem;
		background: $cffffff;
		display: flex;
		justify-content: center;
		align-items: center;
		color: $c6E6E6E;
		font-size: .18rem;
		font-weight: 600;
		border-bottom: 1px solid $c6E6E6E;
		box-sizing: border-box;
		position: relative;
		i{
		
		
			font-size: .21rem;
		}
		span{
			width: 90%;
			text-align: center;
			white-space: nowrap; //文本强制不换行；
			text-overflow:ellipsis; //文本溢出显示省略号；
			overflow:hidden; //溢出的部分隐藏；
		}

  }
</style>
