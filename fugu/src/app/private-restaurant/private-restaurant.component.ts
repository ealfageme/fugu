import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { LoginService } from './../services/login.service';
import { Router } from '@angular/router';

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


  constructor(private http: Http, private loginService: LoginService, private router: Router) {
    this.inSession = false;
    this.followButton = false;
    this.unfollowButton = true;
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name).subscribe(
      response => {
        const data = response.json();
        this.restaurant = data;
      },
      error =>  {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
   this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/menus').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const menu = data.content[i];
          this.menus.push(menu);
        }
      },
      error =>  {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
     this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/voucher').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const voucher = data.content[i];
          this.vouchers.push(voucher);
        }
      },
      error =>  {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/book').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const book = data.content[i];
          this.bookings.push(book);
        }
      },
      error =>  {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/reviews').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const review = data.content[i];
          this.reviews.push(review);
        }
      },
      error =>  {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
  }

  ngOnInit() {
  }

}
