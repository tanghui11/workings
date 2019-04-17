var issOnlineUrl = "http://127.0.0.1:24010/ZKIDROnline";
var browserFlag = getBrowserType();
/*
$().ready(function(){
	$(document).off("DOMNodeInserted","#cert_message_type");
	$("#cert_message_type").on("DOMNodeInserted",function(e){
		openMessage($("#cert_message_type").text(), $("#cert_message").text());
	});
});
*/
//身份证
//includeScript("baseISSObject.js", function(){
//includeScript("baseISSOnline.js", function(){
	var setting = {
			Cert : {
				callBack : function(result){
					setCertificateData(result);
				},
				select : "#button_readID"
			},
			Methods : {
				showMessage : function(type,message){
					$("#cert_message").text(message);
					$("#cert_message_type").text(msgType[type]);
				},
				downloadDrive : function(){
					$.jBox.closeTip();
					messageBox({messageType: "confirm", text: "请安装相关硬件驱动！点击确定下载驱动。", 
						callback: function(result){
							if(result)
							{
								window.location.href = "middleware/ZKIDROnline.exe";
							}
							closeMessage();
					}});
				}
			}
		}
		createISSonlineDevice(setting);
//});
//});

function setCertificateData(result)
{
	$("#birthday").val(result.Certificate.Birthday.slice(0,4)+'-'+result.Certificate.Birthday.slice(4,6)+'-'+result.Certificate.Birthday.slice(6,8));
	$("#certificateNo").val(result.Certificate.IDNumber);
	$("#idIssued").val(result.Certificate.IDIssued);
	$("#issuedValidDate").val(result.Certificate.IssuedData+"-"+result.Certificate.ValidDate);
//转base64为路径(开头)
	imgData =result.Certificate.Base64Photo;
	var url = "image/jpg;base64,"+imgData
	var idcard = result.Certificate.IDNumber;
	
	sumitImageFile(url)
	function sumitImageFile(base64Codes){  
		var form=$("#signupForm")  

		var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数  

		//convertBase64UrlToBlob函数是将base64编码转换为Blob  
		formData.append("uploadfile",convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同  
		formData.append("idcard",idcard);
		//ajax 提交form  
		$.ajax({  
			url : contextPath+"/common/idcardUpload",  
			type : "POST",  
			data : formData,  
			dataType:"json",  
			processData : false,         // 告诉jQuery不要去处理发送的数据  
			contentType : false,        // 告诉jQuery不要去设置Content-Type请求头  

			success:function(data){  
				$("#id_img_pers").attr("src",data.filePath[0]);
				$("#idcardPic").val(data.filePath[0])
			},  
			xhr:function(){            //在jquery函数中直接使用ajax的XMLHttpRequest对象  
				var xhr = new XMLHttpRequest();  

				xhr.upload.addEventListener("progress", function(evt){  
					if (evt.lengthComputable) {  
						var percentComplete = Math.round(evt.loaded * 100 / evt.total);    
						//console.log("正在提交."+percentComplete.toString() + '%');        //在控制台打印上传进度  
					}  
				}, false);  

				return xhr;  
			}  

		});  
	}  

		/**  
		 * 将以base64的图片url数据转换为Blob  
		 * @param urlData  
		 *            用url方式表示的base64图片数据  
		 */  
		function convertBase64UrlToBlob(urlData){  

			var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte  

			//处理异常,将ascii码小于0的转换为大于0  
			var ab = new ArrayBuffer(bytes.length);  
			var ia = new Uint8Array(ab);  
			for (var i = 0; i < bytes.length; i++) {  
				ia[i] = bytes.charCodeAt(i);  
			}  

			return new Blob( [ab] , {type : 'image/png'});  
		}
//转base64为路径(结束)
	
	//$("#id_img_pers").attr("src","data:image/jpg;base64,"+imgData);
	$("#personIdPhoto").val(imgData);
	$("#personPhoto").val("");
	
	$("#name").val(result.Certificate.Name);
	//对应性别
	var genderO = $("#gender option");
	for(var i = 0; i < genderO.length; i++){
		var tmp = genderO[i].text;
		if(tmp == result.Certificate.Sex){
			genderO[i].selected = 'selected';
			break;
		}
	}
	//$("#gender").val(result.Certificate.Sex);
	//$("#nation").val(result.Certificate.Nation);
	//对应性别民族
	var nationO = $("#nation option");
	for(var i = 0; i < nationO.length; i++){
		var tmp = nationO[i].text;
		if(tmp == result.Certificate.Nation){
			nationO[i].selected = 'selected';
			break;
		}
	}
	//证件类别
	var certificateTypeO = $("#certificateType option");
	for(var i = 0; i < certificateTypeO.length; i++){
		var tmp = certificateTypeO[i].text;
		if(tmp == "内地居民身份证"){
			certificateTypeO[i].selected = 'selected';
			break;
		}
	}
	$("#address").val(result.Certificate.Address);
	$("#nativePlace").val(result.Certificate.Address.substring(0,result.Certificate.Address.indexOf("市")+1));
	CC2PY('name','pinyin');
	
}
	
function getRandomNum() 
{
    var random = parseInt(Math.random() * 10000);
    return random;
}

//消息控件的使用类型的类
var msgType = 
{
	info : "info",
	success : "success",
	warning : "warning",
	error : "error",
	loading : "loading"
};

function getBrowserType()
{
	var browserFlag = "";
	 //是否支持html5的cors跨域
    if (typeof(Worker) !== "undefined")
    {
        browserFlag = "html5";
    }
    //此处判断ie8、ie9
    else if(navigator.userAgent.indexOf("MSIE 7.0")>0||navigator.userAgent.indexOf("MSIE 8.0")>0 || navigator.userAgent.indexOf("MSIE 9.0")>0)
    {
        browserFlag = "simple";
    }
    else
	{
		browserFlag = "upgradeBrowser";//当前浏览器不支持该功能,请升级浏览器
	}
    return browserFlag;
}


function openMessage(type, text, ptimeout)
{ 
	text = (text == "" ? null : text);
	var timeout = 1000;
	if(type == msgType.warning || type == msgType.info)//警告
	{
		timeout = 3000;
	}
	else if(type == msgType.success)//成功 
	{
		
		text = (text && text != null ? text : "操作成功");//${common_op_succeed}:操作成功
		var num = strlen(text)/30;
		num = num > 8 ? 8 : num;
		timeout = Math.ceil(num) * timeout;//动态判断显示字符数的长度来延长显示时间
	}
	else if(type == msgType.error)//失败
	{
		text = (text && text != null) ? text : "操作失败";//${common_op_failed}:操作失败，程序出现异常
		timeout = 3000;
	}
	else if(type == msgType.loading)//处理中
	{
		timeout = 0;//当为'loading'时，timeout值会被设置为0，表示不会自动关闭。
		text = text && text != null ? text : "处理中";//${common_op_processing}:处理中
	}
	var width = strlen(text) * 6.1 + 45;//按字符计算宽度
	timeout = ptimeout ? ptimeout : timeout;
	$.jBox.tip(text, type,{timeout: timeout, width: (width > 400 ? 400 : "auto")});//设定最大宽度为400
}


function closeMessage(timeout)
{
	timeout = timeout ? timeout : 1000;
	window.setTimeout("$.jBox.closeTip();", timeout);//设定最小等待时间
}

function strlen(str)
{  
    var len = 0;  
    if(str != null)
    {
   		for (var i=0; i<str.length; i++)
    	{   
			var c = str.charCodeAt(i);
			if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) 
			{
				len++;   
			}	
			else 
			{
				len+=2;   
			}
    	}
    }
    return len;
}  

function messageBox(paramsJson)
{ 

	this.messageType = paramsJson.messageType ? $.trim(paramsJson.messageType) : "confirm";
	this.types = "";
  	if(paramsJson.type)
  	{
  		this.typeArray = paramsJson.type.split(" ");
	  	for(var i=0; i<this.typeArray.length; i++)
	  	{
  			this.types += this.typeArray[i]+" ";
	  	}
  	}
  	switch(this.messageType)
	{
		case "confirm":
		  	$.jBox.confirm(paramsJson.text, "提示", function(v, h, f) {
	     		if (v == "ok") 
	     		{ 
	     	 		paramsJson.callback(true);
	     		}
			});
			break;
	}
}
