# AutoService :car: 

AutoService is a pet web application built using Spring Boot framework. It allows users to send HTTP requests (GET, PUT, POST) and work with DTO objects using Swagger. The app was implemented following SOLID and OOP principles. It was developed using the CRUD methodology

## Project Structure 📁

AutoService follows a standard project structure for Spring-based applications using PostresSQL. Here is overview of the structure:

- `java`: This directory contains all the Java code of the application.

    - `autoservice`:  Main package.

    - `controller`: Controller classes that handle the incoming requests.

    - `repository`: Interfaces that provide work with database.

    - `dto`: Data transfer objects for working with users.

    - `mapper`: Classes for mapping DTO to model and via reverse.

    - `model`: Entity classes used in the application.

    - `service`: Service classes that provide business logic.

- `resources`: This directory contains the `application.properties` file, that has the database, ports, swagger configuration.

### Current project functionality:
Here is the list of all endpoints and their short description:

#### CarController:
- POST: /cars - create new car.
- PUT: /cars/{id} - update car by ID.

#### CarOwnerController:
- POST: /owners - create new owner.
- PUT: /owners/{id} - update owner by ID.
- GET: /owners/{id} - returns orders that belong to car owner by ID.

#### MasterController:
- POST: /masters - create new master.
- PUT: /masters/{id} - update master by ID.
- GET: /masters/{id} - returns orders that belong to master by ID.
- GET: /masters/{id}/salary - calculate and return master salary by ID.

#### OrderController:
- POST: /orders - create new order.
- POST: /orders/{id} - add products to order by ID.
- PUT: /orders/{id} - update order by ID.
- PUT: /orders/{id}/status - change status of order by ID.
- GET: /orders/{id}/price - calculate total order price by ID.

#### ProductController:
- POST: /products - create new product.
- PUT: /products/{id} - update product by ID.

#### WorkController:
- POST: /works - create new work.
- PUT: /works/{id} - update work by ID.
- PUT: /works/{id}/status - change status of work by ID.

## Technologies & Patterns :gear:
AutoService was built using the following technologies and patterns:

- Java 17, PostgresSQL, Maven
- Spring Boot
- Swagger
- OOP, SOLID

## Installation 💻

Here are the steps to run this app locally:

1. Clone this repository and open it locally.
2. Add all the required configurations to application.properties file(URL to your DB, username, password).
3. Run the app.
4. In browser open http:/localhost/8082/swagger-ui/#/
5. Connection established! 🎉

If you'd like to fork this project, feel free to use it in your own.