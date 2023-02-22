Feature: Owners - Default Data Table
This feature shows how to
  - convert data table to java list of objects using default transformer with ObjectMapper
  - use those objects to pass data to Page Objects for fill form on web page
  - use those object to assert changes on system (via db) by simply asserting equality

  Scenario: Add and verify owner
    Given Doctor Vet Qualified
    When add pet owners
      | First Name | Last Name | Address           | City   | Telephone |
      | Marcin     | Maj       | Some Street 12    | Gdansk | 112       |
      | Zenon      | Mia       | Another Street 12 | Gdansk | 998       |
      | Barbara    | Woof      | Yet Another 12    | Gdansk | 997       |
    Then owner of pet are added to system