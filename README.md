Set up Eclipse
1. Help -> Install new software -> add and use http://download.eclipse.org/technology/m2e/releases
2. Add it and press the following nexts

Set up backend:
1. Install Eclipse IDE for Java EE Developers
2. Dowlonade Apache Tomcat and add it to Eclipse
3. In Eclipse -> File -> Import... -> 
4. Maven -> Existing Maven project -> next -> choose root directory
5. Chose project /Octopus/P2P/pom.xml-> finish

6. Now it should be open in Project Explorer
7. Go down to server (middle of Eclipse in bottom windows) 
8. Choose Tomcat (If it not existing you need to redo steep 2
9. Right click "Add and remove..." 

10. Choose Octopus and add it to right side
11. Right click on Tomcat and restart or CTRL + ALT + R

12. Something should be loading now and lots of red lines will appear

13. Type in webbrowser http://localhost:8080/Octopus/API/hello

14. {"name":"Fidde","surename":"Lass","age":32} should apper and you are ready to go! 

Create folder tmp in order to have a folder that will not be included in git repository. 
If one wants another folder name please add the folder name to git ignore. 