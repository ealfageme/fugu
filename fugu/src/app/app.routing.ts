import { SearchWebComponent } from './search-web/search-web.component';
import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './city/city.component'; 
 import { MainComponent } from './main/main.component';


const appRoutes = [
  { path: 'new/city/:name', component: CityComponent,  },
  { path: 'new/main', component: MainComponent,  },
  { path: 'new/search-web', component: SearchWebComponent, },
  { path: '', redirectTo: 'new/main', pathMatch: 'full' }
  
];
export const routing = RouterModule.forRoot(appRoutes);
