
//This function handles the front-end effects/notice when user does not place the correct input.
function enterValidInput() {
	var title = document.getElementById('anntitle').value;
    var body = document.getElementById('annbody').value;
    
    if(title === null || title === ""){
    	// TODO title input error notif
    	alert("Title");
    }
    	
	if(body === null || body === ""){
		// TODO body input error notif
		alert("Body");
	}
}

/*******************************************/

function loadPosts(){
	
	$.ajax({
 	    context: this,
        url:'getPosts',
        type: 'GET',
        cache:false,
        success: function(data){
        	var postFeed = document.getElementById("postFeed");
        	
        	// Remove all children
        	while (postFeed.firstChild) {	
        		postFeed.removeChild(postFeed.firstChild);
        	}

        	// Append html snippet 
    	    $(postFeed).append(data);
    	},
        error:function(){
            console.log("URL getPosts does not exist");
        }
    });
}


/*******************************************/

function submitTheForm(title, body) {
	$.ajax({
 	    context: this,
        url:'createPost',
        data:{'title':title,
        	  'body': body},
        type:'POST',
        cache:false,
        success: function(data){
        	loadPosts();
        },
        error:function(){
        	console.log("error searchResult.js");
        }
     });
}

$("document").ready(function() {

	loadPosts();
	
	// Creating new post
	$("#ANpost").click(function() {
		
		var title = document.getElementById('anntitle').value;
        var body = document.getElementById('annbody').value;
        
        if(title !== null && title !== "" &&
		   body !== null && body !== ""){
        	submitTheForm(title, body);
        	 
        //Error checking for front page happens here:
        } else{
        	enterValidInput();
        }
	});
	
});