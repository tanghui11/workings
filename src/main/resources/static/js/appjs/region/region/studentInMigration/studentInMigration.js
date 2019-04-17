
var prefix = contextPath + "/student/studentInRegion"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/migrationList", // 服务器数据的加载地址
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
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            name:$('#searchName').val(),
                                certificateNo:$('#searchCertificate').val()
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
									field : 'name', 
									title : '姓名' 
								},
										{
									field : 'gender', 
									title : '性别' 
								},
										{
									field : 'certificateNo', 
									title : '证件号码' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'outOperator', 
									title : '调档操作员' 
								},
										{
									field : 'outUpdateDate', 
									title : '调档日期' 
								},
										{
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
                                            + '\')"><i class="fa fa-remove"></i></a> ';

										var f = '<a class="btn btn-success btn-sm" href="#" title="考生成绩"  mce_href="#" onclick="studentScoreIn(\''
												+ row.id
												+ '\')">查看成绩</a> ';
										return e + d + f;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
//查询
function search() {
    if($("#searchName").val()==""){
        layer.msg("姓名不能为空！");
        return;
    }
    if($("#searchCertificate").val()==""){
        layer.msg("证件号不能为空！");
        return;
    }
	reLoad()
}


//调档
function migration() {
    if($("#searchstudentid").val()==""){
        layer.msg("准考证号不能为空！");
        return;
    }
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要调档的数据");
        return;
    }
    layer.confirm("确认要调档选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids ;
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            id= row['id'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "id" : id,
                "studentid":$('#searchstudentid').val(),
                certificateNo:$('#searchCertificate').val()
            },
            url : prefix + '/updateMigration',
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
    reLoad();
}


//弹窗显示考生成绩
function studentScoreIn(id) {
    layer.open({
        type: 2,
        title: ' 【 成绩信息 】 ',
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content: contextPath + 'student/studentScoreInRegion?studentInid='+id // iframe的url
    });

}

