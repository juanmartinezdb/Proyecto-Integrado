import { Component, inject, OnInit } from '@angular/core';
import { ZoneResponse } from '../../../models/zone.model';
import { ZoneService } from '../../../services/zone.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-zone-list',
  imports: [RouterModule],
  templateUrl: './zone-list.component.html',
  styleUrl: './zone-list.component.css'
})
export class ZoneListComponent implements OnInit {
  zones: ZoneResponse[] = [];
  zoneService = inject(ZoneService);

  ngOnInit(): void {
    this.zoneService.getZones().subscribe((data) => {
      this.zones = data;
    });
  }
}
