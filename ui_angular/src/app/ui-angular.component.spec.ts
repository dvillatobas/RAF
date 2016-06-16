import {
  beforeEachProviders,
  describe,
  expect,
  it,
  inject
} from '@angular/core/testing';
import { UiAngularAppComponent } from '../app/ui-angular.component';

beforeEachProviders(() => [UiAngularAppComponent]);

describe('App: UiAngular', () => {
  it('should create the app',
      inject([UiAngularAppComponent], (app: UiAngularAppComponent) => {
    expect(app).toBeTruthy();
  }));

  it('should have as title \'ui-angular works!\'',
      inject([UiAngularAppComponent], (app: UiAngularAppComponent) => {
    expect(app.title).toEqual('ui-angular works!');
  }));
});
