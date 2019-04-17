
var prefix =contextPath+  "/student/practiceSchool"
$(function() {
	load();
	loadTree();
	//计算机构树div高度
	 $("#jstree").height($(document).height() - 120);
});
var studentids;
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
								status:$("#status").val(),
								time1:$("#time1").val(),
								time2:$("#time2").val(), 
								specialityid:$("#specialityid").val(),
								type:"a",
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
									title : '序号',
									formatter: function (value, row, index) {
										return index+1;
									}
									
								},
										{
									field : 'studentid', 
									title : '准考证号' 
								},
									{
									field : 'studentName', 
									title : '姓名' 
								},
									{
									field : 'specialityid', 
									title : '专业代码' 
								},
									{
									field : 'specialityName', 
									title : '专业名称' 
								},
										/* {
									field : 'specialityRecordid', 
									title : '专业开设编号' 
								}, */
										{
									field : 'courseid', 
									title : '课程代码' 
								},
								{
									field : 'courseName', 
									title : '课程名称' 
								},
										{
									field : 'grade', 
									title : '成绩' 
								},
										/* {
									field : 'inOperator', 
									title : '入库操作员' 
								},
										{
									field : 'inDate', 
									title : '入库日期' 
								},
										{
									field : 'status', 
									title : '入库状态' 
								}, */
										{
									field : 'confirmStatus', 
									title : '确认状态' 
								},
										/* {
									field : 'confirmOperator', 
									title : '确认操作员' 
								},
										{
									field : 'confirmDate', 
									title : '确认日期' 
								}, */
										{
									field : 'auditStatus', 
									title : '审核状态' 
								},
										/* {
									field : 'auditOperator', 
									title : '审核操作员' 
								},
										{
									field : 'auditDate', 
									title : '审核日期' 
								}, */
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
										/* {
									field : 'dbFlag', 
									title : '数据标记' 
								}, */
										{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="查看" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a style="display: none " class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ],onCheck:function(row){
                                    studentids = row.studentid;
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
	layer.open({
		type : 2,
		title : '查看',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '500px' ],
		content : prefix + '/edit/' + id  // iframe的url
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
//树状图
function getTreeData() {
    $.ajax({
        type: "GET",
        url:contextPath+ "/common/tree/specialityRecordTree",
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
                    return contextPath+'/common/tree/specialityRecordTree';
                },
                "data" : function(node){
                    if(node.id =="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        return {"schoolid" : node.id,"parentid":-1,"type":"school"};
                    }
                }
            }
        }
    });
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
    } else {
		if(data.node.original.attributes.type=="childSchool"){
			 $('#specialityid').val(data.selected[0]);
			 reLoad();	
		}else{
		}
        $('#orgid').val(data.selected[0]);
       
       // $('#orgType').val(data.node.original.attributes.type);
        	
    }
});
//导入
function importData(){
	layer.open({
		type : 2,
		title : '导入 ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '600px', '200px' ],
		content : prefix + '/importData' // iframe的url
	});
}
//入库
function storage(){
	if($("#time1").val()==""){
		layer.msg("导入日期不能为空");
			return ;
	}
	if($("#time2").val()==""){
			layer.msg("导入日期不能为空");
			return ;
		}
	layer.confirm('你确定要入库吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/SHScoreInDB",
			type : "post",
			data : {
				"yes":$("#yes").val(),
				"time1":$("#time1").val(),
				"time2":$("#time2").val(),
				"type":"a",
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
//取消入库
function cancelImport(){
	if($("#time1").val()==""){
		layer.msg("导入日期不能为空");
			return ;
	}
	if($("#time2").val()==""){
			layer.msg("导入日期不能为空");
			return ;
		}
	layer.confirm('你确定要取消入库吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/QXScoreInDB",
			type : "post",
			data : {
                "yes":$("#yes").val(),
				"time1":$("#time1").val(),
				"time2":$("#time2").val(),
				"type":"a",
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
//
function wrk(){
	if($("#yes").val()=="a"){
        $("#yes").val("b")
	}else{
        $("#yes").val("a")
	}
}

//手动录入检测和执行
function hangworkPerformance() {
    if (studentids == null || studentids==""){
        layer.msg("请先选择要手动录入考生信息");
        return;
    }
    if ($("#grade").val()== null || $("#grade").val()== ""){
        layer.msg("请填写要更新的成绩");
        return;
    }
    layer.confirm('你确定要手动录入成绩吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix+"/hangworkPerformance",
            type : "post",
            data : {
                "studentid":studentids,
                "grade":$("#grade").val(),
                "type":'a'
            },
            success : function(r) {
              if (r == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

//删除成绩
function removePerformance() {
    if (studentids == null || studentids==""){
        layer.msg("请先选择要删除成绩的考生信息");
        return;
    }

    layer.confirm('你确定要删除成绩吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix+"/deleteGrade",
            type : "post",
            data : {
                "studentid":studentids
            },
            success : function(r) {
                if (r == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}