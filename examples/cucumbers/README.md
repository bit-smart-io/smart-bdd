Exampled to show simple usage. Mostly how to get going with builders.

2 Test Suites:
* EatCucumbersTest - Tutorial and example usage of builders
* EatCucumbersWithoutBuildersTest - Tutorial and example usage of without builders. You don't want to do this, but it's possible.


Below is an extract from https://cucumber.io/docs/gherkin/reference/ it shows how Cucmumber can elegantly test very simple services.
Scenario: eat 5 out of 12
Given there are 12 cucumbers
When I eat 5 cucumbers
Then I should have 7 cucumbers

Scenario Outline: eating
Given there are <start> cucumbers
When I eat <eat> cucumbers
Then I should have <left> cucumbers
Examples:
| start | eat | left |
|    12 |   5 |    7 |
|    20 |   5 |   15 |
