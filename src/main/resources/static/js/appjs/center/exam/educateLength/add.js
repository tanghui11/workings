$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
//提交倒计时
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=exa_educate_length&filedName1=classify&filedValue1="+$("#classify").val()+"&filedName2=type&filedValue2="+$("#type").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("专业层次和助学方式重复！");
            }else{
				 $.ajax({
					cache : true,
					type : "POST",
					url : contextPath+"/exam/educateLength/save",
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
	/* */

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			studyLength:{
				required : true
			},
			graduate_length:{
				graduateLength : true
			},
			graduateLength:{
				required : true
			}
		},
		messages : {
			studyLength:{
				required : icon + "请输入学制长度"
			},
			graduate_length:{
				graduateLength : icon + "请输入毕业学制长度"
			},
			graduateLength:{
				required : icon + "请输入毕业学制长度"
			}
		}
	})
}