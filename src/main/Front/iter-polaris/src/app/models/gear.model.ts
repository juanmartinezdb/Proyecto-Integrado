
export interface GearRequest {
  name: string;
  description?: string;
  image?: string;
  type?: string;
  maxUses?: number;
  cost?: number;
  consumable?: boolean;
  rarity?: string;
  effectId?: number;
}

export interface GearResponse extends GearRequest {
  id: number;
}
