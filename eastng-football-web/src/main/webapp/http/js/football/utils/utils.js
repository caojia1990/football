/**
 * 自定义的一些工具方法
 * <p>这里需要补充弹出框 的操作</p>
 */
define( function(require, exports, module) {
	var $ = require('jquery');
	var template = require('template');
	/**
	 * 解析的后端返回的json {"code" : 1 ,"message": "success".......}
	 */
	var checkResult = function(result , message){
		var code = parseInt(result.code);
		if (code == 1) {
			return true;
		}
		alert(result.message);
		return false;
	};
	var showData4Template = function(templateId, templateData, htmlId) {
		var templateHtml = template(templateId, templateData);
		//console.log(templateHtml);
		document.getElementById(htmlId).innerHTML = templateHtml;
		//$("#" + htmlId).append(templateHtml);
	};
	module.exports ={
		checkResult : function(result , message){
			return checkResult(result , message);
		},
		showData4Template : function(templateId, templateData, htmlId){
			showData4Template(templateId, templateData, htmlId);
		}
	};
});
