// 以下为官方示例
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
        url: contextPath+"/system/user/changePwdSave",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg(data.msg);
                window.location.reload();
                layer.close(index);
            } else {
                layer.msg(data.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            pwdOld: {
                required: true
            },
            pwdNew: {
                required: true,
                minlength: 6,
                maxlength: 18
            },
            pwdNew2: {
                required: true,
                equalTo: "#pwdNew",
                minlength: 6,
                maxlength: 18
            },
        },
        messages: {
            pwdOld: {
                required: icon + "请输入旧密码"
            },
            pwdNew: {
                required: icon + "请选择新密码",
                minlength: icon + "请再次6-18位新密码",
                maxlength: icon + "请再次6-18位新密码",
            },
            pwdNew2: {
                required: icon + "请再次输入新密码",
                equalTo: icon + "请再次输入新密码",
                minlength: icon + "请再次6-18位新密码",
                maxlength: icon + "请再次6-18位新密码",
            }
        }
    })
}