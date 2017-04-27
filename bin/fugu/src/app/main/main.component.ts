import { Component, OnInit, ViewChild } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import {LoginService } from '../services/login.service';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  username: string;
  email2: string;
  body: string;
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

  constructor(private http: Http, private route: ActivatedRoute, private router: Router, private loginService: LoginService) {
    this.inSession = loginService.isLogged;
    console.log("ammmmms: "+ loginService.isLogged)
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/?page=' + this.pagenumber + '&size=4').subscribe(
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
  ngOnInit() {
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

