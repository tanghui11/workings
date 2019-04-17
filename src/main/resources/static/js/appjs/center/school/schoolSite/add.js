$().ready(function() {
	$("#schoolid").val(parent.$("#orgid").val());
	validateRule();
	regionList();
	
});
 var url = location.search;
var ids = url.split("?")[1].split("=")[1]; 
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+ "/school/schoolSite/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
                //self.location=document.referrer;
				
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
			code:{
				required : true,
				maxlength:4,
				digits:true
			},
			schoolid:{
				required : true
			},
			regionid:{
				required : true
			}
		},
		messages : {
			code:{
				required : icon + "请输入代码",
				maxlength:icon + "只能输入4位",
				digits: icon + "必须输入数字",
			},
			schoolid:{
				required : icon + "请输入助学组织编码"
			},
			regionid:{
				required : icon + "请输入助学考区编码"
			}
		}
	})
}
//地州市考办
function regionList(){
	var _html ="<option value = '-1'>-请选择-</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'type' : "a"
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
				_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
			}
			$("#schoolids").html(_html);
		}
	});
}
//根据省考办获取县考办
function region(){
	var _html ="";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'parentid' : $("#schoolids").val()
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){

				_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
			}
			$("#regionid").html(_html);
		}
	});
}