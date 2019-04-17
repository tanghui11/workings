
var prefix = contextPath+ "/student/studentCourseMovie"
$(function() {
	ksrw();
	load();
	regionList();
	regionLists();
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
								offset:params.offset,
                                examTaskid:$('#examTaskid').val(),//开考任务id
                                examCourseid:$('#examCourseid').val(),
								regionid:$('#regionid').val(),
								childRegionid:$('#childRegionid').val(),
                                /* newRegionId:$('#newRegionId').val(),
                                newChildRegionId:$('#newChildRegionId').val() */
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
                                	title : '准考证号',
									width:'150px'
                            	},
									{
									field : 'name',
									title : '姓名',
									width:'120px'
								},
										{
									field : 'courseid',
									title : '课程代码',
									width:'80px'
								},
                            	{
                            		field : 'courseName',
                               	 	title : '课程名称'
                           		 },
                           		 {
                               		 field : 'type',
                               		 title : '报考类别',
									 width:'100px'
                          		  },
										{
									field : 'regionid', 
									title : '报考地州市' ,
									width:'150px'
								},
										{
									field : 'childRegionid', 
									title : '报考县区' ,
									width:'150px'
								}]

					});
}
function reLoad() {
	if($("#examTaskid").val()==""){
		layer.msg("请选择考试任务！")
	}else if($("#examCourseName").val()==""){
		layer.msg("请选择考试课程！")
	}else if($("#regionid").val()==""){
		layer.msg("请选择报名考试地点！")
	}else if($("#childRegionid").val()==""){
		layer.msg("请选择报名考试地点！")
	}else{
		$('#exampleTable').bootstrapTable('refresh');
	}
	
}
function reloads(){
	$('#exampleTable').bootstrapTable('refresh');
}
// function add() {
//
//     location.href =  prefix + '/add';
// }
// function edit(id) {
//
//     location.href =  prefix + '/edit/' + id ;
// }
// function remove(id) {
// 	layer.confirm('确定要删除选中的记录？', {
// 		btn : [ '确定', '取消' ]
// 	}, function() {
// 		$.ajax({
// 			url : prefix+"/remove",
// 			type : "post",
// 			data : {
// 				'id' : id
// 			},
// 			success : function(r) {
// 				if (r.code==0) {
// 					layer.msg(r.msg);
// 					reLoad();
// 				}else{
// 					layer.msg(r.msg);
// 				}
// 			}
// 		});
// 	})
// }

function resetPwd(id) {
}
// function batchRemove() {
// 	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
// 	if (rows.length == 0) {
// 		layer.msg("请选择要删除的数据");
// 		return;
// 	}
// 	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
// 		btn : [ '确定', '取消' ]
// 	// 按钮
// 	}, function() {
// 		var ids = new Array();
// 		// 遍历所有选择的行数据，取每条数据对应的ID
// 		$.each(rows, function(i, row) {
// 			ids[i] = row['id'];
// 		});
// 		$.ajax({
// 			type : 'POST',
// 			data : {
// 				"ids" : ids
// 			},
// 			url : prefix + '/batchRemove',
// 			success : function(r) {
// 				if (r.code == 0) {
// 					layer.msg(r.msg);
// 					reLoad();
// 				} else {
// 					layer.msg(r.msg);
// 				}
// 			}
// 		});
// 	}, function() {
//
// 	});
// }

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
////报考课程
function baokao(){
	layer.open({
		type : 2,
		title : '报考课程',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '400px' ],
		content :  contextPath + 'student/studentCourseRepaire/bkCourse/?examTaskid='+$("#examTaskid").val() // iframe的url
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
//new地州市考办
function regionLists(){
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
			$("#newRegionId").html(_html);
		}
	});
}
//new根据省考办获取县考办
function regions(){
	var _html ="<option value=''>--请选择--</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'parentid' : $("#newRegionId").val()
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){

				_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
			}
			$("#newChildRegionId").html(_html);
		}
	});
}
//地点转移
function shift (){
	if($("#examTaskid").val()==""){
		layer.msg("请选择考试任务！")
	}else if($("#examCourseName").val()==""){
		layer.msg("请选择考试课程！")
	}else if($("#regionid").val()==""){
		layer.msg("请选择报名考试地点！")
	}else if($("#childRegionid").val()==""){
		layer.msg("请选择报名考试地点！")
	}else if($("#newRegionId").val()==""){
		layer.msg("请选择转移考试地点！")
	}else if($("#newChildRegionId").val()==""){
		layer.msg("请选择转移考试地点！")
	}else{
	 layer.confirm("您确定要将[" + $("#examTaskid option:selected").html() + "]考试中在["+$("#regionid option:selected").html()+"--"+$("#childRegionid option:selected").html()+"]考:["+$("#examCourseName").val()+"]课程的参数转移至["+$("#newRegionId option:selected").html()+$("#newChildRegionId option:selected").html()+"]参与考试吗？", {
 		btn : [ '确定', '取消' ]
	}, function() {
			$.ajax({
				type : 'get',
				data : {
                    examTaskid:$('#examTaskid').val(),//开考任务id
                    examCourseid:$('#examCourseid').val(),
                    regionid:$('#regionid').val(),
                    childRegionid:$('#childRegionid').val(),
					 newRegionid:$('#newRegionId').val(),
					 newChileRegionid:$('#newChildRegionId').val()
				},
				url : prefix + '/newPlace',
				success : function(r) {
					if (r == 1) {
						console.log(r.msg)
						layer.msg("操作成功");
						reloads();
					} else {
						layer.msg("操作失败");
					}
				}
			});
		}, function() {

		}); 
	}
}