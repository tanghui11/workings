
var prefix =contextPath+ "/exam/task"
$(function() {
	
	//考试年份
	var _yearHtml="<option value=''>-请选择-</option>"
	$.ajax({
		type : 'get',
		url :contextPath + '/exam/task/serchTaskAll',
		success : function(r) {
			for(var i=0;i<r.length;i++){
				_yearHtml+="<option value='"+r[i].examYear+"'>"+r[i].examYear+"</option>"
			}
			$("#year").html(_yearHtml);
		}
	});
	load();
});
var hangId;
var nian;
var yue;
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/seachBkTimeSetlist", // 服务器数据的加载地址
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
								examYear:$('#year').val()
					           // examYear:$('#year').val(),
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
									radio : true
								},
									/* {
									field : 'id', 
									title : '编号' 
								}, */
										{
									field : 'examYear', 
									title : '年份' 
								},
										{
									field : 'examMonth', 
									title : '月份' 
								},
										{
									field : 'beginDate', 
									title : '助学生报考开始日期' 
								},
										{
									field : 'endDate', 
									title : '助学生报考结束日期' 
								},
										{
									field : 'beginDateAppend', 
									title : '助学生补报开始日期' 
								},
										{
									field : 'endDateAppend', 
									title : '助学生补报结束日期' 
								},
										{
									field : 'beginDate1', 
									title : '社会生报考开始日期' 
								},
										{
									field : 'endDate1', 
									title : '社会生报考结束日期' 
								},
										{
									field : 'beginDateAppend1', 
									title : '社会生补报开始日期' 
								},
										{
									field : 'endDateAppend1', 
									title : '社会生补报结束日期' 
								},
									/* 	{
									field : 'arrangeBeginDate', 
									title : '考场编排考试日期' 
								},
										{
									field : 'arrangeEndDate', 
									title : '考场编排考试日期' 
								}, 
										{
									field : 'remark', 
									title : '备注' 
								}, */
										{
									field : 'status', 
									title : '状态' 
								},/*
										{
									field : 'confirmStatus', 
									title : '确认状态' 
								},
										{
									field : 'auditStatus', 
									title : '审核状态' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},*/
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="设置" onclick="edit(\''
												+ row.id+","+row.examYear+";"+row.examMonth
												+ '\')">设置</a> ';
										return e  ;
									}
								} ],
								onCheck:function(row){
									 hangId = row.id;
									 nian = row.examYear;
									 yue = row.examMonth;
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
    location.href =  prefix + '/add';
}
function edit(ids) {
	var id = ids.split(",")[0];
	var nian =ids.split(",")[1].split(";")[0];
	var yue = ids.split(";")[1];
	layer.open({
		type : 2,
		title : '设置 [ 考务管理 > 报考时间设置 ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '900px', '570px' ],
		content :prefix + '/edit/' + id+"?year="+nian+"&mouth="+yue// iframe的url
	});
    /* location.href =  prefix + '/edit/' + id+"?year="+nian+"&mouth="+yue; */
}
function edits(){
	 if(hangId==undefined){
		layer.msg("请选中某一行")
	}else{
		layer.open({
		type : 2,
		title : '设置 [ 考务管理 > 报考时间设置 ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '900px', '570px' ],
		content :prefix + '/edit/' + hangId+"?year="+nian+"&mouth="+yue // iframe的url
	});
		/* location.href =  prefix + '/edit/' + hangId+"?year="+nian+"&mouth="+yue ; */
	}
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
function ss(that){
}

//导入
function importData() {
    layer.open({
        type : 2,
        title : '报考时间导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/TimportData' // iframe的url
    });
}