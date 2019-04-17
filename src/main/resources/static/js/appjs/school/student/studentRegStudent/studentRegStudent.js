
var prefix = contextPath+"/student/studentRegStudent"
$(function() {
	load();
});

var id;
var dyID = $("#studentSpecialityid").val();
function load() {
		$('#exampleTable2')
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
								studentSpecialityid:dyID
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						onCheck:function(row, eve){
							id = row.id
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						singleSelect : true,
						columns : [
								{
									checkbox : true,
								},
									{
									field : 'id', 
									title : '序号',
									formatter: function (value, row, index) {
										return index+1;
									}
								},
										
										{
									field : 'regBeginDate', 
									title : '学年开始日期' 
								},
										{
									field : 'regEndDate', 
									title : '学年结束日期' 
								},
										{
									field : 'operator', 
									title : '注册人' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										 ]
					});
}
function reLoad2() {
	$('#exampleTable2').bootstrapTable('refresh',{url: prefix + "/list"});
}
//注册
function save() {
	layer.confirm('您确定要为该生添加注册信息吗？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({  //判断注册信息重复报考
				type : "get",
				url : contextPath+"/student/studentRegStudent/selectYear",
				data:{
					'studentSpecialityid':dyID
				},
				success : function(data) {
					if(data!=0){
						layer.alert("同一年不能注册两次！");
					}else{
						zc();
					}
					
				}
			});
		})
	
	function zc(){ //注册信息成功
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath+"/student/studentRegStudent/save",
			data : {
				studentSpecialityid : dyID
			},
			success : function(data) {
				reLoad2();
				parent.reLoad();
				layer.close(layer.index)//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
				layer.msg("注册成功！");
			}
		});
	}
	
}
			


//取消注册
function qxzhuce(){
	var rows = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择需要取消的注册信息！");
		return;
	}
	layer.confirm('您确定要取消该学生的注册信息吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : contextPath+"common/searchIfExist",
			type : "get",
			data : {
				'tableName':'stu_student_reg',
				'filedName1':'status',
				'filedValue1':'b',
				'id':id
			},
			success : function(data) {
				if(data==0){
					remove(id);
					layer.close(layer.index)//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
				}else{
					layer.close(layer.index)//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
					layer.msg("注册信息已审核！");
				}
				
			}
		});
	})
}


function remove(id) {
	$.ajax({
		url : prefix+"/remove",
		type : "post",
		data : {
			'id' : id
		},
		success : function(r) {
			if (r.code==0) {
				layer.msg("注册信息已删除！");
				reLoad2();
				parent.reLoad();
			}else{
				layer.msg(r.msg);
			}
		}
	});
}

