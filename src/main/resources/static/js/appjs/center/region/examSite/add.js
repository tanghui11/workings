$().ready(function() {
	validateRule();
	var url =window.location.href;
	
	var regionid = url.split("?")[1].split("=")[1];
	$("#regionid").val(regionid)
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+ "/region/examSite/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index); 
            } else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true,
				maxlength:100
			},
			code:{
				required : true,
				maxlength:20
			},
			pinyin:{
				maxlength:100
			},
			num:{
				digits:true
			},
			linkman:{
				maxlength:20
			},
			phone:{
				isTel:true
			},
			fax:{
				fax:true
			},
			address:{
				maxlength:200
			},
			postCode:{
				isZipCode:true
			},
			remark:{
				maxlength:200
			},
			schoolCode:{
				maxlength:100
			},
			teachCode:{
				maxlength:100
			},
			leader:{
				maxlength:100
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength: icon + "超过指定长度"
			},
			code:{
				required : icon + "请输入代码",
				maxlength: icon + "超过指定长度"
			},
			pinyin:{
				maxlength: icon + "超过指定长度"
			},
			num:{
				digits:icon + "请输入整数"
			},
			linkman:{
				maxlength: icon + "超过指定长度"
			},
			phone:{
				isTel:icon + "请输入正确的电话号码"
			},
			fax:{
				fax:icon + "请输入正确的传真"
			},
			address:{
				maxlength: icon + "超过指定长度"
			},
			postCode:{
				isZipCode:icon + "请输入正确的邮编"
			},
			remark:{
				maxlength:icon + "超过指定长度"
			},
			schoolCode:{
				maxlength:icon + "超过指定长度"
			},
			teachCode:{
				maxlength:icon + "超过指定长度"
			},
			leader:{
				maxlength:icon + "超过指定长度"
			}
		}
	})
}