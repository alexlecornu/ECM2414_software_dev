# ECM2414 Software Development README
#### Authors: Harry Williams, Alex Le Cornu

## Main Solution
### Dependencies
To run the program, JDK 25 will need to be installed as this is the kit we have used to write our solution,
JDK 25 can be downloaded [here](https://www.oracle.com/uk/java/technologies/downloads/#java25). There are installation 
options for Linux, MacOS and Windows so any operating system can be used.

We recommend running the solution in an IDE of your choice that has a command line, a recommended example would be IntelliJ
which can be installed [here](https://www.jetbrains.com/idea/download/?section=windows).

### Libraries
All of the libraries we have used ship with the JDK so no extra installation is required.

Here are the libraries that are required for the main solution:

- **java.io** (FileWriter, File, IOException, BufferedReader, Scanner, FileNotFoundException)
- **java.util** (ArrayList)
- **java.util.concurrent**  (CyclicBarrier, BrokenBarrierException)
- **java.lang** (NumberFormatException)


### Running the program
In terms of running the program, ensure that you are in the `\src` folder before running . When running the program without
using the `.jar` file, run the command `java CardGame` in the command line. If running the solution through the `.jar` file, run the
command `java -jar cards.jar`. Ensure that the pack text file you would like to use is placed inside the `\src` directory for it
to be recognised.
## Test Suite
### Dependencies /Libraries
Our testing suite uses JUnit5 which will need to be imported/installed, JUnit is not shipped with the JDK. 
Depending on what IDE or build tool is being used JUnit can be installed in many different ways.
For our recommended IDE which is IntelliJ it can be added to the class path which will allow the tests to be run.
When in the testing file this can be done by checking if the `org.junit.jupiter` import statement is red,
then pressing alt+enter and choosing the option to add Junit to the class path. 

### Running the Test Suite
In IntelliJ, the test files can be run together or individually by either right-clicking on the tests directory and selecting "Run 'All Tests'",
or opening a test class and running the whole class file or a specific test within the class. If using another IDE the process may be different.
