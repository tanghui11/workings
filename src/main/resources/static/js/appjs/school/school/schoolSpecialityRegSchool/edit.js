$().ready(function() {
	validateRule();
	college();
	examInstitutions();
	educational();
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
				$("#collegeId").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				collegeid.push(data[i].id)
			}
			var hui= $("#xueyuan").val();
			var obj = $("#collegeId option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == hui){
			        obj[i].selected = 'selected';
			        var index = $("#collegeId option:selected").index()
			        teachPoint(collegeid[index]);
					professionalName(collegeid[index]);
			        break;
			    }
			}
		}
	});
}

$("#collegeId").on("change",function(){
	var index = $("#collegeId option:selected").index()
	//alert(index)
	teachPoint(collegeid[index]);
	professionalName(collegeid[index]);
})
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
			$("#teachSiteName").find("option").remove();//首先清空下拉数据
			for (var i =0; i < data.length; i++) {
				$("#teachSiteName").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
		}
	});
}
//专业名称
function professionalName(collegeid){
	$.ajax({
		url : contextPath+"/school/collegeSpeciality/listCollegeSpeciality",
		type : "get",
		data:{
			collegeid:collegeid
		},
		success : function(data) {
			$("#specialityName").find("option").remove();//首先清空下拉数据
			for (var i =0; i < data.length; i++) {
				$("#specialityName").append("<option value=" + data[i].specialityRecordid + ">" +data[i].name+ "</option>");
			}
		}
	});
}
//主考院校
function examInstitutions(){
	$.ajax({
		url : contextPath+"/school/school/serchSchoolAll",
		type : "get",
		 //async:false,//更改为同步 
		success : function(data) {
			for (var i =0; i < data.length; i++) {
				$("#gradSchoolid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
			var hui= $("#gradSZ").val();
			var obj = $("#gradSchoolid option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == hui){
			        obj[i].selected = 'selected';
			        break;
			    }
			}
		}
	});
}
//学制联动
function educational(){
	var classify = $("#classify option:selected").val();
	var type = $("#type option:selected").val();
	$.ajax({
		url : contextPath+"/exam/educateLength/listEducateLength",
		type : "get",
		data:{
			classify:classify,
			type:type
		},
		success : function(data) {
			if(data.length==0){
				$("#educateLength").val("没有找到学制设置！");
				$("#educateLength2").val("");
			}else{
				$("#educateLength").val(data[0].lengthName);
				$("#educateLength2").val(data[0].length);
			}
		}
	});
}
function update() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/school/schoolSpecialityRegSchool/update",
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
function yemian(){
	layer.open({
		type : 2,
		title : "申请来源",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolForm"
	});
} 
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			collegeId : {
				required : true
			},
			teachSiteName : {
				required : true
			},
			specialityName : {
				required : true
			},
			type : {
				required : true
			},
			classify : {
				required : true
			},
			educateLength : {
				required : true
			},
			regYear : {
				required : true
			},
			num : {
				digits:true,
				Bgnum:true
			},
		},
		messages : {
			collegeId : {
				required : icon + "请选择学院名称"
			},
			teachSiteName : {
				required : icon + "请选择教学点名称"
			},
			specialityName : {
				required : icon + "请选择专业名称"
			},
			type : {
				required : icon + "请选择授课方式"
			},
			classify : {
				required : icon + "请选择专业层次"
			},
			educateLength : {
				required : icon + "学制不能为空"
			},
			regYear : {
				required : icon + "请选择年份"
			},
			num : {
				digits : icon + "请输入整数",
				Bgnum : icon + "必须大于0"
			},
		}
	})
}