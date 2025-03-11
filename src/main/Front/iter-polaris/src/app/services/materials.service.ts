import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MaterialRequest, MaterialResponse } from '../models/material.model';

@Injectable({ providedIn: 'root' })
export class MaterialService {
  private baseUrl = 'http://localhost:8080/materials';

  constructor(private http: HttpClient) {}

  getMaterials(): Observable<MaterialResponse[]> {
    return this.http.get<MaterialResponse[]>(`${this.baseUrl}`);
  }

  getMaterialById(id: number): Observable<MaterialResponse> {
    return this.http.get<MaterialResponse>(`${this.baseUrl}/${id}`);
  }

  createMaterial(material: MaterialRequest): Observable<MaterialResponse> {
    return this.http.post<MaterialResponse>(`${this.baseUrl}`, material);
  }

  updateMaterial(id: number, material: MaterialRequest): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}`, material);
  }

  deleteMaterial(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
