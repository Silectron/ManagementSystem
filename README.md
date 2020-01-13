# Management System

To run the backend in Spring Tools or Spring Boot Eclipse:

Initial setup:
  In managementsystem package under src/main/java,
  1. right click ManagementSystemApplication
  2. run as -> run configurations -> environment
  3. add new variable "SPRING_DATASOURCE_URL" with value "jdbc:postgresql://ec2-184-72-223-163.compute-1.amazonaws.com:5432/dfq7m6lno5c6oj?&user=qurdljedqyudsv&password=c55ad9c5202b78a34e23d231d0784462d6285d6d95e36719b9874510a5ab7266&sslmode=require" (both without "")
  
To start the application:
  4.Run ManagementSystemApplication as Spring Boot app

The application should run on port 8080

If errors arrise,
It might be necessary to add the same variable and value to each of the test classes following the same steps as above 
