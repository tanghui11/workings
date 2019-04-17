var prefix = contextPath+ "student/studentSpecialityStudent";
$().ready(function() {
	/* $("#name").val(parent.names)
	$("#specialityName").val(parent.specialityids+" "+parent.specialityNames);
	load();
	update(); */
	load();
	var score =0;
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "student/studentSpeciality/applyExcelScore",
		data : {
			"studentid":parent.students,
			"specialityRecordid":parent.specialityRecord
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data[0].auditStatus=="b"){
				$("#certificateNo").html(data[0].certificateNo);
				$("#name").html(data[0].name);
				$("#gender").html(data[0].gender);
				$("#homeType").html(data[0].homeType);
				$("#nation").html(data[0].nation);
				$("#politics").html(data[0].politics);
				$("#studentid").html(data[0].studentid);
				$("#classify").html(data[0].classify);
				$("#specialityName").html(data[0].specialityName);
				$("#grad_certificate").html(data[0].grad_certificate);		
				$(".gradAuditDate").html(data[0].gradAuditDate.split(" ")[0]);				
				$("#score").html(data[0].score);	
				for(var i=0;i<data.length;i++){
					/* if(data[i].){} */
					score=parseInt(score)+parseInt(data[i].courseScore)
					$("#courseScore"+i).html(data[i].courseScore);
					$("#grade"+i).html(data[i].grade);
					$("#courseName"+i).html(data[i].courseName);
				} 
			}else{
				$("#biye1").hide();
				$("#biye2").hide();
			}
		}
	});
});
function update() {
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "school/schoolSpecialityRegSchool/list",
		data : {
			"id":parent.schoolSpecialityRegids,
			"limit":20,
			"offset":0
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			$("#educateLength").val(data.rows[0].educateLength);
			$("#regSeason").val(data.rows[0].regSeason);
			$("#regYear").val(data.rows[0].regYear);
			$("#classify").val(data.rows[0].classify);
			$("#type").val(data.rows[0].type);
		}
	});
	$.ajax({
		cache : true,
		type : "get",
		url : contextPath+ "/student/studentSpeciality/information",
		data : {
			"id":parent.idss
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			$("#studentid").val(data.studentid);
			$("#gradSchool").val(data.gradSchool)
		}
	});
}


function prints(){
	$("#prints").hide();
	document.getElementsByTagName('body')[0].style.zoom=1.02;
	window.print();
	window.location.reload(); 
	$("#prints").show();
}