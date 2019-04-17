$().ready(function() {
    validateRule();
   // childRegion();
  
    phoneHX();
	regionList();
	typeZhuxue();
	college($("#schoolid").val())
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
//拍照
function Photo(){
	if($("#id").val()!=""){
		layer.open({
			type : 2,
			title : "拍照",
			maxmin : true,
			shadeClose : false, 
			area : [ '490px', '415px' ],
			content : contextPath+"/student/studentRegion/showPhotoCenter"
		});
	}else{
		layer.msg("没有准考证号不能上传！");
	}
	
}
//照片回显
function phoneHX(){
    var src = $("#pic").val()+'?number='+Math.random();//url不变参数变
    $("#scPhone").attr("src",src)
    var url = $("#idcardPic").val()
    $("#id_img_pers").attr("src",url)
}

//报考专业
function yemian(){
	var zhuxueText =  $("#type").find("option:selected").text()
	if(zhuxueText=="社会型考试"){
		if($("#schoolids").val()=="-1"){
			layer.msg("请先选择地州市考办！");
			return;
		}else{
			layer.open({
				type : 2,
				title : "报考专业",
				maxmin : true,
				shadeClose : false,
				area : [ '90%', '90%' ],
				content : contextPath+"/student/studentFileOption/SchoolSpecialityRegSchoolStudentCenter"
			});
		}
	}else{
		if($("#schoolid2").val()==""||$("#collegeid").val()==""||$("#teachSiteid").val()==""){
			layer.msg("请先选择助学、学院、教学点！");
			return;
		}else{
			layer.open({
				type : 2,
				title : "报考专业",
				maxmin : true,
				shadeClose : false,
				area : [ '90%', '90%' ],
				content : contextPath+"/student/studentFileOption/SchoolSpecialityRegSchoolStudentCenter2"
			});
		}
	}
    
}

//县区回显
function childRegion(){
    var regionid = $("#regionid").val();
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
            var hui= $("#childRegion").val();
            var obj = $("#childRegionid option");
            for(var i = 0; i < obj.length; i++){
                var tmp = obj[i].value;
                if(tmp == hui){
                    obj[i].selected = 'selected';
                    var index = $("#childRegionid option:selected").index()
                    /* groups(childRegionid[index]);  */
                    break;
                }
            }
        }
    });
}   
//集体代码数据
function groups(){
    var regionid = $("#schoolids").val();
    $.ajax({
        url : contextPath+"/student/studentRegion/groupslist2",
        type : "get",
        data:{
            regionid:regionid
        },
		async : false,
        success : function(data) {
			$("#groupid").html("<option value='-1'>请选择</option>")
            /*$("#childRegionid").find("option").remove();//首先清空下拉数据*/
            for (var i =0; i < data.length; i++) {
				
				if($("#groupids").val()==data[i].id ){
					$("#groupid").append("<option value=" + data[i].id + " selected>" +data[i].name+ "</option>");
				}else{
					$("#groupid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				}
                
            }
        }
    });
}

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
	if($("#id").val()!=""){
		layer.open({
			type : 2,
			title : "上传",
			maxmin : true,
			shadeClose : false,
			area : [ '600px', '90%' ],
			content : contextPath+"/common/uploadPhotoCenter"
		});
	}else{
		layer.msg("没有准考证号不能上传！");
	}
}
function update() {
	countDown("6","submission","提交");
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
				tijiao();
			}else{
				layer.msg("同一次考试任务内身份证只能注册一次！");
				return;
			}
			
		}
	});
	function tijiao(){
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath+"/student/studentFileOption/update",
			data : $('#signupForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg(data.msg);
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
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
            gradSpecialityid : {
                required : true,
            },
			zx : {
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
            gradSpecialityid : {
                required : icon + "请选择原学专业",
            },
			zx : {
                required : icon + "请选择助学组织",
            }
        }
    })
}
//地州市考办
function regionList(){
	var parentid=null;
	$.ajax({
		url :contextPath+  "/region/region/getregionByregionid",
		type : "get",
		data : {
			'id' : $("#regionids").val()
		},
		success : function(r) {
			
			parentid=r.id
		}
	});
	var _html ="<option value = '-1'>-请选择-</option>";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'type' : "a"
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
				if(parentid==r[i].id){
					_html+="<option value = '"+r[i].id+"' selected>"+r[i].name+"</option>"
				}else{
					_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
				}
				
			}
			$("#schoolids").html(_html);
			region();
			groups(); 
		}
	});
}
//根据省考办获取县考办
function region(q){
	if(q=="q"){
		$("#specialityRecordid2").val("")
		$("#specialityid").val("")
		$("#specialityRecordid").val("")
		$("#schoolSpecialityRegid").val("")
	}
	var _html ="";
	$.ajax({
		url :contextPath+  "/region/region/regionList",
		type : "get",
		data : {
			'parentid' : $("#schoolids").val()
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
					if($("#rRid").val()==r[i].id){
						_html+="<option value = '"+r[i].id+"' selected>"+r[i].name+"</option>"
					}else{
						_html+="<option value = '"+r[i].id+"'>"+r[i].name+"</option>"
					}
				
			}
			$("#regionid").html(_html);
			groups(); 
		}
	});
}
function typeZhuxue(q){
	//判断是否清空以有数据
	if(q=="q"){
		$("#specialityRecordid2").val("")
		$("#specialityid").val("")
		$("#specialityRecordid").val("")
		$("#schoolSpecialityRegid").val("")
	}
	var zhuxueText =  $("#type").find("option:selected").text()
	if(zhuxueText=="应用型考试"){
		$("#schoolid2").attr("placeholder","点击选择！")
		$("#schoolid2").attr("onclick","zhuxue()")
		$("#collegeid").removeAttr("disabled")
		$("#teachSiteid").removeAttr("disabled")
	}else{
		$("#schoolid2").attr("placeholder","不可选择！")
		$("#schoolid2").removeAttr("onclick")
		$("#collegeid").attr("disabled","disabled")
		$("#teachSiteid").attr("disabled","disabled")
		$("#schoolid2").val("")
		$("#schoolid").val("")
		$("#collegeid option:first").prop("selected", 'selected')
		$("#teachSiteid option:first").prop("selected", 'selected')
		$("#collegeid option:gt(0)").remove();
	}
}
//助学组织
function zhuxue(){
	layer.open({
        type : 2,
        title : "助学组织",
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : contextPath+"/school/school/schoolOpen"
    });
}



