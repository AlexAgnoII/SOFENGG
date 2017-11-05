//Global variables 
var FBctr; //counting for the names of the dynamic family members field.

/**
 * Checks if string field input is valid.
 * - Input must only be words with or without spaces ONLY.
 * @param value - value string being tested
 * @returns true or false
 */
function stringIsWord(value) {
	return /^[a-z\s]+$/i.test(value);
}

/**
 * Checks if the field contains only words with/without spaces.
 * @param inputField - the input field to be checked
 */
function checkStringField(stringField, spanString) {
	var value = stringField.val();

	//Value only all spaces, do this.
	if(/^\s+$/.test(value)) {
		//This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.html("Must consists of letters and/or spaces only.");
	}
	//Contains nothing OR the right value.
	else if(stringIsWord(value) || value === "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanString.html("");
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		 //This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.html("Must consists of letters and/or spaces only.");
	}
}

function stringIsDigit(value) {
	return /^\d+$/.test(value)
}

/**
 * Checks if the field contains digits only.
 * @param inputField - the input field
 * @param spanString - the span tag to use for constraint.
 * @returns
 */
function checkDigitField(inputField, spanString) {
	var value = inputField.val();
	//it is a number.
	if(stringIsDigit(value) || value === "") {
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanString.html("");
		} 
	}
	//not a number.
	else {
		spanString.html("Must be integers only.");
	}
}



/**
 * Checks if all fields in the given form has the correct answer.
 * @returns true or false
 */
function checkPIConstraints() {
	var ready = true; //ready variable to see if its ok to submit form.
	var stringFieldArray = [$("#address"), $('#city'), $('#country'), $('#prov'), $('#citizen')];
	var integerFieldArray = [$('#cell'),$('#tel'),  $('#zip')];
	var value;
	
	//Checking all string fields.
	stringFieldArray.forEach(function(field, index, array) {
		value = field.val();
		//if the value is a word OR empty, its passed.
		if(stringIsWord(value) || value === "") {
			console.log(field.attr('id') + ": Passed!");
		}
		
		//if it breaks the constraints, return failure.
		else {
			console.log(field.attr('id') + ": Failed!");
			ready = false
		}
		
	});
	
	//Checking all integer fields.
	integerFieldArray.forEach(function(field, index, array) {
		value = field.val()
		if(stringIsDigit(value) || value === "") {
			
			//if the field is id and it did not reach the required digit count, fail.
			if(field.attr("id") === "cel") {
				if(value.length != 11) {
					ready = false
				}
			}
			console.log(field.attr('id') + ": Passed!");
		}
		
		else {
			console.log(field.attr('id') + ": Failed!");
			ready = false;
		}
	});
	
	return ready;
}

/**
 * Submits the PI form.
 */
function submitPIform() {
	$.ajax({
		  context: this,
	      url:'updatePersonal',
	      data:$("form#PIform").serialize(),
	      type:'POST',
	      cache:false,
	      success: function(data){
	    	  
	      	//Front end stating success
	    	 alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	alert("Update Failed!")
	      }
	   });
}

/**
 * 
 * Submits the family background form.
 */
function submitFBform() {
	$.ajax({
		  context: this,
	      url:'updateFamily',
	      data:$("form#FBform").serialize(),
	      type:'POST',
	      cache:false,
	      success: function(data){
	    	  
	      	//Front end stating success
	    	 alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	alert("Update Failed!")
	      }
	   });
}


/**
 * Sends internal involvements form
 */
function sendIntInvForm() {
	document.getElementById("intInv").submit();
}

/**
 * Sends external involvements form
 */
function sendExtInvForm() {
	console.log("Pasok");
	document.getElementById("extInv").submit();
}

/**
 * gets the Family background count.
 * @returns a number in string.
 */
function getFBcount(value) {
	var number = value.split("-");
	console.log(number[1]);
	return number[1];
	
}

