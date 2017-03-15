$( "#moreRestaurants" ).on( "click", function() {
	var html=document.getElementById("restaurants").innerHTML
	$("#restaurants").replaceWith( '<div id="restaurants"> <img class="img-responsive" style="max-width: 20%;margin:auto;"src="/images/logo/spinner-loop.gif" alt="Custom Image"></div>' );
	$.ajax({
		url : "https://localhost:8443/restaurants/?pagenumber=1"
	}).done(function(data) {		
		console.log(data);
		console.log(data[0].name);
		
		var html2='<div class="menu row rowmar">';
		html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href="/public-restaurant/"'+data[0].name+' class="food"><div class="food-desc">';
		html2+='<h6 class="food-name">'+data[0].name+' ('+data[0].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"> 5</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data[0].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
		html2+='<!-- /menu-column --></div>';
		console.log(html2);
		$("#restaurants").replaceWith('<div class="menus-container" id="restaurants">'+html2+ '</div>');
		//{{#restaurant}}
		/*<div class="col-md-6 wow fadeInRight animated"
			style="visibility: visible; animation-name: fadeInRight;">

			<div class="menu-column">

				<button
					onClick=" window.location.href='/public-restaurant/{{name}}'"
					class="food">


					<div class="food-desc">
						<h6 class="food-name">{{name}} ({{foodType}})</h6>
						<br>
						<div class="food-price">
							<span class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"> {{rate}}</span>
						</div>
						<!-- /food-price -->
					</div>
					<!-- /food-desc -->
					<div class="food-details">
						<span>{{address}}</span>
					</div>
					<!-- /food-details -->
				</button>


			</div>
			<!-- /food -->
		</div>
		{{/restaurant}}
		<!-- /menu-column -->
	</div>'*/
	}).fail(function() {
	    alert( "error" );
	});
});
