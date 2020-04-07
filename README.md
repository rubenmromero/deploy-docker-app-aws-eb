# Deploy-Docker-App-AWS

Custom CLI to deploy a dockerized Spring boot application to AWS Elastic Beanstalk.

![AWS Elastic Beanstalk + Docker](aws_eb-docker.jpeg)

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

## How does it work?

The custom CLI deploys the Spring boot example application to AWS Elastic Beanstalk performing the following tasks:

1. Create a boto3 session to store the AWS credentials and the region to use for the deployment. If there is any configuration stored in `~/.aws` folder from AWS CLI, the AWS credentials and the region are directly retrieved from there (it is possible to specify the profile to use as optional argument). If there is no stored configuration in `~/.aws` folder, the custom CLI ask for these parameters interactively.
2. Check if the Elastic Beanstalk application is already created, and if not, create it.
3. Build the Spring boot example application.
4. Create the ZIP package (source bundle) for the new application version.
5. Upload the application version package to a source bundle S3 bucket.
6. Create the new application version for the Elastic Beanstalk application.
7. Create the deployment environment if it does not exist or has been previously terminated, or update it if it is already created. The CLI check if there is an operation in progress in the environment and wait for it to be completed before updating or recreating it.

## Dependencies

* `Python 3.3+`
* `boto3`

## Prerequisites

* The `aws-elasticbeanstalk-ec2-role` instance profile (AWS IAM EC2 Role) created in the AWS account:

    [Managing Elastic Beanstalk instance profiles](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/iam-instanceprofile.html)

* The `aws-elasticbeanstalk-service-role` service role (AWS IAM Elastic Beanstalk Role) created in the AWS account:

    [Managing Elastic Beanstalk service roles](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/iam-servicerole.html)

* An AWS IAM EC2 Role (only for executions from EC2 instances) or an IAM User with the following associated IAM managed policy:

      AWSElasticBeanstalkFullAccess

* Pip tool for Python packages management. Installation:

      # From Linux
      $ curl -O https://bootstrap.pypa.io/get-pip.py
      $ sudo python3 get-pip.py

      # From macOS
      $ brew install python

* AWS SDK for Python. Installation:

      # From Linux
      $ sudo pip3 install boto3

      # From macOS
      $ pip3 install boto3

## Execution Method

Here you have the message that you will get if you request help to the `deploy.py` custom CLI:

    $ ./deploy.py --help
    usage: deploy.py [-h] -a APPLICATION_NAME -e ENVIRONMENT_NAME [-p PROFILE]

    Custom CLI to deploy an application to AWS Elastic Beanstalk

    optional arguments:
      -h, --help            show this help message and exit

    Options:
      -a APPLICATION_NAME, --application-name APPLICATION_NAME
                            Name of the Elastic Beanstalk application
      -e ENVIRONMENT_NAME, --environment-name ENVIRONMENT_NAME
                            Name of the deployment environment for the Elastic
                            Beanstalk application
      -p PROFILE, --profile PROFILE
                            Use a specific profile from AWS CLI stored
                            configurations

## Provided Configuration Profiles

This project provides the following configuration profiles:

* `env.yaml.staging` => Configuration profile intended for staging environments deployed for development, test, QA or demo purposes (lower cost and performance)
* `env.yaml.production` => Configuration profile intended for environments with productive loads

## Execution Examples

    # Deployment of a staging environment
    $ ln -sf env.yaml.staging env.yaml
    $ ./deploy.py --application-name helloworld --environment-name test [--profile <aws_cli_profile>]

    # Deployment of a productive environment
    $ ln -sf env.yaml.production env.yaml
    $ ./deploy.py --application-name helloworld --environment-name live [--profile <aws_cli_profile>]

## Related Links

* [Managing Elastic Beanstalk instance profiles](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/iam-instanceprofile.html)
* [Managing Elastic Beanstalk service roles](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/iam-servicerole.html)
* [AWS SDK for Python (Boto3)](https://aws.amazon.com/sdk-for-python/)
* [Single Container Docker Configuration](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/single-container-docker-configuration.html)
* [Environment Manifest (env.yaml)](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/environment-cfg-manifest.html)
