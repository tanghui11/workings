
var prefix =contextPath+  "/school/school"
$(function() {
	/* loadTree(); */
    load();
    //计算机构树div高度
    /* $("#jstree").height($(document).height() - 120);
	if($('#schoolsid').val()!=0||$('#schoolsid').val()!=""){
		reLoad();
	} */
});
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list/", // 服务器数据的加载地址
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
								name:$('#searchName').val(),
					             /* regionid:$('#searchRegionid').val(),
								legalPerson:$('#legalPerson').val() */
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
									title : '序号	',
									formatter: function (value, row, index) {
										return index+1;
									},
									width :'60px',

								},
										{
									field : 'code', 
									title : '代码' ,
									width :'80px',
								},
									/* 	{
									field : 'regionid', 
									title : '考区编号' 
								}, */
										{
									field : 'name', 
									title : '助学组织名称' 
								}/* ,
										{
									field : 'pinyin', 
									title : '拼音' 
								} ,
										{
									field : 'bcode',
									title : '办学许可证编号' 
								},
										{
									field : 'bSendUnit', 
									title : '办学许可证发放单位' 
								},
										{
									field : 'bSendDate', 
									title : '办学许可证发放日期' 
								} ,
										{
									field : 'zcode',
									title : '助学许可证编号'
								} ,
										{
									field : 'zSendUnit',
									title : '助学许可证发放单位'
								},
										{
									field : 'zSendDate',
									title : '助学许可证发放日期'
								} */,
										{
									field : 'type', 
									title : '助学主体类型' ,
									width :'90px',
								},
										{
									field : 'legalPerson', 
									title : '法人名称' ,
									width :'90px',
								},
									/*	{
									field : 'legalPersonDuty', 
									title : '法人职务' 
								}
									, 	{
									field : 'jTeacherNum', 
									title : '兼职教学人员' 
								},
										{
									field : 'zTeacherNum', 
									title : '专职教学人员' 
								},
										{
									field : 'managerNum', 
									title : '管理人员总数' 
								},
										{
									field : 'allNum', 
									title : '教学和管理人员数' 
								} ,
										{
									field : 'charger', 
									title : '负责人' 
								}*/
									/* ,	{
									field : 'address', 
									title : '联系地址' 
								},
										{
									field : 'postCode', 
									title : '邮政编码' 
								},
										{
									field : 'phone', 
									title : '固定电话' 
								}, */
										{
									field : 'mphone', 
									title : '移动电话' ,
									width :'120px',
								},
									/* 	,{
									field : 'fax', 
									title : '传真' 
								},
										{
									field : 'email', 
									title : '邮件' 
								} ,*/
										/* {
									field : 'status', 
									title : '状态' ,
									width :'90px',
								}  */],
									onCheck:function(row){
										parent.layer.closeAll('iframe');
										parent.$("#schoolid").val(row.id);
										parent.$("#schoolName").val(row.name);
									}
					});
}
function reLoad() {
	/* $('#exampleTable').bootstrapTable('refresh',{url : prefix + '/list/' + $('#schoolsid').val()}); */
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	 //sessionStorage.setItem("zhuxueId",$('#orgid').val());
	if($("#schoolsid").val()==''||$("#schoolsid").val()==0){
		layer.msg("请选择考区")
	}else{
		layer.open({
			type : 2,
			title : '添加 [ 助学组织 >  助学组织管理 ]',
			shadeClose : false, // 点击遮罩关闭层
			area : [ '80%', '80%' ],
			content : prefix + '/add' // iframe的url
		});
		//location.href = prefix + '/add';
	}
	
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑 [ 助学组织 >  助学组织管理 ] ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content : prefix + '/edit/' + id // iframe的url
	}); 
	//location.href = prefix + '/edit/' + id;
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
	layer.open({
		type : 2,
		title : '学院管理',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '90%' ],
		content :contextPath+ "/school/college/" +id// iframe的url
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
                    return contextPath+ '/common/tree/regionTree';
                },
                "data" : function(node){
                    if(node.id !="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        return {"id" : $('#orgid').val(),"parentid":-1};
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
        $('#orgid').val(data.selected[0]);
		 $('#schoolsid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        reLoad();
    }
});