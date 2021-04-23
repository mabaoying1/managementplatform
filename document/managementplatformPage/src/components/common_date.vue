<template>
    <div class="common_date">
        <div class="bg" ></div>
        
        <div class="main">
            <button type="submit" @click="aa()"> 11111</button>
            <!-- <el-date-picker
                v-model="value2"
                :editable='true'
                align="right"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions1"
                
               
                @change="handleFocus"
                >
            </el-date-picker> -->
        </div>
    </div>
</template>

<script>
export default {
    name: 'common_date',
    data () {
        return {
            value2:new Date,
            popupVisible2:true,
            //时间组件的快速时间配置
            pickerOptions1: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                },
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                    picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                    }
                }]
            }   
        }
    },
    methods:{
       aa(){
           document.getElementsByClassName('el-picker-panel')
       },
        handleFocus(val){
            // val.popperElm.hidden = false;
            // val.popperElm.hidden = false
        }

    },
    mounted(){
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
    .common_date{
        height: 6.67rem;
        width: 3.75rem;
        position: fixed;
        top: 0rem;
        .bg{
            background: #ffffff;
            filter: blur(.05rem);
            height: 6.67rem;
            width: 3.75rem;
            z-index: 10;
        }
        .main{
            padding: .05rem;
            position: fixed;
            top: 0rem;
            height: 6.67rem;
            width: 3.75rem;
            z-index: 20;
            
            
        }
    }
    .el-picker-panel__body-wrapper{
        width: 3.75rem !important;
        display: flex;
        .el-picker-panel__sidebar{
            width: 0.65rem;
        }
        .el-picker-panel__body{
            margin-left: 0.65rem;
            flex: 1;
        }
    }
  
</style>
