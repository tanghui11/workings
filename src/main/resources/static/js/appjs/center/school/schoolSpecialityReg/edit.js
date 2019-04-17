$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/school/schoolSpecialityReg/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				
                location.href="javascript:history.go(-1)";
                self.location=document.referrer;
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
			schoolid : {
				required : true
			},
			specialityRecordid : {
				required : true
			},
			regionid : {
				required : true 
			},
			regYear:{
				required : true,
				maxlength:4
			},
			num:{
				digits:true
			}
		},
		messages : {
			schoolid : {
				required : icon + "请输入助学组织编号"
			},
			specialityRecordid : {
				required : icon + "请输入专业开设编号"
			},
			regionid : {
				required : icon + "请输入地州市考办代码  "
			},
			regYear:{
				required : icon + "请输入招生年份",
				maxlength:icon + "请正确输入年份"
			},
			num:{
				digits:icon + "请输入整数"
			}
		}
	})
}