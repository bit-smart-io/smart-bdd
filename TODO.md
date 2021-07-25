TODOs
- [x] Parse params
- [x] Have a context
- [x] Have failing tests
- [x] Capture the failures
- [x] Stats for passed, skipped, aborted and failed
- [x] Handle names of methods that have params
- [ ] Read the @BeforeEach @BeforeAll etc... and add to the result
- [ ] Add timestamp, hostname, time (maybe setup, execute and after times)
- [ ] Handle all test factories, templates and dynamic  
- [x] Capture standard output and standard error mechanism.
- [ ] Apply Capture standard output and standard error.
- [ ] Handle Strings i.e. anything in quotes
- [ ] Additional wordifies i.e. swap words around to create more readable sentences
- [ ] @Given, @When, @Then
- [ ] @Notes  
- [ ] UnderTest fields - this is to highlight fields, possible change the values
- [ ] Logger than can added extra text in the report 
- [ ] Sequence diagrams - mermaid 
- [x] Create report in Json
- [x] Website prototype
- [ ] Create the website from the report - maybe vue or jquery? Load the json rather than static pages.
- [ ] Re-run tests endpoints
- [ ] Re-run tests ui
- [ ] Update test values in the ui - so that the test can be re-run with different values
- [ ] Store the tests in a DB
- [ ] Project structure - need modules/projects 
- [x] Use Kotlin for Gradle
- [ ] Explore when and how builders should be the same for ft and unit tests. See if feasible to sync builders class and or package

Ideas
-  Closet matching fields. I.e. 3/4 fields match
  - assertThat(actual).isEqualTo(expected);
  - assertThat(list).contains(item);
- Dummy Objects
- AssertingClasses 

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

This is using a builder as normal. This is limiting as you must test all the state.
This has the advantage of being more concise.  
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

Is something like this possible? It would be more concise?
```
private TestSuite assertFirstTestCase() {
    aTestSuiteAssertionBuilder()
      .withName("shared.undertest.ClassUnderTest") // assumes equality 
      .withMethodNames().contains(..) // can we chain assertJ methods would they have to be hamcrest?
      .withOtherField().startsWith(...) 
}
```


### Add vue web app
Can't load local files after page has loaded, possibly complied due to security.

Options for the web reports:
1. Inject JSON in to index.html
2. Use npm to compile the webapp
3. Have a start script that starts up a local webserver
 