
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

function stringIsWord(value) {
	return /^[a-z\s]+$/i.test(value);
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
	
	//check if there's anything that has been input on.
	if(idInputField.value) {
		console.log("Has input!");
		spanId.innerHTML = ""; //change the text to nothing.
		
		//Check if idNumber follows constraint.
		if(idNumberChecker(idInputField.value)) {
			spanId.innerHTML = "CORRECT";
		}
		else {
			spanId.innerHTML = "Must be 8 digit integers only.";
		}
		
	}
	else {
		spanId.innerHTML = "Please enter an input.";
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
		alert("Invalid IDnumber");
		satisfied = false;
	}
	
	//Password//
	//Must be atleast 8 characters
	//atleast 1 number, 1 special char
	if(password.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$/.test(password)) {
		////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		alert("Invalid Password");
		satisfied = false;
	}
	
	//fName//
	//Characters ONLY
	if(stringIsWord(fName)) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		alert("Invalid First name");
		satisfied = false;
	}

	//lName//
	//Characters ONLY
	if(stringIsWord(lName)) {
		
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		alert("Invalid last name");
		satisfied = false;
	}
	
	//mName//
	//Characters ONLY
	if(stringIsWord(mName) || mName == "" || mName == null) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		alert("Invalid Middle name");
		satisfied = false;
	}
	
	//courseName//
	//Characters ONLY
	if(stringIsWord(courseName)) {
	////make some sort of indicator near field that it is done correctly.
	}
	else {
	////make some sort of indicator near field that it is done incorrectly.
		alert("Invalid courseName");
		satisfied = false;
	}
	
	return satisfied;
}

function removeExtraWhiteSpaces(value) {
	return value.replace(/\s+/g,' ').trim();
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
        console.log(typeof(idNum))
        
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
    	   college != "" &&
    	   password2 === password ){
        	
        	//if all fields valid, proceed. if not, place front end effects of what field is not ok.
        	if(constraintChecker(password, idNum, fName, lName, mName, courseName)) {
        		//$('.modal').modal();
        		submitTheForm();
        	}
        	

        } else{
        	// TODO front end notif
        	alert("Please fill up all important fields.");
        }
	});
	
	//Send verification link here.
	$("#proceedModal").click(function() {
        // TODO update once verified mail
		//$("form#signUpForm").submit();
		submitTheForm();
	});

});