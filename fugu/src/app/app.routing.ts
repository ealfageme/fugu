import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './city/city.component';
const appRoutes = [
  { path: 'city', component: CityComponent ,  },
  { path: '', redirectTo: 'books', pathMatch: 'full' }
];
export const routing = RouterModule.forRoot(appRoutes);
