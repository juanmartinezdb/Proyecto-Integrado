import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MaterialService } from '../../../services/material.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-material-create',
  imports: [ReactiveFormsModule],
  templateUrl: './material-create.component.html',
  styleUrl: './material-create.component.css'
})
export class MaterialCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  materialService = inject(MaterialService);
  router = inject(Router);

  ngOnInit(): void {
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      quantity: [1, [Validators.required, Validators.min(1)]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.materialService.createMaterial(this.formu.value).subscribe(() => {
        this.router.navigate(['/app/materials']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
