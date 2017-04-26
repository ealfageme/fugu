import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

export interface Voucher {
  id?: number;
  name: string;
  description: string;
  expiryDate: string;
}

@Injectable()
export class VoucherService {
  vous:string[];
  constructor(private http: Http) { }
  
  getVoucher(i : number,restaurantName: string) {
    const getURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
      return this.http.get(getURL +i, { withCredentials: true })
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

  getVouchers(restaurantName: string) {
    const getURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
    return this.http.get(getURL , { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  saveVoucher(voucher: Voucher, restaurantName: string) {
    const saveURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
    var calendar = new Date();
			     if (voucher.expiryDate == '1') {
				       calendar.setDate(calendar.getDate() + 7);
				}else if (voucher.expiryDate == '2') {
				       calendar.setDate(calendar.getDate() + 14);
				}else if (voucher.expiryDate == '3') {
						calendar.setDate(calendar.getDate() + 21);
				}else if(voucher.expiryDate == '4'){
					      calendar.setDate(calendar.getDate() + 30);
				}else if(voucher.expiryDate == '5'){
					calendar.setDate(calendar.getDate() + 60);
				}else if(voucher.expiryDate == '6'){
					calendar.setDate(calendar.getDate() + 90);
				}else if(voucher.expiryDate == '7'){
					calendar.setDate(calendar.getDate() + 180);}
    voucher.expiryDate = calendar.toString();
    const body = JSON.stringify(voucher);
    console.log(body);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    if (!voucher.id) {
      return this.http.post(saveURL, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    } else {
      return this.http.put(saveURL + voucher.id, body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }
  }

  removeVoucher(voucher: Voucher, restaurantName:string) {
    const removeURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(removeURL + voucher.id, options)
      .map(response => undefined)
      .catch(error => this.handleError(error));
  }
  vouchers(restaurantName:string){
    this.getAllVouchers(restaurantName);
    return this.vous;
  }
  getAllVouchers(restaurantName :string){
    this.vous = [];
    const getURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
    this.http.get(getURL , { withCredentials: true })
    .subscribe(
      response =>{
          const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const vou = data.content[i];
          this.vous.push(vou);
         
        } 
        },
      error => console.error(error)
    )
  }

  updateVoucher(voucher: Voucher, restaurantName: string) {
    const updateURL = 'https://localhost:8443/api/restaurants/'+restaurantName+'/voucher';
    const body = JSON.stringify(voucher);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.put(updateURL + voucher.id, body, options)
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
