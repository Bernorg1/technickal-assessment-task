# Support Java Developer - Technical Assessment Task

A simple REST API for a product catalog management system, built as a technical assessment. The API allows managing products from multiple producers, where each product can have a varying dynamic number of attributes.

## 🚀 Technology Stack
* **Java:** 21
* **Framework:** Spring Boot 3.5.13
* **Database Management:** Liquibase
* **Database:** H2 (In-memory)
* **Build Tool:** Maven

## 🛠 Setup & Running the Application

1. Make sure you have **Java 21** installed.
2. Clone this repository.
3. Open your terminal in the project root directory.
4. Run the application using the Maven wrapper:
   
   **On Windows:**
   ```cmd
   mvnw.cmd spring-boot:run
   ```
   **On Linux/Mac:**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## 🗄 Database Configuration (H2 Console)
The application uses an in-memory H2 database. Liquibase migrations will automatically create the required schema on startup.
You can access the H2 console via your browser:
* **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **JDBC URL:** `jdbc:h2:mem:task_db`
* **Username:** `sa`
* **Password:** *(leave blank)*

## 📡 API Endpoints

### Products Endpoints
* `GET /products` - Retrieve all products and their dynamic attributes
* `POST /products` - Create a new product along with its attributes
* `PUT /products/{id}` - Update existing product and its attributes
* `DELETE /products/{id}` - Delete a product

### Producers Endpoints (Bonus Feature)
* `GET /producers` - Retrieve all producers
* `POST /producers` - Add a new producer

