import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ZoneResponse } from '../models/zone.model';

@Injectable({ providedIn: 'root' })
export class ZoneService {
  private baseUrl = 'http://localhost:8080/zones';

  constructor(private http: HttpClient) {}

  // Obtener todas las zonas del usuario
  getZones(): Observable<ZoneResponse[]> {
    return this.http.get<ZoneResponse[]>(`${this.baseUrl}`);
  }

  // Obtener una zona por ID
  getZoneById(id: number): Observable<ZoneResponse> {
    return this.http.get<ZoneResponse>(`${this.baseUrl}/${id}`);
  }

  // Obtener hábitos de una zona
  getZoneHabits(zoneId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${zoneId}/habits`);
  }

  // Obtener diarios de una zona
  getZoneJournals(zoneId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${zoneId}/journals`);
  }

  // Obtener tareas de una zona
  getZoneTasks(zoneId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${zoneId}/tasks`);
  }

  // Obtener proyectos de una zona
  getZoneProjects(zoneId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${zoneId}/projects`);
  }
}
