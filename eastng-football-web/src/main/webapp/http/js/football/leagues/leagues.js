/**
 * 球赛信息
 */
define( function(require, exports, module) {
	// 引用jQuery模块
	var $ = require('jquery');
	var utils = require("football/utils/utils");
	require("bootstrap");
	var base = require("football/utils/base");
	module.exports = {
		renderLeagues : function() {
			var url = base.baseUrl + "web/m/findAllLeagues";
			$.ajax({
		    	type: "GET",
				url: url,
				//data : {"channelId" : channelId, "date": date},
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				dataType : "json"
			    }).done(
			    	function(result){
			    		if(utils.checkResult(result ,"服务器异常")){							
			    			utils.showData4Template("leaguesContent",result,"leagues");
			    		}
			    }).fail(
		    		function(XMLHttpRequest, textStatus, errorThrown){ 
	    			alert("服务器异常");
			    });
		}
	}
});