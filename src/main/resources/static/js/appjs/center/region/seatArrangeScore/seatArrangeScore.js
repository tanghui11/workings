
var prefix =contextPath+  "/region/seatArrangeScore"
$(function() {
	 load(); 
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
					//	url : prefix + "/list", // 服务器数据的加载地址
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
					            secretCode:$('#secretCode').val(),
					           firstStudentid:$('#firstStudentid').val()
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
									title : '序号' ,
									width : '50px',
									formatter: function (value, row, index) {
										return index+1;
									}
								},
									{
									field : 'seq', 
									title : '座位号' ,
									width : '60px'
								},
									{
									field : 'quekao', 
									title : '是否缺考' ,
									formatter : function(value, row, index) {
										if(row.message=="1"){
											if(row.examFlag=="y"){
												var str ="<input type='checkbox' onclick='quekao(this)' ondblclick='quqk(this)' checked='checked'/><input type='hidden' name='scoreTempList["+index+"].examFlag1' value='"+row.examFlag+"'/><input type='text' name='scoreTempList["+index+"].seatArrangeid' value='"+row.id+"' style='display:none'/>";
											}else{
													var str ="<input type='checkbox' onclick='quekao(this)' ondblclick='quqk(this)'/><input type='hidden' name='scoreTempList["+index+"].examFlag1' value='n'/><input type='text' name='scoreTempList["+index+"].seatArrangeid' value='"+row.id+"' style='display:none'/>";
											}
										}else{
											if(row.examFlag=="y"){
												var str ="<input type='checkbox' onclick='quekao(this)' ondblclick='quqk(this)' checked='checked'/><input type='hidden' name='scoreTempList["+index+"].examFlag2' value='"+row.examFlag+"'/><input type='text' name='scoreTempList["+index+"].seatArrangeid' value='"+row.id+"' style='display:none'/>";
											}else{
													var str ="<input type='checkbox' onclick='quekao(this)' ondblclick='quqk(this)'/><input type='hidden' name='scoreTempList["+index+"].examFlag2' value='n'/><input type='text' name='scoreTempList["+index+"].seatArrangeid' value='"+row.id+"' style='display:none'/>";
											}
										}
										return str;
									},
									width : '100px'
									
								},
									{
									field : 'cj', 
									title : '成绩' ,
									formatter : function(value, row, index) {
										if(row.message=="1"){
											if(row.grade==null){
												var str ='<input type="text" name="scoreTempList['+index+'].grade1" value=""  class="form-control" style="width:200px" onkeyup="keyups(this.value)" onblur="blurs(this.value)">';
											}else{
												var str ='<input type="text" name="scoreTempList['+index+'].grade1" value="'+row.grade+'"  class="form-control" style="width:200px" onkeyup="keyups(this.value)" onblur="blurs(this.value)">';
											}
										}else{
											if(row.grade==null){
												var str ='<input type="text" name="scoreTempList['+index+'].grade2" value=""  class="form-control" style="width:200px" onkeyup="keyups(this.value)" onblur="blurs(this.value)">';
											}else{
												var str ='<input type="text" name="scoreTempList['+index+'].grade2" value="'+row.grade+'"  class="form-control" style="width:200px" onkeyup="keyups(this.value)" onblur="blurs(this.value)">';
											}
										}
										
										
										return str;
									}
								},
										/* {
									field : 'roomArrangeid', 
									title : '考场编排编号' 
								},
										{
									field : 'studentCourseid', 
									title : '考生报考编号' 
								},
										{
									field : 'examCourseid', 
									title : '开考课程编号' 
								}, 
									{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
										{
									field : 'dbFlag', 
									title : '数据标记' 
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
function reLoad() {
	if($("#secretCode").val()!=""&&$("#firstStudentid").val()!=""){
		$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list"});
	}else{
		layer.msg("请输入保密号和首考号末俩位");
	}
	
}
function add() {
		$.ajax({
				cache : true,
				type : "POST",
				url : contextPath+ "/region/seatArrangeScore/saveScore",
				data : $('#signupForm').serialize(),// 你的formid
				async : false,
				error : function(request) {
					parent.layer.alert("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						parent.layer.msg("操作成功");
						reLoad();
					} else {
						parent.layer.alert(data.msg)
					}

				}
			}); 
}
function edit(id) {

    location.href =  prefix + '/edit/' + id ;
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
//缺考
function quekao(that){
	if($(that).siblings("input").eq(0).val()=="y"){
		$(that).siblings("input").eq(0).val("n")
	}else{
		$(that).siblings("input").eq(0).val("y")
	}
	
}
//取消缺考
function quqk(that){
	 $(that).attr("checked",false);
	$(that).siblings("input").eq(0).val("n")
}
function keyups(val){
	/* var reg = new RegExp("^(100|([1-9][0-9]{0,1}|[0-9])(?:\.5|))$");
	if(!reg.test(val)){
		layer.msg("请输入正确的分数")
	} */
}
function blurs(val){
	
	var reg =  /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/;
	if(val==""){
		return;
	}
	if(!reg.test(val)){
		layer.msg("请输入正确的分数")
	}
}