Feature: Error Handling Cases


  Scenario: Verify the 'authentication' endpoint returns 401 Unauthorized when requested with an invalid token
    Given User has an invalid API Key/Token
    When User sends a GET request to 'authentication' endpoint
    Then Response status code should be 401 Unauthorized
    And Response body should contain 'Invalid API key' as an error message

  Scenario: Verify the 'authentication' endpoint returns 401 Unauthorized when requested with an empty token
    Given User does not have an API Key/Token
    When User sends a GET request to 'authentication' endpoint
    Then Response status code should be 401 Unauthorized
    And Response body should contain 'Invalid API key' as an error message

  Scenario: Verify the 'movie/{movie_id}' endpoint returns 404 Not Found when requested with an invalid movie id
    Given User has an invalid movie id
    When User sends a GET request to 'movie/{movie_id}' endpoint
    Then Response status code should be 404 Not Found
    And Response body should contain 'Invalid id' as an error message

  Scenario: Verify the 'trending/tv/month' endpoint returns 400 Bad Request when requested with wrong timeline
    When User sends a GET request to 'trending/tv/month' endpoint
    Then Response status code should be 400 Bad Request
    And Response body should contain 'Invalid parameters' as an error message

  Scenario: Verify the 'list' endpoint returns 401 Unauthorized when requested with an invalid token
    Given User has an invalid API Key/Token
    When User sends a POST request to 'list' endpoint
    Then Response status code should be 401 Unauthorized
    And Response body should contain 'Authentication failed' as an error message

  Scenario: Verify the 'list/{list_id}/add_item' endpoint returns 400 Bad Request when requested with an invalid body parameter
    Given User has an invalid body parameter
    When User sends a POST request to 'list/{list_id}/add_item' endpoint
    Then Response status code should be 400 Bad Request
    And Response body should contain 'Invalid parameters' as an error message

  Scenario: Verify the 'account/{account_id}/favorite' endpoint returns 400 Bad Request when requested with an invalid body parameter
    Given User has an invalid body parameter
    When User sends a POST request to 'account/{account_id}/favorite' endpoint
    Then Response status code should be 400 Bad Request
    And Response body should contain 'Invalid parameters' as an error message