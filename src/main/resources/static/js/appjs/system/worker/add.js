$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

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

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: contextPath+"/system/worker/save",
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
            certificate_no: {
                maxLength: 18,
                minLength: 16
            }
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
        }
    })
}
