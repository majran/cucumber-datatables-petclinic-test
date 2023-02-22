Feature: Visit - parameter type
  This feature shown how to use parameter types
  - based on first and last name pet owner is fetched from db with all details
  - based on type of pet and its name it is fetch from db
  - visit is created (via db - simplification) also using parameter type

  NOTE: context holds
  - owner
  - pet
  - visit

  NOTE: parameter types for owner, pet and visit uses regex groups

  On Then part it looks for visit (in db) matching added ones using objects from in context

  Scenario:
    Given Carlos Estaban pet owner
    And pet dog Lucky
    When add visit on 2023-02-14 for super injection
    Then visit is scheduled