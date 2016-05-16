The P2P file sharing application is developed using Java.

Upon start,the peers are connected to the bootstrap server.Bootstrap server displays the IP addresses of
random peers,along with the blacklisted IPs.

Peers are responsible for periodically refreshing the validity time for their IP addresses. The peer attempts to
establish connections to the peers received from the bootstrap server through HTTPS/REST.

When another peer requests for the same file as the former peer, it now has two sources to download from
simultaneously.

When a peer/client searches for data,a request is sent to the backend,which in turn communicates with database and
processes the request, and responds with the relevant data.

The repo both contains what was called Bootstrap and Client before. 

# Install JAVA
1. http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. make sure you download java SE 1.8 if not the project might not work. 
3. Set java path in env variables


# Set up Eclipse
1. Install Eclipse IDE for Java SE Developers
2. Help -> Install new software -> add and use http://download.eclipse.org/technology/m2e/releases
3. Add it and press the following nexts

# Set up backend:
1. In Eclipse -> File -> Import... -> 
2. Maven -> Existing Maven project -> next -> choose root directory
3. Chose project /Octopus/P2P/pom.xml-> finish
4. Now it should be open in Project Explorer
5. Run the project as normally 
6. Something should be loading now and lots of red lines will appear
7. Type in web browser http://localhost:9999/rest/rest/test/

8. {"name":"Fidde","surname":"Lass","age":32} should appear and you are ready to go!

# Build whit Maven
Make sure you have maven: *mvn -version*
1. Cd to dicretory P2P
2. mvn package
3. cd target
4. java -jar .\Octopus-0.0.1-SNAPSHOT.jar

NOTE : If the server can't come up you need to close the existing connection or change port. 
A program that can be used is TCPView but it is only for Windows.

Create folder tmp in order to have a folder that will not be included in git repository. 
If one wants another folder name, please add the folder name to git ignore. 

# MySQL Step-by-step Installation Guide

1. Go to this page and select proper package based on your platform:
http://dev.mysql.com/downloads/mysql/

2. In the next page you can Login/Sign up if you have/want an Oracle account, otherwise at the bottom of the page choose "No thanks, just start my download." to start your download.
The package size is between 250MB to 350MB depend on the platform you choose.

3. For Installing MySQL step-by-step in 'Linux' go to this link and follow the instructions:
http://www.thegeekstuff.com/2008/07/howto-install-mysql-on-linux/


## FOR WINDOWS:
1. Once your mySQL server download is complete you just need to double click the file to start the installation process.  Just follow the instructions to install mySQL Installer – Community.  Once the installation is complete it will open mySQL Installer from where you need to setup and create the mySQL server instance.

2. Installation Walkthrough
- On the 'Welcome' page, press the 'Install MySQL Products' -link.
- On the License Agreement page, Check the box to accept the license terms and press Next.
- On the Choosing a Setup Type choose Custom so you can decide exactly which packages to install. This is the only place where you can specify whether to install 32-bit or 64-bit MySQL Server.
- On the Select Products and Features page, select the 32bit or 64bit versions of all the applications that you want. At the very least get:
   MySql Server
   MySQL Workbench (a UI used to manage your local databases)
   MySQL Notifier (adds a tray icon that lets you quickly start/stop MySQL server)
   MySQL Connectors (you’ll probably want to install them all because you never know which future tool might depend on a particular connector)
- On the Check Requirements page, install any missing requirements by pressing the Execute button, or go back to the previous page and change the packages that will be installed to remove the requirement. Once all of the requirements are met, press the Next button.
- On the Installation page, you will be shown a list of software that will be downloaded. Press the Execute button to begin the downloads.
- At this point, you might get an error stating that the Connector/OOBC 5.x.x failed to download. This is a known issue and the workaround is to run a separate Connector/OOBC installer. We’ll handle that at the end. Press the Next button to start the installation.

3. Add mysql command to the Windows PATH Variable
Adding MySQL to the Windows PATH variable will allow you run various commands from the console, and allow 3rd party applications to execute various MySQL commands.

- Click the 'Start Orb'
- Right click on the computer menu/button Computer
- Select the Properties menu option.
- On the dialog that opens, select Advanced system settings.
- Select the Advanced tab.
- Click the Environment Variables button.
- Select the Path variable in the table, then press the Edit button.
- Append the path to your MySQL Server bin directory (make sure to add a semi-colon after any existing items in the path before appending the new path). The default path for a 64bit installation is C:\Program Files\MySQL\MySQL Server 5.x\bin.

# Download JDBC driver for MySQL
in order to have Java program working with MySQL, we need a JDBC driver for MySQL. Browse this URL:
http://dev.mysql.com/downloads/connector/j/
to download the latest version of the JDBC driver for MySQL called Connector/J.

