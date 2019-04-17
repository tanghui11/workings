
var prefix =contextPath+  "/region/studentOut"
$(function() {
	load();
});

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
								studentid:$("#searchName").val(),
								status:$("#status").val(),
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
									radio : true
								},
									{
									field : 'id', 
									title : '序号' ,
									formatter: function (value, row, index) {
									return index+1;
									}
								},
									{
									field : 'name', 
									title : '姓名' 
								},
									{
									field : 'pinyin', 
									title : '拼音' 
								},
										{
									field : 'regionid', 
									title : '转出地区编号' 
								},
										{
									field : 'studentid', 
									title : '准考证号' 
								},
										{
									field : 'specialityid', 
									title : '专业代码' 
								},
										/* {
									field : 'code', 
									title : '档案编号' 
								}, */
										{
									field : 'province', 
									title : '转入省份' 
								},
										{
									field : 'city', 
									title : '转入地市' 
								},
										{
									field : 'cause', 
									title : '转出原因' 
								},
										{
									field : 'zkInZkz', 
									title : '转入准考证号' 
								},
										{
									field : 'zkInZyDm', 
									title : '转入专业代码' 
								},
										{
									field : 'zkZyFx', 
									title : '转入专业方向' 
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
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-success btn-sm" href="#" title=""  mce_href="#" onclick="edit(\''
												+ row.studentid
												+ '\')">考试成绩</a> ';
										var q = '<a class="btn btn-success btn-sm" href="#" title=""  mce_href="#" onclick="blzy(\''
												+ row.studentid
												+ '\')">报考专业</a> ';
										return q+e  ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
}
function add() {

    location.href =  prefix + '/add';
}
function edit(studentid) {
	layer.open({
		type : 2,
		title : '考试成绩 ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '360px' ],
		content : contextPath+  '/region/studentOutScore?studentid='+studentid// iframe的url
	});
}
function blzy(studentid) {
	layer.open({
		type : 2,
		title : '报考专业 ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : contextPath+'region/studentOutSpeciality?studentid='+studentid
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
//转出
function zhuanchu(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择转出数据！");
		return;
	}
	var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['studentid'];
		});
	layer.confirm('确定要转出该考生的信息吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		layer.confirm('考籍转出后将无法恢复，您确定要继续吗？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				type : 'POST',
				data : {
					"id" : ids[0]
				},
				url : prefix + '/out',
				success : function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
						reLoad();
					} else {
						layer.msg(r.msg);
					}
				}
			});
		})
	})
	
}