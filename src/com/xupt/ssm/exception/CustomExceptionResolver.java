package com.xupt.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by 梁峻磊 on 2017/8/22.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{
    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e 系统抛出异常
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        CustomException customException = null;
        //解析异常类型
        if(e instanceof CustomException){  //是系统自定义异常
            customException = (CustomException) e;
        }else {  //不是系统自定义异常，也就是运行时异常
            customException = new CustomException("未知错误！");
        }
        //错误信息
        String message = customException.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        //错误信息传到页面
        modelAndView.addObject("message",message);
        //指向到错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
