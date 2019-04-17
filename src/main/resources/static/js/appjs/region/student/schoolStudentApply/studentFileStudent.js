var prefix = contextPath+"/student/studentRegion"
$(function() {
    college();
	load();
	load1();

});
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
function yemian(){
	layer.open({
		type : 2,
		title : "报考专业",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/plan/specialityRecord/showSubject"
	});
} 
var student=null;
var myName=null;
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
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                   /*  collegeid:$('#collegeid').val(),
                                    teachSiteid:$('#teachSiteid').val(),
                                    specialityRecordid:$('#specialityRecordid').val(),
                                    classify:"b" */
									id:$("#studentid").val()
                                };
						},
						columns : [
								{
									checkbox : true
								},
									{
									field : 'studentid',
									title : '准考证号' ,
									width:'120px'
								},
								{
									field : 'name',
									title : '姓名' ,
									formatter : function(value, row, index) {
										 myName =row.name;
										 return myName
									} 
								},
								{
									field : 'gender',
									title : '性别' 
								},
								{
									field : 'nation',
									title : '名族' 
								},{
									field : 'profession',
									title : '职业' 
								},
								{
									field : 'birthday',
									title : '出生日期' 
									
								},
								{
									field : 'certificateType',
									title : '证件类别' 
								},
								{
									field : 'certificateNo',
									title : '证件号码' 
								},
								{
									field : 'mphone',
									title : '联系电话' 
								},
								{
									field : 'address',
									title : '联系地址' 
								},
								{
									field : 'status',
									title : '状态' 
								},
								/* {
									field : 'status',
									title : '审核状态' 
								}, */
								
								/* {
								field : 'specialityid',
								title : '专业代码'
							},{
								field : 'specialityName',
								title : '专业名称'
							},
							{
								field : 'graduate',
								title : '申请状态'
							},{
								field : 'gradSchool',
								title : '原毕业院校'
							},{
								field : 'gradCertificate',
								title : '原毕业证书编号'
							},{
								field : 'gradSpecialityid',
								title : '原专业代码'
							},
							{
								field : 'gradSpecialityName',
								title : '原专业名称'
							},
							{
								field : 'education',
								title : '原学历'
							}, */
							{
                                title : '操作',
                                field : 'id',
                                align : 'center',
								width : '210px',
                                formatter : function(value, row, index) {
                                    var d = '<a class="btn btn-info btn-sm" href="#" title="删除"  mce_href="#" onclick="edits(\''
                                        + row.studentid
                                        + '\')">更正考生信息</a> ';
									var e = '<a class="btn btn-success btn-sm" href="#" title="更改报考专业"  mce_href="#" onclick="alter(\''
                                        + row.studentid+"','"+row.name
                                        + '\')">更改报考专业</a> '; 
                                    return e + d;
                                }
								} ],
								onCheck:function (row){
									student = row.studentid;
								}
					});
}
var specialityRecordid = null;
function load1() {
	$('#exampleTable1')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						//url :contextPath+"student/studentSpeciality/list", // 服务器数据的加载地址
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
						/* height: ($(document).height() - 210), */
						queryParams : function(params) {
                                return {
                                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                    limit: params.limit,
                                    offset:params.offset,
                                   /*  collegeid:$('#collegeid').val(),
                                    teachSiteid:$('#teachSiteid').val(),
                                    specialityRecordid:$('#specialityRecordid').val(),
                                    classify:"b" */
									studentid:$("#studentid").val(),
                                };
						},
						columns : [
							{
								checkbox : true
							},
							{
								field : 'gradSchool',
								title : '毕业院校' ,
										formatter: function (value, row, index) {
											specialityRecordid = row.specialityRecordid;
                                            return  row.gradSchool
										},
								},
								{
									field : 'grad_certificate',
									title : '毕业证书号' 
								},
								{
									field : 'gradSpecialityid',
									title : '原学专业代码' 
								},
								{
									field : 'gradSpecialityName',
									title : '原学专业名称' 
								},
								{
									field : 'education',
									title : '原学历' 
								},{
									field : 'specialityid',
									title : '专业代码' 
								},
								{
									field : 'specialityName',
									title : '报考专业' 
								},
								{
									field : 'auditStatus',
									title : '审核状态' 
								},
								{
									field : 'status',
									title : '状态' 
								},
								{
									field : 'graduate',
									title : '毕业申请状态' 
								},	
							{
                                title : '操作',
                                field : 'id',
                                align : 'center',
								width : '270px',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                        + row.studentid+"','"+myName+"','"+row.id
                                        + '\')">修改报考信息</a> ';
                                   /*  var d = '<a class="btn btn-info btn-sm" href="#" title="删除"  mce_href="#" onclick="edits(\''
                                        + row.studentid
                                        + '\')">更正考生信息</a> '; */
                                     var g = '<a class="btn btn-success btn-sm"'
                                         +'style="margin-right:5px"'
                                         + '" href="'+contextPath + 'student/studentSpeciality/pageInto?studentid='+row.studentid+'&specialityRecordid='+$("#specialityRecordid").val()+"&collegeid="+$("#collegeid").val()+"&teachSiteid="+$("#teachSiteid").val()
                                         + '" data-index="examReg"'
                                         + ' data-text="专业课程"'
                                         + ' data-refresh="true"'
                                         + ' onclick="parent.menuItem(this);return false;"'
                                         + ' data-id="examReg">专业课程</a>';
										       + '\')">修改报考信息</a> ';
                                    var f = '<a class="btn btn-info btn-sm" href="#" title="成绩补录"  mce_href="#" onclick="gradeEntering(\''
                                        + row.studentid+"','"+row.id+"','"+row.specialityRecordid
                                        + '\')">成绩补录</a> ';
                                    return e+f+g;
                                }
								} ],
								onCheck:function (row){
									student = row.studentid;
								}
					});
}
//成绩补录
var studentidss = null;
var id1 = null;
var specialityRecordid1 = null;
function gradeEntering(studentid,id,specialityRecordid){
	studentidss= studentid;
	id1 =id;
	specialityRecordid1 = specialityRecordid;
	layer.open({
		type : 2,
		title : "成绩补录",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : contextPath+"/student/studentSpecialityApply/regionInsertPage"
	});
}
//更改报考专业
var studentid1 = null;
var name1 = null;
function alter(studentid,name){
	studentid1 = studentid;
	name1 = name;
	layer.open({
		type : 2,
		title : "更改报考专业",
		maxmin : true,
		shadeClose : false, 
		area :[ '80%', '80%' ],
		content : contextPath+'student/studentSpecialityApply/specialityAdd/'
	});
}
function reLoad() {
   /*  if($("#collegeid").val()=="" || $("#teachSiteid").val()==""){
        layer.msg("请选择教学点！")
        return;
    }
    if($("#collegeid").val()=="" || $("#teachSiteid").val()=="" || $("#specialityName").val()==""){
        layer.msg("请选择专业名称！")
        return;
    } */
	if($("#studentid").val()==""){
        layer.msg("请输入准考证号")
        return;
    }
	$('#exampleTable').bootstrapTable('refresh',{url : contextPath + "student/studentSpecialityApply/selectStudent"});
	$('#exampleTable1').bootstrapTable('refresh',{url :contextPath+"student/studentSpeciality/listStudentSpeciality"});	
}
function reLoad1() {
	$('#exampleTable1').bootstrapTable('refresh',{url :contextPath+"student/studentSpeciality/list"});
}
function add() {
	layer.open({
		type : 2,
		title : "修改报考信息",
		maxmin : true,
		shadeClose : false, 
		area : [ '90%', '90%' ],
		content : prefix + '/addApply'
	});
}
var studentids=null;
var names=null;
var ids = null;
function edit(studentid,name,id) {
	studentids=studentid;
	names=myName;
	ids = id;
	layer.open({
		type : 2,
		title : "修改报考信息",
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : contextPath+'student/studentSpecialityApply/addApply/'+id
	});
}
function edits(studentid) {
	layer.open({
		type : 2,
		title : "更正考生信息",
		maxmin : true,
		shadeClose : false, 
		area :[ '800px', '520px' ],
		content : contextPath+'student/studentSpecialityApply/editApply/'+studentid
	});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : "课程",
		//maxmin : true,
		shadeClose : false,
		area : [ '95%', '95%' ],
		content : contextPath + 'student/studentSpeciality/pageInto'
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


//申请

function shape(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要申请的数据");
		return;
	}
	layer.confirm("你确定要提交该考生的毕业申请信息?", {
		btn : [ '是', '否' ]
	// 按钮
	}, function() {
		$.ajax({
			type : 'POST',
			data : {
				"studentid" : student,
				"specialityRecordid":$("#specialityRecordid").val(),
				"graduate":"b"
			},
			url : contextPath+"/student/studentSpeciality/apply",
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					
				} else {
					layer.msg(r.msg);
					reLoad();
				}
			}
		});
	}, function() {

	});
}
//取消申请
function qxsh(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消申请的数据");
		return;
	}
	layer.confirm("你确定要取消该考生的毕业申请信息?", {
		btn : [ '是', '否' ]
	// 按钮
	}, function() {
		$.ajax({
			type : 'POST',
			data : {
				"studentid" : student,
				"specialityRecordid":$("#specialityRecordid").val(),
				"graduate":"a"
			},
			url : contextPath+"/student/studentSpeciality/apply",
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					
				} else {
					layer.msg(r.msg);
					reLoad();
				}
			}
		});
	}, function() {

	});
}
//打印申请表
function dysq(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要申请的数据");
		return;
	}
	layer.open({
		type : 2,
		title : "打印申请表",
		//maxmin : true,
		shadeClose : false,
		area : [ '100%', '100%' ],
		content : contextPath + 'student/studentSpecialityApply/printPage'
	});
}