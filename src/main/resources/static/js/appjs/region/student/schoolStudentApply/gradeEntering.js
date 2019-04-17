var prefix = contextPath+"/school/specialityRegSchool"
$(function() {
	$("#studentid").val(parent.studentid1)
    load();
});
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
				url :contextPath+"student/studentSpeciality/listStudentSpeciality", // 服务器数据的加载地址
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
                singleSelect : true, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                //height: ($(document).height() - 140),
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
						studentid:$("#studentid").val()
                       /*  specialityid:$('#specialityid').val(),
                        auditStatus:'a' */
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
						 field : 'state',
						checkbox:true, 
						formatter : function (value, row, index) {
						        return {
						            disabled : false,//设置是否可用
						            checked : true//设置选中
						        }
						}

                    },
                    /* {
                        field : 'id',
                        title : '专业代码'
                    }, */
					{
									field : 'gradSchool',
									title : '毕业院校' ,
								},
								{
									field : 'grad_certificate',
									title : '毕业证书号' 
								},
								{
									field : 'gradSpecialityid',
									title : '原学专业代码' 
								},
								{
									field : 'gradSpecialityName',
									title : '原学专业名称' 
								},
								{
									field : 'education',
									title : '原学历' 
								},{
									field : 'specialityid',
									title : '专业代码' 
								},
								{
									field : 'specialityName',
									title : '报考专业' 
								},
								{
									field : 'auditStatus',
									title : '审核状态' 
								},
								{
									field : 'status',
									title : '状态' 
								},
								{
									field : 'graduate',
									title : '毕业申请状态' 
								},	
                    /* {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return  d ;
                        }
                    }*/ ]
            });
}
//添加
function tianjia(){
	/* var row=$.map($("#exampleTable").bootstrapTable('getSelections'),function(row){
		return row[0] ;
	});
	console.log(row) */
	layer.open({
        type : 2,
        title : "添加",
        maxmin : true,
        shadeClose : false,
        area : [ '80%', '80%' ],
        content :  contextPath + '/student/studentSpecialityApply/pageToAdd'
    });
}


//启用停用
function qtyong(str){
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择列表数据！");
        return;
    }
    var ids = new Array();
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        ids[i] = row['id'];
    });
    layer.confirm('确定要改变状态吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : contextPath+"/school/specialityRegSchool/batchAuditStatus",
            type : "post",
            data : {
                'ids':ids,
                'status':str
            },
            success : function(data) {
                parent.layer.msg("操作成功");
                reLoad();
                layer.close(layer.index)//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
            }
        });
    })
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : "添加 【 院校管理 > 专业开设申请 】",
        //maxmin : true,
        shadeClose : false,
        area : [ '470px', '315px' ],
        content : prefix + '/add'
    });
}
function edit(id) {
    layer.open({
        type : 2,
        title : "编辑",
        maxmin : true,
        shadeClose : false,
        area : [ '80%', '80%' ],
        content :  prefix + '/edit/' + id
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