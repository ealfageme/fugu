import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicRestaurantComponent } from './public-restaurant.component';

describe('PublicRestaurantComponent', () => {
  let component: PublicRestaurantComponent;
  let fixture: ComponentFixture<PublicRestaurantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublicRestaurantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
