import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

// Importación de componentes principales
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ForgotPasswordComponent } from './components/auth/forgot-password/forgot-password.component';
import { MainComponent } from './components/main/main-content/main-content.component';

import { DashboardComponent } from './components/main/dashboard/dashboard.component';

// Importación de componentes de zonas
import { ZoneListComponent } from './components/zones/zone-list/zone-list.component';
import { ZoneDetailComponent } from './components/zones/zone-detail/zone-detail.component';
import { ZoneTasksComponent } from './components/zones/zone-tasks/zone-tasks.component';
import { ZoneProjectsComponent } from './components/zones/zone-projects/zone-projects.component';
import { ZoneHabitsComponent } from './components/zones/zone-habits/zone-habits.component';
import { ZoneJournalsComponent } from './components/zones/zone-journals/zone-journals.component';

// Importación de componentes de tareas
import { TaskListComponent } from './components/tasks/task-list/task-list.component';
import { TaskCreateComponent } from './components/tasks/task-create/task-create.component';
import { TaskEditComponent } from './components/tasks/task-edit/task-edit.component';
import { CompletedTaskComponent } from './components/main/completed-tasks/completed-tasks.component';
import { OverdueTasksComponent } from './components/main/overdue-tasks/overdue-tasks.component';

// Importación de componentes de proyectos
import { ProjectListComponent } from './components/projects/project-list/project-list.component';
import { ProjectCreateComponent } from './components/projects/project-create/project-create.component';
import { ProjectEditComponent } from './components/projects/project-edit/project-edit.component';

// Importación de componentes de hábitos
import { HabitListComponent } from './components/habits/habit-list/habit-list.component';
import { HabitCreateComponent } from './components/habits/habit-create/habit-create.component';
import { HabitEditComponent } from './components/habits/habit-edit/habit-edit.component';

// Importación de componentes de diarios
import { JournalListComponent } from './components/journals/journal-list/journal-list.component';
import { JournalCreateComponent } from './components/journals/journal-create/journal-create.component';
import { JournalEditComponent } from './components/journals/journal-edit/journal-edit.component';
import { JournalEntryListComponent } from './components/journals/journal-entry-list/journal-entry-list.component';
import { JournalEntryCreateComponent } from './components/journals/journal-entry-create/journal-entry-create.component';
import { JournalEntryEditComponent } from './components/journals/journal-entry-edit/journal-entry-edit.component';

// Importación de componentes de agenda
import { AgendaListComponent } from './components/main/agenda-list/agenda-list.component';
import { AgendaCalendarComponent } from './components/main/agenda-calendar/agenda-calendar.component';

// Importación de componentes de materiales
import { MaterialListComponent } from './components/materials/material-list/material-list.component';
import { MaterialCreateComponent } from './components/materials/material-create/material-create.component';
import { MaterialEditComponent } from './components/materials/material-edit/material-edit.component';

// Importación de componentes adicionales
import { EnergyBalanceComponent } from './components/main/top-nav/energy-balance/energy-balance.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  {
    path: 'app',
    component: MainComponent,
    canActivate: [authGuard],
    children: [
      { path: 'dashboard', component: DashboardComponent },

      // Rutas de zonas
      { path: 'zones', component: ZoneListComponent },
      {
        path: 'zones/:id',
        component: ZoneDetailComponent,
        children: [
          { path: 'tasks', component: ZoneTasksComponent },
          { path: 'projects', component: ZoneProjectsComponent },
          { path: 'habits', component: ZoneHabitsComponent },
          { path: 'journals', component: ZoneJournalsComponent }
        ]
      },
      { path: 'zones/:id/tasks', component: ZoneTasksComponent },
      { path: 'zones/:id/projects', component: ZoneProjectsComponent },
      { path: 'zones/:id/habits', component: ZoneHabitsComponent },
      { path: 'zones/:id/journals', component: ZoneJournalsComponent },

      // Rutas de tareas
      { path: 'tasks', component: TaskListComponent },
      { path: 'tasks/create', component: TaskCreateComponent },
      { path: 'tasks/:id/edit', component: TaskEditComponent },
      { path: 'tasks/completed', component: CompletedTaskComponent },
      { path: 'tasks/overdue', component: OverdueTasksComponent },

      // Rutas de proyectos
      { path: 'projects', component: ProjectListComponent },
      { path: 'projects/create', component: ProjectCreateComponent },
      { path: 'projects/:id/edit', component: ProjectEditComponent },

      // Rutas de hábitos
      { path: 'habits', component: HabitListComponent },
      { path: 'habits/create', component: HabitCreateComponent },
      { path: 'habits/:id/edit', component: HabitEditComponent },

      // Rutas de diarios
      { path: 'journals', component: JournalListComponent },
      { path: 'journals/create', component: JournalCreateComponent },
      { path: 'journals/:id/edit', component: JournalEditComponent },
      { path: 'journals/:id/entries', component: JournalEntryListComponent },
      { path: 'journals/:id/entries/create', component: JournalEntryCreateComponent },
      { path: 'journals/:id/entries/:entryId/edit', component: JournalEntryEditComponent },

      // Rutas de agenda
      { path: 'agenda', component: AgendaListComponent },
      { path: 'calendar', component: AgendaCalendarComponent },

      // Rutas de materiales
      { path: 'materials', component: MaterialListComponent },
      { path: 'materials/create', component: MaterialCreateComponent },
      { path: 'materials/:id/edit', component: MaterialEditComponent },

      // Otros componentes
      { path: 'energy-balance', component: EnergyBalanceComponent }
    ]
  }
];
