import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HabitService } from '../../../services/habits.service';
import { HabitResponse } from '../../../models/habit.model';

@Component({
  selector: 'app-habit-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './habit-edit.component.html',
  styleUrl: './habit-edit.component.css'
})
export class HabitEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  habitService = inject(HabitService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  habitId!: number;

  ngOnInit(): void {
    this.habitId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      frequency: ['DAILY', [Validators.required]]
    });

    this.habitService.getHabitById(this.habitId).subscribe((habit: HabitResponse) => {
      this.formu.patchValue(habit);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.habitService.updateHabit(this.habitId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/habits']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
