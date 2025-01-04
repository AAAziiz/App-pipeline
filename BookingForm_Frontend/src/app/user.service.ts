import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './flight/model/user';
import { Observable } from 'rxjs';
import { Flight } from './flight/model/flight';
import { Hotel } from './flight/model/hotel';
import { Car } from './flight/model/car';
import { Card } from './flight/model/card';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  accountValue: any;

  constructor(private http:HttpClient) { }

  public CreateService(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/registeruser", user);

  }
  public LoginService(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/authentication", user);

  }
  public FlightService(flight:Flight):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api2/registerflight", flight,{
      headers:this.crateAuthorizationHeader()
       
    });
  }
  public GetFlightService():Observable<any>{
    return this.http.get<any>("http://localhost:8090/api2/getallflights",{
      headers:this.crateAuthorizationHeader()
       
    });
  }
  public FilterFlightService(from: string, to: string): Observable<any> {
  const data = { fromwhere: from, towhere: to };
  return this.http.post<string>("http://localhost:8090/api2/filterflights", data, {
    headers: this.crateAuthorizationHeader()
  });
}
 public getFlightById(id: number): Observable<any> {
  return this.http.get('http://localhost:8090/api2/flights/' + id,{
    headers:this.crateAuthorizationHeader()
     
  });
}

  public HotelService(hotel:Hotel):Observable<any>{
      return this.http.post<any>("http://localhost:8090/api2/registerhotel", hotel,{
        headers:this.crateAuthorizationHeader()

      });}
      public GetHotelService():Observable<any>{
        return this.http.get<any>("http://localhost:8090/api2/getallhotels",{
          headers:this.crateAuthorizationHeader()
  
        });}

        public FilterHotelService(dest: string): Observable<any> {
          const data = { destination: dest};
          return this.http.post<string>("http://localhost:8090/api2/filterhotels", data, {
            headers: this.crateAuthorizationHeader()
          });
        }    

        public getHotelsById(id: number): Observable<any> {
          return this.http.get('http://localhost:8090/api2/hotels/' + id,{
            headers:this.crateAuthorizationHeader()
             
          });
        }

  public CARService(car:Car):Observable<any>{
       return this.http.post<any>("http://localhost:8090/api2/registercar", car,{
        headers:this.crateAuthorizationHeader()

       });} 
       public GetCARService():Observable<any>{
        return this.http.get<any>("http://localhost:8090/api2/getallcars",{
         headers:this.crateAuthorizationHeader()
 
        });}    



        public FilterCarService(typecar: string): Observable<any> {
          const data = { type: typecar};
          return this.http.post<string>("http://localhost:8090/api2/filtercars", data, {
            headers: this.crateAuthorizationHeader()
          });
        } 
        public getCarsById(id: number): Observable<any> {
          return this.http.get('http://localhost:8090/api2/cars/' + id,{
            headers:this.crateAuthorizationHeader()
             
          });
        }
        
        public RegisterCardService(card:Card):Observable<any>{
          return this.http.post<any>("http://localhost:8090/api2/registercard",card,{
            headers:this.crateAuthorizationHeader()
    
          });}



       private crateAuthorizationHeader():HttpHeaders{
        const jwt=localStorage.getItem('JWT');
        if(jwt){
          return new HttpHeaders().set(
            'Authorization','Bearer '+jwt
          )
        }else{
          console.log("JWT not found in local storage");
        }
        return new HttpHeaders();
       }


}
