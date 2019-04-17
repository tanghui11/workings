
var prefix =contextPath+ "/exam/examCourse"
$(function() {
    $("#one").html($("#examYear").val()+"年"+$("#examMonth").val()+"考试课程设置");
    kSrw();
    load();
});
var code;
var names;
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
                        type:$('#type').val(),
                        examTaskid:$('#searchName').val(),
                        courseName:$("#coursess").val(),
                        courseid:$('#coDaima').val(),
                        auditStatus:$("#auditStatus").val(),
                        courseType:$("#courseType").val(),
                        schoolid:$("#schoolid").val(),
                    examTimeid:$("#kktime").val()
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
                        field : 'id',
                        title : '序号' ,
                        formatter: function (value, row, index) {
                            return index+1;
                        },
                        width : '60px'

                    },
                    {
                        field : 'courseid',
                        title : '课程代码' ,
                        width : '80px'
                    },
                    {
                        field : 'courseName',
                        title : '课程名称'
                    },
                    {
                        field : 'bookName',
                        title : '教材名称'
                    },

                    /*
										{
									field : 'examTimeid',
									title : '开考时间编号'
								},
										{
									field : 'fullScore',
									title : '满分'
								},
										{
									field : 'passScore',
									title : '及格分数'
								},
										{
									field : 'objectivesScore',
									title : '客观题总分'
								},
										{
									field : 'subjectiveScore',
									title : '主观题总分'
								},
										{
									field : 'exceedNumber',
									title : '超员人数'
								}, */
                    {
                        field : 'type',
                        title : '类别' ,
                        width : '70px',
                    },
                    {
                        field : 'classify',
                        title : '命题类别' ,
                        width : '70px',
                    },
                    {
                        field : 'cardType',
                        title : '题卡类别' ,
                        width : '80px',
                    },
                    /* 	{
                    field : 'remark',
                    title : '操作员'
                },
                        {
                    field : 'seq',
                    title : '操作员'
                },
                */
                    {
                        field : 'auditStatus',
                        title : '审核状态'
                    },
                    {
                        field : 'courseType',
                        title : '考试类型'
                    },
                    {
                        field : 'schoolName',
                        title : '助学组织'
                    },
                    {
                        field : 'operator',
                        title : '操作员' ,
                        width : '80px',
                    },
                    {
                        field : 'updateDate',
                        title : '操作日期' ,
                        width : '90px',
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        width : '300px',
                        formatter : function(value, row, index) {
                            if(row.auditStatus =="待审核"){
                                var k = '<a class="btn btn-success btn-sm SH" style="width:70px" href="#" title="已审核"  mce_href="#"  onclick="aag(\''
                                    + row.id+"','"+row.auditStatus + '\')">审核</a> ';
                            }else{
                                var k = '<a class="btn btn-warning btn-sm" href="#" title="取消审核"  mce_href="#"  onclick="aag(\''
                                    + row.id+"','"+row.auditStatus+ '\')">取消审核</a> ';
                            }
                            var e = '<a class="btn btn-primary btn-sm ' +
                                ''+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id+"','"+row.courseName+"','"+row.bookName
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm"'
                                +'style="margin-right:5px'
                                + '" href="'+contextPath+'exam/examCourse/placeCourses?courseid='+row.courseid
                                + '" data-index="examRegOrs"'
                                + ' data-text="限报课程"'
                                + ' data-refresh="true"'
                                + ' onclick="parent.menuItem(this);return false;"'
                                + ' data-id="examRegOrs">限报课程</a>';
                            return e + d + f + k;
                        }
                    } ],
                onCheck:function(row){
                    code = row.courseid;
                    names =row.courseName;
                }

            });
}

//单个审核
function aag(id,auditStatus) {
    if(auditStatus=="待审核"){
        layer.confirm('您确定要审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            $.ajax({
                type : 'POST',
                url : contextPath + '/exam/examCourse/updateAuait',
                data : {
                    "id" : id,
                    "auditStatus":"b"
                },
                success : function(r) {
                    if (r.code == 0) {
                        layer.msg(r.msg);
                        reLoad();

                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        });

    }else{
        layer.confirm('您确定要取消审核吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            $.ajax({
                type : 'POST',
                url : contextPath + '/exam/examCourse/updateAuait',
                data : {
                    "id" : id,
                    "auditStatus":"a"
                },
                success : function(r) {
                    if (r.code == 0) {
                        layer.msg(r.msg);
                        reLoad();
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        });

    }

}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : '添加 [ 计划管理 &gt; 开考课程设置(按课程) ]',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '390px' ],
        content : prefix + '/add' // iframe的url
    });
}
var cour;
var book;
function edit(id,courseName,bookName) {
    cour = courseName;
    book = bookName;
    layer.open({
        type : 2,
        title : '编辑 [ 计划管理 &gt; 开考课程设置(按课程) ]',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '395px' ],
        content : prefix + '/edit/' + id +"?courseName="+courseName // iframe的url
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

//考试任务
function kSrw(){
 //   var url = window.location.search;
   // var ids = url.split("?")[1].split("&")[0].split("=")[1];
   var type= $("#type").val();
   if(type=="衔接"){
       type="b"
   }else if(type=="统考"){
        type="a"
    }else if(type=="实践"){
        type="c"
    }
    var ids = $("#ids").val();
    $.ajax({
        cache : true,
        type : "get",
        url :contextPath+ "/exam/task/serchTaskAll?type="+type,// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            var _html="<option value=''>-请选择-</option>";
            for(var i=0;i<data.length;i++){
                if(ids == data[i].id){
                    _html+="<option value="+"'"+data[i].id+"'"+" selected='selected'>"+data[i].examYear+"年"+data[i].examMonth+"</option>";
                }else{
                    _html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
                }
            }
            $("#searchName").html(_html);
            ktime($("#searchName").val());
        }
    });
}
//开考时间

var kaoshi
function ktime(val){
	if(val==""){
		$("#kktime").html("<option value=''>-请选择-</option>");
		return;
	}
    kaoshi = val;
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
//课程名称
function courseName(){
    layer.open({
        type: 2,
        title: '课程名称',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content:contextPath+  "/plan/course/clist"
    });

}

function place(){
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要增加限制报考专业的数据");
        return;
    }
    layer.open({
        type: 2,
        title: '增加限制报考专业',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '300px'],
        content:contextPath+  "/exam/examCourse/place"
    });
}
function texture(){
    layer.open({
        type : 2,
        title : "助学组织 ",
        shadeClose : false,
        area : [ '800px', '520px' ],
        content :  contextPath+ "/school/schoolScoreRatio/School"
    });
}
function  importData() {
    layer.open({
        type : 2,
        title : '批量导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/importData?examTaskid='+$('#searchName').val()+"&examTimeid="+$("#kktime").val() // iframe的url
    });

}
