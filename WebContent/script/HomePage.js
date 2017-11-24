
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}


//This function handles the front-end effects/notice when user does not place the correct input.
function enterValidInput() {
	//Materialize.toast('Please enter a valid email / pasword', 7000);
	//$('#dnm').css({"color":"indianred"});
    $('#dnm').show();
}

//Account login failure front-end done here.
function accPassMismatch(data) {
	//Front end constraint warning here
	if(data === "FAIL-LOGIN") {
		//alert(data);
       // Materialize.toast('Username and password do not match', 7000);
		//$('#dnm').css({"color":"indianred"});
         $('#dnm').show();
	}
	
	//redirect to adminhomepage.
	else if(data === "PASS-LOGIN-ADMIN"){
		document.location.href = 'AdminHomePage.jsp';
	}
	
	//redirect to user homepage.
	else {
		document.location.href = 'UserHomePage.jsp';
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
	

    $(document).keyup(function (e) {
		var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        var activeElement = document.activeElement;
        
        // When enter key is pressed
        if((activeElement.id == 'username' || activeElement.id == 'password') && (e.keyCode == 13))
	        if(validateEmail(username) && password !== null && password !== "")
	        	 submitTheForm(username, password);
	        else enterValidInput();
        
    });
});