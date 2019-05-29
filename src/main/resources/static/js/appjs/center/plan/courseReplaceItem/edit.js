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
		url :contextPath+ "/plan/courseReplaceItem/update",
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
			coursess:{
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			coursess:{
				required : icon + "课程名称不能为空"
			}
		}
	})
}
//课程代码
function courseNames(id){
	layer.open({
		type: 2,
		title: '课程名称',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['80%', '80%'],
		content: contextPath+"/plan/course/clist/?id="+id
	});
}