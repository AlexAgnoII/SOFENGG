
	//Global Variable
	var FLAG_IS_MY_PASS;
	var PROCEED_CHANGING_PASS;

	function checkPassword(value) {
	   return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$/.test(value);
    }

    function checkPasswordField(passwordField) {
        var value = passwordField.value;

        console.log("Start here xd");
        console.log("CheckPasswordField");
        console.log("Value of new password: " + value);
        console.log(" ");
        //Check if password follows constraint.
        if(checkPassword(value)|| value == "") {

 
          //Add check or something in front end showing that user did iit correctly.
          //spanPassword.innerHTML ="";
          $('#npwError1').hide();
          
          //Only remove
          if($("#newPassword").val() != $("#oPassword").val())
        	  $('#npwError2').hide();
          
          $('#newPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
          console.log("password_valid");


        }
        //If not follow, do front end magic to do show this.
        else {
            //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
            $('#npwError1').show();
		    $('#npwError2').hide();
            $('#newPassword').css("border-color", "indianred");
            console.log("password_invalid_1")
        }
        
        if (document.getElementById('nPasswordConfirm').value != "") {
    	console.log("WWTF: " + document.getElementById('nPasswordConfirm').value);
            checkPasswordEqual(document.getElementById('nPasswordConfirm'), passwordField);
        }
    }

    function checkCurrentPasswordField(passwordField) {
        var value = passwordField.value;

        //Check if password follows constraint.
        if(checkPassword(value)|| value == "") {

          //Add check or something in front end showing that user did iit correctly.
          //spanPassword.innerHTML ="";
          $('#opwError2').hide();
          $("#opwError1").hide();
          $('#oPassword').css("border-color", "rgba(0, 0, 0, 0.3)");

        }
        //If not follow, do front end magic to do show this.
        else {
            //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
            $('#opwError2').show();
		    $('#opwError1').hide();
            $('#oPassword').css("border-color", "indianred");
            console.log("password_invalid_1")
        }
    }

    function checkPasswordEqual(pass2Field, pass1Field) {
        var pass2Val = pass2Field.value;
        var pass1Val = pass1Field.value;
        
        console.log("CHeckPasswordEqual");
        console.log("pass1: " + pass1Val);
        console.log("pass2: " + pass2Val);
        console.log(" ");

        //Equal!
        if(pass1Val=== pass2Val) {
            $('#npwcError1').hide();
            $('#nPasswordConfirm').css("border-color", "rgba(0, 0, 0, 0.3)");
            console.log("password_match");
        }

        //Not equal!
        else {
            //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
            $('#npwcError1').show();
            $('#nPasswordConfirm').css("border-color", "indianred");
            console.log("password_mismatch");
        }
    }



	function submitNewPassword(newPass) {
		$.ajax({
			  context: this,
		      url:'changePassword',
		      data: {"newPass":newPass},
		      type:'POST',
		      cache:false,
		      success: function(data){
                  
		    	  //Email sent
		     //   alert("Success ChangePassword.js")
		      },
		      
		      error:function(){
		    	//This is server error, just add something that states that server is having issue.
		      	console.log("error ResetPassword.js");
		      	//alert("Something went wrong (ChangePassword.js)")
		      }
		   });
	}

//    function checkOldPassword(idPassword) {
//        var oldPassword = idPassword.value;
//        
//        if(oldPassword != "")
//        	validateUser(oldPassword);
//        else {
//            $('#opwError1').hide();
//            $('#oPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
//        }
//    }
	
	function checkMyPassword(idPassword) {
		var myPass = idPassword.value;
		
		if(myPass != "") {
          $('#opwError1').hide();
          $('#oPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
		}
	}

	function validateUser(password) {
		return $.ajax({
				  context: this,
			      url:'checkPasswordMatch',
			      data: {"password":password},
			      type:'POST',
			      cache:false,
			      success: function(data){
			        //console.log("Success ValidateUser");
			        
			        //Password is wrong.
			        if(data === "WRONG") {
			        	//alert("Password not matching");
			        	console.log("This is not the user's current password");
                        $('#opwError1').show();
                        $('#oPassword').css("border-color", "indianred");
			        	FLAG_IS_MY_PASS = false;
			        }
			        
			        else {
			        	console.log("Ths is the user's password!")
			        	//alert("Password matching wtf");
                        $('#opwError1').hide();
                        $('#oPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
			        	FLAG_IS_MY_PASS = true;
			        }
			      },
			      
			      error:function(){
			    	//This is server error, just add something that states that server is having issue.
			      	console.log("Error ChangePassword validateUser.");
			      	//alert("Something went wrong (ChangePassword.js)")
			      	FLAG_IS_MY_PASS = false;
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
//	function constraintPassword(password, newPass, newPassRe) {
//		var proceed = true;
//		var promise = new Promise(function(resolve, reject){
//			console.log("I do this first..");
//			resolve("Done!");
//			validateUser(password); //Check if entered password matches account	
//		});
//		
//		//Promises are made so that this happens before that.
//		promise.then(function(result) {
//			console.log("Then i Do this...");
//			console.log("FLAG_MY_PASS: " + FLAG_IS_MY_PASS);
//			if(!FLAG_IS_MY_PASS) {
//				//Password did not match the current user's password.
//				proceed = false;
//				//alert("Passowrd failed!!!!!");
//	            $('opwError1').show();
//	            $('#oPassword').css("border-color", "indianred");
//	            
//			}
//			
//			//Check newPass if its correct.
//			if (!checkPassword(newPass)) {
//				proceed = false;
//				//alert("New password failed!")
//	            
//			}
//				
//			//Check newPassRe if its correct.
//			if(!checkPassword(newPassRe)) {
//				proceed = false;
//				//alert("Confirm new password failed!")
//	            $('npwError1').show();
//	            $('#newPassword').css("border-color", "indianred");
//			}
//			
//			if(newPass != newPassRe) {
//				alert("Re and New pass is equal so no continuino")
//				proceed = false;
//			}
//			
//			console.log("Proceed: " + proceed);
//			return proceed;
//		});
//			
//	}

    $("document").ready(function() {
        $('.modal').modal();
	   
    	$("#ResetPassword").click(function() {
    		var password = $("#oPassword").val(); 
    		var newPass = $("#newPassword").val();
    		var newPassRe = $("#nPasswordConfirm").val();
    		
    		FLAG_IS_MY_PASS = true;
    		PROCEED_CHANGING_PASS = true;
    		
    		
    		console.log("Password: " + password);
    		console.log("NewPassword: " + newPass);
    		console.log("NewPassWordConfirm: " + newPassRe);
    		
    		
    		var promise = new Promise(function(resolve, reject){
    			console.log("I do this first..");
    			 //Check if entered password matches account	
    			resolve(validateUser(password));
    		});
    		
    		//Promises are made so that this happens before that.
    		promise.then(function(result) {
    			console.log("Then i Do this...");
    			console.log("FLAG_MY_PASS: " + FLAG_IS_MY_PASS);
    			if(!FLAG_IS_MY_PASS) {
    				//Password did not match the current user's password.
    				PROCEED_CHANGING_PASS = false;
    				//alert("Passowrd failed!!!!!");
    	            $('opwError1').show();
    	            $('#oPassword').css("border-color", "indianred");
    	            
    			}
    			
    			//Check newPass if its correct.
    			if (!checkPassword(newPass)) {
    				PROCEED_CHANGING_PASS= false;
    				//alert("New password failed!")
    	            
    			}
    				
    			//Check newPassRe if its correct.
    			if(!checkPassword(newPassRe)) {
    				PROCEED_CHANGING_PASS = false;
    				//alert("Confirm new password failed!")
    	            $('npwError1').show();
    	            $('#newPassword').css("border-color", "indianred");
    			}
    			
    			if(newPass != newPassRe) {
    				//alert("Re and New pass is equal so no continuino")
    				PROCEED_CHANGING_PASS = false;
    			}
    			

    			return PROCEED_CHANGING_PASS;
    		}).then(function(result) {
    			console.log("Can I change my password?: " + result);
            	if(PROCEED_CHANGING_PASS) {
            		if(password != newPass && FLAG_IS_MY_PASS)
            			submitNewPassword(newPass);
            		else {
            		//	alert("Current and New Password matches, not proceeding po.");
            			$('#npwError2').show();
                        $('#newPassword').css("border-color", "indianred");
            		}
            	}
            			
            	else {
            		//alert("Constraint password did not allow you through");
            	}	
    		});
    		
    	});
    	
	});