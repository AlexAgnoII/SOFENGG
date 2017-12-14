	
	
	
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
		    	  //User exist, no constraint.
		    	  if(data === "EXISTS") {
		    		  console.log("exists");
		    		  document.location.href = 'EmailSent.html';
		    		  return true;
		    	  }
		    	  
		    	  //User does NOT exist, put your magic here.
		    	  else if(data === "DOES-NOT-EXIST") {
		    		  console.log("does not exists");
		    		  alert("User does not exist");
		    		  return false;
		    	  }
		    	  
		    	  //Something went wrong if it goes here, don't do anything.
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
	