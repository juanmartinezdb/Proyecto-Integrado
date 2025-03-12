import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwt_token');
  if (token) {
    req = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) });
  }


  // Inyectamos el Router para poder redirigir en caso de error
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      // Si la respuesta es 401 (no autorizado), significa que el token ha expirado o es inválido
      if (error.status === 401) {
        // Eliminamos el token expirado
        localStorage.removeItem('jwt_token');
        // Redirigimos al login
        router.navigate(['/auth/login']);
      }
      return throwError(() => error);
    })
  );
};
