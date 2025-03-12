import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { SkillResponse } from '../models/skill.model';

@Injectable({
  providedIn: 'root'
})
export class StoreService {
  http = inject(HttpClient);
  private skillsSubject = new BehaviorSubject<SkillResponse[]>([]);
  skills$ = this.skillsSubject.asObservable();
  url = 'http://localhost:3000/store';

  constructor() {
    this.loadSkills();
  }

  loadSkills() {
    this.http.get<SkillResponse[]>(`${this.url}/skills`).subscribe({
      next: (data) => this.skillsSubject.next(data),
      error: (err) => console.error('Error loading store skills', err)
    });
  }

  loadItems() {
    return this.http.get<SkillResponse[]>(`${this.url}/items`);
  }

  acquireSkill(skillId: number) {
    this.http.post<SkillResponse>(`${this.url}/purchase/skill/${skillId}`, {}).subscribe({
      next: () => this.loadSkills(),
      error: (err) => console.error(`Error acquiring skill with ID ${skillId}`, err)
    });
  }

  purchaseItem(itemId: number) {
    return this.http.post<string>(`${this.url}/purchase/item/${itemId}`, {});
  }
}
