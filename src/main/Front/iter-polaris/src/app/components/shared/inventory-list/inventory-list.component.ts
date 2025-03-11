import { Component } from '@angular/core';
import { InventoryService } from '../../../services/inventory.service';
import { InventoryItemResponse } from '../../../models/inventory.model';

@Component({
  selector: 'app-inventory-list',
  imports: [],
  templateUrl: './inventory-list.component.html',
  styleUrl: './inventory-list.component.css'
})
export class InventoryListComponent {
  inventoryItems: InventoryItemResponse[] = [];

  constructor(private inventoryService: InventoryService) {}

  ngOnInit() {
    this.inventoryService.getInventory().subscribe((data) => {
      this.inventoryItems = data;
    });
  }
}
