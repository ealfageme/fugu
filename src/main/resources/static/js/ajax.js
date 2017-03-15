var pagenumber=document.getElementById("reference").value;
$( "#moreRestaurants" ).on( "click", function() {
	var html=document.getElementById("restaurants").innerHTML
	if(pagenumber==0){
		document.getElementById('prevRestaurants').style.display = '';
	}
	pagenumber++;
	$("#restaurants").replaceWith( '<div id="restaurants"> <img class="img-responsive" style="max-width: 20%;margin:auto;"src="/images/logo/spinner-loop.gif" alt="Custom Image"></div>' );
	$.ajax({
		url : "https://localhost:8443/restaurants/?page="+pagenumber+"&size=4"
	}).done(function(data) {		
		console.log(data);
		console.log(data.content[0].name);
		console.log("https://localhost:8443/restaurants/?page="+pagenumber+"&size=4");
		console.log(pagenumber);
		var web="'/public-restaurant/"+data.content[0].name+"'";
		var html2='<div class="menu row rowmar">';
		
		html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" value="'+pagenumber+'" id="reference" class="food"><div class="food-desc">';
		html2+='<h6 class="food-name">'+data.content[0].name+' ('+data.content[0].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[0].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
		if(data.content[1]!=null){
			web="'/public-restaurant/"+data.content[1].name+"'";
			html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
			html2+='<h6 class="food-name">'+data.content[1].name+' ('+data.content[1].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[1].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
			if(data.content[2]!=null){
				web="'/public-restaurant/"+data.content[2].name+"'";
				html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
				html2+='<h6 class="food-name">'+data.content[2].name+' ('+data.content[2].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[2].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';	
			}
			if(data.content[3]!=null){
				web="'/public-restaurant/"+data.content[3].name+"'";
				html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
				html2+='<h6 class="food-name">'+data.content[3].name+' ('+data.content[3].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[3].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
			}else{
				$("#moreRestaurants").hide();
			}
		}
		html2+='<!-- /menu-column --></div>';
		$("#restaurants").replaceWith('<div class="menus-container" id="restaurants">'+html2+ '</div>');
	
	}).fail(function() {
	    alert( "error" );
	});
});

$( "#prevRestaurants" ).on( "click", function() {
	var html=document.getElementById("restaurants").innerHTML
	$("#moreRestaurants").show();
	pagenumber--;
	$("#restaurants").replaceWith( '<div id="restaurants"> <img class="img-responsive" style="max-width: 20%;margin:auto;"src="/images/logo/spinner-loop.gif" alt="Custom Image"></div>' );
	$.ajax({
		url : "https://localhost:8443/restaurants/?page="+pagenumber+"&size=4"
	}).done(function(data) {		
		console.log(data);
		console.log(data.content[0].name);
		console.log("https://localhost:8443/restaurants/?page="+pagenumber+"&size=4");
		console.log(pagenumber);
		var web="'/public-restaurant/"+data.content[0].name+"'";
		var html2='<div class="menu row rowmar">';
		
		html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" value="'+pagenumber+'" id="reference" class="food"><div class="food-desc">';
		html2+='<h6 class="food-name">'+data.content[0].name+' ('+data.content[0].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[0].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
		if(data.content[1]!=null){
			web="'/public-restaurant/"+data.content[1].name+"'";
			html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
			html2+='<h6 class="food-name">'+data.content[1].name+' ('+data.content[1].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[1].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
			if(data.content[2]!=null){
				web="'/public-restaurant/"+data.content[2].name+"'";
				html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
				html2+='<h6 class="food-name">'+data.content[2].name+' ('+data.content[2].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[2].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';	
			}
			if(data.content[3]!=null){
				web="'/public-restaurant/"+data.content[3].name+"'";
				html2+='<div class="col-md-6 wow fadeInRight animated"style="visibility: visible; animation-name: fadeInRight;"><div class="menu-column"><button onClick="'+'window.location.href='+web+'" class="food"><div class="food-desc">';
				html2+='<h6 class="food-name">'+data.content[3].name+' ('+data.content[3].foodType+')</h6><br><div class="food-price"><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star">'+ data.content[0].rate+'</span></div><!-- /food-price --></div><!-- /food-desc --><div class="food-details"><span>'+data.content[3].address+'</span></div><!-- /food-details --></button></div><!-- /food --></div>';
			}else{
				$("#moreRestaurants").hide();
			}
		}
		html2+='<!-- /menu-column --></div>';
		$("#restaurants").replaceWith('<div class="menus-container" id="restaurants">'+html2+ '</div>');
		if(pagenumber==0){
			document.getElementById('prevRestaurants').style.display = 'none';
		}
	}).fail(function() {
	    alert( "error" );
	});
});
