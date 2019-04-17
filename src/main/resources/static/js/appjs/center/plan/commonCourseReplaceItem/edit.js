$().ready(function() {
	validateRule();
	$("#coursess").val(parent.course);
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_common_course_replace_item&method=update&updateKey=id&updateKeyValue="+$("#id").val()+"&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=course_replaceid&filedValue2="+$("#courseReplaceid").val(),
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
					url :contextPath+ "/plan/commonCourseReplaceItem/update",
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