$().ready(function() {
	validateRule();
	provinceLd();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
//省市联动
$("#province").change(function(){
	$("#city").children().remove()
	var id = $("#province option:selected").val();
	cityLd(id)
})
function provinceLd(){
	$.ajax({
		type : "get",
		url : contextPath+"/sys/native/list",
		data : {
			parentid:0
		},
		async : false,
		success : function(data) {
			for(var i=0;i<data.rows.length;i++){
				var html = "<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>"
				$("#province").append(html)
			}
			var id = $("#province option:selected").val();
			cityLd(id)
		}
	});
}
function cityLd(id){
	$.ajax({
		type : "get",
		url : contextPath+"/sys/native/list",
		data : {
			parentid:id
		},
		async : false,
		success : function(data) {
			for(var i=0;i<data.rows.length;i++){
				var html = "<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>"
				$("#city").append(html)
			}
		}
	});
}
//转入专业名称
function zrzymc(){
	layer.open({
		type : 2,
		title : '转入专业名称',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : contextPath + '/plan/speciality/showSubjectZc ' // iframe的url
	});
}
//学生信息
function studentXinxi(){
	layer.open({
		type : 2,
		title : '信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : contextPath + '/student/studentCourseRepaire/zcStudent' // iframe的url
	});
}



function save() {
	countDown("6","submission","转出");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/region/studentOutRegion/save",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			zkInZkz : {
				required : true
			},
			zzd : {
				required : true
			},
			jbxx : {
				required : true
			},
			bkxx : {
				required : true
			},
		},
		messages : {
			zkInZkz : {
				required : icon + "请输入转入准考证号"
			},
			zzd : {
				required : icon + "请选择转入专业名称"
			},
			jbxx : {
				required : icon + "请选择基本信息"
			},
			bkxx : {
				required : icon + "请选择报考信息"
			},
		}
	})
}