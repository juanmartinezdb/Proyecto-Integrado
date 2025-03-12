import { Component, inject, OnInit } from '@angular/core';
import { TaskService } from '../../../services/task.service';
import { TaskResponse } from '../../../models/task.model';

@Component({
  selector: 'app-overdue-tasks',
  imports: [],
  templateUrl: './overdue-tasks.component.html',
  styleUrl: './overdue-tasks.component.css'
})
export class OverdueTasksComponent implements OnInit {
  overdueTasks: TaskResponse[] = [];
  taskService = inject(TaskService);

  ngOnInit(): void {
    this.taskService.getOverdueTasks().subscribe((data) => {
      this.overdueTasks = data;
    });
  }
}
