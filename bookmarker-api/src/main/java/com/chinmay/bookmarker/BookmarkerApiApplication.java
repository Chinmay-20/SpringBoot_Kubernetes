/*
part 11 in this go to root directory and run "docker-compose up" to create and run containers.
to run them in background "docker-compose up -d"
to see logs ""docker-compose up -d" then "docker-compose logs -f"
this commands assume you already have docker-compose.yml file. for other file name "docker-compose -f docker-compose-app.yml up -d"
to stop after "docker compose up -d" "docker-compose stop"
to stop and remove containers "docker-compose rm"
started containers with "docker-compose up -d", and you made some changes, to pick up those changes "docker-compose up -d --build"

we can also create two different docker files, one for dependencies like database, etc, and other for application.
with this approach i can get dependencies from docker and then run application in IDE

after splitting docker-compose.yml and docker-compose-app.yml
for multiple files "docker-compose -f docker-compose.yml -f docker-compose-app.yml up -d" then to see logs docker-compose -f docker-compose.yml -f docker-compose-app.yml logs -f

shell script to include this commands
*/

package com.chinmay.bookmarker;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmarkerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkerApiApplication.class, args);
	}

}
