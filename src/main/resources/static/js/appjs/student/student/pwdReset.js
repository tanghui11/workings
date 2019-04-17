var prefix = contextPath+"/student/student";

$().ready(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/listStudentByKey", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
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
                        searchName: $('#searchName').val(),
                    };
                },
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'className',
                        title: '班级名称'
                    },
                    {
                        field: 'name',
                        title: '学生姓名'
                    },
                    {
                        field: 'gender',
                        title: '性别'
                    },
                    {
                        field: 'certificateNo',
                        title: '身份证件号'
                    },
                    {
                        field: 'code',
                        title: '学籍号'
                    },
                    {
                        field: 'nation',
                        title: '民族'
                    },
                    {
                        field: 'politics',
                        title: '政治面貌'
                    },
                    {
                        field: 'phone',
                        title: '联系电话'
                    },
                    {
                        field: 'mphone',
                        title: '移动电话'
                    },
                    {
                        field: 'address',
                        title: '通讯地址'
                    },
                    {
                        field: 'feature',
                        title: '照顾特征'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        widht: '100px',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_reset_h + '" href="#" mce_href="#" title="重置密码" onclick="reset(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm ' + s_reset_h + '" href="#" mce_href="#" title="修改手机号码" onclick="updateMphone(\''
                                + row.id
                                + '\')"><i class="fa fa-check-circle"></i></a> ';
                            return e + p;
                        }
                    }]
            });
}

function reLoad() {
    if($.trim($('#searchName').val()) == ''){
        layer.alert("请输入姓名、身份证号、学籍号、手机号等关键字检索！");
        return;
    }
    $('#exampleTable').bootstrapTable('refresh');
}

function reset(id) {
    layer.confirm('确定要重置该考生的登录密码吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            cache: true,
            type: "post",
            url: prefix + "/pwdReset",
            data: {
                'id': id
            },
            beforeSend: function(){
                layer.load();
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                } else {
                    layer.alert(r.msg);
                }
            },
            async: false,
            complete: function(){
                layer.closeAll('loading');
            }
        })
    })
}

function updateMphone(id) {
    layer.prompt({title: '输入新手机号码，并确认', formType: 0,value: '', yes: function(index, layero){
        var value = layero.find(".layui-layer-input").val();
        if(value != ''){
            var phone = /^((13[0-9])|147|145|(17[0-35-8])|(15[0-35-9])|(18[0-35-9]))[0-9]{8}$/;
            if(!phone.test(value)){
                 layer.msg("手机号错误");
                 return;
            }
        }
        layer.close(index);
        $.ajax({
            cache: true,
            type: "post",
            url: prefix + "/updateMphone",
            data: {
                'id': id,
                'mphone': value
            },
            beforeSend: function(){
                layer.load();
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    $('#exampleTable').bootstrapTable('refresh');
                } else {
                    layer.alert(r.msg);
                }
            },
            async: false,
            complete: function(){
                layer.closeAll('loading');
            }
        })
    }
    });
}