export interface GearRequest {
  name: string;
  description: string;
  image: string;
  type: 'mental' | 'physical' | 'emotional' | 'social' | 'creative' | 'all';
  maxUses: number;
  cost: number;
  consumable: boolean;
  rarity: string;
  effectId: number;
}

export interface GearResponse {
  id: number;
  name: string;
  description: string;
  image: string;
  type: string;
  maxUses: number;
  cost: number;
  consumable: boolean;
  rarity: string;
  effectId: number;
}
