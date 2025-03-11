import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NotificationResponse } from '../models/notification.model';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private baseUrl = 'http://localhost:8080/notifications';

  constructor(private http: HttpClient) {}

  getUserNotifications(): Observable<NotificationResponse[]> {
    return this.http.get<NotificationResponse[]>(`${this.baseUrl}`);
  }

  markNotificationAsRead(notificationId: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/mark-as-read/${notificationId}`, {});
  }
}
