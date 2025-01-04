// flight-filter.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FlightFilterService {
  private origin = new BehaviorSubject<string>('');
  private destination = new BehaviorSubject<string>('');

  origin$ = this.origin.asObservable();
  destination$ = this.destination.asObservable();

  setOrigin(origin: string) {
    this.origin.next(origin);
  }

  setDestination(destination: string) {
    this.destination.next(destination);
  }
}
