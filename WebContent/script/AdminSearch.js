function getCollegeValue(collegeAbbreviate){
	
	switch(collegeAbbreviate){
		case "College": return "";
						break;
		case "CCS":     return "College of Computer Studies";
					    break;
		case "RVRCOB":  return "College of Business";
					    break;
		case "BAGCED":  return "College of Education";
					    break;
		case "GCOE":    return "College of Engineering";
					    break;
		case "CLA":     return "College of Liberal Arts";
					    break;
		case "COS":     return "College of Science";
					    break;
		case "SOE":     return "School of Economics";
					    break;
	    default   :     return "";
	}
}

/*******************************************/

function loadStudents(studentName, collegeVal){
	
	var searchFeed = document.getElementById("tableDetails");
	$('tr.tableDataRow').remove();
	
	$.ajax({
 	    context: this,
        url:'search',
        data:{'name': studentName,
        	  'collegeVal': collegeVal},
        type: 'GET',
        cache:false,
        success: function(data){
        	
        	// Append html snippet
    	    $(searchFeed).append(data);

            $('#searchResultText').text("Found " + $("tbody").children().length + " students");
    	    
    	},
        error:function(){
            console.log("URL get search does not exist");
        }
    });
}


function loadQualifiedStudents(studentName, collegeVal){
	
	var searchFeed = document.getElementById("tableDetails");
	$('tr.tableDataRow').remove();
	$.ajax({
 	    context: this,
        url:'searchQualifiedStudents',
        data:{'name': studentName, 'collegeVal' : collegeVal},
        type: 'GET',
        cache:false,
        success: function(data){
        	$( "tr:first" ).nextAll().remove();
        	
        	// Append html snippet
    	    $(searchFeed).append(data);
            $('#searchResultText').text("Found " + $("tbody").children().length + " students");
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
	    
		var dropDownCollege = document.getElementById('dropDownFilterCollege');
		var name	   		= document.getElementById('search').value,
			collegeVal 		= getCollegeValue(dropDownCollege.options[dropDownCollege.selectedIndex].text);
		
		switch(document.getElementById('dropDownFilterAwards').value){
			case "0"			: loadStudents(name, collegeVal);
								  break;
			default				: loadQualifiedStudents(name, collegeVal);
		}
	})
	  

	$("#dropDownFilterAwards").on("change", function(e) {
		
		console.log(this.value);
		var dropDownCollege = document.getElementById('dropDownFilterCollege');
		var name	   		= document.getElementById('search').value,
			collegeVal 		= getCollegeValue(dropDownCollege.options[dropDownCollege.selectedIndex].text);
		
		switch(this.value){
			case "0"			: loadStudents(name, collegeVal);
								  break;
			default				: loadQualifiedStudents(name, collegeVal);
		}
	});
	
	$("#dropDownFilterCollege").on("change", function(e) {
		console.log(this.value);
		var name  	   = document.getElementById('search').value,
			collegeVal = getCollegeValue(this.options[this.selectedIndex].text);
		switch(document.getElementById('dropDownFilterAwards').value){
			case "0"			: loadStudents(name, collegeVal);
								  break;
			default				: loadQualifiedStudents(name, collegeVal);
		}
	});
});