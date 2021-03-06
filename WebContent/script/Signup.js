
// https://stackoverflow.com/questions/46155/how-to-validate-email-address-in-javascript
function enterKeyEvent(event) {
    var char = event.which || event.keyCode;
    
    if (char == 13) {

    }
}

function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return email.length <= 50 && re.test(email) && email.trim().length > 0;
}

function duplicateHandler(data) {
	
	//front end handling constraint done here (create functions for them so we can reuse it on the 3rd case in the switch-case)
	switch(data) {
		case "EMAIL-TAKEN": 
//                            break;
            //alert("Email is already taken."); break;
            $('#emailerrorPlaceHolder').hide();
            $('#emailerror2').show();
            $('#email').css("border-color", "indianred"); break;
            //only email
		case "IDNUM-TAKEN": 
//                            break;
            //alert("ID-number is already taken."); break; 
            $('#idnumerrorPlaceHolder').hide();
            $('#idnumerror2').show();
            $('#idNum').css("border-color", "indianred"); break;
            //only idnum
		case "EMAIL-TAKEN|IDNUM-TAKEN": 
            //alert("ID-number is already taken.");
            $('#emailerrorPlaceHolder').hide();
            $('#emailerror2').show();
            $('#email').css("border-color", "indianred");
            
            $('#idnumerrorPlaceHolder').hide();
            $('#idnumerror1').show();
            $('#idNum').css("border-color", "indianred"); break;
            
		case "GO-SIGNAL": submitForSending();
						  document.location.href = 'VerifyLink.html';
						  break;
		default:console.log("CANNOT READ RESPOND FROM SERVLET ( SIGNUP.JS )");		
	}
	
}

function submitForSending() {
	$.ajax({
		   context: this,
	      url:'sendEmailForVerification',
	      data:$("form#signUpForm").serialize(),
	      type:'POST',
	      cache:false,
	      success: function(data){
	      	
	      },
	      error:function(){
	      	console.log("error searchResult.js");
	      }
	   });
}


