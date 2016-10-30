/**
 * 球赛信息
 */
define( function(require, exports, module) {
	// 引用jQuery模块
	var $ = require('jquery');
	var utils = require("football/utils/utils");
	require("bootstrap");
	var base = require("football/utils/base");
	var dialog = require("football/utils/my-dialog");
	module.exports = {
		renderLeagues : function() {
			dialog.showLoading();
			var url = base.baseUrl + "web/m/findAllLeagues";
			$.ajax({
		    	type: "post",
				url: url,
				//data : {"channelId" : channelId, "date": date},
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				dataType : "json"
			    }).done(
			    	function(result){
			    		dialog.closeLoading();
			    		if(utils.checkResult(result ,"服务器异常")){							
			    			utils.showData4Template("leaguesContent",result,"leagues");
			    			try{
			    				var iframe = parent.document.getElementById("leaguesIframe");
			    				parent.setIframeHeight(iframe);
			    			}catch(e){
			    				
			    			}
			    			
			    		}
			    }).fail(
		    		function(XMLHttpRequest, textStatus, errorThrown){ 
		    			dialog.closeLoading();
		    			dialog.myAlert("服务器异常");
			    });
		}
	}
});