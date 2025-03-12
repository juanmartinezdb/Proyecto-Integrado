import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { JournalRequest, JournalResponse } from '../models/journal.model';

@Injectable({
  providedIn: 'root'
})
export class JournalService {
  http = inject(HttpClient);
  private journalsSubject = new BehaviorSubject<JournalResponse[]>([]);
  journals$ = this.journalsSubject.asObservable();
  url = 'http://localhost:3000/journals';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<JournalResponse[]>(this.url).subscribe({
      next: (data) => this.journalsSubject.next(data),
      error: (err) => console.error('Error loading journals', err)
    });
  }

  add(journal: JournalRequest) {
    this.http.post<JournalResponse>(this.url, journal).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding journal', err)
    });
  }

  update(id: number, journal: JournalRequest) {
    this.http.put(`${this.url}/${id}`, journal).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating journal with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting journal with ID ${id}`, err)
    });
  }
}
