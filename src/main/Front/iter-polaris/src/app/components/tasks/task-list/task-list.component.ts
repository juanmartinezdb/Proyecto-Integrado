import { Component } from '@angular/core';
import { TaskService } from '../../../services/task.service';
import { TaskResponse } from '../../../models/task.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-task-list',
  imports: [RouterModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {
  tasks: TaskResponse[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    this.taskService.getTasks().subscribe((data) => {
      this.tasks = data;
    });
  }
}
