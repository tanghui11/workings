
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
            "meunType":"C2"
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
    reLoad();//联动二级下拉（传回来的onchange事件）
    setTimeout(hqDom,500); //通用下拉数据
    $(".hidden").find("input").css("display","block") // 把隐藏的input转换成type=hidde标签
    $(".hidden").find("input").attr("type","hidden")
})

//二级下拉事件
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



function load() {
    $('#mainIframe').attr("src",prefix+ "/reportCode?"+$('#form').serialize());

}
//二级下拉
function hqDom(){
    for(var i=1;i<$("select").length;i++){

        var dom = $("select").eq(i).attr("id")
        var bm = $("select").eq(i).attr("onload").split("'")[1]

        tyShuJu(dom,bm)
    }
}
//二级下拉加载数据
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


//通用打开页面
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

//通用下拉选项赋值hidden框
function changeTY(that,hdObj){
    var val = $(that).find("option:selected").text();
    //console.log($("#"+hdObj))
    $("#"+hdObj).val(val);
}

function saveForm(){
    load()
}

function refresh(){
    load()
}








