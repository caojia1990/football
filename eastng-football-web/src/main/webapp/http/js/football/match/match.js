/**
 * 球赛信息
 */
define( function(require, exports, module) {
	// 引用jQuery模块
	var $ = require('jquery');
	var utils = require("football/utils/utils");
	require("bootstrap");
	var base = require("football/utils/base");
	var template = require('template');
	var matchCanvas = require("football/match/canvas-matchs");
	var dialog = require("football/utils/my-dialog");
	template.helper('getMatchDateTime', function (value) {
		//if '' is default yyyy-MM-dd hh:mm:ss
		var date = new Date(value);
		return utils.formatDate(date ,'');
	});
	var matchNo = utils.getUrlParam("matchNo");
	
	var findInfo = function(){
		dialog.showLoading();
		var url = base.baseUrl + "web/m/findHistoryMatch";
		if(matchNo == ""){
			dialog.myAlert("mactchNo is null");
		}
		$.ajax({
	    	type: "post",
			url: url,
			data : {"matchNo" : matchNo},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json"
		    }).done(
		    	function(result){
		    		dialog.closeLoading();
		    		if(utils.checkResult(result ,"服务器异常")){
		    			var match = result.responseBody.match;
		    			utils.showData4Template("historyContent",result,"historyHtml");		    			
		    			utils.showData4Template("hostHistoryContent",result,"hostHistoryHtml");
		    			utils.showData4Template("guestHistoryContent",result,"guestHistoryHtml");
		    			$("#matchInfo").text(match.hostShortName + "VS" + match.guestShortName);
		    			var canvasText = "";
		    			if(match.matchStatus == 2){
		    				canvasText = match.hostGoal + "  :  " + match.guestGoal;
		    			}else if(match.matchStatus == 0){
		    				var date = new Date(match.matchTime);
		    				canvasText = utils.formatDate(date ,'')
		    			}else if(matchStatus == 1){
		    				canvasText = "VS";
		    			}
		    			matchCanvas.renderImage(base.baseUrl + "http/images/background.jpg",
		    					base.baseUrl + "http/images/yc.jpg",
		    					base.baseUrl + "http/images/xj.jpg" , canvasText);
		    		}
		    }).fail(
	    		function(XMLHttpRequest, textStatus, errorThrown){
	    			dialog.closeLoading();
	    			dialog.myAlert("服务器异常");
		    });
	};
	
	module.exports = {
		renderLeagues : function() {
			findInfo();
		}
	}
});