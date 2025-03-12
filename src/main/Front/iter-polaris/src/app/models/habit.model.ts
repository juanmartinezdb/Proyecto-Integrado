export interface HabitRequest {
  name: string;
  description: string;
  image: string;
  active: boolean;
  energy: number;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  frequency: 'DAILY' | 'WEEKLY' | 'MONTHLY';
  zoneId?: number;
  effectId?: number;
}

export interface HabitResponse {
  id: number;
  name: string;
  description: string;
  image: string;
  active: boolean;
  energy: number;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  frequency: 'DAILY' | 'WEEKLY' | 'MONTHLY';
  streak: number;
  totalCheck: number;
  zoneId?: number;
  userId: number;
  effectId?: number;
}
