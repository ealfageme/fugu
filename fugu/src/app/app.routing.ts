import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './city/city.component';
import { MainComponent } from './main/main.component';
const appRoutes = [
  { path: 'city', component: CityComponent,  },
  { path: 'main', component: MainComponent,  },
  { path: '', redirectTo: 'main', pathMatch: 'full' }
];
export const routing = RouterModule.forRoot(appRoutes);
