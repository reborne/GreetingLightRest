# GreetingLightRest
Simple REST API Spring Boot Application

##Overall:
This Application demonstrates how REST API works using Database Connectivity (Spring Data)
<br>Unit Tests are included.

#Project contents:
<ol>
<li>Spring Boot - Base of application that maintain application runtime</li>
<li>Spring Data - Module that allows to persist (save objects) to database (h2 in this scope)</li>
<li>H2 Database - Database that performs serialization/deserialization during in-memory execution</li>
</ol>

#Project requirements: 
<ol>
<li>Java 8 installed</li>
<li>Maven 3.0 (3.9 recomended)</li>
<li>Any IDE (optional)</li>
</ol>

#How to execute application
<ol>
<li>Clone project (green button "Clone or Download")</li>
<li>Extract to location by your will (e.g Desktop)</li>
<li>Open terminal and move to location where project located</li>
<li>run <code>mvn spring-boot:run</code></li>
<li>open browser and go <a href="http://localhost:8080/api/greetings">here</a> (http://localhost:8080/api/greetings) to see final result</li>
</ol>

<b>Note: </b>If everything done correctly, then the line with 2 id's will be return by service<br>

Example: <code>[{"id":1,"name":"Hello World!"},{"id":2,"name":"Hola Mundo!"}]</code>

#How to test application
Two options: 
<ol>
<li>Run jUnit Tests using IDE (*path to project*\src\main\test\com\reborne\light)</li>
<li>Using Postman (Download <a href=https://www.getpostman.com/>here</a>)</li>
</ol>
