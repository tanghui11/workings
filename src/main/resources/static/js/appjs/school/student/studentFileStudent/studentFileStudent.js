var prefix = contextPath+"/student/studentFileStudent"
$(function() {
    college();
	load();

});
//学院名称数据
function college(){
	$.ajax({
		url : contextPath+"/school/collegeSchool/listCollege",
		type : "get",
		 async:false,//更改为同步
		success : function(data) {
			for (var i =0; i < data.length; i++) {
				$("#collegeid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
			firstV= $("#collegeid option:first").val();
			teachPoint(firstV);
		}
	});
	teachPoint('')
}
$("#collegeid").on("change",function(){
	var val = $("#collegeid option:selected").val()
	teachPoint(val)
	$("#schoolSpecialityRegid").val("")
	$("#schoolSpecialityRegid2").val("")
})
//教学点数据
function teachPoint(collegeid){
	if(collegeid==''){
		$("#teachSiteid").find("option").remove();//首先清空下拉数据
		$("#teachSiteid").append("<option value=''>请选择教学点</option>");
		return;
	}else{
		$.ajax({
			url : contextPath+"/school/teachSite/listTeachSite",
			type : "get",
			data:{
				collegeid:collegeid
			},
			success : function(data) {
				$("#teachSiteid").find("option").remove();//首先清空下拉数据
				$("#teachSiteid").append("<option value=''>请选择教学点</option>");
				for (var i =0; i < data.length; i++) {
					$("#teachSiteid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				}
			}
		});
	}
}
$("#teachSiteid").on("change",function(){
	$("#schoolSpecialityRegid").val("")
	$("#schoolSpecialityRegid2").val("")
})
function yemian(){
	if($("#teachSiteid").val()==''||$("#collegeid").val()==''){
		layer.msg("请先选择学院名称和教学点！");
		return;
	}else{
		layer.open({
			type : 2,
			title : "报考专业",
			maxmin : true,
			shadeClose : false, 
			area : [ '90%', '90%' ],
			content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
		});
	}
	
} 
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
						height: ($(document).height() - 210),
						queryParams : function(params) {
							if($("#pic").val() != ""){
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                    collegeid:$('#collegeid').val(),
                                    teachSiteid:$('#teachSiteid').val(),
                                   /* specialityid:$('#schoolSpecialityRegid2').val(),*/
                                    specialityRecordid:$('#specialityZYid').val(),
                                    classify:"b",
                                    status:$('#status').val(),
                                    auditStatus:$('#auditStatus').val(),
                                    confirmStatus:$('#confirmStatus').val(),
									pic:"noPhoto"
                                    // username:$('#searchName').val()
                                };
							}else{
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                    collegeid:$('#collegeid').val(),
                                    teachSiteid:$('#teachSiteid').val(),
                                    /*specialityid:$('#schoolSpecialityRegid2').val(),*/
                                    specialityRecordid:$('#specialityZYid').val(),
                                    classify:"b",
                                    status:$('#status').val(),
                                    auditStatus:$('#auditStatus').val(),
                                    confirmStatus:$('#confirmStatus').val(),
                                    // username:$('#searchName').val()
                                };
							}

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
									title : '准考证号' 
								},
										{
									field : 'certificateNo', 
									title : '身份证号' 
								},
										{
									field : 'name', 
									title : '姓名' 
								},
									/*	{
									field : 'pinyin', 
									title : '拼音' 
								},
										{
									field : 'gender', 
									title : '性别' 
								},*/
										/*{
									field : 'homeType', 
									title : '户籍' 
								},
										{
									field : 'politics', 
									title : '政治面貌' 
								},
										{
									field : 'nation', 
									title : '民族' 
								},*/
										/*{
									field : 'profession', 
									title : '职业' 
								},
										{
									field : 'birthday', 
									title : '出生日期' 
								},*/
										{
									field : 'nativePlace', 
									title : '籍贯' 
								},
									/*	{
									field : 'certificateType', 
									title : '证件类别' 
								},*/
									
									/*	{
									field : 'phone', 
									title : '固定电话' 
								},*/
										{
									field : 'mphone', 
									title : '移动电话' 
								},
										{
									field : 'collegeName', 
									title : '学院' 
								},
										{
									field : 'teachName', 
									title : '教学点' 
								},
										{
									field : 'specialityName', 
									title : '现报专业' 
								},
										{
									field : 'createDate', 
									title : '注册时间' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'regCount', 
									title : '注册次数' 
								},
										
									{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.studentSpecialityid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.studentSpecialityid
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="注册"  mce_href="#" onclick="resetPwd(\''
												+ row.studentSpecialityid
												+ '\')">考生注册</a> ';
										if(row.status=="退学"){
                                            f = '<a class="btn btn-success btn-sm disabled " href="#" title="注册"　disabled="disabled" 　 mce_href="#" >考生注册</a> ';
										}
										return e + f;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function LockLoad() {
	$("#pic").val("1");
    $('#exampleTable').bootstrapTable('refresh');
    $("#pic").val("");
}
//打印
function dayin(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要打印的考生");
		return;
	}else if(rows.length != 1){
		layer.msg("每次打印只能选择一个考生");
		return;
	}
	layer.confirm("确认要为选中的考生打印吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		preview(1);
		layer.close(index);
	})
}
//退学
function tuiXue(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要退学的考生");
		return;
	}
	layer.confirm("确认要为选中的'" + rows.length + "'个考生办理退学手续吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['studentSpecialityid'];
		});
		$.ajax({
			url : prefix+"/updateTx",
			type : "post",
			data : {
				'ids' : ids
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
//专业停考
function tingKao(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要停考的考生");
		return;
	}
	layer.confirm("确认要为选中的'" + rows.length + "'个考生设置专业停考吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		/* $.ajax({
			url : prefix+"/updateTx",
			type : "post",
			data : {
				'ids' : ids
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		}); */
	})
	
}
function add() {
	layer.open({
		type : 2,
		title : "增加",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : prefix + '/add'
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : "编辑 【 二级院校 > 学生档案管理 】",
		maxmin : true,
		shadeClose : false, 
		area : [ '80%', '80%' ],
		content : prefix + '/edit/' + id
	});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : "考生注册",
		//maxmin : true,
		shadeClose : false, 
		area : [ '600px', '350px' ],
		content : contextPath + 'student/studentRegStudent?id=' +id
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
			ids[i] = row['studentSpecialityid'];
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