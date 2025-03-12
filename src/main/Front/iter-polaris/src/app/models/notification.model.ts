export interface NotificationRequest {
  message: string;
  type: 'reminder' | 'achievement' | 'alert';
}

export interface NotificationResponse {
  id: number;
  message: string;
  type: string;
  read: boolean;
  createdAt: string; // ISO Date format
}
