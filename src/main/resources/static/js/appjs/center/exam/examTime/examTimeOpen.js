
var prefix =contextPath+ "/exam/examTime"
$(function() {

	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
								examTaskid:parent.$("#examTaskid").val()
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
									/* {
									field : 'id', 
									title : '编号' 
								},
										{
									field : 'examTaskid', 
									title : '任务编号' 
								},
										{
									field : 'seq', 
									title : '序号' 
								} ,*/
										{
									field : 'examDate', 
									title : '开考日期' 
								},
										{
									field : 'segment', 
									title : '时段' 
								},
										{
									field : 'beginTime', 
									title : '开考时间' 
								},
										{
									field : 'endTime', 
									title : '结束时间' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width : '150px',
								} ],onCheck:function(row){
                            parent.layer.closeAll('iframe');
                            parent.$("#examTimeid").val(row.id);
                            parent.$("#examTimeName").val(row.examDate+" "+row.segment+" "+row.beginTime+"-"+row.endTime);
                            //parent.$("#workerid").val(row.id)
                        }
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '添加 [ 计划管理 > 考试时间设置 ] ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '360px' ],
		content : prefix + '/add' // iframe的url
	})
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑 [ 计划管理 > 考试时间设置 ]',
		shadeClose : false, // 点击遮罩关闭层
		area : ['800px', '360px' ],
		content :  prefix + '/edit/' + id // iframe的url
	})
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
function  importData() {
    layer.open({
        type : 2,
        title : '批量导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix +'/importData?examTaskid='+$('#examTaskid').val() // iframe的url
    });
}
