## 在线教育商城

### Deployment Steps

Make sure `java` in `PATH` by checking `java -version` output.

- Start database.

```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_DB=course -e POSTGRES_USER=root -d postgres:12.4
```

- Initialize database.

```
./mvnw flyway:migrate
```

- Package and run (assuming database is running at `localhost:5432`, if you're using Windows docker, change it accordingly in `src/main/resources/application.properties`):

```
./mvnw package -DskipTests
java -jar target/course-0.0.1-SNAPSHOT.jar
```

Now access `localhost:8080`:

```
$ curl http://localhost:8080/api/v1/session
{"message":"Unauthorized"}    
```



