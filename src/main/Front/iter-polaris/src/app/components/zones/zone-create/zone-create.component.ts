import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ZoneService } from '../../../services/zone.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zone-create',
  imports: [ReactiveFormsModule],
  templateUrl: './zone-create.component.html',
  styleUrls: ['./zone-create.component.css']
})
export class ZoneCreateComponent {
  zoneForm: FormGroup;
  fb = inject(FormBuilder);
  zoneService = inject(ZoneService);
  router = inject(Router);

  constructor() {
    this.zoneForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      color: ['#007bff', Validators.required]
    });
  }

  submit(): void {
    if (this.zoneForm.valid) {
      this.zoneService.createZone(this.zoneForm.value).subscribe(() => {
        this.router.navigate(['/app/zones']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
