var prefix = contextPath+"/student/schoolScoreStudent"
$(function() {
    college();
	load();

});
function yemian(){
    layer.open({
        type : 2,
        title : "报考专业",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
    });
}
//学院名称数据
function college(){
	$.ajax({
		url : contextPath+"/school/collegeSchool/listCollege",
		type : "get",
		 async:false,//更改为同步
		success : function(data) {
			for (var i =0; i < data.length; i++) {
				$("#collegeid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
			firstV= $("#collegeid option:first").val();
			teachPoint(firstV);
		}
	});
	teachPoint('')
}
$("#collegeid").on("change",function(){
	var val = $("#collegeid option:selected").val()
	teachPoint(val)
	$("#schoolSpecialityRegid").val("")
	$("#schoolSpecialityRegid2").val("")
})
//教学点数据
function teachPoint(collegeid){
	if(collegeid==''){
		$("#teachSiteid").find("option").remove();//首先清空下拉数据
		$("#teachSiteid").append("<option value=''>请选择教学点</option>");
		return;
	}else{
		$.ajax({
			url : contextPath+"/school/teachSite/listTeachSite",
			type : "get",
			data:{
				collegeid:collegeid
			},
			success : function(data) {
				$("#teachSiteid").find("option").remove();//首先清空下拉数据
				$("#teachSiteid").append("<option value=''>请选择教学点</option>");
				for (var i =0; i < data.length; i++) {
					$("#teachSiteid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				}
			}
		});
	}
}
/* function yemian(){
	layer.open({
		type : 2,
		title : "报考专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/school/schoolSpecialityRegSchool/intoPage"
	});
}  */
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/listQu", // 服务器数据的加载地址
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
						height: ($(document).height() - 210),
						queryParams : function(params) {
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                    collegeid:$('#collegeid').val(),
                                    teachSiteid:$('#teachSiteid').val(),
                                    specialityRecordid:$('#specialityRecordid').val(),
                                    classify:"b"
                                };
						},
						columns : [
								{
									radio : true
								},
									{
									field : 'id', 
									title : '准考证号' 
								},
									
										{
									field : 'name', 
									title : '姓名' 
								},
									

									{
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="查看" onclick="chakan(\''+ row.id + '\',\''+ row.name + '\')">查看考生成绩</a> ';
                                    return e;
                                }
								} ]
					});
					
					
	$('#exampleTable2')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url : contextPath + "/student/schoolCourseScoreInput/listSchoolCourse", // 服务器数据的加载地址
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
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						height: ($(document).height() - 210),
						queryParams : function(params) {
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
									type:"a",
                                    schoolSpecialityRegid:$('#schoolSpecialityRegid2').val()
                                };
						},
						columns : [
								{
									radio : true
								},
								{
									field : 'id', 
									title : '序号',
									formatter : function(value, row, index) {
										return index+1;
									}
								},
									{
									field : 'courseid', 
									title : '课程代码' 
								},
									
										{
									field : 'courseName', 
									title : '课程名称' 
								}]
					});
}
function reLoad() {
    if($("#collegeid").val()=="" || $("#teachSiteid").val()==""){
        layer.msg("请选择教学点！")
        return;
    }
    if($("#teachSiteid").val()==""){
        layer.msg("请选择专业名称！")
        return;
    }
	if($("#schoolSpecialityRegid").val()==""){
        layer.msg("请选择专业招生！")
        return;
    }
	$('#exampleTable').bootstrapTable('refresh',{url :contextPath + "/student/studentFileStudent/listQu"});
	$('#exampleTable2').bootstrapTable('refresh',{url : contextPath + "/student/schoolCourseScoreInput/listSchoolCourse"});
}
function luru(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请先选择左侧要录入的考生！");
		return;
	}
	var rows2 = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows2.length == 0) {
		layer.msg("请选择右侧要录入的课程！");
		return;
	}
	if($("#chengji").val()==''){
		 layer.msg("请先输入成绩！")
		 return;
	}
	
	$.ajax({
		url : contextPath+"/student/schoolScoreStudent/save",
		type : "post",
		data : {
			'studentid' : rows[0].id,
			'specialityRecordid' : $("#specialityRecordid").val(),
			'courseid' : rows2[0].courseid,
			'grade' : $("#chengji").val(),
		},
		success : function(r) {
			layer.msg(r.msg)
		}
	});
}
function chakan(id,name) {
	$("#name_id").val(name)
	layer.open({
		type : 2,
		title : "查看考生成绩",
		maxmin : true,
		shadeClose : false, 
		area : [ '80%', '80%' ],
		content : contextPath + '/student/schoolScoreStudent/schoolScoreList?studentid='+id+"&&specialityRecordid="+$("#specialityRecordid").val()
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
			ids[i] = row['studentSpecialityid'];
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