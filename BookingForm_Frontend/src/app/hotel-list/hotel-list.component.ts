import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Hotel } from '../flight/model/hotel';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  hotels: Hotel[] | null = null; // Update the type here
  constructor(private service:UserService,private router:Router, private route: ActivatedRoute
    ){}
   ngOnInit(): void {
  this.route.queryParamMap.subscribe(queryParams => {
    const dest = queryParams.get('destination');

    console.log('destination is :', dest);

    if (dest) {
      this.service.FilterHotelService(dest).subscribe(
        data => {
          console.log('Filtered hotels:', data);
          this.hotels = data;

          
        },
        error => {
          console.error('Error fetching filtered hotels:', error);
        }
      );
    } else {
      this.service.GetHotelService().subscribe(
        data => {
          console.log('All hotels:', data);
          this.hotels = data;
        },
        error => {
          console.error('Error fetching all hotels:', error);
        }
      );
    }
  });
}




  bookHotel(id:number){
    this.router.navigate(['pay', id]);

  }

}
