//aula 25 da playlist (https://www.youtube.com/watch?v=yQD8V-nsndQ&list=PLsjUcU8CQXGGV3LzJqIPgZCtoja2zohwo&index=25)
import { test } from '@playwright/test'

test('locator by css selector', async ({ page }) => {
    await page.goto("http://omayo.blogspot.com/");
    await page.locator('css=textarea[id=ta1]').fill("test text"); //pode ser abriviado ocultando "css=" e o "textarea", pode substituir "id=" por "#"
    await page.locator('textarea[id=ta1]').pressSequentially(" test without css=");
    await page.locator('id=ta1').pressSequentially(" test without textarea");
    await page.locator('#ta1').pressSequentially(" test without id=");
});

test('locator by xpath expressions', async ({ page }) => {
    await page.goto("http://omayo.blogspot.com/");
    await page.locator('xpath=//textarea[@id="ta1"]').fill("test text"); //pode ser abriviado ocultando "xpaht="
    await page.locator("//textarea[@id='ta1']").pressSequentially(" test without xpath=");
});

test('locator by text of element', async ({ page }) => {
    await page.goto("http://omayo.blogspot.com/");
    await page.locator('text=Dropdown').last().click(); //Dropdown neste caso não é case sensitive, ou seja, dropdown irá funcionar. Se o valor for colocado entre aspas passará a ser case sensitive ("text='Dropdown'")
});

test('locator with multiple matches', async ({ page }) => {
    await page.goto("http://omayo.blogspot.com/");
    await page.locator("#LinkList1 a").first().click();
    await page.goto("http://omayo.blogspot.com/");
    await page.locator("#LinkList1 a").nth(0).click(); //nth(0) é o mesmo que first
    await page.goto("http://omayo.blogspot.com/");
    await page.locator("#LinkList1 a").last().click();
});