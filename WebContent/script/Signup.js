
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

//This function handles submitting the data.
function submitTheForm(email, password, password2, idnum, fName, mName, lName) {
	$.ajax({
 	   context: this,
        url:'s',
        data:{'email':username,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	//Front-end here.
        	accPassMismatch(data);
        },
        error:function(){
        	console.log("error searchResult.js");
        }
     });
}

//This will handle the errors that would happen if one of the field is violated.
//
function constraintChecker() {
	//if id number is valid, proceed.
	//Else return FALSE
	
	//if password is valid, proceed.
	//Else return FALSE
	
	//if fName is valid, proceed.
	//Else return FALSE
	
	//if lName is valid, proceed.
	//Else return FALSE
	
	//if courseName is valid, proceed.
	//Else return FALSE
	
	
	return true;
}

$("document").ready(function(idNum, password, fName, lName) {
	
	//To show the select(Dropdown).
	console.log("This happened II");
	$('select#dropDownCollege').material_select();

	$("a#backb").click(function() {
		window.location.href='HomePage.jsp';
	});
	
	$("#signupb").click(function(event) {

        var password = document.getElementById('password').value;
        var idNum = document.getElementById('idNum').value;
        var fName = document.getElementById('firstName').value;
        var mName = document.getElementById('middleName').value;
        var lName = document.getElementById('lastName').value;
        var email = document.getElementById('email').value;
        var password2 = document.getElementById('password2').value;
        var courseName = document.getElementById('courseName').value;
        
        //All input must be placed (No blanks, except for mName).
        if(validateEmail(email) && 
           password &&
    	   idNum && 
    	   fName && 
    	   lName &&
    	   courseName &&
    	   password2 === password ){
        	
        	//If all has inputs, place constraint warnings (If violated in a certain field).
        	//If constraintChecker returns true, proceed submitting.
        	if(constraintChecker(idNum, password, fName, lName)) {
        		$('.modal').modal();
        	}

        
        
        } else{
        	// TODO front end notif
        	alert("Please fill up all important fields.");
        }
	});
	
	//Send verification link here.
	$("#proceedModal").click(function() {
        // TODO update once verified mail
		$("form#signUpForm").submit();
	});

});