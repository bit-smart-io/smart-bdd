# List of TODOs for the mono repo

### MVP:

- [x] Static webpage for test suites - maybe Thymeleaf
- [x] Static webpage menu
- [ ] Add examples
    - [ ] Book store?
    - [ ] Calculator microservice - see cucumber examples
- [x] Move learning tests to own project
- [x] General code tidy
- [x] Copy write every class
    - https://choosealicense.com/licenses/mit/ - short and cucumber has this
    - https://choosealicense.com/licenses/gpl-3.0/ - means don't profit from my code
- [ ] Investigate META-INF/Services SmartTestExecutionListener gets exported

### Post MVP:

- [ ] Add to github
- [ ] Parametrised tests (see testCaseResult.setName(...) and verifyThirdTest_paramsWithCustomName). See Notes below.
- [ ] Read the @BeforeEach @BeforeAll etc... and add to the result
- [ ] Add timestamp, hostname, time (maybe setup, execute and after times)
- [ ] Handle all test factories, templates and dynamic
- [ ] Apply Capture standard output and standard error.
- [ ] Handle Strings i.e. anything in quotes
- [ ] Additional wordifies i.e. swap words around to create more readable sentences
- [ ] @Given, @When, @Then - These would have to be parameter annotations
- [ ] @Notes - Can add notes for the feature/scenario
- [ ] UnderTest fields - this is to highlight fields, possible change the values
- [ ] Logger than can added extra text in the report - `log.add('some notes')`
- [ ] Sequence diagrams - mermaid
- [ ] Re-run tests endpoints
- [ ] Re-run tests ui
- [ ] Update test values in the ui - so that the test can be re-run with different values
- [ ] Store the tests in a DB
- [ ] Explore when and how builders should be the same for ft and unit tests. Is it feasible to sync builders classes
  and packages?
- [ ] Add Java 9 modules?

### Done

- [x] Parse params
- [x] Have a context
- [x] Have failing tests
- [x] Capture the failures
- [x] Stats for passed, skipped, aborted and failed
- [x] Handle names of methods that have params
- [x] Capture standard output and standard error mechanism.
- [x] Create report in Json
- [x] Website prototype
- [x] Use Kotlin for Gradle
- [x] Project structure - need modules/projects

### Actions

Wrap given() body in async actions. This is make test set parallel

### Mutation tests

#### Setup and givens:

Once givens steps are wrapped in actions. You can check if all the setup actions are required. Imagine you have 4 setup
actions numbered 1,2,3,4. You could run 1. 1,2. 1,2,3. 1,2,3,4. 1,3 etc... Setup actions could duplicate default state,
that is valid.

The @Given annotation allows for running the test with different values. I.e. my_test(@Given int quantity) could be run
for argument value 0,1,2,3 etc...

It gets interesting when combine actions and parameter values for mutation tests.

### Adding notes

#### Feature notes

For docs there are 2 choices:

1. @BeforeEach calls notes()/doc() once. If testSuiteNotes().text().size() == 0 then add notes. Means that notes could
   only be added in the setuo()/notes()/doc() method.
2. @Notes/@Doc annotation

With feature documentation you probably only want text, html and or markdown. If you want to inject uml then you'll need
to insert notes that are of type text, html, markdown, uml. Maybe need feature().notes().text() .html(), .markdown(),
.uml(). The corresponding scenario().notes()
Add in order you have added. i.e. using add():

```
scenario().notes().text().add("this is an explanation. Below is a diagram.")
scenario().notes().uml().add(...)
scenario().notes().markdown().add("some notes with markdown formatting")
```

Or assuming `add` is the only thing you want to do it can be omitted:

```
scenario().notes().text("this is an explanation. Below is a diagram.")
scenario().notes().uml(...)
scenario().notes().markdown("some notes with markdown formatting")
```

Feature and scenario can have the same notes() object, but feature has a title.

#### Scenario Notes

`scenario().notes()` or just `notes()` as it will be used more and it could be implied. Prefix scenario and feature is
better than testCase and testSuite as it would look like:

`feature`/`scenario` vs `testSuite`/`testCase`

this is long way to access but the user navigate. 
```
context().feature().notes().diagrams().add(new SequenceDiagram())
  .addActor("User")
  .addParticipant("BookStore")
  .addParticipant("ISBNdb");

context().test().notes().diagrams().add(new SequenceDiagram())
  .addActor("User")
  .addParticipant("BookStore")
  .addParticipant("ISBNdb");
```

```
notes().uml() or uml()

testCase().notes().uml()
testSuite().notes().uml()
```

Parent object/accessor `scenario()`/`feature()`. Else `notes().feature()` and `notes().uml()` adding a note and
specifying where to add the note doesn't work keyed off of off `notes()`
Could there be `scenario().store(key, value)`, `scenario().context()` or `scenario().metric()` etc...?

### Test titles to be wordified

Test method names could benefit from being wordified. How to implement. Java properties, overridden in test via an
injected config object?

### Parametrised tests

Need something to reflect a test matrix:

