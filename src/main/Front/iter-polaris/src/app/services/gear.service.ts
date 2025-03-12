import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { GearRequest, GearResponse } from '../models/gear.model';

@Injectable({
  providedIn: 'root'
})
export class GearService {
  http = inject(HttpClient);
  private gearsSubject = new BehaviorSubject<GearResponse[]>([]);
  gears$ = this.gearsSubject.asObservable();
  url = 'http://localhost:3000/gears';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<GearResponse[]>(this.url).subscribe({
      next: (data) => this.gearsSubject.next(data),
      error: (err) => console.error('Error loading gears', err)
    });
  }

  add(gear: GearRequest) {
    this.http.post<GearResponse>(this.url, gear).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding gear', err)
    });
  }

  update(id: number, gear: GearRequest) {
    this.http.put(`${this.url}/${id}`, gear).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating gear with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting gear with ID ${id}`, err)
    });
  }
}
