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
		url :contextPath+  "/common/searchIfExist?tableName=pla_old_course&filedName1=id&filedValue1="+$("#id").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("编号不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/plan/oldCourse/save",
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
	/*  */

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			id : {
				required : true,
				maxlength:6
			},
			name : {
				required : true,
				maxlength: 33
			},
			pinyin:{
				maxlength: 100
			}
		},
		messages : {
			id : {
				required : icon + "请输入代码",
				maxlength: icon + "最多输入6个字符"
			},
			name : {
				required : icon + "请输入老课程名称",
				maxlength: icon + "名称最多只能输入30个字"
			},
			pinyin:{
				maxlength: icon + "最大不能超过100个字符"
			}
		}
	})
}