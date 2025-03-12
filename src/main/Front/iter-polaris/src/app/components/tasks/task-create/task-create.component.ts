import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../../services/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-create',
  imports: [ReactiveFormsModule],
  templateUrl: './task-create.component.html',
  styleUrl: './task-create.component.css'
})
export class TaskCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  taskService = inject(TaskService);
  router = inject(Router);

  ngOnInit(): void {
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      challengeLevel: ['NORMAL', [Validators.required]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.taskService.createTask(this.formu.value).subscribe(() => {
        this.router.navigate(['/app/tasks']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
