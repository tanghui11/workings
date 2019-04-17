$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/region/groupsRegion/save",
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
            code:{
                maxlength:20
            },
            name : {
                required : true,
                maxlength:60
            },
            pinyin:{
                maxlength:60
            },
            classify:{
                required : true
            }
        },
        messages : {
            code:{
                maxlength : icon + "代码字符过长"
            },
            name : {
                required : icon + "请输入名称",
                maxlength : icon + "名称超过指定范围"
            },
            pinyin:{
                maxlength:icon + "拼音超过指定范围"
            },
            classify:{
                required : icon + "层次不能为空"
            }
        }
    })
}