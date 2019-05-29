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
		url :contextPath+  "/common/searchIfExist?tableName=sch_school&filedName1=code&filedValue1="+$("#code").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("代码不能重复");
            }else{
				 $.ajax({
					cache : true,
					type : "POST",
					url : contextPath+ "/school/school/save",
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
			code:{
				required : true,
				maxlength:4,
				minlength:4
			},
			regionid:{
				required : true,
				maxlength:20
			},
			name : {
				required : true,
				maxlength:16
			},
			pinyin:{
				maxlength:50
			},
			bCode:{
				required : true,
				maxlength:30
			},
			bSendUnit:{
				required : true,
				maxlength:30
			},
			bSendDate:{
				required : true
			},
			zCode:{
				maxlength:30
			},
			zSendUnit:{
				maxlength:16
			},
			legalPerson:{
				required : true,
				maxlength:6
			},
			legalPersonDuty:{
				maxlength:10
			},
			jTeacherNum:{
				digits:true
			},
			zTeacherNum:{
				digits:true
			},
			managerNum:{
				required : true,
				digits:true
			},
			allNum:{
				required : true,
				digits:true
			},
			charger:{
				required : true,	
				maxlength:10
			},
			address:{
				maxlength:33
			},
			postCode:{
				isZipCode:true
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
			code:{
				required : icon + "请输入代码",
				maxlength:icon + "代码只能是四位",
				minlength:icon + "代码输入少于四位"
			},
			regionid:{
				required : icon + "请输入考区编号",
				maxlength:icon + "考区编号字符超过指定范围"
			},
			name : {
				required : icon + "请输入名称",
				maxlength:icon + "名称超过指定范围"
			},
			pinyin:{
				maxlength:icon + "拼音超过指定范围"
			},
			bCode:{
				required : icon + "请输入办学许可证编号",
				maxlength:icon + "办学许可证编号超出指定范围"
			},
			bSendUnit:{
				required : icon + "请输入办学许可证发放单位",
				maxlength:icon + "办学许可证发放单位超出指定范围"
			},
			bSendDate:{
				required : icon + "办学许可证发放日期不能为空"
			},
			zCode:{
				maxlength:icon + "助学许可证编号超出指定范围"
			},
			zSendUnit:{
				maxlength:icon + "助学许可证发放单位超出指定范围"
			},
			legalPerson:{
				required : icon + "法人名称不能为空",
				maxlength:icon + "法人名称不能过长"
			},
			legalPersonDuty:{
				maxlength:icon + "法人职务超过指定范围"
			},
			jTeacherNum:{
				digits:icon + "必须输入整型数字"
			},
			zTeacherNum:{
				digits:icon + "必须输入整型数字"
			},
			managerNum:{
				required : icon + "不能为空",
				digits:icon + "必须输入整型数字"
			},
			allNum:{
				required : icon + "不能为空",
				digits:icon + "必须输入整型数字"
			},
			charger:{
				required : icon + "负责人不能为空",
				maxlength:icon + "负责人超过指定范围"
			},
			address:{
				maxlength:icon + "联系地址超过指定范围"
			},
			postCode:{
				isZipCode:icon + "请输入正确的邮编"
			},
			phone:{
				isTel:icon + "请输入正确的固定电话格式"
			},
			mphone:{
				isPone:icon + "请输入正确的移动电话格式"
			},
			fax:{
				fax:icon + "请输入正确的传真格式"
			},
			email:{
				email:icon + "请输入正确的邮箱格式"

			}
		}
	})
}
function kqbh(){
	layer.open({
			type : 2,
			title : '添加 [ 助学组织 >  助学组织管理 ]',
			shadeClose : false, // 点击遮罩关闭层
			area : [ '95%', '95%' ],
			content : contextPath+"/system/user/region",// iframe的url"选择考区端用户"
		});
}