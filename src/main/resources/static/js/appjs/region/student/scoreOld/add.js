$().ready(function() {
	$("#id").val(parent.parent.id1);
	$("#studentid").val(parent.parent.studentidss);
	$("#specialityRecordid").val(parent.parent.specialityRecordid1);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/student/studentSpeciality/insertStudent",
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
			id : {
				required : true
			},
			studentid : {
				required : true
			},
			courseName : {
				required : true
			},
			grade : {
				required : true
			}
		},
		messages : {
			id : {
				required : icon + "请输入id"
			},
			id : {
				required : icon + "请输入studentid"
			},
			grade : {
				required : icon + "请输入成绩"
			}
		}
	})
}
//课程名称
var classify =null;
function courseNames(){
	classify = $("#classify").val();
	layer.open({
		type : 2,
		title : "成绩补录",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+ "/student/studentSpeciality/showSubjectBL"
	});
}
function classifys(){
	$("#courseid").val("");
	$("#courseName").val("");
}