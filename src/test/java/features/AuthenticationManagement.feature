Feature: Authentication and Account Management


  Scenario: Verify the 'authentication' endpoint works correctly with valid API Key
    Given User has a valid API Key/Token
    When User sends a GET request to 'authentication' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct

  Scenario: Verify the 'account/{account_id}/favorite/movies' endpoint returns user's favorite movies correctly
    Given User has a valid SessionID
    When User sends a GET request to the 'account/{account_id}/favorite/movies' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'Deadpool & Wolverine', 'Inside Out 2', 'Venom: The Last Dance' and 'Anna and the King' as favorited movie titles

  Scenario: Verify the 'account/{account_id}/rated/movies' endpoint returns user's rated movies correctly
    Given User has a valid SessionID
    When User sends a GET request to 'account/{account_id}/rated/movies' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The Shawshank Redemption' as rated movie title

  Scenario: Verify the 'account/{account_id}/lists' endpoint works correctly with valid API Key
    Given User has a valid API Key/Token
    When User sends a GET request to 'account/{account_id}/lists' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'testtvshows' and 'testlist' as list names