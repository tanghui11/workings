
var prefix =contextPath+  "/region/seatArrangeScore"
$(function() {
	 load(); 
	 kSrw();
});
function flag(){
	if($('#flag').is(':checked')){
		$('#flag').val("1")
	}else{
		$('#flag').val("0")
	}
}
//考试任务
function kSrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "exam/task/serchTaskAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option value=''>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
			}
			$("#searchName").html(_html);
		}
	});
}
$("#searchName").on('change',function(){
	$("#search").val("");
	$("#examCourseid").val("");
})
//报考课程
function baokao(){
	if($("#searchName").val()==""){
		layer.msg("请选择考试任务！")
		return;
	}
	layer.open({
		type : 2,
		title : '报考课程',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '400px' ],
		content :  contextPath + 'student/studentCourseRepaire/bkCourse/?examTaskid='+$("#searchName").val() // iframe的url
	});
}
//录入
function luru(){
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if(rows.length==0){
		layer.msg("请先查询并选择下列数据");
		return;
	}
	var studentid= rows[0].准考证号;
	if(rows[0].成绩||rows[0].成绩==0){
		layer.msg("成绩已存在，请修改！");
		return;
	}
	var id= rows[0].id;
	if($("#chengji").val()==""){
		layer.msg("请输入成绩！");
		return;
	}
	if($('#flag').is(':checked')){
		$("#chengji").val('0')
	}
	$.ajax({
		cache : true,
		type : "post",
		url :prefix+ "/saveScoreSingle",// 你的formid
		data:{
			"objid":id,
			"studentid":studentid,
			"grade":$("#chengji").val(),
			"courseid":$("#bkkcCourseid").val(),
			"examFlag": $("#flag").val()
		},
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			layer.msg("录入成功！");
			reLoad();
		}
	});
}
//修改
function xiuGai(){
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if(rows.length==0){
		layer.msg("请先选择要修改的数据！");
		return;
	}
	var studentid= rows[0].准考证号;
	var id= rows[0].id;
	if($("#chengji").val()==""){
		layer.msg("请输入成绩！");
		return;
	}
	if($('#flag').is(':checked')){
		$("#chengji").val('0')
	}
	$.ajax({
		cache : true,
		type : "post",
		url :prefix+ "/updateScoreSingle",// 你的formid
		data:{
			"objid":id,
			"studentid":studentid,
			"grade":$("#chengji").val(),
			"courseid":$("#bkkcCourseid").val(),
			"examFlag": $("#flag").val()
		},
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			layer.msg("修改成功！");
			reLoad();
		}
	});
}

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listSingle", // 服务器数据的加载地址
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
					            examTaskid:$('#searchName').val(),
								courseid:$('#bkkcCourseid').val()
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
									width : '50px',
									formatter: function (value, row, index) {
										return index+1;
									}
								},
									{
									field : '准考证号', 
									title : '准考证号' ,
									
								},
								{
									field : '姓名', 
									title : '姓名' ,
									
								},
								{
									field : '课程代码', 
									title : '课程代码' ,
									
								},
								{
									field : '课程名称', 
									title : '课程名称' ,
									
								},{
									field : '考点代码', 
									title : '考点代码' ,
									
								},{
									field : '考点名称', 
									title : '考点名称' ,
									
								},
								{
									field : '考场号', 
									title : '考场号' ,
									
								},
								{
									field : '座位号', 
									title : '座位号' ,
									
								},
								{
									field : '考试时间', 
									title : '考试时间' ,
									
								},
								{
									field : '是否缺考', 
									title : '是否缺考' ,
									width :'120px',  
									editable: {
											type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
											source: [{ value: "无", text: "不缺考" }, { value: "缺考", text: "缺考" }],
											disabled: false,           //是否禁用编辑
											showbuttons:false,
											emptytext: "空文本",       //空值的默认文本
											mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
											validate: function (value) { //字段验证
												if (!$.trim(value)) {
													return '不能为空';
												}
											}
									}
								},
								{
									field : '成绩', 
									title : '成绩' ,
									width :'120px',
										formatter : function(value, row, index) {
										if(row.成绩 == null || row.成绩 == ""){
											return '0';
										}else{
											return row.成绩;
										}
									},
									editable: {
										type: 'text',
										title: '成绩',
										validate: function (v) {
											if (!$.trim(v)) {
												return '不能为空';
											}
											if(0>parseInt($.trim(v))||parseInt($.trim(v))>100){
												return '只能1-100';
											}
										}
									}
									
								}
									],
							onEditableSave: function (field, row, oldValue, $el) {
									var kao = row.成绩
									if(row.是否缺考=="缺考"){
										kao=0
									}
									
									//console.log(row)
									$.ajax({
										type: "post",
										url: prefix+ "/updateScoreSingle",
										data:{
											"objid":row.id,
											"studentid":row.准考证号,
											"grade":kao,
											"courseid":$("#bkkcCourseid").val(),
											"examFlag": row.是否缺考=="缺考"?"1":"0"
										},
										dataType: 'JSON',
										success: function (data,status) {
											
										
											if (status == "success" && data) {
												layer.msg('修改成功！');
											}
											if(row.是否缺考=="缺考"){
												layer.msg('缺考分数为0！');
											}
										},
										error: function () {
										   layer.msg("编辑失败！")
										},
										complete: function () {

										}
									});
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');/* ,{url : prefix + "/listSingle"} */
}

