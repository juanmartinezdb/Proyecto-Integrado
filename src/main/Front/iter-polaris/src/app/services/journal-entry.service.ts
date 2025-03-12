import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { JournalEntryRequest, JournalEntryResponse } from '../models/journal-entry.model';

@Injectable({
  providedIn: 'root'
})
export class JournalEntryService {
  http = inject(HttpClient);
  private journalEntriesSubject = new BehaviorSubject<JournalEntryResponse[]>([]);
  journalEntries$ = this.journalEntriesSubject.asObservable();
  url = 'http://localhost:3000/journals';

  constructor() {}

  load(journalId: number) {
    this.http.get<JournalEntryResponse[]>(`${this.url}/${journalId}/entries`).subscribe({
      next: (data) => this.journalEntriesSubject.next(data),
      error: (err) => console.error('Error loading journal entries', err)
    });
  }

  add(journalId: number, entry: JournalEntryRequest) {
    this.http.post<JournalEntryResponse>(`${this.url}/${journalId}/entries`, entry).subscribe({
      next: () => this.load(journalId),
      error: (err) => console.error('Error adding journal entry', err)
    });
  }

  update(journalId: number, entryId: number, entry: JournalEntryRequest) {
    this.http.put(`${this.url}/${journalId}/entries/${entryId}`, entry).subscribe({
      next: () => this.load(journalId),
      error: (err) => console.error(`Error updating journal entry with ID ${entryId}`, err)
    });
  }

  remove(journalId: number, entryId: number) {
    this.http.delete(`${this.url}/${journalId}/entries/${entryId}`).subscribe({
      next: () => this.load(journalId),
      error: (err) => console.error(`Error deleting journal entry with ID ${entryId}`, err)
    });
  }
}
