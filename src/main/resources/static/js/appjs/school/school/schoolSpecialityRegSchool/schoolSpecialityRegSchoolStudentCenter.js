
var prefix = contextPath+"/school/schoolSpecialityRegSchool"
$(function() {
    teachid = parent.$("#teachSiteid option:selected").val();
    collegeId = parent.$("#collegeid option:selected").val();
    load();
    //loadNew();
});
var teachid;
var collegeId;
//搜索为空不加载table数据
function loadNew(){

    if(teachid!=''||collegeId!=''){
        $('#exampleTable').bootstrapTable('refresh', {url: prefix + "/list"});
    }
}
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
                        //regYear:$('#searchRegYear').val(),
                        teachid : parent.$("#teachSiteid").val(),
                        collegeid : parent.$("#collegeid").val(),
                        schoolid:parent.$("#schoolid").val(),
                        audit_status:"b"
                        // username:$('#searchName').val()
                    };
                },
                onCheck:function(row){
                    parent.layer.closeAll('iframe');
                    //新生注册、报考专业回值
                    var data = row.specialityId+row.specialityName+"["+row.regYear+" "+row.regSeason+"]"+"["+row.classify+" "+row.type+" "+row.educateLength+"]"
                    parent.$("#schoolSpecialityRegid").val(data);
                    parent.$("#specialityRecordid2").val(row.id);
                    parent.$("#specialityRecordid").val(row.specialityRecordid)
                    parent.$("#specialityid").val(row.specialityId)
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
                        }
                    },
                    {
                        field : 'teachSiteName',
                        title : '教学点名称'
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
                        title : '授课方式'
                    },
                    // 		{
                    // 	field : 'method',
                    // 	title : '助学手段'
                    // },
                    {
                        field : 'educateLength',
                        title : '学制'
                    },
                    {
                        field : 'regYear',
                        title : '招生年份'
                    },
                    {
                        field : 'regSeason',
                        title : '招生季节'
                    },
                    {
                        field : 'schoolName',
                        title : '主考学校'
                    },
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {

    location.href =  prefix + '/edit/' + id ;
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