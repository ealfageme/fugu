import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
@Component({
  selector: 'app-private-restaurant',
  templateUrl: './private-restaurant.component.html',
  styleUrls: ['./private-restaurant.component.css']
})
export class PrivateRestaurantComponent implements OnInit {

  private city: string;
  private inNormalSession: boolean;
  private inFacebookSession: boolean;
  private inSession: boolean;
  private followButton: boolean;
  private unfollowButton: boolean;
  private restaurants: string[] = [];
  private bookings: string[] = [];
  private following: string[] = [];
  private reviews: string[] = [];
  private vouchers: string[] = [];
  private menus: string[] = [];
  private bookingsInProcess: string[] = [];
  restaurant: string;
  private user: string;


  constructor(private http: Http) {
    this.inSession = false;
    this.followButton = false;
    this.unfollowButton = true;
    this.http.get('https://localhost:8443/api/restaurants/American%20Whey').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        this.restaurant = data;
        console.log('1');
        console.log(this.restaurant);
        console.log('2');
},
      error => console.error(error)
    );
   this.http.get('https://localhost:8443/api/restaurants/American%20Whey/menus').subscribe(
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
     this.http.get('https://localhost:8443/api/restaurants/American%20Whey/voucher').subscribe(
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
    this.http.get('https://localhost:8443/api/restaurants/American%20Whey/book').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const book = data.content[i];
          this.bookings.push(book);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/restaurants/American%20Whey/reviews').subscribe(
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
