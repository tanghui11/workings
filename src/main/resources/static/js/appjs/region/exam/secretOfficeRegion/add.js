$().ready(function() {
	validateRule();
	kSrw();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath+"/exam/secretOfficeRegion/save",
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
			name : {
				required : true,
				maxlength:33
			},
			regionid:{
				required : true
			},
			phonePre:{
				required : true,
				maxlength:4
			},
			phone:{
				required : true,
				isTel:true
			},
			chargeman:{
				required : true,
				maxlength:30
			},
			chargemanPhonePre:{
				required : true,
				maxlength:4
			},
			chargemanPhone:{
				required : true,
				isTel:true
			},
			chargemanTel:{
				required : true,
				isMobile:true
			},
			dutyMan:{
				maxlength:160
			},
			forceNum:{
				digits:true
			},
			leader:{
				maxlength:30
			},
			leaderPhone:{
				isTel:true
			},
			leaderMphone:{
				isMobile:true
			},

		},
		messages : {
			name : {
				required : icon + "请输入名称",
				maxlength:icon + "名称过长"
			},
			regionid : {
				required : icon + "请输入考区"
			},
			phonePre : {
				required : icon + "请输入保密室固定电话区号",
				maxlength:icon + "保密室固定电话区号只能是四位"
			},
			phone:{
				required : icon + "请输入保密室固定电话号码",
				isTel:icon + "固定电话号码格式不正确"
			},
			chargeman:{
				required : icon + "请输入负责人",
				maxlength:icon + "负责人长度过长"
			},
			chargemanPhonePre:{
				required : icon + "请输入负责人固定电话区号",
				maxlength:icon + "负责人固定电话区号只能是四位"
			},
			chargemanPhone:{
				required : icon + "请输入负责人固定电话号码",
				isTel:icon + "负责人固定电话号码格式不正确"
			},
			chargemanTel:{
				required : icon + "请输入负责人手机",
				isMobile:icon + "负责人手机格式不正确"
			},
			dutyMan:{
				maxlength:icon + "值班名单过多"
			},
			forceNum:{
				digits:icon + "只能是数字"
			},
			leader:{
				maxlength:icon + "考区主任姓名过长"
			},
			leaderPhone:{
				isTel:icon + "考区主任电话号码格式不正确"
			},
			leaderMphone:{
				isMobile:icon + "考区主任手机格式不正确"
			}
		}
	})
}
//考试任务
function kSrw(){
	$.ajax({
		cache : true,
		type : "get",
		url :contextPath+ "exam/task/serchTaskAll",// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var _html="";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].examYear+"年"+data[i].examMonth+"</option>";
			}
			$("#examTaskid").html(_html);
		}
	});
}