$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: contextPath+"/system/dept/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            parentName: {
                required: true
            },
            name: {
                required: true
            },
            email: {
                email: true
            }
        },
        messages: {
            parentName: {
                required: icon + "请选择上级部门"
            },
            name: {
                required: icon + "请输入名称"
            },
        }
    })
}

var openDeptTree = function () {
    layer.open({
        type: 2,
        title: "选择上级部门",
        area: ['300px', '450px'],
        content: contextPath+"/system/dept/treeView/"+ $('#orgid').val()
    })
}

function loadDeptTree(parentid, parentName) {
    $("#parentid").val(parentid);
    $("#parentName").val(parentName);
}