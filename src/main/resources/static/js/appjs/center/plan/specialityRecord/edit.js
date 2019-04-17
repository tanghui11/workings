$().ready(function() {
	validateRule();
	$("#gradCourse").val(parent.gradCourse);
	$("#subjectName").val(parent.subjectNames);
	var _html = '<option value="">-请选择-</option>';
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/school/school/serchSchoolAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				if(data[i].id==$("#schoolid").val()){
					_html+="<option value="+data[i].id+" selected='selected'>"+data[i].name+"</option>"
				}else{
					_html+="<option value="+data[i].id+">"+data[i].name+"</option>"
				}
			}
			$("#schoolName").html(_html);
			
		}
	});
	$("#schoolName").trigger("change");
	if($("#type").val()=="b"){
		$(".zhukao").hide();
	}else if($("#type").val()=="a"){
		$(".zhukao").show();
	}	
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
		url :contextPath+  "/common/searchIfExist?tableName=pla_speciality_record&method=update&updateKey=id&updateKeyValue="+$("#id").val()+"&filedName1=specialityid&filedValue1="+$("#specialityid").val()+"&filedName2=type&filedValue2="+$("#type").val(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			  if (data > 0) {
				layer.msg("专业名称不能重复");
            }else{
				$.ajax({
					cache : true,
					type : "POST",
					url :contextPath+  "/plan/specialityRecord/update",
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
function xzy (id){
	layer.open({
		type : 2,
		title : '设置新专业',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : contextPath+ "/plan/specialityRecord/showSubject2" // iframe的url
	}); 
	//alert(id)
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			subjectName:{
				required : true
			},
			type:{
				required : true
			},
			schoolName:{
				required : true
			},
			collegeName:{
				required : true
			},
			gradCourseid:{
				required : true
			},
			status:{
				required : true
			},
			specialityid: {
				required : true,
				maxlength:20
			},
			direction: {
				maxlength:20
			}
		},
		messages : {
			subjectName:{
				required :icon + "不能为空！"
			},
			type:{
				required :icon + "不能为空！"
			},
			schoolName:{
				required :icon + "不能为空！"
			},
			collegeName:{
				required :icon + "不能为空！"
			},
			gradCourseid:{
				required :icon + "不能为空！"
			},
			status:{
				required :icon + "不能为空！"
			},
			specialityid: {
				required :icon + "专业代码不能为空！",
				maxlength:icon + "最多不能超过20"
			},
			direction: {
				maxlength:icon + "最多不能超过20"
			}
		}
	})
}
//根据类别来判断
function types(that){
	if(that=="a"){
		$(".zhukao").show();
	}else if(that=="b"){
		$(".zhukao").hide();
	}
}


//跟据组织机构ID查询相应的二级学院
function secondCollege(){
	$("#schoolid").val($("#schoolName").val());
	var _html ="<option value=''>-请选择-</option>";
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+  "/school/college/seachCollege?schoolid="+$("#schoolid").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				if($("#collegeid").val()==data[i].id){
					_html+="<option value="+data[i].id+" selected='selected'>"+data[i].name+"</option>"
				}else{
					_html+="<option value="+data[i].id+">"+data[i].name+"</option>"
				}
			}
				$("#collegeName").html(_html);
		}
	});
}
//专业名称
function majorName(){
	  layer.open({
        type: 2,
        title: '专业名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
		  content: contextPath+  "/plan/speciality/showSubject"
    }); 
	
}
//课程名称
function courseName(){
	  layer.open({
        type: 2,
        title: '专业名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content:contextPath+  "/plan/course/clist"
    }); 
	
}
//主考学院id
function colleges(that){
	$("#collegeid").val(that);
}