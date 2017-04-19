import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-private-client',
  templateUrl: './private-client.component.html',
  styleUrls: ['./private-client.component.css']
})
export class PrivateClientComponent implements OnInit {

  private city: string;
  private inNormalSession: boolean;
  private inFacebookSession: boolean;
  private inSession: boolean;
  private followButton: boolean;
  private unfollowButton: boolean;
  private restaurants: string[] = [];
  private following: string[] = [];
  private reviews: string[] = [];
  private vouchers: string[] = [];
  private bookingsInProcess: string[] = [];
  private user:string;


  constructor(private http: Http) { 
    this.inSession=false;
    this.followButton=false;
    this.unfollowButton=true;
    this.http.get('https://localhost:8443/api/restaurants/').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const restaurant = data.content[i];
          console.log(restaurant);
          this.restaurants.push(restaurant);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/clients/').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const follow = data.content[i];
          console.log(follow);
          this.following.push(follow);
        }
      },
      error => console.error(error)
    );
     this.http.get('https://localhost:8443/api/clients/1').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        for (let i = 0; i < data.reviews.length; i++) {
          const review = data.reviews[i];
          console.log(review);
          this.user = data;
          console.log(this.user);
          this.reviews.push(review);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/clients/1').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        for (let i = 0; i < data.userVouchers.length; i++) {
          const voucher = data.userVouchers[i];
          console.log(voucher);
          this.vouchers.push(voucher);
        }
      },
      error => console.error(error)
    );
    this.http.get('https://localhost:8443/api/clients/1').subscribe(
      response => {
        console.log(response.json());
        const data = response.json();
        for (let i = 0; i < data.bookings.length; i++) {
          const book = data.bookings[i];
          console.log(book);
          this.bookingsInProcess.push(book);
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
}
