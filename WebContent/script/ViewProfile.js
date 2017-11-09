//Global variables 
var FBctr; //counting for the names of the dynamic family members field.
var INctr; //counting for the names of the dynamic internal fields
var EXctr; //counting for the names of the dynamic external fields

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
	$.ajax({
		  context: this,
	      url:'addIntInv',
	      data:$("form#intInv").serialize(),
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
 * Sends external involvements form
 */
function sendExtInvForm() {
	$.ajax({
		  context: this,
	      url:'addExtInv',
	      data:$("form#extInv").serialize(),
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
 * gets the dynamic count.
 * @returns a number in string.
 */
function getDynamicCount(value) {
	var number = value.split("-");
	console.log(number[1]);
	return number[1];
	
}

/**
 * Adds dynamic input fields to the appropriate place (Internal/External involvements)
 * @param placeYear - place to put year field
 * @param placeEvent - place to put event field
 * @param placePos - place to put position fiield
 * @param yearInput - input field for year
 * @param eventInput - input field for event
 * @param posInput - input field for position
 * @param ctr - counter of the field
 * @param nameSuffx - either ex for external, in for internal
 */
function addFieldsInvolvement(placeYear, //Div id to append year
						      placeEvent, //Div id to append event/org
						      placePos, //Div id to append pos
						      yearInput,
						      eventInput,
						      posInput,
						      ctr,
						      nameSuffix) {
	yearInput.attr("class", "ipfield threeipyear");
	yearInput.attr("name", nameSuffix + "Year-" + ctr + "-0");
	yearInput.attr("type", "text");
	
	eventInput.attr("class", "ipfield threeip" );
	eventInput.attr("name", nameSuffix + "Org-" + ctr + "-0" );
	eventInput.attr("type", "text" );
	
	posInput.attr("class", "ipfield threeip" );
	posInput.attr("name", nameSuffix + "Pos-" + ctr + "-0" );
	posInput.attr("type", "text");
	
	placeYear.append(yearInput);
	placeEvent.append(eventInput);
	placePos.append(posInput);
}

$(document).ready(function() {
	FBctr = getDynamicCount($("form#FBform div.wrap div.f input").last().attr("name"));
	INctr = getDynamicCount($("form#intInv div.wrap div.f input").last().attr("name"));
	EXctr = getDynamicCount($("form#extInv div.wrap div.f input").last().attr("name"));
	
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
		$('#imInternal').show();
		$('#IIsave').show();
		$('#IIcancel').show();
	})

	$('#IIsave').click(function() {
		
		//Constraints handling soon
		if(true) {
			sendIntInvForm();
			$('form#intInv input.ipfield').attr('disabled', 'disabled');

			$('#IIedit').show();
			$('#imInternal').hide();
			$('#IIsave').hide();
			$('#IIcancel').hide();
		}
		else {
			//front end error for constraints.
		}

	})

	$('#EIedit').click(function() {
		$('form#extInv input.ipfield').removeAttr('disabled');

		$('#EIedit').hide();
		$('#imExternal').show();
		$('#EIsave').show();
		$('#EIcancel').show();
	})

	$('#EIsave').click(function() {
		
		//constraints handler
		if(true) {
			sendExtInvForm();
			$('form#extInv input.ipfield').attr('disabled', 'disabled');

			$('#EIedit').show();
			$('#imExternal').hide();
			$('#EIsave').hide();
			$('#EIcancel').hide();	
		}
		else {
			
		}

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
		
		nameInput.attr("class", "sname ipfield threeip");
		nameInput.attr("type", "text");
		nameInput.attr("name", "sibName-" +FBctr+"-0");
		
		occInput.attr("class", "swork ipfield threeip");
		occInput.attr("type", "text");
		occInput.attr("name", "sibWork-"+FBctr+"-0");
		
		bdayInput.attr("class", "sbday ipfield threeip");
		bdayInput.attr("type", "date");
		bdayInput.attr("name", "sibBday-"+FBctr+"-0");
		bdayInput.attr("value", "2000-12-01");
		
		console.log(nameInput);
		console.log(occInput);
		console.log(bdayInput);
		
		//Else, it will be placed there.
		$("#FBname").append(nameInput);
		$("#FBocc").append(occInput);
		$("#FBbday").append(bdayInput);
	});
	
	$(".addMoreInvolvments").click(function() {
		var eventInput = $("<input></input>");
		var yearInput = $("<input></input>");
		var posInput =$("<input></input>");
		var id = $(this).attr("id");
		console.log(id);
		//Internal
		if(id === "imInternal") {
			console.log("internal adding: " + INctr);
			INctr++;
			
			addFieldsInvolvement($("#INyear"),
					             $("#INevent"),
					             $("#INposition"),
					             yearInput,
					             eventInput,
					             posInput,
					             INctr,
					             "in");
		}
		//External
		else {
			console.log("external adding: " + EXctr)
			EXctr++;
			addFieldsInvolvement($("#EXyear"),
					             $("#EXevent"),
					             $("#EXposition"),
					             yearInput,
					             eventInput,
					             posInput,
					             EXctr,
					             "ex");
		}
		
		
	});
	
	//-----------------------------------------

});

