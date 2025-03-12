import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';

// Función auxiliar para decodificar el JWT y verificar su expiración
function isTokenExpired(token: string): boolean {
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const expiry = payload.exp;
    // La fecha actual en segundos
    return (Math.floor(new Date().getTime() / 1000)) >= expiry;
  } catch (e) {
    // Si falla el parseo, asumimos que el token es inválido o está expirado
    return true;
  }
}

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const token = localStorage.getItem('jwt_token');

  if (token && !isTokenExpired(token)) {
    return true;
  } else {
    // Si no hay token o ya expiró, lo limpiamos y redirigimos al login
    localStorage.removeItem('jwt_token');
    router.navigate(['/auth/login']);
    return false;
  }
};
