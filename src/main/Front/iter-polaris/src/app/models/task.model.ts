export interface TaskRequest {
  name: string;
  description: string;
  image: string;
  status: 'PENDING' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED';
  energy: number;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  priority: 'HIGH' | 'MEDIUM' | 'LOW';
  cycle: 'NONE' | 'DAILY' | 'WEEKLY' | 'MONTHLY';
  startDate: string; // ISO Date format
  endDate?: string; // ISO Date format
  active: boolean;
  projectId?: number;
  parentTaskId?: number;
}

export interface TaskResponse {
  id: number;
  name: string;
  description: string;
  image: string;
  status: string;
  energy: number;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  priority: string;
  cycle: string;
  startDate: string; // ISO Date format
  endDate?: string; // ISO Date format
  active: boolean;
  userId: number;
  projectId?: number;
  parentTaskId?: number;
  subTasksIds?: number[];
}
