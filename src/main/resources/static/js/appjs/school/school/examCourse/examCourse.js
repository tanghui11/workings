
var prefix =contextPath+ "school/examCourse"
$(function() {
	kSrw();

	load();
});
var code;
var names;
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
								type:$('#type').val(),
								examTaskid:$('#searchName').val(),
								courseName:$("#coursess").val(),
					            courseid:$('#coDaima').val(),
					            courseType: $("#courseT").val(),
                                examTimeid:$("#kktime").val()
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
									field : 'id', 
									title : '序号' ,
									formatter: function (value, row, index) {
										return index+1;
									},
									width : '60px'
									
								},
									{
									field : 'courseid', 
									title : '课程代码' ,
									width : '80px'
								},
								{
									field : 'courseName', 
									title : '课程名称' 
								},
										{
									field : 'bookName', 
									title : '教材名称' 
								},/* 
										{
									field : 'examTimeid', 
									title : '开考时间编号' 
								},
										{
									field : 'fullScore', 
									title : '满分' 
								},
										{
									field : 'passScore', 
									title : '及格分数' 
								},
										{
									field : 'objectivesScore', 
									title : '客观题总分' 
								},
										{
									field : 'subjectiveScore', 
									title : '主观题总分' 
								},
										{
									field : 'exceedNumber', 
									title : '超员人数' 
								}, */
										{
									field : 'type', 
									title : '类别' ,
									width : '70px',
								},
										{
									field : 'classify', 
									title : '命题类别' ,
									width : '70px',
								},
										{
									field : 'cardType', 
									title : '题卡类别' ,
									width : '80px',
								},
								// 	 	{
								// 	field : 'auditStatus',
								// 	title : '审核状态'
								// },
								// 		{
								// 	field : 'course_type',
								// 	title : '考试类型'
								// },
								// {
								// 	field : 'schoolid',
								// 	title : '助学组织'
								// },
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
									width : '200px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm ' +
											''+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id+"','"+row.courseName+"','"+row.bookName
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
                                        var f = '<a class="btn btn-success btn-sm"'
                                            +'style="margin-right:5px'
                                            + '" href="'+contextPath+'exam/examCourse/placeCourses?courseid='+row.courseid
                                            + '" data-index="examRegOrs"'
                                            + ' data-text="限报课程"'
                                            + ' data-refresh="true"'
                                            + ' onclick="parent.menuItem(this);return false;"'
                                            + ' data-id="examRegOrs">限报课程</a>';
										return e + d + f;
									}
								} ],
                        onCheck:function(row){
							code = row.courseid;
							names =row.courseName;
						}

					});

}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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
var cour;
var book;

function dd() {
    var obj=document.getElementById('searchCourseType');
    var index=obj.selectedIndex; //序号，取当前选中选项的序号
    var val = obj.options[index].value;
    $("#courseT").val(val)
}

function edit(id,courseName,bookName) {
	cour = courseName;
	book = bookName;
	layer.open({
		type : 2,
		title : '编辑 [ 计划管理 &gt; 开考课程设置(按课程) ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '395px' ],
		content : prefix + '/edit/' + id +"?courseName="+courseName // iframe的url
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

//考试任务
function kSrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "/exam/task/serchTaskAll?type=b",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=''>-请选择-</option>";
			for(var i=0;i<data.length;i++){

					_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"(衔接)"+"</option>";
			}
			$("#searchName").html(_html);
			// ktime($("#searchName").val());
		}
	});
}
//开考时间

var kaoshi
function ktime(val){
	kaoshi = val;
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "/exam/examTime/seachExamTimeAllList?examTaskid="+val,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=''>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examDate.split(" ")[0]+data[i].segment+"【"+data[i].beginTime+"--"+data[i].endTime+"】"+"</option>";
			}
			$("#kktime").html(_html);
		}
	});
}
//课程名称
function courseName(){
	  layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content:contextPath+  "/plan/course/clistXJ"
    }); 
	
}



function exportExcel(src){
    document.getElementById('form').action=contextPath+"/"+src;
//若需提交表单（如导出）

    document.getElementById('form').submit();
}
function place(){
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要增加限制报考专业的数据");
        return;
    }
    layer.open({
        type: 2,
        title: '增加限制报考专业',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '300px'],
        content:contextPath+  "/exam/examCourse/place"
    });
}