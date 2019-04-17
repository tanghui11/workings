$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: contextPath+"/student/student/update",
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
            orgName: {
                required: true
            },
            code: {
                required: true
            },
            name: {
                required: true
            },
        },
        messages: {
            orgName: {
                required: icon + "请选择学校"
            },
            code: {
                required: icon + "请输入学籍号"
            },
            name: {
                required: icon + "请输入名称"
            },
        }
    })
}

var openOrgTree = function () {
    layer.open({
        type: 2,
        title: "选择所在部门",
        area: ['300px', '450px'],
        content: contextPath+"/system/org/treeView"
    })
}

function loadOrgTree(orgid, orgName) {
    $("#orgid").val(orgid);
    $("#orgName").val(orgName);
}