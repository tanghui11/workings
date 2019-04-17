
var prefix = contextPath+"/student/fileInfoQuery"
$(function() {
	
	load();
	//loadTree();
    //计算机构树div高度
	bianhua();
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
						height: ($(document).height() - 140),
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            courseid:$('#searchName').val(),
								schoolid:$("#schoolid").val(),
								collegeid:$("#collegeid").val(),
								teachSiteid:$("#teachSiteid").val(),
								regionid:$("#regionid").val(),
								childRegionid:$("#childRegionid").val(),
                                startDate:$("#time1").val(),
                                endDate:$("#time2").val(),
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
									field : 'studentid',
									title : '准考证号' ,
									width : '90px'
									},
											{
									field : 'name',
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

											{
									field : 'schoolName',
									title : '主考院校'
									},
											{
									field : 'rdExaDate',
									title : '报考时间' ,
									width : '80px'
									},
											{
									field : 'auditStatus',
									title : '毕业审核状态' ,
									width : '80px'
									},
											{
									field : 'gradAuditOperator',
									title : '审批人' ,
									width : '80px'
									},
											{
									field : 'auditDate',
									title : '审批日期' ,
									width : '80px'
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '80px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="毕业证书信息" onclick="edit(\''
												+ row.studentid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.type+"','"+ row.courseid
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.type
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e ;
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
		title : '添加 [ 计划管理 > 课程分类 ] ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '700px', '400px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	 layer.open({
		type : 2,
		title : '毕业证书信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content : prefix + '/listDiplomahtml'// iframe的url
	});
}
function remove(id,courseid) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'type' : id,
                'courseid' : courseid
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
function  save() {
    layer.open({
        type : 2,
        title : '批量导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/save' // iframe的url
    });
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

//树状图
function getTreeData() {
    $.ajax({
        type: "GET",
        url:contextPath+ "/common/tree/regionTree",
        success: function (tree) {
			loadTree(tree);
        }
    });
}
function bianhua(){
	if($("#type").val()=="a"){
		$('#jstree').data('jstree', false).empty();
		$(".ibox-title>h5").html("考区列表");
		loadTree(contextPath+ '/common/tree/regionTree');
	}else if($("#type").val()=="b"){
		$('#jstree').data('jstree', false).empty();
		$(".ibox-title>h5").html("助学组织列表")
		loadTree1(contextPath+'/common/tree/schoolCollegeTeachSiteTree')
	}
}
 function loadTree(url) {
     $("#jstree").on("loaded.jstree", function (event, data) {
                // 展开所有节点
                //$('#jstree').jstree('open_all');
                // 展开指定节点
                data.instance.open_node(0);     // 单个节点 （1 是顶层的id）
               // data.instance.open_node([0, 10]); // 多个节点 (展开多个几点只有在一次性装载后所有节点后才可行）
            }).jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return url;
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
function loadTree1(url) {
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
                    return url;
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
		if($("#type").val()=="a"){
			$('#orgid').val(data.selected[0]);
			if(data.node.original.attributes.type=="a"){
				$("#regionid").val(data.selected[0]);
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="b"){
				$("#regionid").val("");
				$("#childRegionid").val(data.selected[0]);
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}
			//$('#orgType').val(data.node.original.attributes.type);
			reLoad();
		}else if($("#type").val()=="b"){
			$('#orgid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			if(data.node.original.attributes.type=="school"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="college"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="teachSite"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			}
			 //$('#orgType').val(data.node.original.attributes.type);
			 reLoad();
		}
		
   
	}
});
function courseName(){
	  layer.open({
        type: 2,
        title: '专业名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content:contextPath+  "/plan/speciality/showSubject"
    }); 
}