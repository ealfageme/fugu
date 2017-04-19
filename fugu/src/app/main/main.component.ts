import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';

import {AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  inSession: boolean;
  nextRestaurant = true;
  prevRestaurant = false;
  facebookSession: boolean;
  private restaurants: string[] = [];
  email: string;
  password: string;
  pagenumber = 0;
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(private http: Http, private route: ActivatedRoute, private router: Router,
        private authenticationService: AuthenticationService) {
    this.inSession = false;
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/?page=' + this.pagenumber + '&size=4').subscribe(
      response => {
        console.log(response);
        const  data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const  restaurant = data.content[i];
          this.restaurants.push(restaurant);
        }
      },
      error => console.error(error)
    );
  }
  ngOnInit() {
    // reset login status
        this.authenticationService.logout();
        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    console.log(this.email + ' ' + this.password);
            this.loading = true;
        this.authenticationService.login(this.email, this.password)
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                },
                error => {
                    console.error(error);
                    this.loading = false;
                });
  }

  nextRestaurants() {
    this.pagenumber++;
    this.http.get('https://localhost:8443/api/restaurants/?page=' + this.pagenumber + '&size=4').subscribe(
      response => {
        const  data = response.json();
        this.restaurants.splice(0, 4);
        for (let i = 0; i < data.content.length; i++) {
          const  restaurant = data.content[i];
          this.restaurants.push(restaurant);
        }
        if (this.restaurants.length < 4) {
          this.nextRestaurant = false;
        }
        this.prevRestaurant = true;
      },
    error => console.error(error)
    );
  }

  prevRestaurants() {
    this.pagenumber--;
    this.http.get('https://localhost:8443/api/restaurants/?page=' + this.pagenumber + '&size=4').subscribe(
      response => {
        const  data = response.json();
        this.restaurants.splice(0, 4);
        for (let i = 0; i < data.content.length; i++) {
          const  restaurant = data.content[i];
          this.restaurants.push(restaurant);
        }
        if (this.pagenumber === 0) {
          this.prevRestaurant = false;
        }
          this.nextRestaurant = true;
      },
    error => console.error(error)
    );
  }
  goTo(location: string): void {
    window.location.hash = location;
  }
}

