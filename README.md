# LibraryManagmentSystem
Guide to Run the Library Management System


INSTALLING JAR FILES 

-	Locate the lib folder 
-	There are 3 JAR files in the lib folder: mysql-connector , javax.mail.jar, activation.jar 
-	Go to files and click on project structure 
-	Under project settings -> Modules 
-	You will see a ‘ + ‘ sign to add more SDKs press that and go to the lib folder and add all the three JAR files 
-	Click on Apply and then press OK. 



Loading the Database 

-	I have added a dump file with my code, since it is a library management system it has to have books. You can locate the dump file in the 'dumpFolder'
-	Add the dump file in your preferred sql visual tool (XAMPP or mysql workbench) 
-	Go to the JDBC class and change the username and password (if you haven’t changed it before leave it to what it is since I am using the default one).
-	For connecting to the email, locate the EMAIL class and add your own gmail email and app password to send out emails.
![image](https://github.com/taham655/LibraryManagmentSystem/assets/96670924/fcf216c0-9588-4674-bdf2-4cdff8818aa5)
