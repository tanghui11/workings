$().ready(function() {
	validateRule();
	var examTaskid = parent.$("#examTaskid").val();
	var years = parent.$("#examTaskYear").val();
	//alert(years)
	//var mounth = parent.yue;
	$("#examTaskid").val(examTaskid);
	$("#examTaskYear").val(years);
	/* $("#examTaskid").val(localStorage.getItem('examTaskid'));
	$("#examTaskYear").val(localStorage.getItem('examTaskYear')); */
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
		url :contextPath+  "/common/searchIfExist?tableName=exa_exam_item&filedName1=exam_month&filedValue1="+$("#examMonth").val()+"&filedName2=exam_taskid&filedValue2="+$("#examTaskid").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("考试项目重复");
			}else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/exam/examItem/save",
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
			name : {
				required : true
			},
			examTaskYear:{
				required : true
			},
			examMonth:{
				required : true,
				maxlength:10
			},
			examCodeFixed:{
				required : true,
				maxlength:10
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			examTaskYear:{
				required : icon + "不能为空"
			},
			examMonth:{
				required : icon + "不能为空",
				maxlength : icon + "字符过长"
			},
			examCodeFixed:{
				required : icon + "不能为空",
				maxlength : icon + "字符过长"
			}
		}
	})
}

function month(that){
	$("#examCodeFixed").val($("#examTaskYear").val().substr(2)+that);
}
