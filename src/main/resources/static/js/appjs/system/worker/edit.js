// 以下为官方示例
$().ready(function () {
    validateRule();
    // $("#signupForm").validate();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    $("#roleIds").val(getCheckedRoles());
    $.ajax({
        cache: true,
        type: "POST",
        url: contextPath+"/system/worker/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg);
            }
        }
    });
}

function getCheckedRoles() {
    var adIds = "";
    $("input:checkbox[name=role]:checked").each(function (i) {
        if (0 == i) {
            adIds = $(this).val();
        } else {
            adIds += ("," + $(this).val());
        }
    });
    return adIds;
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            },
            deptName: {
                required: true
            },
            mphone: {
                required: true
            },
            email: {
                email: true
            },
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            },
            deptName: {
                required: icon + "请选择部门"
            },
            mphone: {
                required: icon + "请输入手机号码"
            },
            certificate_no: {
                maxLength: 18,
                minLength: 16
            }
        }
    })
}

var openDeptTree = function () {
    layer.open({
        type: 2,
        title: "选择所在部门",
        area: ['300px', '450px'],
        content: contextPath+"/system/dept/treeView/"+$('#orgid').val()
    })
}

function loadDeptTree(deptid, deptName) {
    $("#deptid").val(deptid);
    $("#deptName").val(deptName);
}