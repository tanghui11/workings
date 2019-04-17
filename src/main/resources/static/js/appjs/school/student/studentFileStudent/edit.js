$().ready(function() {
	validateRule();
	college();
	phoneHX();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
//学院名称数据
var collegeid=[];
function college(){
	$.ajax({
		url : contextPath+"/school/collegeSchool/listCollege",
		type : "get",
		 //async:false,//更改为同步 
		success : function(data) {
			for (var i =0; i < data.length; i++) {
				$("#collegeid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				collegeid.push(data[i].id)
			}
			var hui= $("#xueyuan").val();
			var obj = $("#collegeid option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == hui){
			        obj[i].selected = 'selected';
			        var index = $("#collegeid option:selected").index()
			        teachPoint(collegeid[index]);
			        break;
			    }
			}
		}
	});
}
$("#collegeid").on("change",function(){
	var index = $("#collegeid option:selected").index()
	//alert(index)
	teachPoint(collegeid[index])
	$("#schoolSpecialityRegid").val("")
})

//照片回显
function phoneHX(){
	var src = $("#pic").val()+'?number='+Math.random();//url不变参数变
	$("#scPhone").attr("src",src)
	var url = $("#idcardPic").val()
	$("#id_img_pers").attr("src",url)
}

//教学点数据
function teachPoint(collegeid){
	//alert(collegeid)
	$.ajax({
		url : contextPath+"/school/teachSite/listTeachSite",
		type : "get",
		data:{
			collegeid:collegeid
		},
		success : function(data) {
			$("#teachSiteid").find("option").remove();//首先清空下拉数据
			for (var i =0; i < data.length; i++) {
				$("#teachSiteid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
			var hui= $("#jiaoxuedian").val();
			var obj = $("#teachSiteid option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == hui){
			        obj[i].selected = 'selected';
			    }
			}
		}
	});
}
//报考专业
function yemian(){
	layer.open({
		type : 2,
		title : "报考专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
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
//原学专业
function yemianYXZY(){
	layer.open({
		type : 2,
		title : "原学专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/student/studentStudent/showSubject"
	});
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
function update() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/student/studentStudent/update",
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
				required : true,
				maxlength : 15
			},
			pinyin : {
				maxlength : 15
			},
			birthday : {
				required : true,
			},
			certificateNo : {
				required : true,
				isIdentity : true
			},
			address : {
				maxlength : 65
			},
			postCode : {
				isZipCode : true
			},
			email : {
				email : true 
			},
			phone : {
				isTel : true 
			},
			mphone : {
				isPhone : true 
			},
			teachSiteid : {
				required : true,
			},
			xBZY : {
				required : true,
			},
			gradSchool : {
				required : true,
			},
			gradCertificate : {
				required : true,
			},
			yYZY : {
				required : true,
			},
		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength : icon + "最多输入15个字"
			},
			pinyin : {
				maxlength : icon + "最多输入15个字符"
			},
			birthday : {
				required : icon + "请选择日期",
			},
			certificateNo : {
				required : icon + "身份证不能为空",
				isIdentity : icon + "请输入正确的身份证",
			},
			address : {
				isIdentity : icon + "最多输入65个字",
			},
			postCode : {
				isZipCode : icon + "请输入正确的邮政编码",
			},
			email : {
				email : icon + "请输入正确邮箱",
			},
			phone : {
				isTel : icon + "请填写正确的座机号码",
			},
			mphone : {
				isPhone : icon + "请填写正确的11位手机号",
			},
			teachSiteid : {
				required : icon + "请选择现教学点",
			},
			xBZY : {
				required : icon + "请选择现报专业",
			},
			gradSchool : {
				required : icon + "请输入毕业院校",
			},
			gradCertificate : {
				required : icon + "请输入毕业证书号",
			},
			yYZY : {
				required : icon + "请选择原学专业",
			},
		}
	})
}