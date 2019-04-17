var prefix = contextPath+"/oa/notify"
$(function () {
    load();
});
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listNotifyView", // 服务器数据的加载地址
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                clickToSelect: false,                //是否启用点击选中行
                // //发送到服务器的数据编码类型
                pageSize : parent.pageSize, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        title:$('#searchName').val()
                    };
                },
                columns : [
                    {
                        visible :false,
                        field : 'id',
                        title : '编号'
                    },
                    {
                        field : 'type',
                        title : '类型',
                        width:100
                    },
                    {
                        field : 'title',
                        title : '标题',
                        formatter: function(value,row,index){
                            return '<a href="#" onclick="preview(\''+row.id+'\')">'+row.title+'</a>';
                        }
                    },
                    {
                        visible : true,
                        field : 'updateDate',
                        title : '发布日期',
                        width:160
                    },
                    {
                        title : '操作',
                        field : 'operation',
                        align : 'center',
                        width:60,
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_preview_h + '" href="#" mce_href="#" title="预览" onclick="preview(\''
                                + row.id
                                + '\')"><i class="fa fa-eye"></i></a> ';
                            return e ;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function preview(id) {
    layer.open({
        type : 2,
        title : '预览',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/preview/' + id // iframe的url
    });
}
