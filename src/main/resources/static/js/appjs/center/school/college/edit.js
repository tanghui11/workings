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
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=sch_college&method=update&updateKey=id&updateKeyValue="+$("#id").val()+"&filedName1=name&filedValue1="+$("#name").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("名称不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url : contextPath+ "/school/college/update",
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
			schoolid:{
				required : true,
				maxlength:20
			},
			name : {
				required : true,
				maxlength:30
			},
			pinyin:{
				maxlength:100
			},
			linkman:{
				required : true,
				maxlength:16
			},
			address:{
				maxlength:66
			},
			postCode:{
				maxlength:6
			},
			phone:{
				maxlength:20
			},
			mphone:{
				maxlength:20
			},
			fax:{
				maxlength:20
			},
			email:{
				maxlength:100
			}
		},
		messages : {
			schoolid:{
				required : icon + "请输入助学组织编号",
				maxlength:icon + "字符不能超过20"
			},
			name : {
				required : icon + "请输入名称",
				maxlength:icon + "名称不能超过30",
			},
			pinyin:{
					maxlength:icon + "拼音字符不能超过100"
			},
			linkman:{
				required :icon + "联系人不能为空",
				maxlength:icon + "请输入正确的联系人"
			},
			address:{
				maxlength:icon + "请输入正确的联系地址"
			},
			postCode:{
				maxlength:icon + "请输入正确的邮编"
			},
			phone:{
				maxlength:icon + "请输入正确的固定电话"
			},
			mphone:{
				maxlength:icon + "请输入正确的移动电话"
			},
			fax:{
				maxlength:icon + "请输入正确的传真"
			},
			email:{
				maxlength:icon + "邮箱字符不能超过100"
			}
		}
	})
}