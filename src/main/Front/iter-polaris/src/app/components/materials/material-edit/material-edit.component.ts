import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MaterialService } from '../../../services/material.service';
import { MaterialResponse } from '../../../models/material.model';

@Component({
  selector: 'app-material-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './material-edit.component.html',
  styleUrl: './material-edit.component.css'
})
export class MaterialEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  materialService = inject(MaterialService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  materialId!: number;

  ngOnInit(): void {
    this.materialId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      quantity: [1, [Validators.required, Validators.min(1)]]
    });

    this.materialService.getMaterialById(this.materialId).subscribe((material: MaterialResponse) => {
      this.formu.patchValue(material);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.materialService.updateMaterial(this.materialId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/materials']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
