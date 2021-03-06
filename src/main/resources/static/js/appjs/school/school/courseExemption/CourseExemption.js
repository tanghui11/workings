
var prefix =contextPath+ "school/courseExemption"
$(function() {
	load();
});
var code;
var names;
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
									field : 'gradSchool',
									title : '毕业院校' ,
									width : '60px'
									
								},
									{
									field : 'gradCertificate',
									title : '毕业证书号' ,
									width : '80px'
								},
								{
									field : 'gradSpecialityName',
									title : '原学专业'
								},
										{
									field : 'education',
									title : '原学历'
								},
										{
									field : 'specialityName',
									title : '报考专业' ,
									width : '70px',
								},
										{
									field : 'auditStatus',
									title : '审核状态' ,
									width : '70px',
								},
										{
									field : 'status',
									title : '状态' ,
									width : '80px',
								},
										{
									field : 'operator', 
									title : '操作员' ,
									width : '80px',
								},
										{
									field : 'updateDate', 
									title : '操作日期' ,
									width : '90px',
								},
										{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '100px',
									formatter : function(value, row, index) {
                                        var f = '<a class="btn btn-success btn-sm"'
                                            +'style="margin-right:5px'
                                            + '" href="'+contextPath+'school/GeneralExaminationCourse?studentid='+row.studentid
                                            + '" data-index="examRegOrs"'
                                            + ' data-text="统考课程"'
                                            + ' data-refresh="true"'
                                            + ' onclick="parent.menuItem(this);return false;"'
                                            + ' data-id="examRegOrs">统考课程</a>';
										return f ;
									}
								} ],
                        onCheck:function(row){
							code = row.courseid;
							names =row.courseName;
						}

					});
}

function reLoad() {
    if($("#studentid").val()==""){
        layer.msg("准考证不能为空！");
        return;
    }
	$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
}

function add() {
	layer.open({
		type : 2,
		title : '添加 [ 计划管理 &gt; 开考课程设置(按课程) ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '390px' ],
		content : prefix + '/add' // iframe的url
	});
}

function dd() {
    var obj=document.getElementById('searchCourseType');
    var index=obj.selectedIndex; //序号，取当前选中选项的序号
    var val = obj.options[index].value;
    $("#courseT").val(val)
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