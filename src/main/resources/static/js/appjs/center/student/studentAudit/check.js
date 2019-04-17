$().ready(function() {
	update();
});


function update() {
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "/student/studentSpeciality/selectStudentInfo",
		data : {
			"id":parent.ids
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log(data)
			$("#id_img_pers").attr("src",data.pic);
			$("#pinyin").val(data.pinyin);
			$("#name").val(data.name);
			$("#birthday").val(data.birthday);
			$("#address").val(data.address);
			$("#applyDate").val(data.applyDate);
			$("#applyOperator").val(data.applyOperator);
			$("#auditDate").val(data.auditDate);
			$("#auditOperator").val(data.auditOperator);
			$("#auditStatus").val(data.auditStatus);
			$("#certificateNo").val(data.certificate_no);
			$("#certificate_type").val(data.certificate_type);
			$("#childRegionid").val(data.childRegionid);
			$("#child_regionid").val(data.child_regionid);
			$("#classify").val(data.classify);
			$("#collegeName").val(data.collegeName);
			$("#courseid").val(data.courseid);
			$("#createDate").val(data.createDate);
			$("#dbFlag").val(data.dbFlag);
			$("#direction").val(data.direction);
			$("#educateLength").val(data.educateLength);
			$("#education").val(data.education);
			$("#email").val(data.email);
			$("#studentid").val(data.studentid);
			$("#specialityRecordid").val(data.specialityRecordid);
			$("#gradSchool").val(data.gradSchool);
			$("#gradSpecialityid").val(data.gradSpecialityid);
			$("#gradCertificate").val(data.gradCertificate);
			$("#gradCertificate").val(data.gradCertificate);
			$("#kjh").val(data.kjh);
			$("#regionid").val(data.regionid);
			$("#gender").val(data.gender);
			$("#nation").val(data.nation);
			$("#type").val(data.type);
			$("#politics").val(data.politics);
			$("#profession").val(data.profession);
		}
	});

}
