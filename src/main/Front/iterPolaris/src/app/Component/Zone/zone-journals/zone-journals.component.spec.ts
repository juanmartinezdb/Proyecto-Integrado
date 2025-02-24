import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneJournalsComponent } from './zone-journals.component';

describe('ZoneJournalsComponent', () => {
  let component: ZoneJournalsComponent;
  let fixture: ComponentFixture<ZoneJournalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneJournalsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneJournalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
