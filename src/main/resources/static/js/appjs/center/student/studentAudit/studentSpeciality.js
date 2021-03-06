
var prefix = contextPath+ "/student/studentSpeciality"
$(function() {
	times();
	$("#type").val("a");
	bianhua();
	 $("#jstree").height($(document).height() - 120);
	load();
});
var specialityRecord=null;
var students =null;
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listStudentAudit", // 服务器数据的加载地址
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
								type:$("#type").val(),
								years:$("#years").val(),
								time:$("#time").val(),
								gradAuditStatus:$("#gradAuditStatus").val(),
								schoolid:$("#schoolid").val(),
								collegeid:$("#collegeid").val(),
								teachSiteid:$("#teachSiteid").val(),
								regionid:$("#regionid").val(),
								childRegionid:$("#childRegionid").val(),
								specialityid:$("#specialityid").val(),
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
									checkbox : true
								},
									/* {
									field : 'id', 
									title : '序号'
								}, */
									/* {
									field : 'regYear', 
									title : '招生年份' 
								}, */
										{
									field : 'studentid', 
									title : '准考证号' 
								},
									{
									field : 'name', 
									title : '姓名' 
								},
									{
									field : 'gender', 
									title : '性别' 
								},
									{
									field : 'certificate_no', 
									title : '证件号码' 
								},
									{
									field : 'specialityid', 
									title : '专业代码' 
								},
								{
									field : 'specialityName', 
									title : '专业名称' 
								},
								{
									field : 'direction', 
									title : '方向' 
								},
										{
									field : 'gradSchool', 
									title : '毕业院校' 
								},
										
								{
									field : 'gradCertificate', 
									title : '毕业证书号' 
								},
										/* {
									field : 'gradSpecialityid', 
									title : '原学专业编号' 
								}, */
									{
									field : 'collegeName', 
									title : '学院' 
								},
									/* {
									field : 'teachName', 
									title : '教学点' 
								}, */
										{
									field : 'education', 
									title : '原学历' 
								},
								
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'graduate', 
									title : '毕业申请状态' 
								},
										/* {
									field : 'printCertificate', 
									title : '打印毕业证' 
								},
										{
									field : 'applyOperator', 
									title : '申请人' 
								}, 
										{
									field : 'applyDate', 
									title : '申请日期' 
								},*/
										{
									field : 'auditStatus', 
									title : '审核状态' 
								},
									/* 	{
									field : 'auditOperator', 
									title : '审核人' 
								},
										{
									field : 'auditDate', 
									title : '审核日期' 
								}, */
										{
									field : 'gradAuditStatus', 
									title : '毕业审核状态' 
								},/* 
										{
									field : 'gradAuditOperator', 
									title : '毕业审核人' 
								},
										{
									field : 'gradAuditDate', 
									title : '毕业审核日期' 
								},
										{
									field : 'oldStudentid', 
									title : '旧准考证' 
								}, */
									/* 	{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								}, */
									/* 	{
									field : 'dbFlag', 
									title : '数据标记' 
								}, */
									{
									title : '操作',
									field : 'id',
									align : 'center',
									width : '145px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary" href="#" mce_href="#" title="查看档案" onclick="check(\''
												+ row.id+"','"+row.name+"','"+row.specialityid+"','"+row.specialityName
												+ '\')">档案</a> ';
										 var d = '<a class="btn btn-warning btn-sm" href="#" title="注册信息"  mce_href="#" onclick="checkRegister(\''
												+ row.id+"','"+row.name+"','"+row.specialityid+"','"+row.specialityName+"','"+row.schoolSpecialityRegid
												+ '\')">注册信息</a> ';
										/* var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';  */
										return e + d ;
									}
								}],
								onCheck:function (row){
									students = row.studentid;
									specialityRecord = row.specialityRecordid
								}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
