
var prefix = contextPath+ "/student/studentBLXX"
$(function() {
	load();
});

function load() {
	table()
    $("#form_table").ajaxSubmit({
        url :  prefix + "/list",
        type : "post",
        dataType: "json",//预期服务器返回的数据类型
        data: $('#form_table').serialize(),
        success : function(data) {
            console.info(data)
			$('#exampleTable').bootstrapTable('load',data)
        }
    });

	function table(){
				$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/list", // 服务器数据的加载地址
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
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								//limit: params.limit,
								//offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						columns : [
								{
									checkbox : true
								},
									{
									field : 'address', 
									title : 'address' 
								},
										{
									field : 'answer', 
									title : 'answer' 
								},
										{
									field : 'audit_stat', 
									title : 'audit_stat' 
								},
										{
									field : 'back_date', 
									title : 'back_date' 
								},
										{
									field : 'back_opera', 
									title : 'back_opera' 
								},
										{
									field : 'birthday', 
									title : 'birthday' 
								},
										{
									field : 'certifica1', 
									title : 'certifica1' 
								},
										{
									field : 'certificat', 
									title : 'certificat' 
								},
										{
									field : 'child_regi', 
									title : 'child_regi' 
								},
										{
									field : 'classify', 
									title : 'classify' 
								},
										{
									field : 'collegeid', 
									title : 'collegeid' 
								},
										{
									field : 'confirm_da', 
									title : 'confirm_da' 
								},
										{
									field : 'confirm_op', 
									title : 'confirm_op' 
								},
										{
									field : 'confirm_st', 
									title : 'confirm_st' 
								},
										{
									field : 'create_dat', 
									title : 'create_dat' 
								},
										{
									field : 'db_flag', 
									title : 'db_flag' 
								},
										{
									field : 'email', 
									title : 'email' 
								},
										{
									field : 'enabled_fl', 
									title : 'enabled_fl' 
								},
										{
									field : 'ename', 
									title : 'ename' 
								},
										{
									field : 'gender', 
									title : 'gender' 
								},
										{
									field : 'groupid', 
									title : 'groupid' 
								},
										{
									field : 'home_type', 
									title : 'home_type' 
								},
										{
									field : 'id', 
									title : 'id' 
								},
										{
									field : 'idcard_pic', 
									title : 'idcard_pic' 
								},
										{
									field : 'key_value', 
									title : 'key_value' 
								},
										{
									field : 'kjh', 
									title : 'kjh' 
								},
										{
									field : 'mphone', 
									title : 'mphone' 
								},
										{
									field : 'name', 
									title : 'name' 
								},
										{
									field : 'nation', 
									title : 'nation' 
								},
										{
									field : 'native_pla', 
									title : 'native_pla' 
								},
										{
									field : 'old_studen', 
									title : 'old_studen' 
								},
										{
									field : 'operator', 
									title : 'operator' 
								},
										{
									field : 'password', 
									title : 'password' 
								},
										{
									field : 'phone', 
									title : 'phone' 
								},
										{
									field : 'pic', 
									title : 'pic' 
								},
										{
									field : 'pinyin', 
									title : 'pinyin' 
								},
										{
									field : 'politics', 
									title : 'politics' 
								},
										{
									field : 'post_code', 
									title : 'post_code' 
								},
										{
									field : 'profession', 
									title : 'profession' 
								},
										{
									field : 'question', 
									title : 'question' 
								},
										{
									field : 'regionid', 
									title : 'regionid' 
								},
										{
									field : 'schoolid', 
									title : 'schoolid' 
								},
										{
									field : 'status', 
									title : 'status' 
								},
										{
									field : 'teach_site', 
									title : 'teach_site' 
								},
										{
									field : 'type', 
									title : 'type' 
								},
										{
									field : 'update_dat', 
									title : 'update_dat' 
								},
										{
									field : 'work_addre', 
									title : 'work_addre' 
								}
										]
					});
		}

}
function reLoad() {
    load();
	//$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
			type: 2,
			title: '增加',
			maxmin: true,
			shadeClose: false, // 点击遮罩关闭层
			area: ['800px', '520px'],
			content: prefix + '/add' // iframe的url
		});
}
function edit(id) {
	 layer.open({
			type: 2,
			title: '查看',
			maxmin: true,
			shadeClose: false, // 点击遮罩关闭层
			area: ['800px', '520px'],
			content: prefix + '/edit/'+id// iframe的url
		});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/moveOut",
			type : "post",
			data : {
				'studentid' : id
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
//自动跳过没有照片的考生
function zdtg(){
	if($("#havePhoto").val()=="true"){
		$("#havePhoto").val("fasle");
	}else{
		$("#havePhoto").val("true");
	}
}