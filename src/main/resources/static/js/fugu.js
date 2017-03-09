function sendFuguFeedback() {
	let username=document.getElementById("username").value;
	let email=document.getElementById("useremail").value;
	let body=document.getElementById("message").value;
	window.location.href = "mailto:feedback@fugu.com?subject=Feedback&body="+body+"%0A%0AMessage written by: "+username+" ("+email+").";
}

function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function validate() {
  var email = $("#email").val();
  if (validateEmail(email)) {
    $("#email").css("color", "green");
  } else {
    $("#email").css("color", "red");
  }
  return false;
}

$('#password_restaurant, #confirm_password_restaurant').on('keyup', function () {
	if($('#password_restaurant').val().length>=6){
		if ($('#password_restaurant').val() !== $('#confirm_password_restaurant').val()&&$('#confirm_password_restaurant').val().length>=6) {
			$('#message_restaurant').html('Passwords not matching').css('color', 'red');
	    } else{
	    	$('#message_restaurant').html('');
	    }
	}
});

$('#password_client, #confirm_password_client').on('keyup', function () {
	if($('#password_client').val().length>=6){
		if ($('#password_client').val() !== $('#confirm_password_client').val()&&$('#confirm_password_client').val().length>=6) {
			$('#message_client').html('Passwords not matching').css('color', 'red');
	    } else{
	    	$('#message_client').html('');
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



