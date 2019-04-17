
//var prefix = contextPath+"/student/studentCourseStudent"
var prefix = contextPath+"/student/studentRegStudent"
var dyID;
$(function() {
	load();
    task();
    college();
});

//学院名称数据
function college(){
    $.ajax({
        url : contextPath+"/school/collegeSchool/listCollege",
        type : "get",
        //async:false,//更改为同步
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
$("#teachSiteid").on("change",function(){
	$("#schoolSpecialityRegid2").val("")
	$("#specialityRecordid").val("")
	$("#schoolSpecialityRegid").val("")
})
//报考专业
function yemian(){
	if($("#teachSiteid").val()==''||$("#collegeid").val()==''){
		layer.msg("请先选择学院名称和教学点！");
		return;
	}else{
		layer.open({
			type : 2,
			title : "报考专业",
			maxmin : true,
			shadeClose : false,
			area : [ '90%', '90%' ],
			content : contextPath+"/school/schoolSpecialityRegSchool/SchoolSpecialityRegSchoolStudent"
		});
	}
    
}
function course(){
   var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if ($("#specialityRecordid").val() == "") {
        layer.msg("请选择招生计划");
        return;
    }
	layer.open({
        type : 2,
        title : "开考课程",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentCourseStudent/studentCourse?specialityRecordid="+$("#specialityRecordid").val()
    });
}
//考试任务
function task(){
    $.ajax({
        url : contextPath+ "exam/task/serchTaskAll",
        type : "get",
		data:{
			"type":"a",
			"date":"1",
			"confirm_status":"b",
			"audit_status":"b"
		},
        //async:false,//更改为同步
        success : function(data) {
            for (var i =0; i < data.length; i++) {
                $("#task").append("<option value=" + data[i].id + ">" +data[i].examYear+ "年"+data[i].examMonth+"</option>");
            }
        }
    });
}
$("#task").on("change",function(){

})
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                //url : prefix + "/listStudent", // 服务器数据的加载地址
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
                        schoolSpecialityRegid:$("#schoolSpecialityRegid2").val()

                    };
                },
                onCheck:function(row, eve){
                    zId = row.studentid;
                    oneByO(zId); //一一对应学生注册信息
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                //	singleSelect : true,// 单选checkbox
                columns : [
                    {
                        radio : true
                    },
                    {
                        field : 'id',
                        title : '序号',
                        formatter: function (value, row, index) {
                            return index+1;
                        }
                    },
                    {
                        field : 'studentid',
                        title : '准考证号'
                    },
                    {
                        field : 'name',
                        title : '姓名'
                    },

                ]
            });
    function oneByO(id){
        dyID = id;
        reLoad2();
    }
    $('#exampleTable2')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
               // url : contextPath+"/student/studentCourseStudent/ybkList", // 服务器数据的加载地址
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
                        studentid:dyID
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
                //singleSelect : true,
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'id',
                        title : '序号' ,
                        width:'50px',
                        formatter: function (value, row, index) {
                            return index+1;
                        }
                    },
                    {
                        field : 'studentid',
                        title : '准考证号' ,
                        width:'110px',
                    },
                    {
                        field : 'name',
                        title : '考生姓名' ,
                        width:'100px',
                    },
                    {
                        field : 'courseid',
                        title : '课程代码' ,
                        width:'70px',
                    },
                    {
                        field : 'courseName',
                        title : '课程名称'
                    },
                    {
                        field : 'examDate',
                        title : '考试时间' ,
                        width:'90px',
                    },
                    {
                        field : 'type',
                        title : '类别' ,
                        width:'80px',
                    },
                    {
                        field : 'regionName',
                        title : '考试地州' ,
                        width:'90px',
                    },
                    {
                        field : 'childRegionName',
                        title : '考试县区' ,
                        width:'90px',
                    },
                    {
                        field : 'segment',
                        title : '时段' ,
                        width:'60px'
                    },
                    {
                        field : 'status',
                        title : '状态' ,
                        width:'80px'
                    },
                    {
                        field : 'arrangeStatus',
                        title : '编排状态' ,
                        width:'80px'
                    },
                    {
                        field : 'operator',
                        title : '操作员' ,
                        width:'80px'
                    },
                    {
                        field : 'updateDate',
                        title : '操作日期' ,
                        width:'90px',
                    }
                   ]
            });
}

function reLoad2() {
    $('#exampleTable2').bootstrapTable('refresh',{url : contextPath+"/student/studentCourseStudent/ybkList"});
}
function reLoad() {

    if($("#collegeid").val()==""){
        layer.msg("请选择学院")
        return false;
    }
    if($("#teachSiteid").val()==""){
        layer.msg("请选择教学点")
        return false;
    }
    if($("#task").val()==""){
        layer.msg("请选择考试任务")
        return false;
    }
    $('#exampleTable').bootstrapTable('refresh',{url : prefix + "/listStudent"});

}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/edit/' + id // iframe的url
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
function  importData() {
    layer.open({
        type : 2,
        title : '批量课程报考',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData' // iframe的url
    });
}
function history(){
    layer.open({
        type : 2,
        title : "未报考人员名单",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentCourseStudent/studentNoExam"
    });
}