# Simple Books API demo

A small REST Assured + Java + Cucumber assessment project.

## Run
1. Install JDK 17 and Maven (or open the project in IntelliJ with JDK 17 and its bundled Maven).
2. Extract this ZIP and open the books-api-demo folder.
3. From the folder containing pom.xml run:

   mvn test

4. Open target/cucumber-report.html for the result.

Internet access is needed to download dependencies and call the API. You can also run RunCucumberTest from your IDE after Maven dependencies load.

## What each file does
- pom.xml: dependencies and build settings.
- books.feature: readable Given/When/Then scenario.
- BookSteps.java: sends the request and performs assertions.
- RunCucumberTest.java: runs the feature and produces an HTML report.

## What is checked
- HTTP status is 200.
- Response time is below 3000 milliseconds.
- Response body size is greater than zero bytes (headers excluded).
- Array contains six books.
- First record's name is The Russian.
- Record with ID 4 has name The Midnight Library.
- Filtering gives four fiction books and five available books.
- Book with ID 2 has available=false.

## Explain it in the demo
Cucumber reads each step and calls the matching Java method. REST Assured sends GET /books and saves the response. Assertions compare the actual values with expected values. [0] accesses the first record; find searches by ID; findAll filters several records.

Array size (number of books) and body size (bytes) are separate assertions. Body bytes may differ from Postman's displayed total size.

The 3000 ms limit is an example for this assessment, not a published service SLA. Client/network overhead can affect timing. Exact book values and counts are based on the supplied response and may change on this public API. The scenario stops at its first failed step.

## References
- REST Assured: https://github.com/rest-assured/rest-assured/wiki/Usage
- Cucumber Java: https://cucumber.io/docs/installation/java/

## Validation
Prepared for Java 17. See VALIDATION.txt for whether execution was verified in the preparation environment. Run locally before submission and review the generated report.
