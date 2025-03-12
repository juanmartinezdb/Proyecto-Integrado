import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { NotificationResponse } from '../models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  http = inject(HttpClient);
  private notificationsSubject = new BehaviorSubject<NotificationResponse[]>([]);
  notifications$ = this.notificationsSubject.asObservable();
  url = 'http://localhost:3000/notifications';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<NotificationResponse[]>(this.url).subscribe({
      next: (data) => this.notificationsSubject.next(data),
      error: (err) => console.error('Error loading notifications', err)
    });
  }

  markAsRead(id: number) {
    this.http.put(`${this.url}/${id}/read`, {}).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error marking notification with ID ${id} as read`, err)
    });
  }
}
