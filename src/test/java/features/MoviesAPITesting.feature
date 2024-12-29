Feature: Movies API Testing


  Scenario: Verify the 'movie/popular' endpoint returns popular movies correctly
    When User sends a GET request to 'movie/popular' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains popular movie's titles, descriptions and ratings

  Scenario: Verify the 'movie/{movie_id}' endpoint returns a specific movie's information
    Given User has a valid movie id
    When User sends a GET request to the 'movie/{movie_id}' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains movie title, genre, release_date and imdb_id