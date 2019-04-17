
var prefix = contextPath+"/plan/course"
$(function() {
	load();
	
});
var url = location.search;
var panduanId = url.split("=")[1];
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
					            name:$('#searchName').val()
					            //pinyin:$('#searchPinyin').val(),
								//ename:$('#searchEname').val()
							};
						},
						onClickRow: function (row) { 
							$("#courseid").val(row.id)
							layer.close(layer.index);
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
									title : '课程编码' 
								},
								{
									field : 'name', 
									title : '名称' 
								},
								
								{
									field : 'score', 
									title : '学分' 
								},
								{
									field : 'type', 
									title : '类别' 
								},
								{
									field : 'classify', 
									title : '分类' 
								},
								/*{
									field : 'attribute', 
									title : '属性' 
								},
								{
									field : 'practiceCourseid', 
									title : '实践课程代码' 
								},*/
								/* {
									field : 'enabledFlag', 
									title : '0无效,1有效' 
								},
							
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var f = '<a class="btn btn-success btn-sm" href="#" title="选择课程代码"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-check"></i></a> ';
										return f;
									}
								} */ ],
								onCheck:function(row){
									//alert(row.name);
									//alert(row.id);
										parent.layer.closeAll('iframe');
										if(panduanId=="1"){
											parent.$("#appendCourse1").val('('+row.id+')'+row.name);
											parent.$("#appendCourseid1").val(row.id);
										}else if(panduanId=="2"){
											parent.$("#appendCourse2").val('('+row.id+')'+row.name);
											parent.$("#appendCourseid2").val(row.id);
										}else if(panduanId=="3"){
											parent.$("#appendCourse3").val('('+row.id+')'+row.name);
											parent.$("#appendCourseid3").val(row.id);
										}else if(panduanId=="4"){
											parent.$("#appendCourse4").val('('+row.id+')'+row.name);
											parent.$("#appendCourseid4").val(row.id);
										}else{
											parent.$("#practiceCourseid").val(row.id +"  " +row.name );
											parent.$("#gradCourseid").val(row.id);
											parent.$("#gradCourse").val('('+row.id+')'+row.name);
											parent.$("#coursess").val('('+row.id+')'+row.name);
											parent.$("#kkkc").val(row.name);
											//parent.$("#workerid").val(row.id);
											parent.$("#courseid").val(row.id);
											parent.$("#names").val(row.id + " "+ row.name);
											parent.$("#name").val(row.id );
											parent.$("#course").val(row.name);
											parent.$("#courseName").val($("#course").val());
											var _html = "<option>-请选择-</option>";
											$.ajax({
												cache : true,
												type : "get",
												url :contextPath +"/plan/book/seachBookList?courseid="+row.id,
												async : false,
												error : function(request) {
													parent.layer.alert("Connection error");
												},
												success : function(data){
													for(var i=0;i<data.length;i++){
														_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].name+"</option>"
													}
													//console.log(_html)
													parent.$("#book").html(_html);
												}
												
											});
									}
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

/* function add2() {
	layer.open({
		type : 1,
		title : '课程代码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : $('#box')  // iframe的url
	});
} */
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
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