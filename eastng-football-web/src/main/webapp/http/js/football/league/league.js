/**
 * 球赛信息
 */
define( function(require, exports, module) {
	// 引用jQuery模块
	var $ = require('jquery');
	var utils = require("football/utils/utils");
	//require("bootstrap");
	var template = require('template');
	var base = require("football/utils/base");
	var dialog = require("football/utils/my-dialog");
	template.helper('getMatchStatus', function (value) {
		//'0：未开始\n            1：比赛中\n            2：已结束',
		if(parseInt(value) === 0){
			return "未开始";
		}else if(parseInt(value) === 1){
			return "比赛中";
		}else if(parseInt(value) === 2){
			return "已结束";
		}
	});
	template.helper('getMatchDateTime', function (value) {
		//if '' is default yyyy-MM-dd hh:mm:ss
		var date = new Date(value);
		return utils.formatDate(date ,'');
	});
	template.helper('getMatchTime', function (value) {
		//if '' is default yyyy-MM-dd hh:mm:ss
		var date = new Date(value);
		return utils.formatDate(date ,'hh:mm');
	});
	var renderLeague = function(leagueNo){
//		utils.getUrlParam("leagueNo");
		dialog.showLoading();
		var url = base.baseUrl + "web/m/findLeaguesByleagueNo";
		$.ajax({
	    	type: "GET",
			url: url,
			data : {"leagueNo" : leagueNo},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json"
		    }).done(
		    	function(result){
		    		dialog.closeLoading();
		    		if(utils.checkResult(result ,"服务器异常")){
		    			var season = result.responseBody.leagueSeason;
		    			$("#leagueInfo").text(season.leagueShortName + "(" + season.seasonName + ")");
		    			utils.showData4Template("matchContent", result, "match");
		    			utils.showData4Template("leagueTableContent", result, "leagueTable");
		    			
		    		}
		    }).fail(
	    		function(XMLHttpRequest, textStatus, errorThrown){ 
	    			dialog.closeLoading();
	    			dialog.myAlert("服务器异常");
		    });
	};
	module.exports = {
		renderLeague : function(leagueNo) {
			renderLeague(leagueNo);
		}
	}
});