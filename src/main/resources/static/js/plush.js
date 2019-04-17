//进度条
var isFirstExport=true;
//显示进度条
var isFirstExport=true;
function showProgress(){
    $("#Mask").css("height",$(document).height());
    $("#Mask").css("width",$(document).width());
    $("#Mask").show();
    if(isFirstExport){
        $("#Progress").circliful();
    }else{
        $("#Progress .circle-text").text("0%");
        $("#Progress .circle-info").text("导出进度");
        $("#Progress").show();
    }
}
//隐藏进度条
function hideProgress(){
    $("#Mask").hide();
    $("#Progress").hide();
}

function onZipAll(flag) {

    //这里开始下载文件
    // var formData=$("#form_id").serialize();
    // location.href="${root}/record/v_seal_excel_all.do?"+
    if(flag==undefined){
        $("#form").submit()
    }else{
            document.getElementById('form').action=contextPath+"/"+flag;
//若需提交表单（如导出）
            document.getElementById('form').submit();

    }

    //Ajax刷新进度条
    showProgress();
    window.setTimeout(function(){
        var timer=window.setInterval(function(){
            $.ajax({
                type:'post',
                dataType:'json',
                url: contextPath + "/common/flush",
                success: function(data) {
                    $("#Progress .circle-text").text(data.percentText);
                    if(data.curCount===undefined||data.totalCount===undefined){
                        $("#Progress .circle-info").text("导出进度");
                    }
                    else{
                        $("#Progress .circle-info").text("导出进度:"+data.curCount+"/"+data.totalCount);
                    }
                    if(data.percent=="100"){
                        window.clearInterval(timer);
                        hideProgress();
                    }
                },
                error:function(data){}
            });
        },200);
    },200);
    isFirstExport=false;
}
