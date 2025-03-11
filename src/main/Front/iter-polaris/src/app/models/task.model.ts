
export interface TaskRequest {
  name: string;
  description?: string;
  image?: string;
  status: string;
  energy?: number;
  points?: number;
  challengeLevel: string;
  priority?: string;
  cycle?: string;
  startDate?: string;
  endDate?: string;
  active?: boolean;
  projectId?: number;
  parentTaskId?: number;
}

export interface TaskResponse extends TaskRequest {
  id: number;
  userId: number;
  subTasksIds?: Set<number>;
}
