Refactoring

#### Prerequisite

Installed:   
[Docker](https://www.docker.com/)   
[git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git)    
[Docker-Compose](https://docs.docker.com/compose/install/)   
[Java 1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html)   
[Maven 3.x](https://maven.apache.org/install.html)

##### Clone source code from git
```
$  git clone https://github.com/dperezh/refactoring.git .
```

##### Build Maven project
```
$ mvn clean install .
```

##### Run Java project with Maven
```
$ mvn compile exec:java -Dexec.mainClass="org.exercise.App" -Dexec.cleanupDaemonThreads=false
```

## Run with docker-compose 

Build and start the container by running 

```
$ docker-compose up
```

##### Stop Docker Container:
```
$ docker-compose down
```