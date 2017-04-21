import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

export interface Booking {
  id?: number;
  date: string;
  guests: number;
  specialRequirements: string;
  restaurantName: string;
  userName: string;
}

const URL = 'http://localhost:8080/api/'+this.restaurantName+'book';

@Injectable()
export class BookingService {

  constructor(private http: Http) { }

  getBooking(i : number) {
      return this.http.get(URL +i, { withCredentials: true })
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

  getBookings() {
    return this.http.get(URL , { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  saveBook(booking: Booking) {
    const body = JSON.stringify(booking);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    if (!booking.id) {
      return this.http.post(URL, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    } else {
      return this.http.put(URL + booking.id, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }
  }

  removeBook(booking: Booking) {

    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(URL + booking.id, options)
      .map(response => undefined)
      .catch(error => this.handleError(error));
  }

  updateBook(booking: Booking) {

    const body = JSON.stringify(booking);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.put(URL + booking.id, body, options)
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
