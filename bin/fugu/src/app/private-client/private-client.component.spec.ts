import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateClientComponent } from './private-client.component';

describe('PrivateClientComponent', () => {
  let component: PrivateClientComponent;
  let fixture: ComponentFixture<PrivateClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivateClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
