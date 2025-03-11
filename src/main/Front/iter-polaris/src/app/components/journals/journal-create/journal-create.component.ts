import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JournalService } from '../../../services/journals.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-journal-create',
  imports: [ReactiveFormsModule],
  templateUrl: './journal-create.component.html',
  styleUrl: './journal-create.component.css'
})
export class JournalCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  journalService = inject(JournalService);
  router = inject(Router);

  ngOnInit(): void {
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      type: ['Personal', [Validators.required]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.journalService.createJournal(this.formu.value).subscribe(() => {
        this.router.navigate(['/app/journals']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
