import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GearDetailsComponent } from './gear-details.component';

describe('GearDetailsComponent', () => {
  let component: GearDetailsComponent;
  let fixture: ComponentFixture<GearDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GearDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GearDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
