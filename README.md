<h3>Project settings </h3>

- Language: Java 11
- Language level: 11 - Local variable syntax for lambda parameters

---
<h3>Build the application </h3>

- build with Maven by command: `mvn clean install`


---
<h3>Run the application </h3>

- __Option_1__: Use IDEA's Run Configuration


- __Option_2__: 
  - [1] Open a terminal and navigate to the root folder of the project
  - [2] Run command: `java -jar target/webshop-report-generator-1.0-SNAPSHOT.jar`


- __Option_3__:
  - [1] Copy 'webshop-report-generator-1.0-SNAPSHOT.jar' (after the build) and paste to any folder you want  
  - [2] Open a terminal and navigate to that folder
  - [3] Run command: `java -jar webshop-report-generator-1.0-SNAPSHOT.jar`


---
<h3>What does this application do?</h3>

- [1] Reads data from 2 csv file according to the user's choice _(see additional info in *Process Details)_
- [2] Process the data based on requirements _(see details in OTPMobil - JAVA backend 2024.pdf)_
- [3] Creates 1 log file ('_application.log_') and save it to '__C:\webshop\log__' folder. (__Note:__ _When folder not exists the app creates it automatically!_) 
- [4] Creates 3 csv files ('_report01.csv_', '_report02.csv_' and _top.csv_'), and save them to '__C:\webshop\reports__' folder. (__Note:__ _When folder not exists the app creates it automatically!_)


__Process Details:__
- When application is starts the user should provide Setups in 2 steps:
- __[STEP_1]__
  -  User must enter the full path of the file which contains the CUSTOMER data _(example: C:\webshop-source\customer.csv)_
  - [WARNING]: The file path must not contain accented characters (like 'áéíóöúű')
- __[STEP_2]__
  -  User must enter the full path of the file which contains the PAYMENT data _(example: C:\webshop-source\payments.csv)_
  - [WARNING]: The file path must not contain accented characters (like 'áéíóöúű')
- After Setups was successful: application starts to read the data from the specified files (first the Customer then the Payment) 
  and validate each line (based on many aspect)
- [WARNING]: If it finds any errors during the validation the affected lines won't be save, but an error has been logged to '_application.log_' file

---
<h3>Playground</h3>


- You can find the following 2 csv files in the source code under the __src\main\resources__ folder:
  - customer_full_with_errors.csv
  - payments_full_with_errors.csv
- Please feel free to download and try running the application with them
- Have fun :)