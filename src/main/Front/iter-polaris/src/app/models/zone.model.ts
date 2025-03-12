export interface ZoneRequest {
  name: string;
  description: string;
  image: string;
  color: string;
  zoneTypes: string[];
}

export interface ZoneResponse {
  id: number;
  name: string;
  description: string;
  image: string;
  color: string;
  energy: number;
  xp: number;
  level: number;
  zoneTypes: string[];
}
