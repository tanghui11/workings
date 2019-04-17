var prefix = contextPath+"/system/dept"
$().ready(function () {
    loadTree();
    load();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});

function load() {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'id',
                code: 'id',
                parentCode: 'parentid',
                type: "GET", // 请求数据的ajax类型
                url: prefix + '/list/' + $('#orgid').val(), // 请求数据的ajax的url
                ajaxParams: {}, // 请求数据的ajax的data属性
                expandColumn: '1',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: true, // 是否全部展开
                // toolbar : '#exampleToolbar',
                 columns: [
                    {
                        field: 'id',
                        title: '编号',
                        align: 'left'
                    },
                    {
                        field: 'name',
                        title: '名称',
                        align: 'left'
                    },
                    {
                        field: 'linkman',
                        title: '联系人',
                        align: 'left'
                    },
                    {
                        field: 'phone',
                        title: '联系电话',
                        align: 'left'
                    },
                    {
                        field: 'address',
                        title: '联系地址',
                        align: 'left'
                    },
                    {
                        field: 'postCode',
                        title: '邮编',
                        align: 'left'
                    },
                    {
                        field: 'email',
                        title: '电子邮箱',
                        align: 'left'
                    },
                    {
                        field: 'operator',
                        title: '操作员',
                        width: '100px'
                    },
                    {
                        field: 'updateDate',
                        title: '操作日期',
                        width: '100px'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        width: '90px',
                        formatter: function (row, index) {
                            if (row.parentid != '0') {
                                var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                    + row.id
                                    + '\')"><i class="fa fa-edit"></i></a> ';
                                var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                    + row.id
                                    + '\')"><i class="fa fa-remove"></i></a> ';
                                return e + d;
                            }
                        }
                    }]
            });
}

function reLoad() {
    load();
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + $('#orgid').val()   // iframe的url
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

function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+"/system/org/tree",
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
                    return contextPath+'/system/org/tree';
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
        load();
    } else {
        $('#orgid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        load();
    }
});