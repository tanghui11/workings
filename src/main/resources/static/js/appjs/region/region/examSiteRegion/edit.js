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
		url : contextPath+"/region/examSiteRegion/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
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
				maxlength: 33,
			},
			code : {
				required : true,
				maxlength: 20,
			},
			pinyin : {
				maxlength: 33,
			},
			num : {
				number:true,
				digits:true,
			},
			linkman : {
				maxlength: 6,
			},
			phone : {
				isPhone : true,
			},
			fax : {
				fax : true,
			},
			postCode : {
				isZipCode : true,
			},
			school : {
				required : true,
			},
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength: icon + "最多输入33个字",
			},
			code : {
				required : icon + "请输入考点代码",
				maxlength: icon + "最多输入20个字符",
			},
			pinyin : {
				maxlength: icon + "最多输入33个字符",
			},
			num : {
				number: icon + "必须输入合法的数字（负数，小数）", 
				digits: icon + "必须输入整数",
			},
			linkman : {
				maxlength: icon + "最多输入6个字",
			},
			phone : {
				isPhone : icon + "请填写正确的11位手机号"
			},
			fax : {
				fax : icon + "传真格式如：0371-68787027"
			},
			postCode : {
				isZipCode : icon + "请正确填写您的邮政编码"
			},
			school : {
				required : icon + "请选择助学组织"
			},
		}
	})
}