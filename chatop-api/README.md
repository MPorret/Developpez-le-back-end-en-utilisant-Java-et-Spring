# ChâTop API

![ChâTop cover](/src/assets/1666686016025_P3_Banner_V2.png)

ChâTop API is a backend service for a rental platform where users can register, authenticate, and manage rental listings. The API provides secure authentication, CRUD operations for rentals, and a message system between users.

The API was generated with **Spring Boot 3**.

## Configuration

### Prerequisites

- **Java 17+**
- **Maven** (check with `mvn -version`)
- **MySQL**

### Create the database

SQL script for creating the schema is available: [/ressources/sql/script.sql](../ressources/sql/script.sql)

### Add the environment variables

In your IDE, add and custom the following environment variables:
```
APP_DB_HOST=127.0.0.1
APP_DB_PORT=3306
APP_DB_NAME=myDatabase
APP_DB_USER=root
APP_DB_PASSWORD=password
JWT_KEY=yourJwtKey
```

## Start the project

Git clone:
> git clone https://github.com/MPorret/Developpez-le-back-end-en-utilisant-Java-et-Spring

Go inside folder:
> cd  Developpez-le-back-end-en-utilisant-Java-et-Spring/chatop-api

Install dependencies:
> mvn clean install

Launch API:
> mvn spring-boot:run
 
Now, you can test the API with [Swagger](http://localhost:3001/swagger-ui/index.html#/), Postman or the [frontend of ChâTop](https://github.com/MPorret/Developpez-le-back-end-en-utilisant-Java-et-Spring).

## Routes documentation

The API is documented with Swagger.

Launch the API:
> mvn spring-boot:run

Access to the documentation: http://localhost:3001/swagger-ui/index.html#/

Some routes will need a token:
- Use the **register** route: /api/auth/register
- Copy the token you received
- Click on the **Authorize** button
- Paste the token

Now you can use all the routes.

### User's routes

| Method | Route              | Authentication required | Body parameters                                         | Response                                                                                        | Description                        |
|--------|--------------------|-------------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------------|------------------------------------|
| POST   | /api/auth/login    | ❌                       | {email: string,<br/>password: string}                   | {token: string}                                                                                 | Log an user                        |
| POST   | /api/auth/register | ❌                       | {name: string,<br/>email: string,<br/>password: string} | {token: string}                                                                                 | Register un new user               |
| GET    | /api/auth/me       | ✅                       | x                                                       | {id: integer,<br/>name: string,<br/>email: string,<br/>created_at: Date,<br/>updated_at: Date}  | Get information of the logged user |
| GET    | /api/user/{id}     | ✅                       | x                                                       | {id: integer,<br/>name: string,<br/>email: string,<br/>created_at: Date,<br/>updated_at: Date}  | Get information of an user         |

### Rental's routes
| Method | Route                 | Authentication required | Body parameters                                                                                                  | Response                                                                                                                                                                                     | Description              |
|--------|-----------------------|-------------------------|------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------|
| POST   | /api/rentals          | ✅                       | form-data: {name: string,<br/>description: string,<br/>picture: file,<br/> price: integer,<br/>surface: integer} | {message: string}                                                                                                                                                                            | Add a new rental         |
| GET    | /api/rentals/**{id}** | ✅                       | x                                                                                                                | {id: integer,<br/>name: string,<br/>description: string, <br/>picture: string,<br/>price: integer,<br/>surface:integer,<br/>owner_id: integer,<br/>created_at: Date,<br/>updated_at: Date}   | Get a specific rental    |
| PUT    | /api/rentals/**{id}** | ✅                       | {name: string,<br/>description: string,<br/> price: integer,<br/>surface: integer}                               | {message: string}                                                                                                                                                                            | Update a specific rental |
| GET    | /api/rentals          | ✅                       | x                                                                                                                | [{id: integer,<br/>name: string,<br/>description: string, <br/>picture: string,<br/>price: integer,<br/>surface:integer,<br/>owner_id: integer,<br/>created_at: Date,<br/>updated_at: Date}] | Get all rentals          |

### Message's route
| Method | Route         | Authentication required | Body parameters                                                 | Response          | Description                 |
|--------|---------------|-------------------------|-----------------------------------------------------------------|-------------------|-----------------------------|
| POST   | /api/messages | ✅                       | {message: string,<br/>user_id: integer,<br/>rental_id: integer} | {message: string} | Send a message for a rental |

## Security

- The API is protected with **Spring Security**.
- Authentication is made using **JWT tokens**.
- **Password encryption** is applied.
- Token expiration is set to **1 hour**.
- **Session management** is stateless.
- All routes require authentication except for login, register, and Swagger.
