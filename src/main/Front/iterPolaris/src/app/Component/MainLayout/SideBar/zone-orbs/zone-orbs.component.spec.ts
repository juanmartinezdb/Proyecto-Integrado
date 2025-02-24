import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneOrbsComponent } from './zone-orbs.component';

describe('ZoneOrbsComponent', () => {
  let component: ZoneOrbsComponent;
  let fixture: ComponentFixture<ZoneOrbsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneOrbsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneOrbsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
