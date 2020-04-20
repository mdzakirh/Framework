/// <reference types="Cypress" />

// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

import config from '../config/config';

const ENTER = '{enter}';
const $ = Cypress.$;

Cypress.Commands.add('clearAndType', { prevSubject: 'element' }, (subject, text, options) => {
    subject
        .clear(options)
        .type(text, options);

    return cy;
});

Cypress.Commands.add('Login', (user, subUrl) => {

    cy.visit(config.appUrl + (subUrl || ""));

    cy.get('#Username')
        .type(user.UserName);

    cy.get('#Password')
        .type(user.Password)
        .type(ENTER);

    return cy;
});

Cypress.Commands.add('logout', () => {
    cy.get('.btn-profile-dropdown').click()
        .get('.nav-profile-links-dropdown li a[href*=Logout]').click()
        .get('#Username'); // waiting for the login page to appear

    return cy;
});



