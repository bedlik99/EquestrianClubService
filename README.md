# EquestrianClubService
Web application. It will provide user authentication and authorization. The aim for the app is to give basic CRUD operations done for web-users.
Application was written in Java + Spring boot and use Thymealf for representing views.

The fastest way to run application is downloading EquestrianClubService.jar file from DOWNLOAD_JAR_AND_RUN directory. 
You need to have preinstaled Java on your machine. Prefered version is 11 but everything should work with the version 1.8 and above.

Application is working on local machine. Port 8080. --> localhost:8080. H2 Database is used - it runs in <b>client-server mode</b>. 
Read more here: https://www.tutorialspoint.com/h2_database/h2_database_introduction.htm 

You do not need sql workbenches or data developer applications.
You do not need to create connection with used database and change URLs so it should work on your local machine.  

<h2>Command to run application - jar file: </h2>

```
java -jar EquestrianClubService.jar
```

To see the content of H2 database:

Go to database URL: http://localhost:8080/h2-console

Fill the box like below:

'Saved settings' and 'Setting name': Generic H2 (Embedded)
'Driver class': org.h2.Driver
JDBC URL: jdbc:h2:file:./data/database
Credentials:

User name: dev
Password: dev
<hr>
Thank you for researching my projects! 
