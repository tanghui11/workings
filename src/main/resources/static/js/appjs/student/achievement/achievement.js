var prefix = contextPath+"/student/achievement"
var subjects = '';
$().ready(function () {
    getTreeData();
    loadStudent();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});
function changeExamTask(){
	window.location = prefix + '?currentExamTaskid='+$("#examTaskid").val();
}


function getAllSubject(){
   $.ajax({
         type: "GET",
         data: {
             "examTaskid": $('#examTaskid').val()
         },
         url: prefix + "/getExamSubject",
         success : function(data) {
             subjects = data;
             for(var i=0;i<subjects.length;i++){
                 $('#exampleTable').bootstrapTable('showColumn', 'score'+i);
             }
             for(var i=0;i<subjects.length;i++){
                 $('.score'+i).children(".th-inner ").html(subjects[i].subjectName);
             }
         }
     });
}

function loadStudent() {
     getAllSubject();
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
                        orgid: $('#regOrgid').val(),
                        examTaskid : $('#examTaskid').val(),
                        searchKey : $('#searchName').val()
                    };
                },
                columns : [
                            {
                                title : '序号',
                                width : '10px',
                                formatter: function (value, row, index) {
                                    return index+1;
                                }
                            },
                            {
                                field : 'name',
                                title : '考生姓名',
                                width : '60px'
                             },
                            {
                                field : 'orgid',
                                title : '毕业学校',
                                width : '120px'
                             },
                            {
                                field : 'certificateNo',
                                title : '身份证号',
                                width : '100px'
                             },
                            {
                              field : 'examCode',
                              title : '准考证号',
                              width : '80px'
                            },
                            {
                                field : 'studentType',
                                title : '考生类别',
                                width : '40px'
                            },
                            {
                                field : 'gender',
                                title : '性别',
                                width : '40px'
                            },
                            {
                                field : 'nation',
                                title : '民族',
                                width : '40px'
                            },
                           {
                                class : 'score0',
                                field : 'score0',
                                title : '分数0',
                                width : '60px',
                                visible: false
                            },
                           {
                            class : 'score1',
                            field : 'score1',
                            title : '分数1',
                            width : '60px',
                            visible: false
                           },
                          {
                            class : 'score2',
                            field : 'score2',
                            title : '分数2',
                            width : '60px',
                            visible: false
                          },
                          {
                              class : 'score3',
                              field : 'score3',
                              title : '分数3',
                              width : '60px',
                              visible: false
                          },
                         {
                              class : 'score4',
                              field : 'score4',
                              title : '分数4',
                              width : '60px',
                              visible: false
                          },
                         {
                          class : 'score5',
                          field : 'score5',
                          title : '分数5',
                          width : '60px',
                          visible: false
                         },
                        {
                          class : 'score6',
                          field : 'score6',
                          title : '分数6',
                          width : '60px',
                          visible: false
                        },
                        {
                          class : 'score7',
                          field : 'score7',
                          width : '60px',
                          visible: false
                          },
                         {
                          class : 'score8',
                          field : 'score8',
                          width : '60px',
                          visible: false
                         },
                        {
                          class : 'score9',
                          field : 'score9',
                          width : '60px',
                          visible: false
                        }]
            });
}


function load() {
        $("#exampleTable").bootstrapTable('refresh');
        loadStudent();
}

function getTreeData() {
    $('#jstree').jstree('destroy');
    $("#exampleTable").bootstrapTable('refresh');
    $.ajax({
        type: "GET",
        data: {
            "orgid": $('#orgid').val(),
            "examTaskid": $('#examTaskid').val()
        },
        url: prefix + "/treeAll",
        success: function (tree) {
            loadTree(tree);
        }
    });

    $('#jstree').on("changed.jstree", function (e, data) {
        if (data.selected == -1) {
            $('#regOrgid').val('');
            $('#orgType').val('');
        } else {
            $('#regOrgid').val(data.selected[0]);
            $('#orgType').val(data.node.original.attributes.type);
            load();
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "checkbox": {
            "three_state": true,
        }
    });
    $('#jstree').jstree().open_all();
}
