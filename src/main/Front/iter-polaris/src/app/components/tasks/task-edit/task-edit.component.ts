import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../../services/task.service';
import { TaskResponse } from '../../../models/task.model';

@Component({
  selector: 'app-task-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './task-edit.component.html',
  styleUrl: './task-edit.component.css'
})
export class TaskEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  taskService = inject(TaskService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  taskId!: number;

  ngOnInit(): void {
    this.taskId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      status: ['PENDING', [Validators.required]],
      priority: ['MEDIUM', [Validators.required]]
    });

    this.taskService.getTaskById(this.taskId).subscribe((task: TaskResponse) => {
      this.formu.patchValue(task);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.taskService.updateTask(this.taskId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/tasks']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
