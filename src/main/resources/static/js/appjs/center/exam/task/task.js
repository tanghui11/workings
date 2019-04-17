
var prefix =contextPath+ "/exam/task"
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
								offset:params.offset,
					            examYear:$('#searchName').val(),
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
								}, */
										{
									field : 'examYear', 
									title : '年份' ,
									width : '80px'
								},
										{
									field : 'examMonth', 
									title : '月份',
									width : '80px'									
								},		{
									field : 'type', 
									title : '考试类型',
									width : '100px'									
								},/* 
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
										{
									field : 'arrangeBeginDate', 
									title : '考场编排考试日期' 
								},
										{
									field : 'arrangeEndDate', 
									title : '考场编排考试日期' 
								}, */
										{
									field : 'remark', 
									title : '备注' 
								},
										{
									field : 'status', 
									title : '状态' ,
									width : '80px'
								},
										{
									field : 'confirmStatus', 
									title : '确认状态' ,
									width : '80px'
								},
									/* 	{
									field : 'auditStatus', 
									title : '审核状态' 
								}, */
										{
									field : 'operator', 
									title : '操作员' ,
									width : '80px'
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width : '90px'
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '350px',
									formatter : function(value, row, index) {
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
											var q = '<a class="btn btn-success btn-sm"'
												+'style="margin-right:5px"'
												+ '" href="'+contextPath+'/exam/examItem/?id='+row.id+'&examYear='+row.examYear+'&examMonth='+row.examMonth+'&type='+row.type
												+ '" data-index="examRegOrg"'
												+ ' data-text="[' + row.examYear +'年'+ row.examMonth+']考试项目设置"'
												+ ' data-refresh="true"'
												+ ' onclick="parent.menuItem(this);return false;"'
												+ ' data-id="examRegOrg">考试项目</a>';
											var e = '<a class="btn btn-primary btn-sm"'
												+'style="margin-right:5px"'
												+ '" href="'+contextPath+'/exam/examTime/?id='+row.id+'&examYear='+row.examYear+'&examMonth='+row.examMonth
												+ '" data-index="examRegOrg"'
												+ ' data-text="[' + row.examYear +'年'+ row.examMonth+']考试时间设置"'
												+ ' data-refresh="true"'
												+ ' onclick="parent.menuItem(this);return false;"'
												+ ' data-id="examRegOrg">考试时间</a>';
												
												/*  var f = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="确认" onclick="edit(\''
													+ row.id+"','"+row.examYear+"','"+row.examMonth+"','"+row.confirmStatus
													+ '\')">确认</a> '; */
											var f ='<div style="display:inline-block;">'
													+'<div class="btn-group">'
														+'<button class="btn btn-info btn-sm " style="border-radius:3px;margin-right:5px" data-toggle="dropdown">操作&nbsp;&nbsp;&nbsp;<span class="caret"></span></button>'
														+'<ul class="dropdown-menu">'
															+'<li><a  href="#" mce_href="#" title="确认" onclick="edit(\''
																+ row.id+"','"+row.confirmStatus
																+ '\')">确认</a></li>'
															+'<li><a href="#" mce_href="#" title="取消确认" onclick="edits(\''
																+ row.id+"','"+row.confirmStatus
																+ '\')">取消确认</a></li>'
															+'<li><a  href="#" mce_href="#" title="转结" onclick="verify(\''
																+ row.id+"','"+row.status
																+ '\')">转结</a></li>'
															+'<li><li><a  href="#" mce_href="#" title="取消转结" onclick="unverify(\''
																+ row.id+"','"+row.status
																+ '\')">取消转结</a></li>'
														+'</ul>'
													+'</div>'
												+'</div>';
											var g = '<a class="btn btn-success btn-sm"'
												+'style="margin-right:5px"'
												+ '" href="'+contextPath+'/exam/examCourse/?id='+row.id+'&examYear='+row.examYear+'&examMonth='+row.examMonth
												+ '" data-index="examRegOrg"'
												+ ' data-text="[' + row.examYear +'年'+ row.examMonth+']考试课程设置"'
												+ ' data-refresh="true"'
												+ ' onclick="parent.menuItem(this);return false;"'
												+ ' data-id="examRegOrg">考试课程</a>';
										return q + e +g+f+ d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '添加 [ 计划管理 > 考试任务管理 ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '645px', '305px' ],
		content : prefix + '/add' // iframe的url
	})
}
//确认
function edit(id,confirmStatus) {
	if(confirmStatus=="待确认"){
		layer.confirm('考试任务确认后院校及考区就可以报考开考课程。您确定要确认该考试任务吗？', {
            btn : [ '是', '否' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				url :contextPath+ "/exam/task/confirmStatus",
				type : "post",
				data : {
					'examTaskid' : id,
					'confirmStatus' :"b"
				},
				success : function(r) {
					console.log(r);
					if (r.code==0) { 
						layer.msg(r.msg);
						reLoad();
					}else{
						layer.msg(r.msg);
					}
				}
			});
       }); 
	}else{
		layer.msg("已经是确认状态");
	}
}
//取消确认
function edits(id,confirmStatus) {
	if(confirmStatus=="待确认"){
		layer.msg("已经是取消确认状态")
	}else{
		layer.confirm('您确定要取消确认该考试任务吗？', {
            btn : [ '是', '否' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				url :contextPath+ "/exam/task/confirmStatus",
				type : "post",
				data : {
					'examTaskid' : id,
					'confirmStatus' :"a"
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
       });
	}
}
//转结
function verify(id,status) {
	if(status=="待转结"){
		layer.confirm('您确定要转结该考试任务吗？', {
            btn : [ '是', '否' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				url :contextPath+ "/exam/task/status",
				type : "post",
				data : {
					'examTaskid' : id,
					'status' :"b"
				},
				success : function(r) {
					console.log(r);
					if (r.code==0) { 
						layer.msg(r.msg);
						reLoad();
					}else{
						layer.msg(r.msg);
					}
				}
			});
       }); 
	}else{
		layer.msg("已经是转结状态");
	}
}
//取消转结
function unverify(id,status) {
	if(status=="待转结"){
		layer.msg("已经是取消转结状态");
	}else{
		layer.confirm('您确定要取消转结该考试任务吗？', {
            btn : [ '是', '否' ]//按钮
        }, function(index) {
            layer.close(index);
			$.ajax({
				url :contextPath+ "/exam/task/status",
				type : "post",
				data : {
					'examTaskid' : id,
					'status' :"a"
				},
				success : function(r) {
					console.log(r);
					if (r.code==0) { 
						layer.msg(r.msg);
						reLoad();
					}else{
						layer.msg(r.msg);
					}
				}
			});
       }); 
	}
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
//考试任务设置
var ids;
var year;
var yue;
function resetPwd(id) {
	ids = id.split(";")[0];
	year = id.split(";")[1].split(",")[0];
	yue = id.split(";")[1].split(",")[1];
	//localStorage.setItem('examTaskid',ids);
	//localStorage.setItem('examTaskYear',year);
	layer.open({
		type : 2,
		title : year+" 年"+yue+"考试任务设置",
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :contextPath+ "/exam/examItem"// iframe的url
	}); 
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

function exportExcel(src){
    document.getElementById('form').action=contextPath+"/"+src;
//若需提交表单（如导出）
    document.getElementById('form').submit();
}

function  importData() {
    layer.open({
        type : 2,
        title : '批量导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData' // iframe的url
    });
}
