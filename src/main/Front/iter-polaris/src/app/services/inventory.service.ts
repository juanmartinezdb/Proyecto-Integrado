import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { InventoryItemRequest, InventoryItemResponse } from '../models/inventory.model';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  http = inject(HttpClient);
  private inventorySubject = new BehaviorSubject<InventoryItemResponse[]>([]);
  inventory$ = this.inventorySubject.asObservable();
  url = 'http://localhost:3000/inventory';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<InventoryItemResponse[]>(this.url).subscribe({
      next: (data) => this.inventorySubject.next(data),
      error: (err) => console.error('Error loading inventory', err)
    });
  }

  add(item: InventoryItemRequest) {
    this.http.post<InventoryItemResponse>(this.url, item).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding inventory item', err)
    });
  }

  update(id: number, item: InventoryItemRequest) {
    this.http.put(`${this.url}/${id}`, item).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating inventory item with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting inventory item with ID ${id}`, err)
    });
  }
}
