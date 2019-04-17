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
		url :contextPath+  "/common/searchIfExist?tableName=pla_speciality&filedName1=id&filedValue1="+$("#id").val()+"&filedName2=name&filedValue2="+$("#name").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("开考课程或者是开考时间不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/plan/speciality/save",
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
			id : {
				required : true,
				maxlength : 10,
				
			},
			name : {
				required : true,
				maxlength : 30
			},
			pinyin : {
				maxlength : 100
			},
			type : {
				required : true,
			},
			classify : {
				required : true,
			},
			flag : {
				required : true,
			},
			grantType : {
				required : true,
			},
			score : {
				digits:true,
			},
			auditStatus : {
				maxlength : 1
			},
			zkSpecialityid : {
				maxlength : 6
			}
		},
		messages : {
			id : {
				required : icon + "请输入专业代码",
				maxlength : icon + "专业代码不符合规范"
			},
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "最多只有30汉字！"
			},
			pinyin : {
				maxlength : icon + "最多只有100字母！"
			},
			type : {
				required : icon + "请选择类别",
			},
			classify : {
				required : icon + "请选择专业层次",
			},
			flag : {
				required : icon + "请选择层次类型",
			},
			grantType : {
				required : icon + "请选择委托类型",
			},
			score : {
				digits : icon + "请输入整数",
			},
			auditStatus : {
				maxlength : icon + "最多只有1个字符！"
			},
			zkSpecialityid : {
				maxlength : icon + "最多只有6个字符！",
			}
		}
	})
}
//专科代码
function majorName(){
	layer.open({
		type : 2,
		title : '专科代码',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : contextPath+  "/plan/speciality/showSubject?classify=6" // iframe的url
	});
}

