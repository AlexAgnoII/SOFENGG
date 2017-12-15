
//This function handles the front-end effects/notice when user does not place the correct input.
function enterValidInput(type) {

	var title;
    var body;
    var boolean = true;
    
    if(type == "new"){
		title = document.getElementById('anntitle').value;
	    body = document.getElementById('annbody').value;
    } else if(type == "update"){
		title = document.getElementById('updateTitle').value;
	    body = document.getElementById('updateBody').value;
    }
    
    if(title === null || title === "" || title.length > 50){
    	// TODO title input error notif
    	alert("Title should not be empty and not be more than 50 characters");
    	boolean = false;
    }
    	
	if(body === null || body === "" || body.length > 1000){
		// TODO body input error notif
		alert("Body should not be empty and not be more than 1000 characters");
    	boolean = false;
	}
	
	return boolean;
}

/*******************************************/

function loadPosts(){
	
	$.ajax({
 	    context: this,
        url:'getPosts',
        data:{'user': 'admin'},
        type: 'GET',
        cache:false,
        success: function(data){
        	var postFeed = document.getElementById("postFeed");
        	if(postFeed != null){
	        	// Remove all children
	        	while (postFeed.firstChild) {	
	        		postFeed.removeChild(postFeed.firstChild);
	        	}
	
	        	// Append html snippet 
	    	    $(postFeed).append(data);
	    	    

	    		$('.postBody').each(function() {
	    	        $(this).height($(this).prop('scrollHeight'));
	    	    });
        	}
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
        	$('#anntitle').val("");
        	$('#annbody').val("");
        	$('.modal-overlay').trigger('click');
        	loadPosts();
        },
        error:function(){
        	alert("Exceeded number of allowed characters.")
        	console.log("error createPost URL");
        }
     });
}

function updateForm(postId, title, body) {
	$.ajax({
 	    context: this,
        url:'updatePost',
        data:{'postId': postId,
    		  'title' : title,
    		  'body'  : body},
        type:'POST',
        cache:false,
        success: function(data){

        	$('#updateTitle').val("");
        	$('#updateBody').val("");
        	$('.modal-overlay').trigger('click');
        	loadPosts();
        },
        error:function(){
        	alert("Exceeded number of allowed characters.")
        	console.log("error updatePost URL");
        }
     });
}

$("document").ready(function() {
	
    $('.modal').modal();
    loadPosts();
	
	// Creating new post
	$("#ANpost").click(function() {
		
		var title = document.getElementById('anntitle').value;
        var body = document.getElementById('annbody').value;

        if(enterValidInput("new")){
        	submitTheForm(title, body);
        }
	});

	// Updating post
	$("#ANupdate").click(function() {

        var postId   = document.getElementById('updateTitle').getAttribute("postId");
		var title  = document.getElementById('updateTitle').value;
        var body   = document.getElementById('updateBody').value;
        
        if(enterValidInput("update")){
        	updateForm(postId, title, body);
        }
	});
});