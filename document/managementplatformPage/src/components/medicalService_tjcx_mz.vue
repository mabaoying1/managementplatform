<template>
	<div class="medicalService_tjcx_mz" v-loading = "all_Loading" >
		<div class="top">
			<div class="t1">
				<span>统计结果</span>
				<span class="s2" >
                    {{this.startTime_show}}-{{this.endTime_show}}
                </span>
			</div>
            <van-tabs class="van-tabs" v-model="activeName"  @change='changeTab'>
                <van-tab v-for="item in tabs" :title="item" :key="item">
                </van-tab>
            </van-tabs>
			

		</div>
        <!-- 收入 -->
        <div v-show="activeName==0" class="sr" >
            <div class="top2" style="margin-top:-5px;">
                <div class="p1">
                    <span>全院总收入(万元)</span>
                    <!-- <span class="s1" @click="show_pop=true">查看科室<i class="icon iconfont icon-xiayiji"></i>  </span> -->
                </div>
                <div class="p2">
                    <span class="s2">¥{{sr_zfy/10000|incisionLeft}}</span>
                </div>
                <div class="d2">
                    <span class="s1">{{sr_zfytb |leftTwoDecimal}}
                         <img v-if="sr_zfytb<0" src="../assets/8.png" alt="">
                         <img v-if="sr_zfytb>0" src="../assets/9.png" alt=""> 
                    </span>
                    <span class="s1">{{sr_zfyhb |leftTwoDecimal}} 
                        <img v-if="sr_zfyhb<0" src="../assets/8.png" alt="">
                        <img v-if="sr_zfyhb>0" src="../assets/9.png" alt=""> 
                    </span>
                </div>
                <div class="d3">
                    <span>同比</span>
                    <span>环比</span>
                </div>
                
            </div>
            <div class="middle">
                <div class="t"></div>
                <div class="w">
                    <div class="d1" id="d1"> 
                        
                    </div>
                    <div class="d1" id="d2" @click="jump('medicalService_tjcx_mz_chart1')"> 
                        
                    </div>
                </div>
                
            </div>
            <div class="middle2">
                <div class="d1" > 门急诊住院对比图</div>
                <div>门急诊住院分布图</div>
            </div>
            <commonTitle2 :title="'门急诊收入(万元)'" :pageName="'详情'" :pageUrl="'medicalService_tjcx_mz_chart2'" ></commonTitle2>
            <div class="info_li">
                <div class="d1">
                    <span class="s1">
                        <span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="sr_mzsr/10000"></v-countup>
                    </span>
                    <span class="s2">
                         {{this.startTime_show}}-{{this.endTime_show}}
                    </span>
                </div>
                <div class="d2">
                    <span class="s1">{{sr_mztb |leftTwoDecimal}} 
                        <img v-if="sr_mztb<0" src="../assets/8.png" alt="">
                        <img v-if="sr_mztb>0" src="../assets/9.png" alt=""> 
                    </span>
                    <span class="s1">{{sr_mzhb |leftTwoDecimal}} 
                        <img v-if="sr_mzhb<0" src="../assets/8.png" alt="">
                        <img v-if="sr_mzhb>0" src="../assets/8.png" alt="">
                    </span>
                </div>
                <div class="d3">
                    <span>同比</span>
                    <span>环比</span>
                </div>
            </div>
            <commonTitle2 :title="'住院收入(万元)'" :pageName="'详情'" :pageUrl="'medicalService_tjcx_mz_chart3'"   ></commonTitle2>
            <div class="info_li">
                <div class="d1">
                    <span class="s1">
                        <span class="s11">¥</span>	<v-countup class="s12" :duration="'2'" :end-value="sr_zysr/10000"></v-countup>
                    </span>
                    <span class="s2">
                        {{this.startTime_show}}-{{this.endTime_show}}
                    </span>
                </div>
                <div class="d2">
                    <span class="s1">{{sr_zytb |leftTwoDecimal}} 
                        <img v-if="sr_zytb<0" src="../assets/8.png" alt="">
                        <img v-if="sr_zytb>0" src="../assets/9.png" alt="">
                    </span>
                    <span class="s1">{{sr_zyhb |leftTwoDecimal}} 
                        <img v-if="sr_zyhb<0" src="../assets/8.png" alt="">
                        <img v-if="sr_zyhb>0" src="../assets/9.png" alt="">
                    </span>
                </div>
                <div class="d3">
                    <span>同比</span>
                    <span>环比</span>
                </div>
            </div>
        </div>

        <!-- 人次 -->
        <div  v-show="activeName==1" class="rc">
            <div class="chart">
                <!-- <div class="h1" >
                    <div class="d1" v-for="(item,index) in list1" :key="index">
                        <span class="s"></span>
                        <span>{{item.name}} {{item.value|incisionLeft}}</span>
                    </div>
                </div> -->
                <div id="d3"></div>
            </div>
            <div class="info">
                <div class="d1">
                    <p class="p1">
                        {{rc_tb|leftTwoDecimal}} 
                        <img v-if="rc_tb>0" src="../assets/9.png" alt=""> 
                        <img v-if="rc_tb<0" src="../assets/8.png" alt="">
                    </p>
                    <p class="p2">人次同比</p>
                </div> 
               <div class="d2">
                    <p class="p1">
                        {{rc_hb|leftTwoDecimal}} 
                        <img v-if="rc_hb>0" src="../assets/9.png" alt="">
                        <img v-if="rc_hb<0" src="../assets/8.png" alt="">
                    </p>
                    <p class="p2">人次环比</p>
                </div> 
                 <!-- <div class="d3">
                   <span class="s1" @click="show_pop=true">
                        查看科室
                        <i class="icon iconfont icon-xiangxia "></i>
                       
                    </span>
                </div> -->
            </div>
            <div id="rc_chart" style="height:3.0rem;"></div>
             <!-- 固定写死 -->
           <!-- <div class="list">
                 <div class="li">
                    <div class="d1">
                        <span>自</span>
                    </div>
                    <div class="d2">
                        <p class="p1">自助机挂号人次</p>
                        <p class="p2">占比{{rc_gh_zzj_zb |leftTwoDecimal}}</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{rc_gh_zzj_tb |leftTwoDecimal}} 
                            <span v-if="rc_gh_zzj_tb>0" class="c1"></span> 
                            <span v-if="rc_gh_zzj_hb<0" class="c2"></span> 
                        </p>
                        <p class="p2">占比 {{rc_gh_zzj_hb |leftTwoDecimal}} 
                            <span v-if="rc_gh_zzj_hb>0" class="c1"></span> 
                            <span v-if="rc_gh_zzj_hb<0" class="c2"></span> 
                        </p>
                    </div>
                </div>
                <div class="li">
                    <div class="d1">
                        <span><img src="../assets/zfb.png" alt=""> </span>
                    </div>
                    <div class="d2">
                        <p class="p1">支付宝挂号人次</p>
                        <p class="p2">占比{{rc_gh_zfb_zb |leftTwoDecimal}}</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{rc_gh_zfb_tb |leftTwoDecimal}} 
                            <span v-if="rc_gh_zfb_tb>0" class="c1"></span> 
                            <span v-if="rc_gh_zfb_tb<0" class="c2"></span>  
                        </p>
                        <p class="p2">环比 {{rc_gh_zfb_hb |leftTwoDecimal}} 
                            <span v-if="rc_gh_zfb_hb>0" class="c1"></span> 
                            <span v-if="rc_gh_zfb_hb<0" class="c2"></span> 
                        </p>
                    </div>
                </div>
                <div class="li">
                    <div class="d1">
                        <span><img src="../assets/wx.png" alt=""> </span>
                    </div>
                    <div class="d2">
                        <p class="p1">微信挂号人次</p>
                        <p class="p2">占比{{rc_gh_wx_zb |leftTwoDecimal}}</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{rc_gh_wx_tb |leftTwoDecimal}} 
                            <span v-if="rc_gh_wx_tb>0" class="c1"></span> 
                            <span v-if="rc_gh_wx_tb<0" class="c2"></span> 
                        </p>
                        <p class="p2">环比 {{rc_gh_wx_hb |leftTwoDecimal}} 
                            <span v-if="rc_gh_wx_hb>0" class="c1"></span> 
                            <span v-if="rc_gh_wx_hb<0" class="c2"></span> 
                        </p>
                    </div>
                </div>
                <div class="li">
                    <div class="d1">
                        <span>窗</span>
                    </div>
                    <div class="d2">
                        <p class="p1">窗口挂号人次</p>
                        <p class="p2">占比{{rc_gh_ck_zb|leftTwoDecimal}}</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{rc_gh_ck_tb |leftTwoDecimal}} 
                            <span v-if="rc_gh_ck_tb>0" class="c1"></span> 
                            <span v-if="rc_gh_ck_tb<0" class="c2"></span>  
                        </p>
                        <p class="p2">环比 {{rc_gh_ck_hb |leftTwoDecimal}} 
                            <span v-if="rc_gh_ck_hb>0" class="c1"></span> 
                            <span v-if="rc_gh_ck_hb<0" class="c2"></span> 
                        </p>
                    </div>
                </div> -->
                <!-- 循环 -->
               <!-- <div class="li" v-for="(item,index) in list1_zb" :key='index'>
                    <div class="d1">
                        <<span>图标</span>
                    </div>
                    <div class="d2">
                        <p class="p1">{{item.SOURCE_Text}}</p>
                        <p class="p2">占比{{item.zb|leftTwoDecimal}}</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{item.tb |leftTwoDecimal}} 
                            <span v-if="item.tb>0" class="c1"></span> 
                            <span v-if="item.tb<0" class="c2"></span>  
                        </p>
                        <p class="p2">环比 {{item.hb |leftTwoDecimal}} 
                            <span v-if="item.hb>0" class="c1"></span> 
                            <span v-if="item.hb<0" class="c2"></span> 
                        </p>
                    </div>
                </div>
            </div>-->
        </div>
        <!-- 药占比 -->
        <div  v-if="activeName==2" class="yzb">
            <div class="top3">
                <van-swipe class="van_swip" :loop="false" :width="344" @change="onChangeSwipe">
                    <van-swipe-item>
                        <div class="li">
                            <div class="d1">
                                <span>总药占比</span>
                            </div>
                            <div class="d0">
                                <span class="s1">
                                    <span class="s12">{{yp_zsr_zb|leftTwoDecimal}}</span>
                                </span>
                                
                            </div>
                            <div class="d2">
                                <span class="s1">{{yp_zsr_tb |leftTwoDecimal}} 
                                    <img v-if="yp_zsr_tb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_zsr_tb > 0" src="../assets/9.png" alt="">
                                </span>
                                <span class="s1">{{yp_zsr_hb |leftTwoDecimal}} 
                                    <img v-if="yp_zsr_hb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_zsr_hb > 0" src="../assets/9.png" alt="">
                                </span>
                            </div>
                            <div class="d3">
                                <span>同比</span>
                                <span>环比</span>
                            </div>
                        </div>
                    </van-swipe-item>
                    <van-swipe-item>
                        <div class="li">
                            <div class="d1">
                                <span>门急诊药占比</span>
                            </div>
                            <div class="d0">
                                <span class="s1">
                                    <span class="s12">{{yp_mzsr_zb|leftTwoDecimal}}</span>
                                </span>
                                
                            </div>
                            <div class="d2">
                                <span class="s1">{{yp_mzsr_tb |leftTwoDecimal}} 
                                    <img v-if="yp_mzsr_tb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_mzsr_tb > 0" src="../assets/9.png" alt=""> 
                                </span>
                                <span class="s1">{{yp_mzsr_hb |leftTwoDecimal}} 
                                    <img v-if="yp_mzsr_hb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_mzsr_hb > 0" src="../assets/9.png" alt=""> 
                                </span>
                            </div>
                            <div class="d3">
                                <span>同比</span>
                                <span>环比</span>
                            </div>
                        </div>
                    </van-swipe-item>
                    <van-swipe-item>
                        <div class="li">
                            <div class="d1">
                                <span>住院药占比</span>
                            </div>
                            <div class="d0">
                                <span class="s1">
                                    <span class="s12">{{yp_zysr_zb|leftTwoDecimal}}</span>
                                </span>
                                
                            </div>
                            <div class="d2">
                                <span class="s1">{{yp_zysr_tb |leftTwoDecimal}}
                                    <img v-if="yp_zysr_tb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_zysr_tb > 0" src="../assets/9.png" alt=""> 
                                </span>
                                <span class="s1">{{yp_zysr_hb |leftTwoDecimal}}
                                    <img v-if="yp_zysr_hb < 0" src="../assets/8.png" alt=""> 
                                    <img v-if="yp_zysr_hb > 0" src="../assets/9.png" alt="">
                                </span>
                            </div>
                            <div class="d3">
                                <span>同比</span>
                                <span>环比</span>
                            </div>
                        </div>
                    </van-swipe-item>
                    
                </van-swipe>
            </div>
            <div class="top4"></div>
            <div class="list" >
                <!-- <div class="li" v-for="(item,index) in arr3" :key='index'> -->
                <div class="li" > 
                    <div class="d1">
                        <span>西</span>
                    </div>
                    <div class="d2">
                        
                        <p class="p2">{{this.yp_xyf_zb_sy |leftTwoDecimal}}</p>
                        <p class="p1">西药占比</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{yp_xyf_tb_sy |leftTwoDecimal}} 
                            <span v-if="yp_xyf_tb_sy>0" class="c1"></span>
                            <span v-if="yp_xyf_tb_sy<0" class="c2"></span>
                        </p>
                        <p class="p2">环比 {{yp_xyf_hb_sy |leftTwoDecimal}} 
                            <span v-if="yp_xyf_hb_sy>0" class="c1"></span>
                            <span v-if="yp_xyf_hb_sy<0" class="c2"></span>
                        </p>
                    </div>
                </div>
                <div class="li" > 
                    <div class="d1">
                        <span>中</span>
                    </div>
                    <div class="d2">
                        
                        <p class="p2">{{this.yp_zyf_zb_sy|leftTwoDecimal}}</p>
                        <p class="p1">中成药占比</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{yp_zyf_tb_sy |leftTwoDecimal}}
                            <span v-if="yp_zyf_tb_sy>0" class="c1"></span>
                            <span v-if="yp_zyf_tb_sy<0" class="c2"></span>
                        </p>
                        <p class="p2">环比 {{yp_zyf_hb_sy |leftTwoDecimal}}
                            <span v-if="yp_zyf_hb_sy>0" class="c1"></span>
                            <span v-if="yp_zyf_hb_sy<0" class="c2"></span>
                        </p>
                    </div>
                </div>
                 <div class="li" > 
                    <div class="d1">
                        <span>草</span>
                    </div>
                    <div class="d2">
                        
                        <p class="p2">{{this.yp_zcyf_zb_sy|leftTwoDecimal}}</p>
                        <p class="p1">中草药占比</p>
                    </div>
                    <div class="d3">
                        <p class="p1">同比 {{yp_zcyf_tb_sy |leftTwoDecimal}} 
                            <span v-if="yp_zcyf_tb_sy>0" class="c1"></span>
                            <span v-if="yp_zcyf_tb_sy<0" class="c2"></span> 
                        </p>
                        <p class="p2">环比 {{yp_zcyf_hb_sy |leftTwoDecimal}} 
                            <span v-if="yp_zcyf_hb_sy>0" class="c1"></span>
                            <span v-if="yp_zcyf_hb_sy<0" class="c2"></span> 
                        </p>
                    </div>
                </div>                               
               
                

            </div>
        </div>
        <!-- 次均 -->
        <div  v-show="activeName==3" class="cj">
            <div class="chart">
                <div id="d4"></div>
            </div>
            <div class="info">
                <div class="d1">
                    <p class="p1">
                        {{jc_tb|leftTwoDecimal}} 
                        <img v-if="jc_tb>0" src="../assets/9.png" alt=""> 
                        <img v-if="jc_tb<0" src="../assets/8.png" alt="">
                    </p>
                    <p class="p2">均次同比</p>
                </div>
                <div class="d2">
                    <p class="p1">
                        {{jc_hb|leftTwoDecimal}} 
                        <img v-if="jc_hb>0"  src="../assets/9.png" alt="">
                        <img v-if="jc_hb<0"  src="../assets/8.png" alt="">
                    </p>
                    <p class="p2">均次环比</p>
                </div>
               <!-- <div class="d3">
                     <span class="s1" @click="show_pop=true">
                        查看科室
                        <i class="icon iconfont icon-xiangxia "></i>
                       
                    </span>
                </div> -->
            </div>
             <div id="jc_chart" style="height:3.0rem;"></div>
        </div>
		
		<commonNavMeishan></commonNavMeishan>
        <van-popup class="pop" position="right"  v-model="show_pop">
            <div class="pop_title">
                <span>请选择科室</span>
                <span class="s1" @click="jumpPage()">确定</span>
            </div>
            <div class="warp">
                <div class="d1">
                    <div class="pop_li " :class="{'bingo':AllActive==true }" @click="getAllDep()">全院</div>

                </div>
                <div class="pop_li" :class="{'bingo':item.active==true }" @click="getDep(item)" 
                    v-for="(item,index) in this.ks_info" :key="item.index">{{item.name}}
                </div>
                
            </div>
            <div class="sub">
                <span @click="show_pop=false">取消</span>
            </div>
            
        </van-popup>
	</div>

	
