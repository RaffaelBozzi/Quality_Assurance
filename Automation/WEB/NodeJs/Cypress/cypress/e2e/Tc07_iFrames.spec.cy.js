/// <reference types="Cypress" />

describe('Advanced UI Elements - iFrame', function(){
  it('Typing in iFrames', function(){
    cy.visit('https://the-internet.herokuapp.com/iframe')
    cy.get('#mce_0_ifr').within(($iframe) => { //get on the iframe id
      const frame = $iframe.contents().find('#tinymce') // uses JQuery on the iframe element to reach the box 
      cy.wrap(frame).clear().type("Hello World")  //clear the default text and then fill it
    })
  })

})