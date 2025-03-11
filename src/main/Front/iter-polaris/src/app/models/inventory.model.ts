export interface InventoryItemRequest {
  gearId: number;
  remainingUses?: number;
}

export interface InventoryItemResponse extends InventoryItemRequest {
  id: number;
  acquiredAt: string;
  gearName?: string;
  userId: number;
}
