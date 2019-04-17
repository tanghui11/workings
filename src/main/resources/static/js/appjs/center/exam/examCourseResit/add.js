$().ready(function() {
	validateRule();
	kSrw();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	if($("#checkBox").prop("checked")){
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+  "/common/searchIfExist?tableName=exa_exam_course&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=exam_timeid&filedValue2="+$("#examTimeid").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				  if (data > 0) {
					layer.msg("开考课程或者是开考时间不能重复");
				}else{
					$.ajax({
						cache : true,
						type : "POST",
						url :contextPath+ "/exam/examCourse/save",
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
	}else{
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+  "/common/searchIfExist?tableName=exa_exam_course&filedName1=courseid&filedValue1="+$("#courseid").val()+"&filedName2=exam_timeid&filedValue2="+$("#examTimeid").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				  if (data > 0) {
					layer.msg("开考课程或者是开考时间不能重复");
				}else{
					$.ajax({
						cache : true,
						type : "POST",
						url :contextPath+ "/exam/examCourse/save",
						data : $('#signupForm').serialize(),// 你的formid
						async : false,
						error : function(request) {
							parent.layer.alert("Connection error");
						},
						success : function(data) {
							if (data.code == 0) {
								parent.layer.msg("操作成功");
								parent.reLoad();
								location.reload();
							} else {
								parent.layer.alert(data.msg)
							}

						}
					});
				}
			}
		});
	}
	/*    */
	 

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			ksrw:{
				min : 0
			},
			kktime:{
				min : 0
			},
			kkkc:{
				required : true
			},
			book:{
				min : 0
			}
		},
		messages : {
			name : {
				min : icon + "请输入名称"
			},
			ksrw:{
				min :icon + "考试任务不能为空"
			},
			kkkc:{
				required :icon + "开考课程不能为空"
			},
			book:{
				min :icon + "教材名称不能为空"
			},
			kktime:{
				min :icon + "开考时间不能为空"
			},
		}
	})
}

//考试任务
	
function kSrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "/exam/task/serchTaskAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value='-1'>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				if(data[i].id==parent.kaoshi){
					_html+="<option value="+"'"+data[i].id+"'"+" selected = 'selected'>"+data[i].examYear+"年"+data[i].examMonth+"</option>";
				}else{
					_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
				}
			}
			$("#ksrw").html(_html);
			$("#ksrw").trigger("change");
		}
	});
}
//开考时间
function ktime(val){
	$("#examTaskid").val(val);
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "/exam/examTime/seachExamTimeAllList?examTaskid="+val,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examDate.split(" ")[0]+"年"+data[i].segment+"【"+data[i].beginTime+"--"+data[i].endTime+"】"+"</option>";
			}
			$("#kktime").html(_html);
		}
	}); 
}

//开考课程 
function courseName(){
	  layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
        content:contextPath+  "/plan/course/clist"
    }); 
	
}
//获取bookid
function bookids(that){
	$("#bookid").val(that);
}
//开考时间id
function times(val){
	$("#examTimeid").val(val);
}