</template>

<script>
import common_nva from "./common_nva_meishan.vue"
import common_title2 from "./common_title2.vue"
import http from '../utils/http'
import api from '../utils/api'
import moment from 'moment'; //格式化时间



export default {
	name: 'hospitalInfo_qy_yl',
	components:{
		"commonNavMeishan":common_nva,
		"commonTitle2":common_title2
	
	},
	data () {
		return {
            startTime_show : sessionStorage.getItem('startTime'),
            endTime_show : sessionStorage.getItem('endTime'),
            CWorYW : sessionStorage.getItem('CWorYW'),
			all_Loading:false,
            show_pop:false,
            AllActive:false,
            // tabs
            tabs:['收入','人次','药占比','均次'],
            activeName:0,
            i:0,
            //科室
            return_ks:{},
            tmp_ks:{},
            ks_info:[],
            ksids:"",
            ksnames:"",
			info:{},
            //收入
            sr_info:{},
            sr_zfy:0,
            SR_ZFY_MOM:0,
            SR_ZFY_YOY:0,
            sr_zfytb:0,
            sr_zfyhb:0,
            sr_mzsr:0,
            SR_MZSR_MOM:0,
            SR_MZSR_YOY:0,
            sr_zysr:0,
            SR_ZYSR_MOM:0,
            SR_ZYSR_YOY:0,
			sr_mztb:0,
			sr_mzhb:0,
			sr_zytb:0,
            sr_zyhb:0,
            //出院收入
            sr_cysr:0,
            CYFY_:0,
            CYFY_MOM:0,
            CYFY_YOY:0,

            //门诊住院收入分布图
            ret_srfbt:[],
            tmp_rq:'',
            tmp_sr:[],
            mz_srfbt:[],
            zy_srfbt:[],

            //药品收入
            ypsr:{},
            yp_zsr:0,
            YP_ZSR_MOM:0,
            YP_ZSR_YOY:0,
            yp_zsr_zb:0,
            yp_zsr_tb:0,
            yp_zsr_hb:0,

            yp_mzsr:0,
            YP_MZSR_MOM:0,
            YP_MZSR_YOY:0,
            yp_mzsr_zb:0,
            yp_mzsr_tb:0,
            yp_mzsr_hb:0,

            yp_zysr:0,
            YP_ZYSR_MOM:0,
            YP_ZYSR_YOY:0,
            yp_zysr_zb:0,
            yp_zysr_tb:0,
            yp_zysr_hb:0,

            //药品收入分类
            ret_ypsrfl:{},
            //门诊
            yp_mz_zyf:0,
            yp_mz_zcyf:0,
            yp_mz_xyf:0,
            //住院
            yp_zy_zyf:0,
            yp_zy_zcyf:0,
            yp_zy_xyf:0, 

            //药品占比
            ret_yp_thb:[],  
            
            yp_zyf_zb:0,
            yp_zyf_tb:0,
            yp_zyf_hb:0,
            yp_zcyf_zb:0,
            yp_zcyf_tb:0,
            yp_zcyf_hb:0,
            yp_xyf_zb:0,
            yp_xyf_tb:0,
            yp_xyf_hb:0, 

            yp_zyf_mz_zb:0,
            yp_zyf_mz_tb:0,
            yp_zyf_mz_hb:0,
            yp_zcyf_mz_zb:0,
            yp_zcyf_mz_tb:0,
            yp_zcyf_mz_hb:0,
            yp_xyf_mz_zb:0,
            yp_xyf_mz_tb:0,
            yp_xyf_mz_hb:0, 
            
            yp_zyf_zy_zb:0,
            yp_zyf_zy_tb:0,
            yp_zyf_zy_hb:0,
            yp_zcyf_zy_zb:0,
            yp_zcyf_zy_tb:0,
            yp_zcyf_zy_hb:0,
            yp_xyf_zy_zb:0,
            yp_xyf_zy_tb:0,
            yp_xyf_zy_hb:0, 
                  
            tmp_yp_tb:0,
            tmp_yp_hb:0,
            tmp_yp_mz_tb:0,
            tmp_yp_mz_hb:0,
            tmp_yp_zy_tb:0,
            tmp_yp_zy_hb:0,  
            
            yp_zyf_zb_sy:0,
            yp_zyf_tb_sy:0,
            yp_zyf_hb_sy:0,
            yp_zcyf_zb_sy:0,
            yp_zcyf_tb_sy:0,
            yp_zcyf_hb_sy:0,
            yp_xyf_zb_sy:0,
            yp_xyf_tb_sy:0,
            yp_xyf_hb_sy:0,             
            //人次
            list1:[],
            list1_zb:[],
            rc_tb:0,
            rc_hb:0,
            ret_rctj:[],
            rc_mzrc:0,
            rc_mzrc_tb:0,
            rc_mzrc_hb:0,
            rc_jzrc:0,
            rc_jzrc_tb:0,
            rc_jzrc_hb:0,
            rc_ryrc:0,
            rc_ryrc_tb:0,
            rc_ryrc_hb:0,            
            rc_cyrc:0,
            rc_cyrc_tb:0,
            rc_cyrc_hb:0,                        
            rc_ssrc:0,
            rc_ssrc_tb:0,
            rc_ssrc_hb:0, 



            //人次折线图
            rc_title_data:[],
            rc_title:['门诊人次','急诊人次','收治人次','出院人次','手术人次'],
            rc_data:[],
            ret_rcbt:[],
            rc_mzrcbt:[],
            rc_jzrcbt:[],
            rc_ryrcbt:[],
            rc_cyrcbt:[],
            rc_ssrcbt:[],
            tmp_rq1:'',
            

            
            ret_ghrc:[],
            rc_gh_wx:0,
            rc_gh_wx_zb:0,
            rc_gh_wx_tb:0,
            rc_gh_wx_hb:0,
            rc_gh_zfb:0,
            rc_gh_zfb_zb:0,
            rc_gh_zfb_tb:0,
            rc_gh_zfb_hb:0,                                    
            rc_gh_ck:0,
            rc_gh_ck_zb:0, 
            rc_gh_ck_tb:0, 
            rc_gh_ck_hb:0,                                    
            rc_gh_zzj:0,
            rc_gh_zzj_zb:0,
            rc_gh_zzj_tb:0,
            rc_gh_zzj_hb:0,                                    
            rc_gh_qt:0,
            ret_gh_xx:{},
            tmp_gh_tb:0,
            tmp_gh_hb:0,
            //均次
            ret_jcinfo:[],
            jc_mzsr:0,
            jc_mzrc:0,
            MJZRC_MOM:0,
            MJZRC_YOY:0,
            jc_mzjc:0,
            jc_zysr:0,
            jc_zyrc:0,
            jc_zyjc:0,
            jc_sssr:0,
            jc_ssrc:0,
            jc_ssjc:0,
            jc_mzjc_tb:0,
            jc_mzjc_hb:0,
            jc_zyjc_tb:0,
            jc_zyjc_hb:0,
            jc_ssjc_tb:0,
            jc_ssjc_hb:0,

            jc_tb:0,
            jc_hb:0,
            jc_tmp:0,

             //人次折线图
            jc_title_data:'',
            jc_title:['门急诊均次费用','住院均次费用','手术均次费用'],
            jc_data:[],
            ret_jcbt:[],
            jc_mjzjcbt:[],
            jc_zyjcbt:[],
            jc_ssjcbt:[],
            tmp_rq2:'',
            jc_mzjc2:0,
            jc_zyjc2:0,
            jc_ssjc2:0,
		}
		
	},
	methods:{
        // tabs 切换
        changeTab(d){
           this.activeName = d
           switch(d){
               case 0:
                setTimeout(()=>{
                    this.initChart1()
                    this.init1()
                },500)
                
                break
                case 1:
                
                setTimeout(()=>{
                    this.init2();
                    //this.initRC();
                },500)
                break
                case 3:
                
                setTimeout(()=>{
                    this.init3()
                },500)
                break
           }
            // sessionStorage.setItem('jumpPage',this.activeName) 
        },
        // 选择科室
        getDep(i){
            if(i.active==1){
                i.active=0
            }else{
                i.active=1
            }
            
            this.$forceUpdate()
        },
        // 选择全部科室
        getAllDep(){
            this.AllActive = !this.AllActive
            if(this.AllActive){
                for (const key in this.ks_info) {
                    this.ks_info[key].active = 1
                   
                }
            }else{
                for (const key in this.ks_info) {
                    this.ks_info[key].active = 0
                    
                }
            }            
            this.$forceUpdate()
        },
        // 确认科室-跳转页面
        jumpPage(){
            //console.log(this.ks_info)
            for(this.i = 0;this.i < this.ks_info.length;this.i++){
               if(this.ks_info[this.i].active =='1'){
                   if(this.ksids==''){
                        this.ksids = this.ks_info[this.i].value
                        this.ksnames = this.ks_info[this.i].name
                   }else{
                        this.ksids = this.ksids+","+this.ks_info[this.i].value
                        this.ksnames = this.ksnames+","+this.ks_info[this.i].name
                   }

               }
            }
            //console.log(this.ksids)
            //console.log(this.ksname)

            //传递选择的科室id与value
            sessionStorage.setItem("ksids", this.ksids)
            sessionStorage.setItem("ksnames", this.ksnames)

            switch(this.activeName){
                case 0:
                    this.jump('medicalService_tjcx_mz_kssr')
                break
                case 1:
                    this.jump('medicalService_tjcx_mz_rc')
                break
                case 2:
                    // this.jump('')
                break
                case 3:
                    this.jump('medicalService_tjcx_mz_cj')
                break
            }
        },
        // 门急诊住院收入图
        async initChart1(){
            var that = this
           // console.log(that)
            let chart1 = this.$echarts.init(document.getElementById('d1'));
            let option = {
                color:['#AE2C2E','#AF8760'],
                series: [{
                    name:'收入',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: true,
                            position: 'center',
                            formatter: ' {b|{b}：}\n {c}\n  {per|{d}%}  ',
                            backgroundColor: '#eee',
                            borderColor: '#aaa',
                            borderWidth: 1,
                            borderRadius: 4,
                            rich: {
                                // a: {
                                //   color: '#999',
                                //     lineHeight: 11,
                                //     align: 'center'
                                // },  
                                abg: {
                                   // backgroundColor: '#333',
                                    width: '100%',
                                    align: 'right',
                                    height: 12,
                                    borderRadius: [4, 4, 0, 0]
                                },
                                // hr: {
                                //     borderColor: '#aaa',
                                //     width: '100%',
                                //     borderWidth: 0.5,
                                //     height: 0
                                // },
                                b: {
                                    fontSize: 11,
                                    lineHeight: 12
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '11',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:(that.sr_mzsr/10000).toFixed(2), name:'门急诊收入(万)'},
                        {value:(that.sr_zysr/10000).toFixed(2), name:'住院收入(万)'},
                        
                    ]
                }]
            }
            chart1.setOption(option,true);
        },
        // 门诊住院分布图
        async init1(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('d2'));
            let option = {
                color:['#AF8760','#AE2C2E'],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    type: 'category',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                    },
                    axisLabel:{                        
                        color:'#9E9D9C',
                        fontSize:8,
                        fontWeight:'bold',
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        // show:false,
                        color:'#9E9D9C',
                        fontSize:9,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                    
                },
                series: [{
                    symbolSize: 10,
                    data: that.mz_srfbt,
                    type: 'scatter'
                },{
                    symbolSize: 10,
                    data: that.zy_srfbt,
                    type: 'scatter'
                }]
            };
            
            chart1.setOption(option,true);

        }, 
        // **********************人次图表********************************
        async init2(){
             
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('d3'));
            let option = {
                //color:['#D7C3AC','#876048','#5C9252'],
                color:['#D7C3AC','#876048','#5C9252','#C56535','#876048'],
                grid: {
                    top:'5%',
                    left: '3%',
                    right: '4%',
                    bottom: '5%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    data: that.rc_title,
                    type: 'category',
                    axisLine: {
                        show:true,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                    },
                    axisLabel:{
                        show:true,
                        color:'#ffffff',
                        fontSize:10,
                        fontWeight:'bold',
                    },
                },
                yAxis: {
                    type: 'value',
                    show:true,
                    axisLine: {
                        show:true,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        show:true,
                        color:'#D7C3AC',
                        fontSize:9,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        show:true,
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                    
                },
                series: [{
                    type: 'bar',
                    stack: '总量',
                    barWidth:20,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: [this.rc_mzrc,this.rc_jzrc, this.rc_ryrc,this.rc_cyrc,this.rc_ssrc]
                }]
            };
            chart1.on('click',function(p){
                that.jc_tb = 0
                that.jc_hb= 0
                if(p.dataIndex ==0){
                    that.rc_data=that.rc_mzrcbt;
                    that.rc_tb = that.rc_mzrc_tb;
                    that.rc_hb = that.rc_mzrc_hb;
                }else if(p.dataIndex ==1){
                    that.rc_data=that.rc_jzrcbt;
                    that.rc_tb = that.rc_jzrc_tb
                    that.rc_hb = that.rc_jzrc_hb
                }else if(p.dataIndex ==2){
                    that.rc_data=that.rc_ryrcbt;
                    that.rc_tb = that.rc_ryrc_tb
                    that.rc_hb = that.rc_ryrc_hb
                }else if(p.dataIndex ==3){
                    that.rc_data=that.rc_cyrcbt;
                    that.rc_tb = that.rc_cyrc_tb
                    that.rc_hb = that.rc_cyrc_hb    
                }else{
                    that.rc_data=that.rc_ssrcbt;
                    that.rc_tb = that.rc_ssrc_tb
                    that.rc_hb = that.rc_ssrc_hb    
                }
                that.rc_title_data=that.rc_title[p.dataIndex ];
                that.initRC();
            })
            chart1.setOption(option,true);
            //默认显示门诊人次的同环比和折线趋势图
            this.rc_tb = this.rc_mzrc_tb;
            this.rc_hb = this.rc_mzrc_hb;
            that.rc_data=that.rc_mzrcbt;
            that.rc_title_data=that.rc_title[0];
            that.initRC();
        },
         // ******人次折线图表********** 
        async initRC(){
            var that = this
            let rcChart = this.$echarts.init(document.getElementById('rc_chart'));
            let option = {
                 tooltip: { //提示框组件
                    trigger: 'axis'
                },
               color:['#AE2C2E'],
                  legend: {
                     data:that.rc_title_data.split(','),
                 },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    type: 'category',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色                            
                        },
                    },
                    axisLabel:{                       
                        color:'#9E9D9C',
                        fontSize:12,
                        //fontWeight:'bold',
                        rotate:45 
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        // show:false,
                        color:'#9E9D9C',
                        fontSize:9,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                    
                },
                series: [{
                    name:that.rc_title_data,
                    symbolSize: 5,
                    data: that.rc_data,
                     type: 'line'
                   // type: 'scatter' //分布图
                }]
            };
            rcChart.setOption(option,true);
        },
        onChangeSwipe(index){
            //console.log(index)
                this.yp_zyf_zb_sy = 0
                this.yp_zyf_tb_sy = 0
                this.yp_zyf_hb_sy = 0
                this.yp_zcyf_zb_sy = 0
                this.yp_zcyf_tb_sy = 0
                this.yp_zcyf_hb_sy = 0
                this.yp_xyf_zb_sy = 0
                this.yp_xyf_tb_sy = 0
                this.yp_xyf_hb_sy = 0           
            if(index==0){
                this.yp_zyf_zb_sy=this.yp_zyf_zb
                 this.yp_zyf_tb_sy=this.yp_zyf_tb
                 this.yp_zyf_hb_sy=this.yp_zyf_hb
                 this.yp_zcyf_zb_sy=this.yp_zcyf_zb
                 this.yp_zcyf_tb_sy=this.yp_zcyf_tb
                 this.yp_zcyf_hb_sy=this.yp_zcyf_hb
                 this.yp_xyf_zb_sy= this.yp_xyf_zb
                 this.yp_xyf_tb_sy=this.yp_xyf_tb
                 this.yp_xyf_hb_sy= this.yp_xyf_hb
            }else if(index==1){
                 this.yp_zyf_zb_sy=this.yp_zyf_mz_zb
                 this.yp_zyf_tb_sy=this.yp_zyf_mz_tb
                 this.yp_zyf_hb_sy=this.yp_zyf_mz_hb
                 this.yp_zcyf_zb_sy=this.yp_zcyf_mz_zb
                 this.yp_zcyf_tb_sy=this.yp_zcyf_mz_tb
                 this.yp_zcyf_hb_sy=this.yp_zcyf_mz_hb
                 this.yp_xyf_zb_sy=this.yp_xyf_mz_zb
                 this.yp_xyf_tb_sy=this.yp_xyf_mz_tb
                 this.yp_xyf_hb_sy=this.yp_xyf_mz_hb
            }else{
                 this.yp_zyf_zb_sy= this.yp_zyf_zy_zb
                 this.yp_zyf_tb_sy= this.yp_zyf_zy_tb
                 this.yp_zyf_hb_sy= this.yp_zyf_zy_hb
                 this.yp_zcyf_zb_sy= this.yp_zcyf_zy_zb
                 this.yp_zcyf_tb_sy= this.yp_zcyf_zy_tb
                 this.yp_zcyf_hb_sy= this.yp_zcyf_zy_hb
                 this.yp_xyf_zb_sy= this.yp_xyf_zy_zb
                 this.yp_xyf_tb_sy= this.yp_xyf_zy_tb
                 this.yp_xyf_hb_sy=  this.yp_xyf_zy_hb
            }

        },
        // ***************次均************************
        async init3(){
            var that = this
            let chart1 = this.$echarts.init(document.getElementById('d4'));
            let option = {
                color:['#D7C3AC','#876048','#5C9252'],
                // color:['#D7C3AC','#876048','#5C9252','#C56535','#876048'],
                grid: {
                    top:'5%',
                    left: '3%',
                    right: '4%',
                    bottom: '5%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    data: that.jc_title,
                    type: 'category',
                    axisLine: {
                        show:true,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                        },
                    },
                    axisLabel:{
                        show:true,
                        color:'#ffffff',
                        fontSize:10,
                        fontWeight:'bold',
                    },
                },
                yAxis: {
                    type: 'value',
                    show:true,
                    axisLine: {
                        show:true,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                            
                        },
                        
                    },
                    axisLabel:{
                        show:true,
                        color:'#D7C3AC',
                        fontSize:9,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        show:true,
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                    
                },
                series: [{
                    type: 'bar',
                    stack: '总量',
                    barWidth:20,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: [this.jc_mzjc, this.jc_zyjc,this.jc_ssjc ]
                }]
            };
            chart1.on('click',function(p){
                that.jc_tb = 0
                that.jc_hb= 0
                if(p.dataIndex ==0){
                    that.jc_data=that.jc_mjzjcbt;
                    that.jc_tb = that.jc_mzjc_tb
                    that.jc_hb = that.jc_mzjc_hb
                }else if(p.dataIndex ==1){
                    that.jc_data=that.jc_zyjcbt;
                    that.jc_tb = that.jc_zyjc_tb
                    that.jc_hb = that.jc_zyjc_hb
                }else{
                    that.jc_data=that.jc_ssjcbt;
                    that.jc_tb = that.jc_ssjc_tb
                    that.jc_hb = that.jc_ssjc_hb
                }
                that.jc_title_data=that.jc_title[p.dataIndex ];
                that.initJC();
            })
            chart1.setOption(option,true);

             //默认显示门急诊均次的同环比和折线趋势图
            that.jc_tb = that.jc_mzjc_tb
            that.jc_hb = that.jc_mzjc_hb
            that.jc_data=that.jc_mjzjcbt;
            that.jc_title_data=that.jc_title[0];
            that.initJC();
        },
        // ******均次折线图表********** 
        async initJC(){
            var that = this
            let jcChart = this.$echarts.init(document.getElementById('jc_chart'));
            let option = {
                 tooltip: { //提示框组件
                    trigger: 'axis'
                },
                color:['#AE2C2E'],
                  legend: {
                     data:[that.jc_title_data],
                 },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    boundaryGap: true,
                    type: 'category',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色                            
                        },
                    },
                    axisLabel:{                       
                        color:'#9E9D9C',
                        fontSize:12,
                        //fontWeight:'bold',
                        rotate:45 
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        show:false,
                        lineStyle: {
                            color: '#ffffff', // 颜色
                        },
                    },
                    axisLabel:{
                        // show:false,
                        color:'#9E9D9C',
                        fontSize:9,
                        fontWeight:'bold',
                    },
                    splitLine:{
                        lineStyle:{
                            type:'dashed',
                            color: '#C8CAD2'
                        }
                    }
                },
                series: [{
                    name:that.jc_title_data,
                    symbolSize: 5,
                    data: that.jc_data,
                     type: 'line'
                   // type: 'scatter' //分布图
                }]
            };
            jcChart.setOption(option,true);
        },
        //查询全部科室
        async initKs(){
			let data = {
                //"dicid":"DIM0000584",		
                "officeType":this.CWorYW
        	 }
            let res = await http.get(api.getOfficeCode,data)
            this.return_ks = res.data
            for(this.i = 0;this.i < this.return_ks.length;this.i++){
                this.tmp_ks = {value:Number(this.return_ks[this.i].code),name:this.return_ks[this.i].text}
                this.ks_info.push(this.tmp_ks )       
            }
        },
        //*************收入查询****************************
        async initSrInfo(){
			let data = {
                "themeID":this.CWorYW+"_zfy_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
               		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.sr_info =res.data[0]
                //console.log( this.sr_info )
                //门急诊收入
                this.sr_mzsr = Number( this.sr_info.MZFY_) + Number( this.sr_info.JZFY_);          
                this.sr_zysr = Number( this.sr_info.ZYFY_);
                this.sr_zfy =  this.sr_mzsr + this.sr_zysr;

                 //console.log("月总费用==="+this.sr_zfy);
                
                this.SR_ZFY_MOM=Number( this.sr_info.MZFY_MOM)+Number( this.sr_info.JZFY_MOM)+Number( this.sr_info.ZYFY_MOM);
                this.SR_ZFY_YOY=Number( this.sr_info.MZFY_YOY)+Number( this.sr_info.JZFY_YOY)+Number( this.sr_info.ZYFY_YOY);

                this.SR_MZSR_MOM= Number( this.sr_info.MZFY_MOM)+Number( this.sr_info.JZFY_MOM);
                this.SR_MZSR_YOY= Number( this.sr_info.MZFY_YOY)+Number( this.sr_info.JZFY_YOY);

                this.SR_ZYSR_MOM= Number( this.sr_info.ZYFY_MOM);
                this.SR_ZYSR_YOY= Number( this.sr_info.ZYFY_YOY);
               
                //console.log( this.sr_zysr )
                if(this.sr_info.MZFY_MOM!=0){
                    this.sr_mzhb = (Number(this.sr_info.MZFY_MOMGROWTH)+Number(this.sr_info.JZFY_MOMGROWTH)) / this.SR_MZSR_MOM
                }
                if(this.sr_info.MZFY_YOY!=0){
                    this.sr_mztb = (Number(this.sr_info.MZFY_YOYGROWTH)+Number(this.sr_info.JZFY_YOYGROWTH)) / this.SR_MZSR_YOY
                }                                
                if(this.sr_info.ZYFY_MOM!=0){
                    this.sr_zyhb = this.sr_info.ZYFY_MOMGROWTH / this.sr_info.ZYFY_MOM
                } 
                if(this.sr_info.ZYFY_YOY!=0){
                    this.sr_zytb = this.sr_info.ZYFY_YOYGROWTH / this.sr_info.ZYFY_YOY
                }

                //总收入同环比
                if(this.SR_ZFY_YOY!=0){
                     this.sr_zfytb = (Number(this.sr_info.MZFY_YOYGROWTH) +Number(this.sr_info.JZFY_YOYGROWTH) + Number(this.sr_info.ZYFY_YOYGROWTH)) /  this.SR_ZFY_YOY
                }

                if(this.SR_ZFY_MOM!=0){
                     this.sr_zfyhb = (Number(this.sr_info.MZFY_MOMGROWTH) + Number(this.sr_info.JZFY_MOMGROWTH) + Number(this.sr_info.ZYFY_MOMGROWTH)) / this.SR_ZFY_MOM
                }
               
                
            }

            this.initChart1()
            setTimeout(()=>{
               this.initYpInfo()
            },1000)
            
            
        },                
        //*****************药品查询********************************
        async initYpInfo(){
            
			let data = {
                "themeID":this.CWorYW+"_ypsr_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }

            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ypsr =res.data[0]
                this.yp_mzsr = Number( this.ypsr.MZ_YPSR_)    
                this.yp_zysr = Number( this.ypsr.ZY_YPSR_)
                this.yp_zsr =  this.yp_mzsr + this.yp_zysr;
                this.YP_ZSR_MOM=Number(this.ypsr.MZ_YPSR_MOM) +  Number(this.ypsr.ZY_YPSR_MOM);
                this.YP_ZSR_YOY=Number(this.ypsr.MZ_YPSR_YOY) +  Number(this.ypsr.ZY_YPSR_YOY);

                //药品总费用占比
                if( this.sr_zfy !=0){
                    this.yp_zsr_zb = this.yp_zsr /  this.sr_zfy 
                }

                //药品总费用同环比
                this.fun_jcfythb(this.yp_zsr, this.sr_zfy,  this.YP_ZSR_MOM, this.SR_ZFY_MOM);
                this.yp_zsr_hb = this.jc_tmp;
                this.fun_jcfythb(this.yp_zsr, this.sr_zfy, this.YP_ZSR_YOY, this.SR_ZFY_YOY);
                this.yp_zsr_tb = this.jc_tmp;
                console.log("门急诊药品收入=="+this.yp_mzsr+"   住院药品收入="+this.yp_zysr+"收入总费用="+this.sr_zfy+"    药占比="+ this.yp_zsr_zb);

                //门诊药品占比
                if(this.sr_mzsr !=0){
                    this.yp_mzsr_zb = this.yp_mzsr / this.sr_mzsr
                }
                //门诊药品同环比
                this.fun_jcfythb(this.yp_mzsr, this.sr_mzsr,  Number(this.ypsr.MZ_YPSR_MOM), this.SR_MZSR_MOM);
                this.yp_mzsr_hb = this.jc_tmp;
                this.fun_jcfythb(this.yp_mzsr, this.sr_mzsr,  Number(this.ypsr.MZ_YPSR_YOY), this.SR_MZSR_YOY);
                this.yp_mzsr_tb = this.jc_tmp;

                //住院药品占比
                if(this.sr_zysr!=0){
                    this.yp_zysr_zb = this.yp_zysr / this.sr_zysr 
                }
                //住院药品同环比
                this.fun_jcfythb(this.yp_zysr, this.sr_zysr,  Number(this.ypsr.ZY_YPSR_MOM), this.SR_ZYSR_MOM);
                this.yp_zysr_hb = this.jc_tmp;
                this.fun_jcfythb(this.yp_zysr, this.sr_zysr,  Number(this.ypsr.ZY_YPSR_YOY), this.SR_ZYSR_YOY);
                this.yp_zysr_tb = this.jc_tmp;

                

               // this.yp_mzsr_hb =this.ypsr.MZ_YPSR_MOM>0? this.ypsr.MZ_YPSR_MOMGROWTH / this.ypsr.MZ_YPSR_MOM: this.ypsr.MZ_YPSR_MOMGROWTH
                //this.yp_mzsr_tb =this.ypsr.MZ_YPSR_YOY>0? this.ypsr.MZ_YPSR_YOYGROWTH / this.ypsr.MZ_YPSR_YOY: this.ypsr.MZ_YPSR_YOYGROWTH
              //  this.yp_zysr_hb =this.ypsr.ZY_YPSR_MOM>0? this.ypsr.ZY_YPSR_MOMGROWTH / this.ypsr.ZY_YPSR_MOM: this.ypsr.ZY_YPSR_MOMGROWTH
              //  this.yp_zysr_tb =this.ypsr.ZY_YPSR_YOY>0? this.ypsr.ZY_YPSR_YOYGROWTH / this.ypsr.ZY_YPSR_YOY: this.ypsr.ZY_YPSR_YOYGROWTH
                                               
            }
			let data2 = {
                "themeID":this.CWorYW+"_ypsr_tj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	}
            let res2 = await http.get(api.getBIDataAll,data2)
            if(res2.data!=null){
                this.ret_ypsrfl = res2.data
               // console.log(this.ret_ypsrfl);
                for(let i = 0;i < this.ret_ypsrfl.length;i++){
                    //console.log(this.ret_ypsrfl[this.i].SRLB_Text)
                    if(this.ret_ypsrfl[i].SRLB_Text.indexOf("西")!= -1){
                        this.yp_mz_xyf = Number(this.ret_ypsrfl[i].MZ_YPSR_)
                        this.yp_zy_xyf = Number(this.ret_ypsrfl[i].ZY_YPSR_) 
                        await this.intiYpthb(this.ret_ypsrfl[i].SRLB)
                        this.yp_xyf_tb = this.tmp_yp_tb
                        this.yp_xyf_hb = this.tmp_yp_hb  
                        this.yp_xyf_mz_tb = this.tmp_yp_mz_tb
                        this.yp_xyf_mz_hb = this.tmp_yp_mz_hb        
                        this.yp_xyf_zy_tb = this.tmp_yp_zy_tb
                        this.yp_xyf_zy_hb = this.tmp_yp_zy_hb                                                      
                    }if(this.ret_ypsrfl[i].SRLB_Text.indexOf("成")!= -1){
                        //console.log(2)
                        this.yp_mz_zyf = Number(this.ret_ypsrfl[i].MZ_YPSR_)
                        this.yp_zy_zyf = Number(this.ret_ypsrfl[i].ZY_YPSR_)
                        await this.intiYpthb(this.ret_ypsrfl[i].SRLB)
                        this.yp_zyf_tb = this.tmp_yp_tb
                        this.yp_zyf_hb = this.tmp_yp_hb    
                        this.yp_zyf_mz_tb = this.tmp_yp_mz_tb
                        this.yp_zyf_mz_hb = this.tmp_yp_mz_hb     
                        this.yp_zyf_zy_tb = this.tmp_yp_zy_tb
                        this.yp_zyf_zy_hb = this.tmp_yp_zy_hb                                                                     
                    }if(this.ret_ypsrfl[i].SRLB_Text.indexOf("饮")!= -1){
                        //console.log(3)
                        this.yp_mz_zcyf = Number(this.ret_ypsrfl[i].MZ_YPSR_)
                        this.yp_zy_zcyf = Number(this.ret_ypsrfl[i].ZY_YPSR_)
                        await this.intiYpthb(this.ret_ypsrfl[i].SRLB)
                        this.yp_zcyf_tb = this.tmp_yp_tb
                        this.yp_zcyf_hb = this.tmp_yp_hb       
                        this.yp_zcyf_mz_tb = this.tmp_yp_mz_tb

                        this.yp_zcyf_mz_hb = this.tmp_yp_mz_hb 
                        this.yp_zcyf_zy_tb = this.tmp_yp_zy_tb
                        this.yp_zcyf_zy_hb = this.tmp_yp_zy_hb                                                                    
                    }
                }

                
                if(this.yp_zsr!=0){
                    this.yp_xyf_zb = (this.yp_mz_xyf + this.yp_zy_xyf)/ this.yp_zsr
                    this.yp_zyf_zb = (this.yp_mz_zyf + this.yp_zy_zyf)/ this.yp_zsr
                    this.yp_zcyf_zb = (this.yp_mz_zcyf + this.yp_zy_zcyf)/ this.yp_zsr

                    this.yp_xyf_mz_zb = this.yp_mz_xyf/ this.yp_zsr
                    this.yp_zyf_mz_zb = this.yp_mz_zyf/ this.yp_zsr
                    this.yp_zcyf_mz_zb = this.yp_mz_zcyf / this.yp_zsr

                    this.yp_xyf_zy_zb = this.yp_zy_xyf/ this.yp_zsr
                    this.yp_zyf_zy_zb = this.yp_zy_zyf/ this.yp_zsr
                    this.yp_zcyf_zy_zb = this.yp_zy_zcyf/ this.yp_zsr
                }
                //中药
                this.yp_zyf_zb_sy=this.yp_zyf_zb
                this.yp_zyf_tb_sy=this.yp_zyf_tb
                this.yp_zyf_hb_sy=this.yp_zyf_hb
               // console.log("中药费="+(this.yp_mz_zyf + this.yp_zy_zyf)+"  中药占比="+this.yp_zyf_zb_sy+"  同比="+this.yp_zyf_tb_sy+"   环比="+this.yp_zyf_hb_sy);
                //中成药
                this.yp_zcyf_zb_sy=this.yp_zcyf_zb
                this.yp_zcyf_tb_sy=this.yp_zcyf_tb
                this.yp_zcyf_hb_sy=this.yp_zcyf_hb
                // console.log("中成药费="+(this.yp_mz_zcyf + this.yp_zy_zcyf)+"   中成药占比="+this.yp_zcyf_zb_sy+"  同比="+this.yp_zcyf_tb_sy+"   环比="+this.yp_zcyf_hb_sy);
                //西药
                this.yp_xyf_zb_sy= this.yp_xyf_zb
                this.yp_xyf_tb_sy=this.yp_xyf_tb
                this.yp_xyf_hb_sy= this.yp_xyf_hb         
                // console.log("西药费="+(this.yp_mz_xyf + this.yp_zy_xyf)+"   西药占比="+this.yp_xyf_zb_sy+"  同比="+this.yp_xyf_tb_sy+"   环比="+this.yp_xyf_hb_sy);      
            }
        },
        async intiYpthb(param){
			let data = {
                "themeID":this.CWorYW+"_ypsr_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
                "dim_key":"fylb",
                "value":param        		
        	 }           
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_yp_thb = res.data[0]
                this.tmp_yp_hb =Number(this.ret_yp_thb.ZY_YPSR_MOM) +Number(this.ret_yp_thb.MZ_YPSR_MOM)!=0? (Number(this.ret_yp_thb.ZY_YPSR_MOMGROWTH) +Number(this.ret_yp_thb.MZ_YPSR_MOMGROWTH))/ (Number(this.ret_yp_thb.ZY_YPSR_MOM) +Number(this.ret_yp_thb.MZ_YPSR_MOM)):(Number(this.ret_yp_thb.ZY_YPSR_MOMGROWTH) +Number(this.ret_yp_thb.MZ_YPSR_MOMGROWTH))
                this.tmp_yp_tb =Number(this.ret_yp_thb.ZY_YPSR_YOY) +Number(this.ret_yp_thb.MZ_YPSR_YOY)!=0? (Number(this.ret_yp_thb.MZ_YPSR_YOYGROWTH) +Number(this.ret_yp_thb.ZY_YPSR_YOYGROWTH))/ (Number(this.ret_yp_thb.ZY_YPSR_YOY) +Number(this.ret_yp_thb.MZ_YPSR_YOY)):(Number(this.ret_yp_thb.MZ_YPSR_YOYGROWTH) +Number(this.ret_yp_thb.ZY_YPSR_YOYGROWTH))
                this.tmp_yp_mz_hb =Number(this.ret_yp_thb.MZ_YPSR_MOM)!=0? Number(this.ret_yp_thb.MZ_YPSR_MOMGROWTH)/ Number(this.ret_yp_thb.MZ_YPSR_MOM):Number(this.ret_yp_thb.MZ_YPSR_MOMRATE)
                this.tmp_yp_mz_tb =Number(this.ret_yp_thb.MZ_YPSR_YOY)!=0? Number(this.ret_yp_thb.MZ_YPSR_YOYGROWTH) / Number(this.ret_yp_thb.MZ_YPSR_YOY):Number(this.ret_yp_thb.MZ_YPSR_YOYRATE)
                this.tmp_yp_zy_hb =Number(this.ret_yp_thb.ZY_YPSR_MOM)!=0? Number(this.ret_yp_thb.ZY_YPSR_MOMGROWTH) / Number(this.ret_yp_thb.ZY_YPSR_MOM) :Number(this.ret_yp_thb.ZY_YPSR_MOMRATE)
                this.tmp_yp_zy_tb =Number(this.ret_yp_thb.ZY_YPSR_YOY)!=0? Number(this.ret_yp_thb.ZY_YPSR_YOYGROWTH)/ Number(this.ret_yp_thb.ZY_YPSR_YOY):Number(this.ret_yp_thb.ZY_YPSR_YOYRATE)                               
                
            }
        }  , 
        //*********门诊住院收入分布图***************
        async initSrfbt(){
            
			let data = {
                "themeID":this.CWorYW+"_zfy_tj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_srfbt = res.data
                for(this.i = 0;this.i < this.ret_srfbt.length ;this.i++){
                    this.tmp_rq = Number(this.ret_srfbt[this.i].DOC.substring(0,4)+''+this.ret_srfbt[this.i].DOC.substring(5,7)+''+this.ret_srfbt[this.i].DOC.substring(8,10))
                    this.mz_srfbt.push([this.tmp_rq,(Number(this.ret_srfbt[this.i].INDEX_MZFY_)+Number(this.ret_srfbt[this.i].INDEX_JZFY_))/10000])  
                    this.zy_srfbt.push([this.tmp_rq,Number(this.ret_srfbt[this.i].INDEX_ZYFY_)/10000])
                }
                this.mz_srfbt.sort(function(a,b){
                    return a[0] - b[0];
                });
                this.zy_srfbt.sort(function(a,b){
                    return a[0] - b[0];
                });
                this.init1()
            }

        }, 
        //*****************人次查询************************
        async initRcInfo(){            
			let data = {
                "themeID":this.CWorYW+"_rc_rctj",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	}
            let res = await http.get(api.getBIDataAll,data)
            if(res.data!=null){
                this.ret_rctj = res.data[0];
                //门诊挂号人次
                this.rc_mzrc = Number(this.ret_rctj.MZRC_)
                this.rc_mzrc_hb = this.ret_rctj.MZRC_MOM!=0 ? this.ret_rctj.MZRC_MOMGROWTH /this.ret_rctj.MZRC_MOM:this.ret_rctj.MZRC_MOMRATE;
                this.rc_mzrc_tb = this.ret_rctj.MZRC_YOY!=0 ? this.ret_rctj.MZRC_YOYGROWTH /this.ret_rctj.MZRC_YOY:this.ret_rctj.MZRC_YOYRATE;
                
                 //急诊挂号人次
                this.rc_jzrc = Number(this.ret_rctj.JZRC_)
                this.rc_jzrc_hb = this.ret_rctj.JZRC_MOM!=0 ? this.ret_rctj.JZRC_MOMGROWTH /this.ret_rctj.JZRC_MOM:this.ret_rctj.JZRC_MOMRATE;
                this.rc_jzrc_tb = this.ret_rctj.JZRC_YOY!=0 ? this.ret_rctj.JZRC_YOYGROWTH /this.ret_rctj.JZRC_YOY:this.ret_rctj.JZRC_YOYRATE;
                //入院人次
                this.rc_ryrc = Number(this.ret_rctj.RYRC_)
                this.rc_ryrc_hb = this.ret_rctj.RYRC_MOM!=0?this.ret_rctj.RYRC_MOMGROWTH /this.ret_rctj.RYRC_MOM:this.ret_rctj.RYRC_MOMRATE
                this.rc_ryrc_tb = this.ret_rctj.RYRC_YOY!=0?this.ret_rctj.RYRC_YOYGROWTH /this.ret_rctj.RYRC_YOY:this.ret_rctj.RYRC_YOYRATE
                
                //出院人次
                this.rc_cyrc = Number(this.ret_rctj.CYRC_)
                this.rc_cyrc_hb = this.ret_rctj.CYRC_MOM!=0?this.ret_rctj.CYRC_MOMGROWTH /this.ret_rctj.CYRC_MOM:this.ret_rctj.CYRC_MOMRATE
                this.rc_cyrc_tb = this.ret_rctj.CYRC_YOY!=0?this.ret_rctj.CYRC_YOYGROWTH /this.ret_rctj.CYRC_YOY:this.ret_rctj.CYRC_YOYRATE
                
                //手术人次
                this.rc_ssrc = Number(this.ret_rctj.SSRC_)
                this.rc_ssrc_hb = this.ret_rctj.SSRC_MOM!=0?this.ret_rctj.SSRC_MOMGROWTH /this.ret_rctj.SSRC_MOM:this.ret_rctj.SSRC_MOMRATE
                this.rc_ssrc_tb = this.ret_rctj.SSRC_YOY!=0?this.ret_rctj.SSRC_YOYGROWTH /this.ret_rctj.SSRC_YOY:this.ret_rctj.SSRC_YOYRATE
                
            }


		/*	let data2 = {
                "themeID":this.CWorYW+"_ghrc",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }
            let res2 = await http.get(api.getBIDataAll,data2)
            this.list1_zb=[]
            if(res2.data!=null){
                this.ret_ghrc = res2.data
                console.log( this.ret_ghrc);
                for(this.i = 0;this.i < this.ret_ghrc.length ;this.i++){
                    if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("微") !=-1){
                        this.rc_gh_wx = Number(this.ret_ghrc[this.i].MZRC_)
                        await this.initGhthb(this.ret_ghrc[this.i].SOURCE)
                        this.rc_gh_wx_tb = this.tmp_gh_tb
                        this.rc_gh_wx_hb = this.tmp_gh_hb
                        // 循环列表
                        this.list1_zb.push(
                            {
                                SOURCE_Text:this.ret_ghrc[this.i].SOURCE_Text,
                                tb:this.rc_gh_wx_tb,
                                hb:this.rc_gh_wx_hb,
                                zb:this.rc_gh_wx_zb = this.rc_gh_wx /this.rc_mzrc

                            }
                        )
                    }else if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("支") !=-1){
                        this.rc_gh_zfb = Number(this.ret_ghrc[this.i].MZRC_)
                        await this.initGhthb(this.ret_ghrc[this.i].SOURCE)
                        this.rc_gh_zfb_tb = this.tmp_gh_tb
                        this.rc_gh_zfb_hb = this.tmp_gh_hb  
                        this.list1_zb.push(
                            {
                                SOURCE_Text:this.ret_ghrc[this.i].SOURCE_Text,
                                tb:this.rc_gh_zfb_tb,
                                hb:this.rc_gh_zfb_hb,
                                zb:this.rc_gh_zfb_zb = this.rc_gh_wx /this.rc_mzrc

                            }
                        )     

                    }else if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("自") !=-1){
                        this.rc_gh_zzj = Number(this.ret_ghrc[this.i].MZRC_)
                        await this.initGhthb(this.ret_ghrc[this.i].SOURCE)
                        this.rc_gh_zzj_tb = this.tmp_gh_tb
                        this.rc_gh_zzj_hb = this.tmp_gh_hb     
                        this.list1_zb.push(
                            {
                                SOURCE_Text:this.ret_ghrc[this.i].SOURCE_Text,
                                tb:this.rc_gh_zzj_tb,
                                hb:this.rc_gh_zzj_hb,
                                zb:this.rc_gh_zzj_zb = this.rc_gh_zzj /this.rc_mzrc

                            }
                        )      
                       // console.log(this.list1_zb)

                    }else if(this.ret_ghrc[this.i].SOURCE_Text.indexOf("窗") !=-1){
                        this.rc_gh_ck = Number(this.ret_ghrc[this.i].MZRC_)
                        await this.initGhthb(this.ret_ghrc[this.i].SOURCE)
                        this.rc_gh_ck_tb = this.tmp_gh_tb
                        this.rc_gh_ck_hb = this.tmp_gh_hb   
                        this.list1_zb.push(
                            {
                                SOURCE_Text:this.ret_ghrc[this.i].SOURCE_Text,
                                tb:this.rc_gh_ck_tb,
                                hb:this.rc_gh_ck_hb,
                                zb:this.rc_gh_ck_zb = this.rc_gh_ck /this.rc_mzrc

                            }
                        )     
                      //  console.log(this.list1_zb)

                    }

                }
                this.list1_zb
                if(this.rc_mzrc>0){
                    this.rc_gh_wx_zb = this.rc_gh_wx /this.rc_mzrc
                    this.rc_gh_zfb_zb = this.rc_gh_wx /this.rc_mzrc
                    this.rc_gh_zzj_zb = this.rc_gh_zzj /this.rc_mzrc
                    this.rc_gh_ck_zb = this.rc_gh_ck /this.rc_mzrc
                }
              //  console.log(this.list1_zb)
            }   */                            
        },
          //人次统计折线图
        async initRcbt(){
			let data = {
                "themeID":this.CWorYW+"_rc_rctj2",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_rcbt = res.data;
            for(this.i = 0;this.i < this.ret_srfbt.length ;this.i++){
                this.tmp_rq1 = Number(this.ret_rcbt[this.i].DOC.substring(0,4)+''+this.ret_rcbt[this.i].DOC.substring(5,7)+''+this.ret_rcbt[this.i].DOC.substring(8,10))
                this.rc_mzrcbt.push([this.tmp_rq1,Number(this.ret_rcbt[this.i].MZRC_)]);  
                this.rc_jzrcbt.push([this.tmp_rq1,Number(this.ret_rcbt[this.i].JZRC_)]); 
                this.rc_ryrcbt.push([this.tmp_rq1,Number(this.ret_rcbt[this.i].RYRC_)]);  
                this.rc_cyrcbt.push([this.tmp_rq1,Number(this.ret_rcbt[this.i].CYRC_)]); 
                this.rc_ssrcbt.push([this.tmp_rq1,Number(this.ret_rcbt[this.i].SSRC_)]);  
            }
            this.rc_mzrcbt.sort(function(a,b){
                return a[0] - b[0];
            });
            this.rc_jzrcbt.sort(function(a,b){
                return a[0] - b[0];
            });
            this.rc_ryrcbt.sort(function(a,b){
                return a[0] - b[0];
            });
            this.rc_cyrcbt.sort(function(a,b){
                return a[0] - b[0];
            });
            this.rc_ssrcbt.sort(function(a,b){
                return a[0] - b[0];
            });
        },          
        //挂号同环比
        async initGhthb(param){
			let data = {
                "themeID":this.CWorYW +"_ghrc_zb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				//"startdate":"2017-01-01",
                //"enddate":"2019-01-01",	                	
                "dim_key":"source",
                "value":param		
        	 }

            let res = await http.get(api.getBIDataAll,data)   
            if(res.data!=null){
                this.ret_gh_xx = res.data[0]
                this.tmp_gh_hb = this.ret_gh_xx.MZRC_MOM>0?this.ret_gh_xx.MZRC_MOMGROWTH /this.ret_gh_xx.MZRC_MOM:this.ret_gh_xx.MZRC_MOMRATE
                this.tmp_gh_tb = this.ret_gh_xx.MZRC_YOY>0?this.ret_gh_xx.MZRC_YOYGROWTH /this.ret_gh_xx.MZRC_YOY:this.ret_gh_xx.MZRC_YOYRATE
            }

        } ,        
        //********************均次***************************************
        async initJcfy(){
			let data = {
                "themeID":this.CWorYW +"_ys_sssrzb",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,
				// "startdate":"2017-01-01",
                // "enddate":"2019-01-01",	                			
        	 }

            let res = await http.get(api.getBIDataAll,data) 
            if(res.data!=null){
                this.ret_jcinfo = res.data[0];
                //门急诊挂号人次=门诊挂号人次+急诊挂号人次
                this.jc_mzrc = Number(this.ret_jcinfo.MZRC_) + Number(this.ret_jcinfo.JZRC_);
                this.MJZRC_MOM=Number(this.ret_jcinfo.MZRC_MOM) + Number(this.ret_jcinfo.JZRC_MOM);
                this.MJZRC_YOY=Number(this.ret_jcinfo.MZRC_YOY) + Number(this.ret_jcinfo.JZRC_YOY);
                //门诊均次费用
                if(this.jc_mzrc!=0){
                    this.jc_mzjc =  ((Number(this.ret_jcinfo.MZSR_)+Number(this.ret_jcinfo.JZSR_))/this.jc_mzrc).toFixed(2); 
                } 
               // console.log("门急诊人次=="+this.jc_mzrc+"   门急诊费用==="+(Number(this.ret_jcinfo.MZSR_)+Number(this.ret_jcinfo.JZSR_)));
              //  console.log("门急诊均次费用==="+this.jc_mzjc);
                this.fun_jcfythb(Number(this.ret_jcinfo.MZSR_)+Number(this.ret_jcinfo.JZSR_),this.jc_mzrc,Number(this.ret_jcinfo.MZSR_MOM)+Number(this.ret_jcinfo.JZSR_MOM),this.MJZRC_MOM);
                this.jc_mzjc_hb = this.jc_tmp;
               // console.log("this.ret_jcinfo.MZSR_MOM=="+Number(this.ret_jcinfo.MZSR_MOM)+Number(this.ret_jcinfo.JZSR_MOM)+"    this.MJZLRC_MOM=="+this.MJZRC_MOM);
               // console.log("this.jc_mzjc_hb==="+this.jc_mzjc_hb);
                this.fun_jcfythb(Number(this.ret_jcinfo.MZSR_)+Number(this.ret_jcinfo.JZSR_),this.jc_mzrc,Number(this.ret_jcinfo.MZSR_YOY)+Number(this.ret_jcinfo.JZSR_YOY),this.MJZRC_YOY)
                this.jc_mzjc_tb = this.jc_tmp;
               // console.log("this.ret_jcinfo.MZSR_YOY=="+Number(this.ret_jcinfo.MZSR_YOY)+Number(this.ret_jcinfo.JZSR_YOY)+"    this.MJZLRC_YOY="+this.MJZRC_YOY);
               // console.log("this.jc_mzjc_tb==="+this.jc_mzjc_tb);


               this.jc_zyjc = Number(this.ret_jcinfo.CYRC_)>0? (Number(this.ret_jcinfo.CYSR_) /Number(this.ret_jcinfo.CYRC_)).toFixed(2):0
              console.log("出院人次=="+this.ret_jcinfo.CYRC_+"   出院费用==="+this.ret_jcinfo.CYSR_);
              // console.log("出院均次费用==="+this.jc_zyjc);
               this.fun_jcfythb(Number(this.ret_jcinfo.CYSR_),Number(this.ret_jcinfo.CYRC_),Number(this.ret_jcinfo.CYSR_MOM),Number(this.ret_jcinfo.CYRC_MOM))
                this.jc_zyjc_hb = this.jc_tmp;
               // console.log("this.ret_jcinfo.CYSR_MOM_=="+this.ret_jcinfo.CYSR_MOM+"    this.ret_jcinfo.CYRC_MOM=="+this.ret_jcinfo.CYRC_MOM);
               // console.log("this.jc_zyjc_hb==="+this.jc_zyjc_hb);
                this.fun_jcfythb(Number(this.ret_jcinfo.CYSR_),Number(this.ret_jcinfo.CYRC_),Number(this.ret_jcinfo.CYSR_YOY),Number(this.ret_jcinfo.CYRC_YOY))
                this.jc_zyjc_tb = this.jc_tmp;
               // console.log("this.ret_jcinfo.CYSR_YOY=="+this.ret_jcinfo.CYSR_YOY+"    this.ret_jcinfo.CYRC_YOY="+this.ret_jcinfo.CYRC_YOY);
               // console.log("this.jc_zyjc_tb==="+this.jc_zyjc_tb);


                this.jc_ssjc = Number(this.ret_jcinfo.SSRC_)>0? (Number(this.ret_jcinfo.SSSR_) /Number(this.ret_jcinfo.SSRC_)).toFixed(2):0
               // console.log("手术人次=="+this.ret_jcinfo.SSRC_+"   手术费用==="+this.ret_jcinfo.SSSR_);
               // console.log("手术均次费用==="+this.jc_ssjc);
                this.fun_jcfythb(Number(this.ret_jcinfo.SSSR_),Number(this.ret_jcinfo.SSRC_),Number(this.ret_jcinfo.SSSR_MOM),Number(this.ret_jcinfo.SSRC_MOM))
                this.jc_ssjc_hb = this.jc_tmp;
               // console.log("this.ret_jcinfo.SSSR_MOM=="+this.ret_jcinfo.SSSR_MOM+"    this.ret_jcinfo.SSRC_MOM=="+this.ret_jcinfo.SSRC_MOM);
               // console.log("this.jc_ssjc_hb==="+this.jc_ssjc_hb);
                this.fun_jcfythb(Number(this.ret_jcinfo.SSSR_),Number(this.ret_jcinfo.SSRC_),Number(this.ret_jcinfo.SSSR_YOY),Number(this.ret_jcinfo.SSRC_YOY))
                this.jc_ssjc_tb = this.jc_tmp;
              //  console.log("this.ret_jcinfo.SSSR_YOY=="+this.ret_jcinfo.SSSR_YOY+"    this.ret_jcinfo.SSRC_YOY="+this.ret_jcinfo.SSRC_YOY);
               // console.log("this.jc_ssjc_tb==="+this.jc_ssjc_tb);

                //默认均次同环比
                this.jc_tb = this.jc_mzjc_tb;
                this.jc_hb = this.jc_mzjc_hb;
            }  
        }, 
           //均次统计折线图
        async initJcbt(){
			let data = {
               "themeID":this.CWorYW +"_ys_sssrtj2",
				"startdate":this.startTime_show,
                "enddate":this.endTime_show,         		
        	 }
            let res = await http.get(api.getBIDataAll,data)
            this.ret_jcbt = res.data;
            for(this.i = 0;this.i < this.ret_srfbt.length ;this.i++){
                 this.tmp_rq2 = Number(this.ret_jcbt[this.i].DOC.substring(0,4)+''+this.ret_jcbt[this.i].DOC.substring(5,7)+''+this.ret_jcbt[this.i].DOC.substring(8,10));
              //日均次费用
                this.jc_mzjc2 =  (Number(this.ret_jcbt[this.i].MZRC_) + Number(this.ret_jcbt[this.i].JZRC_))>0?((Number(this.ret_jcbt[this.i].MZSR_) + Number(this.ret_jcbt[this.i].JZSR_))/(Number(this.ret_jcbt[this.i].MZRC_) + Number(this.ret_jcbt[this.i].JZRC_))).toFixed(2) : 0; 
                this.jc_zyjc2 = Number(this.ret_jcbt[this.i].CYRC_)>0? (Number(this.ret_jcbt[this.i].CYSR_) /Number(this.ret_jcbt[this.i].CYRC_)).toFixed(2):0
                this.jc_ssjc2 = Number(this.ret_jcbt[this.i].SSRC_)>0? (Number(this.ret_jcbt[this.i].SSSR_) /Number(this.ret_jcbt[this.i].SSRC_)).toFixed(2):0

                this.jc_mjzjcbt.push([this.tmp_rq2,this.jc_mzjc2]);
                this.jc_zyjcbt.push([this.tmp_rq2, this.jc_zyjc2]); 
                this.jc_ssjcbt.push([this.tmp_rq2,this.jc_ssjc2]);  
            }

           this.jc_mjzjcbt.sort(function(a,b){
                return a[0] - b[0];
            });
           
            this.jc_zyjcbt.sort(function(a,b){
                return a[0] - b[0];
            });
           
            this.jc_ssjcbt.sort(function(a,b){
                return a[0] - b[0];
            });
        },      
        fun_jcfythb(param1,param2,param3,param4){
            if(param1>0&&param2>0&&param3>0&&param4>0){
                this.jc_tmp = ((param1/param2)-(param3/param4))/(param3/param4)
            }else if(param1>0&&param2>0&&param3==0&&param4==0){
                this.jc_tmp = param1/param2
            }else{
                this.jc_tmp = 0
            }
        } ,  
        
    },

	created(){
		
	},
	mounted(){
        this.initKs()
      this.initSrInfo()
        setTimeout(()=>{
              this.initSrfbt()
       },500)
       setTimeout(()=>{
            this.initRcInfo();
            this.initRcbt();
       },1500);
       setTimeout(()=>{
           this.initJcfy();
           this.initJcbt();
       },2500)               


        // sessionStorage.setItem('jumpPage',this.activeName) 
        
	} 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only  -->
