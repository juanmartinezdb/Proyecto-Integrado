import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AchievementResponse } from '../models/achievement.model';

@Injectable({
  providedIn: 'root'
})
export class AchievementService {
  http = inject(HttpClient);
  private achievementsSubject = new BehaviorSubject<AchievementResponse[]>([]);
  achievements$ = this.achievementsSubject.asObservable();
  url = 'http://localhost:3000/achievements';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<AchievementResponse[]>(this.url).subscribe({
      next: (data) => this.achievementsSubject.next(data),
      error: (err) => console.error('Error loading achievements', err)
    });
  }
}
