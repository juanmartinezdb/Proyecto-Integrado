
export interface MaterialResponse {
  id: number;
  name: string;
  description: string;
  quantity: number;
}

export interface MaterialRequest {
  name: string;
  description: string;
  quantity: number;
}
