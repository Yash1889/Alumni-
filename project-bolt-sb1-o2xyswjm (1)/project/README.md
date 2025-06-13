# Alumni Management System

A complete Spring Boot CRUD application for managing alumni data with PostgreSQL database integration.

## Features

- **Complete CRUD Operations**: Create, Read, Update, Delete alumni records
- **RESTful API**: All endpoints for API integration
- **Web Interface**: Thymeleaf-based user interface
- **Database Integration**: PostgreSQL with JPA/Hibernate
- **Validation**: Form validation with error handling
- **Responsive Design**: Bootstrap-based responsive UI
- **Search Functionality**: Search by name, company, and batch year

## Technologies Used

- **Backend**: Spring Boot 3.2.0, Spring Data JPA, Spring MVC
- **Database**: PostgreSQL
- **Frontend**: Thymeleaf, Bootstrap 5, Font Awesome
- **Build Tool**: Maven
- **Java Version**: 17

## API Endpoints

### REST API Endpoints

- `GET /api/alumni` - Get all alumni
- `GET /api/alumni/{id}` - Get alumni by ID
- `GET /api/alumni/year/{year}` - Get alumni by batch year
- `POST /api/alumni` - Create new alumni
- `PUT /api/alumni/{id}` - Update alumni
- `DELETE /api/alumni/{id}` - Delete alumni
- `GET /api/alumni/search/name/{name}` - Search alumni by name
- `GET /api/alumni/search/company/{company}` - Search alumni by company

### Web Interface Endpoints

- `GET /alumni` - List all alumni
- `GET /alumni/new` - Show form to add new alumni
- `GET /alumni/edit/{id}` - Show form to edit alumni
- `POST /alumni/save` - Save alumni (new or update)
- `GET /alumni/delete/{id}` - Delete alumni
- `GET /alumni/view/{id}` - View alumni details
- `GET /alumni/year/{year}` - Get alumni by batch year

## Database Setup

1. Install PostgreSQL on your system
2. Create a database named `alumni_db`
3. Update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/alumni_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## How to Run

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL installed and running

### Steps

1. **Clone/Import the project** into Spring Tool Suite as an existing Maven project

2. **Configure Database**:
   - Create PostgreSQL database `alumni_db`
   - Update `application.properties` with your database credentials

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the main class `AlumniManagementApplication` from your IDE

5. **Access the application**:
   - Web Interface: http://localhost:8080/alumni
   - API Base URL: http://localhost:8080/api/alumni

## Project Structure

```
src/
├── main/
│   ├── java/com/alumni/
│   │   ├── AlumniManagementApplication.java
│   │   ├── controller/
│   │   │   ├── AlumniRestController.java
│   │   │   ├── AlumniWebController.java
│   │   │   └── HomeController.java
│   │   ├── model/
│   │   │   └── Alumni.java
│   │   ├── repository/
│   │   │   └── AlumniRepository.java
│   │   └── service/
│   │       └── AlumniService.java
│   └── resources/
│       ├── application.properties
│       ├── data.sql
│       └── templates/
│           ├── layout.html
│           └── alumni/
│               ├── list.html
│               ├── form.html
│               └── view.html
└── test/
    └── java/com/alumni/
        ├── AlumniManagementApplicationTests.java
        └── service/
            └── AlumniServiceTest.java
```

## Sample Data

The application includes sample data in `src/main/resources/data.sql` that will be loaded automatically when you first run the application.

## Testing

Run tests using:

```bash
mvn test
```

## API Testing with Postman

You can test the REST API endpoints using Postman:

1. **GET** all alumni: `GET http://localhost:8080/api/alumni`
2. **GET** alumni by ID: `GET http://localhost:8080/api/alumni/1`
3. **POST** new alumni: `POST http://localhost:8080/api/alumni`
   ```json
   {
     "name": "John Doe",
     "batch": 2023,
     "company": "Example Corp",
     "contact": "john@example.com"
   }
   ```
4. **PUT** update alumni: `PUT http://localhost:8080/api/alumni/1`
5. **DELETE** alumni: `DELETE http://localhost:8080/api/alumni/1`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.