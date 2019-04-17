$().ready(function() {
	validateRule();
	$("#certificateReplaceid").val(parent.$("#certificateReplace").val());
	$("#oldCourseid").val(parent.$("#oldCourseid").val());
	$("#oldCourseName").val(parent.$("#oldCourseName").val());
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
		url :contextPath+  "/common/searchIfExist?tableName=pla_certificate_replace_item&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=certificate_replaceid&filedValue2="+$("#certificateReplaceid").val(),
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
					url :contextPath+ "/plan/certificateReplaceItem/save",
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
//课程代码
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