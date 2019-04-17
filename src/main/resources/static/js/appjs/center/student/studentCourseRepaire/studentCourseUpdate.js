
var prefix = contextPath+ "/student/studentCourseRepaire"
$(function() {
	load();
	kSrw();
	regionList();
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
						height: ($(document).height() - 160),
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								regionid :$('#schoolid').val(),
								studentid:$('#studentId').val(),
					          //  examTaskid:$('#searchName').val(),
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
									radio : true
								},
									{
									field : 'id', 
									title : '序号' ,
									width:'50px',
									formatter: function (value, row, index) {
										return index+1;
									}
								},
										{
									field : 'studentid', 
									title : '准考证号' ,
									width:'110px',
								},
										{
									field : 'name', 
									title : '考生姓名' ,
									width:'100px',
								},
										{
									field : 'courseid', 
									title : '课程代码' ,
									width:'70px',
								},
								{
									field : 'courseName', 
									title : '课程名称' 
								},
										{
									field : 'examDate', 
									title : '考试时间' ,
									width:'90px',
								},
										{
									field : 'type', 
									title : '类别' ,
									width:'80px',
								},
										{
									field : 'regionName', 
									title : '考试地州' ,
									width:'90px',
								},
										{
									field : 'childRegionName', 
									title : '考试县区' ,
									width:'90px',
								},
										/*{
									field : 'regionid', 
									title : '考试考区编号' 
								},
										 {
									field : 'childRegionid', 
									title : '考试区县编号' 
								},
										{
									field : 'examCourseid', 
									title : '开考课程编号' 
								}, */
										{
									field : 'segment', 
									title : '时段' ,
									width:'60px'
								},
										{
									field : 'status', 
									title : '状态' ,
									width:'80px'
								},
										{
									field : 'arrangeStatus', 
									title : '编排状态' ,
									width:'80px'
								},
										{
									field : 'operator', 
									title : '操作员' ,
									width:'80px'
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width:'90px',
								},
									/* 	{
									field : 'dbFlag', 
									title : '数据标记' 
								},  
									{
									title : '操作',
									field : 'id',
									align : 'center',
									width:'100px',
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
										return e + d ;
									}
								}*/]
					});
}
function reLoad() {
	if($("#teachSiteid").val()==""){
		layer.msg("请选择教学点！")
		return;
	}
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {

    location.href =  prefix + '/edit/' + id ;
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
//考试任务
function kSrw(){
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
			$("#searchName").html(_html);
		}
	});
}
$("#searchName").on('change',function(){
	$("#search").val("");
	$("#examCourseid").val("");
})
//报考课程
function baokao(){
	if($("#searchName").val()==""){
		layer.msg("请选择考试任务！")
		return;
	}
	layer.open({
		type : 2,
		title : '报考课程',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '400px' ],
		content :  contextPath + 'student/studentCourseRepaire/bkCourse/?examTaskid='+$("#searchName").val() // iframe的url
	});
}
//准考证号
function zhunkao(){
	layer.open({
		type : 2,
		title : '准考证号',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '600px', '400px' ],
		content :  contextPath + 'student/studentCourseRepaire/bkStudent/' // iframe的url
	});
}
//地州市考办
function regionList(){
	var _html ="<option value = ''>-请选择-</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'type' : "a"
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
				_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
			}
			$("#regionid").html(_html);
		}
	});
}
//根据省考办获取县考办
function region(){
	var _html ="<option value=''>--请选择--</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'parentid' : $("#regionid").val()
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){

				_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
			}
			$("#childRegionid").html(_html);
		}
	});
}
//报考
function register(){
	if($("#searchName").val()==""){
		layer.alert("请选择考试任务");
	}else if($("#search").val()==""){
		layer.alert("请选择报考课程");
	}else if($("#studentId").val()==""){
		layer.alert("请选择准考证号");
	}else if($("#regionid").val()==""){
		layer.alert("请选择考试地区");
	}else if($("#childRegionid").val()==""){
		layer.alert("请选择考试县区");
	}else if($("#childRegionid").val()==""){
		layer.alert("请选择考试县区");
	}else{
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath+ "/student/studentCourseRepaire/save",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					$("#searchName").val("");
					$("#search").val("");
					$("#studentId").val("");
					$("#regionid").val("");
					$("#regionid").val("");
					$("#childRegionid").val("");
					reLoad();
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
//取消报考
function cancelRegister(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消报考的数据");
		return;
	}
	layer.confirm("确认要取消报考选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		var flag = false;
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
			if(row.type!="中心"){
				layer.msg("只能修改中心端的报考");
				flag=true;
			}
		});
		if(flag==false){
			$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : contextPath+ "/student/studentCourseRepaire/batchRemove",
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
		}
		
	}, function() {

	});
}
//修改课程
function revamp(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要修改课程的数据");
		return;
	}
	if ($("#search").val() == "") {
		layer.msg("请选择报考课程");
		return;
	}
	var id = rows[0].id;
	$.ajax({
		type : 'POST',
		data : {
			"id" : id,
			"examCourseid":$("#examCourseid").val()
		},
		url : contextPath+ "/student/studentCourseRepaire/update",
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
				$("#search").val("");
			} else {
				layer.msg(r.msg);
			}
		}
	});

}
//修改考点
function centre(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要修改课程的数据");
		return;
	}
	if ($("#regionid").val() == "") {
		layer.msg("请选择考试地州");
		return;
	}
	if ($("#childRegionid").val() == "") {
		layer.msg("请选择考试县区");
		return;
	}
	var id = rows[0].id;
	$.ajax({
		type : 'POST',
		data : {
			"id" : id,
			"regionid":$("#regionid").val(),
			"childRegionid":$("#childRegionid").val()
		},
		url : contextPath+ "/student/studentCourseRepaire/update",
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
				$("#regionid").val("");
				$("#childRegionid").val("");
			} else {
				layer.msg(r.msg);
			}
		}
	});


}