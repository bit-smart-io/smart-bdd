TODOs
- [x] Parse params
- [x] Have a context
- [ ] Have failing tests
- [ ] Handle Strings i.e. anything in quotes
- [ ] @Given, @When, @Then
- [ ] @Notes  
- [ ] UnderTest fields
- [ ] Log extra for the report
- [ ] Sequence diagrams  
- [ ] Create report in Json
- [ ] Create the website from the report

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