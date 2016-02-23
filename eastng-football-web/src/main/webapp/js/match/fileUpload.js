function uploadOddFile(){
				 $.ajaxFileUpload({
				        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				        url:'../uploadOddFile',
				        secureuri:false,                       //是否启用安全提交,默认为false
				        fileElementId:'oddFile',           //文件选择框的id属性
				        dataType:'text',                       //服务器返回的格式,可以是json或xml等
				        success:function(data, status){        //服务器响应成功时的处理函数
				        	alert(data);
				        },
				        error:function(data, status, e){ //服务器响应失败时的处理函数
				        	alert(status);
				        }
				    });
			}