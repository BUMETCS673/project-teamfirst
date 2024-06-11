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
