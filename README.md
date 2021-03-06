# myTaxy

Test on https://jsonplaceholder.typicode.com/

Project is wrote on Java using TestNg+Junit and Rest assured to test these stub services at the given page.
Please check myTaxyTest.xml to see the test configuration and parameters
Please check POM.xml to check for dependencies and configuration

To run it on maven please use: mvn clean test -Dsurefire.suiteXmlFiles=myTaxyTest.xml

<h2> Overview </h2>

Project is divided on 3 layers:
<ul>
<li>API: Those classes are intended to model all API related methods, parameters and the way to create the request and how to consume them. 
<li>Clients: Clients classes are intented to use those API and consume their services, perform actions, validations, and any data related operation over the API responses. These clients hide the API model from test, so they are not dependant on changes on them and allow me to use the API services in either the test or dataproviders methods.
<li>Test: Classes that contains the actual test. They just uses the clients to get the services responses in the required format and perform some assert on the operations (methods to validate something and return a boolean to the test assert). Note: there is an assertion also on API classes based only on response codes. If code is not the expected probably further assertion will fail and test is finished.
</ul>

Frameworks supports components like:
<ul>
<li>POJO: Java class to model the post object to either create or update a post.
<li>ExtentReportListener: To create a friendly user report of execution.
<li>JsonUtilities: Class created to operate search and extraction of data from the Json objects or list.
<li>EmailValidation: Just a class to perform the email validation.
</ul>

<h2> CI </h2>
https://circleci.com/gh/jarvcol/myTaxy
Project has some test on green (passed) and some on red (failed). Those red are related to failed test against the test site. Report on failures could be improved but had no more time (further improvement).
