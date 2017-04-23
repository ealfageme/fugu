import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from './../services/login.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'app-public-client',
  templateUrl: './public-client.component.html',
  styleUrls: ['./public-client.component.css']
})
export class PublicClientComponent implements OnInit {
  private city: string;
  private inSession: boolean;
  private followButton: boolean;
  private restaurants: string[] = [];
  private following: string[] = [];
  private reviews: string[] = [];
  private user: string;
  private username: string;
  params: any;


  constructor(private http: Http, activatedRoute: ActivatedRoute, private router: Router, private loginService: LoginService) {
    this.params = activatedRoute.params.subscribe(
      params => {
        this.binding(params);
      }
    );
  }

  ngOnInit() {
  }

  goTo(location: string): void {
    window.location.hash = location;
  }

  binding(params) {
    this.username = params['username'];
    this.inSession = false;
    console.log("voy a hacer el get");
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true});
    this.http.get('https://localhost:8443/api/clients/'+this.username+'/isfollowing', options).subscribe(
      response => {
        const  data = response.json()._body;
          this.followButton = true;
          console.log(data);
           console.log(data);
            console.log(data);
          if(data!=null)
          {
            console.log("ya le sigue");
            this.followButton = false;
          }
      },
      error => console.error(error)
    );
    this.getRestaurants();
    this.getFollowers();
    this.getReviews();
  }

  getRestaurants() {
    this.restaurants.splice(0);
    this.http.get('https://localhost:8443/api/restaurants/?page=0&size=4').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const  restaurant = data.content[i];
          this.restaurants.push(restaurant);
        }
      },
      error => console.error(error)
    );
  }

  getFollowers() {
    this.following.splice(0);
    this.http.get('https://localhost:8443/api/clients/').subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const  follow = data.content[i];
          this.following.push(follow);
        }
      },
      error => console.error(error)
    );
  }

  getReviews() {
    this.reviews.splice(0);
    this.http.get('https://localhost:8443/api/clients/' + this.username).subscribe(
      response => {
        const  data = response.json();
        for (let i = 0; i < data.reviews.length; i++) {
          const  review = data.reviews[i];
          this.reviews.push(review);
        }
        this.user = data;
      },
      error => console.error(error)
    );
  }
  follow(){
    var body;
    console.log('https://localhost:8443/api/clients/' + this.username +"/follow")
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    this.followButton = false;
    const options = new RequestOptions({ withCredentials: true});
    this.http.post('https://localhost:8443/api/clients/' + this.username +"/follow","",options).subscribe(
        response  =>  console.log(response),
        error  =>  console.error(error)
      );
  }
    unfollow(){
    console.log('https://localhost:8443/api/clients/' + this.username +"/follow")
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    this.followButton = true;
    const options = new RequestOptions({ withCredentials: true});
    this.http.delete('https://localhost:8443/api/clients/' + this.username +"/unfollow",options).subscribe(
        response  =>  console.log(response),
        error  =>  console.error(error)
      );
  }

}
