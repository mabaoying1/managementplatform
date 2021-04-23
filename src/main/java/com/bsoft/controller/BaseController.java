/**
 * @(#)BaseController Created on 2018-07-03 17:32
 * <p>
 * 版权：版权所有 bsoft.com.cn 保留所有权力。
 */
package com.bsoft.controller;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:yanjie@bsoft.com.cn">yanjie</a>
 * @description
 */
@Component
public abstract class BaseController {


    public static final int PAGE_SIZE = 20;
    public static final int PAGE_NO = 1;
    public static final String  DIM_DATETIEM="DIM0000001";
    /**
     * 描述：参数解析 统计查询功能面板用
     * @param request
     * @param response
     * @return 
     * @author yinxu
     * @return Map<String,Object>
     * @Creating_Time 2019年3月18日
     */
    protected abstract Map<String, Object> getParameterMap(HttpServletRequest request, HttpServletResponse response);
    /**
     * 描述：参数解析 首页面板用
     * @param request
     * @param response
     * @return 
     * @author yinxu
     * @return Map<String,Object>
     * @Creating_Time 2019年3月18日
     */
    protected abstract Map<String, Object> getParameterMapSY(HttpServletRequest request, HttpServletResponse response);
}
