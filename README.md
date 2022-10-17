# My solution to the developer test

[Link to the test](https://github.com/lioncowlionant/developer-test)

## Technical stack

- Millennium Falcon onboard computer (back): Spring Boot
- C3P0 (Front): Angular
- R2D2 (CLI): Spring Boot

----

## How to build and start the application

### Back and CLI

#### Requirements:
- JDK 17+
- Maven (Optional, you can use Maven Wrapper instead using `./mvnw`)

#### Build them: 

Run `./mvwn clean package`

#### Start the backend server

Run `java -jar ./back/target/computer-0.0.1-SNAPSHOT.jar`

Note: You can custom path to the `millennium-falcon.json` by setting the following env variable `MILLENNIUM_FALCON_LOCATION`

The application will serve on port 8080

#### Run the CLI

Run `java -jar ./cli/target/cli-0.0.1-SNAPSHOT.jar  path-to-millennium-falcon.json path-to-empire.json`


### Front

#### Requirements:

- NodeJS 14.15.x/^16.10.x or later minor version
- Angular CLI v14

#### Run the application

In order to run the application you might first download the dependencies:

```
cd front
npm install
```

then you can simply run `ng serve` and access it [in local on port 4200](http://localhost:4200)

The back must be running on local on port 8080

You can then drag and drop the empire.json file to C3P0 or give him the file location
