
var prefix =contextPath+  "/region/roomArrange"
$(function() {
	load();
	kSrw();
});
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
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listCheck", // 服务器数据的加载地址
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
						singleSelect : true, // 设置为true将禁止多选
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
								exam_taskid:$("#searchName").val(),
								type:$("#type").val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						onClickRow : function(row, $element){
							xuanze(row.code,row.name,)
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								/* {
									checkbox : true
								}, */
									{
									field : 'code', 
									title : '地州市代码' 
								},
										{
									field : 'name', 
									title : '地州市名称' 
								},
										{
									field : 'code1', 
									title : '县区代码' 
								},
										{
									field : 'name1', 
									title : '县区名称' 
								},
										{
									field : 'num', 
									title : '科次' 
								},
										]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
	
	setTimeout(aaa,500) 
	setTimeout(zongji,500) 
	
}
function aaa() {
	var rowsData = $('#exampleTable').bootstrapTable('getData');
	//console.log(rowsData)
	textX(rowsData[0].name,rowsData[0].code)
}

function xuanze(code,name){
	textX(name,code)
	//$('#exampleTable').bootstrapTable('refresh');
}


function textX(name,code){
	//console.log(code)
	var rowsData = $('#exampleTable').bootstrapTable('getData');
	var num=0;
	for(var i=0;i<rowsData.length;i++){
		if(name==rowsData[i].name){
			num=num+parseInt(rowsData[i].num)
		}
	}
	var text = code+' '+name+' 共 '+num+' 科次'
	$("#text").val(text)
}



function zongji(){
	var rowsData = $('#exampleTable').bootstrapTable('getData');
	var num=0;
	for(var i=0;i<rowsData.length;i++){
		num=num+parseInt(rowsData[i].num)
	}
	$("#num").text(num)
}
