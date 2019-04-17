
var prefix = contextPath+"/student/practiceSchoolRegion"
$(function() {
	load();
});
function kecheng() {
	if($("#studentId").val()==""){
		layer.msg("请先选择考生！");
		return;
	}
	layer.open({
		type : 2,
		title : '实践课程列表',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '700px', '90%' ],
		content : prefix + '/sjCourse?specialityRecordid='+$("#specialityRecordid").val() // iframe的url
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
		content :  contextPath + 'student/studentCourseRepaire/sjbkStudent/' // iframe的url
	});
}
function load() {
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
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								studentid:$('#studentId2').val(),
								specialityRecordid:$('#specialityRecordid').val()
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
									field : 'studentid', 
									title : '准考证号' 
								},{
									field : 'studentName', 
									title : '姓名' 
								},{
									field : 'specialityid', 
									title : '专业代码' 
								},{
									field : 'specialityName', 
									title : '专业名称' 
								},{
									field : 'courseid', 
									title : '课程代码' 
								},{
									field : 'courseName', 
									title : '课程名称' 
								},{
									field : 'status', 
									title : '状态' 
								},{
									field : 'operator', 
									title : '操作员' 
								},{
									field : 'updateDate', 
									title : '操作日期' 
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
										var f = '<a class="btn btn-danger btn-sm" href="#" title="取消报考"  mce_href="#" onclick="quxiaobaoming(\''
												+ row.id +'\',\''+ row.courseid
												+ '\')">取消报考</a> ';
										return f ;
									}
								} ]
					});
}
function reLoad() {
	if($("#studentId").val()==""){
		layer.msg("请先选择考生！");
		return;
	}
	$('#exampleTable').bootstrapTable('refresh',{url:prefix + "/list"});
}
function quxiaobaoming(id,courseid) {
	$.ajax({
		url : prefix+"/remove",
		type : "post",
		data : {
			'id' : id,
			'studentid' : $("#studentId2").val(),
			'specialityRecordid' : $("#specialityRecordid").val(),
			'courseid' : courseid,
		},
		success : function(r) { 
			parent.layer.msg("已取消！");
			reLoad();
		}
	});
}


