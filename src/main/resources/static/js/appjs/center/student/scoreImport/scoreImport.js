
var prefix =contextPath+  "/student/scoreImport"
$(function() {
	ksrw();
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
								offset:params.offset
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
									field : 'kemuid', 
									title : '课程代码' ,
									width : '100px'
								},
										{
									field : 'code', 
									title : '准考证号' 
								},
										{
									field : 'name', 
									title : '姓名' 
								},
										{
									field : 'totalscore', 
									title : '总分' 
								},
										{
									field : 'zgscore', 
									title : '主观总分' 
								},
										{
									field : 'omrscore', 
									title : '客观总分' 
								},
										{
									field : 'qk', 
									title : '缺考' 
								},
										{
									field : 'wj', 
									title : '违纪' 
								},
								{
									field : 'enabledFlag', 
									title : '状态' ,
									 formatter: function (value, row, index) {
									if(row.enabledFlag=="0"){
										return "已入库"
									}else{
										return "待入库"
									}
									} 
								},
										{
									field : 'operator', 
									title : '操作员' ,
									width : '90px',
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width : '100px',
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '120px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.kemuid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.kemuid
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.kemuid
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {
	layer.open({
        type: 2,
        title: '编辑',
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '365px'],
        content:prefix + '/edit/' + id 
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
				'kemuid' : id
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
			ids[i] = row['kemuid'];
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
//考试任务
function ksrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "/exam/task/serchTaskAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=''>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				if(data[i].id==parent.kaoshi){
					_html+="<option value="+"'"+data[i].id+"'"+" selected = 'selected'>"+data[i].examYear+"年"+data[i].examMonth+"</option>";
				}else{
					_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
				}
			}
			$("#ksrw").html(_html);
			//$("#ksrw").trigger("change");
		}
	});
}
//导出临时表
function channel(){
	layer.open({
        type: 2,
        title: '课程名称',
        shadeClose: false, // 点击遮罩关闭层
        area: ['600px', '200px'],
        content:prefix+  "/leadingInPage"
    }); 
}
//入库
function ruku(){
	if($("#ksrw").val()==""){
		layer.msg("请选择考试任务后入库");
	}else{
		$.ajax({
			type : 'POST',
			data : {
				"examTaskid":$("#ksrw").val(),
			},
			url : prefix + '/scoreInDB',
			success : function(r) {
					console.log(r)
				if (r.code == 0) {
					layer.msg(r.msg);
				} else {
					layer.msg(r.msg);
					reLoad();
				}
			}
		});
	}
}
//无差别入库
function wcbrk(){
	if($("#ksrw").val()==""){
		layer.msg("请选择考试任务后入库");
	}else{
		$.ajax({
			type : 'POST',
			data : {
				"examTaskid":$("#ksrw").val(),
			},
			url : prefix + '/scoreInDBT',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
				} else {
					layer.msg(r.msg);
					reLoad();
				} 
			}
		});
	}
}
//删除
function delede(){
	layer.confirm('是否全部删除已入库数据？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/delete",
			type : "delete",
			success : function(r) {
				if (r.code == 1) {
					layer.msg("删除成功！");
					reLoad();
				} else {
					layer.msg("删除失败或者没有符合删除条件的数据！");
				}
			}
		});
	})
}
