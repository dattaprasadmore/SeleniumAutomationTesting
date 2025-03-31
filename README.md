## Test Automation Framework

This repository contains a Java-based Test Automation Framework designed for functional testing of web applications. The framework is built using Java 17, TestNG, and Maven, with integration for Lambda Test, data-driven testing, Selenium Grid parallel test execution, headless execution, and logging/reporting mechanisms.

## 🚀 About Me
Hi, My Name is Dattaprasad More and I have 9+ years of experience in Software testing using technologies like Selenium WebDriver, RestAssured.

My major experties is in Java Programming Language. 

## Authors

- [@dattaprasadmore](https://github.com/dattaprasadmore)
- EmailAddress: dattaprasad.more@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/dattaprasadmore)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/dattaprasad-more-1b346618/)
## Prerequisites

Before running this framework, ensure the following software is installed on your System. 

- **Java 17** - Make sure Java is installed and JAVA_HOME environment variable is set.

- **Maven** - Ensure Maven is installed and added to the system path 
-   Download link: https://maven.apache.org/download.cgi

## Features

- **Data-Driven Testing**: Supports OpenCSV, Gson, Jackson, and Apache POI for reading the test data from CSV, Excel files and JSON.

- **Fake Data Generation**: Uses the Faker library to generate random test data.
  
- **Selenium Grid Execution**: Test can be executed on Dockerized Selenium grid.

- **Cloud Execution**: Tests can be executed on Lambda Test.

- **Headless Execution**: Speeds up test execution.

- **Command Line Execution**: Run tests via Maven Surefire Plugin with customizable parameters.

- **Logging**: Generates detailed logs with Log4j.
  
- **Reporting**: Generates detailed test reports with Extent Reports.

## Technologies Used
- **Java 17**
- **TestNG**
- **OpenCSV**
- **Gson**
- **Apache POI**
- **Faker**
- **Log4j**
- **Dockerized Selenium Grid**
- **LambdaTest**
- **ExtentReport**
## Installation

**Clone the repository**:

```bash
git clone https://github.com/dattaprasadmore/SeleniumAutomation.git

cd SeleniumAutomation
```

## Running Tests

You can execute tests via the command line using Maven Surefire Plugin. The framework supports the following parameters:

**Running tests on Chrome browser in Local Mode**:

```bash
mvn clean test -DbrowserName=chrome -DexecutionMode=local -Dheadless=false
```

**Running tests on seleniumGrid**:

```bash
mvn clean test -DbrowserName=chrome -DexecutionMode=seleniumGrid -Dheadless=false
```

**Running tests on LambdaTest**:

```bash
mvn clean test -DbrowserName=chrome -DexecutionMode=lambdaTestGrid -Dheadless=false
```

## Test Reports & Logs
- Reports: After execution, a detailed HTML report will be generated at ./report.html  
    The report contains information on test cases executed pass failed and skipped, along with screenshots for failed tests. 

## Logs:
  Logs are created during the test execution and stored in the ./logs/
  directory.
