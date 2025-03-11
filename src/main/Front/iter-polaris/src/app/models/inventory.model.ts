export interface InventoryItemResponse {
  id: number;
  gearName: string;
  remainingUses: number;
  acquiredAt: string;
}

export interface GearResponse {
  id: number;
  name: string;
  description?: string;
  type?: string;
  maxUses?: number;
  cost?: number;
  consumable?: boolean;
  rarity?: string;
  effectId?: number;
}
