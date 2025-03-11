
export interface HabitRequest {
  name: string;
  description?: string;
  image?: string;
  active?: boolean;
  energy?: number;
  points?: number;
  challengeLevel?: string;
  frequency?: string;
  zoneId?: number;
  effectId?: number;
}

export interface HabitResponse extends HabitRequest {
  id: number;
  userId: number;
  streak?: number;
  totalCheck?: number;
}
