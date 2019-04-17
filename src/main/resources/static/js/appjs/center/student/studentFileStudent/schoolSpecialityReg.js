
var prefix = contextPath+"/student/studentFile"
$(function() {
	for(var i=0;i<$("#auditStatus").children("option").length;i++){
		if($("#auditStatus").children("option")[i].value=="a"){
			$("#auditStatus").children("option")[i].selected = true;
		}
	}
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listSchoolSpecialityReg", // 服务器数据的加载地址
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
								specialityRecordid:parent.$("#specialityRecordid").val(),
								schoolid:parent.$("#schoolid").val(),
					            subjectCode:$('#searchName').val(),
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
									},
									width : '40px'
								},
								{
									field : 'subjectCode', 
									title : '专业代码' 
								},
									{
									field : 'subjectName', 
									title : '专业名称' 
								},
										{
									field : 'schoolName', 
									title : '助学组织' 
								},/* 
										{
									field : 'specialityRecordid', 
									title : '专业开设编号' 
								},
										{
									field : 'regionid', 
									title : '地州市考办代码' 
								},
										{
									field : 'specialityRecordid', 
									title : '专业开设编号' 
								},
										{
									field : 'regionid', 
									title : '地州市考办代码' 
								},
										{
									field : 'specialityRecordid', 
									title : '专业开设编号' 
								},
										{
									field : 'regionid', 
									title : '地州市考办代码' 
								}, */
										{
									field : 'classify', 
									title : '专业层次' 
								},
										{
									field : 'type', 
									title : '助学方式' 
								},
										{
									field : 'method', 
									title : '助学手段' 
								},
										{
									field : 'educateLength', 
									title : '学制' ,
									width : '60px'
								},
										{
									field : 'regYear', 
									title : '招生年份' ,
									width : '70px'
								},
										/* {
									field : 'regSeason', 
									title : '招生季节' ,
									width : '70px'
								},
										{
									field : 'num', 
									title : '招生人数' ,
									width : '60px'
								},
										{
									field : 'sourceRegid', 
									title : '申请来源' ,
									width : '70px'
								}, */
										{
									field : 'teachSiteName', 
									title : '主考学校' 
								},/* 
										{
									field : 'auditStatus', 
									title : '审核状态' ,
									width : '70px'
								},
										{
									field : 'status', 
									title : '状态' ,
									width : '70px'
								},
										{
									field : 'operator', 
									title : '操作员' ,
									width : '70px'
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width : '90px'
								}, */
										/* {
									title : '操作',
									field : 'id',
									align : 'center',
									width : '80px',
									formatter : function(value, row, index) {
										if(row.auditStatus=="待审核"){
											var f = '<a class="btn btn-success btn-sm" href="#" title=""  mce_href="#" onclick="resetPwd(\''
												+ row.id +";" + row.auditStatus
												+ '\')">审核</a> ';
										}else{
											var f = '<a class="btn btn-warning btn-sm" href="#" title=""  mce_href="#" onclick="resetPwd(\''
												+ row.id +";" + row.auditStatus
												+ '\')">取消审核</a> ';
										}
										return f ;
									}
								} */ ],
								onCheck:function(row){
									parent.layer.closeAll('iframe');
									parent.$("#searchName").val('【'+row.subjectCode+'】'+row.subjectName+"  【"+row.classify+" "+row.type+" "+row.educateLength+"】【"+row.regYear+" "+row.regSeason+"】");
									parent.$("#schoolSpecialityRegid").val(row.id);
								}
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
//单个审核
function resetPwd(id) {
	 var ids =id.split(";")[0];
	var zt = id.split(";")[1];
	if(zt=="待审核"){
		layer.confirm('您确定要审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				type : 'POST',
				url : contextPath + '/school/schoolSpecialityReg/updateAudit',
				data : {
					"id" : ids,
					"auditStatus":"b"
				},
				
				success : function(r) {
					console.log(r);
					if (r.code == 0) {
						layer.msg(r.msg);
						reLoad();
					} else {
						layer.msg(r.msg);
					}
				}
			});
       }); 	
	}else{
		layer.confirm('您确定要取消审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				type : 'POST',
				url : contextPath + '/school/schoolSpecialityReg/updateAudit',
				data : {
					"id" : ids,
					"auditStatus":"a"
					
				},
				success : function(r) {
					console.log(r);
					if (r.code == 0) {
						
						layer.msg(r.msg);
						reLoad();
					} else {
						layer.msg(r.msg);
					}
				}
			});
       }); 
		
	}

}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	layer.confirm("确认要审核选中的'" + rows.length + "'条数据吗?", {
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
			url : contextPath + '/school/schoolSpecialityReg/batchUpdateAudit',
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


//批量审核
function shenghe(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	layer.confirm("确认要审核选中的'" + rows.length + "'条数据吗?", {
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
				"ids" : ids,
				"auditStatus":"b"
			},
			url : contextPath + '/school/schoolSpecialityReg/batchUpdateAudit ',
			success : function(r) {
				console.log(r)
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
//批量取消审核

function cancalAudit() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消审核的数据");
		return;
	}
	layer.confirm("确认要取消审核选中的'" + rows.length + "'条数据吗?", {
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
				"ids" : ids,
				"auditStatus":"a"
			},
			url : contextPath + '/school/schoolSpecialityReg/batchUpdateAudit ',
			success : function(r) {
				console.log(r)
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
