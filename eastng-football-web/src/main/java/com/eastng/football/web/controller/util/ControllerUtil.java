package com.eastng.football.web.controller.util;

import com.eastng.football.api.exception.ExceptionCode;
import com.eastng.football.web.controller.bean.JsonResponse;

/**
 * Controller的辅助方法
 * 
 * @author laughing
 * @date 2016-09-26 20:56:22
 * 
 */
public class ControllerUtil {

	/**
	 * 错误对象的组装
	 * 
	 * @param jsonResponse
	 * @param errorCode
	 * @param errorMessage
	 * @return
	 */
	public static JsonResponse getErrorJsonResponse(JsonResponse jsonResponse, int errorCode, String errorMessage) {
		jsonResponse.setCode(errorCode);
		jsonResponse.setMessage(errorMessage);
		return jsonResponse;
	}

	/**
	 * <p>
	 * 错误对象的组装
	 * </p>
	 * <p>
	 * message 是默认的"system error"
	 * </p>
	 * 
	 * @param jsonResponse
	 * @param errorCode
	 * @return
	 */
	public static JsonResponse getErrorJsonResponse(JsonResponse jsonResponse, int errorCode) {
		return getErrorJsonResponse(jsonResponse, errorCode, ExceptionCode.SYSTEM_ERROR.getMessage());
	}
}
