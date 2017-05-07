import { PublicClientComponent } from './public-client/public-client.component';
import { PrivateClientComponent } from './private-client/private-client.component';
import { SearchWebComponent } from './search-web/search-web.component';
import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './city/city.component';
import { MainComponent } from './main/main.component';
import { ErrorComponent} from './error/error.component';
import { PublicRestaurantComponent} from './public-restaurant/public-restaurant.component';
import { PrivateRestaurantComponent} from './private-restaurant/private-restaurant.component';

const appRoutes = [
  { path: 'city/:name', component: CityComponent,  },
  { path: 'public-client/:username', component: PublicClientComponent},
  { path: 'private-client', component: PrivateClientComponent},
  { path: 'private-client/refresh', component: PrivateClientComponent},
  { path: 'main', component: MainComponent,  },
  { path: 'search-web', component: SearchWebComponent, },
  { path: 'public-restaurant/:name', component: PublicRestaurantComponent, },
  { path: 'private-restaurant', component:  PrivateRestaurantComponent, },
  { path: 'private-restaurant/refresh', component:  PrivateRestaurantComponent, },
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: '**', component: ErrorComponent }
];
export const routing = RouterModule.forRoot(appRoutes);
