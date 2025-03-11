
export interface TemplateRequest {
  name: string;
  description?: string;
  energy?: number;
  points?: number;
  priority?: string;
  cycle?: string;
  category: string;
}

export interface TemplateResponse extends TemplateRequest {
  id: number;
}
