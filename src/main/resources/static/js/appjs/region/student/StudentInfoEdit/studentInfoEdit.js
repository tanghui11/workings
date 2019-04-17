
var prefix = contextPath+"/region/StudentInfoEdit"
$(function() {
	validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
//查询
function reloads(){
	if($("#serachStudentid").val()==""){
		layer.msg("请输入准考证号查询");
		return ;
	}
    $.ajax({
        type : 'get',
        url : prefix + '/list/'+$('#serachStudentid').val(),
        success : function(r) {
        	$("#studentid").val(r.studentid);
        	$("#name").val(r.name);
        	$("#phone").val(r.phone);
        	$("#mphone").val(r.mphone);
        	$("#address").val(r.address);
        	$("#postCode").val(r.postCode);
        	$("#email").val(r.email);
        	$("#groupid").val(r.groupid);
        	$("#certificateNo").val(r.certificateNo);
			$("#regionid").val(r.regionid);
        }
    });
}
//保存
function save(){
    if($("#serachStudentid").val()==""){
        layer.msg("请输入准考证号");
        return ;
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix+"/save",
        data : $('#signupForm').serialize(), // 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("网络超时");
        },
        success : function(data) {
        	console.log(data)
            if (data.code == 0) {
                parent.layer.msg("操作成功");
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}//集体代码
function jtDm(){
    layer.open({
        type : 2,
        title : '集体代码',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '630px', '80%' ],
        content : contextPath+"/student/studentRegion/group"

    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
	
    $("#signupForm").validate({
        rules : {
            phone : {
                required : true,
                isPhone : true
            },
            mphone : {
                required : true,
                isPhone : true
            },
			address : {
                required : true,
            },
			postCode : {
				isZipCode : true,
            },
			email : {
                email : true,
            },
			groupid : {
                required : true,
            },
        },
        messages : {
            phone : {
               required : icon + "请输入手机号",
               isPhone : icon + "请填写正确的11位手机号",
            },
			mphone : {
               required : icon + "请输入手机号",
               isPhone : icon + "请填写正确的11位手机号",
            },
			address : {
               required : icon + "请输入通讯地址",
            },
			postCode : {
			   isZipCode : icon + "请输入正确的邮政编码",
            },
			email : {
			   email : icon + "请输入正确邮箱",
            },
			groupid : {
			   required : icon + "请选择集体代码",
            },
        }
    })
}