//查看档案
var ids = null;
function check(id){
	ids=id
	layer.open({
		type : 2,
		title : '查看档案',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '80%' ],
		content :prefix + '/check'  // iframe的url
	})
}
var idss =null;
var names =null;
var specialityids  = null;
var specialityNames  = null;
var schoolSpecialityRegids = null;
function checkRegister(id,name,specialityid,specialityName,schoolSpecialityRegid){
	idss = id;
	names =name;
	specialityids = specialityid;
	specialityNames = specialityName;
	schoolSpecialityRegids = schoolSpecialityRegid;
	layer.open({
		type : 2,
		title : '注册信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '80%' ],
		content :prefix + '/checkRegister'  // iframe的url
	})
}
//注册信息
function add() {
	layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :prefix + '/add'  // iframe的url
	})
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '80%' ],
		content :prefix + '/edit/' + id  // iframe的url
	})
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
		console.log(ids);
		/* $.ajax({
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
		}); */
	}, function() {

	});
}
function exportExcel(src){
    document.getElementById('form').action=src+"?studentid="+$("#studentid").val();

//若需提交表单（如导出）

    document.getElementById('form').submit();
}
//树状图
function getTreeData() {
    $.ajax({
        type: "GET",
        url:contextPath+ "/common/tree/regionTree",
        success: function (tree) {
			loadTree(tree);
        }
    });
}
function bianhua(){  
	if($("#type").val()=="a"){
		$('#jstree').data('jstree', false).empty();
		$(".ibox-title>h5").html("考区列表");
		loadTree(contextPath+ '/common/tree/onlyRegionTree');
	}else if($("#type").val()=="b"){
		$('#jstree').data('jstree', false).empty();
		$(".ibox-title>h5").html("助学组织列表")
		loadTree1(contextPath+'/common/tree/schoolCollegeTeachSiteTree')
	}
}
 function loadTree(url) {
     $("#jstree").on("loaded.jstree", function (event, data) {
                // 展开所有节点
                //$('#jstree').jstree('open_all');
                // 展开指定节点
                data.instance.open_node(0);     // 单个节点 （1 是顶层的id）
               // data.instance.open_node([0, 10]); // 多个节点 (展开多个几点只有在一次性装载后所有节点后才可行）
            }).jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return url;
                },
                "data" : function(node){
                    if(node.id !="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        return {"id" : $('#orgid').val(),"parentid":-1};
                    }
                }
            }
        }
    });
}
function loadTree1(url) {
     $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    /*return contextPath+'/common/tree/schoolCollegeTree';

                     */
                    return url;
                },
                "data" : function(node){
                    if(node.id =="#"){
                        return {};

                    }else if(node.original.attributes.type=="college"){
                        return {"type":"college","schoolid":node.id.split(node.id.substr(0,1))[1]};
                    } else{
                        return {"type":"school","schoolid":node.id.split(node.id.substr(0,1))[1]};
                    }
                }
            }
        }
    });
}
$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
        reLoad();
    } else {
		if($("#type").val()=="a"){
			$('#orgid').val(data.selected[0]);
			if(data.node.original.attributes.type=="a"){
				$("#regionid").val(data.selected[0]);
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="b"){
				$("#regionid").val("");
				$("#childRegionid").val(data.selected[0]);
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}
			//$('#orgType').val(data.node.original.attributes.type);
			reLoad();
		}else if($("#type").val()=="b"){
			$('#orgid').val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			if(data.node.original.attributes.type=="school"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
				$("#collegeid").val("");
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="college"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
				$("#teachSiteid").val("");
			}else if(data.node.original.attributes.type=="teachSite"){
				$("#regionid").val("");
				$("#childRegionid").val("");
				$("#schoolid").val("");
				$("#collegeid").val("");
				$("#teachSiteid").val(data.selected[0].split(data.selected[0].substr(0,1))[1]);
			}
			 //$('#orgType').val(data.node.original.attributes.type);
			 reLoad();
		}
		
   
	}
});

function courseName(){
	  layer.open({
        type: 2,
        title: '专业名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content:contextPath+  "/plan/specialityRecord/showSubject"
    }); 
}
function times(){
	var date = new Date;
	var years = date.getFullYear();
	$("#years").val(years);
}
//审核
function shenghe() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核的数据");
		return;
	}
	layer.confirm("确认要审核选中的'" + rows.length + "'条数据吗?", {
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
				"ids" : ids,
				"gradAuditStatus":"b"
			},
			url : prefix + '/updateAuditStudent',
			success : function(r) {
				//console.log(r)
				if (r.code == 0) {
					//layer.msg(r.msg);
					layer.msg("审核成功！");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		}); 
	}, function() {

	});
}
//取消审核
function qxsh() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消审核的数据");
		return;
	}
	layer.confirm("确认要取消审核选中的'" + rows.length + "'条数据吗?", {
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
				"ids" : ids,
				"gradAuditStatus":"a"
			},
			url : prefix + '/updateAuditStudent',
			success : function(r) {
				//console.log(r)
				if (r.code == 0) {
					//layer.msg(r.msg);
					layer.msg("取消审核成功！");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		}); 
	}, function() {

	});
}
//退回功能
function tuihui() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消审核的数据");
		return;
	}
	layer.confirm("确认要取消审核选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		console.log(ids);
		  $.ajax({
			type : 'POST',
			data : {
				"ids" : ids,
			},
			url : prefix + '/updateGradute',
			success : function(r) {
				//console.log(r)
				if (r.code == 0) {
					//layer.msg(r.msg);
					layer.msg("退回成功！");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		}); 
	}, function() {

	});
}
//打印审批表
function info(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要打印的数据");
		return;
	}
	//window.open(contextPath+  "/student/studentSpeciality/applyExcel");
	  layer.open({
        type: 2,
        title: '打印审批表',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content:contextPath+  "/student/studentSpeciality/applyExcel"
    }); 
}