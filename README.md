# smart-bdd

### Overview

Create interactive feature files from Java code:

- Ability to re-run tests.
- Show downstream interactions with diagrams.
- Capture downstream data such as HTTP request/response headers and body.
- More consistent as builders as used to set state and exercise the system under test.
- More productive as you are encouraged to use best practices and don't have the complexities of the steps 1-3 below.
- Test results as data so that historical test results can be persisted and queried.

This is stark contrast to existing frameworks: Cucumber, JBehave, Concordion etc...

Traditionally have approximately four layers:

1. Feature file / specification / UI.
    - A feature is a text description of a feature that consists of scenarios and are made up of steps
    - This is the first thing that is writen and leads the design for the following.
1. The glue layer.
    - Matches Java method for that step and the corresponding arguments. Usually a regular expression.
1. Orchestrate your actual FT framework.
    - A Java method has been supplied data from the glue layer. You'll need to orchestrate your actual FT framework to
      actually do any testing.
    - You may have more data than you require - in non-trivial steps you have to chose code re-use or code duplication.
      Opting for code reuse makes glue layer and down more complex.
    - You may have a different domain and or bounded context for the feature file and the actual FT framework domain.
      This means adapting/transforming the data for the FT framework.
1. The actual FT framework.
    - This usually would have the following functionality:
        - Set state before and after tests.
        - Exercise the thing under test.
        - Store state so that you assert on expected behaviour.
    - This has been designed to accommodate additional complexities from above.

Adding new data, steps and or features is not linear because of the following:

* Due to complexity
* Coupling to between layers
* Limitations in the glue layer
    * Forcing step definitions to be constructed in a certain way. Possibly not the way you planned and or wanted.
    * Forcing refactoring when you alter steps definitions

These 3 forces can unfortunately can compound each other. Complexity and coupling should be kept to a minimum not part
of the solution. Layers 1-3 exist so that we can have feature files, these serve as static documentation for the system.
There are no guaranties that the documentation is consistent, in fact there isn't anything enforcing it.

The alternative to this is generating dynamic, consistent documentation. With smart-bdd you leg up on developing the
actual FT framework, so you can focus on testing your application. You add `@ReportExtension` annotation to your class,
this will generate a report. There is a `wordify` process that takes the Java code and converts it in English sentences.
For example `givenSomething()` would produce `given something`. There is a strong emphasis on using builders therefore
forcing you to create a fluent API.

The `wordify` process isn't finished, you can't simply get rid of complexity and coupling, but it's the gaol of this
project to reduce both of them.

With thanks to https://github.com/bodar/yatspec who did a similar project that worked with JUnit 4.

### smart-bdd usage:

Please see `example:books`.

1. Import the `report` project `testImplementation(project(":report"))`
2. Copy the file `org.junit.platform.launcher.TestExecutionListener` to `src/test/resources/META-INF/services`
3. Add `@ExtendWith(ReportExtension.class)` to any class that you want to generate a report from.

Currently, after you un a test the report file locations will be printed to the console.

```
@ExtendWith(ReportExtension.class)
public class GetBookTest {
    @Test
    public void getBook() {
        whenGetBookIsCalled();
        thenTheBookIsReturned();
    }
    ...
}
```

Will produce the following step defs:

```
When get book is called 
Then the book is returned
```

### smart-bdd projects:

| project name  | package  | description  | notes  |
|---|---|---|---|
| root       | io.bitsmart.bdd | root for repo  |
| report     | io.bitsmart.bdd.report | reporting extension `@ReportExtension` and report creation (.html and .json)  | Should be `@smart-bdd`? |
| wordify    | io.bitsmart.bdd.wordify | wordify java code | |
| ft         | io.bitsmart.bdd.ft | FT for the report generation | | 
| test-utils | io.bitsmart.bdd.report.utils | testing utils such as builder | only the builders at the moment | 
| examples   | n/a| examples of using smart-bdd | maybe `io.bitsmart.example.bookstore` | 
| webpage    | n/a| vue js | replace with react | 

Questions:

* Should everything be prefixed with `smart-`? `smart-report`, `smart-wordify`, `smart-test-utils` etc...
* Need to understand what projects relate to bdd, tdd and or testing.
* Maybe all `io.bitsmart.bdd`, `io.bitsmart.tdd`, and `io.bitsmart.test`?

### smart-bdd projects to do:

* client-side-report or webpage or webapp - dynamic React web app. Have created a Vue.js app, React would be better.
* test-re-runner - rest app to select a test and parse in params. FT's have a dependency on this and can therefore spin
  up Spring Boot app?
* smart-report-shipper - shipping results
    * create webpage?
    * file data json/xml
    * data to db
    * data to rest service
