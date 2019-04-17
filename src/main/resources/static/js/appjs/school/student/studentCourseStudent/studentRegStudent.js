
var prefix = contextPath+"/student/studentRegStudent"
$(function() {
	load();
});

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
						url : contextPath+"/student/studentCourseStudent/listCourse", // 服务器数据的加载地址
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
                                examTaskid:parent.$("#task").val(),
                                specialityRecordid:$("#specialityRecordid").val()
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
								},
										 ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function add(){
	var stu= parent.$('#exampleTable').bootstrapTable('getSelections')
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择需要报考的课程！");
		return;
	}
    if (stu.length == 0) {
        layer.msg("请选择报考的学生！");
        return;
    }
	var arr=[];
	var arr2=[];
	var nary;
	var nary2;
	for(var i=0;i<rows.length;i++){
		arr.push(rows[i].examDate);
		arr2.push(rows[i].segment);
	}
	nary=arr.slice().sort();
	nary2=arr2.slice().sort();
	for(var i=0;i<arr.length;i++){
		if (nary[i]==nary[i+1]&&nary2[i]==nary2[i+1]){
			//alert("数组重复内容："+nary[i]+nary2[i]);
			layer.msg("同一日期同一时间不能报考！");
			return;
		}
	}
    $.ajax({
        cache : true,
        type : "POST",
        url : contextPath+"/student/studentCourseStudent/save",
        data :{
        "teachid":parent.$("#teachSiteid").val(),
           "stuList": JSON.stringify(stu),
			"courseList":JSON.stringify(rows)
		} ,// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {

			if(data.msg=="已报考"){
                parent.layer.msg("已报考,或已有成绩");

            }else{
                parent.layer.msg("报考成功");
			}

            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引*/


            $("#history").show();
        }
    }); 
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
			




