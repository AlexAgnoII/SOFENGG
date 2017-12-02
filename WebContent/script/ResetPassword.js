	


	function submitUpdate(password, token) {
		$.ajax({
			  context: this,
		      url:'resetPasswordAction',
		      data: {"password":password,
		    	      "token":token},
		      type:'POST',
		      cache:false,
		      success: function(data){
		        console.log("Success ResetPassword.js")
		        //Show modal that its successful.
		      },
		      
		      error:function(){
		    	//This is server error, just add something that states that server is having issue.
		      	console.log("error ResetPassword.js");
		      	alert("Something went wrong (ResetPassword.js)")
		      }
		   });
	}



    $("document").ready(function() {
		$("#ResetPassword").click(function() {
			var token = $("#token").val();
			var password = $("#nPassword").val();
			var confirmPassword = $("#nPasswordConfirm").val();
			
			//Check for password constraint first..
			
			//if nothing wrong with constraint, send update.
			if(true) { //<<<<----- please create a function or something that returns true if constraint is accomplished. or it was done wrong.
				       //kayo na bahala mag implement, you may change the code if u wish.
				submitUpdate(password, token);
			}
			
			//if something wrong, then dont send update.
			else {
				
			}
		});
	});