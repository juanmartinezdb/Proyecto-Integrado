import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProjectService } from '../../../services/projects.service';
import { ProjectResponse } from '../../../models/project.model';

@Component({
  selector: 'app-project-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './project-edit.component.html',
  styleUrl: './project-edit.component.css'
})
export class ProjectEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  projectService = inject(ProjectService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  projectId!: number;

  ngOnInit(): void {
    this.projectId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      status: ['ACTIVE', [Validators.required]],
      priority: ['MEDIUM', [Validators.required]]
    });

    this.projectService.getProjectById(this.projectId).subscribe((project: ProjectResponse) => {
      this.formu.patchValue(project);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.projectService.updateProject(this.projectId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/projects']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
