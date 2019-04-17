$().ready(function() {
    $('#help_content').summernote({
        height:'800px',
        lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files) { //the onImageUpload API
                img = sendFile(files[0]);
            }
        }
    });
    $('#help_content').summernote('code', decodeURIComponent($("#content").val()));
	validateRule();
});

function sendFile(file) {
    var data = new FormData();
    data.append("file", file);
    data.append("subPath", "help");
    $.ajax({
        data: data,
        type: "POST",
        url: contextPath+"/system/summernote/upload",
        cache: false,
        contentType: false,
        processData: false,
        success: function(data){
            if(data.code == 0){
                $("#help_content").summernote('insertImage', data.url, function ($image) {
                    $image.attr('src', data.url);
                });
            }else{
                layer.alert(data.msg);
            }
        }
    });
}

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {
    $("#content").val(encodeURIComponent($("#help_content").summernote('code')));
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/system/help/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.load();
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
		rules : {
			content : {
				required : true
			}
		},
		messages : {
            content : {
				required : icon + "请输入内容"
			}
		}
	})
}