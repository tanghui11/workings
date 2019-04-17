var prefix =contextPath+"student/student/seachStuSubjectlist";
$(function() {
	var url = window.location.href;
	var studentid = url.split("showAudit/")[1].split("?")[0];
	var auditStatus =  decodeURI(url.split("showAudit/")[1].split("=")[1]);
	$("#studentid").val(studentid);
	$("#auditStatus").val(auditStatus);
	load(); 
	phoneHX();
});
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix, // 服务器数据的加载地址
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
					            name:$('#searchName').val(),
								studentid:$("#studentid").val(),
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
										field : 'checked', 
										checkbox : true, 
										formatter : function stateFormatter(value, row, index) { 
											return { 
												checked : true//设置选中 
											}; 
									} 
								}, 
									{
									field : 'id', 
									title : '序号',	
									formatter: function (value, row, index) {
										return index+1;
									}
								},
									{
									field : 'gradSpecialityid', 
									title : '专业代码' 
								},
									{
									field : 'classify', 
									title : '专业层次' 
								},
									{
									field : 'type', 
									title : '助学方式' 
								},
									{
									field : 'educateLength', 
									title : '学制' 
								},
									{
									field : 'teachName', 
									title : '主考院校' 
								},
									{
									field : 'collegeName', 
									title : '学院' 
								},
									{
									field : 'schoolName', 
									title : '助学组织名称' 
								},
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'auditStatus', 
									title : '审核状态' 
								},
									/*  {
									title : '操作',
									field : 'id',
									align : 'center',
									width : '130px',
									 formatter : function(value, row, index) {
											var e = '<a class="btn btn-primary btn-sm ss" style="display:none" href="#" mce_href="#" title="编辑" onclick="check(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> '
											
											
											$('.ss').click(check(row.id));
											return e ;
											} 
											
								}  */ ]
					});
}
 $().ready(function() {
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	SH($("#studentid").val(),$("#auditStatus").val());
}
function SH(id,iauditStatus) {
	layer.confirm('您确定要审核吗？', {
		btn : [ '确定', '取消' ]//按钮
	}, function(index) {
		layer.close(index);
		single (id,"b");
	
   }); 
}
function single (ids,auditStatus){
	$.ajax({
		type : 'POST',
		url : contextPath + '/student/student/updateAudit',
		data : {
			"id" : ids,
			"auditStatus":auditStatus
		},
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				parent.reLoad();
				parent.layer.closeAll();				
			} else {
				layer.msg(r.msg);
			}
		}
	});
}
function cancalAudit(){
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
	var studnetid = parent.hangId;
	if(parent.auditStatus=="待审核"){
		layer.msg("已经是取消审核状态")
	}else{
		$.ajax({
			type : 'POST',
			url : contextPath + '/student/student/updateAudit',
			data : {
				"ids" : ids,
				"id":studnetid,
				"auditStatus":"a"
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index); 
					
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名称"
			}
		}
	})
}
//照片回显
function phoneHX(){
	var src = $("#pic").val()+'?number='+Math.random();//url不变参数变
	$("#scPhone").attr("src",src)
	var url = $("#idcardPic").val()
	$("#id_img_pers").attr("src",url)
}
//上传
function upload(){
	layer.open({
		type : 2,
		title : "上传",
		maxmin : true,
		shadeClose : false, 
		area : [ '600px', '90%' ],
		content : contextPath+"/common/uploadPhoto"
	});
}