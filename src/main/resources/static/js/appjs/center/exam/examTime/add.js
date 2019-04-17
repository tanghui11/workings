$().ready(function() {
	validateRule();
	var examTimeid = parent.$("#examTaskid").val();
	var years = parent.$("#examTaskYear").val();
	var mounth = parent.$("#mounth").val();
	$("#examTaskid").val(examTimeid);
	//考试任务
	$("#examTask").val(years+"年"+mounth);
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
		url :contextPath+  "/common/searchIfExist?tableName=exa_exam_time&filedName1=exam_taskid&filedValue1="+$("#examTaskid").val()+"&filedName2=begin_time&filedValue2="+$("#beginTime").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("开考时间不能重复");
			}else{
				 $.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/exam/examTime/save",
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
							parent.layer.alert(data.msg);
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
			name : {
				required : true
			},
			examDate :{
				required : true
			},
			beginTime :{
				required : true
			},
			endTime:{
				required : true
			},
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			examDate :{
				required : icon + "开考日期不能为空"
			},
			beginTime :{
				required : icon + "开考时间不能为空"
			},
			endTime:{
				required : icon + "结束时间不能为空"
			},
		}
	})
}
