import { UiAngularPage } from './app.po';

describe('ui-angular App', function() {
  let page: UiAngularPage;

  beforeEach(() => {
    page = new UiAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('ui-angular works!');
  });
});
