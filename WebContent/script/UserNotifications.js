function loadNotifs(){
	
	$.ajax({
 	    context: this,
        url:'getNotifs',
        data:{'user': 'student'},
        type: 'GET',
        cache:false,
        success: function(data){
        	var notifFeed = document.getElementById("dp3");
        	if(notifFeed != null){
	        	// Remove all children
	        	while (notifFeed.firstChild) {	
	        		notifFeed.removeChild(notifFeed.firstChild);
	        	}
	
	        	// Append html snippet 
	    	    $(notifFeed).append(data);
        	}
    	},
        error:function(){
            console.log("URL getNotifs does not exist");
        }
    });
}


$(document).ready(function() {
    loadNotifs();
    
    $('#notif').click( function(event){
        event.stopPropagation();
        $('#dp3').slideToggle();
    });
    
    $(document).click( function(){
        $('#dp3').hide();
    });
});