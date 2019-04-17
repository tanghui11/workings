$().ready(function() {
	validateRule();
	$("#oldCourse").val(parent.oldCourse)
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
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_certificate_replace&method=update&updateKey=id&updateKeyValue="+$("#id").val()+"&filedName1=old_courseid&filedValue1="+$("#oldCourseid").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			 if (data > 0) {
				layer.msg("证书不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url : contextPath+"/plan/certificateReplace/update",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			}
		}
	})
}
//证书
function zhengshu(){
	layer.open({
		type : 2,
		title : '证书顶替信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%'  ],
		content :contextPath+ '/plan/oldCourse/showSearchList?type=b' // iframe的url
	});
}