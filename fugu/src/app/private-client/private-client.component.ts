import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import  {  Http  }  from  '@angular/http';

@Component({
    selector: 'app-private-client',
    templateUrl: './private-client.component.html',
    styleUrls: ['./private-client.component.css']
})
export class PrivateClientComponent implements OnInit {

    private city: string;
    private inNormalSession: boolean;
    private inFacebookSession: boolean;
    private inSession: boolean;
    private followButton: boolean;
    private unfollowButton: boolean;
    private  restaurants: string[] = [];
    private  following: string[] = [];
    private reviews: string[] = [];
    private vouchers: string[] = [];
    private bookingsInProcess: string[] = [];
    private user: string;


    constructor(private  http: Http, private loginService: LoginService) {
        this.inSession = false;
        this.followButton = false;
        this.unfollowButton = true;
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/following').subscribe(
            response => {
                const data = response.json();
                for (let i = 0; i < data.length; i++)  {
                  const follow = data[i];
                  this.following.push(follow);
                }
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name).subscribe(
            response => {
                const data = response.json();
                this.user = data;
                for (let i = 0; i < data.restaurants.length; i++)  {
                  const restaurant = data.restaurants[i];
                  this.restaurants.push(restaurant);
                }
                for (let i = 0; i < data.reviews.length; i++) {
                  const review = data.reviews[i];
                  this.reviews.push(review);
                }
                for (let i = 0; i < data.userVouchers.length; i++) {
                  const voucher = data.userVouchers[i];
                  this.vouchers.push(voucher);
                }
                for (let i = 0; i < data.bookings.length; i++) {
                  const book = data.bookings[i];
                  this.bookingsInProcess.push(book);
                }
            },
            error => console.error(error)
        );
    }

    ngOnInit() {
    }

    goTo(location: string): void {
        window.location.hash = location;
    }
}
