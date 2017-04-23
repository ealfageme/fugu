import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-public-restaurant',
  templateUrl: './public-restaurant.component.html',
  styleUrls: ['./public-restaurant.component.css']
})
export class PublicRestaurantComponent implements OnInit {

  inSession: boolean;
  seeMorebtn: boolean;
  favButton: boolean;
  nextRestaurant = true;
  prevRestaurant = false;
  facebookSession: boolean;
   restaurant: string;
  private restaurantname: string;
  email: string;
  password: string;
  number = 0;
  private menus: string[] = [];
  private vouchers: string[] = [];
  private reviews: string[] = [];
  private bookings: string[] = [];
  private rate: number = 1;
  private content: String = "Please enter your message";

  constructor(private http: Http, activatedRoute: ActivatedRoute, private loginService: LoginService) {
    this.restaurantname = activatedRoute.snapshot.params['name'];
    this.favButton = true;
    this.seeMorebtn = true;
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
    this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname + '/menus/?page=' + this.number + '&size=4').subscribe(
      response => {
        this.number ++;
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

   seeMore() {
      this.http.get('https://localhost:8443/api/restaurants/' + this.restaurantname + '/menus/?page=' + this.number + '&size=4').subscribe(
      response => {
        this.number ++;
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const menu = data.content[i];
          this.menus.push(menu);
        }
        if (data.content.length < 4) {
          this.seeMorebtn = false;
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

  sendReview(){
      let day = new Date();
      console.log(this.content);
      console.log(this.rate)
      const review= {
            "content": this.content,
            "rate": this.rate,
            "date": day
      }
       this.http.post('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/reviews',  review).subscribe(
        response  =>  console.log(response),
        error  =>  console.error(error)
      );
   
  }
  fav(){
    console.log('https://localhost:8443/api/restaurants/' + this.restaurantname +"/follow")
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    this.favButton = false;
    const options = new RequestOptions({ withCredentials: true, headers});
    this.http.post('https://localhost:8443/api/restaurants/' + this.restaurantname +"/follow","",options).subscribe(
        response  =>  console.log(response),
        error  =>  console.error(error)
      );
  }
    unfav(){
    console.log('https://localhost:8443/api/restaurants/' + this.restaurantname +"/follow")
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    this.favButton = true;
    const options = new RequestOptions({ withCredentials: true, headers});
    this.http.delete('https://localhost:8443/api/restaurants/' + this.restaurantname +"/unfollow",options).subscribe(
        response  =>  console.log(response),
        error  =>  console.error(error)
      );
  }
  

}