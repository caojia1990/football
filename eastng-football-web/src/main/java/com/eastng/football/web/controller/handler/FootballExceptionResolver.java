package com.eastng.football.web.controller.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.eastng.football.api.constant.StatusConstant;
import com.eastng.football.api.exception.FootballException;
import com.eastng.football.api.result.Result;
import com.eastng.football.web.controller.bean.JsonResponse;


/**
 * 
 * @author huang.xiaolong
 * @date 2016-07-26 10:14:31
 * @description http://www.oschina.net/code/snippet_2357069_51863
 */
public class FootballExceptionResolver extends SimpleMappingExceptionResolver {

	@SuppressWarnings("rawtypes")
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		HandlerMethod mathod = (HandlerMethod) handler;
		ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
		StringBuilder errorMsg = new StringBuilder();
		ex.printStackTrace();
		errorMsg.append(mathod.getMethod().getName()).append(" error cause : ")
				.append(ex.getMessage());
		ex.printStackTrace();
		logger.error(errorMsg.toString());
		// 判断有没有@ResponseBody的注解没有的话调用父方法
		if (body == null) {
			return super.doResolveException(request, response, handler, ex);
		}
		ModelAndView mv = new ModelAndView();
		// 设置状态码
		// response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		// 设置ContentType
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// 避免乱码
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		// 测试 是不是 BaseException的子类（SmtHttpException 是不是 BaseException 的父类 ）
		JsonResponse jsonResponse = new JsonResponse();
		if (Result.class.isAssignableFrom(ex.getClass())) {
			FootballException exception = (FootballException) ex;
			jsonResponse.setCode(exception.getCode());
			jsonResponse.setMessage(exception.getMessage());
		} else {
			jsonResponse.setCode(StatusConstant.Code.error);
			jsonResponse.setMessage(StatusConstant.Message.error);
		}
		try {
			response.getWriter().write(JSON.toJSONString(jsonResponse));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mv;
	}

	public static void main(String[] args) {
		
	}
}
