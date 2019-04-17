
var prefix = contextPath+"/student/studentRegion"
$().ready(function() {
    load();

});

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function saveEnexam() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择需要报考的课程！");
        return;
    }
    var examCourseid =[];
    for ( var i =0;i<rows.length; i++) {
        examCourseid.push(rows[i].examCourseid)
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : contextPath+"/student/studentRegion/saveexam",
        data : {
            "studentid":$("#studentid").val(),
            "regionid":$("#regionid").val(),
            "childRegionid":$("#childRegionid").val(),
            "examCourseid":examCourseid
        },// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {

               parent.parent.layer.closeAll();
                parent.parent.reLoad();
                /*parent.reLoad();*/
               /* var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                console.log(index)*/
                parent.layer.msg("报考成功");
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/enexam", // 服务器数据的加载地址
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
                        name:$("#searchName").val(),
                        regionid:$("#regionid").val(),
                        examTaskid:$("#examTaskid").val(),
                        studentid:$("#studentid").val(),
                        specialityRecordid:$("#specialityRecordid").val()
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
                        title : '课程代码'
                    },
                            {
                        field : 'name',
                        title : '课程名称'
                    },
                    {
                        field : 'examDate',
                        title : '考试日期'
                    },
                    {
                        field : 'segment',
                        title : '时段'
                    },{
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="添加" onclick="saveEnexam(\''
                                + row.examCourseid
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            return e;
                        }
                    }
                ]
            });
}
