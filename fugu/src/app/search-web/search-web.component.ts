import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-search-web',
  templateUrl: './search-web.component.html',
  styleUrls: ['./search-web.component.css']
})
export class SearchWebComponent implements OnInit {
  inSession: boolean;
  facebookSession: boolean;
  private restaurants: string[] = [];
  email: string;
  password: string;
  maxPrice = 25;
  minPrice = 1;
  minRating = 0;
  maxRating = 5;
  city: String;
  typefood: String;
  nameRestaurant: String;


  constructor(private http: Http) {
    this.inSession = false;
    //dates as default
    this.city = "Madrid";
    this.typefood = "Chinese";
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
    this.http.get('https://localhost:8443/api/search-web/filters?min=' + this.minRating + '&&max=' + this.maxRating + '&&minPrice=' + this.minPrice + '&&maxPrice=' + this.maxPrice).subscribe(
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
  searchByCityAndType() {
    this.restaurants = [];
    
    this.http.get('https://localhost:8443/api/search-web/foodtypeandcity?typeFood=' + this.typefood + '&&city=' + this.city).subscribe(
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
