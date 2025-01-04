import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { Flight } from '../flight/model/flight';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { Card } from '../flight/model/card';
import { Car } from '../flight/model/car';
import { Hotel } from '../flight/model/hotel';

@Component({
  selector: 'app-payement',
  templateUrl: './payement.component.html',
  styleUrls: ['./payement.component.css']
})
export class PayementComponent implements OnInit {
  id!: number;
  flight!: Flight;
  car!:Car;
  hotel!:Hotel;
  msg="";
  card=new Card();

  constructor( private renderer: Renderer2,private el: ElementRef,private route: ActivatedRoute,private service:UserService){}

  ngOnInit(): void {
    this.setupCardScroll();

    this.flight= new Flight();
    this.id = this.route.snapshot.params['id'];
    this.service.getFlightById(this.id)
      .subscribe(data => {
        console.log(data);
        this.flight=data;
        this.card.id=this.flight.id;
      }, error => console.log(error));


      this.hotel= new Hotel();
      this.id = this.route.snapshot.params['id'];
      this.service.getHotelsById(this.id)
        .subscribe(data => {
          console.log(data);
          this.hotel=data;
          this.card.id=this.hotel.id;
        }, error => console.log(error));

        
        this.car= new Car();
        this.id = this.route.snapshot.params['id'];
        this.service.getCarsById(this.id)
          .subscribe(data => {
            console.log(data);
            this.car=data;
            this.card.id=this.car.id;
          }, error => console.log(error));


  }
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

  RegisterCard(){
    this.service.RegisterCardService(this.card).subscribe(
      data=>{
        console.log(this.card);
        this.msg="Register card information has done successfully";
        this.card=new Card();
        ;
        alert(this.msg);
      },error=>{
        console.log("Registration failed"),
        this.msg=error.error;
        this.card=new Card();
        ; 
      })}

}
