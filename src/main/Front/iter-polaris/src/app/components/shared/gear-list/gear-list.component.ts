import { Component } from '@angular/core';
import { InventoryService } from '../../../services/inventory.service';
import { GearResponse } from '../../../models/inventory.model';

@Component({
  selector: 'app-gear-list',
  imports: [],
  templateUrl: './gear-list.component.html',
  styleUrl: './gear-list.component.css'
})
export class GearListComponent {
  gears: GearResponse[] = [];

  constructor(private inventoryService: InventoryService) {}

  ngOnInit() {
    this.inventoryService.getAvailableGears().subscribe((data) => {
      this.gears = data;
    });
  }

  addToInventory(gearId: number) {
    this.inventoryService.addGearToInventory(gearId).subscribe(() => {
      alert('Objeto añadido al inventario');
    });
  }
}
