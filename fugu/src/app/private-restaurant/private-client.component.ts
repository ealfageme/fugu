import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

interface User {
    username: string;
    age?: number;
    favouritefood?: string;
    description?: string;
    email: string;
    password: string;
    confirmPassword: string;
}

@Component({
    selector: 'app-private-client',
    templateUrl: './private-client.component.html',
    styleUrls: ['./private-client.component.css']
})
export class PrivateClientComponent implements OnInit {

    public client: User;
    private city: string;
    private inNormalSession: boolean;
    private inFacebookSession: boolean;
    private inSession: boolean;
    private followButton: boolean;
    private unfollowButton: boolean;
    private restaurants: string[] = [];
    private following: string[] = [];
    private reviews: string[] = [];
    private vouchers: string[] = [];
    private bookingsInProcess: string[] = [];
    private bookingsAccepted: string[] = [];
    private user: string;


    constructor(private http: Http, private loginService: LoginService) {
        this.inSession = false;
        this.followButton = false;
        this.unfollowButton = true;
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/following').subscribe(
            response => {
                const  data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const  follow = data[i];
                    this.following.push(follow);
                }
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/book').subscribe(
            response => {
                const  data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const  book = data[i];
                    if (book.state === 'In process') {
                        this.bookingsInProcess.push(book);
                    } else {
                        this.bookingsAccepted.push(book);
                    }
                }
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/vouchers').subscribe(
            response => {
                const  data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const  voucher = data[i];
                    this.vouchers.push(voucher);
                }
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name).subscribe(
            response => {
                const  data = response.json();
                this.user = data;
                for (let i = 0; i < data.restaurants.length; i++) {
                    const  restaurant = data.restaurants[i];
                    this.restaurants.push(restaurant);
                }
                for (let i = 0; i < data.reviews.length; i++) {
                    const  review = data.reviews[i];
                    this.reviews.push(review);
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

    updateUser() {
        // CLIENT
        console.log(this.client.username);
        console.log(this.client.email);
        console.log(this.client.age);
        console.log(this.client.favouritefood);
        console.log(this.client.description);
        console.log(this.client.password);
        console.log(this.client.confirmPassword);

        const dataclient = {
            'name': this.client.username,
            'password': this.client.password,
            'email': this.client.email,
            'age': this.client.age,
            'description': this.client.description,
            'favouriteFood': this.client.favouritefood,
            'roles': 'ROLE_USER'
        };
        this.http.put('https://localhost:8443/api/clients/', dataclient).subscribe(
            response => console.log(response),
            error => console.error(error)
        );
    }
}
