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