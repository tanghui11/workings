$().ready(function() {
	validateRule();
	$("#studentid").val(parent.$("#studentid").val());
	$("#collegeName").val(parent.$("#collegeName").val());
	$("#teachSiteName").val(parent.$("#teachName").val());
	$("#teachSiteid").val(parent.$("#teachSiteid").val());
	$("#collegeid").val(parent.$("#collegeid").val());
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
		url : contextPath+ "/student/studentSpeciality/update",
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