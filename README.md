digitalLibraryServer
====================

A web application resource (*.war) file for the digital library client. 

For GitHub users, we advocate the use of Maven to build and run the system from 
the command line. This runs somewhat slowly but will execute out-of-the-box. 


Building the system
-------------------

The system should build using the following maven command. Not only does 
this build the *.war file, but it also copies files pertaining to the 
'View-Primitive Data Model framework' (VPMDf) that the system needs to
construct the underlying database.

1. Make sure you have both the `bmkeg-parent` and the `lapdftextServer` repositories as sibling directories in your workspace.
2. run `mvn clean package`. This will perform all necessarybuild functions, including all retreiving dependencies.  

Running system commands
-----------------------

This web application uses the VPMDf which uses an underlying MySQL database. 
The first step of running the system is to (A) build the database and (B) 
populate it with PDF files. 

1. Building the database

`mvn exec:java -Dexec.mainClass="edu.isi.bmkeg.lapdf.bin.BuildFtdDatabase" -Dexec.args="<db_name> <mysql_login> <mysql_password>"` 

2. Adding PDF Files



3. Adding Rule Files explicitly 

