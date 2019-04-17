
var prefix = contextPath+ "/plan/specialityRecord"
$(function() {
    load();
});
var url = location.search;
var panduanId = url.split("=")[1];
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
                height: ($(document).height() - 160),
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        specialityid:$('#searchName').val(),
                        // subjectName:$('#searchSchoolid').val(),
                        /* schoolName:$('#searchCollegeid').val(), */
                        type:$('#type').val()
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
                        field : 'id',
                        title : '序号',
                        formatter: function (value, row, index) {
                            return index+1;
                        },
                        width:"60px",
                    },
                    {
                        field : 'specialityid',
                        title : '专业代码' ,
                        width:"90px",
                    },
                    {
                        field : 'subjectName',
                        title : '专业名称'
                    },
                   /*  {
                        field : 'gradCourseid',
                        title : '毕业论文'
                    },

                    {
                        field : 'schoolName',
                        title : '主考院校'
                    },
                    {
                        field : 'collegeName',
                        title : '主考学院'
                    }, */
                    {
                        field : 'type',
                        title : '类别' ,
                        width:"90px",
                    },
                    {
                        field : 'direction',
                        title : '专业方向' ,
                        width:"70px",
                    },
					/*
                    {
                        field : 'status',
                        title : '状态' ,
                        width:"80px",
                    },
                    {
                        field : 'gradStatus',
                        title : '办证状态' ,
                        width:"80px",
                    },
										{
									field : 'remark',
									title : '备注'
								},
										{
									field : 'operator',
									title : '操作员'
								},
										{
									field : 'updateDate',
									title : '操作日期'
								}, */
                    
                    ],onCheck : function(row, $element){
                    parent.$("#newSpecialityRecordid").val(row.id);
                    parent.$("#newSpecialityRecordidA").val(row.subjectName+"("+row.direction+")");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                },
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : '添加 [ 计划管理 > 开考专业设置 ] ',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '470px' ],
        content : prefix + '/add' // iframe的url
    });
}
var schoolName;
var collegeName;
var gradCourse;
function edit(id,schoolName,collegeName,gradCourseid) {
    schoolName = schoolName;
    collegeName = collegeName;
    gradCourse= gradCourseid;
    layer.open({
        type : 2,
        title : '编辑 [ 计划管理 > 开考专业设置 ] ',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '470px' ],
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
var majorName ;
var specialityRecord;
function resetPwd(name) {
    specialityRecord = name.split(";")[0];
    majorName = name.split(";")[1];
    layer.open({
        type : 2,
        title : "专业课程["+majorName+"]",
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '80%', '80%' ],
        content :contextPath+  "/plan/specialityCourse"// iframe的url
    });
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
function copyCourse (id){
    layer.open({
        type : 2,
        title : '设置课程',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : contextPath+ "/plan/specialityRecord/showCopyCourse/" +id// iframe的url
    });
    //alert(id)
}
function setCourse(){
    /* layer.open({
        type : 2,
        title : '设置课程',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    }); */
}


function  importData() {
    layer.open({
        type : 2,
        title : '弹出导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData' // iframe的url
    });
}

//导出到Excel
function exportExcel(src){
    document.getElementById('form1').action=contextPath+"/"+src;
//若需提交表单（如导出）
    document.getElementById('form1').submit();
}