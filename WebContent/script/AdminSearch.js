
/*******************************************/

function loadStudents(studentName){
	
	var searchFeed = document.getElementById("searchResultFeed");
	$.ajax({
 	    context: this,
        url:'search',
        data:{'name': studentName},
        type: 'GET',
        cache:false,
        success: function(data){
        	while (searchFeed.firstChild) 
        		searchFeed.removeChild(searchFeed.firstChild);
        	
        	// Append html snippet
    	    $(searchFeed).append(data);
    	},
        error:function(){
            console.log("URL get search does not exist");
        }
    });
}


/*******************************************/


$("document").ready(function() {
    console.log("This happened II");
    $('select').material_select();
    $('.modal').modal();
    
	$("#searchForm").on("submit", function(e) {
	    e.preventDefault();
		var name  = document.getElementById('search').value;
		alert("submitted");
		loadStudents(name);
	  })
});