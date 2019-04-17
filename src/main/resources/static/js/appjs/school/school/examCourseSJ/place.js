$().ready(function() {
	validateRule();
	$("#courseName").val(parent.code+"    "+parent.names);
	$("#courseid").val(parent.code);
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

					$.ajax({
						cache : true,
						type : "POST",
						url :contextPath+ "/exam/examCourse/placeCourse",
						data : $('#signupForm').serialize(),// 你的formid
						async : false,
						error : function(request) {
							parent.layer.alert("Connection error");
						},
						success : function(data) {
							if (data == 1) {
								parent.layer.msg("操作成功");
								parent.reLoad();
								var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
								parent.layer.close(index);
							} else {
								parent.layer.alert("添加失败或限报课程已存在！");
							}

						}
					})
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
function coursePlace(){
	  layer.open({
        type: 2,
        title: '课程名称',
        shadeClose: false, // 点击遮罩关闭层
        area: ['90%', '90%'],
        content:contextPath+  "/exam/examCourse/coursePlace"
    }); 
	
}

