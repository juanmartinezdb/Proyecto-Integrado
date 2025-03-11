
export interface NotificationResponse {
  id: number;
  message: string;
  type: string;
  read: boolean;
  createdAt: string;
}

export interface NotificationRequest {
  message: string;
  type: string;
}
