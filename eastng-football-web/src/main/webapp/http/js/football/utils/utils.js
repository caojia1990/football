/**
 * 自定义的一些工具方法
 * <p>
 * 这里需要补充弹出框 的操作
 * </p>
 */
define(function(require, exports, module) {
	var $ = require('jquery');
	var template = require('template');
	var dialog = require("football/utils/my-dialog");
	
	var jumpMatch = function(matchNo){
		window.location.href="match.html?matchNo=" + matchNo;
	}
	var backHistory = function(){
		window.location.href=document.referrer;
	}
	$(function(){
		$(document).delegate('.back', 'click', function(event) {
			backHistory();
		});
		$(document).delegate('.my-match', 'click', function(event) {
			var matchNo = $(this).attr("data-value");
			jumpMatch(matchNo);
		});
	});
	
	
	
	/**
	 * 解析的后端返回的json {"code" : 1 ,"message": "success".......}
	 */
	var checkResult = function(result, message) {
		var code = parseInt(result.code);
		if (code == 1) {
			return true;
		}
		dialog.myAlert(result.message);
		return false;
	};
	var showData4Template = function(templateId, templateData, htmlId) {
		var templateHtml = template(templateId, templateData);
		document.getElementById(htmlId).innerHTML = templateHtml;
		// $("#" + htmlId).append(templateHtml);
	};
	/**
	 * 获取地址栏的参数
	 * 
	 * @param name
	 *            地址栏参数的名称
	 */
	var getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); // 匹配目标参数
		if (r != null){
			var value = unescape(r[2]);
			if(endWith(value, "#")){
				return str.subString(0, str - 1);
			}
			return unescape(r[2]);
		}
			
		return null; // 返回参数值
	}
	
	var endWith = function(str , endStr){
		return str.indexOf(endStr, str.length - endStr.length) !== -1;		
	}
	var formatDate = function(date,fmt){ 
			// author: meizz
		  // yyyy-MM-dd hh:mm:ss
		  if(fmt == null || fmt == "")
		  	fmt = 'yyyy-MM-dd hh:mm:ss';
		  var o = {   
		    "M+" : date.getMonth()+1,                 // 月份
		    "d+" : date.getDate(),                    // 日
		    "h+" : date.getHours(),                   // 小时
		    "m+" : date.getMinutes(),                 // 分
		    "s+" : date.getSeconds(),                 // 秒
		    "q+" : Math.floor((date.getMonth()+3)/3), // 季度
		    "S"  : date.getMilliseconds()             // 毫秒
		  };   
		  if(/(y+)/.test(fmt))   
			  fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
			  if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
	} 
	module.exports = {
		checkResult : function(result, message) {
			return checkResult(result, message);
		},
		showData4Template : function(templateId, templateData, htmlId) {
			showData4Template(templateId, templateData, htmlId);
		},
		getUrlParam : function(name) {
			return getUrlParam(name);
		},
		formatDate : function(dateTime ,format){
			return formatDate(dateTime, format);
		}
	};
});
