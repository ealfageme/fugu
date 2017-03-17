var pagenumber=document.getElementById("reference").value;
var restaurantid=document.getElementById("restaurantid").value;
$( "#seeMore" ).on( "click", function() { 
	var html=document.getElementById("menus").innerHTML
	pagenumber++;
	$("#menus").append( '<div id="spinner"> <img class="img-responsive" style="max-width: 20%;margin:auto;"src="/images/logo/spinner-loop.gif" alt="Custom Image"></div>' );
	$.ajax({
		url : "https://localhost:8443/restaurants/"+restaurantid+"/menus/?page="+pagenumber+"&size=4"
	}).done(function(data) {		
		console.log(data);
		console.log("https://localhost:8443/restaurants/"+restaurantid+"/menus/?page="+pagenumber+"&size=4");
		console.log(data.content[0].name);
		$( "#spinner" ).delay( 800 );
		$("#spinner").replaceWith('');

		
		var html2='<div class="col-md-6 wow fadeInRight animated" style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><div class="food"><div class="food-desc"><img src="/private-restaurant/menu/image/menuImageRestaurant'+restaurantid+(1+(4*pagenumber))+'.jpg" /><h6 class="food-name">'+data.content[0].dish+'</h6><div class="dots"></div><div class="food-price"><span>'+data.content[0].price+'€</span></div></div><div class="food-details"><span>'+data.content[0].description+'</span></div></div></div></div>';
		if(data.content[1]!=null){
			html2+='<div class="col-md-6 wow fadeInRight animated" style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><div class="food"><div class="food-desc"><img src="/private-restaurant/menu/image/menuImageRestaurant'+restaurantid+(2+(4*pagenumber))+'.jpg" /><h6 class="food-name">'+data.content[1].dish+'</h6><div class="dots"></div><div class="food-price"><span>'+data.content[1].price+'€</span></div></div><div class="food-details"><span>'+data.content[1].description+'</span></div></div></div></div>';
			}if(data.content[2]!=null){
				html2+='<div class="col-md-6 wow fadeInRight animated" style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><div class="food"><div class="food-desc"><img src="/private-restaurant/menu/image/menuImageRestaurant'+restaurantid+(3+(4*pagenumber))+'.jpg" /><h6 class="food-name">'+data.content[2].dish+'</h6><div class="dots"></div><div class="food-price"><span>'+data.content[2].price+'€</span></div></div><div class="food-details"><span>'+data.content[2].description+'</span></div></div></div></div>';
				}if(data.content[3]!=null){
					html2+='<div class="col-md-6 wow fadeInRight animated" style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><div class="food"><div class="food-desc"><img src="/private-restaurant/menu/image/menuImageRestaurant'+restaurantid+(4+(4*pagenumber))+'.jpg" /><h6 class="food-name">'+data.content[3].dish+'</h6><div class="dots"></div><div class="food-price"><span>'+data.content[3].price+'€</span></div></div><div class="food-details"><span>'+data.content[3].description+'</span></div></div></div></div>';
				}else{
				$("#seeMore").hide();
				}
		$("#menus").append(html2);
		document.getElementById("reference").value=pagenumber;
	}).fail(function() {
	    alert( "error" );
	});
});