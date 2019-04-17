$().ready(function() {
	validateRule();
});
/* var courseAppendid = parent.$("#courseAppendid").val()
var courseid = parent.$("#courseid").val()
$("#courseid").val(courseid.split(",")[0])
$("#courseName").val("("+courseid.split(",")[0]+")"+courseid.split(",")[1]) */
$("#courseAppendid").val(parent.$("#courseAppendid").val())
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+ "/plan/courseSpeciality/save",
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
//课程名称
function kcmc(){
	layer.open({
		type : 2,
		title : '课程名称',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content : contextPath + '/plan/course/clist' // iframe的url
	});
}