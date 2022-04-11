# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# How to run the code

Have provided scripts to execute the code for unix based systems. 

Use `run.sh` to run the application.

Internally the script runs the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `ledger.jar` in the `target` folder.
 * `java -jar target/ledger.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument
 
# How to execute the unit tests

 `mvn clean test` will execute the unit test cases.
 
# Help

In case of any issues mail me at pkprince48@gmail.com
