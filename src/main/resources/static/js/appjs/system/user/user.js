var prefix = contextPath+"/system/user"
$().ready(function () {
    loadTree();
    load();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});

function load(deptid) {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
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
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        type: $('#type').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'workerName',
                        title: '职员姓名'
                    },
                    {
                        field: 'name',
                        title: '登录帐号'
                    },
                    {
                        field: 'workerMphone',
                        title: '手机号码'
                    },
                    {
                        field: 'workerPhone',
                        title: '联系电话'
                    },
                    {
                        field: 'workerEmail',
                        title: '邮箱'
                    },
                    {
                        field: 'type',
                        title: '用户类型',
                        width: '100px',
                        formatter: function (value, row, index) {
                            if(row.type==1){
                                return "中心端用户";
                            }if(row.type==2){
                                return "考区端用户";
                            }if(row.type==3){
                                return "助学组织用户";
                            }if(row.type==4){
                                return "院校用户";
                            }

                        }
                    },
                  /*  {
                        field: 'deptWorkerStatus',
                        title: '在职状态',
                        width: '100px'
                    },*/
                    {
                        field: 'status',
                        title: '帐号状态',
                        width: '100px'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        width: '125px',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="停用"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-pause"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function edit(userid) {
    layer.open({
        type: 2,
        title: '用户修改',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + userid // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要停用该帐号？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: contextPath+"/system/user/remove",
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
function add() {
    location.href =  prefix + '/add';
}

function resetPwd(id) {
    layer.confirm('确定要重置密码？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/resetPwd",
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

function loadTree() {
    $("#jstreeOrg").jstree({
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

$('#jstreeOrg').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
    } else {
        $('#orgid').val(data.selected[0]);
        $('#deptid').val("0");
        $('#orgType').val(data.node.original.attributes.type);
        $('#jstreeDept').jstree("destroy");
        loadDeptTree();
    }
});

function loadDeptTree() {
    $("#jstreeDept").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+'/system/dept/tree';
                },
                "data" : function(node){
                    if(node.id != "#"){
                        return {"orgid":$('#orgid').val(),"id":-1 , "parentid":node.id};
                    }else{
                        return {"orgid":$('#orgid').val(),"id":-1,"parentid":$('#deptid').val()};
                    }
                }
            }
        }
    });
    $('#jstreeDept').on("changed.jstree", function (e, data) {
        if (data.selected == -1) {
            $('#deptid').val('0');
            reLoad();
        } else {
            $('#deptid').val(data.selected[0]);
            reLoad();
        }
    });
}

