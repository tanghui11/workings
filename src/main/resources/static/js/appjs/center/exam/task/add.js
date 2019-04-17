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
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=exam_task&filedName1=exam_year&filedValue1="+$("#examYear").val()+"&filedName2=exam_month&filedValue2="+$("#examMonth").val()+"&filedName3=type&filedValue3="+$("#type").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("年份,月份,课程类型不能重复");
			}else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/exam/task/save",
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
	/*  */

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			examYear:{
				required : true,
				maxlength:10
			},
			examMonth:{
				required : true,
				maxlength:10
			},
			beginDate:{
				required : true
			},
			endDate:{
				required : true
			},
			beginDateAppend:{
				required : true
			},
			endDateAppend:{
				required : true
			},
			beginDate1:{
				required : true
			},
			endDate1:{
				required : true
			},
			beginDateAppend1:{
				required : true
			},
			endDateAppend1:{
				required : true
			},
			arrangeBeginDate:{
				required : true
			},
			arrangeEndDate:{
				required : true
			},
			remark:{
				maxlength:50
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			examYear:{
				required : icon + "请输入年份！",
				maxlength:icon + "年份最多只能10个字符"
			},
			examMonth:{
				required : icon + "请输入月份！",
				maxlength:icon + "月份最多只能10个字符"
			},
			beginDate:{
				required : icon + "不能为空！"
			},
			endDate:{
				required : icon + "不能为空！"
			},
			beginDateAppend:{
				required : icon + "不能为空！"
			},
			endDateAppend:{
				required : icon + "不能为空！"
			},
			beginDate1:{
				required : icon + "不能为空！"
			},
			endDate1:{
				required : icon + "不能为空！"
			},
			beginDateAppend1:{
				required : icon + "不能为空！"
			},
			endDateAppend1:{
				required : icon + "不能为空！"
			},
			arrangeBeginDate:{
				required : icon + "不能为空！"
			},
			arrangeEndDate:{
				required : icon + "不能为空！"
			},
			remark:{
				maxlength:icon + "字长度过长！"
			}
			
		}
	})
}