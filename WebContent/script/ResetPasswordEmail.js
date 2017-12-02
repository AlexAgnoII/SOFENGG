	
	
	
	function sendResetPassEmail(email) {
		$.ajax({
			  context: this,
		      url:'resetPassCheckEmail',
		      data: {"email":email},
		      type:'POST',
		      cache:false,
		      success: function(data){
		    	  console.log("Success ResetPasswordHTML.js");
		    	  console.log(data);
		    	  if(data === "EXISTS") {
		    		  console.log("exists")
		    		  return true;
		    	  }
		    	  
		    	  else if(data === "DOES-NOT-EXIST") {
		    		  console.log("does not exists")
		    		  alert("User does not exist");
		    		  return false;
		    	  }
		    	  
		    	  else {
		    		  alert("Something went wrong with Resetting Password Email!");
		    	  }
		      },
		      
		      error:function(){
		      	console.log("error ResetPasswordHTML.js");
		      	return false;
		      }
		   });
		return false;
	}


	$("document").ready(function() {
		
		//Confirm button clicked.
		$("#Confirm").click(function() {
			var email = $("#RPemail").val();
			//Check email exist.
			sendResetPassEmail(email);

		});
		
		
	});
