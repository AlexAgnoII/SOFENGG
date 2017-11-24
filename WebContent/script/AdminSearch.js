
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


function loadQualifiedStudents(studentName){
	
	var searchFeed = document.getElementById("searchResultFeed");
	$.ajax({
 	    context: this,
        url:'searchQualifiedStudents',
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

    $('select').material_select();
    $('.modal').modal();
    
	$("#searchForm").on("submit", function(e) {
	    e.preventDefault();
		var name  = document.getElementById('search').value;
		
		switch(document.getElementById('dropDownFilterAwards').value){
			case "0"			: loadStudents(name);
								  break;
			default				: loadQualifiedStudents(name);
		}
	  })
	  

	$("#dropDownFilterAwards").on("change", function(e) {
		console.log(this.value);
		var name  = document.getElementById('search').value;
		switch(this.value){
			case "0"			: loadStudents(name);
								  break;
			default				: loadQualifiedStudents(name);
		}
	});
});