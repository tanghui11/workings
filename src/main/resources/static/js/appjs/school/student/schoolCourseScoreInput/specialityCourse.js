var prefix = contextPath+"/plan/specialityCourse"
$(function() {
    load();
});
function judge(){
	if($("#grade").val()>100){
        $("#grade").val('');
        layer.msg("输入的分数不符合规定");
        return;
	}
}
var url = window.location.search;
var specialityRecord = url.split("?")[1].split("&")[0].split("=")[1];
var schoolSpecialityRegid2 = parent.$("#specialityRecordid").val();
var courseids;
var studentid = url.split("?")[1].split("&")[1].split("=")[1];
function load() {
	
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list/", // 服务器数据的加载地址
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
					            specialityRecordid:specialityRecord,
					            courseid:$('#searchName').val()
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
									field : 'id', 
									title : '序号',
									formatter: function (value, row, index) {
										return index+1;
									},
									width : '60px',
								},
									{
									field : 'courseid',
									title : '课程代码',
									width : '90px',
								},
										{
									field : 'courseName',
									title : '课程名称' 
								},
                            	{
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var f = '<a class="btn btn-warning btn-sm" href="#" title="查看录入成绩"  mce_href="#" onclick="checkscore(\''
                                        + row.courseid
                                        + '\')">查看录入成绩</a> ';

                                    return f;
                                }
                            	}],onCheck:function(row){
                            courseids = row.courseid
						}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '添加 [ 计划管理 > 专业课程 ]',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '410px' ],
		content : prefix + '/add' // iframe的url
	});
}
var course;
var book;
function edit(id,courseName,bookName) {
	course = courseName;
	book = bookName;
	 layer.open({
		type : 2,
		title : '编辑 [ 计划管理 > 专业课程 ] ',
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '360px'],
		content : prefix + '/edit/' + id+"?courseName="+courseName+"&bookName="+bookName// iframe的url
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
	jcid =id;
	 /* layer.open({
		type : 2,
		title : '设置教材',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content :"/plan/book/seachBookList?id=" + id // iframe的url
	}); */
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
//成绩录入
function luru(){

    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要录入成绩的数据");
        return;
    }
	if($("#grade").val()==""){
    	layer.msg("请输入成绩录入!");
    	return;
	}
    $.ajax({
        type : 'POST',
        data : {
            "studentid" : studentid,
            "courseid" :courseids,
            "grade" : $("#grade").val(),
            "specialityRecordid" : schoolSpecialityRegid2,
            "collegeid" :  parent.$("#collegeid").val()

        },
        url : contextPath + '/student/schoolScoreStudent/scoreIn',
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
//查看录入成绩
function checkscore(courseid){
    layer.open({
        type : 2,
        title : '成绩',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '95%', '95%' ],
        content :contextPath+"/student/schoolScoreStudent/schoolScore?studentid="+studentid+"&courseid="+courseid// iframe的url
    });
}
