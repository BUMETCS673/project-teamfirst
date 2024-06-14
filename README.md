# SpringAlerts - Group 1
This is a micorservice based on Springboots, performing user management and notification.

## Getting Started
### Dependencies
- Java 11 or higher
- Intellij or Esclip
- RabbitMQ server 
- Docker (optional, for running RabbitMQ)
### Installing
### Local running RabbitMQ(MacOS)
install RabbitMQ server with:
```
brew install rabbitmq
```
start a local RabbitMQ node:
```
brew services start rabbitmq
```
stop a running node:
```
brew services stop rabbitmq
```
### Running RabbitMQ with Docker

If you don't have RabbitMQ installed, you can run it using Docker:

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
Access the RabbitMQ management console at http://localhost:15672 with the default username and password (guest/guest)

### Clone repository
```
git clone https://github.com/BUMETCS673/project-teamfirst
```
User management service
```
cd metcs673-user-management-service
```
Notification service
```
cd metcs673-notification-service
```
open with intellij
```
code intellij.
```

### Build & run the application
1.Build the application
```
./gradlew build
```


## Iteration 1

# Details on RabbitMQ and Springboot set up.  
## In step 1, need to access docker desktop and import the RabbitMQ, which is done via a search and then use of terminal to load that image. 
See screenshots to ensure you choose the right RabbitMQ management tool. 
## There are 2 steps associated with the terminal, the first of which is to pull the appropriate RabbitMQ version, and the 2nd is to run the RabbitMQ version to get to the RabbitMQ log in.  
Once in RabbitMQ there are some quick exchange and queue set up and binding you can do to ensure you understand the relationships between Exchange and one, or many queues.  In our project I have set up 2 queues, one being for JSON messages.  
Many of the code that is used in this is from the Spring AMQP site, and there are screenshots too of how I have set up the message converters etc.  
At different stages of setting up Producer, Consumer classes, i simply tested the output and connection to RabbitMQ, and it was working.  The url is:  http://localhost:8080/api/v1/publish when I had completed the task.  At one point I simply sent message of "spring alert works" and that was a message that showed up in the Queue between Producer and consumer.  
## Iteration 2 
Both the Notification Service and User Management Service have now utilised CockRoadDB setups which will be required to get tokens etc to ensure that you can run the program.  We are building out unit tests including Integration tests, and have taken the view that we will write integration tests for positive outcomes not for all.  Integration testing is more complex and costly and therefore our teams aim is to achieve 70% or more of Unit tests.  We have not completed all of them as yet however will continue to build them out leading up to Iter3.  At this stage we have used @BeforeAll testing, and used a Test Instance Lifecycle test as it is suitable for our class.  
## Notes on how our part of Microservice works
Log-in page will be at Auth0
UI to talk with all backend services, this is the collaboration efforst that Jordany has done
You can search on both first name and last name, it only needs a few string items to be able to find user.  
Patch allows you to update a new user 
Post is to create a group. 

2.Run the application
```
./gradlew bootRun
```

### Testing - notification
Use API client like Postman to send a POST request to /send-notification endpoint:

URL: http://localhost:8081/send-notification
Method: POST
Body:
Key:"message"
Value:"Hello World"

Check the logs of your Spring Boot application to see the received notifications



### optional - Details on RabbitMQ and Springboot set up.  
#### In step 1, need to access docker desktop and import the RabbitMQ, which is done via a search and then use of terminal to load that image. 
See screenshots to ensure you choose the right RabbitMQ management tool. 
#### There are 2 steps associated with the terminal, the first of which is to pull the appropriate RabbitMQ version, and the 2nd is to run the RabbitMQ version to get to the RabbitMQ log in.  
Once in RabbitMQ there are some quick exchange and queue set up and binding you can do to ensure you understand the relationships between Exchange and one, or many queues.  In our project I have set up 2 queues, one being for JSON messages.  
Many of the code that is used in this is from the Spring AMQP site, and there are screenshots too of how I have set up the message converters etc.  
At different stages of setting up Producer, Consumer classes, i simply tested the output and connection to RabbitMQ, and it was working.  The url is:  http://localhost:8080/api/v1/publish when I had completed the task.  At one point I simply sent message of "spring alert works" and that was a message that showed up in the Queue between Producer and consumer.  


