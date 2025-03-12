export interface AchievementRequest {
  name: string;
  description: string;
  type: string;
  threshold: number;
}

export interface AchievementResponse {
  id: number;
  name: string;
  description: string;
  unlocked: boolean;
}
