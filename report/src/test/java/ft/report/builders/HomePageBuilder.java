package ft.report.builders;

import io.bitsmart.bdd.report.report.model.HomePage;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;

public final class HomePageBuilder {
    private TestSuiteLinks links;
    private TestSuiteSummary summary;

    private HomePageBuilder() {
    }

    public static HomePageBuilder aHomePage() {
        return new HomePageBuilder();
    }

    public HomePageBuilder withLinks(TestSuiteLinks links) {
        this.links = links;
        return this;
    }

    public HomePageBuilder withSummary(TestSuiteSummary summary) {
        this.summary = summary;
        return this;
    }

    public HomePage build() {
        return new HomePage(links, summary);
    }
}
