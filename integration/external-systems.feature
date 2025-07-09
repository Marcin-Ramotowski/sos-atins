Feature: Tests for Books, Loans, and Payments

  Background:
    * url 'http://localhost:8080'

  Scenario: Get all books
    Given path '/books'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * print 'All books:', response

  Scenario: Get one book by ID
    Given path '/books/202'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * print 'Single book (ID 202):', response

  Scenario: Get all loans for student ID 1
    Given path '/students/1/loans'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * print 'Loans for student 1:', response

  Scenario: Get all payments
    Given path '/payments'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * print 'All payments:', response

  Scenario: Get all payments for student ID 1
    Given path '/students/1/payments'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * print 'Payments for student 1:', response

