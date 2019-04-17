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
		url :contextPath+ "/plan/courseReplace/update",
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
			specialityRecordid: {
				required : true
			},
			courseid: {
				required : true,
				maxlength:20
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
			},
		},
		messages : {
			specialityRecordid : {
				required : icon + "请输入专业开设编号"
			},
			courseid: {
				required : icon + "请输入课程编号",
				maxlength:icon + "课程编号最多20个字符"
			},
			courseNum:{
				digits:icon + "必须输入整数"
			},
			leastNum:{
				digits:icon + "必须输入整数"
			},
			leastScore:{
				digits:icon + "必须输入整数"
			},
			appendCourseid1:{
				maxlength:icon + "只能20个字符"
			},
			appendCourseid2:{
				maxlength:icon + "只能20个字符"
			},
			appendCourseid3:{
				maxlength:icon + "只能20个字符"
			},
			appendCourseid4:{
				maxlength:icon + "只能20个字符"
			},
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