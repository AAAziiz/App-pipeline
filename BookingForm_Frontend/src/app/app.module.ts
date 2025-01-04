import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent} from './login/login.component';
import { FlightComponent } from './flight/flight.component';
import { NgImageSliderModule } from 'ng-image-slider';
import { PayementComponent } from './payement/payement.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import { HotelListComponent } from './hotel-list/hotel-list.component';
import { CarListComponent } from './car-list/car-list.component';
import { BlogComponent } from './blog/blog.component';
import { TestComponent } from './test/test.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FlightComponent,
    PayementComponent,
    FlightListComponent,
    HotelListComponent,
    CarListComponent,
    BlogComponent,
    TestComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgImageSliderModule

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
