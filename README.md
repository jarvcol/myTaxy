# myTaxy

Test on https://jsonplaceholder.typicode.com/

Project is wrote on Java using TestNg+Junit and Rest assured to test these stub services at the given page.
Please check myTaxyTest.xml to see the test configuration and parameters
Please check POM.xml to check for dependencies and configuration

To run it on maven please use: mvn clean test -Dsurefire.suiteXmlFiles=myTaxyTest.xml

<h2> Overview <h2>

Project is divided on 3 layers:
API: Those classes are intended to model all API related methods, parameters and the way to create the request and how to consume them. 
Clients: Clients classes are intented to use those API to consume them and then perform actions, validations, and any data related operation over the API responses. These clients hide the API model from test, so they are not dependant on changes on them and allow me to use the API services in either the test or dataproviders
Test: Classes that contains the actual test. They just uses the clients to get the services responses in the required format and perform the assert on the operations (there is an assertion also on API classes based only on response codes. If code is not the expected probably further assertion will fail).

Frameworks supports components like:
POJO: Java class to model the post object to either create or update a post
ExtentReportListener: To create a friendly user report of execution
JsonUtilities: Class created to operate search and extraction of data from the Json objects or list
VerificationMethods: Class create to just hold any method specific validation over data. Most methods on it will return a true or false based on the element under validation.

