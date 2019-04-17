$().ready(function() {
	validateRule();
	$("#coursess").val(parent.coursess)
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=pla_book&method=update&updateKey=id&updateKeyValue="+$("#id").val()+"&filedName1=name&filedValue1="+$("#name").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log(data);
			  if (data > 0) {
				layer.msg("教材名称重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+ "/plan/book/update",
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
			classify : {
				required : true
			},
			courseid : {
				required : true,
				maxlength:20
			},
			name : {
				required : true,
				maxlength:30
			},
			pinyin : {
				maxlength:30
			},
			chiefEditor : {
				maxlength:15
			},
			publisher : {
				maxlength:65
			},
			version : {
				maxlength:30
			},
			price : {
				number:true
			},
			type : {
				required : true,
			},
			remark : {
				maxlength :160
			},
			status : {
				required : true,
				maxlength :1
			},
		},
		messages : {
			classify : {
				required : icon + "请选择教材类别"
			},
			courseid : {
				required : icon + "请输课程编号",
				maxlength : icon + "最多输入20字符"
			},
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "最多输入30字符"
			},
			pinyin : {
				maxlength : icon + "最多输入30字符"
			},
			chiefEditor : {
				maxlength : icon + "最多输入15字符"
			},
			publisher : {
				maxlength : icon + "最多输入65字符"
			},
			version : {
				maxlength : icon + "最多输入30字符"
			},
			price : {
				number : icon + "请输入合法的数字（整数，小数）"
			},
			type : {
				required : icon + "请选择教材类别"
			},
			remark : {
				maxlength : icon + "最多输入160字符"
			},
			status : {
				required : icon + "请输入状态",
				maxlength : icon + "最多输入1字符"
			},
		}
	})
}
//课程名称加课程名称
function courseName(){
	  layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content:contextPath+  "/plan/course/clist"
    }); 
	
}