import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-public-client',
  templateUrl: './public-client.component.html',
  styleUrls: ['./public-client.component.css']
})
export class PublicClientComponent implements OnInit {
  private city: string;
  private inSession: boolean;
  private followButton: boolean;
  private unfollowButton: boolean;
  private restaurants: string[] = [];
  private following: string[] = [];
  private reviews: string[] = [];
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
  }

  ngOnInit() {
  }

}