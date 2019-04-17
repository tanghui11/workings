//提交倒计时
function countDown(obj,txt){
	var count = "9";
	var t1=window.setInterval(refreshCount, 1000);
    function refreshCount() {
	  $("#"+obj).attr("disabled", true);
	  $("#"+obj).html("0"+count)
	  if(count==0){
		  clearInterval(t1);
		  $("#"+obj).attr("disabled", false);
		  $("#"+obj).html(txt)
	  }
	  count--
    }
}