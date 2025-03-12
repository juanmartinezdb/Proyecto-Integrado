import { Component } from '@angular/core';
import { NotificationService } from '../../../services/notification.service';
import { NotificationResponse } from '../../../models/notification.model';

@Component({
  selector: 'app-notifications',
  imports: [],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {
  notifications: NotificationResponse[] = [];

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.notificationService.getUserNotifications().subscribe((data) => {
      this.notifications = data;
    });
  }

  markAsRead(notificationId: number) {
    this.notificationService.markNotificationAsRead(notificationId).subscribe(() => {
      this.notifications = this.notifications.filter(n => n.id !== notificationId);
    });
  }
}
