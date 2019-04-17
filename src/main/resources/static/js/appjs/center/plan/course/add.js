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
		url : contextPath+"/plan/course/save",
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
			id:{
				required : true
			},
			name : {
				required : true
			},
			pinyin:{
				maxlength:100
			},
			ename:{
				maxlength:100
			},
			score:{
				required : true
			},
			type:{
				required : true
			},
			classify:{
				required : true
			},
			attribute:{
				required : true
			},
			practiceCourseid:{
				maxlength:20
			}
			
		},
		messages : {
			id:{
				required : icon + "id不能为空！"
			},
			name : {
				required : icon + "请输入名称！"
			},
			pinyin:{
				maxlength: icon + "拼音长度只能为100！"
			},
			ename:{
				maxlength: icon + "字符长度不能超过100！"
			},
			score:{
				required : icon + "请输入学分！"
			},
			type:{
				required : icon + "请输入类别！"
			},
			classify:{
				required : icon + "请输入分类！"
			},
			attribute:{
				required : icon + "请输入属性！"
			},
			practiceCourseid:{
				maxlength : icon + "长度只能为20！"
			}
		}
	})
}

function practice(){
	layer.open({
		type : 2,
		title : '设置课程代码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content :contextPath+  "/plan/course/clist"// iframe的url
	});
}
	