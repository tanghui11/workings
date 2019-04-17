$().ready(function() {
	validateRule();
	$("#courseReplaceid").val(parent.$("#courseReplace").val())
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_common_course_replace_item&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=course_replaceid&filedValue2="+$("#courseReplaceid").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("课程名称不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url : contextPath+"/plan/commonCourseReplaceItem/save",
					data : $('#signupForm').serialize(),// 你的formid
					async : false,
					error : function(request) {
						parent.layer.alert("Connection error");
					},
					success : function(data) {
						if (data.code == 0) {
							parent.layer.msg("操作成功");
							if($("#checkBox").prop("checked")==true){
								parent.reLoad();
								var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
								parent.layer.close(index);
							}else{
								parent.reLoad();
								location.reload();
							}

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
				required : icon + "请输入课程名称"
			}
		}
	})
}
function courseNames(){
	  layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
        content: contextPath+"/plan/course/clist"
    }); 
}