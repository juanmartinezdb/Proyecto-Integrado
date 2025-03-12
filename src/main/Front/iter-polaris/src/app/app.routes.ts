import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

// Importación de componentes
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { DashboardComponent } from './components/main/dashboard/dashboard.component';
import { MainLayoutComponent } from './components/main/main-layout/main-layout.component';
import { TaskListComponent } from './components/tasks/task-list/task-list.component';
import { ProjectListComponent } from './components/projects/project-list/project-list.component';

export const routes: Routes = [
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/register', component: RegisterComponent },
  {
    path: 'app',
    component: MainLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'tasks', component: TaskListComponent },
      { path: 'projects', component: ProjectListComponent }
    ]
  },
  { path: '**', redirectTo: 'auth/login' }
];
