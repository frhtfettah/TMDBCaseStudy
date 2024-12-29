Feature: Person API Testing


  Scenario: Verify the 'person/popular' endpoint returns popular people correctly
    When User sends a GET request to 'person/popular' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains popular people's name and known for movie title

  Scenario: Verify the 'person/{person_id}' endpoint returns a specific person's information
    Given User has a valid person id
    When User sends a GET request to the 'person/{person_id}' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains person's name, birthday, imdb_id, place of birth and biography