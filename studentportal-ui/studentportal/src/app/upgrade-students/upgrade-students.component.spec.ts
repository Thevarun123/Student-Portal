import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeStudentsComponent } from './upgrade-students.component';

describe('UpgradeStudentsComponent', () => {
  let component: UpgradeStudentsComponent;
  let fixture: ComponentFixture<UpgradeStudentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpgradeStudentsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpgradeStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
