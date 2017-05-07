import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { SigninService } from '../services/signin.service';
import { Http } from '@angular/http';

interface Restaurant {
    restaurantname: string;
    address?: string;
    city?: string;
    foodtype?: string;
    phone?: number;
    description?: string;
    email: string;
    password: string;
    confirmPassword: string;
}
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
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  public user: User;
  public restaurant: Restaurant;
  loginService: LoginService;

  ngOnInit() {
    this.user = {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
    this.restaurant = {
      restaurantname: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  }

  constructor(private router: Router
    , private Signinservice: SigninService, private http: Http, loginServiceaux: LoginService) {
    this.loginService = loginServiceaux;
   }


  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logInRestaurant(user, pass).subscribe(
      u => console.log(u),
      error => console.log('Invalid restaurant or password')
    );

    this.loginService.logInUser(user, pass).subscribe(
      u => console.log(u),
      error => console.log('Invalid user or password')
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to log out: ' + error)
    );
    this.router.navigate(['/main']);
  }
  visitProfile() {
    if (this.loginService.user.roles.indexOf('ROLE_RESTAURANT') !== -1) {
      this.router.navigate(['/private-restaurant/']);
    } else {
      this.router.navigate(['/private-client/']);
    }
  }

  sendFormUser(){
    //CLIENT
    console.log(this.user.username);
    console.log(this.user.email);
    console.log(this.user.age);
    console.log(this.user.favouritefood);
    console.log(this.user.description);
    console.log(this.user.password);
    console.log(this.user.confirmPassword);

    let dataclient = {"name": this.user.username, 
                      "password": this.user.password,
                      "email": this.user.email,
                      "age": this.user.age,
                      "description": this.user.description,
                      "favouriteFood": this.user.favouritefood,
                      "roles": "ROLE_USER"          
                    }
    this.http.post('https://localhost:8443/api/clients/signin', dataclient).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
  }

  sendFormRestaurant(){
    //RESTAURANT
    console.log(this.restaurant.restaurantname);
    console.log(this.restaurant.address);
    console.log(this.restaurant.city);
    console.log(this.restaurant.foodtype);
    console.log(this.restaurant.phone);
    console.log(this.restaurant.description);
    console.log(this.restaurant.email);
    console.log(this.restaurant.password);
    console.log(this.restaurant.confirmPassword);
    let cityId = 0;
    if(this.restaurant.city==="Madrid"){
      cityId=1
    }else if (this.restaurant.city==="Barcelona"){
      cityId=2
    }else if (this.restaurant.city==="Valencia"){
      cityId=3
    }else if (this.restaurant.city==="Sevilla"){
      cityId=4
    }else if (this.restaurant.city==="Zaragoza"){
      cityId=5
    }else{
      cityId=6
    }

    let datarestaurant = {"name": this.restaurant.restaurantname, 
                          "address": this.restaurant.address,
                          "description": this.restaurant.description,
                          "email": this.restaurant.email,
                          "foodType": this.restaurant.foodtype,
                          "menuPrice": 15,
                          "breakfast": true,
                          "lunch": true,
                          "dinner": true,
                          "roles": "ROLE_RESTAURANT",
                          "phone": this.restaurant.phone,
                          "rate": 5,
                          "password": this.restaurant.password,    
                          "users": [],
                          "menus": [],
                          "bookings": [],
                          "city": {"name": this.restaurant.city, "id": cityId},
                          "restaurantReviews": []              
                        }
    this.http.post('https://localhost:8443/api/restaurants/signin', datarestaurant).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
  }

}
