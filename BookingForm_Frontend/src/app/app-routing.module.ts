import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FlightComponent } from './flight/flight.component';
import { PayementComponent } from './payement/payement.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import { HotelListComponent } from './hotel-list/hotel-list.component';
import { CarListComponent } from './car-list/car-list.component';
import { BlogComponent } from './blog/blog.component';
import { TestComponent } from './test/test.component';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'fly',component:FlightComponent},
  {path:'pay/:id',component:PayementComponent},
  {path:'flist',component:FlightListComponent},
  {path:'hlist',component:HotelListComponent},
  {path:'clist',component:CarListComponent},
  {path:'blog',component:BlogComponent},













];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
