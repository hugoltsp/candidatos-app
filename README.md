# candidaturas-app

# How to Run

### Run `mvn install` in order to build the whole project along with it's dependencies and then execute the following to fire up all the needed applications:

<em>java -jar eureka-server/target/eureka-server-0.0.1.jar</em>

<em>java -jar zuul-server/target/zuul-server-0.0.1.jar</em>

<em>java -jar vagas-api/target/vagas-api-0.0.1.jar</em>

<em>java -jar pessoas-api/target/pessoas-api-0.0.1.jar</em>

<em>java -jar candidaturas-api/target/candidaturas-api-0.0.1.jar</em>

### Alternatively you can simply use docker to pull and fire up the registry images through docker-compose :)

#### Example below:
<em>docker-compose pull && docker-compose up</em>

### Tools used
* Kubuntu 18.04 (4.15.0-39-generic)
* Apache Maven 3.6.0
* Java 8 (HotSpot 1.8.0_191)
* Docker version 18.06.1-ce
* docker-compose version 1.22.0
