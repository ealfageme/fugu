import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { LoginService } from './../services/login.service';
import { Router } from '@angular/router';


interface Restaurant {
  name: string;
  address?: string;
  city?: string;
  foodtype?: string;
  phone?: number;
  description?: string;
  email: string;
  password: string;
  confirmPassword: string;
}
export interface Booking {
  id?: number;
  date: string;
  number: string;
  specialRequirements: string;
  state: string;
}
@Component({
  selector: 'app-private-restaurant',
  templateUrl: './private-restaurant.component.html',
  styleUrls: ['./private-restaurant.component.css']
})
export class PrivateRestaurantComponent implements OnInit {

  restaurantUpdate: Restaurant;
  bookingUpdate: Booking;
  city: string;
  inNormalSession: boolean;
  inFacebookSession: boolean;
  inSession: boolean;
  followButton: boolean;
  unfollowButton: boolean;
  restaurants: string[] = [];
  following: string[] = [];
  reviews: string[] = [];
  menus: string[] = [];
  bookingsInProcess: Booking[] = [];
  bookingsAccepted: string[] = [];
  restaurant: string;
  user: string;
  nameDish: string;
  descriptionDish: string;
  priceDish: number;
  loginService: LoginService;


  constructor(private http: Http, loginServiceaux: LoginService, private router: Router) {
    this.loginService = loginServiceaux;
    this.inSession = false;
    this.nameDish = " ";
    this.descriptionDish = " ";
    this.priceDish = 0;
    this.followButton = false;
    this.unfollowButton = true;
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name).subscribe(
      response => {
        const data = response.json();
        this.restaurant = data;
        this.restaurantUpdate = {
          name: data.name,
          address: data.address,
          city: data.city.name,
          foodtype: data.foodType,
          phone: data.phone,
          description: data.name,
          email: data.email,
          password: data.password,
          confirmPassword: ''
        };
        console.log(data);
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/menus').subscribe(
      response => {
        const data = response.json();


        for (let i = 0; i < data.content.length; i++) {
          const menu = data.content[i];
          this.menus.push(menu);
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );

    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/book').subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const book = data.content[i];
          if (book.state === 'In process') {
            this.bookingsInProcess.push(book);
          } else {
            this.bookingsAccepted.push(book);
          }
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/reviews').subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const review = data.content[i];
          this.reviews.push(review);
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );
  }

  ngOnInit() {
    this.restaurantUpdate = {
      name: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  }
  createMenu() {
    if (this.nameDish !== " ") {
      let newMenu = {
        "dish": this.nameDish,
        "price": this.priceDish,
        "description": this.descriptionDish,
      }

      this.http.post('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/menus', newMenu).subscribe(
        response => {
          this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/menus').subscribe(
            response => {
              const data = response.json();
              const menu = data.content[data.content.length - 1];
              this.menus.push(menu);

            },
            error => {
              console.error(error);
              this.router.navigate(['/error/']);
            }
          );
        },
        error => console.error(error)
      );
      this.nameDish = " ";
      this.descriptionDish = " ";
      this.priceDish = 0;
    }


  }
menuFileChange(event) {
        const fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            const file: File = fileList[0];
            const formData: FormData = new FormData();
            const imageTitle = file.name;
            formData.append('file', file, imageTitle);
            const headers = new Headers();
            headers.append('Accept', 'application/json');
            const options = new RequestOptions({ withCredentials: true });
            this.http.post('https://localhost:8443/api/restaurants/menu/image/upload', formData, options)
                .subscribe(
                data => {
                  console.log(data);
                },
                error => console.log(error)
                );
        }
    }

  updateRestaurant() {
    // RESTAURANT
    let cityId = 0;
    if (this.restaurantUpdate.city === 'Madrid') {
      cityId = 1;
    } else if (this.restaurantUpdate.city === 'Barcelona') {
      cityId = 2;
    } else if (this.restaurantUpdate.city === 'Valencia') {
      cityId = 3;
    } else if (this.restaurantUpdate.city === 'Sevilla') {
      cityId = 4;
    } else if (this.restaurantUpdate.city === 'Zaragoza') {
      cityId = 5;
    } else {
      cityId = 6;
    }

    const datarestaurant = {
      'name': this.restaurantUpdate.name,
      'address': this.restaurantUpdate.address,
      'description': this.restaurantUpdate.description,
      'email': this.restaurantUpdate.email,
      'foodType': this.restaurantUpdate.foodtype,
      'menuPrice': 15,
      'breakfast': true,
      'lunch': true,
      'dinner': true,
      'roles': 'ROLE_RESTAURANT',
      'phone': this.restaurantUpdate.phone,
      'rate': 5,
      'password': this.restaurantUpdate.password,
      'users': [],
      'menus': [],
      'bookings': [],
      'city': { 'name': this.restaurantUpdate.city, id: cityId },
      'restaurantReviews': []
    };
    console.log(this.restaurantUpdate.description);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    this.http.put('https://localhost:8443/api/restaurants/', datarestaurant, options).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
  }

  fileChange(event) {
        const fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            const file: File = fileList[0];
            const formData: FormData = new FormData();
            console.log(file.name);
            formData.append('file', file, file.name);
            const headers = new Headers();
            headers.append('Accept', 'application/json');
            const options = new RequestOptions({ withCredentials: true });
            this.http.post('https://localhost:8443/api/restaurants/image/upload', formData, options)
                .subscribe(
                data => {
                    console.log(data);
                    this.router.navigate(['/private-restaurant/refresh']);
                },
                error => console.log(error)
                );
        }
    }

  acceptReservation(id: number) {
    console.log(id);
    const booking = {
      "id": this.bookingsInProcess[id].id,
      "date": this.bookingsInProcess[id].date,
      "number": this.bookingsInProcess[id].number,
      "specialRequirements": this.bookingsInProcess[id].specialRequirements,
      "state": 'Accepted'
    };
    const options = new RequestOptions({ withCredentials: true });
    this.http.put('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/book', booking, options).subscribe(
      response => {
        {
          this.bookingsAccepted = [];
    this.bookingsInProcess = [];
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/book').subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const book = data.content[i];
          if (book.state === 'In process') {
            this.bookingsInProcess.push(book);
          } else {
            this.bookingsAccepted.push(book);
          }
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/error/']);
      }
    );
    
  }
}
