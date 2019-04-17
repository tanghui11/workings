
var prefix = contextPath+"/student/scoreStudent"
$(function() {
	load();
	load1();
});
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
								studentid:$("#searchName").val(),
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
								/* {
									checkbox : true
								}, */
								{
									field : 'type', 
									title : '类别' 
								},
								{
									field : 'courseid', 
									title : '课程代码' 
								},{
									field : 'courseName', 
									title : '课程名称' 
								},
								{
									field : 'grade', 
									title : '成绩' 
								}]
					});
}
function load1() {
	$('#exampleTable1')
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
								studentid:$("#searchName1").val(),
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
								/*{
									checkbox : true
								},
									 {
									field : 'id', 
									title : '序号' ,
									width:'40px',
									formatter: function (value, row, index) {
										return index+1;
										}
								},
								{
									field : 'gender', 
									title : '性别' ,
								},
								{
									field : 'certificateNo', 
									title : '身份证号' 
								}, */
								{
									field : 'type', 
									title : '类别' 
								},
								{
									field : 'courseid', 
									title : '课程代码' 
								},{
									field : 'courseName', 
									title : '课程名称' 
								},
								
								{
									field : 'grade', 
									title : '成绩' 
								},
								{
									field : 'oldStudentid', 
									title : '原准考证号' 
								}]
					});
}
function reLoad() {
	if($("#searchName").val()==""){
		layer.msg("请输入准考证号");
	}else{
		//changes();
		$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
	}
	
}

function reLoad1() {
	if($("#searchName1").val()==""){
		layer.msg("请输入准考证号");
	}else{
		//changess();
		$('#exampleTable1').bootstrapTable('refresh',{url : prefix + "/list"});
	}
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/edit/' + id // iframe的url
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
//合并
function megerZkz(){
	if($("#searchName").val() == $("#searchName1").val()){
		layer.msg("合并的准考证不能一样");
		return;
	}
	if($("#sex").val()==""){
		layer.msg("请查询出数据")
	}else{
		layer.confirm('确定要合并？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				type : 'POST',
				data : {
					"oldStudentid":$("#searchName").val(),
					"newStudentid":$("#searchName1").val(),
				},
				url : prefix + '/megerZkz',
				success : function(r) {
					 if(r.code=="0"){
						$("#yStudentid").val($("#searchName").val())
						layer.alert(r.msg)
						layer.closeAll(); //疯狂模式，关闭所有层
						reLoad();
						reLoad1();
					}else{
						layer.alert(r.msg)
					}
				}
			});
		})
	}
}
function qxhb(){
	if($("#searchName1").val() == $("#yStudentid").val()){
		layer.msg("取消合并的准考证不能一样");
		return;
	}
	if($("#yStudentid").val()==""){
		layer.msg("请输入原准考证号");
		return ;
	}
	if($("#sex1").val()==""){
		layer.msg("请查询出数据")
	}else{
		layer.confirm('确定要取消合并？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				type : 'POST',
				data : {
					"oldStudentid":$("#yStudentid").val(),
					"newStudentid":$("#searchName1").val(),
				},
				url : prefix + '/reMegerZkz',
				success : function(r) {
					 if(r.code=="0"){
						$("#yStudentid").val("")
						layer.alert(r.msg)
						layer.closeAll(); //疯狂模式，关闭所有层
						reLoad();
						reLoad1();
					}else{
						layer.alert(r.msg)
					}
				}
			});
		})
	}
}
function changes(){
	$("#pic").attr("src","");
	$("#sex").val("");
	$("#sfz").val("");
}
function changess(){
	$("#pic1").attr("src","");
    $("#sex1").val("");
    $("#sfz1").val("");
}
//准考证号弹框
function zkzh(type){
	layer.open({
        type : 2,
        title : '准考证号',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : contextPath + 'student/studentCourseRepaire/hbStudent1?type='+type// iframe的url
    });
}