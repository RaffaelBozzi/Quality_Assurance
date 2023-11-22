/// <reference types="Cypress" />

describe('Xpath Funcionality', function(){
  it('Login Test using Conduit by Xpath locators', function(){
    //navigate to login page
    cy.visit('https://react-redux.realworld.io/')
    cy.xpath('//a[contains(text(),"Sign in")]').click()

    //login
    cy.xpath('//input[@placeholder="Email"]').type('riuzher@gmail.com')
    cy.xpath('//input[@placeholder="Password"]').type('automationtest')
    cy.xpath('//button[@type="submit"]').click()
  })
})