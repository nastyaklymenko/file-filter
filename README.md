You need:
● Servlet 3.1
● Java 7
● Tomcat 8.x

http://localhost:8081/ - open my application
http://localhost:8081/api/files/textfileAPI.txt?includeMetaData=true - read text file
http://localhost:8081/api/files/textfileAPI.txt?includeMetaData=true&limit=1000&q=Java&length=5 - filter text file(word occurrence)