import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TaskRequest, TaskResponse } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  http = inject(HttpClient);
  private tasksSubject = new BehaviorSubject<TaskResponse[]>([]);
  tasks$ = this.tasksSubject.asObservable();
  url = 'http://localhost:3000/tasks';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<TaskResponse[]>(this.url).subscribe({
      next: (data) => this.tasksSubject.next(data),
      error: (err) => console.error('Error loading tasks', err)
    });
  }

  add(task: TaskRequest) {
    this.http.post<TaskResponse>(this.url, task).subscribe({
      next: () => this.load(),
      error: (err) => console.error('Error adding task', err)
    });
  }

  update(id: number, task: TaskRequest) {
    this.http.put(`${this.url}/${id}`, task).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating task with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting task with ID ${id}`, err)
    });
  }

  complete(id: number) {
    this.http.post(`${this.url}/${id}/complete`, {}).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error completing task with ID ${id}`, err)
    });
  }
}
