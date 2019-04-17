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
		url :contextPath+ "/plan/oldCourse/update",
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
				maxlength: 33
			},
			pinyin:{
				maxlength: 100
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength: icon + "名称最多只能输入30个字"
			},
			pinyin:{
				maxlength: icon + "最大不能超过100个字符"
			}
		}
	})
}