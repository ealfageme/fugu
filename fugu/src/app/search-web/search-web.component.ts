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
  private restaurantsByPriceAndRating: string[] = [];
  email: string;
  password: string;
  maxPrice = 25;
  minPrice = 1;
  minRating = 0;
  maxRating = 5;
  city = "Madrid";
  typefood = "All";
  nameRestaurant: String;


  constructor(private http: Http) {
    this.inSession = false;
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
    this.restaurantsByPriceAndRating = [];
    this.http.get('https://localhost:8443/api/search-web/filters?min=' + this.minRating + '&&max=' + this.maxRating + '&&minPrice=' + this.minPrice + '&&maxPrice=' + this.maxPrice).subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.length; i++) {
          const restaurant = data[i];
          this.restaurantsByPriceAndRating.push(restaurant);
        }
      },
      error => console.error(error)
    );
    if (this.typefood !== "All") {
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
    } else { // find by city (only)
      this.http.get('https://localhost:8443/api/city/' + this.city).subscribe(
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
    this.intersection();
  }
intersection(){}

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
