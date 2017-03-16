<<<<<<< HEAD
function sendFuguFeedback() {
	let username=document.getElementById("username").getAttribute("name");
	let email=document.getElementById("useremail").getAttribute("name");
	let body=document.getElementById("message").value;
	window.location.href = "mailto:feedback@fugu.com?subject=Feedback&body="+body+"%0A%0AMessage written by: "+username+" ("+email+").";
}

function sendFuguFeedbackOut() {
=======
>>>>>>> d21942b6b72c5c50b6933035921465548eb8e646
	let username=document.getElementById("username").value;
	let email=document.getElementById("useremail").value;
	let body=document.getElementById("message").value;
	window.location.href = "mailto:feedback@fugu.com?subject=Feedback&body="+body+"%0A%0AMessage written by: "+username+" ("+email+").";
}

$('#password_restaurant, #confirm_password_restaurant').on('keyup', function () {
	if($('#password_restaurant').val().length>=6){
		if ($('#password_restaurant').val() !== $('#confirm_password_restaurant').val()&&$('#confirm_password_restaurant').val().length>=6) {
			$('#message_restaurant').html('Passwords not matching').css('color', 'red');
			document.getElementById("sendrestaurant").disabled = true;
			$('#message_restaurant_button').html('Fill the form before register');
			document.getElementById('sendrestaurant').className = "logInBtn";
	    } else if($('#confirm_password_restaurant').val().length<6){  	
	    	document.getElementById("sendrestaurant").disabled = true;
	    	$('#message_restaurant_button').html('Fill the form before register');
	    	document.getElementById('sendrestaurant').className = "logInBtn";
	    }else{
	    	$('#message_restaurant').html('');
	    	if($('#restaurantdescription').val().length>0 && $('#restaurantname').val().length>0 && $('#restaurantaddress').val().length>0 
	    	&& $('#restaurantphone').val().length>0 && $('#restaurantemail').val().length>0){
	    		if(isNumber($('#restaurantphone').val())){
	    			document.getElementById('sendrestaurant').className = "btnMore";
	    			document.getElementById('sendrestaurant').removeAttribute('disabled');
	    			$('#message_restaurant_button').html('');
	    		}
	    			
	    	}
	    }
	}
});

$('#restaurantphone').on('keyup', function () {
	if($('#restaurantphone').val().length==0 || !isNumber($('#restaurantphone').val())){
		document.getElementById("sendrestaurant").disabled = true;
		$('#message_restaurant_phone').html('Phone must be nuberic').css('color', 'red');
		$('#message_restaurant_button').html('Fill the form before register');
		document.getElementById('sendrestaurant').className = "logInBtn";
	}else{
		$('#message_restaurant_phone').html('');
		if($('#restaurantdescription').val().length>0 && $('#restaurantname').val().length>0 && $('#restaurantaddress').val().length>0 
		    	&& $('#restaurantemail').val().length>0 && $('#password_restaurant').val() == $('#confirm_password_restaurant').val()
		    	&& $('#confirm_password_restaurant').val().length>=6){
			document.getElementById('sendrestaurant').className = "btnMore";
			document.getElementById('sendrestaurant').removeAttribute('disabled');
			$('#message_restaurant_button').html('');
		}
	}
});


function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

$('#password_client, #confirm_password_client').on('keyup', function () {
	if($('#password_client').val().length>=6){
		if ($('#password_client').val() !== $('#confirm_password_client').val()&&$('#confirm_password_client').val().length>=6) {
			$('#message_client').html('Passwords not matching').css('color', 'red');
			document.getElementById("senduser").disabled = true;
			$('#message_user_button').html('Fill the form before register');
			document.getElementById('senduser').className = "logInBtn";
	    } else if($('#confirm_password_client').val().length<6){
			document.getElementById("senduser").disabled = true;
			$('#message_user_button').html('Fill the form before register');
	    	document.getElementById('senduser').className = "logInBtn";
	    }else{
	    	$('#message_client').html('');
	    	if($('#userdescriptionform').val().length>0 && $('#usernameform').val().length>0 && $('#usernameform').val().length>0){
	    		if(isNumber($('#userageform').val())){
	    			document.getElementById('senduser').className = "btnMore";
	    			document.getElementById('senduser').removeAttribute('disabled');
	    			$('#message_user_button').html('');
	    		}
	    			
	    	} 	
	    }
	}
});

$('#userageform').on('keyup', function () {
	if($('#userageform').val().length==0 || !isNumber($('#userageform').val())){
		document.getElementById("senduser").disabled = true;
		$('#message_user_age').html('Age must be nuberic').css('color', 'red');
		$('#message_user_button').html('Fill the form before register');
		document.getElementById('senduser').className = "logInBtn";
	}else{f
		$('#message_user_age').html('');
		if($('#userdescriptionform').val().length>0 && $('#usernameform').val().length>0 && $('#usernameform').val().length>0 
		    	&& $('#password_client').val() == $('#confirm_password_client').val()
		    	&& $('#confirm_password_client').val().length>=6){
			document.getElementById('senduser').className = "btnMore";
			document.getElementById('senduser').removeAttribute('disabled');
			$('#message_user_button').html('');
		}
	}
});




let oldDay="13";
function selectDay(newDay) {
	document.getElementById(oldDay+"class").className = "";
	document.getElementById(newDay+"class").className = "active active-date";
	oldDay=newDay;
	document.getElementById("day").innerHTML=newDay+"th";
	document.getElementById("bookingday").value = newDay;
	return false;
}

let oldHour="1";
function selectHour(newHour) {
	document.getElementById("hour"+oldHour).className = "";
	document.getElementById("hour"+newHour).className = "active active-date";
	oldHour=newHour;
	document.getElementById("bookinghour").value = document.getElementById("hour"+newHour).innerHTML;
	return false;
}


function acceptReservation(index){
	$("#" + index).css('background', '#80cc0c');
	document.getElementById(index+'-button').style.display = 'none'; 
}