<style lang="scss">
@import '../sass/Common';
.medicalService_tjcx_mz{
    background: $cF7F3F0;
    height: auto;
    padding-bottom: 0.49rem;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    .top{
        // position:  absolute;
        height: 1.22rem;
        width: calc(100% - 0.4rem);
        background: $cAF8760;
        padding: 0 0.2rem;
        .t1{
            @include fcs;
            height: 0.44rem;
            color: $cffffff;
            font-size: 0.17rem;
            font-weight: 600;
            .s2{
                width: 1.6rem;
                background: $cF2D634;
                height: 0.2rem;
                border-radius: 0.05rem;
                @include fcc;
                font-size: 0.12rem;
                color: $cAF8760;
                
            }
        }
        
    }
    // tabs
    .van-tabs{
        background: $cAF8760;

        .van-hairline--top-bottom::after, .van-hairline-unset--top-bottom::after{
            border: none;
        }
        .van-tab{
            

            @include fcc;
            color: $cffffff;
        }
        .van-tabs__wrap{
            padding:  0.15rem 0;
            height: 32px;
        }
        .van-tabs__nav{
            background: $cAF8760;
        }
        .van-tabs__nav--line{
            padding: 0;
        }
        .van-tabs__line{
            display: none;
        }
        .van-tab--active{
            background: $cffffff;
            color: $cAF8760;
            border-radius: 0.15rem;
        }
    }
    // 收入
    .sr{
        margin-bottom: 0.5rem;
        .top2{
            box-shadow:0 0 .08rem #999999;
            position: absolute;
            height: 1.6rem;
            width: 3.45rem;
            padding: 0.17rem 0.22rem;
            box-sizing: border-box;
            background: $cffffff;
            left: 50%;
            top: 28%;
            transform: translateX(-50%) translateY(-50%);
            border-radius: 0.1rem;
            @include fms;
            align-items: flex-start;
            .p1{
                
                width: 100%;
                font-size: 0.16rem;
                color: $c6E6E6E;
                font-weight: 600;
                @include fcs;
                .s1{
                    height: 0.2rem;
                    width: auto;
                    background: $c8B5016;
                    color: $cffffff;
                    padding: 0.05rem 0.08rem;
                    font-size: 0.17rem;
                    @include fcc;
                    border-radius: 0.15rem;
                    margin-right: 0.15rem;
                    box-sizing: border-box;
                    i{
                        height: 0.2rem;
                        margin-left: 0.02rem;
                        font-size: 0.1rem;
                        // @include fcc;
                        line-height: 0.225rem;
                        
                    }

                }
            }
            .p2{
                .s1{
                    color: $cAE2C2E;
                    font-size: 0.28rem;
                    margin-right: 0.05rem;
                }
                .s2{
                    color: $cAE2C2E;
                    font-size: 0.38rem;
                    font-weight: 600;
                }
            }
            .d2{
                height: 0.2rem;;
                width: 100%;
                @include fcc;
                .s1{
                    flex: 1;
                    @include fcc;
                    font-size: 0.16rem;
                    color: $c6E6E6E

                }
                .s2{
                    flex: 1;
                    @include fcc;
                    font-size: 0.16rem;
                    color: $c6E6E6E;
                }
                img{
                    margin-left: 0.05rem;
                    height: 0.14rem;;
                }
            }
            .d3{
                flex:1;
                width: 100%;
                @include fcc;
                padding-bottom: 0.15rem;
                box-sizing: border-box;
                span{
                    flex: 1;
                    @include fcc;
                    font-size: 0.13rem;
                    color: $c9E9D9C;
                }

            }
            
        }
        
        .middle{
            height: auto;
            background: $cffffff;;
            // padding-top: .85rem;
            .t{
                height: 1rem;
                background: $cAF8760;

            }
            .w{
                @include fcc;
                .d1{
                    height: 2.6rem;
                    flex: 1;
                    // padding-top: .6rem
                }
                #d1,#d2{
                    // height: 2.6rem;
                    margin-top: 0.4rem;
                    width: 100%;;
                }
                #d1{
                    border-right: 0.01rem solid $cF7F3F0;
                }
            }
            

        }
        .middle2{
            background: $cffffff;;
            @include fcc;
            height: 0.4rem;
            font-size: 0.1rem;
            div{
                height: 100%;;
                flex: 1;
                @include fcc;
                color: $c6E6E6E

            }
            .d1{
                border-right: 0.01rem solid $cF7F3F0;
            }
        }
        .info_li{
            // width: 100%;
            height: 1.21rem;
            // background: $cffffff;
            background-image: url(../assets/index_bg.png);
            background-size:cover;
            background-repeat:no-repeat;
            margin:0 0.15rem;
            @include fms;
            .d1{
                width: 100%;
                height: 0.6rem;
                @include fcc;
                justify-content: flex-start;
                .s1{
                    margin-left: 0.2rem;
                    .s11{
                        font-size: 0.14rem;
                        color: $cAE2C2E;
                        font-weight: 600;
                    }
                    .s12{
                        color: $cAE2C2E;
                        font-size: 0.24rem;
                        font-weight: 600;
                    }
                }
                .s2{
                    font-size: 0.1rem;
                    color: $c6D473A;
                    width: auto;
                    padding: 0 0.05rem;
                    background: $cF7F3F0;
                    height: 0.2rem;
                    border-radius: 0.1rem;
                    padding: 0 0.05rem;
                    margin-left: 0.08rem;
                    @include fcc;
                }
            }
            .d2{
                height: 0.2rem;;
                width: 100%;
                @include fcc;
                .s1{
                    flex: 1;
                    @include fcc;
                    font-size: 0.16rem;
                    color: $c6E6E6E

                }
                .s2{
                    flex: 1;
                    @include fcc;
                    font-size: 0.16rem;
                    color: $c6E6E6E;
                }
                img{
                    margin-left: 0.05rem;
                    height: 0.14rem;;
                }
            }
            .d3{
                flex:1;
                width: 100%;
                @include fcc;
                padding-bottom: 0.15rem;
                box-sizing: border-box;
                span{
                    flex: 1;
                    @include fcc;
                    font-size: 0.13rem;
                    color: $c9E9D9C;
                }

            }
        }
    }
    // 人次
    .rc{
       // height: 100%;
        flex: 1;
        display: flex;
        flex-direction: column;
        .h1{
            height: 0.2rem;;
            background: $cF2D634;
            padding: 0 0.15rem;
            color: $c6D473A;
            font-size: 0.1rem;
            @include fcs;
            .s{
                height: 0.1rem;
                width: 0.1rem;
                border-radius: 50%;
            }
            .s1{
                background: $cC56535;
            }
            .s2{
                background: $c55789E;
            }
            .s3{
                background: $c5C9252;
            }
            .s4{
                background: $c876048;
            }
        }
        #d3{
            background: $cAF8760;
            height: 2.5rem;
            
        }
        .info{
            height: 0.8rem;
            background: $cffffff;
            margin-bottom: 0.1rem;
            @include fcc;
            div{
                height: 100%;
                box-sizing: border-box;
                @include fmc;
                .p1{
                    font-size: 0.16rem;
                    color: $c6E6E6E;
                    font-weight: 600;
                }
                .p2{
                    font-size: 0.13rem;
                    color:$c9E9D9C;
                }
                img{
                    width: 0.1rem;
                }
            }
            .d1{
                width: 1.6rem;
                border-bottom: 0.02rem solid $cD7C3AC;
                border-right: 0.01rem solid $cF7F3F0;
            }
            .d2{
                width: 1.6rem;
                border-bottom: 0.02rem solid $cD7C3AC;
                border-right: 0.01rem solid $cF7F3F0;
            }
            .d3{
                flex: 1;
                .s1{
                    height: 0.4rem;
                    width: 0.2rem;
                    font-size: 0.12rem;
                    // @include fms;
                    text-align: center;
                    padding:  0.02rem 0;
                    background: red;
                    line-height: 0.14rem;
                    color: $cffffff;
                    background: $c8B5016;
                    border-radius: 0.1rem;
                    i{
                        font-size: 0.1rem;
                    }
                }
            }
            


        }

        .list{
            
            height: calc(100% - 0.4rem - 0.8rem - 2.7rem);
            // padding-bottom: 0.5rem;
            box-sizing: border-box;
            overflow: auto;
            .li{
                margin-bottom: 0.01rem;
                height: 0.7rem;
                background: $cffffff;
                @include fcs;
                padding:  0 0.2rem;
                img{
                    height: 0.16rem;
                    width: 0.16rem;
                }
                .d1{
                    height: 0.28rem;
                    width: 0.28rem;
                    background: $cF7F3F0;
                    color: $cAF8760;
                    @include fcc;
                    font-size: 0.14rem;
                    font-weight: 600;

                }
                .d2{
                    text-align: left;
                    flex: 1;
                    margin-left: 0.2rem;
                    .p1{
                        text-align: left;
                        color: $c6E6E6E;
                        font-size: 0.17rem;
                        font-weight: 600;
                    }
                    .p2{
                        text-align: left;
                        color: $c9E9D9C;
                        font-size: 0.13rem;
                    }
                }
                .d3{
                    text-align: right;
                    // flex: 1;
                    margin-left: 0.2rem;
                    .p1{
                        color: $c6E6E6E;
                        font-size: 0.12rem;
                        font-weight: 600;
                        
                    }
                    .p2{
                        margin-top: 0.1rem;
                        color: $c6E6E6E;
                        font-size: 0.12rem;
                        font-weight: 600;

                    }
                    span{
                        height: 0.08rem;
                        width: 0.08rem;
                        border-radius: 50%;
                        display: inline-block;
                    }
                }

            }
        }
    }
    // 选择科室
    .pop{
        height: 100%;
        box-sizing: border-box;
        // padding-top: 0.3rem;
        width: 3rem;
        background: $cffffff;
        .pop_title{
            padding-top: 0.25rem;
            height: 0.45rem;
            @include fcs;
            font-size: 0.13rem;
            color: $c9E9D9C;
            border-bottom: 0.01rem solid $cF7F3F0;
            background: $cffffff;
            padding: 0.25rem 0.2rem 0 0.2rem;
            .s1{
                background: $c8B5016;
                color: $cffffff;
                font-size: 0.17rem;
                width: 0.77rem; 
                height: 0.3rem;
                border-radius: 0.15rem;
                @include fcc;
            }
        }
        .warp{
            height: calc(100% - 0.7rem - 1.3rem);
            overflow: auto;
            // display: flex;
            // flex-wrap: wrap;
            // justify-content: space-between;
            // align-items: flex-start;
            .pop_li{
                float: left;
                height: 0.38rem;
                width: 0.88rem;
                margin: 0.1rem 0.06rem;
                @include fcc;
                background: $cEFE7DF;
                // border-bottom: 0.01rem solid $cababab;
                font-size: 0.13rem;
                // font-weight: 600;
                // background: $cffffff;
                color: $c6E6E6E;
                border-radius: 0.05rem;
                

            }
            .d1{
                width: 100%;
                height: 0.6rem;;
            }
        }
        .sub{
            height: 1.28rem;
            @include fcc;
            span{
                height: 0.48rem;
                width: 2.6rem;
                @include fcc;
                background: $cEFE7DF;
                font-size: 0.18rem;
                color: $cAF8760;

            }

        }
        .bingo{
            color: $cAF8760 !important;
            background-image: url('../assets/12.png') !important;
            background-position:bottom right !important;
            background-repeat:no-repeat !important;
            // background-attachment:fixed !important;
            
        }
        
    }
    // 药占比
    
     .yzb{
        .top3{
            height: 1.3rem;
            background: $cAF8760;
            .van_swip{
                height: 1.7rem;
                
                .li{
                    // width: 3.44rem;
                    height: 1.6rem;
                    background: $cffffff;
                    border-radius: 0.1rem;
                    // background-image: url(../assets/zhyb_bg.png);
                    background-size:cover;
                    background-repeat:no-repeat;
                    margin:0 0.12rem;
                    box-shadow:0 0 .08rem #999999;
                    .d1{
                        font-size: 0.16rem;
                        color: $c6E6E6E;
                        font-weight: 600;
                        padding:  0 0.2rem ;
                        padding-top: 0.17rem;
                    }
                    .d0{
                        width: 100%;
                        height: 0.6rem;
                        @include fcc;
                        justify-content: flex-start;
                        .s1{
                            margin-left: 0.2rem;
                            .s11{
                                font-size: 0.14rem;
                                color: $cAE2C2E;
                                font-weight: 600;
                            }
                            .s12{
                                color: $c6E6E6E;
                                font-size: 0.38rem;
                                font-weight: 600;
                            }
                        }
                        
                    }
                    .d2{
                        height: 0.2rem;;
                        width: 100%;
                        @include fcc;
                        .s1{
                            flex: 1;
                            @include fcc;
                            font-size: 0.16rem;
                            color: $c6E6E6E

                        }
                        .s2{
                            flex: 1;
                            @include fcc;
                            font-size: 0.16rem;
                            color: $c6E6E6E;
                        }
                        img{
                            margin-left: 0.05rem;
                            height: 0.14rem;;
                        }
                    }
                    .d3{
                        flex:1;
                        width: 100%;
                        @include fcc;
                        padding-bottom: 0.15rem;
                        box-sizing: border-box;
                        span{
                            flex: 1;
                            @include fcc;
                            font-size: 0.13rem;
                            color: $c9E9D9C;
                        }

                    }
                }
                
            }
        }
        .top4{
            height: 0.8rem;;
            background: $cffffff;
            margin-bottom: 0.1rem;
        }
        .list{ 
            // height: calc(100% - 0.4rem - 0.8rem - 2.7rem);
            height: auto;
            padding-bottom: 0.5rem;
            box-sizing: border-box;
            // overflow: auto;
            .li{
                margin-bottom: 0.01rem;
                height: 0.7rem;
                background: $cffffff;
                @include fcs;
                padding:  0 0.2rem;
                img{
                    height: 0.16rem;
                    width: 0.16rem;
                }
                .d1{
                    height: 0.28rem;
                    width: 0.28rem;
                    background: $cF7F3F0;
                    color: $cAF8760;
                    @include fcc;
                    font-size: 0.14rem;
                    font-weight: 600;

                }
                .d2{
                    text-align: left;
                    flex: 1;
                    margin-left: 0.2rem;
                    .p1{
                        text-align: left;
                        color: $c9E9D9C;
                        font-size: 0.13rem;
                    }
                    .p2{
                        text-align: left;
                        color: $c6E6E6E;
                        font-size: 0.17rem;
                        font-weight: 600;

                    }
                }
                .d3{
                    text-align: right;
                    // flex: 1;
                    margin-left: 0.2rem;
                    .p1{
                        text-align: left;
                        color: $c6E6E6E;
                        font-size: 0.12rem;
                        font-weight: 600;
                        
                    }
                    .p2{
                        text-align: left;
                        margin-top: 0.1rem;
                        color: $c6E6E6E;
                        font-size: 0.12rem;
                        font-weight: 600;

                    }
                    span{
                        height: 0.08rem;
                        width: 0.08rem;
                        border-radius: 50%;
                        display: inline-block;
                    }
                }

            }
        }


    }
    // 次均
    .cj{
        flex: 1;
        display: flex;
        flex-direction: column;
        #d4{
            background: $cAF8760;
            height: 2.5rem;
            
        }
        .info{
            height: 0.8rem;
            background: $cffffff;
            margin-bottom: 0.1rem;
            @include fcc;
            div{
                height: 100%;
                box-sizing: border-box;
                @include fmc;
                .p1{
                    font-size: 0.16rem;
                    color: $c6E6E6E;
                    font-weight: 600;
                }
                .p2{
                    font-size: 0.13rem;
                    color:$c9E9D9C;
                }
                img{
                    width: 0.1rem;
                }
            }
            .d1{
                width: 1.6rem;
                border-bottom: 0.02rem solid $cD7C3AC;
                border-right: 0.01rem solid $cF7F3F0;
            }
            .d2{
                width: 1.6rem;
                border-bottom: 0.02rem solid $cD7C3AC;
                border-right: 0.01rem solid $cF7F3F0;
            }
            .d3{
                flex: 1;
                .s1{
                    height: 0.7rem;
                    width: 0.2rem;
                    font-size: 0.12rem;
                    // @include fms;
                    text-align: center;
                    padding:  0.02rem 0;
                    background: red;
                    line-height: 0.14rem;
                    color: $cffffff;
                    background: $c8B5016;
                    border-radius: 0.1rem;
                    i{
                        font-size: 0.1rem;
                    }
                }
            }


        }
        
    }
}
	
  
  
  
</style>
