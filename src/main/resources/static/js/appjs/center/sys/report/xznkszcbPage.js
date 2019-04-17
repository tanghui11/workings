
var prefix = contextPath+"/sys/report"
$(function() {
	baoBiaoXiala();
});
function changeFrameHeight(that){
    $(that).height(document.documentElement.clientHeight - 120);

}
function baoBiaoXiala(){
	$.ajax({
		cache : true,
		type : "get",
		url :prefix+ "/menuSelected",
		async : false,
		data:{
			"meunType":"S102"
		},
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			//console.log(data)
			var _html="<option value=''>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].code+"'"+">"+data[i].name+"</option>";
			}
			$("#code").html(_html); 
		}
	});
}
var code ='';
$("#code").on('change',function(){
	code=$(this).val()
	if(code==""){
		$("#forms").html("")
		$("#bk").css("border-right","")
		return;
	}
	$("#bk").css("border-right","1px solid #ccc")
	reLoad();
	setTimeout(hqDom,500); 
})
function reLoad() {
	$.ajax({
		cache : true,
		type : "get",
		url :prefix+ "/reportCodeInclude",
		async : false,
		data:{
			"code":code
		},
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data.param!=undefined){
				//console.log(data.param)
				var str = (data.param).split("#")[1]
				$("#forms").html(str+'<div class="columns pull-left" style="margin-left: 10px;"><button type="button" class="btn btn-success" id="btn" onclick="saveForm()">查询</button></div>')
				/* if(str.indexOf("tyShuJu")!= -1){
					tyShuJu();
				} */
			}
		}
	});
}


function saveForm(){
    load()
}

function refresh(){
	load()
}
function load() {
    $('#mainIframe').attr("src",prefix+ "/reportCode?"+$('#form').serialize());
}
function hqDom(){
	for(var i=1;i<$("select").length;i++){
		var dom = $("select").eq(i).attr("id")
		var bm = $("select").eq(i).attr("onload").split("'")[1]
		tyShuJu(dom,bm)
	}
}

function tyShuJu(that,z){
	$.ajax({
		cache : true,
		type : "get",
		url :prefix+ "/xiaLa?z="+z,
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			//console.log(data)
			var _html="<option value=''>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				_html+="<option value="+"'"+data[i].id+"'"+">"+data[i].name+"</option>";
			}
			$("#"+that+"").html(_html); 
		}
	});
}

function openTY(title,src,w,h){
	//console.log(w+","+h)
	if(w==undefined||h==undefined||w==""||h==""){
		w="90%"
		h="90%"
	}
	layer.open({
        type : 2,
        title : title,
        shadeClose : false, 
        area : [ w, h ],
        content : contextPath +"/"+ src 
    });
}