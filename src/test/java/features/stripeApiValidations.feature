Feature: Validating Stripe Api 

@Regression
Scenario Outline: Verify if the customers get successfully created
			Given Add customer payload "<name>" "<email>"
			When  user calls "ADDCUSTOMERAPI" using "post" request
			Then 	the api calls get success with status code 200
			And   "object" in response body is "customer"  
			And 	verify using customerid that "<name>" is created using "GETCUSTOMERAPI" request
			And   delete created customer using customerid using "DELETE_CUSTOMER_API"

Examples:
				|name             | email                   |	
				|Mark Salvatore	  | marksalvatore@test.com  |      
				|Henry House   	  | henryhouse@test.com     |
				