- Click the 'Download' button next to Platform Independent (Architecture Independent), ZIP Archive to download a zip archive. Extract the 'mysql-connector-java-5.x.x.zip' file to a desired location on your computer.

- The distribution includes a binary 'JAR' file, 'source code', 'documentation' and 'license' files. But only one file we need is the JAR file 'mysql-connector-java-5.x.x-bin.jar'.

- Copy this file into your project and make it available in your program’s classpath.

## Configure MySQL Server
When the installer finishes, a new configuration wizard will open.

- On the 'Type and Networking' page, click on the 'Config Type' box to take a look at the available options. Each option contains a nice description to help you choose the type that makes the most sense. Since we don’t want MySQL Server to take over our development box, we’ll choose 'Development Machine'.

Check the box for 'TCP/IP Networking' and choose a port. Port 3306 is standard and works well so leave it alone unless you have a reason to change it. Check the box to 'Open Firewall port for network access'. Lastly, click the 'Show Advanced Options' checkbox and press 'Next'.

On the next page, pick a good password for your 'MySQL root account'. You can add any additional users you need now, or do this later when you’re setting up your application. Press 'Next' when done.

On the next page for 'configuring the Windows Service Details', give your service a new name or leave the default suggestion in place. Next, decided on whether to Start the MySQL Server at System Startup. For a development machine, it makes sense to check this box. The last option, tells MySQL Server which user account to run under. Leave the box checked for 'Standard System Account' unless you have an explicit reason not to. Press Next.

On the 'Advanced Options' page, accepts the defaults unless you would like something non standard. Press Next to finish the configuration and installation. You should see the Configuration Complete message on the Configuration Overview page. You might also get a notification from Windows that the list of Services has changed as well. If you decided to install Samples and Examples, MySQL will need to briefly configure that as well, so press Next to start that configuration. When that is done, press Next to finish the configuration.

## Start and Stop MySQL Server
At this point, you should be on the Installation Complete page with MySQL Server running. If you choose to install MySQL Workbench, you will see a checkbox asking to open MySQL Workbench when the Installer closes. Make your choice and select Finish.

You can start and stop your MySQL Server on Windows by connecting to it in MySQL Workbench. This UI will allow you to administer your server, run queries, manage database, etc… If you installed the MySQL Notifier, you should have a tray icon that will allow you quickly start and stop the service. Lastly, you can jump straight to the Windows Services list by typing 'Services' in Start Menu’s search box. Once the list of services comes up, find the name of the MySQL Service and right click over it. You should see option so Start/Stop the service.

## Test the Installation
If everyone has gone right, you should be able to open the command prompt and execute:

$ mysql -u root -p
At this point you should be prompted for the root password. Enter it and press continue. If you get an error saying the mysql command is not found, check the path variable again. Type the following on the command line and make sure the correct path is listed.

$ path

If the mysql command did work, type '\q' and press 'Enter' to exit

# Should be in wiki
## General info Junit
The bootstrap and client can be built whit
mvn -package

If a junit test is created which fails,then maven will not build the project and you will see what junit tests are failing. 
The junit tests should be added in src/test/java/test

If you want to create a new junit test class add it to the package mention above and name it Test* otherwise you will get into trouble and have a hard time to find the error. 


## How to write test
As you see in the function below it is simple. 
You use assertEquals to see that the test you are doing produce the expected output. 
For the test below the test is "sum = 2 + 3". The assumption is that this generates 5. 
Now we compare with assertEquals the assumption and the variables sum. 
The "assumption" should be on the left and the "test" to the right. 
```java
	@org.junit.Test
	public void test() {
		int sum = 0;
		sum = 2 + 3;
		assertEquals(5, sum);
	}
```

## Logging
Use logging instead of System.out.println()
Import these libraries
```java
import java.util.logging.Level;
import java.util.logging.Logger;
```
Put this at the top of your class
```java
private static final Logger LOG = Logger.getLogger(ClassName.class.getName());
```
Be sure to replace `ClassName` with the name of your class.

### Examples
```java
LOG.log(Level.FINE, "Entering processing loop");
LOG.log(Level.INFO, "Bootstrap server was started at {0}", server.getAddress().toString());

try {
    functionThatMightThrow();
} catch(Exception e) {
    LOG.log(Level.SEVERE, e.toString(), e);
}
```
### Logging levels
Use `Level.FINE` for debugging. Possible levels are:
* `Level.SEVERE` (highest value)
* `Level.WARNING`
* `Level.INFO`
* `Level.CONFIG`
* `Level.FINE`
* `Level.FINER`
* `Level.FINEST` (lowest value)

### See Also


NOTE : If the data in the database is not encrypted, it is always possible to copy and restore the data to a different SQL server.
In order to protect the data from such attacks, it is important to encrypt it i.e., data should be protected from SQL injections.
Injections are new entities that are entered into the database.