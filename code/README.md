# User Nanagement Service

This is a user management service built with Spring Boots. The service performs basic user login and signup function for user management
details check video demo here https://drive.google.com/drive/u/1/folders/1lkzOG85NXhcNLix6EMQJAqCiLuxa138w

## Prerequisites

- Java 11 or higher
- MySQL database, MySQL workbench
- Intellij

### Clone repository
```
git clone https://github.com/BUMETCS673/project-teamfirst/tree/iteration1
cd code
cd metcs673-user-management-service

```

## Getting Started

### database link

download the project and open application.yml file located in code/metcs673-user-management-service/src/main/resources
```

update database username and password in the file with your local info
```
go to code/metcs673-user-management-service/src/main/resources/db/migration, use the code inside to create table in MySQL workbench
```
click build the project and run 
```
Access the user management console at http://localhost:9982/swagger-ui/index.html



# Notification Service

This is a notification service built with Spring Boot and RabbitMQ. The service demonstrates the use of the Observer design pattern to send notifications via a RabbitMQ Fanout Exchange.

## Prerequisites

- Java 11 or higher
- RabbitMQ server
- Docker (optional, for running RabbitMQ)

## Getting Started

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

