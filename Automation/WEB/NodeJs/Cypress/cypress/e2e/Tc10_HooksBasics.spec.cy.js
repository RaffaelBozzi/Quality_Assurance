/// <reference types="Cypress" />

describe('Hooks Functionality', function(){
  before(function(){
    cy.log('Before block')
  })

  beforeEach(function(){
    cy.log('Before each block')
  })

  it('Search item', function(){
    cy.log('This is Search item block')
  })

  it('Order item', function(){
    cy.log('This is Order item block')
  })

  it('Checkout item', function(){
    cy.log('This is Checkout item block')
  })

  afterEach(function(){
    cy.log('After each block')
  })

  after(function(){
    cy.log('After block')
  })

})