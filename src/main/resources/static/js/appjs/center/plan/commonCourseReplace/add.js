$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_common_course_replace&filedName1=courseid&filedValue1="+$("#courseid").val(),
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
					url : contextPath+"/plan/commonCourseReplace/save",
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
			},
			coursess:{
				required : true,
				maxlength:20
			},
			specialityClass:{
				required : true
			},
			flag : {
				required : true
			},
			courseClass : {
				required : true
			},
			type:{
				required : true
			},
			courseNum:{
				digits:true
			},
			leastNum:{
				digits:true
			},
			leastScore:{
				digits:true
			},
			appendCourseid1:{
				maxlength:20
			},
			appendCourseid2:{
				maxlength:20
			},
			appendCourseid3:{
				maxlength:20
			},
			appendCourseid4:{
				maxlength:20
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			coursess:{
				required : icon + "请输入课程名称",
				maxlength:icon + "课程编号字符过长"
			},
			specialityClass:{
				required :icon + "请输入专业类别"
			},
			flag : {
				required :icon + "请输入顶替类别"
			},
			courseClass : {
				required : icon + "请选择课程层次"
			},
			type:{
				required : icon + "请选择类别"
			},
			courseNum:{
				digits:icon + "必须输入正整数"
			},
			leastNum:{
				digits:icon + "必须输入正整数"
			},
			leastScore:{
				digits:icon + "必须输入正整数"
			},
			appendCourseid1:{
				maxlength:icon + "字符不能超过20字符"
			},
			appendCourseid2:{
				maxlength:icon + "字符不能超过20字符"
			},
			appendCourseid3:{
				maxlength:icon + "字符不能超过20字符"
			},
			appendCourseid4:{
				maxlength:icon + "字符不能超过20字符"
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