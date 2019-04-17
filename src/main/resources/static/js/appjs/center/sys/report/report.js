
var prefix = contextPath+"/sys/report/reports"
$(function() {
	
});
	$("#appids").change(function(){
		if($(this).val()=="region"){
			$("#code").val("R")
		}else if($(this).val()=="schools"){
			$("#code").val("S")
		}else if($(this).val()=="stexam"){
			$("#code").val("C")
		}else if($(this).val()==""){
			$("#code").val("")
		}
		
	})
	$("#paramInput").click(function(){
		layer.alert("无需填写，生成代码后自动提交！")
	})
	function save() {
		
		$("#param").val($("#source").val())
		var appid= $("#appids").val();
		var code= $("#code").val();
		var name= $("#name").val();
		var param= $("#param").val();
		var modelid= $("#modelid").val();
		var path= $("#path").val();
		var type1= $("#type1").val();
		var sql1= $("#sql1").val();
		var type2= $("#type2").val();
		var sql2= $("#sql2").val();
		var type3= $("#type3").val();
		var sql3= $("#sql3").val();
		var type4= $("#type4").val();
		var sql4= $("#sql4").val();
		var type5= $("#type5").val();
		var sql5= $("#sql5").val();
		
		var sqlNum=0
		for(var i=0;i<$(".sql").length;i++){
			if($(".sql").eq(i).val()!=""){
				sqlNum+=1
			}
		}
		
		if(appid==""){
			layer.msg("请选择数据库名称！")
			return;
		}else if(code==""){
			layer.msg("请填写报表编码！")
			return;
		}else if(code!=""){
			var codeNum = Number(code.substring(1))
			if(codeNum<100||codeNum>999){
				layer.msg("编码在100和999之间！")
				return;
			}
		}
		
		if(name==""){
			layer.msg("请填写表单名称！")
			return;
		}else if(param==""){
			layer.msg("请生成参数代码！")
			return;
		}else if(modelid==""){
			layer.msg("请填写菜单名称！")
			return;
		}else if(modelid!=""){
			var v4 = /^\d{4}$/i;
			if(v4.test(modelid)==false){
				layer.msg("只能输入四位数字！");
				return;
			}
		}
		if(path==""){
			layer.msg("请填写报表路径！")
			return;
		}else if(type1==""||sql1==""){
			layer.msg("请填写类型一和sql1！")
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : prefix,
			data : {
				"appid":appid,
				"code":code,
				"name":name,
				"param":param,
				"modelid":modelid,
				"path":path,
				"type1":type1,
				"sql1":sql1,
				"type2":type2,
				"sql2":sql2,
				"type3":type3,
				"sql3":sql3,
				"type4":type4,
				"sql4":sql4,
				"type5":type5,
				"sql5":sql5,
				"sqlNum":sqlNum
			},// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				console.log(data)
				if (data.code == 0) {
					layer.msg(data.msg);

				} else {
					layer.msg(data.msg);
				}

			}
		});

	}
