import { Component, inject, OnInit } from '@angular/core';
import { NotificationResponse } from '../../../../models/notification.model';
import { NotificationService } from '../../../../services/notification.service';
// import { NotificationService } from '../../../services/notification.service';
// import { NotificationResponse } from '../../../models/notification.model';

@Component({
  selector: 'app-notifications',
  imports: [],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent implements OnInit {
  notifications: NotificationResponse[] = [];
  notificationService = inject(NotificationService);

  ngOnInit(): void {
    this.notificationService.getUserNotifications().subscribe((data: NotificationResponse[]) => {
      this.notifications = data;
    });
  }

  markAsRead(notificationId: number) {
    this.notificationService.markNotificationAsRead(notificationId).subscribe(() => {
      this.notifications = this.notifications.filter(n => n.id !== notificationId);
    });
  }
}
