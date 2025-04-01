
@E2E
Feature: Verify AutomationExcercies APIs 



@Get
Scenario: Verify GET request API

Given I send the Base URI with request spec builder

When I send the "GET" request with "GETRequest" endpoint

Then I verify the "GET" response and response code 200



@PostSearchProduct
Scenario: Verify POST request API

Given I send the Base URI with request spec builder

When I send the "POSTSearchProduct" request with "POSTSearchProduct" endpoint

Then I verify the "POSTSearchProduct" response and response code 200


@CreateAccount
Scenario: Verify POST request API

Given I send the Base URI with request spec builder

When I send the "POSTCeateAccount" request with "POSTCreateAccount" endpoint

Then I verify the "POSTCeateAccount" response and response code 200




@VerifyUserExist
Scenario: Verify POST request API

Given I send the Base URI with request spec builder

When I send the "VerifyUserExist" request with "VerifyUserExist" endpoint

Then I verify the "VerifyUserExist" response and response code 200


@updateUserDetails
Scenario: Verify PUT request API

Given I send the Base URI with request spec builder

When I send the "PUT" request with "PUTRequest" endpoint

Then I verify the "PUT" response and response code 200

@GetUserAccount
Scenario: Verify GET request API

Given I send the Base URI with request spec builder

When I send the "GETUserAccount" request with "GETUserAccount" endpoint

Then I verify the "GETUserAccount" response and response code 200


@DeleteRequest
Scenario: Verify DELETE request API

Given I send the Base URI with request spec builder

When I send the "DELETE" request with "DELETERequest" endpoint

Then I verify the "DELETE" response and response code 200

