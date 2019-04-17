
var prefix =contextPath+  "/student/student"
$(function() {
	$.each($("#auditStatus").children(),function(index,value){
		if(value.value=='a'){
			$(value).attr("selected","selected");
		}
	})
	load();
	loadTree();
	$("#jstree").height($(document).height() - 120);
});
var hangId=null;
var auditStatus=null;
var status=null;
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
							if($('#pic').val()!=""){
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                    name:$('#searchName').val(),
                                    regYear:$("#regYear").val(),
                                    auditStatus:$("#auditStatus").val(),
                                    status:$("#status").val(),
                                    teachSiteid: $("#teachSiteid").val(),
                                    schoolid: $("#schoolid").val(),
                                    collegeid: $("#collegeid").val(),
                                    subjectName :$("#subject").val(),
                                    pic:"noPhoto",
                                };
							}else{
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                    name:$('#searchName').val(),
                                    regYear:$("#regYear").val(),
                                    auditStatus:$("#auditStatus").val(),
                                    status:$("#status").val(),
                                    teachSiteid: $("#teachSiteid").val(),
                                    schoolid: $("#schoolid").val(),
                                    collegeid: $("#collegeid").val(),
                                    subjectName :$("#subject").val(),
                                    type :$("#type").val()
                                    // username:$('#searchName').val()
                                };
							}

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
									/* formatter : function(value, row, index) {
										if(row.dbFlag==null){	
											var f = '<input data-index="0" name="btSelectItem" type="radio"onclick="check(\''
												+ row.id+"','"+row.auditStatus
												+ '\')">'
											return f;
										}else{
											var f = '<input data-index="0" name="btSelectItem" type="radio" style="display:none">'
											return f;
											
										}
									} */

								}, 
									{
									field : 'id', 
									title : '准考证号' 
								},
										{
									field : 'type', 
									title : '类别' 
								},
									{
									field : 'certificateNo', 
									title : '身份证号'
								},
										{
									field : 'name', 
									title : '姓名' 
								},/* 
									{
									field : 'nativePlace', 
									title : '籍贯' 
								},
								
										{
									field : 'ename', 
									title : '英文名' 
								},
										{
									field : 'pinyin', 
									title : '拼音' 
								}, */
										{
									field : 'gender', 
									title : '性别' 
								},
										/* {
									field : 'homeType', 
									title : '户籍' 
								},
										{
									field : 'politics', 
									title : '政治面貌' 
								},
									 */	{
									field : 'nation', 
									title : '民族' 
								},
									/* 	{
									field : 'profession', 
									title : '职业' 
								},
										{
									field : 'birthday', 
									title : '出生日期' 
								},
										{
									field : 'certificateType', 
									title : '证件类别' 
								}, */
									
									/* 	{
									field : 'phone', 
									title : '固定电话' 
								}, */
										{
									field : 'mphone', 
									title : '移动电话' 
								},/* 
										{
									field : 'address', 
									title : '通讯地址' 
								},
										{
									field : 'postCode', 
									title : '邮编' 
								},
										{
									field : 'email', 
									title : '电子邮箱' 
								},*/
									{
									field : 'specialityName', 
									title : '现报专业' 
								},
									{
									field : 'collegeName', 
									title : '学院' 
								},
									{
									field : 'teachName', 
									title : '办学点'
								},
									
									{
									field : 'schoolName', 
									title : '助学组织名称' 
								},
									/* 	{
									field : 'regionid', 
									title : '地州市考办编号' 
								},
										{
									field : 'childRegionid', 
									title : '县区考办编号' 
								}, */
									/* 	{
									field : 'schoolid', 
									title : '助学组织编号' 
								}, 
									{
									field : 'collegeid', 
									title : '学院编号' 
								}, 
										{
									field : 'teachSiteid', 
									title : '办学点编号'
								},
										{
									field : 'groupid', 
									title : '集体代码' 
								},
										{
									field : 'oldStudentid', 
									title : '旧准考证' 
								},
										{
									field : 'classify', 
									title : '报考类别' 
								}, */
										{
									field : 'status', 
									title : '状态' ,
									width : '60px',
								},
										{
									field : 'auditStatus', 
									title : '审核状态' ,
									width : '70px',
								},
										{
									field : 'confirmStatus', 
									title : '确认状态' ,
									width : '70px',
								},/* 
										{
									field : 'backOperator', 
									title : '退学操作员' 
								},
										{
									field : 'backDate', 
									title : '退学日期' 
								},
										{
									field : 'confirmOperator', 
									title : '确认操作员' 
								},
										{
									field : 'confirmDate', 
									title : '确认操作日期' 
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
									field : 'dbFlag', 
									title : '数据标记' 
								}, */
									 {
									title : '操作',
									field : 'id',
									align : 'center',
									width : '162px',
									 formatter : function(value, row, index) {
											/* var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> '; */
											if(row.dbFlag==null){
												var q = '<a class="btn btn-success btn-sm"'
												+'style="margin-right:5px"'
												+ '" href="'+contextPath+'student/studentSpeciality?studentid='+row.id+"&certificateNo="+row.certificateNo+"&name="+row.name+"&collegeName="+row.collegeName+"&teachName="+row.teachName+"&collegeid="+row.collegeid+"&teachSiteid="+row.teachSiteid
												+ '" data-index="examRegOrg"'
												+ ' data-text="专业管理"'
												+ ' data-refresh="true"'
												+ ' onclick="parent.menuItem(this);return false;"'
												+ ' data-id="examRegOrg">专业管理</a>';
												if(row.auditStatus=="待审核"){
													var e = '<a class="btn btn-primary btn-sm" style="width:70px" href="#" mce_href="#" title="审核" onclick="SH(\''
													+ row.id+"','"+row.auditStatus
													+ '\')">审核</a> ';
												}else{
													var e = '<a class="btn btn-warning btn-sm" href="#" mce_href="#" title="取消审核" onclick="QXSH(\''
													+ row.id+"','"+row.auditStatus
													+ '\')">取消审核</a> ';
												}
											 
												return q+e;
											}
											
											} 
											
								}  ],
								onCheck:function(row){
										hangId = row.id;
										auditStatus = row.auditStatus;
										status = row.status;
								}
								
					});
}
/* function check(id,status){
	hangId = id;
	auditStatus = status;
	//alert(auditStatus)
} */
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
/*
没有照片学生查询
 */
