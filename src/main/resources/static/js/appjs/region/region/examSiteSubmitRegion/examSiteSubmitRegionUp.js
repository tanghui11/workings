
var prefix =contextPath+  "/region/examSiteSubmitRegion"
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
                //url : prefix + "/list/",
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
                        regionid:parent.$('#regionid').val(),
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
                        checkbox : true
                    },
                    {
                        field : 'id',
                        title : '序号',
                        formatter: function (value, row, index) {
                            return index+1;
                        },
						width : '60px'
                    },
                    /*{
						field : 'regionid',
						title : '考区编号'
					},*/
                    {
                        field : 'code',
                        title : '考点代码'
                    },
                    {
                        field : 'name',
                        title : '名称'
                    },
                    {
                        field : 'pinyin',
                            title : '拼音'
                    },
                    {
                        field : 'num',
                        title : '考场数量'
                    },
                    {
                        field : 'linkman',
                        title : '联系人'
                    },
                    {
                        field : 'phone',
                        title : '联系电话'
                    },
                    {
                        field : 'address',
                        title : '地址'
                    },
					{
                        field : 'remark',
                        title : '备注'
                    },
                    /*{
                    field : 'fax',
                        title : '传真'
                    },

                    {
                        field : 'postCode',
                            title : '邮政编码'
                    },
                    {
                        field : 'school',
                            title : '是否助学组织'
                    },
                    {
                        field : 'schoolCode',
                            title : '办学许可证编号'
                    },
                    {
                        field : 'teachCode',
                            title : '助学许可证编号'
                    },
                    {
                        field : 'leader',
                            title : '考点主考姓名'
                    },
                    {
                        field : 'standard',
                            title : '是否标准化考场'
                    },*/
                    {
                        field : 'status',
                        title : '状态'
                    },
                    {
                        field : 'operator',
                        title : '操作员',
						width : '80px'
                    },
                    {
                        field : 'updateDate',
                        title : '操作日期',
						width : '90px'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="添加" onclick="edit(\''
                                + row.id
                                + '\')">上报</a> ';

                            return e ;
                        }
                    } ]
            });
    
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
    parent.$('#exampleTable2').bootstrapTable('refresh');
}
function add() {
    var regionid =  $('#regionid').val();



}
function edit(id) {
    layer.open({
        type : 2,
        title : '添加 【 地州市考办管理 > 考点上报 > 选择考试任务 】',
        //maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '470px', '315px' ],
        content :  prefix + '/edit/' + id // iframe的url
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


function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+"/common/tree/regionRegionTree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree() {
    $("#jstree").jstree({
        //树状图形状
        /* "state" : { "key" : "jstree" },
        "plugins" : ["state"], */
        'core': {
            "data" : {
                "state" : {"opened" : true },
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+ '/common/tree/regionRegionTree';
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
    /* 	$("#jstree").jstree({
          // the key is important if you have multiple trees in the same domain

        }); */
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
        reLoad();
    } else {
        //alert(data.selected[0]);
        $(".ttt").html(" > "+data.node.text);
        $('#orgid').val(data.selected[0]);
        $('#regionid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        reLoad();
    }
});
/* if($("#regionid").val()!=""||$("#regionid").val()=0){
	 reLoad();
} */