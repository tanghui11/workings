
var prefix = contextPath+"/school/collegeSpecialitySchool"
$(function() {
	load();
});

function load() {
	
					
	$('#exampleTable2')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/dList", // 服务器数据的加载地址
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
						pageSize:5,  //最多显示几条数据
						clickToSelect: true,                //是否启用点击选中行
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
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
                                field : 'id',
                                title : '序号',
                                formatter: function (value, row, index) {
                                    return index+1;
                                },
                                width:'60px'
                            },
                            {
                                field : 'specialityname',
                                title : '专业名称'
                            },
									{
									field : 'status', 
									title : '使用状态' 
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="添加" onclick="add(\''
												+ row.id
												+ '\')"><i class="fa fa-plus"></i></a> ';
										
										return e ;
									}
								}
										]
					});
}
function reLoad() {
	$('#exampleTable2').bootstrapTable('refresh');
}
function add(id) {
	var collegid = parent.$('#orgid').val();
		layer.confirm('确定要添加选中的记录？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				url : prefix+"/save",
				type : "post",
				data : {
					"collegeid":collegid,
					"specialityRegid":id
				},
				success : function(r) {
					if (r.code==0) {
						layer.msg(r.msg);
						reLoad();
						parent.reLoad();
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭  
					}else{
						layer.msg(r.msg);
					}
				}
			});
		})
}
function tianjia() {
	layer.open({
		type : 2,
		title : "分配专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '80%', '80%' ],
		content :  prefix + '/waitSpecialitySchool'
	});
}