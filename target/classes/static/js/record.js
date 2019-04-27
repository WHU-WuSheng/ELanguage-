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
		alert($("#state option:selected").val());
		$.ajax({
			
			url:"/record/solveRecord",
			data:{teacher:$("#teacher").text(),
				student:$("#student").text(),
				state:$("#state option:selected").val(),
				applyTime:$("#applyTime").text(),
				},
			success:function(result){
				//var newWin = window.open('', '_blank');
				//newWin.document.write(result);
				window.location.reload();
			}
		})
	})
})