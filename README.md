# Management System

My first React web app

To run the backend in Spring Tools or Spring Boot Eclipse:

Initial setup:                        
  In managementsystem package under src/main/java,                        
  1. right click ManagementSystemApplication
  2. run as -> run configurations -> environment
  3. add new variable "SPRING_DATASOURCE_URL" 
  
To start the application:
  4.Run ManagementSystemApplication as Spring Boot app


If errors arrise,
It might be necessary to add the same variable and value to each of the test classes following the same steps as above 


Frontend setup:                                                 
  Possible issue with react-datepicker due to react-popper and reactstrap,                
  Fix:                                        
      After npm install                                                                 
      in ./node_modules/reactstrap/dist/reactstrap.es.js                                          
      1. remove "Arrow" and "Target" from react-popper import                                       
    
    
To be completed:
  Backend: Tests                                                                 
  Frontend: Integration of project assignment and lead in home page
