//Global variables 
var FBctr; //counting for the names of the dynamic family members field.
var INctr; //counting for the names of the dynamic internal fields
var EXctr; //counting for the names of the dynamic external fields
var ToBeDeleted = [];

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
		 spanString.html("Must consist of letters and/or spaces only.");
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
		 spanString.html("Must consist of letters and/or spaces only.");
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
 * Checks if the field contains digits only.
 * @param inputField - the input field
 * @param spanString - the span tag to use for constraint.
 * @returns
 */
function checkCellField(inputField, spanString) {
	var value = inputField.val();
	if(value.length == 11)
		checkDigitField(inputField, spanString);
	else
		if(value != "")
			spanString.html("Must be valid cellphone number. (11 digits)");
}

/**
 * Checks if the field contains digits only.
 * @param inputField - the input field
 * @param spanString - the span tag to use for constraint.
 * @returns
 */
function checkGenderField(inputField, spanString) {
	var value = inputField.val();
	//Value only all spaces, do this.
	if(/^\s+$/.test(value)) {
		//This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.html("Must only be Male or Female.");
	}
	//Contains nothing OR the right value.
	else if(value == "Male" || value == "Female" || value == "male" || value == "female" ) {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanString.html("");
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		 //This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.html("Must only be Male or Female.");
	}
}

/**
 * Checks if all fields in the given form has the correct answer.
 * @returns true or false
 */
