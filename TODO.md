TODOs
- [x] Parse params
- [x] Have a context
- [ ] Handle Strings i.e. anything in quotes
- [ ] @Given, @When, @Then
- [ ] @Notes  
- [ ] UnderTest fields
- [ ] Log extra for the report
- [ ] Sequence diagrams  
- [ ] Create report in Json
- [ ] Create the website from the report

```
// https://cucumber.io/docs/gherkin/reference/
Scenario: eat 5 out of 12
Given there are 12 cucumbers
When I eat 5 cucumbers
Then I should have 7 cucumbers

Scenario: eat 5 out of 20
Given there are 20 cucumbers
When I eat 5 cucumbers
Then I should have 15 cucumbers

Scenario Outline: eating
Given there are <start> cucumbers
When I eat <eat> cucumbers
Then I should have <left> cucumbers

Examples:
| start | eat | left |
|    12 |   5 |    7 |
|    20 |   5 |   15 |

@Example("
| start | eat | left |
|    12 |   5 |    7 |
|    20 |   5 |   15 |")
eatCucumbers(
)

// TODO is this possible?
eatCucumbers(
    @Given(12,20) int startingAmount,
    @When(5,5) int amountToEat,
    @Then(7,15) int amountLeft) 
{
    given(thereAre().cucumbers(startingAmount))
    when(iEat().cucumbers(amountToEat))
    then(iShouldHave().cucumbers(amountLeft))
}
given there are cucumbers 12
when i eat cucumbers 5
then i should have cucumbers 7

// Can we switch to have
// can this be a simple rule? 
// Or annotations to switch cucumbers(12) to 12 cucumbers
given there are 12 cucumbers
when i eat 5 cucumbers
then i should have 7 cucumbers


@Given(12,20)
@When(5,5)
@Then(7,15)
eatCucumbers(int startingAmount, int eat, int amountLeft) {
    given(cucumbers(startingAmount))
    when(eat().cucumbers(5))
    then(cucumbersLeft(7))
}

@Given(12,20)
@When(5,5)
@Then(7,15)
buyShirt(int start, int eat, int left) {
    given(thereAre().cucumbers(12))
    when(iEat().cucumbers(12))
    then(iShouldHave().cucumbers(12))
}

@Given(12,20)
@When(5,5)
@Then(7,15)
buyShirt(int start, int eat, int left) {
    given(thereAre(12).cucumbers())
    when(iEat(12).cucumbers())
    then(iShouldHave(7).cucumbers())
}

@Given(12,20)
@When(5,5)
@Then(7,15)
buyShirt(int start, int eat, int left) {
    given(thereAre(12, CUCUMBERS)
        when(iEat(12, CUCUMBERS)
            then(iShouldHave(7, CUCUMBERS)
}
```