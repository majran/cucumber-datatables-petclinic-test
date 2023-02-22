Feature: Pets - using @DataTableType
  - table in 'pets' steps will result in List<Pet> using registered DataTableType
  - method responsible for conversion create Pet object but nested object of Owner is taken from db
  - Same conversion will be used in 'single pet' step, but at first data will be transposed (rows to columns) to get same structure as in 'pets' step


  Scenario: Add pets
    Given pets
      | Name  | Type   | DoB        | Owner          |
      | Zelda | dog    | 2023-01-01 | Betty Davis    |
      | Zelda | dog    | 2023-03-03 | Jeff Black     |
      | Maria | lizard | 2023-02-02 | Maria Escobito |
    And single pet
      | Name  | Emi        |
      | Type  | Dog        |
      | DoB   | 2010-01-01 |
      | Owner | Marcin Maj |

