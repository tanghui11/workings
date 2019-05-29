
var prefix = contextPath+"/student/studentRegion"
$(function() {
    childRegion();
	load();
    examTask();
    
});

//报考县区数据
function childRegion(){
    var regionid = $("#regionid").val();
    $.ajax({
        url : contextPath+"/student/studentRegion/childRegionlist",
        type : "get",
        data:{
            parentid:regionid
        },
        success : function(data) {
            /*$("#childRegionid").find("option").remove();//首先清空下拉数据*/
            for (var i =0; i < data.length; i++) {
                $("#childRegionid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
            }
        }
    });
}
var examid=[];
function examTask(){
    $.ajax({
        url : contextPath+"/student/studentRegion/listTask",
        type : "get",
        //async:false,//更改为同步
        success : function(data) {
            for (var i =0; i < data.length; i++) {
                $("#examid").append("<option value=" + data[i].id + ">" +data[i].examName+ "</option>");
                examid.push(data[i].id)
            }
        }
    });
}
function jtDm(){
	layer.open({
		type : 2,
		title : '集体代码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '630px', '80%' ],
		content : prefix + '/group'
	});
}
function bkZy(){
	layer.open({
		type : 2,
		title : '报考专业',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '630px', '80%' ],
		content : prefix + '/specialityRecord'
	});
}
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
								regionid:$("#regionid").val(),
                                childRegionid:$("#childRegionid").val(),
                                groupid:$("#groupid").val(),
                                specialityRecordid:$("#specialityRecordid").val(),
								examTaskid:$("#examid").val(),
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
									field : '',
									title : '序号',
									formatter: function (value, row, index) {
                                    	return index+1;
									},
									width : '60px'
								},
										{
                                	field : 'studentid',
                                	title : '准考证号'
                            	},
										{
									field : 'certificateNo',
									title : '证件号码'
								},
										/*{
									field : 'type',
									title : '类别'
								},*/
										{
									field : 'name',
									title : '姓名'
								},
										/*{
									field : 'ename',
									title : '英文名'
								},
										{
									field : 'pinyin',
									title : '拼音'
								},
										{
									field : 'gender',
									title : '性别'
								},
										{
									field : 'homeType',
									title : '户籍'
								},
										{
									field : 'politics',
									title : '政治面貌'
								},
										{
									field : 'nation',
									title : '民族'
								},
										{
									field : 'profession',
									title : '职业'
								},
										{
									field : 'birthday',
									title : '出生日期'
								},*/
										{
									field : 'nativePlace',
									title : '籍贯'
								},
										/*{
									field : 'certificateType',
									title : '证件类别'
								},
										{
									field : 'phone',
									title : '固定电话'
								},*/
										{
									field : 'mphone',
									title : '移动电话'
								},
										{
									field : 'address',
									title : '通讯地址'
								},
										{
									field : 'gradSchool',
									title : '毕业院校'
								},
										/*{
									field : 'gradCertificate',
									title : '毕业证书号'
								},
										{
									field : 'education',
									title : '原学历'
								},
										{
									field : 'specialityid',
									title : '专业代码'
								},*/
										{
									field : 'specialityName',
									title : '专业名称'
								},
										{
									field : 'createDate',
									title : '注册时间'
								},
										/*{
									field : 'postCode',
									title : '邮编'
								},
										{
									field : 'email',
									title : '电子邮箱'
								},
										{
									field : 'idcardPic',
									title : ''
								},
										{
									field : 'pic',
									title : '照片路径'
								},
										{
									field : 'regionid',
									title : '地州市考办编号'
								},
										{
									field : 'childRegionid',
									title : '县区考办编号'
								},
										{
									field : 'schoolid',
									title : '助学组织编号'
								},
										{
									field : 'collegeid',
									title : '学院编号'
								},
										{
									field : 'teachSiteid',
									title : '教学点编号'
								},
										{
									field : 'groupid',
									title : '集体代码'
								},
										{
									field : 'key',
									title : '验证码'
								},
										{
									field : 'password',
									title : '密码'
								},
										{
									field : 'workAddress',
									title : '工作地址'
								},
										{
									field : 'question',
									title : '密码问题'
								},
										{
									field : 'answer',
									title : '答案'
								},
										{
									field : 'oldStudentid',
									title : '旧准考证'
								},
										{
									field : 'classify',
									title : '报考类别'
								},
										{
									field : 'status',
									title : '状态'
								},
										{
									field : 'auditStatus',
									title : '审核状态'
								},
										{
									field : 'confirmStatus',
									title : '确认状态'
								},
										{
									field : 'backOperator',
									title : '退学操作员'
								},
										{
									field : 'backDate',
									title : '退学日期'
								},
										{
									field : 'confirmOperator',
									title : '确认操作员'
								},
										{
									field : 'confirmDate',
									title : '确认操作日期'
								},
										{
									field : 'updateDate',
									title : '操作日期'
								},
										{
									field : 'createDate',
									title : ''
								},
										{
									field : 'dbFlag',
									title : '数据标记'
								},
                            			{
                                	field : 'operator',
									title : '操作员'
                            	},*/
										{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
}

function add() {
    var index = $("#examid option:selected").index()
	var xq = $("#childRegionid option:selected").val()
    //alert(index)
	layer.open({
		type : 2,
		title : '添加 【 地州市考办管理 > 学生信息采集 】',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content : prefix + '/add?examTaskid=' + examid[index]+"&&childRegionid=" + xq// iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑 【 地州市考办管理 > 学生信息修改 】',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
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