//This function handles submitting the data.
function submitTheForm() {
	$.ajax({
	   context: this,
      url:'signUp',
      data:$("form#signUpForm").serialize(),
      type:'POST',
      cache:false,
      success: function(data){
    	  
      	//Front end stating email or idnum or both is taken.
    	 duplicateHandler(data);
      	
      },
      error:function(){
      	console.log("error searchResult.js");
      }
   });
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


//function to check college
function checkCollege(college) {
   //college = select.options[select.selectedIndex].value;
    college = college.options[college.selectedIndex].value;
    
    console.log(college);
    if (college != 0) {
        $('#collegeerrorPlaceHolder').show();
        $('#collegeerror').hide();
        $('.select-dropdown').css("border-color" , "rgba(0, 0, 0, 0.3)");
    }
}

/**
 * OnBlur function used for input field of id number.
 * @param idInputField = the ID of the input field.
 */
function checkIdNumber(idInputField) {
	
	//Check if idNumber follows constraint.
	if(idNumberChecker(idInputField.value) || idInputField.value == "") {
		
		if(idInputField.value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			//spanId.innerHTML = "";
            $('#idnumerrorPlaceHolder').show();
            $('#idnumerror1').hide();
            $('#idnumerror2').hide();
            $('#idNum').css("border-color" , "rgba(0, 0, 0, 0.3)");
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		//spanId.innerHTML = "Must be 8 digit integers only.";
        $('#idnumerrorPlaceHolder').hide();
        $('#idnumerror1').show();
        $('#idNum').css("border-color", "indianred");
        //This is temporary, you can add this directly to the tag span and just hided/show the tag.
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
	return value.length <=50 && /^[a-z\-\s]+$/i.test(value) && !/^[\-\s]+$/.test(value);
}

/**
 * onBlur function for input field of lastName, firstName, middleName, and course.
 * @param stringField - input field object.
 * @param placeHolder - the placeHolder that would contain the error message.
 * @param errror = the error message.
 */
function checkStringField(stringField, placeHolder, error) {
	var value = stringField.value;

	//Value only all spaces, do this.
	if(/^[\-\s]+$/.test(value)) {
        console.log("hi");
        console.log(error);
        console.log(placeHolder);
        console.log(stringField);
		//This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 //spanString.innerHTML = "Must consists of letters and/or spaces only.";
        placeHolder.style.display = "none";
        error.style.display = "block";
        stringField.style.borderColor = "indianred";
	}
	//Contains nothing OR the right value.
	else if(stringIsWord(value) || value === "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			//spanString.innerHTML = "";
            placeHolder.style.display = "block";
            error.style.display = "none";
            stringField.style.borderColor = "rgba(0, 0, 0, 0.3)";
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		 //This is temporary, you can add these messages directly to the tag span and just hided/show the tag.
		 //spanString.innerHTML = "Must consists of letters and/or spaces only.";
        placeHolder.style.display = "none";
        error.style.display = "block";
        stringField.style.borderColor = "indianred";
	}
}


/**
 * onBlur function for input field emailField
 * @param emailField - the input object for email
 * @returns
 */
function checkEmailField(emailField) {
	var value = emailField.value;
	
	//Check if idNumber follows constraint.
	if(validateEmail(value) || value == "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			//spanEmail.innerHTML ="";
            $('#emailerrorPlaceHolder').show();
            $('#emailerror1').hide();
            $('#emailerror2').hide();
            $('#emailerror3').hide();
            $('#email').css("border-color", "rgba(0, 0, 0, 0.3)");
		} 
		
	}
	
	else if(value.length > 50){
		$('#emailerrorPlaceHolder').hide();
        $('#emailerror1').hide();
        $('#emailerror3').show();
        $('#email').css("border-color", "indianred");
	}
	
	//If not follow, do front end magic to do show this.
	else {
		//spanEmail.innerHTML = "Must be valid email."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
        $('#emailerrorPlaceHolder').hide();
        $('#emailerror1').show();
        $('#emailerror3').hide();
        $('#email').css("border-color", "indianred");
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
	// Java special char // ["'\]
	return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]).*$/.test(value);
	//#?!@$%^&*-
}

/**
 * onBlue function for the password field.
 * @param passwordField - input field object for password
 */
function checkPasswordField(passwordField) {
	var value = passwordField.value;
	
	console.log("sendhelp");
	//Check if idNumber follows constraint.
	if(checkPassword(value)|| value == "") {
		
		if(value != "") { //ONLY remove the error if user places the correct input (This is why there is an IF here.).
			//Add check or something in front end showing that user did iit correctly.
			//spanPassword.innerHTML ="";
            //$('#pwerrorPlaceHolder').show();
            //$('#pwerror').hide();
            $('#password').css("border-color", "rgba(0, 0, 0, 0.3)");
            $('#iPassword').css("color", "darkslategrey");
		} 
		
	}
	//If not follow, do front end magic to do show this.
	else {
		//spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
        //$('#pwerrorPlaceHolder').hide();
        //$('#pwerror').show();
        $('#password').css("border-color", "indianred");
        $('#iPassword').css("color", "indianred");
	}
    
    if (document.getElementById('password2').value != "") {
        checkPasswordEqual(document.getElementById('password2'), value);
    }
}



//if all fields valid, proceed. if not, place front end effects of what field is not ok.
function constraintChecker(password, idNum, fName, lName, mName, courseName) {
	var satisfied = true;
	
	//ID number//
	//- Must be 8 in size.
	//- Must be INTEGERS ONLY.
	if(idNum.length == 8 && /^\d+$/.test(idNum)) {
		//make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid IDnumber");
        $('#idnumerror').css("color", "indianred");
        $('#idNum').css("border-color", "indianred");
		satisfied = false;
	}
	
	//Password//
	//Must be atleast 8 characters
	//atleast 1 number, 1 special char
	if(checkPassword(password)) {
		////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid Password");
        $('#pwerror').css("color", "indianred");
        $('#password').css("border-color", "indianred");
		satisfied = false;
	}
	
	//fName//
	//Characters ONLY
	if(stringIsWord(fName)) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid First name");
        $('#fnameerror').css("color", "indianred");
        $('#firstName').css("border-color", "indianred");
		satisfied = false;
	}

	//lName//
	//Characters ONLY
	if(stringIsWord(lName)) {
		
	////make some sort of indicator near field that it is done correctly.
	}
	
	else if(lname.length > 50){
		$('#lnameerror').css("color", "indianred");
        $('#lastName').css("border-color", "indianred");
		satisfied = false;
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid last name");
        $('#lnameerror').css("color", "indianred");
        $('#lastName').css("border-color", "indianred");
		satisfied = false;
	}
	
	//mName//
	//Characters ONLY
	if(stringIsWord(mName) || mName == "" || mName == null) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid Middle name");
        $('#mnameerror').css("color", "indianred");
        $('#middleName').css("border-color", "indianred");
		satisfied = false;
	}
	
	//courseName//
	//Characters ONLY
	if(stringIsWord(courseName)) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		//alert("Invalid courseName");
        $('#courseerror').css("color", "indianred");
        $('#courseName').css("border-color", "indianred");
		satisfied = false;
        
	}
	
	return satisfied;
}

