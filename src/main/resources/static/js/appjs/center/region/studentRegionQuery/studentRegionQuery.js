var prefix =contextPath+  "/region/studentRegionQuery"
$(function() {
	ksrw();
	//考区名称
    regionList();
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
								studentid:$("#searchName").val(),
								examtaskid:$('#examTaskid').val(),
                                parentid:$('#regionid').val(),
                                childRegionid:$("#childRegionid").val(),

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
									radio : true
								},
									{
									field : 'studentid',
									title : '准考证号' ,
								},
									{
									field : 'name', 
									title : '姓名' 
								},
									{
									field : 'courseid',
									title : '拼音' 
								},
                            		{
                               		 field : 'courseName',
                               		 title : '课程名称'
                            	},
									{
									field : 'parentName',
									title : '地州市'
								},
										{
									field : 'childName',
									title : '县区'
								},
										{
									field : 'type',
									title : '报考类别'
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
										]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function ksrw(){
    $.ajax({
        cache : true,
        type : "get",
        url :contextPath+ "exam/task/serchTaskAll",// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            var _html="";
            for(var i=0;i<data.length;i++){
                _html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
            }
            $("#examTaskid").html(_html);
        }
    });
}

//地州市考办
function regionList(){
    var _html ="<option value = ''>-请选择-</option>";
    $.ajax({
        url :contextPath+  "/region/region/regionList",
        type : "get",
        data : {
            'type' : "a"
        },
        success : function(r) {
            for(var i=0;i<r.length;i++){
                _html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
            }
            $("#regionid").html(_html);
        }
    });
}
//根据省考办获取县考办
function region(){
    if($("#regionid").val()==""){
        var _html ="<option value=''>--请选择--</option>";
        $("#childRegionid").html(_html);
        return;
    }
    var _html ="<option value=''>--请选择--</option>";
    $.ajax({
        url :contextPath+  "/region/region/regionList",
        type : "get",
        data : {
            'parentid' : $("#regionid").val()
        },
        success : function(r) {
            for(var i=0;i<r.length;i++){
                _html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
            }
            $("#childRegionid").html(_html);
        }
    });
}