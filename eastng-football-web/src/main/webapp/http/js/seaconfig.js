
 define("jquery", [], function(require, exports, module) {
	 module.exports = window.jQuery;
 });
seajs.config({
	 //设置路径
    /*paths: {
        'gallery': 'https://a.alipayobjects.com/gallery'
    },*/
	alias: { 
		'jquery': 'jquery',
		// 相对seajs的目录 template.js
		'artTemplate':'template'
	},

	//变量的定义 example if you use path 'zh-cn' ,you can use ${locale} 
	vars: {
		'locale': 'zh-cn'
  	}
});