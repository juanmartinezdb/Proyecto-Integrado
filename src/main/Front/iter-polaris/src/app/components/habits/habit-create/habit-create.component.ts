import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HabitService } from '../../../services/habits.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-habit-create',
  imports: [ReactiveFormsModule],
  templateUrl: './habit-create.component.html',
  styleUrl: './habit-create.component.css'
})
export class HabitCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  habitService = inject(HabitService);
  router = inject(Router);

  ngOnInit(): void {
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      frequency: ['DAILY', [Validators.required]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.habitService.createHabit(this.formu.value).subscribe(() => {
        this.router.navigate(['/app/habits']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
