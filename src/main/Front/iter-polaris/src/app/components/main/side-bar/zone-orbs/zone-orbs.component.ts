import { Component, OnInit, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ZoneService } from '../../../../services/zone.service';
import { ZoneResponse } from '../../../../models/zone.model';

@Component({
  selector: 'app-zone-orbs',
  imports: [RouterLink],
  templateUrl: './zone-orbs.component.html',
  styleUrls: ['./zone-orbs.component.css']
})
export class ZoneOrbsComponent implements OnInit {
  zones: ZoneResponse[] = [];
  zoneService = inject(ZoneService);
  router = inject(Router);

  ngOnInit(): void {
    this.zoneService.getZones().subscribe(data => {
      this.zones = data;
    });
  }

  createZone(): void {
    this.router.navigate(['/app/zones/create']);
  }
}
