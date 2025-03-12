import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { MaterialRequest, MaterialResponse } from '../models/material.model';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
  http = inject(HttpClient);
  private materialsSubject = new BehaviorSubject<MaterialResponse[]>([]);
  materials$ = this.materialsSubject.asObservable();
  url = 'http://localhost:3000/materials';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<MaterialResponse[]>(this.url).subscribe({
      next: (data) => this.materialsSubject.next(data),
      error: (err) => console.error('Error loading materials', err)
    });
  }

  add(material: MaterialRequest) {
    this.http.post<MaterialResponse>(this.url, material).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding material', err)
    });
  }

  update(id: number, material: MaterialRequest) {
    this.http.put(`${this.url}/${id}`, material).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating material with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting material with ID ${id}`, err)
    });
  }
}
