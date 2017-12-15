	
	
	
	function sendResetPassEmail(email) {
		$.ajax({
			  context: this,
		      url:'sendResetPassConfirm',
		      data: {"email":email},
		      type:'POST',
		      cache:false,
		      success: function(data){
		    	  console.log("Success ResetPasswordHTML.js");
		      },
		      
		      error:function(){
		      	console.log("error ResetPasswordHTML.js");
		      }
		   });
	}
	
	function checkEmailExisting(email) {
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
		    		  sendResetPassEmail(email)
		    		  document.location.href = 'EmailSent.html';
		    	  }
		    	  
		    	  //User does NOT exist, put your magic here.
		    	  else if(data === "DOES-NOT-EXIST") {
		    		  console.log("does not exists");
		    		  alert("User does not exist");
		    	  }
		    	  
		    	  else if(data === "ALREADY-SENT") {
		    		alert("Email already sent! Please check email!");  
		    	  }
		    	  
		    	  //Something went wrong if it goes here, don't do anything.
		    	  else {
		    		  alert("Something went wrong with Resetting Password Email!");
		    	  }
		      },
		      
		      error:function(){
		      	console.log("error ResetPasswordHTML.js");
		      }
		   });
	}


	$("document").ready(function() {
		
		//Confirm button clicked.
		$("#Confirm").click(function() {
			var email = $("#RPemail").val();
			//Check email exist.
			checkEmailExisting(email);
			//document.location.href = 'EmailSent.html';
		});		
	});
	