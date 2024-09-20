# Test Automation with Java and Selenium
This Web test automation project is developed using ->
1. IntelliJ IDEA Community 
2. Programming Language -> Java 
3. Build automation tool -> Maven
4. Test Automation Library - Selenium 
5. Test Framework -> Cucumber and TestNG
6. Design Pattern -> Page Object Model (POM)

### Test Execution - Command line
mvn test -DsuiteXmlFile=testng.xml

mvn test -DsuiteXmlFile=testng.xml -Dbrowser=firefox -Denv=uat

mvn test -DsuiteXmlFile=testng.xml -Dbrowser=chrome -Denv=uat

For cross browser-
mvn test -DsuiteXmlFile=crossbrowser-testng.xml

### Parallel Execution- 
Parallel testing in Cucumber is the ability to execute Cucumber scenarios in parallel, allowing multiple scenarios to run simultaneously and speeding up the overall test execution time.

#### 1. ThreadLocal WebDriver in parallel testing - 
We use ThreadLocal class to create a ThreadLocal WebDriver variable.Each thread running a Cucumber scenario or step can have its own WebDriver instance, isolated from other threads.
This approach ensures thread safety and prevents conflicts when executing scenarios in parallel.

#### 2. Create a testng.xml and pass parallel attribute =true
Create a testng.xml file and give parallel=true and thread-count

#### 3. runner file
Override the DataProvider annotation with parallel=true in CucumberRunnerTest that extends AbstractTestNGCucumberTests class.

### Jenkins Integration-
I have created Jenkinsfile using declarative pipeline syntax, where we have to option to select browser and env where we want to run our test.
We can also select CROSSBROWSER as true to run test on multiple browsers, by default it will run with cross browser as false.
![img.png](img.png)
![img_1.png](img_1.png)

# Features of the framework--

#### Parallel Testing and Parameterization-
 testng.xml is a configuration file for organizing and executing test.We can provide different test suites and specify test classes to execute for parallel testing
 This significantly reduces execution time, improving overall efficiency and productivity in testing.
 testng.xml allows to run same test with different input data. Ex we can pass different browser and env values as parameters.

#### Organizing and grouping the test-
We can organize test into different logical groups.Like can create different suites for running test on different browser.
<test thread-count="3" name="TestChrome">
<test thread-count="3" name="TestFireFox">

#### Integration with Jenkins-
testng.xml integrates easily with build tools like Maven and Jenkins. 
We use maven sure fire plugin which executes testng.xml which ultimately executes runner file mentioned insides class in testng.xml.

#### Page Object Model-
inside src/main.java we have created page classes for every page. We have defined variables for web elements using page factory or direct element instantiation. For using the methods of these classes we need to created objects and call those methods.
Page class will have locator to identify the elements, page specific methods and a constructor to initialize the page class variables.


#### OOPS based-
1. Framework supports Inheritance-- We have created a BasePage abstract class which defines the common methods for all the pages of the application like
 getPageTitle, getWebTableCellValue, waitUntilElementVisible etc. These methods are then reused in all the page classes. This way we are achieving inheritance.
2. Framework supports encapsulation-- Binding the fields and methods together. In this framework for every class we have a private driver instance and public constructor to instantiate the driver instance.
3. Framework supports Polymorphism-- Polymorphism allows us to perform same task in different ways. Click, selectDropDown are the methods in the framework that show method overloading.
4. Framework supports Abstraction-- We have base page abstract class, which contains abstract and non-abstract methods.

#### Benefits-
1) Easy Code Maintenance- Changes to UI elements are localized within page classes, reducing test case maintenance efforts.
2) Reusability- Page class objects are reused across multiple test cases , therefore reducing code duplication
3) Better Readability- Test are more focussed on business logic, making them easier to understand and review.

# Setup Project 
Easy way-
1) Create an empty repository on GitHub
2) Use git clone to clone the repository on local
3) Create a maven project locally and copy its content to this newly cloned empty repository.
4) Navigate to cloned repository
5) That's it, and you can use git commands pull and push

Proper way
1) Open GitHub and create a new repository with same name as local repo that we are going to create in below steps.
2) Open eclipse and create a new Maven project
3) open git bash and type below commands
4) git init to initialize or convert existing project to git repo
5) git add . to add all files to staging area
6) git commit -m "message"
7) git remote add origin https://github.com/aditya2001/selenium-java-cucumber
8) git push -u -f origin master
The -f switch forces Git to overwrite any files that already exist on GitHub with your existing project’s files.

# Libraries and Frameworks
Selenium - Web automation
Cucumber - Cucumber integration with TestNG and Selenium
Maven - Build and package management
TestNG - Test execution and Reporting
Tools
Intellij
Eclipse


