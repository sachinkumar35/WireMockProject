# WireMock

# What is wiremock

WireMock is a tool for mocking HTTP-based APIs that runs in the unit tests, on the desktop, or in the test environment.

WireMock is a simulator for HTTP-based APIs. Considered as a Service Virtualization tool or a Mock Server.
 
Enables to stay productive when an API you depend on
 
	> Doesn't exist Or
	> Isn't Complete
	> Costly to access
	
It supports testing of
	> Edge Cases
	> Failure modes

It is Fast so reduces build time significantly

#FEATURES

<b>1. Stubbing</b>:<br>
		It is a technique that allows configuring the HTTP response that is returned by the WireMock server when it receives a specific HTTP request. You can stub HTTP requests with WireMock by using the static givenThat() method of the WireMock class.

<b>2. Verification</b>:<br>
		The WireMock server registers all requests it receives in memory until it is reset. And that makes it possible to verify that a request matching a specific pattern was received, and also to fetch the requests’ details.

<b>3. Record-playback of interaction</b>:<br>  
		WireMock can create stub mappings from requests it has received. Combined with its proxying feature this allows you to “record” stub mappings from interaction with existing APIs.
		
<b>4. Injection of faults and delays:</b><br>
		One of the main reasons it’s beneficial to use web service fakes when testing is to inject faulty behavior that might be difficult to get the real service to produce on-demand. 

<b>5. Simulation of Stateful behaviour:</b><br>
		Most web services tend to have some state, which changes as you and others interact with them. So it’s pretty useful to be able to simulate this when you’ve swapped a real service for a test double.
		
<b>6. Can be used as:</b><br>

		JVM library in unit testing 
		Run as a standalone process either on the same host or remote server or on the cloud. 

7. All of WireMock’s features are easily accessible via its REST (JSON) interface and its’ Java API. 

# WireMock	-------------------       Mocking(Mockito)

	>Web server just like real api			No web server
	>Real http					No http
	>External to app code				It's in application code
	>Can simulate network faults			Can't 
	>Language agnostic				Language specific mocking libs

# What Is Wiremock?
In simple terms, Wiremock is a mocking setup for integration tests. It’s simply a mock server that is highly configurable to return an expected response for a given request.

It is widely used during development and more importantly during Integration testing while a system or service talks to one or multiple external or internal dependencies/services.

Let’s try to understand more about integration testing in general and get to know how Wiremock can help to get past those challenges and make our lives easier.

Generally, when the word integration comes, what strikes us is an end to end integration between 2 communicating systems. Now, let’s look at it from the perspective of an application under test that uses some external service to get the job done.

For Example, let’s suppose that we are building an application for online travel or ticketing system and we have a module for PNR status check, that hits an external API provided by Indian Railways.

Now, how can we integration test our application with the external APIs?

# There are 2 ways to do this:

<b>First,</b> is the Unit test approach, where we stub the interface that’s provided (or created in house) so that our system tests /validate the stubbed or fake response even before hitting the external API. This is nothing but a Unit test trying to mock an external dependency.<br>

<b>Second</b> is testing with some test environment (or the actual production environment) of the external dependencies. However, there are several challenges with that approach such mentioned below:

	->External API systems might not always be available. i.e. We are heavily reliant or dependent on external systems and any downtime there will impact our tests and indirectly the development/release process.

	->Secondly, external APIs might or might not have a test environment. For Example, a PNR status check API might always require real PNR numbers to fetch and return responses.

	->A lot of times, there are costs involved in hitting an API. For example, suppose PNR check API charges Rs 100 for every 1000 requests. As integration tests are usually run during every regression (and most of the time with every commit), it might not be a cost-effective solution to hit such an API that costs us even for testing purposes.

	->An external API can not be configured to return the desired response. Even if possible, you’ll have to create a lot of test data to ensure different responses for different request inputs.

	->For Example, you want to test error scenarios like an API is returning different status codes for different types of data. Now as the response is not under our control, we will need to create multiple sets of data to validate different possible scenarios or outcomes.
