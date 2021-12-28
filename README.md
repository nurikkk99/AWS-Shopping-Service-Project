#AWS Shopping Service Project

Welcome to AWS Shopping Service Project! This project is designed to launch an online store system on AWS services. The project consists of two microservices, which are described below.

#Admin service
The admin service is designed to manage the objects of the online store (adding, deleting, updating the assortment). The service provides an entrance to the administrator account for the implementation of the above functions.

SWAGGER API for admin service described on **AdminServiceSwagger** at the root of project

#Customer-service
This service is designed to provide services for receiving, filtering goods. The service provides the ability to add goods to the basket / several baskets, as well as placing an order based on the goods in the basket.

SWAGGER API for customer service described on **CustomerServiceSwagger** at the root of project

#Interaction Scheme
![](readme/Schema.png)


#Getting started with AWS Shopping Service Project
- Install Java 11
  For Windows: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  Add bin files to environment variable PATH
  For UNIX: https://openjdk.java.net/install/
- Install git: https://git-scm.com/downloads
- Install Docker https://docs.docker.com/desktop/windows/install/
- Read about AWS services: S3, SNS, SQS, RDS, ElasticSearch, EC2 https://docs.aws.amazon.com/index.html?nc2=h_ql_doc_do

#How to run the project locally
After completing the previous steps:
1. Clone repository
2. Run services in docker containers via docker-compose:
- Run ***docker-compose up*** in AWS-Shopping-Service-Project folder. It will start RabbitMq.
- Run ***docker-compose up*** in admin-service folder. It will run localstack with S3 service, postgres and pgadmin in containers.
- Run ***docker-compose up*** in customer-service folder. It will run elasticsearch, postgres and pgadmin in containers.

The started services in docker desktop should look like this:

![](readme/Services.png)

Available addresses for interaction: 

| Services  | Addresses  |
| ------------- | -------------        |
| RabbitMq  | http://localhost:15672  |
| ElasticSearch  | http://localhost:9200  |
| PgAdmin Admin  | http://localhost:5050  |
| PgAdmin Customer  | http://localhost:5051  |
| Localstack(S3)  | http://localhost:4566  |

3. Set environment variables:
- spring.datasource.username=postgres;
- spring.datasource.password=admin;
- spring.profiles.active=local;
4. The project can be started!
