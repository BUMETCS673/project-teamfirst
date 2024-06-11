# Group 1 Project
This is the current repo for the notification service that Team 1 will be building in support of the general classroom management system that the class as a whole is building.
There are supporting documents describing the design work we have done so far.

## Set up Springboot
Use Spring initializr generate config. We choose Gradle,and add dependencies to generate a Springboot project. See build.gradle file for more details.
After opening the project in intellij, find the path and run command line to build project: 
```
./gradlew build
```
# Details on RabbitMQ and Springboot set up.  
## In step 1, need to access docker desktop and import the RabbitMQ, which is done via a search and then use of terminal to load that image. 
See screenshots to ensure you choose the right RabbitMQ management tool. 
## There are 2 steps associated with the terminal, the first of which is to pull the appropriate RabbitMQ version, and the 2nd is to run the RabbitMQ version to get to the RabbitMQ log in.  
Once in RabbitMQ there are some quick exchange and queue set up and binding you can do to ensure you understand the relationships between Exchange and one, or many queues.  In our project I have set up 2 queues, one being for JSON messages.  
Many of the code that is used in this is from the Spring AMQP site, and there are screenshots too of how I have set up the message converters etc.  
At different stages of setting up Producer, Consumer classes, i simply tested the output and connection to RabbitMQ, and it was working.  The url is:  http://localhost:8080/api/v1/publish when I had completed the task.  At one point I simply sent message of "spring alert works" and that was a message that showed up in the Queue between Producer and consumer.  

# Notification Service

This is a notification service built with Spring Boot and RabbitMQ. The service demonstrates the use of the Observer design pattern to send notifications via a RabbitMQ Fanout Exchange.

## Prerequisites

- Java 11 or higher
- RabbitMQ server
- Docker (optional, for running RabbitMQ)

## Getting Started

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
git clone https://github.com/yourusername/notification-service.git
cd notification-service
```

### Build & run the application
1.Build the application
```
./gradlew build
```
2.Run the application
```
./gradlew bootRun
```
### Testing
Use API client like Postman to send a POST request to /send-notification endpoint:

URL: http://localhost:8081/send-notification
Method: POST
Body:
Key:"message"
Value:"Hello World"

Check the logs of your Spring Boot application to see the received notifications
