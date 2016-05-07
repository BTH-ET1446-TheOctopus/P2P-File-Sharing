This project deals about the file sharing among peers. This application is developed using Java.
At start peers are connected to bootstrap server and requests for data.Bootstrap server provides the information of random peers who has the data along with the blacklisted IPs.
Peers periodically refreshes their validity time and connect to one of the addresses related to swarms to download through HTTPS/REST.
When another peer requests for the same file as former peer, now it has two sources to download simultaneously.

The repo both contains what was called Bootstrap and client before. 

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

8. {"name":"Fidde","surename":"Lass","age":32} should appear and you are ready to go!


# Build whit Maven
Make sure you have maven: *mvn -version*
1. Cd to dicretory P2P
2. mvn package
3. cd target
4. java -jar .\Octopus-0.0.1-SNAPSHOT.jar


 


NOTE if the server can't come up you need to close the existing connection or change port. 
A program that can be used is TCPView but it is only for windows.

Create folder tmp in order to have a folder that will not be included in git repository. 
If one wants another folder name, please add the folder name to git ignore. 

# Should be in wiki
## General info Junit
The bootstrap and client can be built whit
mvn -package

If a junit test is created which fails,then maven wont build the project and you will see what junit tests are failing. 
The junit tests should be added in src/test/java/test

If you want to create a new junit test class add it to the package mention above and name it Test* otherwise you will get into trouble and have a hard time to find the error. 


## How to write test
As you see in the function below it is simple. 
You use assertEquals to see that the test you are doing produce the expected output. 
For the test below the test is "sum = 2 + 3". My assumption is that this generates 5. 
Now we compare whit assertEquals the assumption and the variables sum. 
The assumption should be on the left and the "test" to the right. 
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


Note :
If the data in the database is not encrypted, it is always possible to copy and restore the data to a different SQL server.
In order to protect the data from such attacks, it is important to encrypt it i.e., data should be protected from SQL injections.
Injections are new entities that are entered into the database.