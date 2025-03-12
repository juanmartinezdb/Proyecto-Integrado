export interface TemplateRequest {
  name: string;
  description: string;
  energy: number;
  points: number;
  priority: 'HIGH' | 'MEDIUM' | 'LOW';
  cycle: 'NONE' | 'DAILY' | 'WEEKLY' | 'MONTHLY';
  category: 'task' | 'habit' | 'project';
}

export interface TemplateResponse {
  id: number;
  name: string;
  description: string;
  energy: number;
  points: number;
  priority: string;
  cycle: string;
  category: string;
}
