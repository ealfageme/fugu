import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateRestaurantComponent } from './private-restaurant.component';

describe('PrivateRestaurantComponent', () => {
  let component: PrivateRestaurantComponent;
  let fixture: ComponentFixture<PrivateRestaurantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivateRestaurantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
