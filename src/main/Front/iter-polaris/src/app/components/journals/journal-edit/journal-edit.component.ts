import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JournalService } from '../../../services/journals.service';
import { JournalResponse } from '../../../models/journal.model';

@Component({
  selector: 'app-journal-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './journal-edit.component.html',
  styleUrl: './journal-edit.component.css'
})
export class JournalEditComponent implements OnInit {
  formu!: FormGroup;
  fb = inject(FormBuilder);
  journalService = inject(JournalService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  journalId!: number;

  ngOnInit(): void {
    this.journalId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formu = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      type: ['Personal', [Validators.required]]
    });

    this.journalService.getJournalById(this.journalId).subscribe((journal: JournalResponse) => {
      this.formu.patchValue(journal);
    });
  }

  submit() {
    if (this.formu.valid) {
      this.journalService.updateJournal(this.journalId, this.formu.value).subscribe(() => {
        this.router.navigate(['/app/journals']);
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
