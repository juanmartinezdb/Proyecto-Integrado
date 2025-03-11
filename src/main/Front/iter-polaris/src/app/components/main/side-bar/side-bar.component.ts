import { Component } from '@angular/core';
import { UserInfoComponent } from './user-info/user-info.component';
import { ZoneOrbsComponent } from './zone-orbs/zone-orbs.component';
import { NotificationComponent } from './notifications/notifications.component';


@Component({
  selector: 'app-side-bar',
  imports: [UserInfoComponent, ZoneOrbsComponent, NotificationComponent],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {}
