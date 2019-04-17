var prefix = contextPath+"/system/org"
$().ready(function() {
    loadTree();
	load();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});

function load() {
	$('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
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
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset:params.offset,
                        parentid: $('#parentid').val(),
                        searchKey: $("#searchName").val()
                    };
                },
                columns : [
                        {
                            title : '编号',
                            field : 'id',
                            align : 'center',
                            valign : 'middle'
                        },
                        {
                            field : 'type',
                            title : '类别',
                            width : '120px'
                        },
                        {
                            field : 'code',
                            title : '代码',
                            width : '120px'
                        },
                        {
                            field : 'name',
                            title : '名称'
                        },
                        {
                            field : 'schoolType',
                            title : '学校类别',
                            width : '120px'
                        },
                        {
                            field : 'phone',
                            title : '联系电话'
                        },
                        {
                            field : 'fax',
                            title : '传真'
                        },
                        {
                            field : 'email',
                            title : '电子邮件'
                        },
                        {
                            field : 'operator',
                            title : '操作员',
                            width : '100px'
                        },
                        {
                            field : 'updateDate',
                            title : '操作日期',
                            width : '100px'
                        },
                        {
                            title : '操作',
                            field : 'id',
                            align : 'center',
                            width : '140px',
                            formatter : function(value, row, index) {
                                var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                        + row.id
                                        + '\')"><i class="fa fa-edit"></i></a> ';
                                var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                        + row.id
                                        + '\')"><i class="fa fa-remove"></i></a> ';
                                return e + d ;
                            }
                        } ]
            });
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
    if($('#orgType').val() == 'd'){
        layer.alert('学校中不能增加机构信息！');
        return;
    }
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/' + $('#parentid').val() // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
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

function loadTree() {
    $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+'/system/org/tree';
                },
                "data" : function(node){
                    if(node.id !="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        return {"id" : $('#parentid').val(),"parentid":-1};
                    }
                }
            }
        }
    });
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#parentid').val('0');
        $('#exampleTable').bootstrapTable('refresh');
    } else {
        $('#parentid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        $('#exampleTable').bootstrapTable('refresh');
    }
});