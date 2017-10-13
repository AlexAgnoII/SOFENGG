# SOFENGG GUIDES (IMPORTANT PLEASE READ)


**Hibernate related**
1) Right click SOFENGG
2)Click run as
3) Click run on server
4) Click Apache
5) Click Tomcat v8.0 Server
6) Check "Always use this server when running this project"
7) press next.

8) Insert the Tomcat Installation directory
    8.1) Download it  here: https://tomcat.apache.org/download-80.cgi#8.0.47
    8.2) Scroll down on the " 8.0.47"
    8.3) Click the zip, under CORE.
    8.4) Extract the zip.
    8.5) Select the "apache-tomcat-8.0.47" folder as the installation directory.
    8.6) Click finish.

**Servlets related**
9) Servlets will cause an error if you haven't fixed the libraries under java resource.
    9.1) To fix it, right click on SOFENGG, click build path.
    9.2) Click configure build path
    9.3) Click add library
    9.4) Click server runtime, then click Apache tomcat v8.0
    9.5) Click finish.
    9.6) Click apply and close.

**JSTL related**
10) JSTL causing error?
    10.01) Download jstl-1.2:https://tomcat.apache.org/taglibs/standard/
    10.1) To fix it, right click on SOFENGG, click build path.
    10.2) Click configure build path
    10.3) Click Add library
    10.4) Click User libraries
    10.6) Click new
    10.5) IMPORTANT, Name the library: Web-Libraries
                Libraries are case sensitive, so if you placed the wrong 
                library name, it'll cause havoc to the rest of the team.
    10.6) Select Web-Libraries and click "add jars"
    10.7) Select "jstl-1.2"
    10.8) Click apply and close.
    10.9)Now Go to SOFENGG>WebContent>WEB-INF>Lib
    10.10)Copy paste the jstl-1.2 to Lib

**MySql related**
11) MYSQL connector:
        <Coming soon>


**How to use JSTL:
To use jstl, place this on the very top of your JSP page (Before the !DOCTYPE):
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


