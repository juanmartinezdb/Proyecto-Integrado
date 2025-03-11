export interface AchievementResponse {
  id: number;
  name: string;
  description?: string;
  unlocked: boolean;
}

export interface SkillResponse {
  id: number;
  name: string;
  description?: string;
  acquired: boolean;
  levelRequired: number;
  cost: number;
  mana: number;
  icon?: string;
  effectId?: number;
}

export interface UnlockSkillRequest {
  skillId: number;
}
