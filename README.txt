Configuration Required for each basic installation of the template (without webservices)

* Setting up the basic project

Edit the following files
1: /pom.xml (change project name, add new repositories and dependencies)
2: /.settings/org.eclipse.wst.common.component (change project name)
3: src/main/webapp/WEB-INF/bmkegdefault.properties (configuration for running applications)
4: /src/test/resources/bmkegtest.properties (configuration for running tests)

* Application-specific configuration

Add new bean definitions in new files (if required)
1: under /src/resources/path/to/your/application/applicationContext-<appName>.xml
2: edit /src/main/webapp/WEB-INF/web.xml to add a pointer to this new file.

* Eclipse stuff
Right click on project in Navigator and 'add Spring Project nature''

