import { Component } from '@angular/core';
import { CompletedTaskComponent } from '../completed-tasks/completed-tasks.component';
import { OverdueTasksComponent } from '../overdue-tasks/overdue-tasks.component';
import { HabitListComponent } from '../../habits/habit-list/habit-list.component';
import { ProjectListComponent } from '../../projects/project-list/project-list.component';

@Component({
  selector: 'app-dashboard',
  imports: [CompletedTaskComponent, OverdueTasksComponent, HabitListComponent, ProjectListComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {}
