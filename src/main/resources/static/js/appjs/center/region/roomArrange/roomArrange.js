
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
		data:{
			"type":"a",
			"audit_status":"b",
			"confirm_status":"b",
            "arrange_date":"1"
		},
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="<option>请选择</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
			}
			$("#searchKsrw").html(_html);
			ktime($("#searchKsrw").val());
			kd($("#searchKsrw").val());
		}
	});
}
$("#searchKsrw").change(function() {
	ktime($("#searchKsrw").val());
	kd($("#searchKsrw").val());
});
//开考时间

function ktime(val){
    $.ajax({
        cache : true,
        type : "get",
        url :contextPath+ "/exam/examTime/seachExamTimeAllList?examTaskid="+val,// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            var _html="<option value=''>-请选择-</option>";
            for(var i=0;i<data.length;i++){
                _html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examDate.split(" ")[0]+"年"+data[i].segment+"【"+data[i].beginTime+"--"+data[i].endTime+"】"+"</option>";
            }
            $("#kktime").html(_html);
        }
    });
}


//考点
function kd(val){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "region/roomArrangeRegion/getExamSites",// 你的formid
		data:{
			regionid:kdId,
			exam_taskid:val
		},
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			 var _html="<option value=''>-请选择-</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].name+"</option>";
			}
			$("#searchKd").html(_html);
		}
	});
}
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
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            regionid:kdId,
								examTaskid:$('#searchKsrw').val(),
								examTimeid:$('#kktime').val(),
								examSiteid:$('#searchKd').val(),
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
									{
									field : 'id', 
									title : '序号',
									formatter : function(value, row, index) {
										return index+1
									}
								},
										/*{
									field : 'regionid', 
									title : '考区编号' 
								},
										 {
									field : 'examTimeid', 
									title : '考试时间编号' 
								},
										{
									field : 'examRoomid', 
									title : '考场编号' 
								},
										{
									field : 'examCourseid', 
									title : '开考课程编号' 
								}, */
										{
									field : 'siteName', 
									title : '考点' 
								},
										{
									field : 'type', 
									title : '考生类别' 
								},
									{
									field : 'courseName', 
									title : '课程名称' 
								},
										/* {
									field : 'examType', 
									title : '课程类别' 
								}, */
										{
									field : 'seatStart', 
									title : '座次开始号' 
								},
										{
									field : 'seatEnd', 
									title : '座次结束号' 
								},
										/* {
									field : 'secretCode', 
									title : '保密号' 
								},
										{
									field : 'firstStudentid', 
									title : '首考号' 
								}, */
										{
									field : 'status', 
									title : '状态' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										/* {
									field : 'updateDate', 
									title : '操作日期' 
								},
										{
									field : 'dbFlag', 
									title : '数据标记' 
								}, */
									/* 	{
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
								} */ ]
					});
}
function reLoad() {
	if(kdId==undefined){
		layer.msg("请先选择考区！");
		return;
	}
	if($('#searchKsrw').val()=="请选择"){
		layer.msg("请先选择考试任务！");
		return;
	}
	if($('#kktime').val()==""){
		layer.msg("请先选择考试时间！");
		return;
	}
	$('#exampleTable').bootstrapTable('refresh',{url : prefix + "/list", });
}
//编排
function bianpai(type) {
	if(kdId==undefined){
		layer.msg("请先选择考区！");
		return;
	}
	if($('#searchKsrw').val()=="请选择"){
		layer.msg("请先选择考试任务！");
		return;
	}
	var text='';
	if(type=="b"){
		text="确认"
	}else if(type=="a"){
		text="取消"
	}
	layer.confirm("该考区此次编排将全部"+text+"吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
		$.ajax({
			cache : true,
			type : "get",
			url :contextPath+ "region/roomArrange/roomArrangeConfirm",// 你的formid
			async : false,
			data:{
				status:type,
				regionid:kdId,
				exam_taskid:$('#searchKsrw').val(),
			},
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
			   layer.msg(data.msg);
			   reLoad()
			}
		});
	 }, function() {

    });
}
var kdId;

/* ztree */
    $(function (){
        $.ajax({
            type:"get",
            url:contextPath+ '/common/tree/arrangeTree',
            dataType:"json",
            success:function (data){
                var setting = {
                    data: {
                        simpleData: {
                            enable: true,
                            idKey:"id",
                            pIdKey:"parentid"
                        },
                        key: {
                            name: "text"
                        }
                    },
                    callback: {
                        onClick: zTreeOnClick
                    }
                };

                var zNodes = data;

                function zTreeOnClick(event, treeId, treeNode) {
					kdId = treeNode.id;
					kd($("#searchKsrw").val());
                    

                };

                $.fn.zTree.init($("#jstree"), setting, zNodes);
             //   zTree_Menu = $.fn.zTree.getZTreeObj("jstree");
              /*  var node = zTree_Menu.getNodeByParam("20141201160936323685");
                zTree_Menu.selectNode(node,true);//指定选中ID的节点
                zTree_Menu.expandNode(node, true, false);//指定选中ID节点展开*/

            }

        });
    });
