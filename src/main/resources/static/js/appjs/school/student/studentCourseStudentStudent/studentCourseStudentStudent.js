
var prefix = contextPath+"/student/studentCourseStudent"
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
        }
    });
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
function course(id,specialityName){

	layer.open({
        type : 2,
        title : specialityName,
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentCourseStudent/studentCourse?specialityRecordid="+id
    });
}
//考试任务
function task(){
    $.ajax({
        url : contextPath+"/student/studentCourseStudent//listTask",
        type : "get",
        //async:false,//更改为同步
        success : function(data) {
            for (var i =0; i < data.length; i++) {
                $("#task").append("<option value=" + data[i].id + ">" +data[i].examYear+ "年"+data[i].examMonth+"</option>");
            }
        }
    });
}

function load() {
$('#exampleTable')
    .bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
          //  url : contextPath+"/school/schoolSpecialityRegSchool"+"/list", // 服务器数据的加载地址
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
                    regYear:$('#searchRegYear').val(),
                    collegeid:$('#collegeid').val(),
                    teachSiteid:$('#teachSiteid').val()
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
                    field : 'id',
                    title : '序号',
                    formatter: function (value, row, index) {
                        return index+1;
                    }
                },

                {
                    field : 'specialityId',
                    title : '专业代码'
                },
                {
                    field : 'specialityName',
                    title : '专业名称',
                    formatter:function(value, row, index){
                        var temp = '<a href="#" onclick="course(\'' + row.specialityRecordid + '\',\'' + row.specialityName +'\')"> '+ row.specialityName + '</a>';
                        return temp;

                    }
                },
                {
                    field : 'classify',
                    title : '专业层次'
                },

                {
                    field : 'schoolName',
                    title : '主考学校'
                }]
        });
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
    if($("#schoolSpecialityRegid2").val()==""){
        layer.msg("请选择招生计划")
        return false;
    }
    if($("#task").val()==""){
        layer.msg("请选择考试任务")
        return false;
    }
	$('#exampleTable').bootstrapTable('refresh',{url: contextPath+"/school/schoolSpecialityRegSchool"+"/list"});
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