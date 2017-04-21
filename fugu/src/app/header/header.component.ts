import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {

  constructor(private loginService: LoginService, private router: Router) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logInUser(user, pass).subscribe(
      u => console.log(u),
      error => console.log('Invalid user or password')
    );
    this.loginService.logInRestaurant(user, pass).subscribe(
      u => console.log(u),
      error => console.log('Invalid restaurant or password')
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to log out: ' + error)
    );
  }
  visitProfile() {
    if (this.loginService.user.roles.indexOf('ROLE_RESTAURANT') !== -1) {
      this.router.navigate(['/new/private-restaurant/']);
    } else {
      this.router.navigate(['/new/private-client/']);
    }
  }

}
