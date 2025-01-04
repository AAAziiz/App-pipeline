import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from '../flight/model/flight';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  flights: Flight[] | null = null; 
  constructor(private service:UserService,private router:Router, private route: ActivatedRoute
    ){}
   ngOnInit(): void {
  this.route.queryParamMap.subscribe(queryParams => {
    const origin = queryParams.get('from');
    const destination = queryParams.get('to');

    console.log('orign:', origin);
    console.log('destination:', destination);

    if (origin && destination) {
      this.service.FilterFlightService(origin, destination).subscribe(
        data => {
          console.log('Filtered flights:', data);
          this.flights = data;

          
        },
        error => {
          console.error('Error fetching filtered flights:', error);
        }
      );
    } else {
      this.service.GetFlightService().subscribe(
        data => {
          console.log('All flights:', data);
          this.flights = data;
        },
        error => {
          console.error('Error fetching all flights:', error);
        }
      );
    }
  });
}


  
  
  
  bookFlight(id:number){
    this.router.navigate(['/pay', id]);

  }

}
