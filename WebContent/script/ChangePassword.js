    function checkPassword(value) {
	   return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$/.test(value);
    }

    function checkPasswordField(passwordField) {
        var value = passwordField.value;

        console.log("sendhelp");
        //Check if idNumber follows constraint.
        if(checkPassword(value)|| value == "") {

            if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
                //Add check or something in front end showing that user did iit correctly.
                //spanPassword.innerHTML ="";
                $('#npwError1').hide();
                $('#npwError2').hide();
                $('#newPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
                console.log("password_valid");
            } 

            }
            //If not follow, do front end magic to do show this.
            else {
                //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
                $('#npwError1').show();
                $('#newPassword').css("border-color", "indianred");
                console.log("password_invalid_1")
            }
        
        if (document.getElementById('nPasswordConfirm').value != "") {
            checkPasswordEqual(document.getElementById('nPasswordConfirm'), value);
        }
    }

    function checkPasswordEqual(pass2Field, pass1Field) {
        var pass2Val = pass2Field.value;
        var pass1Val = pass1Field.value;

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
		        alert("Success ChangePassword.js")
		      },
		      
		      error:function(){
		    	//This is server error, just add something that states that server is having issue.
		      	console.log("error ResetPassword.js");
		      	alert("Something went wrong (ChangePassword.js)")
		      }
		   });
	}

    function checkOldPassword(idPassword) {
        var oldPassword = idPassword.value;
        
        validateUser(oldPassword);
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
			        	//alert("Password not matching");
                        $('opwError1').show();
                        $('#oPassword').css("border-color", "indianred");
			        	return false;
			        }
			        
			        else {
			        	//alert("Password matching wtf");
                        $('opwError1').hide();
                        $('#oPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
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
			//alert("Passowrd failed!!!!!");
            $('opwError1').show();
            $('#oPassword').css("border-color", "indianred");
            
		}
		
		else {
			//Check if its equal with the newPassword.
			if(password === newPass) {
				proceed = false
				//alert("Old and new password are the same.");
                $('npwError2').show();
                $('#newPassword').css("border-color", "indianred");
			}
		}
		
		//Check newPass if its correct.
		if (!checkPassword(newPass)) {
			proceed = false;
			//alert("New password failed!")
            
		}
			
		//Check newPassRe if its correct.
		if(!checkPassword(newPassRe)) {
			//proceed = false;
			//alert("Confirm new password failed!")
            $('npwError1').show();
            $('#newPassword').css("border-color", "indianred");
		}
		

		
		
		return proceed;
			
	}

    $("document").ready(function() {
	   
    	$("#ResetPassword").click(function() {
    		var password = $("#oPassword").val(); 
    		var newPass = $("#newPassword").val();
    		var newPassRe = $("#nPasswordConfirm").val();
    		
    		
    		if(newPass === newPassRe) {
    			
    			//Only do this if all constraint is followeed.
    			if(constraintPassword(password, newPass, newPassRe)) {
    				submitNewPassword(newPass);
    			}
    			
    		}
    		else { //Design chuchu for not equal password here
    			//alert("New password and re-enter password not equal!");
                checkPassword(newPass);
                checkPasswordEqual(newPassRe, newPass);
    		}
    		
    	});
    	
	});