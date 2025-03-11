import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JournalService } from '../../../services/journals.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-journal-entry-list',
  imports: [RouterModule],
  templateUrl: './journal-entry-list.component.html',
  styleUrl: './journal-entry-list.component.css'
})
export class JournalEntryListComponent implements OnInit {
  journalId!: number;
  entries: any[] = [];
  journalService = inject(JournalService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.journalId = parseInt(this.route.snapshot.paramMap.get('journalId')!, 10);
    this.journalService.getJournalEntries(this.journalId).subscribe((data) => {
      this.entries = data;
    });
  }
}
