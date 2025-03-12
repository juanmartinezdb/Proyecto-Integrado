import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  http = inject(HttpClient);
  private usersSubject = new BehaviorSubject<User[]>([]);
  users$ = this.usersSubject.asObservable();
  url = 'http://localhost:3000/users';

  constructor() {
    this.load();
  }

  load() {
    this.http.get<User[]>(this.url).subscribe({
      next: (data) => this.usersSubject.next(data),
      error: (err) => console.error('Error loading users', err)
    });
  }

  getById(id: number) {
    return this.http.get<User>(`${this.url}/${id}`);
  }

  update(id: number, user: User) {
    this.http.put(`${this.url}/${id}`, user).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error updating user with ID ${id}`, err)
    });
  }

  remove(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe({
      next: () => this.load(),
      error: (err) => console.error(`Error deleting user with ID ${id}`, err)
    });
  }

  getCurrentUser(username: string) {
    return this.http.get<User>(`${this.url}/me?username=${username}`);
  }
}
