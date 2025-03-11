import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AchievementResponse } from '../models/gamification.model';

@Injectable({ providedIn: 'root' })
export class AchievementService {
  private baseUrl = 'http://localhost:8080/achievements';

  constructor(private http: HttpClient) {}

  getAchievements(): Observable<AchievementResponse[]> {
    return this.http.get<AchievementResponse[]>(`${this.baseUrl}`);
  }
}
