$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/system/exportField/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
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
		rules : {
			seq : {
                    required : true
                },
            name : {
                    required : true,
                    maxlength: 10
                },
            type : {
                    required : true
                },
            tableName :{
                required : function(){
                    if($("#transType").val() == 'field_dict'){
                        return true;
                    }else{
                        return false;
                    }
                }
            },
            fieldName :  function(){
                if($("#transType").val() == 'field_dict'){
                    return true;
                }else{
                    return false;
                }
            }
		},
		messages : {
			seq : {
                required : icon + "请输入序号"
            },
			name : {
                required : icon + "请输入字段名称",
                maxlength : icon + "字段名称不能超过10个字符"
            },
            type : {
                required : icon + "请输入字段类型"
            },
            tableName : {
                required : icon + "请输入表名"
            },
            fieldName : {
                required : icon + "请输入字段名"
            }
		}
	})
}