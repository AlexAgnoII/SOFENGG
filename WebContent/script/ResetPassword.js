	


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
			if(true) {
				submitUpdate(password, token);
			}
			
			//if something wrong, then dont send update.
			else {
				
			}
		});
	});