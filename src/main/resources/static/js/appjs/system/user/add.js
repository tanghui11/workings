var prefix = contextPath+"/system/user"
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
        url: prefix + "/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
               /*  parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index); */
				self.location=document.referrer;
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

function setCheckedRoles() {
    var roleIds = $("#roleIds").val();
    alert(roleIds);
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
                required: true,
                minlength: 2
            },
            username: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 6
            },
            confirm_password: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            topic: {
                required: "#newsletter:checked",
                minlength: 2
            },
            agree: "required"
        },
        messages: {

            name: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名必须两个字符以上"
            },
            username: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名必须两个字符以上"
            },
            password: {
                required: icon + "请输入您的密码",
                minlength: icon + "密码必须6个字符以上"
            },
            confirm_password: {
                required: icon + "请再次输入密码",
                minlength: icon + "密码必须6个字符以上",
                equalTo: icon + "两次输入的密码不一致"
            },
            email: icon + "请输入您的E-mail",
        }
    })
}
function fun(){
	if($("#type").val()=="1"){
		yemian(contextPath+"/system/user/worker","选择中心端用户" );
	}else if($("#type").val()=="2"){
		yemian(contextPath+"/system/user/region","选择考区端用户");
	}else if($("#type").val()=="3"){
		yemian(contextPath+"/system/user/school","选择助学组织用户");
	}else if($("#type").val()=="4"){
		yemian(contextPath+"/system/user/college","选择院校用户");
	}else{
		layer.msg($("#type").val());
	}
}
function yemian(url,user){
	layer.open({
		type : 2,
		title : user,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '80%', '80%' ],
		content : url // iframe的url
	});
} 

//
function changes(){
	$("#workerName").val("");
	$("#workerid").val("");
}


