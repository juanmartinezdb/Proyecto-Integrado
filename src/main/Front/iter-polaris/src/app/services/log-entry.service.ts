import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LogEntryRequest, LogEntryResponse } from '../models/log-entry.model';

@Injectable({
  providedIn: 'root'
})
export class LogEntryService {
  http = inject(HttpClient);
  private logEntriesSubject = new BehaviorSubject<LogEntryResponse[]>([]);
  logEntries$ = this.logEntriesSubject.asObservable();
  url = 'http://localhost:3000/log-entries';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<LogEntryResponse[]>(this.url).subscribe({
      next: (data) => this.logEntriesSubject.next(data),
      error: (err) => console.error('Error loading log entries', err)
    });
  }

  add(entry: LogEntryRequest) {
    this.http.post<LogEntryResponse>(this.url, entry).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding log entry', err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting log entry with ID ${id}`, err)
    });
  }
}
