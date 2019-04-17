
var prefix = contextPath+ "/student/studentCourseNewExaminationRoom"/* studentCourse  */
$(function() {
	load();
	kaodian();
});
//考点名称
function kaodian(){
	
	$.ajax({
		cache : true,
		type : "get",
		data :{
			"regionid" :parent.$("#regionid").val(),
			"exam_taskid" :parent.$("#searchName").val(),
		},
		url :contextPath+ "/student/studentCourseNewExaminationRoom/selectSubmitSite",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=' '>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
			}
			$("#searchKaoDian").html(_html);
		}
	});
}

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/selectRoom", // 服务器数据的加载地址
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
								regionid :parent.$("#regionid").val(),
								exam_taskid :parent.$("#searchName").val(),
								exam_site_submitid :$("#searchKaoDian").val(),
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
									field : 'regionid', 
									title : '考区编号' 
								},
										{
									field : 'region', 
									title : '考区' 
								},
										{
									field : 'room_no', 
									title : '考场号' 
								},
										{
									field : 'seat_num', 
									title : '座位数' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'update_date', 
									title : '操作时间' 
								},
										]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
//补考场
function bkc(){
	if($("#searchKaoDian").val()==" "){
		layer.msg("请选择考点并查询！")
		return;
	}
	
	layer.confirm('确定要增加一个考场吗！', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/saveRoom",
			type : "post",
			data : {
				'regionid' :parent.$("#regionid").val(),
				'exam_taskid' : parent.$("#searchName").val(),
				'exam_site_submitid' : $("#searchKaoDian").val()
			},
			success : function(data) {
				layer.closeAll(); //
				layer.alert(data.msg);
				reLoad();
			}
		});
	})
}
//删除考场
function removeKC(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的考场！");
		return;
	}
	layer.confirm('确定要删除本考场吗！', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : rows[0].id,
				'opertor' : rows[0].operator
			},
			success : function(data) {
				layer.closeAll(); //
				layer.alert(data.msg);
				reLoad();
			}
		});
	})
}
//选择考场
function xuanze(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择考场！");
		return;
	}else{
		parent.$("#examRoomid").val(rows[0].id);
		parent.$("#searchKaoDian").val($("#searchKaoDian option:selected").text()+" 考场号: "+rows[0].room_no);
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭  
		parent.reLoad();
	}
	
}


