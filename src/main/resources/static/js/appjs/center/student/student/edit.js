$().ready(function() {
	validateRule();
	phoneHX();
});

$.validator.setDefaults({
	submitHandler : function() { 
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+ "/student/student/update",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			}
		}
	})
}
//照片回显
function phoneHX(){
	var src = $("#pic").val()+'?number='+Math.random();//url不变参数变
	$("#scPhone").attr("src",src)
	var url = $("#idcardPic").val()
	$("#id_img_pers").attr("src",url)
}
//上传
function upload(){
	layer.open({
		type : 2,
		title : "上传",
		maxmin : true,
		shadeClose : false, 
		area : [ '600px', '90%' ],
		content : contextPath+"/common/uploadPhoto"
	});
}
//考生来源
function yemianKSLY(){
	layer.open({
		type : 2,
		title : "考生来源",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/region/groups/groupList"
	});
}
