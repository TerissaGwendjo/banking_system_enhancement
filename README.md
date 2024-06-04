# Online Banking System: Advanced Transactions and User Interaction 
This project enhances an existing Online Banking System by incorporating advanced transaction management and user-friendly interfaces. It utilizes Spring annotations, Thymeleaf templates, and Bootstrap 5 for a secure and interactive user experience.


### Getting Started (Prerequisites)
- This project uses languages like:
- Java development
- Spring Framework (including Spring Data JPA)
- Thymeleaf templating engine
- Basic understanding of HTML and CSS

### Project Overview
The project focuses on:

- **Transaction Management:**
Implementing @Transactional annotations on service methods to ensure data integrity throughout financial operations.
Utilizing @Modifying annotations for efficient database updates during deposits and withdrawals.

**Service Layer Enhancements:**
Creating services(Account and User Services) with methods to handle:
- Creating new accounts
- Depositing funds
- Withdrawing funds
- Transferring funds between accounts
- Logging in users
- Signing up users
Ensuring all service methods leverage transactional security.

**Controller Updates:**
- Introducing endpoints in the controller class to manage user requests for each banking operation.
- Thymeleaf Template Modifications:
- Included in the Thymeleaf templates user-friendly forms for each banking operation (account creation, deposit, withdrawal, transfer, log in, sign up).
- Utilizing Bootstrap 5 for responsive and visually appealing forms and layouts.

**Secure User Interaction:**
- Implementing secure password hashing with industry-standard algorithms (e.g., bcrypt) to protect user credentials.
- Utilizing secure sessions and protection to prevent unauthorized access and attacks.
  
### Code Structure
- src/main/java - Contains Java source code for services, controllers, entities, and repositories.
- src/main/resources - Houses Thymeleaf templates and static resources (HTML,CSS).
- pom.xml - Defines project dependencies and configurations.
## Running the Application 
- Clone this repository.
- Install project dependencies (e.g., mvn clean install).
- Configure your database connection 
- Run the application using your preferred method (e.g., Spring Boot application class).
  
### Additional Notes:
Unit tests are encouraged to ensure code quality and functionality.
This README provides a comprehensive overview of the project's functionalities and structure. Feel free to explore the codebase for in-depth understanding.
