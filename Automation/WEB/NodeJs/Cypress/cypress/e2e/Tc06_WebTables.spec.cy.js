/// <reference types="Cypress" />

describe('Advanced UI Elements - Web Tables', function(){
  it('Value presented anywhere in the table', function(){
    cy.visit('https://the-internet.herokuapp.com/tables')
    cy.get('#table1').contains('td', 'http://www.jdoe.com').should('be.visible')
  })

  it('Value presented in a specific position', function(){
    cy.visit('https://the-internet.herokuapp.com/tables')
    cy.get('#table1>tbody>tr:nth-child(1)>td:nth-child(3)').contains('td', 'jsmith@gmail.com').should('be.visible')

    let row = 1, col = 3
    cy.get(`#table1>tbody>tr:nth-child(${row})>td:nth-child(${col})`).contains('td', 'jsmith@gmail.com').should('be.visible')
  })

  it('Check value on a condition', function(){
    cy.visit('https://the-internet.herokuapp.com/tables')
    cy.get('#table1>tbody>tr>td:nth-child(2)').each(($ele, index, $list) => {
      const fname = $ele.text()
      if(fname.includes('Frank')) {
        cy.get('#table1>tbody>tr>td:nth-child(4)').eq(index).then(($due) => {
          const dueAmount = $due.text()
          expect(dueAmount).to.equal('$51.00')
        })
      }
    })
  })
})