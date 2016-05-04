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
5. run the project as normally 
6. Something should be loading now and lots of red lines will appear
7. Type in webbrowser http://localhost:9999/rest/rest/test/

8. {"name":"Fidde","surename":"Lass","age":32} should apper and you are ready to go!


# Build whit Maven
Make sure you have maven: *mvn -version*
1. Cd to dicretory P2P
2. mvn package
3. cd target
4. java -jar .\Octopus-0.0.1-SNAPSHOT.jar


 


NOTE if the server can't come up you need to close the existing connection or change port. 
A program that can be used is TCPView but it is only for windows

Create folder tmp in order to have a folder that will not be included in git repository. 
If one wants another folder name please add the folder name to git ignore. 

# Should be in wiki
## General info Junit
The bootstrap and client can be built whit
mvn -package

If a junit test is created which fail maven wont build the project and you will see what junit test are failing. 
The junit tests should be added in src/test/java/test

If you want to create a new junit test class add it to tha package mention above and name it Test* otherwise you will get into trouble and have a hard time to find the error. 


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
* http://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html
