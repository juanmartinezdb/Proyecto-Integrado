import { Component, inject, OnInit } from '@angular/core';
import { AgendaService } from '../../../services/agenda.service';
import { AgendaEventResponse } from '../../../models/agenda.model';

@Component({
  selector: 'app-agenda-calendar',
  imports: [],
  templateUrl: './agenda-calendar.component.html',
  styleUrl: './agenda-calendar.component.css'
})
export class AgendaCalendarComponent implements OnInit {
  events: AgendaEventResponse[] = [];
  currentDate: Date = new Date();
  agendaService = inject(AgendaService);

  ngOnInit(): void {
    this.agendaService.getMonthEvents(this.currentDate.getMonth() + 1, this.currentDate.getFullYear())
      .subscribe((data) => {
        this.events = data;
      });
  }

  getEventsForDay(day: number): AgendaEventResponse[] {
    const dateStr = `${this.currentDate.getFullYear()}-${(this.currentDate.getMonth() + 1).toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
    return this.events.filter(e => e.date.startsWith(dateStr));
  }
}
