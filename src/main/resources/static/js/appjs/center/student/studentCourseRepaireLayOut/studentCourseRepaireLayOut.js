
var prefix = contextPath+ "/student/studentCourseRepaireLayOut"
$(function() {
	load();
	kSrw();
	regionList();
});
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
			$("#searchKsrw").html(_html);
		}
	});
}
$("#searchKsrw").on('change',function(){
	$("#search").val("");
	$("#examCourseid").val("");
})
//报考课程
function baokao(){
	if($("#searchKsrw").val()==""){
		layer.msg("请选择考试任务！")
		return;
	}
	layer.open({
		type : 2,
		title : '报考课程',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '400px' ],
		content :  contextPath + 'student/studentCourseRepaire/bkCourse/?examTaskid='+$("#searchKsrw").val() // iframe的url
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
	if($("#regionid").val()==''){
		$("#childRegionid").html("<option value=''>--请选择--</option>");
		return;
	}
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
								regionid:$("#regionid").val(),
								exam_courseid:$("#examCourseid").val()
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
									field : 'regionName', 
									title : '考区' 
								},
										{
									field : 'name', 
									title : '考点' 
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
									field : 'room_no', 
									title : '考场号' 
								},
										{
									field : 'seat_start', 
									title : '座次开始号' 
								},
										{
									field : 'seat_end', 
									title : '座次结束号' 
								},
										{
									field : 'exam_date', 
									title : '考试日期' 
								},
										{
									field : 'segmentName', 
									title : '时段' 
								},
									
										
									{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '100px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="考生报考" onclick="ksbk(\''
												+ row.exam_roomid+'\',\''+row.room_arrangeid
												+ '\')">座次列表（报考）</a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e ;
									}
								} ]
					});
}
function ksbk(roomid,arrangeid) {
	
	layer.open({
		type : 2,
		title : '座次列表',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content :  contextPath + 'student/studentCourseRepaireLayOut/seat?exam_roomid='+roomid+'&room_arrangeid='+arrangeid// iframe的url
	});
}

function reLoad() {
	if($("#search").val()==""){
		layer.msg("请选择报考课程！")
		return;
	}
	if($("#regionid").val()==""){
		layer.msg("请选择考区！")
		return;
	}
	$('#exampleTable').bootstrapTable('refresh');
}
