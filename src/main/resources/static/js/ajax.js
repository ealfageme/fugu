$( "#moreRestaurants" ).on( "click", function() {
	var html=document.getElementById("restaurants").innerHTML
	$("#restaurants").replaceWith( '<div id="restaurants"> <img class="img-responsive" style="max-width: 20%;margin:auto;"src="/images/logo/spinner-loop.gif" alt="Custom Image"></div>' );
	$.ajax({
		url : "https://localhost:8443/restaurants/?pagenumber=1"
	}).done(function(data) {
		$("#restaurants").replaceWith('<div class="menus-container" id="restaurants">'+html+ '</div>');
		console.log(data);
	}).fail(function() {
	    alert( "error" );
	});
});
