/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.bitsmart.bdd.ft.report.infrastructure.json.builders;

import io.bitsmart.bdd.ft.report.infrastructure.json.model.DataReportIndex;
import io.bitsmart.bdd.report.utils.Builder;

import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;

public final class ReportIndexBuilder implements Builder<DataReportIndex> {
    private TestSuiteLinksBuilder links = aTestSuiteLinks();
    private TestSuiteSummaryBuilder summary = aTestSuiteSummary();

    private ReportIndexBuilder() {
    }

    public static ReportIndexBuilder aReportIndex() {
        return new ReportIndexBuilder();
    }

    public ReportIndexBuilder withLinks(TestSuiteLinksBuilder links) {
        this.links = links;
        return this;
    }

    public ReportIndexBuilder withSummary(TestSuiteSummaryBuilder summary) {
        this.summary = summary;
        return this;
    }

    @Override
    public DataReportIndex build() {
        return new DataReportIndex(links.build(), summary.build());
    }
}
