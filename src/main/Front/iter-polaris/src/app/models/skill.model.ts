export interface SkillRequest {
  name: string;
  description: string;
  type: 'mental' | 'physical' | 'emotional' | 'social' | 'creative' | 'all';
  level: number;
  cost: number;
  mana: number;
  icon: string;
  effectId: number;
}

export interface SkillResponse {
  id: number;
  name: string;
  description: string;
  type: string;
  level: number;
  cost: number;
  mana: number;
  icon: string;
  effectId: number;
}
