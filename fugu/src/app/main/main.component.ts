import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  inSession: boolean;
  facebookSession: boolean;
  private restaurants: string[] = [];
  email: string;
  password: string;

  constructor(private http: Http) {
    this.inSession = false;
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/').subscribe(
      response =>  {
        console.log(response);
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const restaurant = data.content[i];
          this.restaurants.push(restaurant);
        }
      },
      error => console.error(error)
      );
   }

  ngOnInit() {
  }

  logIn() {
    console.log(this.email + " " + this.password);
  }

}

