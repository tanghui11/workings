$().ready(function() {
	validateRule();
	//专业名称
	$("#speciality").val(parent.subjectName);
	//专业id
	$("#specialityRecordid").val(parent.specialityRecord);
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	countDown("submission","提交");
	if($("#checkBox").prop("checked")==true){
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+  "/common/searchIfExist?tableName=pla_speciality_course&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=speciality_recordid&filedValue2="+$("#specialityRecordid").val(),
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				console.log(data);
				 if (data > 0) {
					layer.msg("课程名称不能重复");
				}else{
					 $.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/plan/specialityCourse/save",
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
							layer.alert(data.msg)
						}

					}
				}); 
				}
			}
		});
	}else{
		$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_speciality_course&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=speciality_recordid&filedValue2="+$("#specialityRecordid").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log(data);
			 if (data > 0) {
				layer.msg("课程名称不能重复");
            }else{
				 $.ajax({
				cache : true,
				type : "POST",
				url :contextPath+ "/plan/specialityCourse/save",
				data : $('#signupForm').serialize(),// 你的formid
				async : false,
				error : function(request) {
					parent.layer.alert("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						parent.layer.msg("操作成功");
						parent.reLoad();
						location.reload();
					} else {
						layer.alert(data.msg)
					}

				}
			}); 
			}
		}
	});
	}
	 
	
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			speciality_recordid:{
				required : true
			},
			course:{
				required : true
			},
			book:{
				required : true
			},
			courseid:{
				required : true,
				maxlength:20
			},
			seq:{
				required : true,
				digits:true
			},
			status:{
				required : true,
				maxlength:1
			}
		},
		messages : {
			speciality_recordid:{
				required : icon + "专业开设编号不能为空！"
			},
			course:{
				required : icon + "不能为空！"
			},
			book:{
				required : icon + "不能为空！"
			},
			courseid:{
				required : icon + "课程编号不能为空！",
				maxlength:icon + "最多不能超过20个字符"
			},
			seq:{
				required : icon + "序号不能为空！",
				digits: icon + "必须输入数字"
			},
			status:{
				required : icon + "状态不能为空！",
				maxlength:icon + "只能填入一个数值"
			}
		}
	})
}


//课程代码
function courseNames(){
	  layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content: contextPath+"/plan/course/clist"
    }); 
}
//教材id
function bookids(that){	
	$("#bookid").val(that);
}