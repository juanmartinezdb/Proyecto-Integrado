import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/zone.service';

@Component({
  selector: 'app-zone-journals',
  templateUrl: './zone-journals.component.html',
  styleUrl: './zone-journals.component.css'
})
export class ZoneJournalsComponent implements OnInit {
  journals: any[] = [];
  zoneId!: number;
  zoneService = inject(ZoneService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.zoneId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.zoneService.getZoneJournals(this.zoneId).subscribe((data) => {
      this.journals = data;
    });
  }
}
