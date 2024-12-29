Feature: Person API Testing


  Scenario: Verify the 'tv/popular' endpoint returns popular tv shows correctly
    When User sends a GET request to 'tv/popular' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains popular tv shows's name, genre and first air dates

  Scenario: Verify the 'tv/{tv_id}' endpoint returns a specific tv show's information
    Given User has a valid tv show id
    When User sends a GET request to the 'tv/{tv_id}' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains tv show's episode count, status, season air dates and season episode counts

  Scenario: Verify the 'trending/tv/week' endpoint returns correct tv shows for selected timeline
    When User sends a GET request to the 'trending/tv/week' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains tv show's name and first air dates