import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProjectService } from '../../../services/project.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-create',
  imports: [ReactiveFormsModule],
  templateUrl: './project-create.component.html',
  styleUrl: './project-create.component.css'
})
export class ProjectCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  projectService = inject(ProjectService);
  router = inject(Router);

  ngOnInit(): void {
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      priority: ['MEDIUM', [Validators.required]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.projectService.createProject(this.formu.value).subscribe(() => {
        this.router.navigate(['/app/projects']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
