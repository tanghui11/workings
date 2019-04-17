$().ready(function() {
    validateRule();
    childRegion();
    groups();
    /*specialityname();*/
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
//拍照
function Photo(){
	layer.open({
		type : 2,
		title : "拍照",
		maxmin : true,
		shadeClose : false, 
		area : [ '490px', '415px' ],
		content : contextPath+"/student/studentRegion/showPhoto"
	});
}
//照片回显
function phoneHX(){
    return;
}

//报考专业
function yemian(){
    layer.open({
        type : 2,
        title : "报考专业",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentRegion/SchoolSpecialityRegSchoolStudent"
    });
}

//县区回显
function childRegion(){
    var regionid = $("#regionid").val();
	var rRid = $("#rRid").val();
    $.ajax({
        url : contextPath+"/student/studentRegion/childRegionlist",
        type : "get",
        data:{
            parentid:regionid
        },
        success : function(data) {
            /*$("#childRegionid").find("option").remove();//首先清空下拉数据*/
            for (var i =0; i < data.length; i++) {
                $("#childRegionid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
            }
			var obj = $("#childRegionid option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == rRid){
			        obj[i].selected = 'selected';
			        break;
			    }
			}
        }
    });
}
//集体代码数据
function groups(){
    var regionid = $("#regionid").val();
    $.ajax({
        url : contextPath+"/student/studentRegion/groupslist2",
        type : "get",
        data:{
            regionid:regionid
        },
        success : function(data) {
            /*$("#childRegionid").find("option").remove();//首先清空下拉数据*/
            for (var i =0; i < data.length; i++) {
                $("#groupid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
            }
        }
    });
}
/*//报考专业数据
function specialityname(){
    $.ajax({
        url : contextPath+"/student/studentRegion/planame",
        type : "get",
        /!*data:{
            regionid:regionid
        },*!/
        success : function(data) {
            /!*$("#childRegionid").find("option").remove();//首先清空下拉数据*!/
            for (var i =0; i < data.length; i++) {
                $("#specialityname").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
            }
        }
    });
}*/

//原学专业
function yemianYXZY(){
    layer.open({
        type : 2,
        title : "原学专业",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/student/studentRegion/showSubject"
    });
}
//上传
function upload(){
    layer.open({
        type : 2,
        title : "上传",
        maxmin : true,
        shadeClose : false,
        area : [ '600px', '90%' ],
        content : contextPath+"/common/uploadPhoto"
    });
}
//请求上传图片数据
function save() {
	countDown("submission","提交");
    $.ajax({ //判断身份证不能重复
        url : contextPath+"/common/searchIfExist",
        type : "get",
        data : {
            'tableName':'stu_student',
            'filedName1':'certificate_no',
            'filedValue1':$("#certificateNo").val()
        },
        success : function(data) {
            if(data==0){
				var str = $("#scPhone").attr("src").split(".");
				if(str[1]=="png"){//判断照片不能为空
					layer.msg("请上传照片！");
					return;
				}else{
					//验证身份证唯一（同一次考试任务内）
					$.ajax({
						url : contextPath+"/common/searchStudentidCard",
						type : "get",
						async : false,
						data:{
							idcard:$("#certificateNo").val(),
							studentid:$("#studentid").val()
						},
						success : function(data) {
							if(data==0){
								repeatID();
							}else{
								layer.msg("同一次考试任务内身份证只能注册一次！");
								return;
							}
							
						}
					});
				}
            }else{
                layer.msg("身份证已注册，不能重复提交！");
            }

        }
    });

    function repeatID(){
        $.ajax({
            cache : true,
            type : "POST",
            url : contextPath+"/student/studentRegion/save",
            data : $('#signupForm').serialize(),// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {

                if (data.code == 0) {
                    layer.open({
                        type : 2,
                        title : "课程报考",
                        maxmin : true,
                        shadeClose : false,
                        area : [ '90%', '90%' ],
                        content : contextPath + '/student/studentRegion/toenexam?examTaskid=' + $("#examTaskid").val() +"&studentid=" +$("#studentid").val() +"&childRegionid="+$("#childRegionid option:selected").val()+"&specialityRecordid="+$("#specialityRecordid").val()+"" +""
                    });
                    /*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引*!/
                    parent.layer.close(index);
                    parent.layer.msg("操作成功");*/

                    /*parent.reLoad();*/

                } else {
                    parent.layer.alert(data.msg)
                }

            }
        });
    }
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
	
    $("#signupForm").validate({
        rules : {
            name : {
                required : true,
                maxlength : 15
            },
            pinyin : {
                maxlength : 15
            },
            birthday : {
                required : true,
            },
            certificateNo : {
                required : true,
                isIdentity : true
            },
            address : {
                maxlength : 65
            },
            postCode : {
                isZipCode : true
            },
            email : {
                email : true
            },
            phone : {
                isTel : true
            },
            mphone : {
                isPhone : true
            },
            teachSiteid : {
                required : true,
            },
            xBZY : {
                required : true,
            },
            gradSchool : {
                required : true,
            },
            gradCertificate : {
                required : true,
            },
            yYZY : {
                required : true,
            },
        },
        messages : {
            name : {
                required : icon + "请输入名称",
                maxlength : icon + "最多输入15个字"
            },
            pinyin : {
                maxlength : icon + "最多输入15个字符"
            },
            birthday : {
                required : icon + "请选择日期",
            },
            certificateNo : {
                required : icon + "身份证不能为空",
                isIdentity : icon + "请输入正确的身份证",
            },
            address : {
                isIdentity : icon + "最多输入65个字",
            },
            postCode : {
                isZipCode : icon + "请输入正确的邮政编码",
            },
            email : {
                email : icon + "请输入正确邮箱",
            },
            phone : {
                isTel : icon + "请填写正确的座机号码",
            },
            mphone : {
                isPhone : icon + "请填写正确的11位手机号",
            },
            teachSiteid : {
                required : icon + "请选择现教学点",
            },
            xBZY : {
                required : icon + "请选择现报专业",
            },
            gradSchool : {
                required : icon + "请输入毕业院校",
            },
            gradCertificate : {
                required : icon + "请输入毕业证书号",
            },
            yYZY : {
                required : icon + "请选择原学专业",
            }
        }
    })
}