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
        url: contextPath+"/system/fieldDict/update",
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
            tableName: {
                required: true
            },
            field_name: {
                required: true
            },
            fieldValue: {
                required: true
            },
            fieldMean: {
                required: true
            },
            seq: {
                number: true
            }
        },
        messages: {
            tableName: {
                required: icon + "请输入表名"
            },
            fieldName: {
                required: icon + "请输入字段名"
            },
            fieldValue: {
                required: icon + "请输入字段值"
            },
            fieldMean: {
                required: icon + "请输入字段含义"
            },
        }
    })
}