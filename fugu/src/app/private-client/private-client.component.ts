import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';



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

    client: User;
    city: string;
    inNormalSession: boolean;
    inFacebookSession: boolean;
    inSession: boolean;
    followButton: boolean;
    unfollowButton: boolean;
    restaurants: string[] = [];
    following: string[] = [];
    reviews: string[] = [];
    vouchers: string[] = [];
    bookingsInProcess: string[] = [];
    bookingsAccepted: string[] = [];
    user: string;
    params: any;
    loginService: LoginService;


    constructor(private http: Http, private router: Router, loginServiceaux: LoginService) {
        this.loginService = loginServiceaux;
        this.inSession = false;
        this.followButton = false;
        this.unfollowButton = true;
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/following').subscribe(
            response => {
                const data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const follow = data[i];
                    this.following.push(follow);
                }
                console.log(data);
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name + '/book').subscribe(
            response => {
                const data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const book = data[i];
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
                const data = response.json();
                for (let i = 0; i < data.length; i++) {
                    const voucher = data[i];
                    this.vouchers.push(voucher);
                }
            },
            error => console.error(error)
        );
        this.http.get('https://localhost:8443/api/clients/' + this.loginService.user.name).subscribe(
            response => {
                const data = response.json();
                this.user = data;
                this.client = {
                    username: data.name,
                    age: data.age,
                    favouritefood: data.favouriteFood,
                    description: data.description,
                    email: data.email,
                    password: data.password,
                    confirmPassword: ''
                };
                for (let i = 0; i < data.restaurants.length; i++) {
                    const restaurant = data.restaurants[i];
                    this.restaurants.push(restaurant);
                }
                for (let i = 0; i < data.reviews.length; i++) {
                    const review = data.reviews[i];
                    this.reviews.push(review);
                }
            },
            error => console.error(error)
        );
    }

    ngOnInit() {
        this.client = {
            username: '',
            email: '',
            password: '',
            confirmPassword: ''
        };
    }

    goTo(location: string): void {
        window.location.hash = location;
    }

    updateUser() {
        // CLIENT

        const dataclient = {
            'name': this.client.username,
            'password': this.client.password,
            'email': this.client.email,
            'age': this.client.age,
            'description': this.client.description,
            'favouriteFood': this.client.favouritefood,
            'roles': 'ROLE_USER'
        };
        console.log(dataclient);
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });
        this.http.put('https://localhost:8443/api/clients/', dataclient, options).subscribe(
            response => console.log(response),
            error => console.error(error)
        );
    }

    fileChange(event) {
        const fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            const file: File = fileList[0];
            const formData: FormData = new FormData();
            formData.append('file', file, file.name);
            const headers = new Headers();
            headers.append('Accept', 'application/json');
            const options = new RequestOptions({ withCredentials: true });
            this.http.post('https://localhost:8443/api/clients/image/upload', formData, options)
                .subscribe(
                data => {
                    console.log(data);
                    this.router.navigate(['/new/private-client/refresh']);
                },
                error => console.log(error)
                );
        }
    }
}
