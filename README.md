Learning tests for 'code first' working title.

Projects:
* Wordify - parse java code to human readable text
* Report - to generate json report 
* Client side report
* Server test re-runner - api to select a test and parse in params

Group names:
* root      - io.bitsmart.bdd
* report    - io.bitsmart.bdd.report
* wordify   - io.bitsmart.bdd.wordify
* ft        - io.bitsmart.bdd.ft

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

