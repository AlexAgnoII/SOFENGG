
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

$("document").ready(function() {

	//Submit form!
	$("a#loginb").click(function() {
		
		var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        
        if(validateEmail(username) && password !== null && password !== ""){
        	// Check if email
        	 $("form#loginForm").submit();
        } else{
        	// TODO front end notif
        }
	});
	
});