import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { CityComponent } from './city/city.component';
import { MainComponent } from './main/main.component';
import { routing } from './app.routing';
import { RouterModule } from '@angular/router';
import { SearchWebComponent } from './search-web/search-web.component';
import { ModalModule } from 'ngx-modal';
import { FooterComponent } from './footer/footer.component';
import { ErrorComponent } from './error/error.component';
import { PublicRestaurantComponent } from './public-restaurant/public-restaurant.component';
import { PublicClientComponent } from './public-client/public-client.component';
import { PrivateClientComponent } from './private-client/private-client.component';

import { PrivateRestaurantComponent } from './private-restaurant/private-restaurant.component';

import {LoginService } from './services/login.service';
import { HeaderComponent } from './header/header.component';


@NgModule({
  declarations: [
    AppComponent,
    CityComponent,
    MainComponent,
    SearchWebComponent,
    FooterComponent,
    ErrorComponent,
    PublicRestaurantComponent,
    PublicClientComponent,
    PrivateClientComponent,
    PrivateRestaurantComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    routing,
    ModalModule
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
