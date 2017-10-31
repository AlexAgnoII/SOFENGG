
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function duplicateHandler(data) {
	
	//front end handling constraint done here (create functions for them so we can reuse it on the 3rd case in the switch-case)
	switch(data) {
		case "EMAIL-TAKEN": alert("Email is already taken."); break; //only email
		case "IDNUM-TAKEN": alert("ID-number is already taken."); break; //only idnum
		case "EMAIL-TAKEN|IDNUM-TAKEN":  alert("ID-number is already taken."); 
		                                  alert("EMAIL-number is already taken."); break; //both (use functions made in email and inum)
		default: document.location.href = 'HomePage.jsp'; //redirect to homepage.
		
	}
	
}

//This function handles submitting the data.
function submitTheForm() {
//	$.ajax({
//	   context: this,
//      url:'signUp',
//      data:$("form#signUpForm").serialize(),
//      type:'POST',
//      cache:false,
//      success: function(data){
//    	  
//      	//Front end stating email or idnum or both is taken.
//    	 duplicateHandler(data);
//      	
//      },
//      error:function(){
//      	console.log("error searchResult.js");
//      }
//   });
}


/**
 * Constraint checker for idNumber.
 * - Must be 8 digits.
 * - Must be digits ONLY.
 * @param idNum
 * @returns
 */
function idNumberChecker(idNum) {
	return idNum.length == 8 && /^\d+$/.test(idNum)
}

/**
 * OnBlur function used for input field of id number.
 * @param idInputField = the ID of the input field.
 * @param spanId = the spanId.
 */
function checkIdNumber(idInputField, spanId) {
	
	//Check if idNumber follows constraint.
	if(idNumberChecker(idInputField.value) || idInputField.value == "") {
		
		if(idInputField.value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanId.innerHTML = "";
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		spanId.innerHTML = "Must be 8 digit integers only."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
	}
}

/**
 * Removes extra whitespaces in a given input.
 * @param value - to be tested.
 * @returns the value where spaces are trimmed.
 */
function removeExtraWhiteSpaces(value) {
	return value.replace(/\s+/g,' ').trim();
}

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
 * onBlur function for input field of lastName, firstName, middleName, and course.
 * @param stringField - input field object.
 * @param spanString - span object.
 */
function checkStringField(stringField, spanString) {
	var value = stringField.value;

	//Value only all spaces, do this.
	if(/^\s+$/.test(value)) {
		//This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.innerHTML = "Must consists of letters and/or spaces only.";
	}
	//Contains nothing OR the right value.
	else if(stringIsWord(value) || value === "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanString.innerHTML = "";
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		 //This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 spanString.innerHTML = "Must consists of letters and/or spaces only.";
	}
}

/**
 * onBlur function for input field emailField
 * @param emailField - the input object for email
 * @param spanEmail - the span object for email errors.
 * @returns
 */
function checkEmailField(emailField, spanEmail) {
	var value = emailField.value;
	
	//Check if idNumber follows constraint.
	if(validateEmail(value) || value == "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanEmail.innerHTML ="";
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		spanEmail.innerHTML = "Must be valid email."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
	}
}

/**
 * This function checks if the password satisfies the constraints.
 * - Must have atleast 1 captial letter
 * - Must have atleast 1 small letter
 * - must be atleast 8 in size.
 * - must have atleaest 1 special character.
 * @param value
 * @returns true or false
 */
function checkPassword(value) {
	return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$/.test(value);
}

/**
 * onBlue function for the password field.
 * @param passwordField - input field object for password
 * @param spanPassword - span object for password for error message 
 */
function checkPasswordField(passwordField, spanPassword) {
	var value = passwordField.value;
	
	console.log("sendhelp");
	//Check if idNumber follows constraint.
	if(checkPassword(value)|| value == "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			spanPassword.innerHTML ="";
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
	}
}