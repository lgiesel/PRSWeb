PRS = Purchase Request System application

PRS has two GitHub repositories - 
	1) Java/MySQL/SpringBoot backend called PRSWEB 	- https://github.com/lgiesel/PRSWeb.git
	2) Angular/HTML/CSS frontend called PRSNG 		- https://github.com/lgiesel/PRSNG.git

To run the PRS application:
1. In MySQL, run the SQL script - create_insert_into_tables_prs_db.sql in a MySQL database to create the tables and pre-populated data for the PRS application.
2. Open the PRSWEB Java application in Eclipse and run as a Spring Boot application.
3. Start the PRSNG by opening a GITBASH session in the root directory where you downloaded the PRSNG repository.
4. At the GITBASH command prompt, type: start ng serve -o , which opens A browser where you can log in to the application.



