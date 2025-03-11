
export interface JournalRequest {
  name: string;
  description?: string;
  image?: string;
  type?: string;
  lastEntryDate?: string;
  streak?: number;
}

export interface JournalResponse extends JournalRequest {
  id: number;
  createdAt: string;
  userId: number;
  entriesIds?: Set<number>;
}
