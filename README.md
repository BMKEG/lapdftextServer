lapdftextServer
===============

A web application resource (*.war) file for a simple client to show the block classification for a given LAPDFText rule file. 

![Simple Screenshot](src/main/docs/screenshot1.png?raw=true)

For GitHub users, we advocate the use of Maven to build and run the system from 
the command line. This runs somewhat slowly but will execute out-of-the-box. 

Building the system
-------------------

The system should build using the following maven command. Not only does 
this build the *.war file, but it also copies files pertaining to the 
'View-Primitive Data Model framework' (VPMDf) that the system needs to
construct the underlying database.

1. Make sure you have both the `bmkeg-parent` and the `lapdftextServer` repositories as sibling directories in your workspace.
2. run `mvn clean package`. This will perform all build functions, including retreiving all dependencies.  

Running system commands
-----------------------

This web application uses the VPMDf which uses an underlying MySQL database. 
The first step of running the system is to (A) build the database and (B) 
populate it with PDF files. 

**Building the database**

```
mvn exec:java -Dexec.mainClass="edu.isi.bmkeg.lapdf.bin.BuildFtdDatabase" 
        -Dexec.args="<db_name> <mysql_login> <mysql_password>"        
``` 

**Adding Rule Files**

Note that you can only refer to the rule file by name in subsequent commands, so the rules files 
have to be uploaded first for the command line functions to work.

```
mvn exec:java -Dexec.mainClass="edu.isi.bmkeg.lapdf.bin.AddFTDRuleSet" 
        -Dexec.args="<path-to-rule-file> <dbName> <login> <password>"        
``` 

**Adding PDF Files**
 
Here is where you can iterate over large numbers of PDF files and upload them into the system. 

```
mvn exec:java -Dexec.mainClass="edu.isi.bmkeg.lapdf.bin.AddFTD" 
        -Dexec.args="<path-to-pdf-file/dir> <dbName> <login> <password> [<rule-file-name>]"        
``` 

**Run Rules on Files**
 
Here is where you can rerun rules over the PDF files in the database already.

```
mvn exec:java -Dexec.mainClass="edu.isi.bmkeg.lapdf.bin.RunRuleSetOnFTD" 
        -Dexec.args="<pdf-file-name> <rule-file-name> <dbName> <login> <password>"        
``` 

Running the web-app
-------------------

**First use Jetty to start the server**

```
mvn jetty:run-war
``` 

**Navigate in a browser to:**  `http://localhost:8080/lapdftextServer` 

Caveats
-------

* The system is based on AMF-messaging to a Java-based server which talks to an underlying MySQL database and so is a little slow in the upload / rule-file execution / download process.
* The system runs out of the box using the maven commands as shown but is still a research prototype.
