import { Car } from './model/car';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { Flight } from './model/flight';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Hotel } from './model/hotel';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  constructor(private renderer: Renderer2, private el: ElementRef,private service:UserService,private router:Router) {}

    ngOnInit() {
      this.setupCardScroll();
       const flightsButton = this.el.nativeElement.querySelector('#flightsButton');
        const carButton = this.el.nativeElement.querySelector('#carButton');
        const hotelButton = this.el.nativeElement.querySelector('#hotelButton');





        const flightForm = this.el.nativeElement.querySelector('#flightForm');
        const carRentalForm = this.el.nativeElement.querySelector('#carRentalForm');
        const hotelRentalForm = this.el.nativeElement.querySelector('#hotelRentalForm');

       

        
        flightsButton.style.backgroundColor = '#1F2746'; // Set initial state
        carButton.style.backgroundColor = '#22a9bf';
        hotelButton.style.backgroundColor = '#22a9bf'; // Set initial state


        flightForm.classList.add('active');
        carRentalForm.classList.remove('active');
        hotelRentalForm.classList.remove('active');


       flightsButton.addEventListener('click', function() {
        flightsButton.style.backgroundColor = '#1F2746';
        carButton.style.backgroundColor = '#22a9bf';
        hotelButton.style.backgroundColor = '#22a9bf';
        flightForm.classList.add('active');
        carRentalForm.classList.remove('active');
        hotelRentalForm.classList.remove('active');

    });

     hotelButton.addEventListener('click', function() {
      hotelButton.style.backgroundColor = '#1F2746';
     flightsButton.style.backgroundColor = '#22a9bf';
      carButton.style.backgroundColor = '#22a9bf';
      hotelRentalForm.classList.add('active');
      flightForm.classList.remove('active');
      carRentalForm.classList.remove('active');
  });

        carButton.addEventListener('click', function() {
        carButton.style.backgroundColor = '#1F2746';
        flightsButton.style.backgroundColor = '#22a9bf';
        hotelButton.style.backgroundColor = '#22a9bf';
        carRentalForm.classList.add('active');
        flightForm.classList.remove('active');
        hotelRentalForm.classList.remove('active');

    });
};

private setupCardScroll(): void {
  const cardWrapper = this.el.nativeElement.querySelector('.card-wrapper');
  const widthToScroll = cardWrapper.children[0].offsetWidth;
  const arrowPrev = this.el.nativeElement.querySelector('.arrow.prev');
  const arrowNext = this.el.nativeElement.querySelector('.arrow.next');
  const cardBounding = cardWrapper.getBoundingClientRect();
  const cardImageAndLink = cardWrapper.querySelectorAll('img, a');
  let currScroll = 0;
  let initPos = 0;
  let clicked = false;

  cardImageAndLink.forEach((item: Element) => {
    this.renderer.setAttribute(item, 'draggable', 'false');
  });

  arrowPrev.onclick = () => {
    cardWrapper.scrollLeft -= widthToScroll;
  };

  arrowNext.onclick = () => {
    cardWrapper.scrollLeft += widthToScroll;
  };

  cardWrapper.onmousedown = function(e: MouseEvent) {
    cardWrapper.classList.add('grab');
    initPos = e.clientX - cardBounding.left;
    currScroll = cardWrapper.scrollLeft;
    clicked = true;
  };

  cardWrapper.onmousedown = function(e: MouseEvent) {
    if (clicked) {
      const xPos = e.clientX - cardBounding.left;
      cardWrapper.scrollLeft = currScroll + -(xPos - initPos);
    }
  };

  const mouseUpAndLeave = () => {
    cardWrapper.classList.remove('grab');
    clicked = false;
  };

  cardWrapper.onmouseup = mouseUpAndLeave;
  cardWrapper.onmouseleave = mouseUpAndLeave;
}




flight=new Flight();
registerFlight() {
  this.service.FlightService(this.flight).subscribe(
    data => {
      console.log(this.flight);
      this.router.navigate(['/flist'], {
        queryParams: {
          from: this.flight.fromcity,
          to: this.flight.tocity,
        },
      });
      this.flight = new Flight();

    },
    error => {
      console.log("Registration of flight failed");
      this.flight = new Flight();
    }
  );
}


hotel=new Hotel();
registerHotel(){
  this.service.HotelService(this.hotel).subscribe(
    data=>{
      console.log(this.hotel);
      this.router.navigate(['/hlist'], {
        queryParams: {
          destination: this.hotel.destination
        },
      });
      this.flight = new Flight();

    },error=>{
      console.log("Registration of hotel failed"),
      this.hotel=new Hotel();
    } ) 


}
car=new Car();
registerCar(){
  this.service.CARService(this.car).subscribe(
    data => {
      console.log(this.flight);
      this.router.navigate(['/clist'], {
        queryParams: {
          ctype: this.car.cartype,
        },
      });
      this.car = new Car();

    
    },error=>{
      console.log("Registration of car failed"),
      this.car=new Car();
    } ) 

}
}






    


