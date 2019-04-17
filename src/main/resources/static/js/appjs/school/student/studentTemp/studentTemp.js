
var prefix = contextPath+"/student/studentTempStudent"
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
								offset:params.offset
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
									field : 'type', 
									title : '类别' 
								},
										{
									field : 'ksZkz', 
									title : '准考证号' 
								},
										{
									field : 'ksXm', 
									title : '姓名' 
								},
										{
									field : 'ksExm', 
									title : '英文名' 
								},
										{
									field : 'ksXb', 
									title : '性别' 
								},
										{
									field : 'hjDm', 
									title : '户籍代码' 
								},
										{
									field : 'mmDm', 
									title : '面貌代码' 
								},
										{
									field : 'mzDm', 
									title : '民族代码' 
								},
										{
									field : 'xlDm', 
									title : '学历代码' 
								},
										{
									field : 'zhiyDm', 
									title : '职业代码' 
								},
										{
									field : 'zyDm', 
									title : '专业代码' 
								},
										{
									field : 'firstZy', 
									title : '第一专业' 
								},
										{
									field : 'jtdmDm', 
									title : '集体代码' 
								},
										{
									field : 'ksSfz', 
									title : '身份证号' 
								},
										{
									field : 'ksBirthda', 
									title : '出生日期' 
								},
										{
									field : 'ksPhone', 
									title : '联系电话' 
								},
										{
									field : 'ksZip', 
									title : '邮编' 
								},
										{
									field : 'ksAddress', 
									title : '联系地址' 
								},
										{
									field : 'ksBmxs', 
									title : '报名市县' 
								},
										{
									field : 'ksBmsj', 
									title : '报名时间' 
								},
										{
									field : 'ksZdyxx', 
									title : '' 
								},
										{
									field : 'ksTksj', 
									title : '停考时间' 
								},
										{
									field : 'ksYqby', 
									title : '延期毕业' 
								},
										{
									field : 'ksQx', 
									title : '考生区县' 
								},
										{
									field : 'sxDm', 
									title : '市县代码' 
								},
										{
									field : 'modified', 
									title : '修改标志' 
								},
										{
									field : 'ksOldzkz', 
									title : '旧准考证号' 
								},
										{
									field : 'zyMc', 
									title : '专业名称' 
								},
										{
									field : 'mm', 
									title : '密码' 
								},
										{
									field : 'sj1', 
									title : '手机1' 
								},
										{
									field : 'sj2', 
									title : '手机2' 
								},
										{
									field : 'zzdh', 
									title : '电话' 
								},
										{
									field : 'ybmh', 
									title : '预报名号' 
								},
										{
									field : 'bmddm', 
									title : '报名点代码' 
								},
										{
									field : 'gzdw', 
									title : '工作单位' 
								},
										{
									field : 'email', 
									title : '电子邮件' 
								},
										{
									field : 'msdm', 
									title : '' 
								},
										{
									field : 'hkszd', 
									title : '' 
								},
										{
									field : 'gzdwszd', 
									title : '' 
								},
										{
									field : 'flag', 
									title : '' 
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
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.type
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.type
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.type
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {

    location.href =  prefix + '/edit/' + id ;
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'type' : id
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
			ids[i] = row['type'];
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