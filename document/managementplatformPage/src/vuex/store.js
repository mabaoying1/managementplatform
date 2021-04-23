import Vue from 'vue'
import Vuex from 'vuex'
import moment from 'moment'; 
Vue.use(Vuex)
// 首先声明一个状态 state
const state = {
    loginState:'',
    from:'',
    jgName:'四川省骨科医院',
    showMedicalServiceTab:false,
    timeState:'',
    //切换导航
    common_nav_meishan:{
        handle:true,
        show_tab_1:false,
        show_tab_2:false,
        show_tab_3:false,
    },

    //选择时间,默认当天
    TimeNow: moment(new Date()).format('YYYY-MM-DD')

}

// 然后给 actions 注册事件处理函数，当这个函数被触发时，将状态提交到 mutaions中处理
const actions = {
    addAction(context){
    //调用 mutations 中add 方法
        context.commit('add',-3)
    },
    subAction({commit}){
        commit('reduce')
    }
    
}
// 更新状态
const mutations = {
    medicalServiceTabShow(){
        state.showMedicalServiceTab = !state.showMedicalServiceTab
    },
    //判断显示 隐藏导航
    commonNavMeishanTab(context,index){
        // console.log(index)
        switch(index){
            case '1':
                state.common_nav_meishan.show_tab_1 = !state.common_nav_meishan.show_tab_1
                state.common_nav_meishan.show_tab_2 = false
                state.common_nav_meishan.show_tab_3 = false
            break;
            case '2':
                state.common_nav_meishan.show_tab_2 = !state.common_nav_meishan.show_tab_2
                state.common_nav_meishan.show_tab_1 = false
                state.common_nav_meishan.show_tab_3 = false
            break;
            case '3':
                state.common_nav_meishan.show_tab_3 = !state.common_nav_meishan.show_tab_3
                state.common_nav_meishan.show_tab_1 = false
                state.common_nav_meishan.show_tab_2 = false
            break;
            default:
                state.common_nav_meishan.show_tab_1=false
                state.common_nav_meishan.show_tab_2=false
                state.common_nav_meishan.show_tab_3=false
            break;
        }
    },
    //解决重复重复 添加点击其他隐藏导航 事件监听
    commonNavMeishanTabOther(){
        state.common_nav_meishan.handle = false;
    },
    //选择时间
    uploadTimeNow(date,val){
        state.TimeNow = val
    }
}
// 获取状态信息
const getters = {
}

// 下面这个相当关键了，所有模块，记住是所有，注册才能使用
export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters
})