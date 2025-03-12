import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest, RegisterRequest, JwtResponse } from '../models/auth.model';
import { Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}


  login(credentials: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.baseUrl}/login`, credentials).pipe(
      tap(response => {
        if (response.token) {
          localStorage.setItem('jwt_token', response.token); // ✅ Guarda el token correctamente
          window.location.reload(); // ✅ Recarga la página después del login
        }
      })
    );
  }
  register(userData: RegisterRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/register`, userData);
  }

  logout() {
    localStorage.removeItem('jwt_token');
  }
  forgotPassword(email: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/forgot-password`, { email });
  }

}
