
export interface SkillRequest {
  name: string;
  description?: string;
  type?: string;
  level: number;
  cost?: number;
  mana?: number;
  icon?: string;
  effectId?: number;
}

export interface SkillResponse extends SkillRequest {
  id: number;
}
