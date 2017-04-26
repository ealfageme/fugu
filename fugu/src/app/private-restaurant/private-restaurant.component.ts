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
  private menus: string[] = [];
  private bookingsInProcess: Booking[] = [];
  private bookingsAccepted: string[] = [];
  restaurant: string;
  private user: string;
  nameDish: string;
  private descriptionDish: string;
  private priceDish: number;


  constructor(private http: Http, private loginService: LoginService, private router: Router) {
    this.inSession = false;
    this.nameDish = " ";
    this.descriptionDish = " ";
    this.priceDish = 0;
    this.followButton = false;
    this.unfollowButton = true;
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name).subscribe(
      response => {
        const  data = response.json();
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
        console.log(this.restaurantUpdate);
      },
      error => {
        console.error(error);
        this.router.navigate(['/new/error/']);
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
        this.router.navigate(['/new/error/']);
      }
    );
    this.http.get('https://localhost:8443/api/restaurants/' + this.loginService.user.name + '/voucher').subscribe(
      response => {
        const data = response.json();
        for (let i = 0; i < data.content.length; i++) {
          const voucher = data.content[i];
          this.vouchers.push(voucher);
        }
      },
      error => {
        console.error(error);
        this.router.navigate(['/new/error/']);
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
        this.router.navigate(['/new/error/']);
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
        this.router.navigate(['/new/error/']);
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
              this.router.navigate(['/new/error/']);
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
  console.log("subiendo")
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
    console.log(datarestaurant);
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
            formData.append('file', file, file.name);
            const headers = new Headers();
            headers.append('Accept', 'application/json');
            const options = new RequestOptions({ withCredentials: true });
            this.http.post('https://localhost:8443/api/restaurants/image/upload', formData, options)
                .subscribe(
                data => {
                  this.router.navigate(['/new/private-restaurant/refresh']);
                  console.log(data);
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
        console.log(response);
      },
      error => {
        console.error(error);
        this.router.navigate(['/new/error/']);
      }
    );
    }
}
