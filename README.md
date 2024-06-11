# Group 1 Project
This is the current repo for the notification service that Team 1 will be building in support of the general classroom management system that the class as a whole is building.
There are supporting documents describing the design work we have done so far.

## Set up Springboot
Use Spring initializr generate config. We choose Gradle,and add dependencies to generate a Springboot project. See build.gradle file for more details.
After opening the project in intellij, find the path and run command line to build project: 
```
./gradlew build
```
## Iteration 2 
Both the Notification Service and User Management Service have now utilised CockRoadDB setups which will be required to get tokens etc to ensure that you can run the program.  We are building out unit tests including Integration tests, and have taken the view that we will write integration tests for positive outcomes not for all.  Integration testing is more complex and costly and therefore our teams aim is to achieve 70% or more of Unit tests.  We have not completed all of them as yet however will continue to build them out leading up to Iter3.  At this stage we have used @BeforeAll testing, and used a Test Instance Lifecycle test as it is suitable for our class.  
## Notes on how our part of Microservice works
Log-in page will be at Auth0
UI to talk with all backend services, this is the collaboration efforst that Jordany has done
You can search on both first name and last name, it only needs a few string items to be able to find user.  
Patch allows you to update a new user 
Post is to create a group. 
