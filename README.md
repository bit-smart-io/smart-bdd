Learning tests for 'code first' working title.

Projects:
* Wordify - parse java code to human readable text
* Report - to generate json report 
* Client side report
* Server test re-runner - api to select a test and parse in params
* ft - internal ft
* smart-test or smart-tdd

I think 
* smart-bdd
* smart-test - basic test tools for tdd/unit and bdd/ft. smart-test-common, smart-test-utils
* smart-tdd - tdd tools
* smart-reporting - shipping results 
  * create webpage?
  * file data json/xml
  * data to db
  * data to rest service
    
The reporting can be used for unit/ft/it/nft etc... maybe need a base project
 maybe report-model. This is it's own project.


Maybe all io.bitsmart.bdd is for bdd and io.bitsmart.test is generic test???

| project name  | package  | description  |   
|---|---|---|
| root       | io.bitsmart.bdd | root for repo  |  
| report     | io.bitsmart.bdd.report  | reporting extension and report creation. Name should be smart-bdd??  | 
| wordify    | io.bitsmart.bdd.wordify  | wordify java code. wordify or smart-wordify  |   |   |
| ft         | io.bitsmart.bdd.ft | internal ft |
| smart-test | io.bitsmart.bdd | testing utils such as builder |

Reporting ideas:
* Compare log of previous run - show a diff. Can have a cut down version of the log that only shows the dif - hence what has gone wrong.
* Send reports to report aggregator on each test, suite, package or at the end
* Keep track of irregular issues


###Misc Notes:

Project Structure:
* ports/junit5
* domain/annotations/Given

Java passing
* https://github.com/paul-hammant/qdox
* https://github.com/javaparser/javaparser

Other test frameworks:
* https://github.com/reportportal/agent-java-junit5

