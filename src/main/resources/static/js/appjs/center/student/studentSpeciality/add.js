$().ready(function() {
	validateRule();
	$("#studentid").val(parent.$("#studentid").val());
	$("#collegeName").val(parent.$("#collegeName").val());
	$("#teachSiteName").val(parent.$("#teachName").val());
	$("#teachSiteid").val(parent.$("#teachSiteid").val());
	$("#collegeid").val(parent.$("#collegeid").val());
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
		url :contextPath+  "/student/studentSpeciality/save",
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

//报考专业
function yemian(){
	layer.open({
		type : 2,
		title : "报考专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
	});
}
//考生来源
function yemianKSLY(){
	layer.open({
		type : 2,
		title : "考生来源",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/region/groups/groupList"
	});
}
//原学专业
function yemianYXZY(){
	layer.open({
		type : 2,
		title : "原学专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/student/studentStudent/showSubject"
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			regionid:{
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			regionid:{
				required : true
			}
		}
	})
}