import { Component } from '@angular/core';
import { JournalService } from '../../../services/journal.service';
import { JournalResponse } from '../../../models/journal.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-journal-list',
  imports: [RouterLink],
  templateUrl: './journal-list.component.html',
  styleUrl: './journal-list.component.css'
})
export class JournalListComponent {
  journals: JournalResponse[] = [];

  constructor(private journalService: JournalService) {}

  ngOnInit() {
    this.journalService.getJournals().subscribe((data) => {
      this.journals = data;
    });
  }
}