//学院名称数据
function college(id){
	$.ajax({
		url : contextPath+"/school/collegeSchool/listCollege",
		type : "get",
		data : {
			schoolid : id
		},
		 async:false,//更改为同步
		success : function(data) {
			$("#collegeid").find("option").remove();//首先清空下拉数据
			$("#collegeid").append("<option value=''>请选择学院名称</option>");
			for (var i =0; i < data.length; i++) {
				$("#collegeid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
			}
			var hui= $("#collegeid2").val();
			var obj = $("#collegeid option");
			for(var i = 0; i < obj.length; i++){
			    var tmp = obj[i].value;
			    if(tmp == hui){
			        obj[i].selected = 'selected';
			        var index = $("#collegeid option:selected").val()
			        teachPoint(index);
			        break;
			    }
			}
			/* firstV= $("#collegeid option:first").val();
			teachPoint(firstV); */
		}
	});
}
$("#collegeid").on("change",function(){
	var val = $("#collegeid option:selected").val()
	teachPoint(val)
})
//教学点数据
function teachPoint(collegeid){
	if(collegeid==''){
		$("#teachSiteid").find("option").remove();//首先清空下拉数据
		$("#teachSiteid").append("<option value=''>请选择教学点</option>");
		return;
	}else if(collegeid=='q'){
		$("#specialityRecordid2").val("")
		$("#specialityid").val("")
		$("#specialityRecordid").val("")
		$("#schoolSpecialityRegid").val("")
	}else{
		$.ajax({
			url : contextPath+"/school/teachSite/listTeachSite",
			type : "get",
			data:{
				collegeid:collegeid
			},
			success : function(data) {
				$("#teachSiteid").find("option").remove();//首先清空下拉数据
				$("#teachSiteid").append("<option value=''>请选择教学点</option>");
				for (var i =0; i < data.length; i++) {
					$("#teachSiteid").append("<option value=" + data[i].id + ">" +data[i].name+ "</option>");
				}
				var hui= $("#teachSiteid2").val();
				var obj = $("#teachSiteid option");
				for(var i = 0; i < obj.length; i++){
					var tmp = obj[i].value;
					if(tmp == hui){
						obj[i].selected = 'selected';
						break;
					}
				}
			}
		});
	}
	
}




















