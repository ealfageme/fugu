import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicClientComponent } from './public-client.component';

describe('PublicClientComponent', () => {
  let component: PublicClientComponent;
  let fixture: ComponentFixture<PublicClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublicClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
