import { Component, OnInit } from '@angular/core';
import  {  Http  }  from  '@angular/http';
import  {  Router, ActivatedRoute  }  from  '@angular/router';

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
  private  restaurants: string[] = [];
  private  following: string[] = [];
  private reviews: string[] = [];
  private user: string;
  private username: string;
  params: any;


  constructor(private  http: Http, activatedRoute: ActivatedRoute, private router: Router) {
    this.params = activatedRoute.params.subscribe(
      params => {
        this.username = params['username'];
        console.log(this.username); // this consoles the correct true/false value
        this.inSession = false;
        this.followButton = false;
        this.unfollowButton = true;
        this.restaurants.splice(0);
        this.http.get('https://localhost:8443/api/restaurants/?page=0&size=4').subscribe(
          response => {
            const data = response.json();
            for (let i = 0; i < data.content.length; i++)  {
              const restaurant = data.content[i];
              this.restaurants.push(restaurant);
            }
          },
          error => console.error(error)
        );
        this.following.splice(0);
        this.http.get('https://localhost:8443/api/clients/').subscribe(
          response => {
            const data = response.json();
            for (let i = 0; i < data.content.length; i++) {
              const follow = data.content[i];
              this.following.push(follow);
            }
          },
          error => console.error(error)
        );
        this.reviews.splice(0);
        this.http.get('https://localhost:8443/api/clients/' + this.username).subscribe(
          response => {
            const data = response.json();
            for (let i = 0; i < data.reviews.length; i++)  {
              const review = data.reviews[i];
              this.reviews.push(review);
            }
          this.user = data;
          },
          error => console.error(error)
        );
      }
    );

  }

  ngOnInit() {
  }

  goTo(location: string): void {
    window.location.hash = location;
  }
}
