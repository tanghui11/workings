/*this is basic form validation using for validation person's basic information author:Clara Guo data:2017/07/20*/
$(document).ready(function(){
/*	$.validator.setDefaults({
		  submitHandler: function(form) {    
		 		form.submit();    
		}       
	}); */ 
	//整数大于0
	jQuery.validator.addMethod("Bgnum",function(value,element){
		var length = value.length;
		return this.optional(element)||(value>0);
	},"请填写正确的11位手机号");
	//手机号码验证身份证正则合并：(^\d{15}$)|(^\d{17}([0-9]|X)$)
	jQuery.validator.addMethod("isPhone",function(value,element){
		var length = value.length;
		var phone=/^1[3|4|5|6|7|8|9][0-9]\d{8}$/;
		return this.optional(element)||(length == 11 && phone.test(value));
	},"请填写正确的11位手机号");
	//电话号码验证
	jQuery.validator.addMethod("isTel",function(value,element){
		var tel = /^(0\d{2,3}-)?\d{7,8}$/g;//区号3,4位,号码7,8位
		return this.optional(element) || (tel.test(value));
	},"请填写正确的座机号码");
	//姓名校验
	jQuery.validator.addMethod("isName",function(value,element){
		var name=/^[\u4e00-\u9fa5]{2,6}$/;
		return this.optional(element) || (name.test(value));
	},"姓名只能用汉字,长度2-4位");
	//校验用户名
	jQuery.validator.addMethod("isUserName",function(value,element){
		var userName=/^[a-zA-Z0-9]{2,13}$/;
		return this.optional(element) || (userName).test(value);
	},'请输入数字或者字母,不包含特殊字符');
	
	//校验身份证
	jQuery.validator.addMethod("isIdentity",function(value,element){
		var id= /^(\d{15}$|^\d{18}$|^\d{17}(\d|X))$/;
		return this.optional(element) || (id.test(value));
	},"请输入正确的15或18位身份证号,末尾为大写X");
	//校验出生日期
	jQuery.validator.addMethod("isBirth",function(value,element){
		var birth = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
		return this.optional(element) || (birth).test(value);
	},"出生日期格式示例2000-01-01");
	//校验新旧密码是否相同
	jQuery.validator.addMethod("isdiff",function(){
		var p1=$("#pwdOld").val();
		var p2=$("#pwdNew").val();
		if(p1==p2){
			return false;
		}else{
			 return true;
		}
		});
	//校验新密码和确认密码是否相同
	jQuery.validator.addMethod("issame",function(){
		var p3=$("#confirm_password").val();
		var p4=$("#pwdNew").val();
		if(p3==p4){
			return true;
		}else{
			 return false;
		}
		});
	jQuery.validator.addMethod("isZipCode", function(value, element) {    
	  var zip = /^[0-9]{6}$/;    
	  return this.optional(element) || (zip.test(value));    
	}, "请正确填写您的邮政编码!");        

	jQuery.validator.addMethod("numFormart", function(value, element, params) {
		alert(value)
		var re = /^(0|[1-9]\d*)(\.\d{1,2})?$/;
		var result = re.test(value);
		return result;

	}, "金额不能以0开头");

	// 手机号码验证    
	jQuery.validator.addMethod("isMobile", function(value, element) {    
	  var length = value.length;    
	  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/.test(value));    
	}, "请正确填写您的手机号码!");

	

	// 用户名字符验证    
	jQuery.validator.addMethod("userName", function(value, element) {    
	  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
	}, "用户名只能包括中文字、英文字母、数字和下划线!");   

	

	// IP地址验证   
	jQuery.validator.addMethod("ip", function(value, element) {    
	  return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
	}, "请填写正确的IP地址！");

	//用于判断是否是给定数字的整数倍
	jQuery.validator.addMethod("times", function(value, element,param) {    
	  return this.optional(element) || Number(value)%Number(param) == 0;    
	}, jQuery.validator.format("必须是{0}的整数倍！")); 

	//用于判断账户余额
	jQuery.validator.addMethod("accountMax",function(value,element,param){
		return Number(value) <= Number(param);
	},jQuery.validator.format("账户余额不足！"));

	//用于当前用户可投额度
	jQuery.validator.addMethod("tenderMax",function(value,element,param){
		return Number(value) <= Number(param);
	},jQuery.validator.format("不能大于当前可投额度！"));

	//用于判断是否是给定数字的整数倍
	jQuery.validator.addMethod("bigtimes", function(value, element,param) {    
	  return this.optional(element) || Number(value) * 10000 % Number(param) == 0;    
	}, jQuery.validator.format("必须是{0}的整数倍！")); 

	//用于判断一个输入框的值小于另外一个输入框的
	jQuery.validator.addMethod("smaller", function(value, element, param) {
		var target = $( param );
	//  if ( this.settings.onfocusout ) {
	//      target.unbind( ".validate-equalTo" ).bind( "blur.validate-equalTo", function() {
	//          $( element ).valid();
	//      });
	//  }
		return this.optional(element) || Number(value) <= Number(target.val());
	}, jQuery.validator.format("请正确填写"));

	jQuery.validator.addMethod("isNoChinese", function(value, element) {
		var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
		return this.optional(element) || (!reg.test(value) && /^[\u0391-\uFFE5\w]+$/.test(value)) ;
	}, "用户名必须由字母、数字或“_”组成");

	//用于判断昵称是字母、数字、特殊字符或“_”组成
	jQuery.validator.addMethod("isNickName", function(value, element) {
		var reg = /^[\w][\w@.]+$/;
		return this.optional(element) || reg.test(value);
	}, "用户名必须由字母、数字或特殊符号(@_.)组成");

	//用于判断是否含有空格
	jQuery.validator.addMethod("isNoSpace", function(value, element) {
		var reg = /^[^ ]+$/;
		return this.optional(element) || reg.test(value);
	}, "密码中不能含有任何空格");

	jQuery.validator.addMethod("isValidBizUserId", function(value, element) {
		var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
		return this.optional(element) || (!reg.test(value) && /^[\u0391-\uFFE5\w]+$/.test(value)) ;
	}, "引荐人工号格式不正确");

	//密码复杂度校验
	jQuery.validator.addMethod("complexity", function(value, element) {  
		var reg = /^(?=.*\d)(?=.*[a-zA-Z])[\S]+$/;
		return this.optional(element) || reg.test(value);    
	}, jQuery.validator.format("密码过于简单，至少要字母和数字的组合"));

	//登录密码复杂度校验
	jQuery.validator.addMethod("loginPwdComplexity", function(value, element) {  
		var reg = /^(?=.*\d)(?=.*[a-zA-Z])[\S]+$/;
		return this.optional(element) || reg.test(value);    
	}, jQuery.validator.format("登录密码为8~16位数字和字母的组合")); 

	//金额校验
	jQuery.validator.addMethod("money", function(value, element) {  
		var reg = /^[0-9]+(.[0-9]{1,2})?$/;
	  return this.optional(element) || reg.test(value);    
	}, jQuery.validator.format("格式有误，只允许输入两位小数")); 

	jQuery.validator.addMethod("yearRate", function(value, element) {  
		var reg = /^[0-9]+(.[0-9]{1})?$/;
	  return this.optional(element) || reg.test(value);    
	}, jQuery.validator.format("格式有误，只允许输入1位小数")); 

	//年龄 表单验证规则
	jQuery.validator.addMethod("age", function(value, element) {   
		var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
		return this.optional(element) || (age.test(value));
	}, "不能超过120岁"); 
	///// 20-60   /^([2-5]\d)|60$/

	//传真
	jQuery.validator.addMethod("fax",function(value,element){
		var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
		return this.optional(element) || (fax.test(value));
	},"传真格式如：0371-68787027");

	//验证当前值和目标val的值相等 相等返回为 false
	jQuery.validator.addMethod("equalTo2",function(value, element){
		var returnVal = true;
		var id = $(element).attr("data-rule-equalto2");
		var targetVal = $(id).val();
		if(value === targetVal){
			returnVal = false;
		}
		return returnVal;
	},"不能和原始密码相同");

	//大于指定数
	jQuery.validator.addMethod("gt",function(value, element){
		var returnVal = false;
		var gt = $(element).data("gt");
		if(value > gt && value != ""){
			returnVal = true;
		}
		return returnVal;
	},"不能小于0 或空");

	//汉字
	jQuery.validator.addMethod("chinese", function (value, element) {
		var chinese = /^[\u4E00-\u9FFF]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "格式不对");

	//指定数字的整数倍
	jQuery.validator.addMethod("times", function (value, element) {
		var returnVal = true;
		var base=$(element).attr('data-rule-times');
		if(value%base!=0){
			returnVal=false;
		}
		return returnVal;
	}, "必须是发布赏金的整数倍");

	// 字符验证       
	jQuery.validator.addMethod("stringCheck", function(value, element) {       
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);       
	 }, "只能包括中文字、英文字母、数字和下划线");   
	 // 中文字两个字节       
	jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {       
	   var length = value.length;       
	   for(var i = 0; i <value.length; i++){       
			if(value.charCodeAt(i) > 127){       
			 length++;       
			 }       
	   }       
		return this.optional(element) || ( length >= param[0] && length <= param[1] );       
	 }, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");   


	jQuery.validator.addMethod("transferPrice", function(value, element,param) { 
		var account = (parseFloat(param) * 0.75).toString();
		var resultAccount;
		if(account.indexOf('.') >= 0){
			resultAccount = account.substring(0, account.indexOf('.') + 3)
		}

		if(parseFloat(value) < parseFloat(resultAccount)){
			return false;
		}else{
			return true;
		}
	}, jQuery.validator.format("转让价不能小于剩余本金的75%")); 

	jQuery.validator.addMethod("transferMaxPrice", function(value, element,param) {  
		if(parseFloat(value) > parseFloat(param)){
			return false;
		}else{
			return true;
		}
	}, jQuery.validator.format("转让价不能大于剩余本金")); 

	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		var target = $( param );
		alert(target.val())
		return this.optional(element) || Number(value) != Number(target.val());
	});

	//身份证号码验证
	jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
	  return this.optional(element) || isIdCardNo(value);
	}, "身份证号错误(不区分大小写)");
	//校验基础信息表单
	$("#basicInfoForm").validate({
		errorElement:'span',
		errorClass:'help-block error-mes',
		rules:{
			name:{
				required:true,
				isName:true
			},
			sex:"required",
			birth:"required",
            mobile:{
				required:true,
				isPhone:true
			},
			email:{
				required:true,
				email:true
			}
		},
		messages:{
			name:{
				required:"请输入中文姓名",
				isName:"姓名只能为汉字"
			},
			sex:{
				required:"请输入性别"
			},
			birth:{
				required:"请输入出生年月"
			},
            mobile:{
				required:"请输入手机号",
				isPhone:"请填写正确的11位手机号"
			},
			email:{
				required:"请输入邮箱",
				email:"请填写正确的邮箱格式"
			}
		},
	
		errorPlacement:function(error,element){
			element.next().remove();
			element.closest('.gg-formGroup').append(error);
		},
		
		highlight:function(element){
			$(element).closest('.gg-formGroup').addClass('has-error has-feedback');
		},
		success:function(label){
			var el = label.closest('.gg-formGroup').find("input");
			el.next().remove();
			label.closest('.gg-formGroup').removeClass('has-error').addClass("has-feedback has-success");
			label.remove();
		},
		submitHandler:function(form){
			alert("保存成功!");
		}
	});
	
	//校验修改密码表单
	$("#modifyPwd").validate({
		onfocusout: function(element) { $(element).valid()},
		 debug:false, //表示校验通过后是否直接提交表单
		 onkeyup:false, //表示按键松开时候监听验证
		rules:{
			pwdOld:{
				required:true,
				minlength:6
			},
            pwdNew:{
			   required:true,
			   minlength:6,
			   isdiff:true,
			   //issame:true,
		   },
			confirm_password:{
			  required:true,
			  minlength:6,
			  issame:true,
			}
		  
		   },
		messages:{
			 	pwdOld : {
					 required:'必填',
					 minlength:$.validator.format('密码长度要大于6')
				},
            	pwdNew:{
				   required:'必填',
				   minlength:$.validator.format('密码长度要大于6'),
				   isdiff:'原密码与新密码不能重复',
				  
			   },
				confirm_password:{
				   required:'必填',
				   minlength:$.validator.format('密码长度要大于6'),
				   issame:'新密码要与确认新密码一致',
				}
		
		},
		errorElement:"mes",
		errorClass:"gg-star",
		errorPlacement: function(error, element) 
		{ 
			element.closest('.gg-formGroup').append(error);

		}
	});
});