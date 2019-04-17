$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/region/groups/update",
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
			code :{
				required : true,
				maxlength:4,
				minlength:4,
				digits:true
			},
			name : {
				required : true,
				maxlength:60
			},
			pinyin:{
				maxlength:60
			},
			classify:{
				required : true
			}
		},
		messages : {
			code :{
				required : icon + "代码不能为空！",
				maxlength: icon +"只能填入四位",
				minlength: icon +"只能填入四位",
				digits: icon + "只能输入数字"
			},
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "名称超过指定范围"
			},
			pinyin:{
				maxlength:icon + "拼音超过指定范围"
			},
			classify:{
				required : icon + "层次不能为空"
			}
		}
	})
}