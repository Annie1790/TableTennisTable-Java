Feature: League creation

  Scenario: Create a game
    Given A game is initialized and a league is created
    And A player "Bob" is added
    And A player "Charlie" is added
    When "Charlie" won a round against "Bob"
    Then "Charlie" is the winner
    And Print the league

    Scenario: Create an empty league
      Given A game is initialized and a league is created
      When the league has no players
      And Print the league
      Then I should see "No players yet"

