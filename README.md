# CRM API Test

Example REST API project.

## Project structure

This project is divided in this modules:

- **domain**: contains the business entities (user and customer), the repository definitions and the unit test that cover the basic business rules.
- **application**: contains the use cases of the application for all the operations related to the entities, including unit tests that check the application business rules.
- **persistence**: implements repositories declared in the domain module using Hibernate as ORM and JPA provider and PostgreSQL as the database.
- **rest-api**: implements the REST API endpoints for all the user and customer operations. The application uses a series of Spring libraries for the controller implementations and OAuth2 authentication control.

## Tools

The tools used in this project are:

- Java 1.8
- Maven 3.6.0
- Spring Boot 2.1.4
- Hibernate 5.4.2
- PostgreSQL 11
- Docker / Docker Compose

## API endpoints

The REST endpoints for 

- User operations
  - `GET /users:` gets all the users
  - `GET /users/{id}`: get the user with the given id
  - `POST /users`: creates a user
  - `PUT /users/{id}`: updates the user with the given id
  - `DELETE /users/{id}`: deletes the user with the given id

- Customer operations
  - `GET /customers`: gets all the customers
  - `GET /customers/{id}`: get the customer with the given id
  - `POST /customers`: creates a customer
  - `PUT /customers/{id}`: updates the customer with the given id
  - `DELETE /customers/{id}`: deletes the customer with the given id
  - `GET /customers/{id}/photo`: gets the photo of the customer with the given id
  - `POST /customers/{id}/photo`: updates the photo of the customer with the given id

Examples of input and output of all the operations:

| OPERATION    | INPUT | OUTPUT                                |
|:-------------|:------|:--------------------------------------|
| `GET /users`   | | `[{"id": 1, "name": "name", "admin": true},...]` |
| `GET /users/1` | | `{"id": 1, "name": "name", "admin": true}`       |
| `POST /users` | `{"name": "name", "admin": true}` | `{"id": 1, "name": "name", "admin": true}` |
| `PUT /users/1` | `{"name": "name", "admin": true}` | `{"id": 1, "name": "name", "admin": true}` |
| `DELETE /users/1` | | HTTP Status: OK |
| `GET /customers`   | | `[{"id": 1, "name": "name", "surname": "surname"},...]` |
| `GET /customers/1` | | `{"id": 1, "name": "name", "surname": "surname", "createdBy": "user@gmail.com", "updatedBy": null, "photoUrl": "/crmtestapi/customers/1/photo" }` |
| `POST /customers` | `{"name": "name", "surname": "surname"}` | `{"id": 1, "name": "name", "surname": "surname"}` |
| `PUT /customers/1` | `{"name": "name", "surname": true}` | `{"id": 1, "name": "name", "surname": surname}` |
| `DELETE /customers/1` | | HTTP Status: OK |
| `GET /customers/1/photo `| | Image content
| `POST /customers/1/photo` | HTTP request with type=`multipart/form-data`, file content in `file` parameter | HTTP Status: OK

## Authentication

This REST API uses OAuth2 authentication, using Google as the third-party provider. 
A user can only access the API if he is authenticated with Google and is registered 
on the application.

The parameters needed to obtain an access token are:

- Grant type: Authorization Code
- Callback URL: http://localhost:8080/crmapitest/callback
- Auth URL: https://accounts.google.com/o/oauth2/auth
- Token URL: https://accounts.google.com/o/oauth2/token
- Scope: openid profile email
- Client ID: obtained from the Google API Console, creating a new application.
- Client Secret: obtained also from the Google API Console.

## Compile and run

From the project root, compile the project with:
```
$ mvn clean install
```

This command will generate the Spring Boot JAR that can be used to run the application.

If you want to start the Docker compose containers, it's mandatory to firts define 
some environment variables that will be used by the PostgreSQL and application containers. 
This variables are:

- `POSTGRES_HOST`: host where the PostgreSQL database is running.
- `POSTGRES_DB`: database name.
- `POSTGRES_PORT`: database port.
- `POSTGRES_USER`: database user.
- `POSTGRES_PASSWORD`: database password.
- `SERVER_SERVLET_CONTEXT_PATH`: base context of the Spring Boog application.
- `GOOGLE_CLIENT_ID`: Google client ID for the OAuth2 authentication.
- `GOOGLE_CLIENT_SECRET`: Google client secret for the OAuth2 authentication.
- `ADMIN_USER`: GMail email adddress of the admin user that will be created when the 
  container first starts.

A example `.env` file is in the `docker` subdirectory. You can use this as a start point 
copying it from the docker directory to the project root directory and modifying the 
desired variables.

After compiling the application, if you want to run a PostgreSQL Docker container and a custom imagen that starts the 
Spring Boot application, run this:

```
$ docker-compose up `
```