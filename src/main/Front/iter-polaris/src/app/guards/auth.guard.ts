import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard = () => {
  const token = localStorage.getItem('jwt_token');
  if (!token) {
    return inject(Router).navigate(['/login']);
  }
  return true;
};