function checkPIConstraints() {
	var ready = true; //ready variable to see if its ok to submit form.
	var stringFieldArray = [$('#city'), $('#country'), $('#prov'), $('#citizen')];
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
	    	 //alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	//alert("Update Failed!")
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
	    	 //alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	//alert("Update Failed!")
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
	    	 //alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	//alert("Update Failed!")
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
	    	 //alert("Update successful!")
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      	//alert("Update Failed!")
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

function searchForField(fieldForm, index) {
	
	console.log(fieldForm);
	console.log("Index: " + index);
	
	var target = "-" + index  + "-";
	var elements = fieldForm.elements;
	var origSize = 0; //Size of the dynamic fields (excluding the static ones)
	var word = "";
	var value;

	
	for(var i = 0; i < elements.length; i++) {
		if(!/.*mom.*/.test(word) && !/.*dad.*/.test(word) )
			origSize++;
	}
	
	console.log(target);
	console.log(elements);
	console.log("Size: " + elements.length);
	//Delet the rows
	for(var i = 0; i < elements.length; i++) {
		//console.log("checker");
		word = elements[i].name; 
		console.log(word);
		if(word.includes(target)) {
			value = word;
			$('input[name=' + value +']').remove();
		}
	}
	
	var tempCon;
	//Fix indexing for the affected row
	
	console.log("Value: " + value);
	console.log("Original size: " + origSize)
	var tempAgain = value.split("-");
	console.log("Index deleted(+1): " + (parseInt(tempAgain[1])+1));
	console.log("Size / 3: " + (origSize / 3));
	
	//Dont do anything if the last element was targeted.
	if((parseInt(tempAgain[1])+1) != origSize / 3) {
		console.log("Not last.");
		for(var i = 0; i < elements.length; i++) {
			word = elements[i].name; 
			tempCon = word.split("-");
			
			//Rearrange indexing in the views.
			//Must decrement indexes greater than deleted index AND must not be a mom or dad.
			if(tempCon[1] > tempAgain[1] && !(/.*dad.*/.test(tempCon[0])) && !(/.*mom.*/.test(tempCon[0]))) {
				//console.log("New name: " + tempCon[0] + "-" + (tempCon[1]-1) + "-" + tempCon[2]);
				//Warning, might need to check if tempCon[1]-1 needs to be parsed or not.
				elements[i].setAttribute("name", tempCon[0] + "-" + (tempCon[1]-1) + "-" + tempCon[2]); 
			}
			console.log(elements[i].name);
			
		}
	}
	else {
		console.log("Last element deleted. no do anything.")
	}
	return value;
}

function performDelete(type) {

	var container;
	var theName;
	var index;
	var dbID;
	
	
	for(var i = 0; i < ToBeDeleted.length; i++) {
		container = ToBeDeleted[i].split("-");
		theName = container[0];
		index = container[1];
		dbID = container[2];
		
		console.log("Name: " + theName);
		console.log("Index: " + index);
		console.log("DB id: " + dbID);
		
		if(theName.includes(type)) {
			$.ajax({
				  context: this,
			      url:'deleteInformation',
			      data:{'name':theName,
			    	    'dbID':dbID},
			      type:'POST',
			      cache:false,
			      success: function(data){
			    	  
			      	//Front end stating success
			    	 //alert("Update successful!")
			      	
			      },
			      error:function(){
			      	console.log("error searchResult.js");
			      	//alert("Update Failed!")
			      }
			   });
		}
	}
		
	
}

$(document).ready(function() {
	$("div form div div a.delete").hide();
	$('#bDayField').on('input', function() {
		
		var today = new Date();
		var birthDate = new Date(document.getElementById('bDayField').value);
	    var age = today.getFullYear() - birthDate.getFullYear();
	    var m = today.getMonth() - birthDate.getMonth();
	    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
	        age--;
	    }
		
		$('#specialAge').val(age >= 0 ? age : 0);
	});
	
	 $("#printBtn").printPage();	
	
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
	
	$('#PIcancel').click(function() {
		//Disable fields
		$('form#PIform input.ipfield').attr('disabled', 'disabled');
		$('#PIedit').show();
		$('#PIsave').hide();
		$('#PIcancel').hide();
		
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
			//alert("Please make sure all fields has valid inputs!");
		}
		
	})

	$('#FBedit').click(function() {
		$('form#FBform input.ipfield').removeAttr('disabled');
		$("div form div div a.deleteSIB").show();
		$('#FBedit').hide();
		$('#FBsave').show();
		$('#FBcancel').show();
		$('#addSibling').show();
	})
	
	$("#FBcancel").click(function() {
		   $('form#FBform input.ipfield').attr('disabled', 'disabled');
		   $("div form div div a.deleteSIB").hide();
			$('#FBedit').show();
			$('#FBsave').hide();
			$('#FBcancel').hide();
			$('#addSibling').hide();
	})

	$('#FBsave').click(function() {
		//No constraints violated.
		if(true) { //Gonna add constraint checker soon.
			
		   //submit the forms
		   submitFBform();
		   performDelete("sib");
		   $('form#FBform input.ipfield').attr('disabled', 'disabled');
			
			$('#FBedit').show();
			$('#FBsave').hide();
			$('#FBcancel').hide();
			$('#addSibling').hide();
		}
		//Constraints violdated.
		else {
			//alert("Something went wrong sir!")
		}
	})

	$('#IIedit').click(function() {
		$('form#intInv input.ipfield').removeAttr('disabled');
		$("div form div div a.deleteINTERNAL").show();
		$('#IIedit').hide();
		$('#imInternal').show();
		$('#IIsave').show();
		$('#IIcancel').show();
        $('#IIinfo').css("width", "171px");
	})
	
	$("#IIcancel").click(function() {
		$('form#intInv input.ipfield').attr('disabled', 'disabled');
		$("div form div div a.deleteINTERNAL").hide();
		$('#IIedit').show();
		$('#imInternal').hide();
		$('#IIsave').hide();
		$('#IIcancel').hide();
        $('#IIinfo').css("width", "75px");
	})

	$('#IIsave').click(function() {
		
		//Constraints handling soon
		if(true) {
			sendIntInvForm();
			performDelete("in");
			$('form#intInv input.ipfield').attr('disabled', 'disabled');

			$('#IIedit').show();
			$('#imInternal').hide();
			$('#IIsave').hide();
			$('#IIcancel').hide();
            $('#IIinfo').css("width", "75px");
		}
		else {
			//front end error for constraints.
		}

	})

	$('#EIedit').click(function() {
		$('form#extInv input.ipfield').removeAttr('disabled');
		$("div form div div a.deleteEXTERNAL").show();
		$('#EIedit').hide();
		$('#imExternal').show();
		$('#EIsave').show();
		$('#EIcancel').show();
        $('#EIinfo').css("width", "171px");
	})
	
	$("#EIcancel").click(function() {
		$('form#extInv input.ipfield').attr('disabled', 'disabled');
		$("div form div div a.deleteEXTERNAL").hide();
		$('#EIedit').show();
		$('#imExternal').hide();
		$('#EIsave').hide();
		$('#EIcancel').hide();
        $('#EIinfo').css("width", "75px");
		
	})

	$('#EIsave').click(function() {
		
		//constraints handler
		if(true) {
			sendExtInvForm();
			performDelete("ex");
			$('form#extInv input.ipfield').attr('disabled', 'disabled');
			
			$('#EIedit').show();
			$('#imExternal').hide();
			$('#EIsave').hide();
			$('#EIcancel').hide();
            $('#EIinfo').css("width", "75px");
		}
		else {
			
		}

	})
	
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
	
	$('#status').blur(function(){
		var statusField = $("input#status");
		var statusSpan = $("#statusSpan");
		
		checkStringField(statusField, statusSpan);
	});
	
	$('#citizen').blur(function(){
		var citizenField = $("input#citizen");
		var citizenSpan = $("#nationSpan");
		
		checkStringField(citizenField, citizenSpan);
	});
	
	$('#cell').blur(function(){
		var cellField = $("#cell");
		var cellSpan = $("#cellSpan");
		
		checkCellField(cellField, cellSpan);
	});
	
	$('#gender').blur(function(){
		var genderField = $("input#gender");
		var genderSpan = $("#genderSpan");
		
		checkGenderField(genderField, genderSpan);
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
	
	$("div form div div a.delete").click(function() {
		
		var index = $(this).attr("data-index");
		var container = index.split("-");
		var name = "";
		
		if("sibDELETE" === container[0]) {
			console.log("Its a sibling deletion");
			name = searchForField(document.getElementById('FBform'), container[1]);
		}
		
		else if ("inDELETE" === container[0]) {
			console.log("Its a internal deletion");
			name = searchForField(document.getElementById('intInv'),container[1]);
		}
		
		else if ("exDELETE" === container[0]) {
			console.log("Its a external deletion");
			name = searchForField(document.getElementById('extInv'), container[1]);
		}
		
		else {
			console.log("What is this deletion?");
		}
		
		//Delete the button
		console.log("What we delete: " + name);
		//Fix delete button
		var list = ($(this).attr("data-index")).split("-");
		console.log("Deleting this button: " + list[0] + "|" + list[1]);
		var buttons = document.getElementsByClassName('delete');
		var siblingGroup = [];
		var internalGroup = [];
		var externalGroup = [];
		var temp;
		var tempWord;
		for(var i = 0; i < buttons.length; i++) {
			temp = $(buttons[i]);
			console.log(temp.attr("data-index"));
			tempWord = temp.attr("data-index");
			
			if(tempWord.includes("inDELETE")) {
				internalGroup.push(temp);
			}
			else if(tempWord.includes("exDELETE")) {
				externalGroup.push(temp);
			}
			else if(tempWord.includes("sibDELETE")) {
				siblingGroup.push(temp);
			}
			else {
				console.log("send help");
			}
		}
		
		console.log("SiblingGroup: " + siblingGroup.length);
		console.log("internalGroup: " + internalGroup.length);
		console.log("externalGroup: " + externalGroup.length);
		var buttonGroup;
		if(list[0] === "inDELETE") {
			buttonGroup = internalGroup;
		}
		else if(list[0] === "exDELETE") {
			buttonGroup = externalGroup;
		}
		else if(list[0] === "sibDELETE") {
			buttonGroup = siblingGroup;
		}
		else {
			console.log("send help");
		}
		
		console.log("ButtonGroup");
		console.log(buttonGroup);
		console.log(parseInt(list[1])+1);
		console.log(buttonGroup.length);
		if((parseInt(list[1])+1) != buttonGroup.length) {
			var elem;
			var num;
			for (var i = 0; i < buttonGroup.length; i++) {
				elem = buttonGroup[i].attr("data-index").split("-");

				if(elem[1] > list[1]) {
					buttonGroup[i].attr("data-index", elem[0] + "-" + (elem[1]-1))
					
				}
				
				
			}
		}
		
		//Remove button
		$(this).remove();
		
		
		//Save delete at DB 
		//performDelete(name);
		ToBeDeleted.push(name);
		
	});
	
	//-----------------------------------------

});

