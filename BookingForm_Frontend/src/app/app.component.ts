import { Component } from '@angular/core';
import { User } from './flight/model/user';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front';
 
  constructor( private router:Router){
    this.router.navigate(['/login']);

  }
  
}
