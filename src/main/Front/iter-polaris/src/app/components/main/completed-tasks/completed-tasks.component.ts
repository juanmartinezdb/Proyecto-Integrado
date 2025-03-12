import { Component, inject, OnInit } from '@angular/core';
import { TaskService } from '../../../services/task.service';
import { TaskResponse } from '../../../models/task.model';

@Component({
  selector: 'app-completed-task',
  imports: [],
  templateUrl: './completed-tasks.component.html',
  styleUrl: './completed-tasks.component.css'
})
export class CompletedTaskComponent implements OnInit {
  completedTasks: TaskResponse[] = [];
  taskService = inject(TaskService);

  ngOnInit(): void {
    this.taskService.getCompletedTasks().subscribe((data) => {
      this.completedTasks = data;
    });
  }
}