/**
 * Checks if password is equal or not.
 * @param pass2Field - the second verifying password
 * @param pass1Field - the original passsword.
 * @returns
 */
function checkPasswordEqual(pass2Field, pass1Field) {
	var pass2Val = pass2Field.value;
	var pass1Val = pass1Field.value;
	
	//Equal!
	if(pass1Val=== pass2Val) {
        $('#pwerrorPlaceHolderNotEqual').show();
        $('#pwNotEqual').hide();
        $('#password2').css("border-color", "rgba(0, 0, 0, 0.3)");
	}
	
	//Not equal!
	else {
		//spanPassword.innerHTML = "Must follow constraint."; //This is temporary, you can add this directly to the tag span and just hided/show the tag.
        $('#pwerrorPlaceHolderNotEqual').hide();
        $('#pwNotEqual').show();
        $('#password2').css("border-color", "indianred");
	}
}



$("document").ready(function(idNum, password, fName, lName) {
    var password;
    var idNum;
    var fName;
    var mName;
    var lName;
    var email;
    var password2;
    var courseName;
    var select;
    var college;
	//To show the select(Dropdown).
	console.log("This happened II");
	$('select#dropDownCollege').material_select();

	$("a#backb").click(function() {
		window.location.href='HomePage.jsp';
	});
	
	$("#signupb").click(function(event) {

        password = document.getElementById('password').value;
        idNum = document.getElementById('idNum').value;
        fName = document.getElementById('firstName').value;
        mName = document.getElementById('middleName').value;
        lName = document.getElementById('lastName').value;
        email = document.getElementById('email').value;
        password2 = document.getElementById('password2').value;
        courseName = document.getElementById('courseName').value;
        select = document.getElementById('dropDownCollege');
        
        college = select.options[select.selectedIndex].value;
        
        //Removes unecessary extra white spaces.
        idNum = removeExtraWhiteSpaces(idNum);
        fName = removeExtraWhiteSpaces(fName);
        lName = removeExtraWhiteSpaces(fName);
        mName = removeExtraWhiteSpaces(mName);
        courseNName = removeExtraWhiteSpaces(fName);
        
        //All input must be placed (No blanks, except for mName).
        if(validateEmail(email) && 
           password &&
    	   idNum && 
    	   fName && 
    	   lName &&
    	   courseName &&
    	   college != "0" &&
    	   password2 === password ){
        	
        	//if all fields valid, proceed. if not, place front end effects of what field is not ok.
        	if(constraintChecker(password, idNum, fName, lName, mName, courseName)) {
//        		$('.modal').modal({
//        			dismissible: false //makes modal unclickable on background.
//        		});
        		//submitTheForm();
        		submitTheForm();
        	}
        	

        } else{
        	// TODO front end notif
        	//alert("Please fill up all important fields.");
            if (idNum == "") {
                $('#idnumerrorPlaceHolder').hide();
                $('#idnumerror').show();
                $('#idNum').css("border-color", "indianred");
            }
            if (lName == "") {
                $('#lnameerrorPlaceHolder').hide();
                $('#lnameerror').show();
                $('#lastName').css("border-color", "indianred");
            }
            if (fName == "") {
                $('#fnameerrorPlaceHolder').hide();
                $('#fnameerror').show();
                $('#firstName').css("border-color", "indianred");
            }
            if (mName == "") {
                $('#mnameerrorPlaceHolder').hide();
                $('#mnameerror').show();
                $('#middleName').css("border-color", "indianred");
            }
            if (college == "0") {
                //send help
            }
            if (courseName == "") {
                $('#courseerrorPlaceHolder').hide();
                $('#courseerror').show();
                $('#courseName').css("border-color", "indianred");
            }
            if (email == "") {
                $('#emailerrorPlaceHolder').hide();
                $('#emailerror').show();
                $('#email').css("border-color", "indianred");
            }
            if (password == "") {
                $('#pwerrorPlaceHolder').hide();
                $('#pwerror').show();
                $('#password').css("border-color", "indianred");
            }
            if (college == 0) {
                $('#collegeerrorPlaceHolder').hide();
                $('#collegeerror').show();
                $('.select-dropdown').css("border-color", "indianred");
                console.log("college is " + college);
            }
        }
	});
	
	//Send verification link here.
//	$("#proceedModal").click(function() {
//        // TODO update once verified mail
//		//$("form#signUpForm").submit();
//		submitTheForm();
//	});

});