var prefix =contextPath+ "/region/replaceAdd"
$(function() {
	validateRule();
    var url =location.href;
    var studentid = url.split("?")[1].split("=")[1];
    $("#studentid").val(studentid);
    $("#courseid").val(parent.$("#courseid").val());
    $("#specialityRecordid").val(parent.$("#specialityRecordid").val());
	load();
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	 $.ajax({
			cache : true,
			type : "POST",
			url : contextPath+"/region/exemptionCourseReplace/save",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index); 
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			courseFreeName:{
				required : true
			},
			grade:{
				required : true,
				number:true
			}

		},
		messages : {
			courseFreeName:{
				required : icon + "顶替名称不能为空"
			},
			grade:{
				required : icon + "成绩不能为空",
				number:icon + "成绩必须是数字"
			}
		}
	})
}
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/addList", // 服务器数据的加载地址
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
                                	field : 'ids',
                                	title : '编号',
								formatter: function (value, row, index) {
                                      return index+1;
                                            }
                            	},
										{
									field : 'replaceName',
									title : '顶替名称'
								},
										{
									field : 'grade',
									title : '成绩'
								},
										{
									field : 'replaceRemark',
									title : '顶替备注'
								},
                           				 {
                               		 field : 'auditStatus',
                               		 title : '审核状态'
								},
                            			{
                            		 field : 'auditDate',
                                	 title : '审核日期'
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
									 ],
                       		 onCheck:function(row){
                                    layer.confirm('确定要添加选中的记录？', {
                                        btn : [ '确定', '取消' ]
                                    }, function() {
                                        parent.layer.closeAll('iframe');
                                        $.ajax({
                                            url : prefix+"/save",
                                            type : "post",
                                            data : {
                                                'studentid' : $("#studentid").val(),
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
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
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
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '//save',
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


//顶替名称
function courseFreenames(){
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content :contextPath+'plan/courseFreeCenter/courseFreeOpen' // iframe的url
	});
}