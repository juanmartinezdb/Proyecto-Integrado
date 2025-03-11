
export interface ProjectRequest {
  name: string;
  description?: string;
  startDate?: string;
  endDate?: string;
  image?: string;
  icon?: string;
  color?: string;
  status?: string;
  priority?: string;
  zoneId?: number;
  points?: number;
  challengeLevel?: string;
  materialsIds?: Set<number>;
  habitsIds?: Set<number>;
}

export interface ProjectResponse extends ProjectRequest {
  id: number;
  createdAt: string;
  userId: number;
}
