import { FuguPage } from './app.po';

describe('fugu App', () => {
  let page: FuguPage;

  beforeEach(() => {
    page = new FuguPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
