import { test } from '@playwright/test';

test('locator by alt text', async ({ page }) => {
    await page.goto("https://selenium143.blogspot.com/");
    await page.getByAltText("Selenium143 ").click();    
});

test('locator by alt text exact match', async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/");
    await page.getByAltText("MacBook", { exact: true }).click();
})

test('locator by placeholder text', async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/index.php?route=account/register");
    await page.getByPlaceholder("First Name").fill("Test Name");
});

test("locator by text", async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/index.php?route=account/register");
    await page.getByText("Continue").click();
});

test("locator by label", async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/index.php?route=account/register");
    await page.getByLabel("E-mail").fill("email@test.com");
});

test("locator by label 2", async ({ page }) => {
    await page.goto("https://arunmotoori.github.io/m9.html");
    await page.getByLabel("Phone Number").fill("9876543210");
    await page.getByLabel("Subscribe to newsletter").click();
});

test("locator by role", async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/");
    await page.getByRole("textbox", { name: "Search" }).fill("HP");
    await page.getByRole("link", {name: "My Account"}).first().click();
    await page.getByRole("link", {name: "Login"}).click();
    await page.getByRole("textbox", {name: "E-Mail Address"}).fill("test@gmail.com");
    await page.getByRole("textbox", {name: "Password"}).fill("123456");
    await page.getByRole("button", {name: "Login"}).click();
});

test("locator by title", async ({ page }) => {
    await page.goto("https://tutorialsninja.com/demo/");
    await page.getByTitle("Shopping Cart").click()
});

test("locator by testId", async ({ page }) => {
    //o atributo buscado no html é, por padrão, o "data-testid". É possível mudar no arquivo de configuração "playwright.config.ts" o atributo buscado
    await page.goto("https://arunmotoori.github.io/m10.html");
    await page.getByTestId("fName").fill("Test Name"); //atributo data-testid
    // await page.getByTestId("lastName").fill("Test Last"); //atributo data-test
    // await page.getByTestId("emailAddress").fill("test@gmail.com"); //atributo data-qa
});