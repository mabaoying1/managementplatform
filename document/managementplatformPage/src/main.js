// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
Vue.config.productionTip = false
// vuex
import store from './vuex/store'
Vue.use(Vuex)
// 接口
//ajax
import axios from 'axios'
Vue.prototype.$http = axios;

// emement - UI
import 'element-ui/lib/theme-chalk/index.css';
import { Loading ,Input ,Progress,DatePicker,Checkbox} from 'element-ui'; 
Vue.use(Loading.directive)
Vue.use(Input);
Vue.use(Progress);
Vue.use(DatePicker);
Vue.use(Checkbox);

// mint - ui 
import { Popup , DatetimePicker ,Radio  } from 'mint-ui';
import 'mint-ui/lib/style.css'
Vue.component(Popup.name, Popup);
Vue.component(DatetimePicker.name, DatetimePicker);
Vue.component(Radio.name, Radio);

// zan UI
import 'vant/lib/index.css';
import { Popup as Popup2, Tab, Tabs, Swipe, SwipeItem} from 'vant';
Vue.use(Popup2);
Vue.use(Tab);
Vue.use(Tabs);
Vue.use(Swipe);
Vue.use(SwipeItem);

// 时间组件
import dateTime from 'vue-date-time-m';
Vue.component('dateTime', dateTime);

//数字翻盘 组件
// https://www.npmjs.com/package/vue-countupjs 
import VueCountUp from 'vue-countupjs'
Vue.use(VueCountUp)


//百度 echart
import echarts from 'echarts';
Vue.prototype.$echarts = echarts ;

//蚂蚁f2 图表
import F2 from '@antv/f2';
Vue.prototype.$F2 = F2 ;

// eachert X 轴文字过多 换行
import tool from './utils/tool';
Vue.use(tool)

// 全局过滤器
import * as filter from './filter/filter'
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
})

// 路由拦截器
router.beforeEach((to, from, next) => {
  // 配置不同的title
  if (to.meta.title) {
    document.title = to.meta.title
  }
  // console.log('路由拦截器，全局进入');
  if(to.path == '/login'){
    next();
  }else{
    
    if(sessionStorage.getItem('loginState') == '0'){
      next();
    }else{
      // alert('登录过期/或没有登录')
      // router.push('login')
      next();
    }
  }
})

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
})
//注册全部 指令
// Vue.directive('focus', {
//   // 当被绑定的元素插入到 DOM 中时……
//   update: function (el) {
//     // 聚焦元素
//     el.focus()
//   }
// })

























