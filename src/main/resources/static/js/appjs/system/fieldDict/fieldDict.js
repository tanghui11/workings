
var prefix = contextPath+"/system/fieldDict"
$(function() {
	load();
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
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								appid: $('#appid').val(),
								tableName:$('#table_name').val(),
								fieldName:$('#field_name').val()
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
									field : 'appid', 
									title : '数据库名称' 
								},
								{
									field : 'tableName', 
									title : '表名' 
								},
								{
									field : 'fieldName', 
									title : '字典名' 
								},
								{
									field : 'fieldValue', 
									title : '字段值' 
								},
								{
									field : 'fieldMean', 
									title : '字段含义' 
								},
								{
									field : 'type', 
									title : '类别' 
								},
								{
									field : 'displayStatus', 
									title : '显示状态' 
								},
								{
									field : 'status', 
									title : '启用状态' 
								},
								{
									field : 'seq', 
									title : '序号' 
								},
								{
									field : 'remark', 
									title : '备注' 
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
									width : '120px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.appid + '\',\'' + row.tableName + '\',\'' + row.fieldName + '\',\'' + row.fieldValue
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.appid + '\',\'' + row.tableName + '\',\'' + row.fieldName + '\',\'' + row.fieldValue
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
    var appid = $('#appid').val();
    if(appid == ""){
        layer.msg("请选择应用！");
        return;
    }
    var table_name = $("#table_name").val();
    if(table_name == ""){
        layer.msg("请选择表名！");
        return;
    }
    var field_name = $("#field_name").val();
    if(field_name == ""){
        layer.msg("请选择字段名！");
        return;
    }
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/' + appid + '/' + table_name+ '/' + field_name // iframe的url
	});
}
function edit(appid,table_name,field_name,field_value) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + appid + '/' + table_name + '/' + field_name + '/' + field_value  // iframe的url
	});
}
function remove(appid,table_name,field_name,field_value) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'appid' : appid,
				'tableName' : table_name,
				'fieldName' : field_name,
				'fieldValue' : field_value,
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

//查找表名
function listAllTables(){
    var appid = $('#appid').val();
    if(appid == ""){
        layer.msg("请选择应用！");
        return;
    }
    $("#table_name").empty();
    $("#table_name").append("<option value=''>请选择表名</option>");
    $("#field_name").empty();
    $("#field_name").append("<option value=''>请选择字段名</option>");
    $.ajax({
        type: "post",
        url: prefix+"/listAllTables",
        data : {
            "appid" : appid
        },
        dataType : 'json',
        //beforeSend: validateData,
        cache : false,
        success : function(data) {
            $.each(data, function(i, list) {
                $("#table_name").append(
                        "<option value='" + list.id + "'>"
                                + list.name + "</option>");

            });
        }
    });
}
//查找字段
function listAllFields(){
    var appid = $('#appid').val();
    if(appid == ""){
        layer.msg("请选择应用！");
        return;
    }
    var table_name = $("#table_name").val();
    if(table_name == ""){
        layer.msg("请选择表名！");
        return;
    }
    $("#field_name").empty();
    $("#field_name").append("<option value=''>请选择字段名</option>");
    $.ajax({
        type: "post",
        url: prefix+"/listAllFields",
        data : {
            "appid" : appid,
            "table_name" : table_name
        },
        dataType : 'json',
        //beforeSend: validateData,
        cache : false,
        success : function(data) {
            $.each(data, function(i, list) {
                $("#field_name").append(
                        "<option value='" + list.id + "'>"
                                + list.name + "</option>");

            });
        }
    });
}