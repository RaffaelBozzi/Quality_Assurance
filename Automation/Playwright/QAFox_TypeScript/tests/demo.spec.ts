import {chromium, test} from '@playwright/test';

test('Get started with Playwright test', async () => {
    const browser = await chromium.launch();
    const context = await browser.newContext();
    const page = await context.newPage();
    
    await page.goto("https://tutorialsninja.com/demo/");
    await page.locator("//span[text()='My Account']").click();
    await page.locator("//a[text()='Login']").click();
    await page.locator("#input-email").fill("amotooricap6@gmai.com");
    await page.locator("#input-password").fill("12345");
    await page.locator("//input[@value='Login']").click();
    await page.waitForTimeout(3000);

});