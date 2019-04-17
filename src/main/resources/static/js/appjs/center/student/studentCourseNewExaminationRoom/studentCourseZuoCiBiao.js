
var prefix = contextPath+ "/student/studentCourseNewExaminationRoom"/* studentCourse  */
$(function() {
	load();
	
});
	var url = window.location.href;
	var arrangeid = url.split("?")[1].split("=")[1].split("&")[0]
	var roomid = url.split("?")[1].split("&")[1].split("=")[1]

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listSeat", // 服务器数据的加载地址
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
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								exam_roomid:parent.$("#examRoomid").val(),
								exam_timeid:parent.$("#kktime").val()
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
									field : 'studentid', 
									title : '准考证号' 
								},
										{
									field : 'name', 
									title : '姓名' 
								},
										{
									field : 'seq', 
									title : '座位序号' 
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
									field : 'type', 
									title : '报考类别',
									formatter: function (value, row, index) {
										if(row.type=='b'){
											return "学院"
										}else if(row.type=='a'){
											return "网报"
										}else if(row.type=='c'){
											return "考区"
										}else if(row.type=='d'){
											return "中心"
										}
									} 
								},		{
									field : 'update_date', 
									title : '操作日期' 
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '125',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-danger btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="cancelRegister(\''
												+ row.id+'\',\''+row.room_arrangeid
												+ '\')">取消报考</a> ';
										return e ;
									}
								}
										]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

//取消报考
function cancelRegister(id) {
	$.ajax({
		url :contextPath+  "student/studentCourseNewExaminationRoom/removeSeat",
		type : "post",
		data : {
			'id' : id,
			'exam_roomid' : roomid,
			'room_arrangeid' : arrangeid
		},
		success : function(data) {
			layer.closeAll(); //疯狂模式，关闭所有层
			layer.alert(data.msg);
			reLoad();
		}
	});
}

