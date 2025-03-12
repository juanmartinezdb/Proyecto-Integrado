export interface JournalEntryRequest {
  content: string;
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
}

export interface JournalEntryResponse {
  id: number;
  content: string;
  editedAt: string; // ISO Date format
  points: number;
  challengeLevel: 'MUY_FACIL' | 'FACIL' | 'NORMAL' | 'DIFICIL' | 'MUY_DIFICIL';
  userId: number;
  journalId: number;
}
