import { Component } from '@angular/core';
import { ProjectService } from '../../../services/projects.service';
import { ProjectResponse } from '../../../models/project.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-project-list',
  imports: [RouterModule],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.css'
})
export class ProjectListComponent {
  projects: ProjectResponse[] = [];

  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.projectService.getProjects().subscribe((data) => {
      this.projects = data;
    });
  }
}
