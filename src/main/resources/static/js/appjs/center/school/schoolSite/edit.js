$().ready(function() {
	$("#schoolid").val(parent.$("#orgid").val());
	validateRule();
	regionList();
	/* $("#schoolid").val(parent.regionSheng)
	$("#regionid").val(parent.regionXian) */
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	countDown("6","submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/school/schoolSite/update",
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
	var parentid=null;
	$.ajax({
		url :contextPath+  "/region/region/getregionByregionid",
		type : "get",
		data : {
			'id' : $("#regionids").val()
		},
		success : function(r) {
			parentid=r.parentid
		}
	});
	var _html ="<option value = '-1'>-请选择-</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'type' : "a"
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
				if(parentid==r[i].id){
					_html+="<option value = '"+r[i].id+"' selected>"+r[i].name+"</option>"
				}else{
					_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
				}
				
			}
			$("#schoolids").html(_html);
			region();
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
					if($("#regionids").val()==r[i].id){
						_html+="<option value = '"+r[i].id+"' selected>"+r[i].name+"</option>"
					}else{
						_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
					}
				
			}
			$("#regionid").html(_html);
		}
	});
}