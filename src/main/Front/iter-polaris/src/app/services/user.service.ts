import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private baseUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) {}

  getUserProfile(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/profile`);
  }

  updateUser(userId: number, user: Partial<UserDTO>): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/update/${userId}`, user);
  }

  getCalendarSettings(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/calendar-settings`);
  }

  updateCalendarSettings(settings: any): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/calendar-settings`, settings);
  }
  getEnergyBalance(): Observable<{ energy: number; maxEnergy: number }> {
    return this.http.get<{ energy: number; maxEnergy: number }>(`${this.baseUrl}/energy-balance`);
  }

}
