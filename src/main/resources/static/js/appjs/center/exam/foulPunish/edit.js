$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+ "/exam/foulPunish/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
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
			code : {
				required : true,
				maxlength:20
			},
			formula : {
				maxlength:100
			},
			remark : {
				maxlength:200
			},
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength: icon + "不能大于指定长度"
			},
			code : {
				required : icon + "请输入代码",
				maxlength:icon + "不能大于指定长度"
			},
			formula : {
				maxlength:icon + "不能大于指定长度"
			},
			remark : {
				maxlength:icon + "不能大于指定长度"
			},
		}
	})
}