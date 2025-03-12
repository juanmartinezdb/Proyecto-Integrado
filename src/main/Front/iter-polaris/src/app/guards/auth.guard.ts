import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const token = localStorage.getItem('jwt_token');

  if (!token) {
    router.navigate(['/auth/login']);
    return false;
  }

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const expiry = payload.exp;

    // Si el token ha expirado, eliminarlo y redirigir al login
    if (Math.floor(Date.now() / 1000) >= expiry) {
      localStorage.removeItem('jwt_token');
      router.navigate(['/auth/login']);
      return false;
    }

    return true; // ✅ Permitir acceso a cualquier usuario autenticado
  } catch (e) {
    localStorage.removeItem('jwt_token');
    router.navigate(['/auth/login']);
    return false;
  }
};
