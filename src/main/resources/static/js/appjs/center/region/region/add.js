$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/region/region/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
			
                location.href="javascript:history.go(-1)";
                self.location=document.referrer;

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
			parentid : {
				required : true
			},
			code : {
				required : true,
				maxlength: 20
			},
			type : {
				required : true,
				maxlength: 1
			},
			name : {
				required : true,
				maxlength: 30
			},
			pinyin : {
				maxlength: 30
			},
			linkman : {
				maxlength: 15
			},
			address : {
				maxlength: 65
			},
			postCode : {
				isZipCode:true
			},
			phone : {
				isTel:true
			},
			mphone : {
				isPhone:true
			},
			fax : {
				fax:true
			},
			photoFlag : {
				required : true
			},
			examTransfer : {
				maxlength: 18
			},
			roomSize : {
				number:true 
			},
			examMsg : {
				maxlength: 333
			},
			seq : {
				maxlength: 2
			},
		},
		messages : {
			parentid : {
				required : icon + "请输入父编号"
			},
			code : {
				required : icon + "请输入代码",
				maxlength: icon + "最多输入20个字符"
			},
			type : {
				required : icon + "请输入类别",
				maxlength: icon + "最多输入1个字符"
			},
			name : {
				required : icon + "请输入名称",
				maxlength: icon + "最多输入30个字符"
			},
			pinyin : {
				maxlength: icon + "最多输入30个字符"
			},
			linkman : {
				maxlength: icon + "最多输入15个字符"
			},
			address : {
				maxlength: icon + "最多输入65个字符"
			},
			postCode : {
				isZipCode:icon + "请正确填写您的邮政编码"
			},
			Phone : {
				isTel: icon + "请填写正确的座机号码"
			},
			mphone : {
				isPhone: icon + "请填写正确的11位手机号"
			},
			fax : {
				fax: icon + "传真格式如：0371-68787027"
			},
			photoFlag : {
				required : icon + "请选择是或否"
			},
			examTransfer : {
				maxlength: icon + "最多输入18个字符"
			},
			roomSize : {
				number: icon + "请输入正确的考场规格,如:0"
			},
			examMsg : {
				maxlength: icon + "最多输入333个字符"
			},
			seq : {
				maxlength: icon + "最多输入2个字符,如:00"
			},
		}
	})
}