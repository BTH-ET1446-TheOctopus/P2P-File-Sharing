Set up Eclipse
1. Help -> Install new software -> add and use http://download.eclipse.org/technology/m2e/releases
2. Add it and press the following nexts

Set up backend:
1. Install Eclipse IDE for Java SE Developers
2. In Eclipse -> File -> Import... -> 
4. Maven -> Existing Maven project -> next -> choose root directory
5. Chose project /Octopus/P2P/pom.xml-> finish

6. Now it should be open in Project Explorer
7. run the project as normally 
8. Something should be loading now and lots of red lines will appear

9. Type in webbrowser http://localhost:9999/rest/rest/test/

10. {"name":"Fidde","surename":"Lass","age":32} should apper and you are ready to go!

 


NOTE if the server can't come up you need to close the existing connection or change port. 
A program that can be used is TCPView but it is only for windows

Create folder tmp in order to have a folder that will not be included in git repository. 
If one wants another folder name please add the folder name to git ignore. 