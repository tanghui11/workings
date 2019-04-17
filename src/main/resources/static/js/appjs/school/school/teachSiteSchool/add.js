$().ready(function() {
	validateRule();
	SchoolArea();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
//办学地区
function SchoolArea(){
	$.ajax({
		url : contextPath+"/school/schoolSiteSchool/listSchoolSite",
		type : "get",
		 //async:false,//更改为同步 
		success : function(data) {
			for (var i =0; i < data.length; i++) {
				$("#schoolSiteid").append("<option value=" + data[i].id + ">"+data[i].code+" "+data[i].regionName+"</option>");
				$("#regionid").val(data[i].regionid);
			}
		}
	});
}
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/school/teachSiteSchool/save",
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
			schoolSiteid : {
				required : true 
			},
			collegeid : {
				required : true
			},
			name : {
				required : true,
				maxlength : 65,
			},
			pinyin : {
				maxlength : 65,
			},
			linkman : {
				maxlength : 15,
			},
			phone : {
				isTel : true,
			},
			mphone : {
				isPhone : true,
			},
			email : {
				email : true,
			},
			postCode : {
				isZipCode : true,
			},
			address : {
				maxlength : 65,
			},
		},
		messages : {
			schoolSiteid : {
				required : icon + "请选择办学点"
			},
			collegeid : {
				required : icon + "请输入学院编号"
			},
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "最多输入65个字"
			},
			pinyin : {
				maxlength : icon + "最多输入65个字符"
			},
			linkman : {
				maxlength : icon + "最多输入15个字符"
			},
			phone : {
				isTel : icon + "请填写正确的座机号码"
			},
			mphone : {
				isPhone : icon + "请填写正确的11位手机号"
			},
			email : {
				email : icon + "请输入正确的电子邮箱"
			},
			postCode : {
				isZipCode : icon + "请正确填写您的邮政编码"
			},
			address : {
				maxlength : icon + "最多输入65个字符"
			},
		}
	})
}