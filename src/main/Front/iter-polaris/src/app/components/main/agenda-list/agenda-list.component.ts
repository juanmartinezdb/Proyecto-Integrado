import { Component, inject, OnInit } from '@angular/core';
import { AgendaService } from '../../../services/agenda.service';
import { AgendaEventResponse } from '../../../models/agenda.model';

@Component({
  selector: 'app-agenda-list',
  imports: [],
  templateUrl: './agenda-list.component.html',
  styleUrl: './agenda-list.component.css'
})
export class AgendaListComponent implements OnInit {
  events: AgendaEventResponse[] = [];
  agendaService = inject(AgendaService);

  ngOnInit(): void {
    this.agendaService.getUpcomingEvents().subscribe((data) => {
      this.events = data;
    });
  }
}
