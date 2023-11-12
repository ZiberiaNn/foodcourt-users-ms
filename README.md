<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP USERS MICROSERVICE</h3>
  <p align="center">
    Users microservice for Pragma Power-Up challenge about designing the backend of a system that centralizes the services and orders of a restaurant chain that has different branches in the city.
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 11 [https://jdk.java.net/java-se-ri/11](https://jdk.java.net/java-se-ri/11)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone the repo
2. Change directory
   ```sh
   cd foodcourt_users-ms
   ```
3. Create a new database in MySQL called foodcourt_users
4. Update the database connection settings 
   ```yml
   # src/main/resources/application.yml   
   spring:
      datasource:
          url: jdbc:mysql://localhost/foodcourt_users
          username: root
          password: 1234
   ```
4. Run the Spring Boot application atleast once to create the database tables
5. Run the src/main/resources/init_users_ms.sql script in the foodcourt_users database to insert the initial data

<!-- USAGE -->
## Usage

1. Right-click the class PowerUpApplication and choose Run
2. Open [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) in your web browser

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage


