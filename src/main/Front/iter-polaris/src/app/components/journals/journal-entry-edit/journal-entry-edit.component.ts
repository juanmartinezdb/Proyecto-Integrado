import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JournalService } from '../../../services/journal.service';
import { JournalResponse } from '../../../models/journal.model';
@Component({
  selector: 'app-journal-entry-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './journal-entry-edit.component.html',
  styleUrl: './journal-entry-edit.component.css'
})
export class JournalEntryEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  journalService = inject(JournalService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  entryId!: number;
  journalId!: number;

  ngOnInit(): void {
    this.journalId = parseInt(this.route.snapshot.paramMap.get('journalId')!, 10);
    this.entryId = parseInt(this.route.snapshot.paramMap.get('entryId')!, 10);

    this.formu = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      content: ['', [Validators.required, Validators.minLength(10)]]
    });

    this.journalService.getJournalById(this.journalId).subscribe((entry: JournalResponse) => {
      this.formu.patchValue(entry);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.journalService.updateJournal(this.journalId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/journals', this.journalId, 'entries']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
