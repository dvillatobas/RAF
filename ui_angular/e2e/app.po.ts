export class UiAngularPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('ui-angular-app h1')).getText();
  }
}
