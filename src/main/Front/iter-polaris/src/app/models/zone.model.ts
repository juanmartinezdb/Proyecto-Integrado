
export interface ZoneRequest {
  name: string;
  description?: string;
  image?: string;
  color?: string;
  zoneTypes?: Set<string>;
}

export interface ZoneResponse {
  id: number;
  name: string;
  description?: string;
  image?: string;
  color?: string;
  energy?: number;
  xp?: number;
  level?: number;
  zoneTypes?: Set<string>;
}
