import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-public-restaurant',
  templateUrl: './public-restaurant.component.html',
  styleUrls: ['./public-restaurant.component.css']
})
export class PublicRestaurantComponent implements OnInit {

  inSession: boolean;
  favButton: boolean;
  nextRestaurant = true;
  prevRestaurant = false;
  facebookSession: boolean;
  private restaurant: string;
  email: string;
  password: string;
  pagenumber = 0;
  private menus: string[] = [];
  private vouchers: string[] = [];
  private reviews: string[] = [];

  constructor(private http: Http) {
    this.favButton = true;
    this.inSession = true;
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/1').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        this.restaurant = data;
        console.log(this.restaurant);
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/restaurants/1/menus/?page=0&size=4').subscribe(
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
    this.http.get('https://localhost:8443/api/restaurants/1/voucher').subscribe(
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
    this.http.get('https://localhost:8443/api/restaurants/1/reviews').subscribe(
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

}
