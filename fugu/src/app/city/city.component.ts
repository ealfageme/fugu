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
  constructor(private http: Http, activatedRoute: ActivatedRoute) {
    this.http.get('https://localhost:8443/api/restaurants/').subscribe(
      response => console.log(response.json()),
      error => console.error(error)
    );
    this.city = activatedRoute.snapshot.params['name'];
    this.inSession = false;
   }

  ngOnInit() {
  }

}
