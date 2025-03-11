import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./components/auth/login/login.component').then(c => c.LoginComponent) },
  { path: 'register', loadComponent: () => import('./components/auth/register/register.component').then(c => c.RegisterComponent) },
  {
    path: 'app',
    loadComponent: () => import('./components/main/main-layout/main-layout.component').then(c => c.MainLayoutComponent),
    canActivate: [authGuard],
    children: [
      { path: 'dashboard', loadComponent: () => import('./components/main/dashboard/dashboard.component').then(c => c.DashboardComponent) }
    ]
  }
];
