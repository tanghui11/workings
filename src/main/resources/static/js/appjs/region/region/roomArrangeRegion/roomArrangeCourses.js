
var prefix = contextPath+"/region/roomArrangeRegion"
$(function() {
	load();
	kSrw();
});
//考试任务
function kSrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "exam/task/serchTaskAll",// 你的formid
        data:{
            "type":"a",
            "audit_status":"b",
            "confirm_status":"b"
        },
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
			}
			$("#searchKsrw").html(_html);
			ktime($("#searchKsrw").val());
			kd($("#searchKsrw").val());
		}
	});
}
$("#searchKsrw").change(function() {
	ktime($("#searchKsrw").val());
	kd($("#searchKsrw").val());
});
//开考时间

function ktime(val){
    $.ajax({
        cache : true,
        type : "get",
        url :contextPath+ "/exam/examTime/seachExamTimeAllList?examTaskid="+val,// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            var _html="<option value=''>-请选择-</option>";
            for(var i=0;i<data.length;i++){
                _html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examDate.split(" ")[0]+"年"+data[i].segment+"【"+data[i].beginTime+"--"+data[i].endTime+"】"+"</option>";
            }
            $("#kktime").html(_html);
        }
    });
}


//考点
function kd(val){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "region/roomArrangeRegion/getExamSites?exam_taskid="+val,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			 var _html="<option value=''>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].name+"</option>";
			}
			$("#searchKd").html(_html);
		}
	});
}

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/getExamCoursesByExamSiteid", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : true, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize: 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								exam_timeid:$("#kktime").val(),
								exam_siteid:$("#searchKd").val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
									{
									field : 'roomNo', 
									title : '考场编号',
									width :	'80px'
								},
									{
									field : 'courseid', 
									title : '课程代码' 
								},  {
									field : 'courseName', 
									title : '课程名称' 
								},   {
									field : 'count', 
									title : '编排人数' 
								},
								
								 
								 ]
					});
					
					
	
}
function reLoad() {
	if($("#searchKsrw").val()==''||$("#kktime").val()==''||$("#searchKd").val()==''){
		layer.msg("请选择考试时间和考点！");
		return;
	}
	$('#exampleTable').bootstrapTable('refresh');
	$('#exampleTable2').bootstrapTable('refresh');
}
