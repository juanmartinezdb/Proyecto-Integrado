import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserStatsResponse } from '../models/stats.model';

@Injectable({ providedIn: 'root' })
export class StatsService {
  private baseUrl = 'http://localhost:8080/stats';

  constructor(private http: HttpClient) {}

  getUserStats(): Observable<UserStatsResponse> {
    return this.http.get<UserStatsResponse>(`${this.baseUrl}`);
  }
}
