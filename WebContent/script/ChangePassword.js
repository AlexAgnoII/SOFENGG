	



	function submitNewPassword(password, newPass) {
		console.log(password);
		console.log(newPass);
	}

    $("document").ready(function() {
	   
    	$("#ResetPassword").click(function() {
    		var password = $("#oldPassword").val(); 
    		var newPass = $("#newPassword").val();
    		var newPassRe = $("#nPasswordConfirm").val();
    		
    		
    		if(newPass === newPassRe) {
    			submitNewPassword(password, newPass);
    		}
    		else { //Design chuchu for not equal password here
    			alert("New password and re-enter password not equal!");
    		}
    		
    	});
    	
	});