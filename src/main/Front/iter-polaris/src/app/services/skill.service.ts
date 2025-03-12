import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { SkillRequest, SkillResponse } from '../models/skill.model';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  http = inject(HttpClient);
  private skillsSubject = new BehaviorSubject<SkillResponse[]>([]);
  skills$ = this.skillsSubject.asObservable();
  url = 'http://localhost:3000/skills';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<SkillResponse[]>(this.url).subscribe({
      next: (data) => this.skillsSubject.next(data),
      error: (err) => console.error('Error loading skills', err)
    });
  }

  add(skill: SkillRequest) {
    this.http.post<SkillResponse>(this.url, skill).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding skill', err)
    });
  }

  update(id: number, skill: SkillRequest) {
    this.http.put(`${this.url}/${id}`, skill).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating skill with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting skill with ID ${id}`, err)
    });
  }
}
