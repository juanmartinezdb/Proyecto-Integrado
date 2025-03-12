import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TemplateRequest, TemplateResponse } from '../models/template.model';

@Injectable({
  providedIn: 'root'
})
export class TemplateService {
  http = inject(HttpClient);
  private templatesSubject = new BehaviorSubject<TemplateResponse[]>([]);
  templates$ = this.templatesSubject.asObservable();
  url = 'http://localhost:3000/templates';

  constructor() {
    this.load();
  }

  load(category?: string) {
    const endpoint = category ? `${this.url}?category=${category}` : this.url;
    this.http.get<TemplateResponse[]>(endpoint).subscribe({
      next: (data) => this.templatesSubject.next(data),
      error: (err) => console.error('Error loading templates', err)
    });
  }

  add(template: TemplateRequest) {
    this.http.post<TemplateResponse>(this.url, template).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding template', err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting template with ID ${id}`, err)
    });
  }
}
