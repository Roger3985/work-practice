<p align="center">
	<img width="70" height="70" src="spring_docker.png" alt="Spring boot">
    <img width="70" height="70" src="zk.png" alt="Spring boot">  
  <h1 align="center">Build Spring boot application with Docker and ZK Framework</h1>
</p>

[![Platform](https://img.shields.io/badge/Java-21%2B-red)](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)
[![Framework](https://img.shields.io/badge/Spring%20Boot-3.3.1-green)](https://spring.io/projects/spring-boot)
[![Framework](https://img.shields.io/badge/Spring%20Security-6.3.1-green)](https://spring.io/projects/spring-security)
[![Framework](https://img.shields.io/badge/MyBatis-3.0.0-red)](https://mybatis.org/mybatis-3/)
[![Framework](https://img.shields.io/badge/Swagger-3.0.0-green)](https://swagger.io/)
[![Framework](https://img.shields.io/badge/Docker-26.0.0-blue)](https://www.docker.com/)
[![Build Status](https://github.com/zkoss/zk/workflows/zk-build/badge.svg)](https://github.com/zkoss/zk/actions?query=workflow%3Azk-build) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.zkoss.zk/zk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.zkoss.zk/zk) [![Code Climate](https://codeclimate.com/github/zkoss/zk/badges/gpa.svg)](https://codeclimate.com/github/zkoss/zk)

## About Project 
This is a simple spring boot application with the basic details of docker and ZK Framework implementation.

## Repository contains 

## BackEnd 
  * Spring MVC source code.
  * Spring boot application source code.
  * Spring security source code.
  * MyBatis ORM.
  * Swagger 3.0 ui.
  * Docker Hub.

## FrontEnd 
  * Spring-Thymeleaf
  * [ZK](http://www.zkoss.org/)

## How to use the ZK Framework
  * [Tutorial](http://books.zkoss.org/wiki/ZK_Getting_Started/Tutorial)
  * [ZK Essentials](http://books.zkoss.org/wiki/ZK_Essentials)
  * [ZK Developer's Reference](http://books.zkoss.org/wiki/ZK_Developer%27s_Reference)
  * [Javadoc API](http://www.zkoss.org/javadoc/latest/zk/)
  * [More](http://books.zkoss.org)

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