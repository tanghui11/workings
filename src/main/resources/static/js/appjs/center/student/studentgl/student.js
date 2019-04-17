var prefix = contextPath+"/student/studentRegionGl"
$(function () {
    getTreeData();
	$("#jstree").height($(document).height() - 120);
    load();
});
var hangId;
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    if($('#pic').val()!=""){
                        return{
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                            offset: params.offset,
                            //orgid: $('#orgid').val(),
                            examTaskid: $('#examTaskid').val(),
                            regionid : $("#regionid").val(),
                            childRegionid:$("#childRegionid").val(),
                            name:$('#searchName').val(),
                            regYear:$("#regYear").val(),
                            auditStatus:$("#auditStatus").val(),
                            status:$("#status").val(),
                            pic:"noPhoto"

                        };
                }else{
                        return {
                            //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                            limit: params.limit,
                            offset: params.offset,
                            //orgid: $('#orgid').val(),
                            examTaskid: $('#examTaskid').val(),
                            regionid: $("#regionid").val(),
                            childRegionid: $("#childRegionid").val(),
                            name: $('#searchName').val(),
                            regYear: $("#regYear").val(),
                            auditStatus: $("#auditStatus").val(),
                            status: $("#status").val(),
                        };
                    }

                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
					 {
                        field: 'id',
                        title: '准考证号'
                    },
					 {
                        field: 'certificateNo',
                        title: '身份证号码'
                    },
					{
                        field: 'name',
                        title: '姓名'
                    },
					 {
                        field: 'gender',
                        title: '性别'
                    },
					 {
                        field: 'nation',
                        title: '民族'
                    },
					{
						field : 'mphone', 
						title : '移动电话' 
					},
					{
						field : 'specialityName', 
						title : '专业名称' 
					},
					{
						field : 'specialityid', 
						title : '专业代码' 
					},
					{
                        field: 'status',
                        title: '状态'
                    },
					 {
                        field: 'auditStatus',
                        title: '审核状态'
                    },
					{
                        field: 'confirmStatus',
                        title: '确认状态'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                           /*  var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> '; */
                            return e ;
                        }
                    }],
					onCheck:function(row){
						hangId = row.id
					}
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function LockLoad() {
    $("#pic").val("1");
    $('#exampleTable').bootstrapTable('refresh');
    $("#pic").val("");
}

function add() {
   /*  var url = prefix + '/add';
    if ($('#orgid').val() != '') {
        url += '?orgid=' + $('#orgid').val();
    } */
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function importStudent() {
    layer.open({
        type: 2,
        title: '导入',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/importStudent' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
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

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

/* function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+"/system/org/tree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        var opt = {
            query: {
                orgid: '',
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    } else {
        var opt = {
            query: {
                orgid: data.selected[0],
            }
        }
        $('#orgid').val(data.selected[0])
        $('#exampleTable').bootstrapTable('refresh', opt);
    }
}); */
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
   $("#jstree").on("loaded.jstree", function (event, data) {
     // 展开所有节点
     //$('#jstree').jstree('open_all');
     // 展开指定节点
     data.instance.open_node(0);     // 单个节点 （0 是顶层的id）
      //data.instance.open_node([1, 10]); // 多个节点 (展开多个几点只有在一次性装载后所有节点后才可行）
  }).jstree({
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
		if(data.node.original.attributes.type=="a"){
			 $('#regionid').val(data.selected[0]);
			 $('#childRegionid').val("");
		}else if(data.node.original.attributes.type=="b"){
			 $('#regionid').val("");
			 $('#childRegionid').val(data.selected[0]);
		}
        $('#orgid').val(data.selected[0]);
        //$('#orgType').val(data.node.original.attributes.type);
        reLoad();
    }
});

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
        title : '报考专业导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix+'/importData1'   // iframe的url
    });
}
function exportExcel(src){
    document.getElementById('form').action=contextPath+"/"+src;

//若需提交表单（如导出）

    document.getElementById('form').submit();
}