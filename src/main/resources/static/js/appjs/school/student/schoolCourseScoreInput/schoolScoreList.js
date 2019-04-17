var prefix = contextPath+"/student/studentFileStudent"
$(function() {
	load();

});
var url = window.location.href
var studentid = url.split("?")[1].split("&&")[0].split("=")[1]
var specialityRecordid = url.split("?")[1].split("&&")[1].split("=")[1]

$("#nameId").html('准考证号 : '+studentid+' 姓名 : '+parent.$("#name_id").val())
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : contextPath + "/student/schoolCourseScoreInput/listScoreSchool", // 服务器数据的加载地址
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
                                   /*  limit: params.limit,
                                    offset:params.offset, */
                                    studentid:studentid,
                                    specialityRecordid:specialityRecordid
                                };
						},
						columns : [
									{
									field : 'courseid', 
									title : '课程代码' 
								},
									
										{
									field : 'courseName', 
									title : '课程名称' 
								},
								{
									field : 'grade', 
									title : '成绩' 
								},
								{
									field : 'status', 
									title : '状态' 
								},
								{
									field : 'updateDate', 
									title : '录入时间' 
								},
									{
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-warning btn-sm '+s_edit_h+'" href="#" mce_href="#" title="删除" onclick="remove(\''+ row.id + '\')"><i class="fa fa-remove"></i></a> ';
                                    return e;
                                }
								} 
									

						 ]
					});
					
	
}
function reLoad(){
	$('#exampleTable').bootstrapTable('refresh');
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : contextPath+"/student/schoolCourseScoreInput/remove",
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