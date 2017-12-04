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
            $('#pwError1').hide();
            $('#nPassword').css("border-color", "rgba(0, 0, 0, 0.3)");
            console.log("password_valid");
		} 
		
        }
        //If not follow, do front end magic to do show this.
        else {
            //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
            $('#pwError1').show();
            $('#nPassword').css("border-color", "indianred");
            console.log("password_invalid_1")
        }
    }

    function checkPasswordEqual(pass2Field, pass1Field) {
        var pass2Val = pass2Field.value;
        var pass1Val = pass1Field.value;

        //Equal!
        if(pass1Val=== pass2Val) {
            $('#cpwError1').hide();
            $('#nPasswordConfirm').css("border-color", "rgba(0, 0, 0, 0.3)");
            console.log("password_match");
        }

        //Not equal!
        else {
            //spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
            $('#cpwError1').show();
            $('#nPasswordConfirm').css("border-color", "indianred");
            console.log("password_mismatch");
        }
    }

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
		        alert(data);
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