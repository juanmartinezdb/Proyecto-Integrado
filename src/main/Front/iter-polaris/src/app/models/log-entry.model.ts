export interface LogEntryRequest {
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  type: 'TASK' | 'HABIT' | 'JOURNAL_ENTRY' | 'PROJECT';
  itemId: number;
  endTimestamp: string; // ISO Date format
  energy: number;
  zoneId: number;
}

export interface LogEntryResponse {
  id: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  type: string;
  itemId: number;
  endTimestamp: string; // ISO Date format
  energy: number;
  userId: number;
  zoneId: number;
}
