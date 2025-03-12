import { Component } from '@angular/core';
import { OverdueTasksComponent } from '../overdue-tasks/overdue-tasks.component';
import { HabitListComponent } from '../../habits/habit-list/habit-list.component';
import { ProjectListComponent } from '../../projects/project-list/project-list.component';
import { CompletedTaskComponent } from '../completed-tasks/completed-tasks.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CompletedTaskComponent, OverdueTasksComponent, HabitListComponent, ProjectListComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {}