```
ParamTest
* [1] value 1 (PASSED)
  Passing assertion with value 1

* [2] value 2 (PASSED)
  Passing assertion with value 2
```

This means something like:

* `testCaseResult.name` = `[1] value 1`
* `testCaseResult.methodName` = `ParamTest()` should we include the signature???. Could we infer from the params?
* `testCaseResult.parentTest` = `ParamTest()`. Is this needed to group tests??? Is it the same as `methodName`?
* `testCaseResult.args` not empty

#### Options:

* scenario parent and scenario child.
* Or a test is identified with parametrized scenario? `methodName` field relates the parametrized scenarios.

### New args block. With args we can re-run test(s).

```json
{
  "wordify": "Passing assertion with string value 'blah' with int value 1",
  "wordifyTemplate": "Passing assertion with string value '{{ 0 }}' with int value {{ 1 }} ",
  "args": [
    {
      "type": "String",
      "value": "blah"
    },
    {
      "type": "Integer",
      "value": 1
    }
  ],
  "status": "PASSED",
  "methodName": "paramTest",
  "className": "ClassUnderTest",
  "packageName": "shared.undertest"
}
```


### Dummy Objects

Objects that are not under test, but needed to build objects. Potentially these could be ignored when assertJ checks to
equality. We have defaultBuilders that build a default object

```
private SimpleCarBuilder aDefaultCar() {
    return aCar()
        .withEngine(anEngine()
            .withType(ENGINE_TYPE)
            .withSize(ENGINE_SIZE));
}

// style 1
private SimpleCarBuilder aDummyCar() {
    return aCar()
        .withEngine(anEngine()
            .withType(DUMMY_ENGINE_TYPE)
            .withSize(DUMMY_ENGINE_SIZE));
}
SimpleCar simpleCar = aDummyCar().updateEngine().withSize(anotherEngineSize).build;

// style 2
private SimpleCarBuilder aDummyCar() {
    return aCar().withEngine(aDummyEngine());
}
// updating a dummy engine - it needs to be a real engine with dummy values. This doesn't quite work as it would need lots of boiler plate code.
SimpleCar simpleCar = aDummyCar().updateEngine().withSize(anotherEngineSize).build;


// style 3
private SimpleCarBuilder aDummyCar() {
    SimpleCarBuilder car1 = aCar().withEngine(aDefaultEngine()).setAsDummy() 
    SimpleCarBuilder car2 = dummy(aCar().withEngine(aDefaultEngine()));
    ...
}
// updating a dummy engine - it needs to be a real engine with dummy values. If dummy was an annotation then we could add remove at runtime.
SimpleCar simpleCar = aDummyCar().updateEngine().withSize(anotherEngineSize).build;

// assert
assertThat(getCar().withEngine(anotherEngineSize))).isEqualTo(simpleCarBuilder);
```

### Linting Builders

These ideas centre around builders and features that the user may or may not want. That is why linting would be a good
tool. To achieve consistency in the wordify you'd need the builders to be consistent. Note that wordify can have custom
rules, these would break down if the builders are not consistent.

### Try to make some tests more concise

This is using assertJ as normal

```
private void assertTestSuite(TestSuite testSuite) {
    assertThat(testSuite.getName()).isEqualTo("shared.undertest.ClassUnderTest");
    assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
    assertThat(testSuite.getPackageName()).isEqualTo("shared.undertest");
    assertThat(testSuite.getMethodNames()).isEqualTo(asList("testMethod", "paramTest", "paramTest", "paramTest"));
    assertThat(testSuite.getTestCases()).contains(firstTestCase());
    ...
}
```

This is using a builder as normal. This is limiting as you must test all the state. This has the advantage of being more
concise.

```
private TestSuite firstTestSuite() {
    return aTestSuite()
        .withName("shared.undertest.ClassUnderTest")
        .withClassName("ClassUnderTest")
        .withPackageName("shared.undertest")
        .withMethodNames(asList("testMethod", "paramTest", "paramTest", "paramTest"))
        .withTestCases(asList(firstTestCase()))
    ...
    .build();
}
```

from https://assertj.github.io/doc/#assertj-core-assertions-guide
It is possible to use `ignoringFields(String... fieldsToIgnore)` to ignore fields. See dummy objects above.

```
assertThat(sherlock)
  .usingRecursiveComparison()
  .withStrictTypeChecking()
  .isEqualTo(detectiveSherlock);
```

Is something like this possible? It would be more concise?

```
private TestSuite assertFirstTestCase() {
    aTestSuiteAssertionBuilder()
      .withName("shared.undertest.ClassUnderTest") // assumes equality 
      .withMethodNames().contains(..) // can we chain assertJ methods would they have to be hamcrest?
      .withOtherField().startsWith(...) 
}
```

### Closet matching

- Closet matching fields. I.e. 3/4 fields match
    - `assertThat(actual).isEqualTo(expected);`
    - `assertThat(list).contains(item);`

### Reporting ideas:

* Compare log of previous run - show a diff. Can have a cut down version of the log that only shows the dif - hence what
  has gone wrong.
* Send reports to report aggregator on each test, suite, package or at the end
* Keep track of irregular issues

