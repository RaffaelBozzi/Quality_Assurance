//aulas 26 e 27 da playlist (https://www.youtube.com/watch?v=yQD8V-nsndQ&list=PLsjUcU8CQXGGV3LzJqIPgZCtoja2zohwo&index=26)
import {test} from "@playwright/test";

test("filtered locators", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.getByPlaceholder("Username").fill("standard_user");
    await page.getByPlaceholder("Password").fill("secret_sauce");
    await page.locator("#login-button").click();

    await page.locator(".inventory_item")
            .filter({hasText: "Sauce Labs Backpack"})
            .getByRole("button", {name: "Add to cart"})
            .click();

});

test("working with iframes - basics", async ({page}) => {
    await page.goto("https://letcode.in/frame");
    
    await page.frameLocator("#firstFr")
        .getByPlaceholder("Enter name")
        .fill("test placeholder");
    
    await page.frameLocator("#firstFr")
        .frameLocator("[title='Inner Frame']")
        .getByPlaceholder("Enter email")
        .fill("test@placeholder.com");
});

test("locator options", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.locator(".form_group", {has: page.locator("#user-name")}).click();
    await page.locator(".form_group", {has: page.locator("#user-name")}).pressSequentially("standard_user");

    await page.locator(".form_group", {hasNot: page.locator("#user-name")}).click();
    await page.locator(".form_group", {hasNot: page.locator("#user-name")}).pressSequentially("secret_sauce");

    await page.locator("#login-button").click();
    await page.locator(".inventory_item_name", {hasText: "Sauce Labs Backpack"}).click();
    await page.goBack();
    await page.locator(".inventory_item_name", {hasNotText: /Sauce.*/}).click();
});

test("combining locators", async ({page}) => {
    await page.goto("https://tutorialsninja.com/demo/");
    await page.locator("//span[text()='My Account']").click();
    await page.locator("//a[text()='Login']").click();
    await page.locator("form").locator("#input-email").fill("a@b.com");
});