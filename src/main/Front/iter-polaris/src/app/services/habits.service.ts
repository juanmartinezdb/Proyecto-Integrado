import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HabitRequest, HabitResponse } from '../models/habit.model';

@Injectable({ providedIn: 'root' })
export class HabitService {
  private baseUrl = 'http://localhost:8080/habits';

  constructor(private http: HttpClient) {}

  getHabits(): Observable<HabitResponse[]> {
    return this.http.get<HabitResponse[]>(`${this.baseUrl}`);
  }

  getHabitById(id: number): Observable<HabitResponse> {
    return this.http.get<HabitResponse>(`${this.baseUrl}/${id}`);
  }

  createHabit(habit: HabitRequest): Observable<HabitResponse> {
    return this.http.post<HabitResponse>(`${this.baseUrl}`, habit);
  }

  updateHabit(id: number, habit: HabitRequest): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}`, habit);
  }

  deleteHabit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
