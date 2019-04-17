var prefix = contextPath+"/student/score"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
        .bootstrapTable(
                {
                        method: 'get', // 服务器数据的请求方式 get or post
                        url: prefix + "/list", // 服务器数据的加载地址
                        iconSize: 'outline',
                        toolbar: '#exampleToolbar',
                        striped: true, // 设置为true会有隔行变色效果
                        dataType: "json", // 服务器返回的数据类型
                        pagination: true, // 设置为true会在底部显示分页条
                        // //设置为limit则会发送符合RESTFull格式的参数
                        singleSelect: false, // 设置为true将禁止多选
                        // contentType : "application/x-www-form-urlencoded",
                        // //发送到服务器的数据编码类型
                        pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
                        pageNumber: 1, // 如果设置了分布，首页页码
                        //search : true, // 是否显示搜索框
                        showColumns: false, // 是否显示内容下拉框（选择显示的列）
                        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                        queryParams: function (params) {
                            return {
                                //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                                limit: params.limit,
                                offset: params.offset,
                                examTaskid : $("#examTaskid").val()
                                // name:$('#searchName').val(),
                                // username:$('#searchName').val()
                            };
                        },
						columns : [
								{
									field : 'subjectid',
									title : '课程代码',
                                    width : '100px'
								},
								{
									field : 'subjectName',
									title : '课程名称'
								},
								{
									field : 'score',
									title : '成绩数量',
									width : '180px'
								},
								{
									title : '操作',
									width : '100px',
									formatter : function(value, row, index) {
                                        var e = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" mce_href="#" title="删除成绩" onclick="remove(\''
                                                + row.subjectid
                                                + '\')"><i class="fa fa-remove"></i></a> ';
                                        return e;
									}
								} ],
					});
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function remove(subjectid) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove/",
            type: "post",
            data: {
                'examTaskid' : $("#examTaskid").val(),
                'subjectid': subjectid
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function importScore() {
    layer.open({
        type: 2,
        title: '导入成绩',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/importScore/' + $("#examTaskid").val() // iframe的url
    });
}