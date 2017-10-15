

$("document").ready(function() {
	
	//Submit form!
	$("a#loginb").click(function() {
		
		var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        
        if(username !== null && password !== null && username !== "" && password !== ""){
        	// Check if email
        	 $("form#loginForm").submit();
        } else{
        	// TODO front end notif
        }
	});
	
	
	
});