import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-public-restaurant',
  templateUrl: './public-restaurant.component.html',
  styleUrls: ['./public-restaurant.component.css']
})
export class PublicRestaurantComponent implements OnInit {

  inSession: boolean;
  oldDay:string="13";
  oldHour:string="1";
  favButton: boolean;
  nextRestaurant = true;
  prevRestaurant = false;
  facebookSession: boolean;
  private restaurant: string;
  private restaurantname: string;
  email: string;
  password: string;
  pagenumber = 0;
  private menus: string[] = [];
  private vouchers: string[] = [];
  private reviews: string[] = [];

  constructor(private http: Http, activatedRoute: ActivatedRoute) {
    this.restaurantname = activatedRoute.snapshot.params['name'];
    this.favButton = true;
    this.inSession = true;
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname).subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        this.restaurant = data;
        console.log(this.restaurant);
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname + '/menus/?page=0&size=4').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const menu = data.content[i];
          this.menus.push(menu);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname + '/voucher').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const voucher = data.content[i];
          this.vouchers.push(voucher);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname + '/reviews').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const review = data.content[i];
          this.reviews.push(review);
        }
      },
      error => console.error(error)
    );
   }

  ngOnInit() {
  }
  
  goTo(location: string): void {
    window.location.hash = location;
  }
  
    selectDay(newDay) {
	  document.getElementById(this.oldDay+"class").className = "";
	  document.getElementById(newDay+"class").className = "active active-date";
	  this.oldDay=newDay;
	  document.getElementById("day").innerHTML=newDay+"th";
	  document.getElementById("bookingday").nodeValue = newDay;
	  return false;
  }

  selectHour(newHour) {
	document.getElementById("hour"+this.oldHour).className = "";
	document.getElementById("hour"+newHour).className = "active active-date";
	this.oldHour=newHour;
	document.getElementById("bookinghour").nodeValue = document.getElementById("hour"+newHour).innerHTML;
	return false;
  }

}