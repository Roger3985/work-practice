<p align="center">
	<img width="70" height="70" src="spring_docker.png" alt="Spring boot">
  <h1 align="center">Build Spring boot application with Docker</h1>
</p>

[![Platform](https://img.shields.io/badge/Java-21%2B-red)](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)
[![Framework](https://img.shields.io/badge/Spring%20Boot-3.3.1-green)](https://spring.io/projects/spring-boot)
[![Framework](https://img.shields.io/badge/Spring%20Security-6.3.1-green)](https://spring.io/projects/spring-security)
[![Framework](https://img.shields.io/badge/MyBatis-3.0.0-red)](https://mybatis.org/mybatis-3/)
[![Framework](https://img.shields.io/badge/Swagger-3.0.0-green)](https://swagger.io/)
[![Framework](https://img.shields.io/badge/Docker-26.0.0-blue)](https://www.docker.com/)

## About Project 
This is a simple spring boot application with the basic details of docker implementation.

## Repository contains 

* Spring MVC source code.
* Spring boot application source code.
* Spring security source code.
* MyBatis ORM.
* Swagger 3.0 ui.
* Docker Hub.

## How it works using docker container 

* Create a simple spring boot application.
* Create a docker file under parent directory.
* Paste the following code in the dockerfile.

```
FROM amazoncorretto:21
WORKDIR /app
COPY target/work-practice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "SPRING_PROFILES_ACTIVE=dev","-jar", "app.jar"]
```

* Go to the spring boot application root directory open the cmd and create jar file using following command
First: ```mvn clean```
Second: ```mvn package```
Finally: The .jar file will create on the target package.
   ### Docker Commands
* Execute the following docker command to create the docker images.<br>
* ```docker buile -t <imagename> .```
  * Here "-t" is used fo mention the tag of container.
  * Our docker file is present in the same directory where we are executing this command. That is why we mention a dot as last of to commend
* To list the created docker images please use the following command. And make sure that our docker image is in the list.
  ```docker image ls``` or
  ```docker images```
* Now it is time to run the docker image. Use the following command to run the docker image.<b>
  ```docker run -p 8080:8080 <imageanme>```
  * Here java application will be running inside the docker container , so we need to expose the application running port to outside the container. "-p 8080:8080" is the command which expose the port to outside the docker container.
### Thanks :)