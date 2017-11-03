
/**
 * Sends personal information form.
 */
function sendPIForm() {
	
}

/**
 * Sends internal involvements form
 */

function sendIntInvForm() {
	document.getElementById("intInv").submit();
}

/**
 * 
 * @returns
 */
function checkPIConstraints() {
	console.log($('#address').val())
}


$(document).ready(function() {
	$('.collapsible').collapsible();

	$('#PIedit').click(function() {
		alert("AHH");
		$('#address').removeAttr('disabled');
		$('#city').removeAttr('disabled');
		$('#country').removeAttr('disabled');
		$('#cell').removeAttr('disabled');
		$('#prov').removeAttr('disabled');
		$('#zip').removeAttr('disabled');
		$('#tel').removeAttr('disabled');
		$('#bday').removeAttr('disabled');
		$('#civil').removeAttr('disabled');
		$('#age').removeAttr('disabled');
		$('#citizen').removeAttr('disabled');
		$('#gender').removeAttr('disabled');

		$('#PIedit').hide();
		$('#PIsave').show();
	})

	$('#PIsave').click(function() {
		alert("Sure?");
		$('#address').attr('disabled', 'disabled');
		$('#city').attr('disabled', 'disabled');
		$('#country').attr('disabled', 'disabled');
		$('#cell').attr('disabled', 'disabled');
		$('#prov').attr('disabled', 'disabled');
		$('#zip').attr('disabled', 'disabled');
		$('#tel').attr('disabled', 'disabled');
		$('#bday').attr('disabled', 'disabled');
		$('#civil').attr('disabled', 'disabled');
		$('#age').attr('disabled', 'disabled');
		$('#citizen').attr('disabled', 'disabled');
		$('#gender').attr('disabled', 'disabled');

		$('#PIedit').show();
		$('#PIsave').hide();
	})

	$('#FBedit').click(function() {
		$('#dname').removeAttr('disabled');
		$('#mname').removeAttr('disabled');
		$('#bname').removeAttr('disabled');
		$('#sname').removeAttr('disabled');
		$('#dwork').removeAttr('disabled');
		$('#mwork').removeAttr('disabled');
		$('#bwork').removeAttr('disabled');
		$('#swork').removeAttr('disabled');
		$('#dbday').removeAttr('disabled');
		$('#mbday').removeAttr('disabled');
		$('#bbday').removeAttr('disabled');
		$('#sbday').removeAttr('disabled');

		$('#FBedit').hide();
		$('#FBsave').show();
	})

	$('#FBsave').click(function() {
		$('#dname').attr('disabled', 'disabled');
		$('#mname').attr('disabled', 'disabled');
		$('#bname').attr('disabled', 'disabled');
		$('#sname').attr('disabled', 'disabled');
		$('#dwork').attr('disabled', 'disabled');
		$('#mwork').attr('disabled', 'disabled');
		$('#bwork').attr('disabled', 'disabled');
		$('#swork').attr('disabled', 'disabled');
		$('#dbday').attr('disabled', 'disabled');
		$('#mbday').attr('disabled', 'disabled');
		$('#bbday').attr('disabled', 'disabled');
		$('#sbday').attr('disabled', 'disabled');

		$('#FBedit').show();
		$('#FBsave').hide();
	})

	$('#IIedit').click(function() {
		$('#inyear').removeAttr('disabled');
		$('#inorg').removeAttr('disabled');
		$('#inpos').removeAttr('disabled');

		$('#IIedit').hide();
		$('#IIsave').show();
	})

	$('#IIsave').click(function() {
		sendIntInvForm();
		$('#inyear').attr('disabled', 'disabled');
		$('#inorg').attr('disabled', 'disabled');
		$('#inpos').attr('disabled', 'disabled');

		$('#IIedit').show();
		$('#IIsave').hide();
	})

	$('#EIedit').click(function() {
		$('#exyear').removeAttr('disabled');
		$('#exorg').removeAttr('disabled');
		$('#expos').removeAttr('disabled');

		$('#EIedit').hide();
		$('#EIsave').show();
	})

	$('#EIsave').click(function() {
		$('#exyear').attr('disabled', 'disabled');
		$('#exorg').attr('disabled', 'disabled');
		$('#expos').attr('disabled', 'disabled');

		$('#EIedit').show();
		$('#EIsave').hide();
	})
	
	$('#notif').click(function(event) {
		event.stopPropagation();
		$('#dp3').slideToggle();
	});

	$(document).click(function() {
		$('#dp3').hide();
	});
	
	
	//Contraints handler here (FOR PI)
	$("#address").blur(function(){
		
	});
	$('#city').blur(function(){
		
	});
	$('#country').blur(function(){
		
	});
	$('#cell').blur(function(){
		
	});
	$('#prov').blur(function(){
		
	});
	$('#zip').blur(function(){
		
	});
	$('#tel').blur(function(){
		
	});
	$('#bday').blur(function(){
		
	});
	$('#civil').blur(function(){
		
	});
	$('#age').blur(function(){
		
	});
	$('#citizen').blur(function(){
		
	});
	$('#gender').blur(function(){
		
	});
	//---------------------------

});

