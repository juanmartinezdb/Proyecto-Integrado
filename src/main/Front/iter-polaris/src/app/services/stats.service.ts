import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatsService {
  http = inject(HttpClient);
  url = 'http://localhost:3000/stats';

  getUserEnergy(period: string = 'week') {
    return this.http.get<number>(`${this.url}/user/energy?period=${period}`);
  }

  getUserXp(period: string = 'week') {
    return this.http.get<number>(`${this.url}/user/xp?period=${period}`);
  }

  getZoneEnergy(zoneId: number, period: string = 'week') {
    return this.http.get<number>(`${this.url}/zone/${zoneId}/energy?period=${period}`);
  }

  getZoneXp(zoneId: number, period: string = 'week') {
    return this.http.get<number>(`${this.url}/zone/${zoneId}/xp?period=${period}`);
  }
}
