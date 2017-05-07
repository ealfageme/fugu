import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { LoginService } from './../services/login.service';

@Component({
  selector: 'app-search-web',
  templateUrl: './search-web.component.html',
  styleUrls: ['./search-web.component.css']
})
export class SearchWebComponent implements OnInit {
  inSession: boolean;
  facebookSession: boolean;
  email2: string;
  body: string;
  email: string;
  restaurants: string[] = [];
  password: string;
  maxPrice = 25;
  minPrice = 1;
  minRating = 0;
  maxRating = 5;
  city: String;
  typefood: String;
  nameRestaurant = '';
  loginService: LoginService;


  constructor(private http: Http, loginServiceaux: LoginService) {
    this.loginService = loginServiceaux;
    this.inSession = false;
    //dates as default
    this.city = "Madrid";
    this.typefood = "All";
    this.facebookSession = false;


    this.http.get('https://localhost:8443/api/restaurants/?page=').subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const restaurant = data.content[i];
          this.restaurants.push(restaurant);
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

  searchByParam() {
    this.restaurants = [];
    if (this.typefood !== "All") {
      this.http.get('https://localhost:8443/api/search-web/AllParam?typeFood=' + this.typefood + '&&city=' + this.city + '&&min=' + this.minPrice + '&&max=' + this.maxPrice + '&&minRate=' + this.minRating + '&&maxRate=' + this.maxRating).subscribe(
        response => {
          const data = response.json();
          for (let i = 0; i < data.length; i++) {
            const restaurant = data[i];
            this.restaurants.push(restaurant);
          }
        },
        error => console.error(error)
      );

    } else {
      this.http.get('https://localhost:8443/api/search-web/AllParamWithoutType?city='+this.city+'&&min=' + this.minPrice + '&&max=' + this.maxPrice + '&&minRate=' + this.minRating + '&&maxRate=' + this.maxRating).subscribe(
        response => {
          const data = response.json();
          for (let i = 0; i < data.length; i++) {
            const restaurant = data[i];
            this.restaurants.push(restaurant);
          }
        },
        error => console.error(error)
      );
    }
  } 
  searchByName() {
    this.restaurants = [];
    this.http.get('https://localhost:8443/api/search-web/name?name=' + this.nameRestaurant).subscribe(
      response => {
        const restaurant = response.json();
        this.restaurants.push(restaurant);
      },
      error => console.error(error)
    );
  }
}
