
var prefix =contextPath+  "/school/teachSite"
$(function() {
	load();
	loadTree();
	//计算机构树div高度
    $("#jstree").height($(document).height() - 120); 
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
								collegeid:$("#collegeid").val(),
								schoolSiteid:$("#schoolSiteid").val(),
					            name:$('#searchName').val(),
								schoolid:$("#schoolid").val()
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
									formatter: function (value, row, index) {
										return index+1;
									},
									width : '60px',
								},
									/* 	{
									field : 'schoolSiteid',
									title : '办学点编号'
								},
										{
									field : 'collegeid',
									title : '学院编号'
								} , */
										{
									field : 'name', 
									title : '单位名称' 
								},
									{
									field : 'schoolSiteName', 
									title : '办学地区' 
								},
										{
									field : 'pinyin', 
									title : '拼音' 
								},
										{
									field : 'linkman', 
									title : '联系人' ,
									width : '100px',
								},/* 
										{
									field : 'phone', 
									title : '固定电话' 
								}, */
										{
									field : 'mphone', 
									title : '移动电话' ,
									width : '100px',
								},/*
										{
									field : 'email', 
									title : '邮箱' 
								}, 
										{
									field : 'postCode', 
									title : '邮编' 
								},
										{
									field : 'address', 
									title : '联系地址' 
								}, */
										{
									field : 'status', 
									title : '状态' ,
									width : '80px',
								},/* 
										{
									field : 'operator', 
									title : '操作员' 
								}, */
										{
									field : 'updateDate', 
									title : '申请日期' ,
									width : '90px',
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '100px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										if(row.status=="待审核"){
											var f = '<a class="btn btn-success btn-sm" style="width:70px" href="#" title=""  mce_href="#" onclick="resetPwd(\''
												+ row.id+";"+row.status
												+ '\')">审核</a> ';
										}else{
											var f = '<a class="btn btn-warning btn-sm" href="#" title=""  mce_href="#" onclick="resetPwd(\''
												+ row.id+";"+row.status
												+ '\')">取消审核</a> ';
										}
												
										
										return f ;
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
	var ids =id.split(";")[0];
	var zt = id.split(";")[1];
	if(zt=="待审核"){
		layer.confirm('您确定要审核吗？', {
		btn : [ '确定', '取消' ]
		}, function() {
		$.ajax({
			type : 'POST',
			url : contextPath + '/school/teachSite/updateAudit',
			data : {
				"id" : ids,
				"status":"b"
			},
			
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
		
	}else{
		layer.confirm('您确定要取消审核吗？', {
		btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				type : 'POST',
				url : contextPath + '/school/teachSite/updateAudit',
				data : {
					"id" : ids,
					"status":"a"
					
				},
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

function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+"/common/tree/schoolCollegeTree",
        success: function (tree) {
			loadTree(tree);
        }
    });
}
function loadTree() {
    $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){

                    return  contextPath+'/common/tree/schoolCollegeTree' 
                },
                "data" : function(node){

                    if(node.id =="#"){
                        return {};

                    }else{
                        return {"type":"school","schoolid":node.id.split(node.id.substr(0,1))[1]};
                    }
                }
            }
        }
    }); 
	/* $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+ '/common/tree/schoolCollegeTree';
                },
                "data" : function(node){
                    if(node.id !="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        //return {"id" : $('#orgid').val(),"parentid":-1};
                    }
                }
            }
        }
    }); */
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
        reLoad();
    } else {
		if(data.node.original.attributes.type=="school"){
			$("#collegeid").val("");
			$("#schoolid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			 reLoad();
		}else{
			$("#schoolid").val("");
			$("#collegeid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			 reLoad();
		}
		/* $("#collegeid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
		$("#schoolSiteid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
		$('#orgid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
        $('#orgType').val(data.node.original.attributes.type);
        reLoad(); */
    }
});


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
				"status":"b"
			},
			url : contextPath + '/school/teachSite/batchUpdateAudit',
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
				"status":"a"
			},
			url : contextPath + '/school/teachSite/batchUpdateAudit',
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
//导出到Excel
function exportExcel(src){
    if ($("#collegeid").val() == ""){
        layer.msg("请选择左侧助学组织；")
	}else {
        onZipAll();
	}
}

function  importData() {
    layer.open({
        type : 2,
        title : '批量导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData?collegeId='+$("#collegeid").val() +'&schoolSiteId='+$("#schoolSiteid").val()// iframe的url
    });

}
