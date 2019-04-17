$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/school/collegeSchool/save",
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
				maxlength : 33,
			},
			pinyin : {
				maxlength : 33,
			},
			linkman : {
				required : true,
				maxlength : 15,
			},
			address : {
				maxlength : 65,
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
				maxlength : icon + "最多输入33个字",
			},
			pinyin : {
				maxlength : icon + "最多输入33个字",
			},
			linkman : {
				required : icon + "请输入联系人",
				maxlength : icon + "最多输入15个字",
			},
			address : {
				maxlength : icon + "最多输入65个字",
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