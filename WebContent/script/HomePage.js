
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}


//This function handles the front-end effects/notice when user does not place the correct input.
function enterValidInput() {
	console.log("I happened");
	alert("Please enter a valid input.");
}

//Account login failure front-end done here.
function accPassMismatch(data) {
	//Front end constraint warning here
	if(data === "FAIL-LOGIN") {
		//alert(data);
        Materialize.toast('Username and password do not match', 40000);
	}
	
	//redirect to adminhomepage.
	else if(data === "PASS-LOGIN-ADMIN"){
		document.location.href = 'AdminHomePage.jsp';
	}
	
	//redirect to user homepage.
	else {
		document.location.href = 'viewByStudent';
	}
}

//This function handles submitting the form.
function submitTheForm(username, password) {
	$.ajax({
 	   context: this,
        url:'login',
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

$("document").ready(function() {

	//Submit form!
	$("a#loginb").click(function() {
		
		var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        
        //Check if email exists, entered and password is entered.
        if(validateEmail(username) && password !== null && password !== ""){
        	// Check if email
        	submitTheForm(username, password);
        	 
        //Error cheecking for front page happens here:
        } else{
        	enterValidInput();
        }
	});
	
});