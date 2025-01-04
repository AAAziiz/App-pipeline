import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../user.service';
import { Car } from '../flight/model/car';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars: Car[] | null = null; // Update the type here
  constructor(private service:UserService,private router:Router, private route: ActivatedRoute
    ){}
   ngOnInit(): void {
  this.route.queryParamMap.subscribe(queryParams => {
    const thetype = queryParams.get('ctype');

    console.log('car type is:', thetype);

    if (thetype) {
      this.service.FilterCarService(thetype).subscribe(
        data => {
          console.log('Filtered cars:', data);
          this.cars = data;

          
        },
        error => {
          console.error('Error fetching filtered flights:', error);
        }
      );
    } else {
      this.service.GetCARService().subscribe(
        data => {
          console.log('All cars:', data);
          this.cars = data;
        },
        error => {
          console.error('Error fetching all cars:', error);
        }
      );
    }
  });
}

  bookCar(id:number){
    this.router.navigate(['/pay', id]);

  }


}
