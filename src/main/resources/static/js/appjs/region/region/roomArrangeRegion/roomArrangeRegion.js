
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
		async : false,
		data:{
			"type":"a",
			"audit_status":"b",
			"confirm_status":"b",
            "arrange_date":"1"
		},
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option>请选择</option>";
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
//1.安排上下午都有考试的考生
function ap1(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	var reserveV=$("#reserve").val()/100
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "region/roomArrangeRegion/roomArrangeAuto1?exam_timeid="+$("#kktime").val()+"&exam_taskid="+$("#searchKsrw").val()+"&reserve="+reserveV+"&min_student="+$("#minStudent").val()+"&type=x&exam_siteid="+$("#searchKd").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			layer.alert(data);
			reLoad()
			//console.log(data)
		}
	});
}
//2.已安排课程补上剩余人数
function ap2(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	var reserveV=$("#reserve").val()/100
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "region/roomArrangeRegion/roomArrangeAuto2?exam_timeid="+$("#kktime").val()+"&exam_taskid="+$("#searchKsrw").val()+"&reserve="+reserveV+"&min_student="+$("#minStudent").val()+"&type=x&exam_siteid="+$("#searchKd").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			layer.alert(data);
			reLoad()
			//console.log(data)
		}
	});
}
//3.安排剩余课程
function ap3(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	var reserveV=$("#reserve").val()/100
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "region/roomArrangeRegion/roomArrangeAuto3?exam_timeid="+$("#kktime").val()+"&exam_taskid="+$("#searchKsrw").val()+"&reserve="+reserveV+"&min_student="+$("#minStudent").val()+"&type=x&exam_siteid="+$("#searchKd").val()+"&inMaxExamCourse="+$("#inMaxExamCourse").val(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			layer.alert(data);
			reLoad()
			//console.log(data)
		}
	});
}
//4.安排一门课程到一个考点
function ap4(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	var reserveV=$("#reserve").val()/100;
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	var rows2 = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择课程！");
		return;
	}
	if (rows2.length == 0) {
		layer.msg("请选择考点！");
		return;
	}
	layer.confirm("您确认要将选中的课程添加到["+rows2[0].code+" "+rows2[0].name+" "+rows2[0].roomNo+"]考点吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+ "region/roomArrangeRegion/roomArrangeAuto4?exam_timeid="+$("#kktime").val()+"&exam_taskid="+$("#searchKsrw").val()+"&reserve="+reserveV+"&min_student="+$("#minStudent").val()+"&type=x&exam_siteid="+$("#searchKd").val()+"&courseid="+rows[0].courseid+"&inMaxExamCourse="+$("#inMaxExamCourse").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				layer.alert(data);
				reLoad()
				//console.log(data)
			}
		});
	}, function() {

	});
	
}
//5.安排一门课程到一个考场
function ap5(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	var reserveV=$("#reserve").val()/100;
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	var rows2 = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择课程！");
		return;
	}
	if (rows2.length == 0) {
		layer.msg("请选择考点！");
		return;
	}
	layer.confirm("您确认要将课程["+rows[0].courseid+" "+rows[0].courseName+"]添加到["+rows2[0].code+" "+rows2[0].name+" "+rows2[0].roomNo+"]考点吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+ "region/roomArrangeRegion/roomArrangeAuto5?exam_timeid="+$("#kktime").val()+"&exam_taskid="+$("#searchKsrw").val()+"&reserve="+reserveV+"&min_student="+$("#minStudent").val()+"&type=x&exam_siteid="+$("#searchKd").val()+"&courseid="+rows[0].courseid+"&exam_roomid="+rows2[0].id+"&inMaxExamCourse="+$("#inMaxExamCourse").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				layer.alert(data);
				reLoad()
				//console.log(data)
			}
		});
	}, function() {

	});
	
}
//删除编排
function scbp(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	layer.confirm("考场编排删除之后需要重新编排，您确定要继续删除吗？", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+ "region/roomArrangeRegion/removeRoomArrange?exam_timeid="+$("#kktime").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				layer.closeAll(); //疯狂模式，关闭所有层
				layer.msg(data.msg);
				reLoad();
			}
		});
	}, function() {

	});
	
}
//编排确认
function bpqr(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	layer.confirm("编排调整后'本次考试的所有考场编排信息'将不能再修改！您确定要确认编排吗？", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+ "region/roomArrangeRegion/roomArrangeConfirm?exam_taskid="+$("#searchKsrw").val(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				layer.closeAll(); //疯狂模式，关闭所有层
				layer.msg(data.msg);
				reLoad();
			}
		});
	}, function() {

	});
	
}
//编排调整
function bptz(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	if($("#searchKd").val()==''){
		layer.alert("请先选择考点！");
		return;
	}
	layer.open({
		type : 2,
		title : '编排调整',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/region1' // iframe的url
	});
}
//考点间调整
function kdqtz(){
	if($("#kktime").val()==''){
		layer.alert("请先选择考试时间！并查询！");
		return;
	}
	layer.open({
		type : 2,
		title : '考点间调整',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/region2' // iframe的url
	});
}
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/getStudentCourses", // 服务器数据的加载地址
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
									field : 'courseid', 
									title : '课程代码' 
								},  {
									field : 'courseName', 
									title : '课程名称' 
								},   {
									field : 'count', 
									title : '编排人数' 
								},
								
								 
									/*	{
									field : 'regionid', 
									title : '考区编号' 
								},
										{
									field : 'examTimeid', 
									title : '考试时间编号' 
								},
										{
									field : 'examRoomid', 
									title : '考场编号' 
								},
										{
									field : 'examCourseid', 
									title : '开考课程编号' 
								},
										{
									field : 'type', 
									title : '考生类别' 
								},
										{
									field : 'examType', 
									title : '课程类别' 
								},
										{
									field : 'seatStart', 
									title : '座次开始号' 
								},
										{
									field : 'seatEnd', 
									title : '座次结束号' 
								},
										{
									field : 'secretCode', 
									title : '保密号' 
								},
										{
									field : 'firstStudentid', 
									title : '首考号' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
										{
									field : 'dbFlag', 
									title : '数据标记' 
								}, 
										{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								}*/ ]
					});
					
					
	$('#exampleTable2')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/getExamRooms", // 服务器数据的加载地址
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
								exam_taskid:$("#searchKsrw").val(),
								exam_siteid:$("#searchKd").val(),
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
									field : 'code', 
									title : '考点代码' 
								},	{
									field : 'name', 
									title : '考点名称' 
								},	{
									field : 'roomNo',
									title : '考场编号' 
								},	{
									field : 'num',
									title : '剩余座位数' 
								}
										
										
								 ]
					});	
}
function reLoad() {
	if($("#kktime").val()==''){
		layer.msg("请选择考试时间！");
		return;
	}
	$('#exampleTable').bootstrapTable('refresh',{url:  prefix + "/getStudentCourses",});
	$('#exampleTable2').bootstrapTable('refresh',{url: prefix + "/getExamRooms"});
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}