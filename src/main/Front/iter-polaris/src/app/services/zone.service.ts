import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ZoneRequest, ZoneResponse } from '../models/zone.model';

@Injectable({
  providedIn: 'root'
})
export class ZoneService {
  http = inject(HttpClient);
  private zonesSubject = new BehaviorSubject<ZoneResponse[]>([]);
  zones$ = this.zonesSubject.asObservable();
  url = 'http://localhost:3000/zones';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<ZoneResponse[]>(this.url).subscribe({
      next: (data) => this.zonesSubject.next(data),
      error: (err) => console.error('Error loading zones', err)
    });
  }

  add(zone: ZoneRequest) {
    this.http.post<ZoneResponse>(this.url, zone).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding zone', err)
    });
  }

  update(id: number, zone: ZoneRequest) {
    this.http.put(`${this.url}/${id}`, zone).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating zone with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting zone with ID ${id}`, err)
    });
  }
}
