# SOFENGG GUIDES (IMPORTANT PLEASE READ)
Below will be our reference on how to set up the server and libraries needed for the project.

Please follow them THOROUGHLY. Especially the "Web-Libraries" name as our User library. Having different user library name would cause problems with the server.


Note: Before doing Mysql related guide, follow JSTL guide first.


## TOMCAT Related
### Downloading TOMCAT
1. Download it here: https://tomcat.apache.org/download-80.cgi#8.0.47
2. Scroll down till "8.0.47"
3. Click the zip under CORE to download it.
4. Extract the zip.



### Installing TOMCAT
1. Right click SOFENGG
2. Click run as
3. Click run on server
4. Click Apache
5. Clic Tomcat v8.0 Server
6. Check "Always use this server when running this project"
7. Press next
8. Select the "apache-tomcat-8.0.47" folder as the installation directory.
9. Click finish.



## Servlet Related
**Servlets will cause a error if you haven't fixed the libraries under java resource**
1. To fix it, right click on SOFENGG, click build path.
2. Click configure build path
3. Click add library
4. click server runtime, then click Apache tomcat v8.0
5. click finish.


6.Click apply and close.



## JSTL Related
**JSTL may also cause an error if libraries arent fixed.**
1. To fix it, Download jstl-1.2:https://tomcat.apache.org/taglibs/standard/
2. Right click on SOFENGG, click build path.
3. Click configure build path
4. Click Add library
5. Click User libraries
6. Click new
7. IMPORTANT, Name the library: **Web-Libraries**.
8. Select Web-Libraries and click "add jars"
9. Select "jstl-1.2"
10. Click apply and close.
11. Now Go to SOFENGG>WebContent>WEB-INF>Lib
12. Copy paste the jstl-1.2 to Lib



## MySql Related
**MySql Connector**
Download the connector here: https://dev.mysql.com/downloads/connector/j/5.1.html
1. Extract zip file.
2. Copy mysql-connector-java.5.1.44-bin.jar to SOFENGG>WebContent>WEB-INF>lib
3. Right click SOFENGG
4. click build path
5. click configure build path
6. click libraries
7. Click Web-Libraries
8. click edit
9. click user libraries
10. click add external JARS
11. add the mysql-connector-java.5.1.44-bin.jar, then click apply and close
12. click finish.


Great MYSQL guide: https://www.youtube.com/watch?v=2i4t-SL1VsU&t=7s



## How to use JSTL:
To use jstl, place this on the very top of your JSP page (Before the !DOCTYPE):

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core %>

Full JSTL tags guide: https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm


