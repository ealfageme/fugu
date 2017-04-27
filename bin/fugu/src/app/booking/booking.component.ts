import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Booking, BookingService } from './booking.service';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  date: string = new Date().toString();
  newBooking: boolean;
  guests: string;
  private restaurantName: string;
  state: string;
  booking: Booking;
  oldDay = '13';
  oldHour = '12:00';
  constructor(private _router: Router, activatedRoute: ActivatedRoute,
    private service: BookingService, private loginService: LoginService) {

    const id = activatedRoute.snapshot.params['id'];
    this.restaurantName = activatedRoute.snapshot.params['name'];
    if (id) {
      service.getBooking(id, this.restaurantName).subscribe(
        book => this.booking = book,
        error => console.error(error)
      );
      this.newBooking = false;
    } else {
      this.booking = { date: '', number: '0', specialRequirements: '', state: '' };
      this.newBooking = true;
    }
  }

  ngOnInit() {
  }


  cancel() {
    window.history.back();
  }

  save() {
    this.booking.date = '2017-04-' + this.oldDay + ' ' + this.oldHour;
    this.booking.state = 'In process';
    this.booking.number = this.guests;
    console.log(this.guests + ' ' + this.booking.number);
    this.service.saveBook(this.booking, this.restaurantName).subscribe(
      book => { },
      error => console.error('Error creating new book: ' + error)
    );
  }
  selectDay(newDay) {
    document.getElementById(this.oldDay + "class").className = "";
    console.log(newDay);
    document.getElementById(newDay + "class").className = "active active-date";
    this.oldDay = newDay;
    document.getElementById("day").innerHTML = newDay + "th";
    document.getElementById("bookingday").nodeValue = newDay;
    return false;
  }

  selectHour(newHour) {
    document.getElementById(this.oldHour).className = "";
    document.getElementById(newHour).className = "active active-date";
    this.oldHour = newHour;
    console.log(newHour);
    return false;
  }
}

