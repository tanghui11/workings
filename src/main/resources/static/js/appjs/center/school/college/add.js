$().ready(function() {
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
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=sch_college&filedName1=name&filedValue1="+$("#name").val(),// 你的formid
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
					url :contextPath+  "/school/college/save",
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
			id:{
				required : true,
				maxlength:20
			},
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
				isTel:true
			},
			mphone:{
				isPhone:true
			},
			fax:{
				fax:true
			},
			email:{
				email:true
			}
		},
		messages : {
			id:{
				required : icon + "请输入编号",
				maxlength:icon + "字符不能超过20"
			},
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
				isTel:icon + "请输入正确的固定电话"
			},
			mphone:{
				isPhone:icon + "请输入正确的移动电话"
			},
			fax:{
				fax:icon + "请输入正确的传真"
			},
			email:{
				email:icon + "请输入正确的邮箱地址"
			}
		}
	})
}