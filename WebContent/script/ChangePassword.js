	



	function submitNewPassword(newPass) {
		$.ajax({
			  context: this,
		      url:'sendChangePassword',
		      data: {"newPass":newPass},
		      type:'POST',
		      cache:false,
		      success: function(data){
		    	  //Email sent
		        alert("Success ChangePassword.js")
		      },
		      
		      error:function(){
		    	//This is server error, just add something that states that server is having issue.
		      	console.log("error ResetPassword.js");
		      	alert("Something went wrong (ChangePassword.js)")
		      }
		   });
	}
	
	function validateUser(password) {
		return $.ajax({
				  context: this,
			      url:'checkPasswordMatch',
			      data: {"password":password},
			      type:'POST',
			      cache:false,
			      success: function(data){
			        console.log("Success ChangePassword.js")
			        
			        //Password is wrong.
			        if(data === "WRONG") {
			        	alert("Password not matching");
			        	return false;
			        }
			        
			        else {
			        	alert("Password matching wtf");
			        	return true;
			        }
			      },
			      
			      error:function(){
			    	//This is server error, just add something that states that server is having issue.
			      	console.log("error ResetPassword.js");
			      	alert("Something went wrong (ChangePassword.js)")
			      	return false;
			      }
			   });
	}
	
	
	/**
	 * Check password if its correct.
	 * @param value
	 * @returns true if its correct, false if not.
	 */
	function checkPassword(value) {
		return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$/.test(value);
	}
	
	//This is where the checking happens.
	function constraintPassword(password, newPass, newPassRe) {
		var proceed = true;
					
		//Check if password matches.
		if(!validateUser(password)) {
			proceed = false;
			alert("Passowrd failed!!!!!")
		}
		
		//Check newPass if its correct.
		if (!checkPassword(newPass)) {
			proceed = false;
			alert("New password failed!")
		}
			
		//Check newPassRe if its correct.
		if(!checkPassword(newPassRe)) {
			proceed = false;
			alert("Confirm new password failed!")
		}
		
		if(password === newPass) {
			proceed = false
			alert("Old and new password are the same.")
		}
		
		
		return proceed;
			
	}

    $("document").ready(function() {
	   
    	$("#ResetPassword").click(function() {
    		var password = $("#oldPassword").val(); 
    		var newPass = $("#newPassword").val();
    		var newPassRe = $("#nPasswordConfirm").val();
    		
    		
    		if(newPass === newPassRe) {
    			
    			//Only do this if all constraint is followeed.
    			if(constraintPassword(password, newPass, newPassRe)) {
    				submitNewPassword(newPass);
    			}
    			
    		}
    		else { //Design chuchu for not equal password here
    			alert("New password and re-enter password not equal!");
    		}
    		
    	});
    	
	});