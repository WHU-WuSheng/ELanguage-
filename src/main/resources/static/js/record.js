$(function(){
	$("#remark").click(function(){
		$.ajax({
			url:"/record/modifyRecord",
			data:{teacher:$("#teacher").text(),
				student:$("#student").text(),
				star:$("#star option:selected").text(),
				applyTime:$("#applyTime").text(),
				remark:$("#remarkContent").val(),
				},
			success:function(result){
				//var newWin = window.open('', '_blank');
				//newWin.document.write(result);
				window.location.reload();
			}
		})
	})
	

	
	$("#solve").click(function(){
		$.ajax({
			url:"/record/solveRecord",
			data:{teacher:$("#teacher").text(),
				student:$("#student").text(),
				state:$("#state option:selected").val(),
				applyTime:$("#applyTime").text(),
				},
			success:function(result){
				alert("处理成功");
				window.open("/record/showTeach/"+$("#teacher").text());
			}
		})
	})
	
	$("#cancel0").click(function(){
		$.ajax({
			url:"/record/solveRecord",
			data:{
				teacher:$("#teacher").text(),
				student:$("#student").text(),
				state:5,
				applyTime:$("#applyTime").text(),
				discount:1,
				},
			success:function(result){
				alert("取消订单成功，金额已退还");
				window.open("/record/showStudy/"+$("#student").text())
			}
		})
	})
	
	$("#cancel1").click(function(){
		$.ajax({
			url:"/record/solveRecord",
			data:{
				teacher:$("#teacher").text(),
				student:$("#student").text(),
				state:5,
				applyTime:$("#applyTime").text(),
				discount:0.9,
				},
			success:function(result){
				alert("取消订单成功，90%金额已退还");
				window.open("/record/showStudy/"+$("#student").text())
			}
		})
	})
	
	
})