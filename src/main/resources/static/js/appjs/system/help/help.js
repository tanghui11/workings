var prefix = contextPath+"/system/help"
$().ready(function () {
    getTreeData();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});

function load() {
    $.ajax({
        url: prefix + "/list/" + $("#menuid").val(),
        type: "get",
        success: function (data) {
            if(data.code == '1') {
                $("#helpContent").html("<h1>该菜单下没有文档,请编辑!</h1>")
            } else {
                $("#helpContent").html(decodeURIComponent(data.data.content));
            }
        }
    });
}


function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+"/system/help/tree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        }
    });
    $('#jstree').jstree().open_all();
}

$('#jstree')
    .on('loaded.jstree', function (e, data) {
        var inst = data.instance;
        var obj = inst.get_node(e.target.firstChild.firstChild.lastChild.firstChild);
        if (obj.id != "-1") {
            inst.select_node(obj);
        }
    })
    .on("changed.jstree", function (e, data) {
        var tree = $('#jstree').jstree();
        if (data.action == "ready") {
            var node = tree.get_node(data.selected[0])
        } else {
            var node = data.node;
        }
        if (node.id != '-1') {
            $('#menuid').val(node.id)
            load()
        } else {
            if (node.children.length > 0) {
                tree.select_node(node.children[0]);
            }
            tree.deselect_node(node);
        }
    });

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + $("#menuid").val() // iframe的url
    });
}

function remove() {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'menuid': $("#menuid").val()
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    load()
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}