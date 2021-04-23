<template>
    <div class="common_title">
        <!-- 传入title 即可 -->
       <span class="sign"></span>
       <span class="title"> {{title}}</span>
       <span class="date">{{date}}</span>
    </div>
</template>

<script>
export default {
  name: 'common_title',
  props:{
      title:{
          type: String,
          required: true,
          default:'required , type: String'
      },
      date:{
          type: String,
          required: false,
          default:''
      }
  },
  data () {
    return {
    
    }
  },
  methods:{  
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    .common_title{
        width: 100%;
        height: 0.42rem;
        background: #ffffff;
        display:flex;
        justify-content: flex-start;
        align-items: center;
        border-top: .05rem solid #fff;
        border-bottom: .05rem solid #fff;
        .sign{
            width: 0.05rem;
            height: 0.32rem;
            background: #639BFC;
            margin-right: .21rem;
        }
        .title{
            flex: 1;
            font-size: 0.16rem;
            color: #666666;
            font-weight: 600;
            
        }
        .date{
            text-align: right;
            margin-right: .13rem;
            width: 1.4rem;
            font-size: .14rem;
            color: #666666;
        }


    }
</style>
