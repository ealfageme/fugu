import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {
  city: string;
  inSession: boolean;
  private restaurants: string[] = [];

  constructor(private http: Http, activatedRoute: ActivatedRoute) {
     this.city = activatedRoute.snapshot.params['name'];
    this.http.get('https://localhost:8443/api/restaurants/city/' + this.city).subscribe(
      response => {
        console.log(response);
        const data = response.json();
        for (let i = 0; i < data.length; i++) {
          const restaurant = data[i];
          this.restaurants.push(restaurant);
        }
      },
      error => console.error(error)
    );
    this.inSession = false; 
   }

  ngOnInit() {
  }

}
