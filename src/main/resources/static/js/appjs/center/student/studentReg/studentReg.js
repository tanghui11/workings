 
var prefix =contextPath+  "/student/studentReg"
$(function() {
	$.each($("#status").children(),function(index,value){
		if(value.value=='a'){
			$(value).attr("selected","selected");
		}
	})
	load();
	loadTree();
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
								status : $('#status').val(),
								studentid:$("#studentid").val(),
								certificateNo:$("#certificateNo").val(),
					            name:$('#name').val(),
								regYear:$("#regYear").val(),
								teachSiteid: $("#teachSiteid").val(),
								schoolid: $("#schoolid").val(),
								collegeid: $("#collegeid").val(),
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
									title : '序号' ,
									formatter: function (value, row, index) {
										return index+1;
									} ,
									width : '50px',
								},
									{
									field : 'studentid', 
									title : '准考证号' ,
									width : '100px',
								},
									{
									field : 'name', 
									title : '姓名' ,
									width : '100px',
								},
								{
									field : 'certificateNo', 
									title : '证件号码' ,
									/* width : '120px', */
								},
									{
									field : 'gender', 
									title : '性别' ,
									width : '50px'
								},
									{
									field : 'homeType', 
									title : '户籍' ,
									width : '60px'
								},
									{
									field : 'politics', 
									title : '政治面貌' ,
									width : '60px'
								},
									{
									field : 'nation', 
									title : '名族' ,
									width : '60px'
								},
									{
									field : 'birthday', 
									title : '出生日期' ,
									width : '90px',
								},
								
									/* 	{
									field : 'studentSpecialityid', 
									title : '报考专业编号' 
								}, */
										 {
									field : 'regBeginDate', 
									title : '注册开始日期' ,
									width : '100px',
								},
										{
									field : 'regEndDate', 
									title : '注册结束日期' ,
									width : '100px',
								},/*
										{
									field : 'status', 
									title : '状态' 
								},
									/* 	{
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
									field : 'key', 
									title : '识别码' 
								}, */
									{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '80px',
									formatter : function(value, row, index) {
										/* var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> '; */
												if(row.status=="待审核"){
												var g = '<a class="btn btn-success btn-sm" href="#" title="审核"  mce_href="#"  onclick="resetPwd(\''
													+ row.id+"','"+row.status
													+ '\')">审核</a> ';
											}else{
												var g = '<a class="btn btn-warning btn-sm" href="#" title="取消审核"  mce_href="#"  onclick="resetPwd(\''
													+ row.id+"','"+row.status
													+ '\')">取消审核</a> ';
											}
										return g ;
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

//单个审核
function single (ids,auditStatus){
	$.ajax({
		type : 'POST',
		url : contextPath + '/student/studentReg/updateAudit',
		data : {
			"id" : ids,
			"auditStatus":auditStatus
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
}
function resetPwd(id,iauditStatus) {
	if(iauditStatus=="待审核"){
		layer.confirm('您确定要审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
			single (id,"b");
		
       }); 
		
	}else{
		layer.confirm('您确定要取消审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
			single (id,"a");
       }); 
		
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
			url : contextPath + '/student/studentReg/batchUpdateAudit',
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
				"auditStatus":"a"
			},
			url : contextPath + '/student/studentReg/batchUpdateAudit',
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


//树状图
function getTreeData() {
    $.ajax({
        type: "GET",
        url:contextPath+ '/common/tree/regionTree',
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
                   /*return contextPath+'/common/tree/schoolCollegeTree';

                    */
                    return contextPath+'/common/tree/schoolCollegeTeachSiteTree';
                },
                "data" : function(node){
                    if(node.id =="#"){
                        return {};

                    }else if(node.original.attributes.type=="college"){
                        return {"type":"college","schoolid":node.id.split(node.id.substr(0,1))[1]};
					} else{
                        return {"type":"school","schoolid":node.id.split(node.id.substr(0,1))[1]};
                    }
                }
            }
        }
    });
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
        reLoad();
    } else {
		//console.log(data.node.original.attributes.type)
		if(data.node.original.attributes.type=="school"){
			 $('#schoolid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			 $('#collegeid').val("");
			 $('#teachSiteid').val("");
		}else if(data.node.original.attributes.type=="college"){
			 $('#collegeid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			 $('#schoolid').val("");
			 $('#teachSiteid').val("");
		}else if(data.node.original.attributes.type=="teachSite"){
			 $('#teachSiteid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			 $('#collegeid').val("");
			  $('#schoolid').val("");
		}
        $('#orgid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
		// $('#schoolsid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        reLoad();
    }
});