$(function(){
	$("#learnApply").click(function(){
		var str="/capability/show/"+$("#userInfo").children(":first").children(":first").children(":last").text()+"?type=1";
		$.ajax({
			url:str,
			success:function(html){
				$("#applyStyle").append(html);
			}
		})
	});
	
	
	$("#applyConfirm").click(function(){
		$.ajax({
			type: 'POST',
			data:{teacher:$("#userInfo").children(":first").children(":first").children(":last").text(),
				student:$("#username").val(),
				language:$("#language option:selected").text(),
				startTime:($("#startTime").val()).replace("T"," ")+":00",
				endTime:($("#endTime").val()).replace("T"," ")+":00",
				money:$("#money").val()},
			url: "/record/applyRecord",
			success:function(html){
				$("#applyResult").append(html); 
				//var newWin = window.open('', '_blank');
				//newWin.document.write(result);
			}
		})
	});
})