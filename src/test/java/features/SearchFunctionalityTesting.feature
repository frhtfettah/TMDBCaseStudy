Feature: Movies API Testing


  Scenario: Verify the 'search/movie' endpoint returns popular movies correctly
    When User sends a GET request to 'search/movie' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains accurate search results based on given query for movies

  Scenario: Verify the 'search/tv' endpoint returns a specific movie's information
    When User sends a GET request to the 'search/tv' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains accurate search results based on given query for tv shows

  Scenario: Verify the 'search/keyword' endpoint returns correct information
    When User sends a GET request to 'search/keyword' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains accurate search results based on given query for the keyword

  Scenario: Verify the 'search/collection' endpoint returns correct collection information
    When User sends a GET request to the 'search/collection' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains accurate search results based on given query for collections

  Scenario: Verify the 'search/person' endpoint returns correct person information
    When User sends a GET request to the 'search/person' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains accurate search results based on given query for people