/// <reference types="Cypress" />

describe('Login Functionality', function(){
  it('Login Test using Conduit', function(){
    //navigate to login page
    cy.visit('https://react-redux.realworld.io/')
    cy.get('a[href="#login"]').click() //CSS Selector

    //login
    cy.get('input[placeholder="Email"]').type('riuzher@gmail.com')
    cy.get('input[placeholder="Password"]').type('automationtest')
    cy.get('button[type="submit"]').click()

    //navigate to logout
    cy.get('a[href="#settings"]').click()
    cy.get('.btn.btn-outline-danger').click() 
  })
})