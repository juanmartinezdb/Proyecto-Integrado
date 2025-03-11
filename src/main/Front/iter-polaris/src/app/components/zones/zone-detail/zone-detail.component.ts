import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ZoneService } from '../../../services/zone.service';
import { ZoneResponse } from '../../../models/zone.model';
import { SecondaryNavComponent } from '../secondary-nav/secondary-nav.component';

@Component({
  selector: 'app-zone-detail',
  imports: [SecondaryNavComponent, RouterOutlet],
  templateUrl: './zone-detail.component.html',
  styleUrl: './zone-detail.component.css'
})
export class ZoneDetailComponent {
  zone!: ZoneResponse;

  constructor(private route: ActivatedRoute, private zoneService: ZoneService) {}

  ngOnInit() {
    const zoneId = this.route.snapshot.paramMap.get('id');
    if (zoneId) {
      this.zoneService.getZoneById(parseInt(zoneId, 10)).subscribe((data) => {
        this.zone = data;
      });
    }
  }
}
