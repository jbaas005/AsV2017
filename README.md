# Project EWA (Enterprise Web Application)

## Team information

### Members

* Sayf Akhatou
* Mustafa Alemi
* Donavan Asmawidjaja
* Jesse Bass
* Robert Bakker
* Dennis Berkhof
* Ilyass Charkaoui

## Installation

### Requirements

1. Netbeans 7 or 8
2. Java runtime 7
3. Tomcat
4. MySQL 5+

### Database setup

Create a new database in MySQL called `stiho_complaints`

Edit file:

`\project\src\main\resources\application.properties`

To match your username and password (`db.username` and `db.password`)

### Running the project

Open the `project` folder in Netbeans. Expand `StihoKlachtenAfhandeling` and right-click on the `Dependencies` folder.
Press `Download Declared Dependencies` to download all dependencies used in the project.

Click `Run Project(F6)`. After compiling and deploying, a browser will automatically fire up to the correct URL.
If the browser is not firing, open up a browser and go to `http://localhost:8084/StihoComplaints`.

Next, go to `http://localhost:8084/StihoComplaints/dba` to fill the database with example data.

Default logins:

`manager@stiho.nl:1234`
`employee@stiho.nl:1234`

### Team blog

The link to our teamblog is:

[http://is204team1.wordpress.com/](http://is204team1.wordpress.com/)