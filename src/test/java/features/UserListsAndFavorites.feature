Feature: User Lists and Favorites


  Scenario: Verify the 'list' endpoint creates a new list correctly
    Given User has a valid session id
    When User sends a POST request to 'list' endpoint
    Then Response status code should be 201 Created
    And Response body should be correct and contains a success message with created list's id

  Scenario: Verify the 'list/{list_id}/add_item' endpoint adds an item to a list
    Given User has a valid session id and list id
    When User sends a POST request to 'list/{list_id}/add_item' endpoint
    Then Response status code should be 201 Created
    And Response body should be correct and contains 'The item/record was updated successfully.' success message

  Scenario: Verify the 'list/{list_id}/item_status' endpoint returns the newly added item correctly
    Given User has a valid list id
    When User sends a GET request to 'list/{list_id}/item_status' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains the 'item_present' parameter with value 'true'

  Scenario: Verify the 'list/{list_id}/remove_item' endpoint removes an item from a specific list
    Given User has a valid session id and list id
    When User sends a POST request to 'list/{list_id}/remove_item' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message

  Scenario: Verify the 'list/{list_id}' endpoint removes the specified list when a DELETE request sent
    Given User has a valid session id and list id
    When User sends a DELETE request to 'list/{list_id}' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message

  Scenario: Verify the 'account/{account_id}/favorite' endpoint adds a tv show to the favorite list successfully
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/favorite' endpoint with 'media_type' body parameter as 'tv'
    Then Response status code should be 201 Created
    And Response body should be correct and contains 'Success.' success message

  Scenario: Verify the 'account/{account_id}/watchlist' endpoint adds a tv show to the watchlist successfully
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/watchlist' endpoint with 'media_type' body parameter as 'tv'
    Then Response status code should be 201 Created
    And Response body should be correct and contains 'Success.' success message

  Scenario: Verify the 'account/{account_id}/favorite/tv' endpoint returns the newly added tv show correctly from favorite list
    Given User has a valid session id and account id
    When User sends a GET request to 'account/{account_id}/favorite/tv' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains the 'Here's Lucy' which is the newly added item

  Scenario: Verify the 'account/{account_id}/watchlist/tv' endpoint returns the newly added tv show correctly from watchlist
    Given User has a valid session id and account id
    When User sends a GET request to 'account/{account_id}/watchlist/tv' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains the 'Here's Lucy' which is the newly added item

  Scenario: Verify the 'account/{account_id}/favorite' endpoint removes a tv show from user's favorite list
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/favorite' endpoint with 'favorite' body parameter as false and 'media_type' as 'tv'
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message

  Scenario: Verify the 'account/{account_id}/watchlist' endpoint removes a tv show from user's watchlist
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/watchlist' endpoint with 'watchlist' body parameter as false and 'media_type' as 'tv'
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message

  Scenario: Verify the 'account/{account_id}/favorite' endpoint adds a movie to the favorite list successfully
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/favorite' endpoint with 'media_type' body parameter as 'movie'
    Then Response status code should be 201 Created
    And Response body should be correct and contains 'Success.' success message

  Scenario: Verify the 'account/{account_id}/watchlist' endpoint adds a movie to the watchlist successfully
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/watchlist' endpoint with 'media_type' body parameter as 'movie'
    Then Response status code should be 201 Created
    And Response body should be correct and contains 'Success.' success message

  Scenario: Verify the 'account/{account_id}/favorite/tv' endpoint returns the newly added movie correctly from favorite list
    Given User has a valid session id and account id
    When User sends a GET request to 'account/{account_id}/favorite/movies' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains the 'The Substance' which is the newly added item

  Scenario: Verify the 'account/{account_id}/watchlist/tv' endpoint returns the newly added movie correctly from watchlist
    Given User has a valid session id and account id
    When User sends a GET request to 'account/{account_id}/watchlist/movie' endpoint
    Then Response status code should be 200 OK
    And Response body should be correct and contains the 'The Substance' which is the newly added item

  Scenario: Verify the 'account/{account_id}/favorite' endpoint removes a movie from user's favorite list
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/favorite' endpoint with 'favorite' body parameter as false and 'media_type' as 'movie'
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message

  Scenario: Verify the 'account/{account_id}/watchlist' endpoint removes a movie from user's watchlist
    Given User has a valid session id and account id
    When User sends a POST request to 'account/{account_id}/watchlist' endpoint with 'watchlist' body parameter as false and 'media_type' as 'movie'
    Then Response status code should be 200 OK
    And Response body should be correct and contains 'The item/record was deleted successfully.' success message