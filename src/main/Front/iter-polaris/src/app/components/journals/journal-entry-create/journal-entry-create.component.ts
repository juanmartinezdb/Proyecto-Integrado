import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JournalService } from '../../../services/journal.service';

@Component({
  selector: 'app-journal-entry-create',
  imports: [ReactiveFormsModule],
  templateUrl: './journal-entry-create.component.html',
  styleUrl: './journal-entry-create.component.css'
})
export class JournalEntryCreateComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  journalService = inject(JournalService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  journalId!: number;

  ngOnInit(): void {
    this.journalId = parseInt(this.route.snapshot.paramMap.get('journalId')!, 10);
    this.formu = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      content: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  submit() {
    if (this.formu.valid) {
      this.journalService.createJournalEntry(this.journalId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/journals', this.journalId, 'entries']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
