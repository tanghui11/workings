$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
$("#regionid").val(parent.regionid);
//alert($("#regionid").val());
function save() {
/* $.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/common/searchIfExist?tableName=sch_school&filedName1=code&filedValue1="+$("#code").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("代码不能重复");
            }else{
			} 
		}
	}); */
	//提交倒计时
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/region/regionSpeciality/save",
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
				//location.href =  contextPath+  "/region/regionSpeciality?regionid="+ $("#regionid").val();
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
			},
			regionid:{
				required : true
			},
			specialityRecordid:{
				required : true
			},
			speciality:{
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			},
			regionid:{
				required : icon + "不能为空！"
			},
			speciality:{
				required : icon + "不能为空！"
			}
		}
	})
}
//专业名称
function majorName(){
	layer.open({
		type : 2,
		title : '专业名称',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content :contextPath+ "/region/regionSpeciality/showRegionSubject"// iframe的url
	});
}
