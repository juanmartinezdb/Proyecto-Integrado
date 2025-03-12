import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneCreateComponent } from './zone-create.component';

describe('ZoneCreateComponent', () => {
  let component: ZoneCreateComponent;
  let fixture: ComponentFixture<ZoneCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZoneCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
