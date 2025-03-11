import { Component, inject, OnInit } from '@angular/core';
import { ZoneResponse } from '../../../../models/zone.model';
import { ZoneService } from '../../../../services/zone.service';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-zone-orbs',
  imports: [RouterModule], 
  templateUrl: './zone-orbs.component.html',
  styleUrl: './zone-orbs.component.css'
})
export class ZoneOrbsComponent implements OnInit {
  zones: ZoneResponse[] = [];
  zoneService = inject(ZoneService);

  ngOnInit(): void {
    this.zoneService.getZones().subscribe((data: ZoneResponse[]) => {
      this.zones = data;
    });
  }
}
