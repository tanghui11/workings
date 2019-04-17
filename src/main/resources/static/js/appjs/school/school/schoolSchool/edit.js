$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/school/schoolSchool/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
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
				maxlength : 15,
			},
			pinyin : {
				maxlength : 15,
			},
			bCode : {
				required : true,
				maxlength : 30,
			},
			bSendUnit : {
				required : true,
				maxlength : 10,
			},
			bSendDate : {
				required : true,
			},
			zCode : {
				maxlength : 30,
			},
			zSendUnit : {
				maxlength : 15,
			},
			type : {
				required : true,
			},
			legalPerson : {
				required : true,
				maxlength : 6,
			},
			legalPersonDuty : {
				maxlength : 10,
			},
			jTeacherNum : {
				digits:true 
			},
			zTeacherNum : {
				digits:true 
			},
			managerNum : {
				digits:true 
			},
			allNum : {
				digits:true 
			},
			charger : {
				required : true,
				maxlength : 10,
			},
			address : {
				maxlength : 33,
			},
			postCode : {
				isZipCode : true,
			},
			phone : {
				isTel : true,
			},
			mphone : {
				isPhone : true,
			},
			fax : {
				fax : true,
			},
			email : {
				email : true,
			},
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "最多输入15个字符"
			},
			pinyin : {
				maxlength : icon + "最多输入15个字符"
			},
			bCode : {
				required : icon + "请输入办学许可证编号",
				maxlength : icon + "最多输入30个字符"
			},
			bSendUnit : {
				required : icon + "请选择日期",
			},
			zCode : {
				maxlength : icon + "最多输入30个字符"
			},
			zSendUnit : {
				maxlength : icon + "最多输入15个字符"
			},
			type : {
				required : icon + "类型不能为空",
			},
			legalPerson : {
				required : icon + "请输入法人名称",
				maxlength : icon + "最多输入6个字"
			},
			legalPersonDuty : {
				maxlength : icon + "最多输入10个字"
			},
			jTeacherNum : {
				digits : icon + "请输入整数"
			},
			zTeacherNum : {
				digits : icon + "请输入整数"
			},
			managerNum : {
				digits : icon + "请输入整数"
			},
			allNum : {
				digits : icon + "请输入整数"
			},
			charger : {
				required : icon + "请输入负责人",
				maxlength : icon + "最多输入10个字"
			},
			address : {
				maxlength : icon + "最多输入33个字"
			},
			postCode : {
				isZipCode : icon + "请正确填写您的邮政编码"
			},
			phone : {
				isTel : icon + "请填写正确的座机号码"
			},
			mphone : {
				isPhone : icon + "请填写正确的11位手机号"
			},
			fax : {
				fax : icon + "传真格式如：0371-68787027"
			},
			email : {
				email : icon + "请输入正确的电子邮箱"
			},
		}
	})
}