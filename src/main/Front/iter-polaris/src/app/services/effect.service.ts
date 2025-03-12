import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { EffectRequest, EffectResponse } from '../models/effect.model';

@Injectable({
  providedIn: 'root'
})
export class EffectService {
  http = inject(HttpClient);
  private effectsSubject = new BehaviorSubject<EffectResponse[]>([]);
  effects$ = this.effectsSubject.asObservable();
  url = 'http://localhost:3000/effects';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<EffectResponse[]>(this.url).subscribe({
      next: (data) => this.effectsSubject.next(data),
      error: (err) => console.error('Error loading effects', err)
    });
  }

  add(effect: EffectRequest) {
    this.http.post<EffectResponse>(this.url, effect).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding effect', err)
    });
  }

  update(id: number, effect: EffectRequest) {
    this.http.put(`${this.url}/${id}`, effect).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating effect with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting effect with ID ${id}`, err)
    });
  }
}
