
var prefix = contextPath + "/student/WarehousingScoreEdit"
$(function() {
	
	load();
});
var id;
var oldGrade;
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
						singleSelect : true, // 设置为true将禁止多选
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
					            /* name:$('#searchName').val(),
					            certificateNo:$('#searchName').val(),
								status:$('#selectStatus').val(), */
								studentid:$("#studentid").val(),
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
									radio : true
								},
									{
									field : 'courseid',
									title : '课程代码',
									width :'100px'
								},
										{
									field : 'courseName',
									title : '课程名称'
								},
										{
									field : 'examFlag',
									title : '缺考'
								},
										{
									field : 'status',
									title : '违规'
								},
										{
									field : 'grade',
									title : '成绩'
								},
										{
									field : 'operator', 
									title : '操作员' ,
									width :'90px'
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width :'100px'
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width :'120px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
                                            + '\')"><i class="fa fa-remove"></i></a> ';

										var f = '<a class="btn btn-success btn-sm" href="#" title="修改记录"  mce_href="#" onclick="studentScoreIn(\''
												+ row.id
												+ '\')">修改记录</a> ';
										return f;
									}
								} ],
								onCheck:function(row){
									id=row.id;
                                    oldGrade = row.grade;
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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


//弹窗显示考生成绩
function studentScoreIn(id) {
    layer.open({
        type: 2,
        title: ' 【 成绩信息 】 ',
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content: contextPath + 'student/WarehousingScoreEditRecord?scoreid='+id // iframe的url
    });

}

//选择调档
function  selectStatus() {
    reLoad()
}
//成绩修改
function save() {
	if($("#grade").val()==""){
		layer.msg("请输入成绩")
	}else{
		var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
		if (rows.length == 0) {
			layer.msg("请选择要修改成绩的数据");
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : prefix+"/update",
			data : {
				 "id":id, 
				"newGrade":$("#grade").val(),
				"remark":$("#remark").val(),
				"grade":oldGrade,
			},// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(r) {
				 if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
			}
		});
	}
}