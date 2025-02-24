import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventorySideComponent } from './inventory-side.component';

describe('InventorySideComponent', () => {
  let component: InventorySideComponent;
  let fixture: ComponentFixture<InventorySideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InventorySideComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InventorySideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