function LackLoad() {
    $("#pic").val("1");
    $('#exampleTable').bootstrapTable('refresh');
    $("#pic").val("");
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {
	layer.open({
		type : 2,
		title : '修改 [助学管理 > 考生信息管理]',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :prefix + '/edit/' + id  // iframe的url
	});
}
//修改
function edits() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择修改的学生");
		return;
	}
	layer.open({
		type : 2,
		title : '修改',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :prefix + '/edit/' + hangId  // iframe的url
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
//删除档案
function cancalRecord(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择删除档案的学生");
		return;
	}
	layer.confirm('你确定要删除该考生的报考信息吗？？', {
		btn : [ '确定', '取消' ]
		}, function() {
		layer.confirm('删除操作是不可逆的，你确定要继续吗？', {
			btn : [ '确定', '取消' ]
			}, function() {
				if(auditStatus=="已审核"){
					layer.msg("考生信息已经审核，不能删除");
				}else{
					$.ajax({
						url : prefix+"/remove",
						type : "post",
						data : {
							'id' : hangId
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
				}
			
		})
	})	
}
//取消审核
/* function shenghe(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	layer.open({
		type : 2,
		title : '审核状态',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :contextPath+"/student/student/showAudit/"+hangId  // iframe的url
	});
	
} */
//批量审核
function shenghe() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要批量审核的数据");
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
				"auditStatus":"b",
			},
			url : contextPath + '/student/student/batchUpdateAudit',
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
//取消批量审核
function cancalAudit() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消批量审核的数据");
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
				"auditStatus":"a",
			},
			url : contextPath + '/student/student/batchUpdateAudit',
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

//
//取消单个审核
function single (ids,auditStatus){
	$.ajax({
		type : 'POST',
		url : contextPath + '/student/student/updateAudit',
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
function QXSH(id,iauditStatus) {
		layer.confirm('您确定要取消审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
			single (id,"a");
		
       }); 		
}
function SH(id,iauditStatus) {
	layer.open({
		type : 2,
		title : '审核状态',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :contextPath+"student/student/showAudit/"+id +"?auditStatus="+iauditStatus // iframe的url
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
//批量退学
function dropOut(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要退学的数据");
		return;
	}
	layer.confirm("确认要退学选中的'" + rows.length + "'条数据吗?", {
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
				"status":"c"
			},
			url : prefix + '/batchUpdateTx',
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
//批量复学
function goBack(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要复学的数据");
		return;
	}
	layer.confirm("确认要复学选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/batchUpdateTx',
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
        url:contextPath+ '/common/tree/schoolTree',
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
                    return contextPath+'/common/tree/schoolTree';
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
		/* if(data.node.original.attributes.type=="school"){
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
		} */
        $('#orgid').val(data.selected[0]);
		// $('#collegeid').val(data.selected[0]);
		 $('#schoolid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        reLoad();
    }
}); 
//专业名称
function majorName(){
	  layer.open({
        type: 2,
        title: '专业名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content:contextPath+  "/plan/speciality/showSubject"
    });

}

function  importData() {
    layer.open({
        type : 2,
        title : '学生档案导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData' // iframe的url
    });
}
function  importData1() {
    layer.open({
        type : 2,
        title : '学生专业导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : contextPath+'/student/studentSpeciality/importData'   // iframe的url
    });
}
function exportExcel(src){
    document.getElementById('form').action=contextPath+"/"+src;

//若需提交表单（如导出）

    document.getElementById('form').submit();
}