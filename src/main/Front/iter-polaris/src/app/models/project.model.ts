export interface ProjectRequest {
  name: string;
  description: string;
  startDate: string; // ISO Date format
  endDate?: string; // ISO Date format
  image: string;
  icon: string;
  color: string;
  status: 'ACTIVE' | 'COMPLETED' | 'ARCHIVED';
  priority: 'HIGH' | 'MEDIUM' | 'LOW';
  zoneId?: number;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  materialsIds: number[];
  habitsIds: number[];
}

export interface ProjectResponse {
  id: number;
  name: string;
  description: string;
  startDate: string; // ISO Date format
  endDate?: string; // ISO Date format
  createdAt: string; // ISO Date format
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  image: string;
  icon: string;
  color: string;
  status: string;
  priority: string;
  zoneId?: number;
  materialsIds: number[];
  habitsIds: number[];
  userId: number;
}
