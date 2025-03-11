import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AgendaEventResponse } from '../models/agenda.model';

@Injectable({ providedIn: 'root' })
export class AgendaService {
  private baseUrl = 'http://localhost:8080/agenda';

  constructor(private http: HttpClient) {}

  // Obtener eventos desde hoy hacia el futuro
  getUpcomingEvents(): Observable<AgendaEventResponse[]> {
    return this.http.get<AgendaEventResponse[]>(`${this.baseUrl}/events/upcoming`);
  }

  // Obtener eventos de un mes y año específicos
  getMonthEvents(month: number, year: number): Observable<AgendaEventResponse[]> {
    return this.http.get<AgendaEventResponse[]>(`${this.baseUrl}/events/month/${year}/${month}`);
  }
}
