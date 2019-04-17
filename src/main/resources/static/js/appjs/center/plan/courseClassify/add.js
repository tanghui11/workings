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
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_course_classify&filedName1=courseid&filedValue1="+$("#courseid").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("课程代码不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/plan/courseClassify/save",
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
		}
	});
	

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			type : {
				required : true
			},
			name : {
				required : true
			},
			courseid : {
				required : true
			},
		},
		messages : {
			type : {
				required : icon + "请选择类别"
			},
			name : {
				required : icon + "请输入名称"
			},
			courseid : {
				required : icon + "请输入课程代码"
			}
		}
	})
}
function add2() {
	layer.open({
		type : 2,
		title : '课程代码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%'  ],
		content :contextPath+ "/plan/course/clist"  // iframe的url
	});
}