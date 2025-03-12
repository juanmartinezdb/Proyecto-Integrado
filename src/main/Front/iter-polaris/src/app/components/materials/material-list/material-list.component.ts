import { Component, inject, OnInit } from '@angular/core';
import { MaterialService } from '../../../services/material.service';
import { MaterialResponse } from '../../../models/material.model';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-material-list',
  imports: [RouterModule],
  templateUrl: './material-list.component.html',
  styleUrl: './material-list.component.css'
})
export class MaterialListComponent implements OnInit {
  materials: MaterialResponse[] = [];
  materialService = inject(MaterialService);

  ngOnInit(): void {
    this.materialService.getMaterials().subscribe((data) => {
      this.materials = data;
    });
  }
}
