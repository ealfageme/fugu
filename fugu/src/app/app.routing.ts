import { PublicClientComponent } from './public-client/public-client.component';
import { PrivateClientComponent } from './private-client/private-client.component';
import { SearchWebComponent } from './search-web/search-web.component';
import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './city/city.component';
import { MainComponent } from './main/main.component';
import { ErrorComponent} from './error/error.component';
import { PublicRestaurantComponent} from './public-restaurant/public-restaurant.component';

const appRoutes = [
  { path: 'new/city/:name', component: CityComponent,  },
  { path: 'new/public-client', component: PublicClientComponent},
  { path: 'new/private-client', component: PrivateClientComponent},
  { path: 'new/main', component: MainComponent,  },
  { path: 'new/search-web', component: SearchWebComponent, },
  { path: 'new/public-restaurant', component: PublicRestaurantComponent, },
  { path: '', redirectTo: 'new/main', pathMatch: 'full' },
  { path: '**', component: ErrorComponent }
];
export const routing = RouterModule.forRoot(appRoutes);
