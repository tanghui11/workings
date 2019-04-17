
var prefix =contextPath+  "/school/schoolSpecialityReg"
$(function() {

	//审核状态
	for(var i=0;i<$("#auditStatus").children().length;i++){
		if($("#auditStatus").children()[i].value=="a"){
			$("#auditStatus").children()[i].selected=true;
		}
	}
	loadTree();
	//计算机构树div高度
    $("#jstree").height($(document).height() - 120);
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listSchoolSpeciality", // 服务器数据的加载地址
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
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
                                regYear:$("#regYear").val(),
                                regSeason:$("#regSeason").val(),
								schoolid : $('#schoolid').val()
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
									{
									field : 'id', 
									title : '序号' ,
									formatter: function (value, row, index) {
										return index+1;
									},
									width : '60px',
								},
									{
									field : 'specialityId',
									title : '专业代码' 
								},
									{
									field : 'specialityName',
									title : '专业名称' 
								},

										{
									field : 'classify',
									title : '专业层次'
								},
										{
									field : 'type',
									title : '助学方式' ,
									width : '80px',
								},
										{
									field : 'educateLength',
									title : '学制' ,
									width : '80px',
								}],
						onCheck:function(row){
							parent.layer.closeAll('iframe');
							parent.$("#regYear").val($("#regYear").val());
							parent.$("#regSeason").val($("#regSeason").val());
							parent.$("#regSeasonName").val($("#regSeason").find("option:selected").text());
							parent.$("#schoolid").val($("#schoolid").val());
							parent.$("#schoolName").val($("#schoolName").val());
							parent.$("#specialityid").val(row.specialityId);
							parent.$("#specialityName").val(row.specialityName);
							parent.$("#classify").val(row.classify);
							parent.$("#type").val(row.type);
							parent.$("#educateLength").val(row.educateLength);
						}
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}


//树状图
function getTreeData() {
    $.ajax({
        type: "GET",
        url:contextPath+ "/common/tree/schoolTree",
        success: function (tree) {
			loadTree(tree);
        }
    });
}
function loadTree() {
    $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+'/common/tree/schoolTree';
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

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
		flag=true;
        reLoad();
    } else {
		console.info(data)
		 $('#schoolid').val(data.selected[0]);
		 $('#schoolName').val(data.node.text.split(" ")[1]);
        $('#orgid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
		flag=true;
        reLoad();
    }
});
//导入
function importData() {
    layer.open({
        type : 2,
        title : '专业开设备案信息导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/ZYimportData' // iframe的url
    });
}