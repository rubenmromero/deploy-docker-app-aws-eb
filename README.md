# Deploy-Docker-App-AWS

Custom CLI to deploy a dockerized Spring boot example application to AWS Elastic Beanstalk.

# Application

This folder contains the sample code for a Spring boot application. See the instructions below for how to deploy and run this application.

![ci-badge](https://storage.googleapis.com/nodejs-getting-started-tests-badges/1-tests.svg)

## Dependencies

* `PostgreSQL`: Version 9.6 or higher is required. For development environment a docker-compose is provided within this project.
* `Java 8`

## Run application

### Dev environment

1. Go to the application directory
2. Deploy BBDD dependencies: 

```
docker-compose up
```

3. Run the application:

```
./gradlew run
```

### Prod environment

1. Build the application: 

```
./gradlew build
```

This command will generated the following jar: `build/libs/helloworld-0.0.1-SNAPSHOT.jar`

2. Run the application: 

```
java -jar helloworld-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro
```

In both cases, application will run in the 8000 port.

Note: In deploy time a script initializing the Database will be executed, this script could take more than 3 minutes.

## REST API

This application provides a REST API for get users info.

### Get User

* **URL:**`/users/{userId}`
* **Method:**`GET`
* **Response:**  

```json
{"id":104,"name":"user104","surname":"surname104","type":3}
```

### Get Users by type

Gets the users of the given type. 

* **URL:**`/users?userType={userTypeId}` 
* **Method:**`GET`
* **Response:**  

```json
[
  {"id":23,"name":"user23","surname":"surname23","type":6},
  {"id":32,"name":"user32","surname":"surname32","type":6},
  {"id":38,"name":"user38","surname":"surname38","type":6}
]
```

Possible values of `userType` are between `1` and `10`.

#### Note:

Application and access logs will be generated in the following folder: `/var/log/helloworld` 

# Deployment

## Dependencies

* `Python 3.3+`
* `boto3`

## Prerequisites

* AWS IAM EC2 Role (only for executions from EC2 instances) or IAM User with the following associated IAM managed policy:

      AWSElasticBeanstalkFullAccess

* AWS SDK for Python. Installation:

      $ pip3 install boto3

## Execution Method

Here you have the message that you will get if you request help to the `deploy.py` custom CLI:

    $ ./deploy.py --help
    usage: deploy.py [-h] -a APPLICATION_NAME -e ENVIRONMENT_NAME

    Custom CLI to deploy an application to AWS Elastic Beanstalk

    optional arguments:
      -h, --help            show this help message and exit

    Options:
      -a APPLICATION_NAME, --application-name APPLICATION_NAME
                            Name of the Elastic Beanstalk application
      -e ENVIRONMENT_NAME, --environment-name ENVIRONMENT_NAME
                            Name of the deployment environment for the Elastic
                            Beanstalk application

## Execution Example

    $ ./deploy.py --application-name helloworld --environment-name aws
