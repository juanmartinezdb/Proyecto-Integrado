export interface JournalRequest {
  name: string;
  description: string;
  image: string;
  type: 'mental' | 'physical' | 'emotional' | 'creative' | 'social';
  lastEntryDate?: string; // ISO Date format
  streak: number;
}

export interface JournalResponse {
  id: number;
  name: string;
  description: string;
  image: string;
  type: string;
  createdAt: string; // ISO Date format
  userId: number;
  lastEntryDate?: string; // ISO Date format
  streak: number;
  entriesIds: number[];
}
