import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../flight/model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = 'front';
  msg="";
  user=new User();
  constructor(private service:UserService, private router :Router ,private renderer: Renderer2,private el: ElementRef){}
  ngOnInit(): void {
    this.setupCardScroll();
  }
  togglePanel(isSignUp: boolean) {
    const container = document.getElementById('container');
    if (container) {
      if (isSignUp) {
        container.classList.add('right-panel-active');
      } else {
        container.classList.remove('right-panel-active');
      }
    }
  }

  RegisterUser(){
    this.service.CreateService(this.user).subscribe(
      data=>{
        console.log(this.user);
        this.msg="Registration successfully";
        this.user=new User();
        alert(this.msg);
        this.router.navigate(['/test']);
      },error=>{
        console.log("Registration failed"),
        this.msg=error.error;
        this.user=new User(); 
      }
    )
  
  }
    LoginUser(){
      this.service.LoginService(this.user).subscribe(
      (Response)=>{
        console.log(Response);
        const jwt = Response.jwt; 
        localStorage.setItem('JWT', jwt); 
          console.log(this.user);
          this.msg="Login successfully";
          this.user=new User();
          alert(this.msg)
          this.router.navigate(['/fly']);
        },
        error=>{
          console.log("Login failed");
          if (error.status === 401) {
            this.msg = "Incorrect email or password"; 
          } else {
            this.msg = "Incorrect email or password"; 
          }
          this.user=new User(); 
        }
      )
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
    

    
  

}
