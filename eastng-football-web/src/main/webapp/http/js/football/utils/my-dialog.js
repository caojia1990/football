/**
 * [工具方法库]
 * @param  {[type]} require [description]
 * @param  {[type]} exports [description]
 * @param  {[type]} module) {                   var $ [description]
 * @return {[type]}         [description]
 */
define( function(require, exports, module) {
    //引用jQuery模块
    var $ = require('jquery');
    //基本配置
    var base = require("football/utils/base");
    var baseUrl = base.baseUrl;
    // artDialog  from http://aui.github.io/artDialog/doc/index.html
    var dialog = require("dialog/dialog");
    var loadingDialog ;
    /**
     * [myAlert 警告框]
     * @param  {[type]} message [警告内容]
     * @return {[type]}         [description]
     */
    var myAlert = function(message){
    	dialog({
	        fixed: true,
	        title: '警告',
	        width: 200,
	        content: "<div align='center'>" + message + "</div>"
      	}).showModal();
    };

    /**
     * [myLoading loading Message]
     * @param  {[type]} message [description]
     * @return {[type]}         [description]
     */
    var myLoading = function(message){
    	// var loadingUrl = baseUrl + "images/loading.gif"
    	var defalutMessage = "内容加载中。。。";
    	if(message == ""){
    		message = defalutMessage;
    	}
    	loadingDialog = dialog({fixed: true,content:"<span class='ui-dialog-loading'>Loading..</span>"+ message});
    	loadingDialog.showModal();
    }
    
    /**
     * [changeLoadingMessage 改变loading文字]
     * @param  {[type]} message [description]
     * @return {[type]}         [description]
     */
    var changeLoadingMessage = function(message){
    	// var loadingUrl = baseUrl + "images/loading.gif"
    	message = "<span class='ui-dialog-loading'>Loading..</span>"+ message;
    	loadingDialog.content(message);
    }
    
    /**
     * [colseMyLoading 关闭Loading]
     * @return {[type]} [description]
     */
    var colseMyLoading = function(){
    	loadingDialog.close().remove();
    }

    module.exports = {
    	/**
    	 * [myAlert警告框]
    	 * @param  {[type]} message [description]
    	 * @return {[type]}         [description]
    	 */
    	myAlert : function(message){
    		myAlert(message);
    	},
    	/**
    	 * [getBaseUrl 返回基础地址]
    	 * @return {[type]} [description]
    	 */
    	getBaseUrl : function(){
    		return baseUrl;
    	},
    	/**
    	 * [showLoading 提示message]
    	 * @return {[type]} [description]
    	 */
    	showLoading : function(message){
    		myLoading(message);
    	},
    	/**
    	 * [showLoading 提示加载中]
    	 * @return {[type]} [description]
    	 */
    	showLoading : function(){
			myLoading("");
    	},
    	/**
    	 * [colseLoading description]
    	 * @return {[type]} [description]
    	 */
    	closeLoading : function(){
    		colseMyLoading();
    	},
    	/**
    	 * [changeLoadingMessage 改变message信息]
    	 * @param  {[type]} message [description]
    	 * @return {[type]}         [description]
    	 */
    	changeLoadingMessage : function(message){
    		changeLoadingMessage(message);
    	}
    };
});