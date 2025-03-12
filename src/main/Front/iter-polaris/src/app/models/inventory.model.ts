export interface InventoryItemRequest {
  gearId: number;
  remainingUses?: number;
}

export interface InventoryItemResponse {
  id: number;
  remainingUses: number;
  acquiredAt: string; // ISO Date format
  gearId: number;
  gearName?: string;
  userId: number;
}
