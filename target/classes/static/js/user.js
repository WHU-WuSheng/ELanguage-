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
		$("#applyResult").html("");
		$.ajax({
			type: 'POST',
			data:{teacher:$("#userInfo").children(":first").children(":first").children(":last").text(),
				language:$("#language option:selected").text(),
				startTime:($("#startTime").val()).replace("T"," ")+":00",
				endTime:($("#endTime").val()).replace("T"," ")+":00",
				money:$("#money").val()},
			url: "/record/applyRecord",
			success:function(html){
				$("#applyResult").append(html); 
				$("#applyStyle").hide();
				//var newWin = window.open('', '_blank');
				//newWin.document.write(result);
			}
		})
	});
	
	
	$("#modifyUserInfo").click(function(){
		$.ajax({
			type: 'POST',
			data:{
				username:$("#username").text(),
				phone:$("#phone").val(),
				email:$("#email").val(),
				profile:$("#profile").val(),
				sex:$("#sex option:selected").val(),
				age:$("#age").val()},
			url: "/user/modifyUserInfo",
			success:function(){
				window.location.reload();
			}
		})
	});
	
	
	$("#recharge").click(function(){
		$("#rechargeMargin").show();
	});
	
	$("#rechargeConfirm").click(function(){
		
		$.ajax({
			type: 'POST',
			data:{
				rechargeCount:$("#rechargeCount").val()-0
			},
			url: "/user/modifyRecharge",
			success:function(){
				window.location.reload();
			}
		})
	});
	
	$("#showRemark").click(function(){
		$("#remarks").html("");
			$.ajax({
				type: 'POST',
				data:{teacher:$("#username").text()},
				url: "/record/showRemark",
				success:function(html){
					$("#remarks").append(html); 
				}
			})
		});

	
	
	function getObjectURL(file) {  
	     var url = null;  
	     if (window.createObjectURL!=undefined) { // basic
	         url = window.createObjectURL(file) ;
	       }else if (window.webkitURL!=undefined) { // webkit or chrome
	         url = window.webkitURL.createObjectURL(file) ;
	       }else if (window.URL!=undefined) { // mozilla(firefox)
	         url = window.URL.createObjectURL(file) ;
	       }
	       return url ;
	 }
	$("#imageButton").on("change",function(){
		var file = this.files[0];
		$("#image").attr("src",getObjectURL(file));
	});
	
	
	
	
})