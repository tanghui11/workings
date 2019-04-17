$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
		nRepeat();
	}
});
function nRepeat() {
	$.ajax({
		type : "POST",
		url : contextPath+"/school/specialityRegSchool/code?code=4",
		success : function(data) {
			if (data == 0) {
				return;
            } else {
				parent.layer.alert("专业代码不能重复！")
			}

		}
	});

}
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/school/specialityRegSchool/save",
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
			specialityRecordName : {
				required : true
			},
			specialityRecordid : {
				required : true
			},
			code : {
				required : true,
				rangelength:[2,2]
			},
			name : {
				required : true
			}
		},
		messages : {
			specialityRecordName : {
				required : icon + "请选择专业名称"
			},
			specialityRecordid : {
				required : icon + "请输入专业开设编号"
			},
			code : {
				required : icon + "请输入自定义代码",
				rangelength:icon + "只能输入两位",
			},
			name : {
				required : icon + "请输入名称"
			},
		}
	})
}
function yemian(){
    layer.open({
        type : 2,
        title : "报考专业",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/plan/specialityRecord/showSubject"
    });
}