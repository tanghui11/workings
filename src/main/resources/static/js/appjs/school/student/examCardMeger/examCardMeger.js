
var prefix =contextPath+  "/student/score"
$(function() {
	load();
	load1();
});
var studentid=null;
var courseid=null;
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/list", // 服务器数据的加载地址
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
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            studentInid:$('#studentInid').val(),
								studentid:$("#searchName").val()
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
									field : 'id', 
									title : '序号',
									width : '40px',
									formatter: function (value, row, index) {
										return index+1;
									}

								},
									{
									field : 'courseid', 
									title : '课程代码' 
								},
									{
									field : 'courseName', 
									title : '课程名称' 
								},
										
									{
									field : 'studentid', 
									title : '准考证号' 
								},
										{
									field : 'type', 
									title : '类别' 
								},
										{
									field : 'grade', 
									title : '成绩' 
								},
									{
									field : 'useStatus', 
									title : '使用状态' 
								}],onCheck:function(row){
									courseid = row.courseid;
									studentid = row.studentid;
								}
								
					});
}
var courseids = null;
var yStudentid = null;
function load1() {
	$('#exampleTable1')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/list", // 服务器数据的加载地址
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
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            studentInid:$('#studentInid').val(),
								"studentid":$("#searchName1").val(),
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
									field : 'id', 
									title : '序号',
									width : '40px',
									formatter: function (value, row, index) {
										return index+1;
									}

								},
										{
									field : 'courseid', 
									title : '课程代码' 
								},
								
										{
									field : 'courseName', 
									title : '课程名称' 
								},
								{
									field : 'studentid', 
									title : '准考证号' 
								},
										{
									field : 'grade', 
									title : '成绩' 
								},
										{
									field : 'useStatus', 
									title : '使用状态' 
								},{
									field : 'specialityRecordid', 
									title : '使用专业' 
								},
								{
									field : 'oldStudentid', 
									title : '原准考证号' 
								}],onCheck:function(row){
									yStudentid = row.oldStudentid;
									courseids = row.courseid;
								}
					});
}
function reLoad() {
	 if($("#searchName").val()==""){
		layer.msg("请输入准考证查询")
	}else{ 
		$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
	 } 
	
}
function reLoad1() {
	if($("#searchName1").val()==""){
		layer.msg("请输入准考证查询")
	}else{
		$('#exampleTable1').bootstrapTable('refresh',{url : prefix + "/listTwo"});
	}
}
function add() {
	layer.open({
		type : 2,
		title : "添加 【 考籍管理 > 考籍转入 > 成绩信息 】",
		maxmin : true,
		shadeClose : false, 
		area : [ '635px', '345px' ],
		content : prefix + '/add'
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : "编辑 【 考籍管理 > 考籍转入 > 成绩信息】",
		maxmin : true,
		shadeClose : false, 
		area : [ '635px', '330px' ],
		content : prefix + '/edit/' + id
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


function meger(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要合并的数据");
		return;
	}
	if($("#searchName1").val()==""){
		layer.msg("请输入要合并的准考证");
		return;
	}
	 if($("#searchName").val()==$("#searchName").val()){
		layer.msg("准考证不能相同");
		return;
	} 
	$.ajax({
			type : 'POST',
			data : {
				"oldStudentid" : studentid,
				"courseid" : courseid,
				"newStudentid":$("#searchName1").val(),
			},
			url : prefix + '/merge',
			success : function(r) {
				reLoad();
				reLoad1();
			}
		}); 
		
}
function remeger(){
	var rows = $('#exampleTable1').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消合并的数据");
		return;
	}
	if(yStudentid==null || yStudentid==""){
		layer.msg("没有找到取消合并的目标准考证号");
		return;
	}
	$.ajax({
			type : 'POST',
			data : {
				
				"oldStudentid" : yStudentid,
				"courseid" : courseids,
				"newStudentid":$("#searchName1").val(),
			},
			url : prefix + '/reMeger',
			success : function(r) {
				reLoad();
				reLoad1();
				
			}
		}); 
}