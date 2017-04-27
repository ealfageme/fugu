import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

export interface Booking {
  id?: number;
  date: string;
  number: string;
  specialRequirements: string;
  state: string;
}

@Injectable()
export class BookingService {

  constructor(private http: Http) { }
  
  getBooking(i : number,restaurantName: string) {
    const getURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/book';
      return this.http.get(getURL +i, { withCredentials: true })
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

  getBookings(restaurantName: string) {
    const getURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/book';
    return this.http.get(getURL , { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  saveBook(booking: Booking, restaurantName: string) {
    const saveURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/book';
    const body = JSON.stringify(booking);
    console.log(body);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    if (!booking.id) {
      return this.http.post(saveURL, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    } else {
      return this.http.put(saveURL + booking.id, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }
  }

  removeBook(booking: Booking, restaurantName:string) {
    const removeURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/book';
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(removeURL + booking.id, options)
      .map(response => undefined)
      .catch(error => this.handleError(error));
  }

  updateBook(booking: Booking, restaurantName: string) {
    const updateURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/book';
    const body = JSON.stringify(booking);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.put(updateURL + booking.id, body, options)
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
