import { Component, inject, OnInit } from '@angular/core';
import { TaskService } from '../../../services/tasks.service';
import { TaskResponse } from '../../../models/task.model';

@Component({
  selector: 'app-completed-task',
  imports: [],
  templateUrl: './completed-task.component.html',
  styleUrl: './completed-task.component.css'
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
