/// <reference types="Cypress" />

describe('Advanced UI Elements - Alerts', function(){
 
  it('Simple alert', function(){
      cy.visit('https://the-internet.herokuapp.com/javascript_alerts')
      cy.contains('Click for JS Alert').click()
      cy.on('window:alert', (alertText) => { //when a alert occurs do the following
        expect(alertText).to.equal('I am a JS Alert')
      })
      cy.get('#result').should('contain', 'You successfully clicked an alert')
  })
   
  it('Confirmation alert - Ok Button', function(){
    cy.visit('https://the-internet.herokuapp.com/javascript_alerts')
    cy.contains('Click for JS Confirm').click()
    cy.on('window:confirm', (alertText) => { //when a confirm alert occurs do the following
      expect(alertText).to.equal('I am a JS Confirm')
    })
    cy.get('#result').should('contain', 'You clicked: Ok')
  })

  it('Confirmation alert - Cancel Button', function(){
    cy.visit('https://the-internet.herokuapp.com/javascript_alerts')
    cy.contains('Click for JS Confirm').click()
    cy.on('window:confirm', (alertText) => {
      expect(alertText).to.equal('I am a JS Confirm')
      return false //This "click" on cancel
    })
    cy.get('#result').should('contain', 'You clicked: Cancel')
  })

  it('Prompt Alert', function(){
    cy.visit('https://the-internet.herokuapp.com/javascript_alerts')
    cy.window().then(function($win) { //This takes the entire DOM and passes as a parameter on $win
      cy.stub($win,'prompt').returns('Hello Alert') //on a prompt alert it writes the text on returns(str)
      cy.contains('Click for JS Prompt').click() //click on element which prompts the alert
    })
    cy.get('#result').should('contain', 'You entered: Hello Alert')
  })

})