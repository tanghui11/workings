var prefix = contextPath+ "/student/studentSpeciality";
$().ready(function() {
	/* $("#name").val(parent.names)
	$("#specialityName").val(parent.specialityids+" "+parent.specialityNames);

	update(); */
	load();
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "student/studentSpeciality/applyExcelScore",
		data : {
			"studentid":parent.student,
			"specialityRecordid":parent.$("#specialityRecordid").val()
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log(data)
			$("#certificateNo").html(data[0].certificateNo);
			$("#name").html(data[0].name);
			$("#gender").html(data[0].gender);
			$("#homeType").html(data[0].homeType);
			$("#nation").html(data[0].nation);
			$("#politics").html(data[0].politics);
			$("#studentid").html(data[0].studentid);
			$("#classify").html(data[0].classify);
			$("#specialityName").html(data[0].specialityName);
			$("#grad_certificate").html(data[0].grad_certificate);
			$(".gradAuditDate").html(data[0].gradAuditDate.split(" ")[0]);
			$("#score").html(data[0].score);
			$("#phone").html(data[0].mphone);
			}
	});
});
function update() {
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "school/schoolSpecialityRegSchool/list",
		data : {
			"id":parent.schoolSpecialityRegids,
			"limit":20,
			"offset":0
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			$("#educateLength").val(data.rows[0].educateLength);
			$("#regSeason").val(data.rows[0].regSeason);
			$("#regYear").val(data.rows[0].regYear);
			$("#classify").val(data.rows[0].classify);
			$("#type").val(data.rows[0].type);
		}
	});
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "/student/studentSpeciality/information",
		data : {
"id":parent.idss
},// 你的formid
async : false,
	error : function(request) {
	parent.layer.alert("Connection error");
},
success : function(data) {
	$("#studentid").val(data.studentid);
	$("#gradSchool").val(data.gradSchool)
}
});
}
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url :prefix+ "/schoolPrint", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
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
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit: params.limit,
						offset:params.offset,
						"studentid":parent.student,
						"specialityRecordid":parent.$("#specialityRecordid").val()
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
                    	field : 'id',
                    	title : '序号',
						width:'60px',
					 formatter: function (value, row, index) {
						 return index+1;
					 },
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
						field : 'type',
						title : '类别'
					},
					{
						field : 'sourceCourseName',
						title : '源课程'
					},
					{
						field: 'score',
						title: '成绩'
					}
					/*{
                field : 'dbFlag',
                title : '数据标记'
            },
                        {
                field : 'key',
                title : '识别码'
            },
                 {
                title : '操作',
                field : 'id',
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
            } */]
			});
}

function prints(){
	$("#prints").hide();
	document.getElementsByTagName('body')[0].style.zoom=1.02;
	window.print();
	window.location.reload(); 
	$("#prints").show();
}