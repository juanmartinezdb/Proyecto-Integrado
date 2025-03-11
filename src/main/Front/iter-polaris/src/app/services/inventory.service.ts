import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InventoryItemResponse, GearResponse } from '../models/inventory.model';

@Injectable({ providedIn: 'root' })
export class InventoryService {
  private baseUrl = 'http://localhost:8080/inventory';

  constructor(private http: HttpClient) {}

  getInventory(): Observable<InventoryItemResponse[]> {
    return this.http.get<InventoryItemResponse[]>(`${this.baseUrl}`);
  }

  getAvailableGears(): Observable<GearResponse[]> {
    return this.http.get<GearResponse[]>(`${this.baseUrl}/gears`);
  }

  addGearToInventory(gearId: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/add`, { gearId });
  }

  useItem(itemId: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/use/${itemId}`, {});
  }
}
