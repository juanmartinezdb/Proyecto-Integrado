import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JournalRequest, JournalResponse } from '../models/journal.model';

@Injectable({ providedIn: 'root' })
export class JournalService {
  private baseUrl = 'http://localhost:8080/journals';

  constructor(private http: HttpClient) {}

  getJournals(): Observable<JournalResponse[]> {
    return this.http.get<JournalResponse[]>(`${this.baseUrl}`);
  }

  getJournalById(id: number): Observable<JournalResponse> {
    return this.http.get<JournalResponse>(`${this.baseUrl}/${id}`);
  }

  createJournal(journal: JournalRequest): Observable<JournalResponse> {
    return this.http.post<JournalResponse>(`${this.baseUrl}`, journal);
  }

  updateJournal(id: number, journal: JournalRequest): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}`, journal);
  }

  deleteJournal(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Métodos agregados
  getJournalEntries(journalId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${journalId}/entries`);
  }

  getJournalEntryById(journalId: number, entryId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${journalId}/entries/${entryId}`);
  }

  createJournalEntry(journalId: number, entry: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${journalId}/entries`, entry);
  }

  updateJournalEntry(journalId: number, entryId: number, entry: any): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${journalId}/entries/${entryId}`, entry);
  }
}
