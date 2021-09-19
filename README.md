# EquestrianClubService
Web application. It will provide user authentication and authorization. The aim for the app is to give basic CRUD operations done for web-users.
Application was written in Java + Spring boot and use Thymealf for representing views.

The fastest way to run application is downloading EquestrianClubService.jar file from DOWNLOAD_JAR_AND_RUN directory. 
You need to have preinstaled Java on your machine. Preferred version is 11, but everything should work with the version 1.8 and above.

Application is working on local machine. Port 8080. --> localhost:8080. H2 Database is used - it runs in <b>client-server mode</b>. 
Read more here: https://www.tutorialspoint.com/h2_database/h2_database_introduction.htm 

You do not need sql workbenches or data developer applications. <br>
You do not need to create connection with used database and change URLs so it should work on your local machine.  

<h2>Command to run application - jar file: </h2>

```
java -jar EquestrianClubService.jar
```
<hr>
<h2>Users to sign in:</h2>
- Clients:
   * bruc12
   * steve12
   * angela12
   * natala12
   * wika12
   * kacp12
   * kasia12
   * luki12
   * ania12
password: test123
<br>
- Employees:
   * wera12
   * marceg12
   * bruc12
password: test123
<br>
- Admin:
   * mark12
password: admin12
<hr>
<h2>To see the content of H2 database:</h2>

Go to database URL: http://localhost:8080/h2-console

Fill the box like below:

'Saved settings' and 'Setting name': Generic H2 (Embedded)<br>
'Driver class': org.h2.Driver <br>
JDBC URL: jdbc:h2:file:./data/database<br>
Credentials:<br>
User name: dev <br>
Password: dev <br>

<hr>
Thank you for researching my projects! 
