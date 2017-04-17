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

  constructor(private http: Http) {
    this.inSession = false;
    this.facebookSession = false;
    this.http.get('https://localhost:8443/api/restaurants/').subscribe(
      response => console.log(response.json()),
      error => console.error(error)
      );
   }

  ngOnInit() {
  }

}

