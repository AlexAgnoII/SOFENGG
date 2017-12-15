
function loadPosts(){
	
	$.ajax({
 	    context: this,
        url:'getPosts',
        data:{'user': 'student'},
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


$("document").ready(function() {
    loadPosts();
	
});