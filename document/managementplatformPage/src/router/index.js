import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import medicalService_rb from '@/components/medicalService_rb'
import medicalService_rb_chart from "@/components/medicalService_rb_chart"
import medicalService_rb_chart0 from "@/components/medicalService_rb_chart0"
import medicalService_rb_chart2 from "@/components/medicalService_rb_chart2"
import medicalService_rb_bqcw from "@/components/medicalService_rb_bqcw"
import medicalService_rb_ssap from "@/components/medicalService_rb_ssap"
import medicalService_rb_ysap from "@/components/medicalService_rb_ysap"
import medicalService_rb_mzxx from "@/components/medicalService_rb_mzxx"
import medicalService_rb_bqxx from "@/components/medicalService_rb_bqxx"
import medicalService_tjcx from "@/components/medicalService_tjcx"
import medicalService_tjcx_mz from "@/components/medicalService_tjcx_mz"
import medicalService_tjcx_mz_chart1 from "@/components/medicalService_tjcx_mz_chart1"
import medicalService_tjcx_mz_chart2 from "@/components/medicalService_tjcx_mz_chart2"
import medicalService_tjcx_mz_chart3 from "@/components/medicalService_tjcx_mz_chart3"
import medicalService_tjcx_mz_kssr from "@/components/medicalService_tjcx_mz_kssr"
import medicalService_tjcx_mz_kssr_chart from "@/components/medicalService_tjcx_mz_kssr_chart"
import medicalService_tjcx_mz_kssr_yssr from "@/components/medicalService_tjcx_mz_kssr_yssr"
import medicalService_tjcx_mz_kssr_yssr_chart from "@/components/medicalService_tjcx_mz_kssr_yssr_chart"
import medicalService_tjcx_mz_rc from "@/components/medicalService_tjcx_mz_rc"
import medicalService_tjcx_mz_rc_ysrc from "@/components/medicalService_tjcx_mz_rc_ysrc"
import medicalService_tjcx_mz_rc_ysrc_charts from "@/components/medicalService_tjcx_mz_rc_ysrc_charts"
import medicalService_tjcx_mz_cj from "@/components/medicalService_tjcx_mz_cj"
import medicalService_tjcx_mz_cj_chart from "@/components/medicalService_tjcx_mz_cj_chart"
import medicalService_zhyb from "@/components/medicalService_zhyb"
import myZone from "@/components/myZone"
import retPassword from "@/components/retPassword"
import about from "@/components/about"
import seting from "@/components/seting"






Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login1',
      component: login
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      meta: {
        title: '登录'
      }
    }
    ,{
      path: '/medicalService_rb',
      name: 'medicalService_rb',
      component: medicalService_rb,
      meta: {
        title: '综合日报'
      }
    },{
      path: '/medicalService_zhyb',
      name: 'medicalService_zhyb',
      component: medicalService_zhyb,
      meta: {
        title:'综合月报'
      }
    },{
      path: '/medicalService_rb_chart0',
      name: 'medicalService_rb_chart0',
      component: medicalService_rb_chart0,
      meta: {
        title:'日报收入图表'
      }
    },{
      path: '/medicalService_rb_chart',
      name: 'medicalService_rb_chart',
      component: medicalService_rb_chart,
      meta: {
        title:'日报周报收入图表'
      }
    },
    {
      path: '/medicalService_rb_chart2',
      name: 'medicalService_rb_chart2',
      component: medicalService_rb_chart2,
      meta: {
        title:'门诊急诊图表'
      }
    },{
      path: '/medicalService_rb_bqcw',
      name: 'medicalService_rb_bqcw',
      component: medicalService_rb_bqcw,
      meta: {
        title:'病区床位'
      }
    },{
      path: '/medicalService_rb_ssap',
      name: 'medicalService_rb_ssap',
      component: medicalService_rb_ssap,
      meta: {
        title:'手术安排'
      }
    },{
      path: '/medicalService_rb_ysap',
      name: 'medicalService_rb_ysap',
      component: medicalService_rb_ysap,
      meta: {
        title:'医生排班'
      }
    },{
      path: '/medicalService_rb_mzxx',
      name: 'medicalService_rb_mzxx',
      component: medicalService_rb_mzxx,
      meta: {
        title:'门诊信息'
      }
    }
    ,{
      path: '/medicalService_rb_bqxx',
      name: 'medicalService_rb_bqxx',
      component: medicalService_rb_bqxx,
      meta: {
        title:'病区信息'
      }
    },{
      path: '/medicalService_tjcx',
      name: 'medicalService_tjcx',
      component: medicalService_tjcx,
      meta: {
        title:'统计查询'
      }
    },{
      path: '/medicalService_tjcx_mz',
      name: 'medicalService_tjcx_mz',
      component: medicalService_tjcx_mz,
      meta: {
        title:'统计查询-门诊'
      }
    },{
      path: '/medicalService_tjcx_mz_chart1',
      name: 'medicalService_tjcx_mz_chart1',
      component: medicalService_tjcx_mz_chart1,
      meta: {
        title:'统计查询-门诊-收入图表'
      }
    },{
      path: '/medicalService_tjcx_mz_chart2',
      name: 'medicalService_tjcx_mz_chart2',
      component: medicalService_tjcx_mz_chart2,
      meta: {
        title:'统计查询-门诊/住院-收入图表'
      }
    },{
      path: '/medicalService_tjcx_mz_chart3',
      name: 'medicalService_tjcx_mz_chart3',
      component: medicalService_tjcx_mz_chart3,
      meta: {
        title:'统计查询-门诊/住院-收入图表'
      }
    },{      
      path: '/medicalService_tjcx_mz_kssr',
      name: 'medicalService_tjcx_mz_kssr',
      component: medicalService_tjcx_mz_kssr,
      meta: {
        title:'统计查询-门诊-科室收入'
      }
    },{
      path: '/medicalService_tjcx_mz_kssr_chart',
      name: 'medicalService_tjcx_mz_kssr_chart',
      component: medicalService_tjcx_mz_kssr_chart,
      meta: {
        title:'统计查询-门诊-科室收入-图表'
      }
    },{
      path: '/medicalService_tjcx_mz_kssr_yssr',
      name: 'medicalService_tjcx_mz_kssr_yssr',
      component: medicalService_tjcx_mz_kssr_yssr,
      meta: {
        title:'统计查询-门诊-科室收入-医生收入'
      }
    },{
      path: '/medicalService_tjcx_mz_kssr_yssr_chart',
      name: 'medicalService_tjcx_mz_kssr_yssr_chart',
      component: medicalService_tjcx_mz_kssr_yssr_chart,
      meta: {
        title:'统计查询-门诊-科室收入-医生收入-图表'
      }
    },{
      path: '/medicalService_tjcx_mz_rc',
      name: 'medicalService_tjcx_mz_rc',
      component: medicalService_tjcx_mz_rc,
      meta: {
        title:'统计查询-门诊-科室人次详情'
      }
    },{
      path: '/medicalService_tjcx_mz_rc_ysrc',
      name: 'medicalService_tjcx_mz_rc_ysrc',
      component: medicalService_tjcx_mz_rc_ysrc,
      meta: {
        title:'统计查询-门诊-科室人次-医生人次详情'
      }
    },{
      path: '/medicalService_tjcx_mz_rc_ysrc_charts',
      name: 'medicalService_tjcx_mz_rc_ysrc_charts',
      component: medicalService_tjcx_mz_rc_ysrc_charts,
      meta: {
        title:'统计查询-门诊-科室人次-医生人次详情-图表'
      }
    },{
      path: '/medicalService_tjcx_mz_cj',
      name: 'medicalService_tjcx_mz_cj',
      component: medicalService_tjcx_mz_cj,
      meta: {
        title:'统计查询-门诊-科室次均-医生次均详情'
      }
    },{
      path: '/medicalService_tjcx_mz_cj_chart',
      name: 'medicalService_tjcx_mz_cj_chart',
      component: medicalService_tjcx_mz_cj_chart,
      meta: {
        title:'统计查询-门诊-科室次均-医生次均详情-图表'
      }
    },{
      path: '/myZone',
      name: 'myZone',
      component: myZone,
      meta: {
        title:'个人中心'
      }
    },{
      path: '/retPassword',
      name: 'retPassword',
      component: retPassword,
      meta: {
        title:'个人中心-密码修改'
      }
    },{
      path: '/about',
      name: 'about',
      component: about,
      meta: {
        title:'个人中心-关于'
      }
    },{
      path: '/seting',
      name: 'seting',
      component: seting,
      meta: {
        title:'登陆-设置'
      }
    }
    
    
    
    
    
  ]
})
