import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private http: Http) {
    this.http.get('https://localhost:8443/api/restaurants/').subscribe(
      response => console.log(response.json()),
      error => console.error(error)
      );
   }

  ngOnInit() {
  }

}

