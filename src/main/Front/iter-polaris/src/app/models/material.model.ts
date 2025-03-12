export interface MaterialRequest {
  name: string;
  type: 'documento' | 'link' | 'video' | string;
  url: string;
  description: string;
}

export interface MaterialResponse {
  id: number;
  name: string;
  type: string;
  url: string;
  description: string;
  userId: number;
}
