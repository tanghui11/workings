
var prefix =contextPath+  "/region/studentFoul"
$(function() {
	ksrw();
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listStudent", // 服务器数据的加载地址
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
					            examTaskid:$('#examTaskid').val(),
					            studentid:$('#studentid').val()
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
									field : 'studentid', 
									title : '准考证号' 
								},
									{
									field : 'name', 
									title : '姓名' 
								},
									{
									field : 'certificateNo', 
									title : '身份证号' 
								},
									{
									field : 'courseid', 
									title : '课程代码' 
								},
									{
									field : 'courseName', 
									title : '课程名称' 
								},
									{
									field : 'examDate', 
									title : '考试日期' 
								},
									{
									field : 'segment', 
									title : '时段' 
								},
									{
									field : 'roomNo', 
									title : '考点信息' ,
									formatter: function (value, row, index) {
										var str = '考点:'+row.examSiteid+" "+ " "+row.examSiteName+" ,考场:"+row.roomNo+",座次:"+row.seq;
										return str;
									}
								},
									{
									field : 'foulCode', 
									title : '违规代码' 
								},
									{
									field : 'foulName', 
									title : '违规名称' 
								},
									{
									field : 'formula', 
									title : '处罚公式' 
								},
								{
									field : 'remark', 
									title : '备注' 
								},
										/* {
									field : 'studentCourseid', 
									title : '课程报考编号' 
								},
										{
									field : 'foulPunishid', 
									title : '违规编号' 
								},
										{
									field : 'remark', 
									title : '备注' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								}, */
										{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.studentCourseid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.studentCourseid
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ],
								onCheck:function(row){
									$("#studentCourseid").val(row.id);
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	if($("#name").val()==""){
		layer.msg("考试名称不能为空！");
	}else{
		var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
		if (rows.length == 0) {
			layer.msg("请选择要记录违纪的数据");
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url :contextPath+  "/region/studentFoul/save",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					reLoad();
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
function edit() {

   if($("#name").val()==""){
		layer.msg("考试名称不能为空！");
	}else{
		var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
		if (rows.length == 0) {
			layer.msg("请选择要修改违纪记录的数据");
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url :contextPath+  "/region/studentFoul/update",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					reLoad();
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'studentCourseid' : id
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
			ids[i] = row['studentCourseid'];
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
		url :contextPath+ "exam/task/serchTaskAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=''>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
			}
			$("#examTaskid").html(_html);
		}
	});
}
//违规项目
function weigui(){
		layer.open({
			type : 2,
			title : '违规项目',
			shadeClose : false, // 点击遮罩关闭层
			area : ['800px', '520px' ],
			content :  contextPath + 'region/studentFoul/studentFoul'  // iframe的url
		});
}