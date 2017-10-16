
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

$("document").ready(function() {

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
        
        if(validateEmail(email) && password &&
    	   idNum && fName && mName && lName &&
    	    password2 === password){
        	$("form#signUpForm").submit();
            //$('.modal').modal();

        } else{
        	// TODO front end notif
        }
	});
	
	$("#proceedModal").click(function() {
        // TODO update once verified mail
		//$("form#signUpForm").submit();
	});

});