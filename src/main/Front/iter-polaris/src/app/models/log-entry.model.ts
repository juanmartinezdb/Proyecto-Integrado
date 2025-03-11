
export interface LogEntryRequest {
  challengeLevel: string;
  type: string;
  itemId: number;
  endTimestamp: string;
  energy?: number;
  zoneId: number;
}

export interface LogEntryResponse extends LogEntryRequest {
  id: number;
  userId: number;
}