$(document).ready(function() {
	FBctr = getFBcount($("form input").last().attr("name"));
	$('.collapsible').collapsible();

	$('#PIedit').click(function() {
		$('form#PIform input.ipfield').removeAttr('disabled');

		$('#PIedit').hide();
		$('#PIsave').show();
		$('#PIcancel').show();
	})

	$('#PIsave').click(function() {
		
		//Check if all fields has the right input.
		if(checkPIConstraints()) {
			submitPIform()
			
			$('form#PIform input.ipfield').attr('disabled', 'disabled');

			$('#PIedit').show();
			$('#PIsave').hide();
			$('#PIcancel').hide();
		}
		
		else {
			alert("Please make sure all fields has valid inputs!");
		}
		
	})

	$('#FBedit').click(function() {
		$('form#FBform input.ipfield').removeAttr('disabled');

		$('#FBedit').hide();
		$('#FBsave').show();
		$('#FBcancel').show();
		$('#addSibling').show();
	})

	$('#FBsave').click(function() {
		//No constraints violated.
		if(true) { //Gonna add constraint checker soon.
			
		   //submit the forms
		   submitFBform();
		   $('form#FBform input.ipfield').attr('disabled', 'disabled');
			
			$('#FBedit').show();
			$('#FBsave').hide();
			$('#FBcancel').hide();
			$('#addSibling').hide();
		}
		//Constraints violdated.
		else {
			alert("Something went wrong sir!")
		}
	})

	$('#IIedit').click(function() {
		$('form#intInv input.ipfield').removeAttr('disabled');

		$('#IIedit').hide();
		$('#IIsave').show();
		$('#IIcancel').show();
	})

	$('#IIsave').click(function() {
		sendIntInvForm();
		$('form#intInv input.ipfield').attr('disabled', 'disabled');

		$('#IIedit').show();
		$('#IIsave').hide();
		$('#IIcancel').hide();
	})

	$('#EIedit').click(function() {
		$('form#extInv input.ipfield').removeAttr('disabled');

		$('#EIedit').hide();
		$('#EIsave').show();
		$('#EIcancel').show();
	})

	$('#EIsave').click(function() {
		sendExtInvForm();
		$('form#extInv input.ipfield').attr('disabled', 'disabled');

		$('#EIedit').show();
		$('#EIsave').hide();
		$('#EIcancel').hide();
	})
	
	$('#notif').click(function(event) {
		event.stopPropagation();
		$('#dp3').slideToggle();
	});

	$(document).click(function() {
		$('#dp3').hide();
	});
	
	
	//Contraints handler here (FOR PI)
	$('#city').blur(function(){
		var cityField = $("input#city");
		var citySpan = $("#citySpan");
		
		console.log(cityField.val());
		checkStringField(cityField, citySpan);
	});
	
	$('#country').blur(function(){
		var countryField = $("input#country");
		var countrySpan = $("#countrySpan");
		checkStringField(countryField, countrySpan);
	});

	$('#prov').blur(function(){
		var provField = $("input#prov");
		var provSpan = $("#provSpan");
		
		checkStringField(provField, provSpan);
	});
	
	$('#citizen').blur(function(){
		var citizenField = $("input#citizen");
		var citizenSpan = $("#nationSpan");
		
		checkStringField(citizenField, citizenSpan);
	});
	
	$('#cell').blur(function(){
		var cellField = $("#cell");
		var cellSpan = $("#cellSpan");
		
		if(cellField.val().length == 11)
			checkDigitField(cellField, cellSpan);
		else
			if(cellField.val() != "")
				alert("Must be valid cellphone number! (11 digits)");
	});
	
	$('#zip').blur(function(){
		var zipField = $("#zip");
		var zipSpan = $("#zipSpan");
		
		checkDigitField(zipField, zipSpan);
	});
	$('#tel').blur(function(){
		var telField = $("#tel");
		var telSpan = $("#telSpan");
		
		checkDigitField(telField, telSpan);
	});
	//---------------------------
	
	
	//add sibling
	$("#addSibling").click(function() {
		var nameInput = $("<input></input>");
		var occInput = $("<input></input>");
		var bdayInput = $("<input></input>");
		FBctr++;
		console.log("New input field name: " + FBctr);
		
		nameInput.attr("class", "sname");
		nameInput.attr("type", "text");
		nameInput.attr("name", "sibName-" +FBctr);
		
		occInput.attr("class", "swork");
		occInput.attr("type", "text");
		occInput.attr("name", "sibWork-"+FBctr);
		
		bdayInput.attr("class", "sbday");
		bdayInput.attr("type", "date");
		bdayInput.attr("name", "sibBday-"+FBctr);
		
		console.log(nameInput);
		console.log(occInput);
		console.log(bdayInput);
		
		$("#FBname").after(nameInput);
		$("#FBocc").after(occInput);
		$("#FBbday").after(bdayInput);
	});
	
	//-----------------------------------------

});

