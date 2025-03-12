import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HabitRequest, HabitResponse } from '../models/habit.model';

@Injectable({
  providedIn: 'root'
})
export class HabitService {
  http = inject(HttpClient);
  private habitsSubject = new BehaviorSubject<HabitResponse[]>([]);
  habits$ = this.habitsSubject.asObservable();
  url = 'http://localhost:3000/habits';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<HabitResponse[]>(this.url).subscribe({
      next: (data) => this.habitsSubject.next(data),
      error: (err) => console.error('Error loading habits', err)
    });
  }

  add(habit: HabitRequest) {
    this.http.post<HabitResponse>(this.url, habit).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding habit', err)
    });
  }

  update(id: number, habit: HabitRequest) {
    this.http.put(`${this.url}/${id}`, habit).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating habit with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting habit with ID ${id}`, err)
    });
  }

  complete(id: number) {
    this.http.post(`${this.url}/${id}/complete`, {}).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error completing habit with ID ${id}`, err)
    });
  }
}
