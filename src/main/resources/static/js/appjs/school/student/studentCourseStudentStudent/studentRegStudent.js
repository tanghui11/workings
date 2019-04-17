var stuid=""
var prefix = contextPath+"/student/studentRegStudent"
$(function() {
	load();
    task();
});
//考试任务
function task(){
    $.ajax({
        url : contextPath+ "exam/task/serchTaskAll",
        type : "get",
		data:{
			"type":"a",
			"date":"1",
			"confirm_status":"b",
			"audit_status":"b"
		},
        //async:false,//更改为同步
        success : function(data) {
            for (var i =0; i < data.length; i++) {
                $("#task").append("<option value=" + data[i].id + ">" +data[i].examYear+ "年"+data[i].examMonth+"</option>");
            }
        }
    });
}
function yemian(){
	layer.open({
		type : 2,
		title : "报考专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '80%', '80%' ],
		content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
	});
}
var zId;
var id;
var dyID;
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{

						method : 'get', // 服务器数据的请求方式 get or post
					//	url : prefix + "/listStudentStudent", // 服务器数据的加载地址
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
                                name:$("#studentName").val(),
                                studentid:$("#studentid").val(),
                                idcard:$("#idcard").val()
							};
						},

						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						singleSelect : true,// 单选checkbox
						columns : [
								{
									checkbox : true
								},
									{
									field : 'id', 
									title : '序号',
									formatter: function (value, row, index) {
										return index+1;
									}
								},
										{
									field : 'studentid', 
									title : '准考证号' 
								},
										{
									field : 'name', 
									title : '姓名' 
								},	{
									field : 'specialityid',
									title : '专业代码'
								},  {
									field : 'specialityName',
									title : '专业名称'
								}
										],
                        onCheck: function (row) {
                            stuid=row.id
                            $('#exampleTable2').bootstrapTable('refresh',{url: contextPath+"/student/studentCourseStudentStudent/listCourse"});

                        },
					});
		function oneByO(id){
			dyID = id;
			reLoad2();
		}
		$('#exampleTable2')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
					//	url : contextPath+"/student/studentCourseStudentStudent/listCourse", // 服务器数据的加载地址
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
                                examTaskid:$("#task").val(),
								id:stuid
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						//singleSelect : true,
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
									field : 'courseid',
									title : '课程代码'
								},
										{
									field : 'courseName',
									title : '课程名称'
								},
										{
									field : 'examDate',
									title : '考试日期'
								},
										{
									field : 'segment',
									title : '时间段'
								},	{
									field : 'sfbk',
									title : '是否报考',
									formatter: function (value, row, index) {
										if(row.sfbk>0){
											return "已报考"
										}else{
											return "未报考"
										}
									}
								},	{
									field : 'grade',
									title : '考试成绩'
								}
										 ]
					});
}
function reLoad() {
	var nameV = $("#studentName").val();
	var idV = $("#studentid").val();
	var idcard = $("#idcard").val();
	var task = $("#task").val();
	if(nameV==''&&idV==''&&idcard==''){
		layer.msg("姓名、准考证号、身份证号必须输入一个！");
	}else{
		if(task==''){
			layer.msg("请选择考试任务！");
		}else{	
			$('#exampleTable').bootstrapTable('refresh',{url: prefix + "/listStudentStudent"});
		}
	}
}
function reLoad2() {
	$('#exampleTable2').bootstrapTable('refresh',{url: contextPath+"/student/studentCourseStudentStudent/listCourse"});
}
function add(){
	var stu= $('#exampleTable').bootstrapTable('getSelections');
    var rows = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择需要报考的课程！");
		return;
	}
	var examCourseid = new Array();
    var sfbk = new Array();//是否报考字段
	var courseName = new Array();//课程名称
	var grade = new Array();//考试成绩
	var index = new Array();
	
	for ( var i =0;i<rows.length; i++) {
		sfbk[i] = rows[i].sfbk
		if(sfbk[i]>0){
			//index2.push(i+1)
		}else{
			index.push(i)
			courseName.push(rows[i].courseName)
		}
		examCourseid.push(rows[i].examCourseid)
	}
	if(index.length==0){
		layer.alert("勾选的已经全部报考！不能重复报考！")
		return;
	}else if(index.length==rows.length){
		layer.confirm('您确定要批量报考吗？', {
			btn : [ '确定', '取消' ]
		}, function() {
			//console.log(sfbk)
			$.ajax({
				cache : true,
				type : "POST",
				traditional: true,//这里设置为true
				url : contextPath+"/student/studentCourseStudentStudent/save",
				data :{
				"examCourseid":examCourseid,
                 "childRegionid":stu[0].childRegionid,
                  "regionid":stu[0].regionid,
				 "studentid":stu[0].studentid
				} ,// 你的formid
				async : false,
				error : function(request) {
					parent.layer.alert("Connection error");
				},
				success : function(data) {
					parent.layer.msg("批量报考完成");
					reLoad2();
					
					layer.close(layer.index)
				   // $("#history").show();
				}
			});
		})
	}else{
		layer.alert("( "+courseName.toString()+" )未报考！<br/>请单独选择进行报考！其他课程已经报考！")
		return;
	}
	
	
}
function history(){
    layer.open({
        type : 2,
        title : "未报考人员名单",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentCourseStudent/studentNoExam"
    });
}
//注册
function save() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择需要注册的信息！");
		return;
	}
	layer.confirm('您确定要为该生添加注册信息吗？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({  //判断注册信息重复报考
				type : "get",
				url : contextPath+"/student/studentRegStudent/selectYear",
				data:{
					'studentSpecialityid':zId
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
				studentSpecialityid : zId
			},
			success : function(data) {
				reLoad2();
				layer.close(layer.index)//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
				layer.msg("注册成功！");
			}
		});
	}

}
	
function batchRemove() {
	var rows = $('#exampleTable2').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消的报考！");
		return;
	}
	layer.confirm("确认要取消选中的'" + rows.length + "'条报考信息吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		var grade = new Array();//考试成绩
		var gradeIndex = new Array();//以有考试成绩length
		var gradeName = new Array();//已有成绩的课程
		var courseName = new Array();//未报考的专业名称
		var index = new Array();//未报考
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['sfbk'];
			grade[i] = row['grade'];
			if(ids[i]>0){
				if(grade[i]>0){
					gradeIndex.push(i);
					gradeName.push(rows[i].courseName);
				}
				
			}else{
				index.push(i);
				courseName.push(rows[i].courseName);
			}
		});
		if(index.length!=0){
			layer.alert("选择的含有'未报考'课程！不能取消报考！")
			return;
		}else{
			if(gradeIndex.length!=0){
				layer.alert("( "+gradeName.toString()+" ) <br/>已有考试成绩！不能取消报考！")
				return;
			}else{
				$.ajax({
					type : 'POST',
					data : {
						"ids" : ids
					},
					url : contextPath+'/student/studentCourseStudent/batchRemove',
					success : function(r) {
						if (r.code == 0) {
							layer.msg(r.msg);
							reLoad2();
						} else {
							layer.msg(r.msg);
						}
					}
				});
			}
		}
			/* $.ajax({
				type : 'POST',
				data : {
					"ids" : ids
				},
				url : contextPath+'/student/studentCourseStudent/batchRemove',
				success : function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
						reLoad2();
					} else {
						layer.msg(r.msg);
					}
				}
			}); */
		
	}, function() {

	});
}






