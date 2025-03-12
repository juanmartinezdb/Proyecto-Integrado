import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ProjectRequest, ProjectResponse } from '../models/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  http = inject(HttpClient);
  private projectsSubject = new BehaviorSubject<ProjectResponse[]>([]);
  projects$ = this.projectsSubject.asObservable();
  url = 'http://localhost:3000/projects';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<ProjectResponse[]>(this.url).subscribe({
      next: (data) => this.projectsSubject.next(data),
      error: (err) => console.error('Error loading projects', err)
    });
  }

  add(project: ProjectRequest) {
    this.http.post<ProjectResponse>(this.url, project).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding project', err)
    });
  }

  update(id: number, project: ProjectRequest) {
    this.http.put(`${this.url}/${id}`, project).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating project with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting project with ID ${id}`, err)
    });
  }

  complete(id: number) {
    this.http.post(`${this.url}/${id}/complete`, {}).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error completing project with ID ${id}`, err)
    });
  }
}
