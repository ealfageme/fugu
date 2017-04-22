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
  date: string =new Date().toString();
  newBooking: boolean;
  private restaurantName : string;
  state: string;
  booking: Booking;
  oldDay = '13';
  oldHour = '1';
  constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: BookingService,private loginService: LoginService,) {
    const id = activatedRoute.snapshot.params['id'];
    this.restaurantName = activatedRoute.snapshot.params['name'];
    if (id) {
      service.getBooking(id, this.restaurantName).subscribe(
        book => this.booking = book,
        error => console.error(error)
      );
      this.newBooking = false;
    } else {
      this.booking = { date: '', guests: '0',specialRequirements: '', state: ''};
      this.newBooking = true;
    }
  }

  ngOnInit() {
  }
 

  cancel() {
    window.history.back();
  }

  save() {
		this.booking.date = "2017-04-" +"12"+ " " + "10";
    this.booking.state = "In process"
    this.service.saveBook(this.booking, this.restaurantName).subscribe(
      book => { },
      error => console.error('Error creating new book: ' + error)
    );
  }
      selectDay(newDay) {
	  document.getElementById(this.oldDay+"class").className = "";
	  document.getElementById(newDay+"class").className = "active active-date";
	  this.oldDay=newDay;
	  document.getElementById("day").innerHTML=newDay+"th";
	  document.getElementById("bookingday").nodeValue = newDay;
	  return false;
  }

  selectHour(newHour) {
	document.getElementById("hour"+this.oldHour).className = "";
	document.getElementById("hour"+newHour).className = "active active-date";
	this.oldHour=newHour;
	document.getElementById("bookinghour").nodeValue = document.getElementById("hour"+newHour).innerHTML;
	return false;
  }
}

