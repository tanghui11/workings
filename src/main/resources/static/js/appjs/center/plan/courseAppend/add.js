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
		url :contextPath+ "/plan/courseAppend/save",
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
				required : true
			},
			specialityName:{
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			specialityName:{
				required : icon + "专业开设不能为空"
			}
		}
	})
}

//课程名称
function courseName() {
	layer.open({
		type: 2,
		title: '课程名称',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['800px', '520px'],
		content: contextPath + "/plan/course/clist"
	});
}

//专业名称
function majorName(){
	layer.open({
		type: 2,
		title: '专业名称',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['90%', '80%'],
		content:contextPath+  "/plan/specialityRecord/showSubject1"
	});

}