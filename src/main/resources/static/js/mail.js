function sendFuguFeedback() {
	let username=document.getElementById("username").value;
	let email=document.getElementById("useremail").value;
	let body=document.getElementById("message").value;
	window.location.href = "mailto:feedback@fugu.com?subject=Feedback&body="+body+"%0A%0AMessage written by: "+username+" ("+email+").";
}